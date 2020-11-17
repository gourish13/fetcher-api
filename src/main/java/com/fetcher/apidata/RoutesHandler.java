package com.fetcher.apidata;

/**
 * Handles Request Routes 
 *
 */

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class BaseHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExc) throws IOException {
		String res = "{\"cod\": \"404\", \"allowed_req_types\": [\"/weather?city=cityName\", \"/weather?city=city_name,country_code\", \"/forecast?city=cityName\", \"/forecast?city=city_name,country_code\"]}";
       	ResponseHeaders.response(httpExc);
		httpExc.getResponseHeaders().set("Content-Type", "application/json");
		httpExc.sendResponseHeaders(200, res.length()); // Setting Response status to 200 i.e., OK
		OutputStream ot = httpExc.getResponseBody();
		ot.write(res.getBytes()); // Writing Content to Response Body
		ot.close();
	}
}

class ForecastHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExc) throws IOException {

		String[] cityQuery = httpExc.getRequestURI().toString().split("\\?")[1].split("="); // getting the query from Request URL
		
		if (cityQuery.length != 2)
			httpExc.sendResponseHeaders(404, -1); // Setting Response status to 404 i.e., Not Found, when Request is invalid and giving an empty Response
		else {
        	ResponseHeaders.response(httpExc);
			httpExc.getResponseHeaders().set("Content-Type", "application/json"); // Setting response content type to JSON
			String jsonRes = new FetchJSON().getJSONResponse("forecast", cityQuery[1]); // Fetching JSON from URL. Implemented in class FetchJSON
			httpExc.sendResponseHeaders(200, jsonRes.length()); // Setting Response status to 200 i.e., OK
			OutputStream ot = httpExc.getResponseBody();
			ot.write(jsonRes.getBytes()); // Writing Content to Response Body
			ot.close();
		}
	}	
}

class WeatherHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange httpExc) throws IOException{
    
        String[] cityQuery = httpExc.getRequestURI().toString().split("\\?")[1].split("="); // getting the query from Request URL
        
        if(cityQuery.length != 2)
            httpExc.sendResponseHeaders(404 , -1); // Setting Response status to 404 i.e., Not Found, when Request is invalid and giving an empty Response
        else{
        	ResponseHeaders.response(httpExc);
            httpExc.getResponseHeaders().set("Content-Type" , "application/json");  // Setting response content type to JSON
            String jsonRes = new FetchJSON().getJSONResponse("weather" , cityQuery[1]); // Fetching JSON from URL. Implemented in class FetchJSON
            httpExc.sendResponseHeaders(200 , jsonRes.length()); // Setting Response status to 200 i.e., OK
            OutputStream ot = httpExc.getResponseBody(); 
            ot.write(jsonRes.getBytes()); // Writing Content to Response Body
            ot.close();
            
        }
    }
    
}

class ResponseHeaders {
	public static void response(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); // Allowing CORS

        if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            httpExchange.sendResponseHeaders(204, -1);
            return;
        }
    }
}
