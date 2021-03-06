package com.fetcher.apidata;

/**
 * A simple backend server for fetching data from api and giving response
 *
 */

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class App {

	private final static Integer PORT = Integer.valueOf(System.getenv("PORT"));  // gets the port specified from the environment variable PORT

    public static void main( String[] args ) throws IOException {
    	HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);  // Creating a very basic server
	    
	// Creating Routes and assigning routes handler to them
    	server.createContext("/", new BaseHandler());
    	server.createContext("/forecast", new ForecastHandler());
    	server.createContext("/weather" , new WeatherHandler());

    	server.setExecutor(null);
    	server.start();  // Starting the server
    	System.out.println("[ Server ] listening at Port : " + PORT);
    }
}
