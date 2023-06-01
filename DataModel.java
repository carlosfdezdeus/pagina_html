package p2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;	//Para poder crear un elemento Node
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class DataModel{ 
    String URL_PREFIX = "http://alberto.gil.webs.uvigo.es/SINT/22-23/";
    String URL_XML = "muml2001.xml";
    Document doc;
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    

    ArrayList<String> LFV = new ArrayList<String>();
    ArrayList<String> LFE = new ArrayList<String>();
    ArrayList<String> LFE_fatal = new ArrayList<String>();
    HashSet<Document> mapDocs = new HashSet<Document>();           

    public DataModel(){
        //Buscar(URL_XML);
	try {
 	    System.out.println("Entro en Constructor");
            String URI_XML = "http://alberto.gil.webs.uvigo.es/SINT/22-23/muml2001.xml";
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(URI_XML); // Obtiene el árbol DOM
        }catch (ParserConfigurationException ex){
            System.out.println(ex);
        }catch (SAXException ex){
            System.out.println(ex);
        }catch(IOException ex){
            System.out.println(ex);
        }
	Buscar(URL_XML);
    }
	
    public void Buscar(String url) {
        if (url.contains("http") != true) {
            url = URL_PREFIX.concat(url);
        }
	url = url.trim();
        LFV.add(url);
	
	System.out.println(url);
	
        try {
            doc = db.parse(url); // Obtiene el árbol DOM
       	    System.out.println(doc);
//	} catch (ParserConfigurationException ex) {
//            System.out.println(ex);
        } catch (SAXException ex) {
            System.out.println(ex);
	    if(LFE_fatal.contains(url) == false){
	        LFE_fatal.add(url);
            }
	    return;
        } catch (IOException ex) {
            System.out.println(ex);
        }

        NodeList nlYear = doc.getElementsByTagName("Year");
        Element e_year = (Element) nlYear.item(0);
        int year = Integer.parseInt(e_year.getTextContent().trim());
	System.out.println(year);
  
	if ((year < 1980) || (2021 < year)) {
	    //AÑO INCORRECTO
	    System.out.println("Fichero erroneo POR AÑO");
            LFE.add(url);
	    return;
	}
	/*else {
	    //AÑO CORRECTO --> Compruebo si duration es correcta	
            NodeList nlDuration = doc.getElementsByTagName("Duration");
            int num_Duration = nlDuration.getLength();

            for (int du = 0; du < num_Duration; du++) {
		System.out.println(du);
                Element e_duration = (Element) nlDuration.item(du);
                int duration = Integer.parseInt(e_duration.getTextContent().trim());
                System.out.println(duration);
		if ((duration < 120) || (600 < duration)) {
                    System.out.println("Fichero erroneo POR DURATION");
		    LFE.add(url);
		    break; 
                }	
            }
        }*/
	System.out.println("Llego a añadir documento al mapDocs");
        mapDocs.add(doc);

        NodeList nlMuML = doc.getElementsByTagName("MuML");
        int num_MuML = nlMuML.getLength();
	System.out.println("Numero de MuML = "+ num_MuML);
        for (int mu = 0; mu < num_MuML; mu++) {
	    System.out.println(mu + "<" + num_MuML);
            Element e_MuML = (Element) nlMuML.item(mu);
            String new_url_xml = e_MuML.getTextContent().trim();
            System.out.println("new_url_xml: "+new_url_xml);
	    if (LFV.contains(new_url_xml) != true) {
                System.out.println("Vuelvo a inicialiar Buscar");
		Buscar(new_url_xml);
            }
        }
        return;
    }

    public ArrayList<String> getQ2Langs(){
        System.out.println("Entro en getQ2Langs");
        System.out.println(doc);
        int numLangs, numSongs, i;
        HashSet<String> langs = new HashSet<>(); //Creo un HashSet para que no me aperezca dos veces Spain en el ArrayList.

        ArrayList<Document> documents = new ArrayList<Document>(mapDocs);
        int num_docs = mapDocs.size();
        for (int d = 0; d < num_docs; d++) {
            doc = documents.get(d);
	    String url_doc = doc.getDocumentURI();
	    if(LFE.contains(url_doc)){
	        continue;
	    } 
            NodeList nodeListSongs = doc.getElementsByTagName("Song");  //Guardo los nodos en una nodelistl
            numSongs = nodeListSongs.getLength();

            for(i = 0; i<numSongs; i++) {
            System.out.println("Entro bucle 1");
                Element elementSong_i = (Element) nodeListSongs.item(i); // Los elementos de la NodeList son Node hay que hacer una conversión de tipo
                String lang_i = elementSong_i.getAttribute("lang");
                langs.add(lang_i);
            }
            ArrayList<String> listaLangs = new ArrayList<String>(langs); //Creo el ArrayList con los Langs encontrados.
                    //Collections.sort(listaCountries, Collections.reverseOrder); //Ordeno de manera inversamente alfabética: Collections.sort() --> Ordeno alfabeticamente, Colletions.reserveOrder() --> invierto
            Collections.sort(listaLangs, Collections.reverseOrder());
            return listaLangs;	
            /* 
                        NodeList nlCountries = doc.getElementsByTagName("Country");  //Guardo los
                        num_countries = nlCountries.getLength();

                        for (i = 0; i < num_countries; i++) {
                            System.out.println(i);
                            Element elementCountry_i = (Element) nlCountries.item(i); // Los elementos de la NodeList son Node hay que hacer una conversión de tipo
                            String country_i = elementCountry_i.getTextContent().trim();
                            countries.add(country_i);
                        }//Si no entra en el if la lista va a estar vacía.
                    }
                    ArrayList<String> listaCountries = new ArrayList<String>(countries); //Creo el ArrayList con los Countries encontrados.
                    //Collections.sort(listaCountries, Collections.reverseOrder); //Ordeno de manera inversamente alfabética: Collections.sort() --> Ordeno alfabeticamente, Colletions.reserveOrder() --> invierto
                Collections.sort(listaCountries, Collections.reverseOrder());
                return listaCountries;	
            */
    }

    public ArrayList<Song> getQ2Songs(String lang){
	int num_albums, i;
        ArrayList<Album> listaAlbums = new ArrayList<Album>();
	
	ArrayList<Document> documents = new ArrayList<Document>(mapDocs);
	int num_docs = documents.size();
	if (country == null){
		return null;
	}
	for(int d = 0; d<num_docs; d++){
	    doc = documents.get(d);
	    String url_doc = doc.getDocumentURI();
            if(LFE.contains(url_doc)){
                continue;
            } 
	    
	    NodeList nlAlbums = doc.getElementsByTagName("Album");  //Guardo los
            num_albums = nlAlbums.getLength();

            for(i = 0; i<num_albums; i++) {
                Element element_Album_i = (Element) nlAlbums.item(i); // Los elementos de la NodeList son Nodo hay que hacer una conversión de tipo
                NodeList nlCountries = element_Album_i.getElementsByTagName("Country");
                Element e_country_album_i = (Element)nlCountries.item(0); //Se coge el item 0 porque solo hay un country por Album --> la nodeList es de un solo elemento
                String country_album_i = e_country_album_i.getTextContent().trim();
            	    if(country_album_i.equals(country)){
                        NodeList nlName_i = element_Album_i.getElementsByTagName("Name");
                	Element e_name_i = (Element) nlName_i.item(0);
                	String name_i = e_name_i.getTextContent().trim();

                	NodeList nlHijos = element_Album_i.getChildNodes();
                	int num_hijos = nlHijos.getLength();
                	String review = "";
                	for(int re = 0; re < num_hijos; re++){
                    	    Node hijo = nlHijos.item(re);
                    	    short tipo = hijo.getNodeType(); //devuelve el tipo de elemento que es
                    	    short tipo_texto = org.w3c.dom.Node.TEXT_NODE;  //nodo = Nodo de texto
                    	    if(tipo == tipo_texto){ //Si el tipo que se analiza no es un elemento y es texto --> es la review
                                review = review + hijo.getNodeValue();
                        }
                    }
		    review = review.trim();
                    NodeList nlYear_i = doc.getElementsByTagName("Year");
                    Element e_year_i = (Element) nlYear_i.item(0);
                    int year_i = Integer.parseInt(e_year_i.getTextContent().trim());

                    String aid_i = element_Album_i.getAttribute("aid");

                    NodeList nlSinger_i = element_Album_i.getElementsByTagName("Singer");

                    if(nlSinger_i.item(0) == null){
                        NodeList nlGroup_i = element_Album_i.getElementsByTagName("Group");
                        Element e_group_i = (Element) nlGroup_i.item(0);
                        String group_i = e_group_i.getTextContent().trim();

                        Album album_i = new Album(name_i, year_i,null, group_i, review, aid_i);
                        listaAlbums.add(album_i);
                    }else{
                        Element e_singer_i = (Element) nlSinger_i.item(0);
                        String singer_i = e_singer_i.getTextContent().trim();

                        Album album_i = new Album(name_i, year_i,null, singer_i, review, aid_i);
                        listaAlbums.add(album_i);
                    }
                }
	    }
        }//Si no entra en el if la lista va a estar vacía.
	Collections.sort(listaAlbums, new Comparator<Album>() {
            @Override
            public int compare(Album a1, Album a2) {
                 if (a1.getYear() != a2.getYear()) {
                    return new Integer(a1.getYear()).compareTo(new Integer(a2.getYear())); 
                }else{
                    return a1.getName().compareTo(a2.getName());
                }
            }
        });
	return listaAlbums;
    }
    
        public ArrayList<Song> getQ1Songs(String country, String album){
        System.out.println("Entro en getQ1Songs");
	int num_albums, num_songs, num_genres, i, j;
        ArrayList<Song> listaSongs = new ArrayList<Song>();
	
	ArrayList<Document> documents = new ArrayList<Document>(mapDocs);
        int num_docs = mapDocs.size();
        
	if(country == null){
		return null;
	}if(album == null){
		return null;
	}
	for (int d = 0; d < num_docs; d++) {
            doc = documents.get(d);
            String url_doc = doc.getDocumentURI();
            if(LFE.contains(url_doc)){
                continue;
            }

	    NodeList nlAlbums = doc.getElementsByTagName("Album");  //Guardo los nodos en una nodelistl
            num_albums = nlAlbums.getLength();

            for(i = 0; i<num_albums; i++) {
	    	System.out.println("Entro bucle 1");
            	Element elementAlbum_i = (Element) nlAlbums.item(i); // Los elementos de la NodeList son Node hay que hacer una conversión de tipo
            	String aid_i = elementAlbum_i.getAttribute("aid");
            	if(aid_i.equals(album)){
		    System.out.println("Encuentro album con mismo aid");
                    NodeList nlCountry_i = elementAlbum_i.getElementsByTagName("Country");
                    Element e_country_i = (Element) nlCountry_i.item(0);
                    String country_i = e_country_i.getTextContent().trim();
                    
		    if(country_i.equals(country)){
 	                System.out.println("Encuentro album con mismo country");
                        NodeList nlSongs = elementAlbum_i.getElementsByTagName("Song");
                	num_songs = nlSongs.getLength();

                    	for(j = 0; j < num_songs; j++) {
            	    	    System.out.println("Entro bucle 2");
                       	    Element elementSong_j = (Element) nlSongs.item(j);
                            String lang_j = elementSong_j.getAttribute("lang");

                       	    NodeList nlTitle_j = elementSong_j.getElementsByTagName("Title");
                       	    Element e_title_j = (Element) nlTitle_j.item(0);
                       	    String title_j = e_title_j.getTextContent().trim();

                       	    NodeList nlGenre_i = elementSong_j.getElementsByTagName("Genre");
                       	    num_genres = nlGenre_i.getLength();
			    HashSet<String> hashSet_genres = new HashSet<String>();
                       	    for (int ge = 0; ge < num_genres; ge++){
                          	Element e_genre = (Element) nlGenre_i.item(ge);
                                String genre_ge = e_genre.getTextContent().trim();
				hashSet_genres.add(genre_ge);
                            }
			    ArrayList<String> genres = new ArrayList<String>(hashSet_genres);
                            String genre_j = String.join(",", genres);

                            NodeList nlComposer_j = elementSong_j.getElementsByTagName("Composer");
                            Element e_composer_j = (Element) nlComposer_j.item(0);
                            String composer_j = e_composer_j.getTextContent().trim();
			    if(genres.contains("Pop") == true){
                            	Song song_i = new Song(title_j, lang_j, genre_j, composer_j, null);
                            	listaSongs.add(song_i);
			    }
                    	}   
                    }
		}
            }//Si no entra en el if la lista va a estar vacía.
	}
	Collections.sort(listaSongs, new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                String[] genres_s1 = s1.getGenre().split(",");
                int num_genres_s1 = genres_s1.length;
                String[] genres_s2 = s2.getGenre().split(",");
                int num_genres_s2 = genres_s2.length;

                 if (num_genres_s1 != num_genres_s2) {
                    return new Integer(num_genres_s1).compareTo(new Integer(num_genres_s2));
                }else{
                    return s1.getTitle().compareTo(s2.getTitle());
                }
            }
        });
        
	return listaSongs;
    }

}
