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
		String res = "{\"cod\": \"404\", \"sample_req_types\": [\"/weather?city=cityName\", \"/weather?city=city_name,country_code\", \"/forecast?city=cityName\", \"/forecast?city=city_name,country_code\"]}";
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
		httpExc.getResponseHeaders().set("Content-Type", "application/json");

		String[] cityQuery = httpExc.getRequestURI().toString().split("\\?")[1].split("=");
		
		if (cityQuery.length != 2)
			httpExc.sendResponseHeaders(404, -1);
		else {
			String jsonRes = new FetchJSON().getJSONResponse("forecast", cityQuery[1]);
			httpExc.sendResponseHeaders(200, jsonRes.length());
			OutputStream ot = httpExc.getResponseBody();
			ot.write(jsonRes.getBytes());
			ot.close();
		}
	}	
}