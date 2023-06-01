package p2;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.*;
//@WebServlet ("/Sint45P2")
public class Sint45P2 extends HttpServlet {
    DataModel dataModel;
    FrontEnd frontend;    

    public void init(ServletConfig conf) throws ServletException {
	super.init(conf);
	frontend = new FrontEnd();
	dataModel = new DataModel();
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        /*  Parametros:
         *  req: a través del cual se accederá a toda la información de la solicitud
         *  res: a través del cual se configura y escribe la respuesta
         */
        int pphase = 1;
	String contrasena = "Carlos1234";	
	ArrayList<String> listaLangs;
	ArrayList<Song> listaSongs;	
	ArrayList<Album> listaAlbums;

	res.setContentType("text/html");
	
        String strModo = req.getParameter("auto");
        String strContrasena = req.getParameter("p");
        String strFase = req.getParameter("pphase");
		String strLang = req.getParameter("plang");
		String strSid = req.getParameter("psid");
        
        PrintWriter out = res.getWriter();
	ArrayList<String> LFE = dataModel.LFE;
	ArrayList<String> LFE_fatal = dataModel.LFE_fatal;
	
	listaLangs = dataModel.getQ2Langs();
	listaSongs = dataModel.getQ2Songs(strLang);
	listaAlbums = dataModel.getQ2AlbumsSongs(strLang, strSid);	
	
        if((strModo == null) || (strModo.equals("true") != true)){
            //MODO BROWSER
	   if(strContrasena != null){
           	if(strContrasena.equals(contrasena)){
		    System.out.println("Contraseña correcta");
                    //CONTRASEÑA CORRECTA
                	if (strFase == null) {
                    	    // PARAMETRO FASE VACIO = PPHASE01
		    	    frontend.phase_01_browser(out);
			}else{
		    	    pphase = Integer.parseInt(strFase); //Parseo de string a int
		    	    switch (pphase){
		    	    case 1:
		  	    	frontend.phase_01_browser(out);
				break;

		    	    case 2:
				frontend.phase_02_browser(out, LFE, LFE_fatal);
				break;
			
		    	    case 11:
				frontend.phase_11_browser(out, listaCountries);
				break;

		    	    case 12:
				if(listaAlbums != null && strCountry != null){
					frontend.phase_12_browser(out, listaAlbums, strCountry);
				}else{
					frontend.errorCountryBrowser(out);
				}
				break;

		            case 13:
				if(listaSongs == null && strCountry == null){
                	     		frontend.errorCountryBrowser(out);
	                        }else if (listaSongs == null && strAid == null){
        	                        frontend.errorAidBrowser(out);
                                }else{
					frontend.phase_13Browser(out, listaSongs, strCountry, strAid);
				}
				break;
			
		    	    default:
				break;
		    	    }
			}
		    }else{
		    	frontend.errorContrasenaBrowserBad(out);
		    }
	    }else{
		frontend.errorContrasenaBrowserNull(out);
	    }
	}else{
	    //MODO AUTO
	    if(strContrasena != null){
	    	if(strContrasena.equals(contrasena)){
                    //CONTRASEÑA CORRECTA
                    if (strFase == null) {
                    	// PARAMETRO FASE VACIO = PPHASE01
		    	frontend.phase_01_auto(out);
		    }else{
		    	// PARAMETRO FASE != NULL -> Conversion de tipo a Integer
		    	pphase = Integer.parseInt(strFase); //Parseo de string a int

		    	switch (pphase){
		    	case 1:
			    frontend.phase_01_auto(out);
			    break;

		    	case 2:
			    frontend.phase_02_auto(out, LFE, LFE_fatal);
			    break;
			
		  	case 11:
			    frontend.phase_11_auto(out, listaCountries);
			    break;

		    	case 12:
			    if(strCountry != null && listaAlbums != null){
			    	frontend.phase_12_auto(out, listaAlbums, strCountry);
			    }else{
				frontend.errorCountryAuto(out);
			    }
			    break;

		    	case 13:
			    if(strCountry == null && listaSongs == null){
				frontend.errorCountryAuto(out);
			    }else if (strAid == null && listaSongs == null){
			    	frontend.errorAidAuto(out);
			    }else{
			        frontend.phase_13Auto(out, listaSongs, strCountry, strAid);
			    }
			    break;
			
		    	default:
			    break;
		    	}
		    }
		}else{
		    frontend.errorContrasenaAutoBad(out);
		}
	    }else{
		    frontend.errorContrasenaAutoNull(out);
	    }
	}
    }
}


