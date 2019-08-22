package edu.escuelaing.arep.ASE.app;

import static spark.Spark.*;

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
        //get("/inputdata", (req, res) -> inputDataPage(req, res));
        //get("/results", (req, res) -> resultsPage(req, res));
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
