package edu.escuelaing.arep.ASE.app;

import static spark.Spark.*;

import spark.Request;
import spark.Response;

/**
 * 
 * @author Natalia Palacios
 *
 */
public class App {
	
	/**
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {        
    	
    	//setPort(4567);
        port(getPort());
        get("/introDatos", (req, res) -> introducirDatos(req, res));
        get("/operaciones", (req, res) -> operaciones(req, res));
    }
    
	private static String introducirDatos(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>MEDIA Y DESVIACIÓN ESTÁNDAR</h2>"
                + "<h3>Escriba los números separados por un espacio</h3>"
                + "<form action=\"/results\">"
                + "  Columna 1:<br>"
                + "  <input type=\"text\" name=\"columna1\" value=\"\" required>"
                + "  <br>"
                + "  Columna 2):<br>"
                + "  <input type=\"text\" name=\"columna2\" value=\"\" required"
                + "  <br><br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";
        
        return pageContent;
    }

    private static String operaciones(Request req, Response res) {
        String pageContent;
        String[] c1 = req.queryParams("columna1").split(" ");
        String[] c2 = req.queryParams("columna2").split(" ");
        double[] columna1 = leer(c1);
        double[] columna2 = leer(c2);
        
        double media1 = media(columna1);
        double media2 = media(columna2);
        
        double desviacion1 = desviacion(columna1, media1);
        double desviacion2 = desviacion(columna2, media2);
        
        if (columna1.length == columna2.length){
            pageContent
            = "<!DOCTYPE html>"
            + "<html>"
            + "<body>"
            + "<h2>Los resultados son:</h2>"
            + "<h3 style=\"color:red;\">Columna 1:</h3>"
            + "<h4>"+"MEDIA:"+"  "+String.format("%.2f", media1)+"</h4>"
            + "<h4>"+"DESVIACIÓN ESTANDAR:"+"  "+String.format("%.2f", desviacion1)+"</h4>"
            + "<h3 style=\"color:red;\">Columna 2:</h3>"
            + "<h4>"+"MEDIA:"+"  "+String.format("%.2f", media2)+"</h4>"
            + "<h4>"+"DESVIACIÓN ESTANDAR:"+"  "+String.format("%.2f", desviacion2)+"</h4>";
        }else{
            pageContent="error: columna 1 y columna 2 deben contener la misma cantidad de datos";
        }
        return pageContent;
    }
    
    public static double[] leer (String leida[]) {
    	int size = leida.length;
    	double[] lista = new double[size];

        for ( int i = 0; i < size; i++ ) 
          lista[i] = Double.parseDouble(leida[i]);

        return lista;
    }
    
    /**
     * Calcula la media de una lista de numeros.
     * @param list Lista donde estan los numeros para calcular la media.
     * @return La media de la lista.
     */
    public static double media(double list[]) {
    	int size = list.length;
    	double suma = 0; double promedio;
    	for(int i = 0; i < size; i++) {
    		suma += list[i];
    	}
    	promedio = suma/(double)size;
    	return promedio;
    }
    
    /**
     * Calcula la desviacion estandar de una lista de numeros.
     * @param list Lista donde estan los numeros para calcular la desviacion.
     * @param med Media hallada anteriormente.
     * @return Desviacion estandar de la lista de numeros.
     */
    public static double desviacion(double list[], double med) {
    	double sumatoria = 0;
    	int size = list.length;
    	for(int i = 0; i < size; i++) {
    		sumatoria += Math.pow(list[i] - med, 2);
    	}
    	double division = sumatoria/(double)(size-1);
    	double desviacion = Math.pow(division, (double)1/2);
    	return desviacion;
    }
    
    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {        
    	if (System.getenv("PORT") != null) {            
    		return Integer.parseInt(System.getenv("PORT"));        
    	}        
    	return 4567;
    }
}
