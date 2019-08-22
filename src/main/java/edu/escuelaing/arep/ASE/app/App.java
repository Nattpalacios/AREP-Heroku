package edu.escuelaing.arep.ASE.app;

import static spark.Spark.*;

public class App {
	
    public static void main(String[] args) {        
    	get("/hello", (req, res) -> "Hello Heroku");    
    }
    
    static int getPort() {        
    	if (System.getenv("PORT") != null) {            
    		return Integer.parseInt(System.getenv("PORT"));        
    	}        
    	return 4567;
    }
}
