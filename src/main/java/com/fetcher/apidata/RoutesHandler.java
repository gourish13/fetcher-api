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
		httpExc.sendResponseHeaders(200, res.length());
		OutputStream ot = httpExc.getResponseBody();
		ot.write(res.getBytes());
		ot.close();
	}
}

class ForecastHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExc) throws IOException {

		String[] cityQuery = httpExc.getRequestURI().toString().split("\\?")[1].split("=");
		
		if (cityQuery.length != 2)
			httpExc.sendResponseHeaders(404, -1);
		else {
        	ResponseHeaders.response(httpExc);
			httpExc.getResponseHeaders().set("Content-Type", "application/json");
			String jsonRes = new FetchJSON().getJSONResponse("forecast", cityQuery[1]);
			httpExc.sendResponseHeaders(200, jsonRes.length());
			OutputStream ot = httpExc.getResponseBody();
			ot.write(jsonRes.getBytes());
			ot.close();
		}
	}	
}

class WeatherHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange httpExc) throws IOException{
    
        String[] cityQuery = httpExc.getRequestURI().toString().split("\\?")[1].split("=");
        
        if(cityQuery.length != 2)
            httpExc.sendResponseHeaders(404 , -1);
        else{
        	ResponseHeaders.response(httpExc);
            httpExc.getResponseHeaders().set("Content-Type" , "application/json");
            String jsonRes = new FetchJSON().getJSONResponse("weather" , cityQuery[1]);
            httpExc.sendResponseHeaders(200 , jsonRes.length());
            OutputStream ot = httpExc.getResponseBody();
            ot.write(jsonRes.getBytes());
            ot.close();
            
        }
    }
    
}

class ResponseHeaders {
	public static void response(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            httpExchange.sendResponseHeaders(204, -1);
            return;
        }
    }
}