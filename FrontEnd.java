package p2;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FrontEnd {
    public FrontEnd(){

    }

    public void phase_01_browser(PrintWriter out) {
	out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset ='UTF-8'>");
        out.println("<title> Fase 01 </title>");
        out.println("<link rel='stylesheet' type='text/css'  href = 'p2.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información musical</h1>");
        out.println("<h1>Bienvenido a este servicio</h1>");
        out.println("<h2>Selecciona una consulta</h2>");
        out.println("<h3>");
        out.println("<ul>");
        out.println("<li type='circle'> <a href = '?pphase=02&p=Carlos1234' target = '_self'>Ver los ficheros erróneos </a> </li>"); //_self es para que se abra el enlace en la misma página
        out.println("<li type='circle'> <a href = '?pphase=21&p=Carlos1234' target = '_self'> Consulta 2: álbumes de una compañía con canciones en un idioma </a> </li>");
        // target = '_self' --> link en la misma ventana (opción por defecto)
        out.println("</ul>");
        out.println("</h3>");
        out.println("<br> <br> Carlos Fernández Deus (2022-23)");
        out.println("</body>");
        out.println("</html>");
    }
    
    public void phase_01_auto(PrintWriter out){
            out.println("<?xml version='1.0' encoding ='utf-8'?>");
            out.println("<service>");
            out.println("   <status>OK</status>");
            out.println("</service>");
    }

    public void phase_02_browser(PrintWriter out, ArrayList<String> LFE, ArrayList<String> LFE_fatal){
        int num_errores = LFE.size();
        int num_erroresFatales = LFE_fatal.size();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset ='UTF-8'>");
        out.println("<title> Ficheros erroneos </title>");
        out.println("<link rel='stylesheet' href = 'p2.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información musical</h1>");
        out.println("<h2>Ficheros con errores: " + num_errores + "</h2>");
        out.println("<h3>");
        out.println("<ol>");
        for (int i = 0; i < num_errores; i++) {
           out.println("<ul>" + LFE.get(i) + "</ul>");
        }
        out.println("</ol>");
        out.println("</h3>");
        out.println("<h2>Ficheros con errores fatales: " + num_erroresFatales + "</h2>");
        out.println("<h3>");
        out.println("<ol>");
        for (int i = 0; i < num_erroresFatales; i++) {
           out.println("<ul>" + LFE_fatal.get(i) + "</ul>");
        }
        out.println("</ol>");
        out.println("</h3>");
        out.println("<a id = button href = '?p=Carlos1234'> <input type='button' value='Atrás'></a> ");
        out.println("<br> <br>Carlos Fernández Deus (2022-23)");
        out.println("</body>");
        out.println("</html>");
    }


    public void phase_02_auto(PrintWriter out, ArrayList<String> LFE, ArrayList<String> LFE_fatal){
        int num_errores = LFE.size();
        int num_erroresFatales = LFE_fatal.size();

        out.println("<?xml version='1.0' encoding ='utf-8'?>");
        out.println("<wrongDocs>");
        out.println("<errors>");
        for (int i = 0; i < num_errores; i++) {
            String url_i = LFE.get(i);
            // Parseo
            String xml_i = url_i.substring(url_i.lastIndexOf("/")+1);
            out.println("<error><file>" + xml_i + "</file></error>");
        }
        out.println("</errors>");
        out.println("<fatalerrors>");
        for (int i = 0; i < num_erroresFatales; i++) {
            String url_i = LFE_fatal.get(i);
            // Parseo
            String xml_i = url_i.substring(url_i.lastIndexOf("/")+1);
            out.println("<fatalerror><file>" + xml_i + "</file></fatalerror>");
        }
        out.println("</fatalerrors>");
        out.println("</wrongDocs>");
    }

    public void phase_21_browser(PrintWriter out, ArrayList<String> langs){
        int NumLangs = langs.size();
	
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset ='UTF-8'>");
        out.println("<title> Fase 21 </title>");
        out.println("<link rel='stylesheet' href = 'p2.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información musical</h1>");
        out.println("<h2>Consulta 2: Fase 1</h2>");
        out.println("<h2>Selecciona un idioma: </h2>");
        out.println("<h3>");
        out.println("<ol>");
        for(int i=0; i<NumLangs; i++){
            out.println("<li> <a href = '?pphase=22&p=Carlos1234&plang=" + langs.get(i) + "' target = '_self'>" + langs.get(i) + "</li>");
        }
        out.println("</ol>");
        out.println("</h3>");    
        out.println("<a id = button href = '?p=Carlos1234'> <input type='button' value='Inicio'></a> ");
        out.print("&nbsp; &nbsp; <a id = button href = '?p=Carlos1234'> <input type='button' value='Atrás'></a> ");
	out.println("<br> <br>Carlos Fernández Deus (2022-23)");
	out.println("</body>");
        out.println("</html>");

    }
    
    public void phase_21_auto(PrintWriter out, ArrayList<String> langs){
        int NumLangs = langs.size();       

        out.println("<?xml version='1.0' encoding ='utf-8'?>");
        out.println("<langs>");
        for(int i=0; i<NumLangs; i++){
            out.println("   <lang>" + langs.get(i) + "</lang>");
        }
        out.println("</langs>");
    }

    public void phase_22_browser(PrintWriter out, ArrayList<Song> songs, String lang) {
        int numSongs = songs.size();
	
	out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset ='UTF-8'>");
        out.println("<title> Fase 22 </title>");
        out.println("<link rel='stylesheet' href = 'p2.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información musical</h1>");
        out.println("<h2>Consulta 2: Fase 2 (Idioma =" + lang + ")</h2>");
        out.println("<h2>Selecciona un Album:</h2>");
        out.println("<h3>");
        out.println("<ol>");

        /*
        for(int i=0; i<numSongs; i++){
                out.println("<li> <a href = '?pphase=23&p=Carlos1234&plang=" + lang + "&psid=" + songs.get(i).getSid()  +"' target = '_self'> Álbum = '" + albums.get(i).getName() + "' </a>");
                if(albums.get(i).getGroup() == null) {
                	out.print(" --- Año = '" + albums.get(i).getYear() + "' --- Intérprete = '" + albums.get(i).getSinger()+ "' --- Review = '" + albums.get(i).review + "' </li>");
                }else if(albums.get(i).getSinger() == null){
                    	out.print(" --- Año = '" + albums.get(i).getYear() + "' --- Intérprete = '" + albums.get(i).getGroup() + "' --- Review = '" + albums.get(i).review + "' </li>");
                }
        }
        */

        for(int i=0; i<numSongs; i++){
            out.println("<li> <a href = '?pphase=23&p=Carlos1234&plang=" + lang + "&psid=" + songs.get(i).getSid()  +"' target = '_self'> --- Álbum = '" + songs.get(i).getAlbum() + "' --- Duración = '" + songs.get(i).getDuration() +"' --- Géneros = '" + songs.get(i).getGenre() + "' </a> </li>");
        }
        out.println("</ol>");
        out.println("</h3>");
        out.println("<a id = button href = '?p=Carlos1234'> <input type='button' value='Inicio'></a> ");
        out.print("&nbsp; &nbsp; <a id = button href = '?p=Carlos1234&pphase=21'> <input type='button' value='Atrás'></a> ");        
	out.println("<br> <br>Carlos Fernández Deus (2022-23)");
        out.println("</body>");
        out.println("</html>");
    }

    public void phase_22_auto(PrintWriter out, ArrayList<Song> songs, String lang) {
        int numSongs = songs.size();
	
	out.println("<?xml version='1.0' encoding ='utf-8'?>");
        out.println("<songs>");
        /*
        for(int i=0; i<n_albums; i++){
                Album album_i = albums.get(i);
		if(album_i.getGroup() == null){
                	out.println("   <album year='" + album_i.getYear() + "' performer='" + album_i.getSinger() + "' review='" + album_i.review + "'>" + album_i.getName() + "</album>");
                }else if(album_i.getSinger() == null){
                    	out.println("   <album year='" + album_i.getYear() + "' performer='" + album_i.getGroup() + "' review='" + album_i.review + "'>" + album_i.getName() + "</album>");
                }
	}
        out.println("</songs>");
        */
        for(int i=0; i<numSongs; i++){
            // REVISAR SI CON /n FUNCIONA
            out.println("<song album='" + songs.get(i).getAlbum() + "' duration='" + songs.get(i).getDuration() +"' genres='" + songs.get(i).getGenre() + "'");
            out.println(songs.get(i).getName + "</song>");
        }
        out.println("</songs>");
    }

    public void phase_33Browser(PrintWriter out, ArrayList<Album> albums, String song, String sid){
        int numAlbums = albums.size();

	out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset ='UTF-8'>");
        out.println("<title> Fase 23 </title>");
        out.println("<link rel='stylesheet' href = 'p2.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información musical</h1>");
        out.println("<h2>Consulta 2: Fase 3 (Idioma =" + lang + ", Canción =" + sid + ")</h2>");
        out.println("<h2>Este es el resultado de la consulta:</h2>");
        out.println("<h3>");
        out.println("<ol>");
        for(int i=0; i<numAlbums; i++){
            out.println("<li> Álbum = '" + albums.get(i).getName() + "' --- País = '" + albums.get(i).getCountry() + "' --- Compañía = '" + albums.get(i).getCompany() + "' --- Crítica = '" + albums.get(i).getReview() + "' </li>");
        }
        out.println("</ol>");
        out.println("</h3>");
        out.println("<a id = button href = '?p=Carlos1234'> <input type='button' value='Inicio'></a> ");
	    out.print("&nbsp; &nbsp; <a id = button href = '?p=Carlos1234&pphase=22&plang=" + lang  + "'> <input type='button' value='Atrás'></a> ");
        out.println("<br> <br>Carlos Fernández Deus (2022-23)");
        out.println("</body>");
        out.println("</html>");
    }
    
    public void phase_13Auto(PrintWriter out, ArrayList<Album> albums, String lang, String sid){
        int n_songs = songs.size();
	
	out.println("<?xml version='1.0' encoding ='utf-8'?>");
        out.println("<songs>");
        for(int i=0; i<n_songs; i++){
            Song song_i = songs.get(i);
            out.println("<song lang='" + song_i.getLang() + "' genres = '" + song_i.getGenre() + "' composer = '" + song_i.getComposer() + "'>" + song_i.getTitle() + "</song>");
        }
        out.println("</songs>");
    }

    public void errorContrasenaBrowserBad(PrintWriter out){
	out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset ='UTF-8'>");
        out.println("<title> wrong Request </title>");
        out.println("<link rel='stylesheet' href = 'p2.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>bad passwd</h1>");
        out.println("<br> Carlos Fernández Deus (2022-23)");
        out.println("</body>");
        out.println("</html>");
    }
	
    public void errorContrasenaBrowserNull(PrintWriter out){
	out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset ='UTF-8'>");
        out.println("<title> wrong Request </title>");
        out.println("<link rel='stylesheet' href = 'p2.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>no passwd</h1>");
        out.println("<br> Carlos Fernández Deus (2022-23)");
        out.println("</body>");
        out.println("</html>");
    }

    public void errorContrasenaAutoBad(PrintWriter out){
	out.println("<?xml version='1.0' encoding ='utf-8'?>");
        out.println("<wrongRequest>bad passwd</wrongRequest>");

    }

    public void errorContrasenaAutoNull(PrintWriter out){
	out.println("<?xml version='1.0' encoding ='utf-8'?>");
        out.println("<wrongRequest>no passwd</wrongRequest>");
    }

    public void errorCountryBrowser(PrintWriter out){
    	out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset ='UTF-8'>");
        out.println("<title> wrong Request </title>");
        out.println("<link rel='stylesheet' href = 'p2.css'>");
        out.println("</head>");
        out.println("<body>"); 
        out.println("<h1>no param: pcountry</h1>");
        out.println("<br> Carlos Fernández Deus (2022-23)");
        out.println("</body>");
        out.println("</html>");
    }

    public void errorCountryAuto(PrintWriter out){
    	out.println("<?xml version='1.0' encoding ='utf-8'?>");
        out.println("<wrongRequest>no param:pcountry</wrongRequest>");

    }

    public void errorAidBrowser(PrintWriter out){
	out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset ='UTF-8'>");
        out.println("<title> wrong Request </title>");
        out.println("<link rel='stylesheet' href = 'p2.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>no param: paid</h1>");
        out.println("<br> Carlos Fernández Deus (2022-23)");
        out.println("</body>");
        out.println("</html>");
    }

    public void errorAidAuto(PrintWriter out){
        out.println("<?xml version='1.0' encoding ='utf-8'?>");
        out.println("<wrongRequest>no param:paid</wrongRequest>");
    }
}
