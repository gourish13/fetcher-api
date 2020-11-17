package com.fetcher.apidata;

/**
 * gets JSON from api URL
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.net.URL;
import java.util.Formatter;

class FetchJSON {
	
	private static final String URI = "https://api.openweathermap.org/data/2.5/%s?q=%s&appid=%s";      // OpenWeatherMap Free Api
	private static final String API_KEY = System.getenv("APIKEY");   //// Accessing environment variable APIKEY to get the OpenWeatherMap API key
	/*
		Reads the JSON response fetched from OpenWeatherMap and returns it as a String
	*/
	private String readJSON(Reader rd) throws IOException {
		String jsonStr = new String();
		int charVal;
		while ((charVal = rd.read()) != -1)
			jsonStr += (char) charVal;
		return jsonStr; 
	}
	/*
		Fetches the JSON response from OpenWeatherMap Api
	*/
	public String getJSONResponse(String apiMode, String cityName) throws IOException {
		String url = String.format(URI, apiMode, cityName, API_KEY);  //// Using String Formatter to generate the API Request
		InputStream inStream = new URL(url).openStream(); 
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inStream, Charset.forName("UTF-8")));
			String json = readJSON(br); 	///// Reads JSON from the URL 
			return json;		///////////// Returning the Api JSON Response as String
		} finally {
			inStream.close();
		}
	}
}
