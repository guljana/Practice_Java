package com.project.tasks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.github.opendevl.JFlat;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MainApp {

	public static void main(String[] args) {
		String inline = "";

		try {
			URL url = new URL("https://eonet.sci.gsfc.nasa.gov/api/v3/events");
			// Parse URL into HttpURLConnection in order to open the connection in order to
			// get the JSON data
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// Set the request to GET or POST as per the requirements
			conn.setRequestMethod("GET");
			// Use the connect method to create the connection bridge
			conn.connect();
			// Get the response status of the Rest API
			int responsecode = conn.getResponseCode();
			System.out.println("Response code is: " + responsecode);

			// Iterating condition to if response code is not 200 then throw a runtime
			// exception
			// else continue the actual process of getting the JSON data
			if (responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			else {
				// Scanner functionality will read the JSON data from the stream
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();// string builder
				}
				// System.out.println("\nJSON Response in String format");
				// System.out.println(inline);
				// Close the stream when reading the data has been finished
				sc.close();
			}

			JSONParser parse = new JSONParser();

			JSONObject jobj = (JSONObject) parse.parse(inline);

			JSONArray events_arr = (JSONArray) jobj.get("events");

			dataToCsv(events_arr);
			// JsonToCsvTest( events_arr);

			/*
			 * for(int i=0;i<events_arr.size();i++) {
			 * 
			 * JSONObject eventObj = (JSONObject)events_arr.get(i); JSONArray categories_arr
			 * = (JSONArray) eventObj.get("categories"); JSONArray geometry_arr =
			 * (JSONArray) eventObj.get("geometry");
			 * 
			 * 
			 * //System.out.println("\n id: " +eventObj.get("id"));
			 * //System.out.println("title: " +eventObj.get("title"));
			 * //System.out.println("close: " +eventObj.get("closed")); for(int
			 * j=0;j<categories_arr.size();j++) {
			 * 
			 * JSONObject categoryObj = (JSONObject) categories_arr.get(j); String
			 * category_id = (String) categoryObj.get("id");
			 * //System.out.println(str_data1);
			 * 
			 * } for(int j=0;j<geometry_arr.size();j++) {
			 * 
			 * JSONObject geometryObj = (JSONObject) geometry_arr.get(j); Double
			 * magnitudeValue = (Double) geometryObj.get("magnitudeValue"); String
			 * magnitudeUnit = (String) geometryObj.get("magnitudeUnit"); String date =
			 * (String) geometryObj.get("date");
			 * 
			 * 
			 * } }
			 */
			// Disconnect the HttpURLConnection stream
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void JsonToCsv(JSONArray events_arr) throws IOException {
	 * System.out.println("In function");
	 * 
	 * JsonNode jsonTree = new ObjectMapper().readTree(events_arr.toJSONString());
	 * Builder csvSchemaBuilder = CsvSchema.builder(); JsonNode firstObject =
	 * jsonTree.elements().next();
	 * 
	 * firstObject.fieldNames().forEachRemaining(fieldName ->
	 * {csvSchemaBuilder.addColumn(fieldName);} ); CsvSchema csvSchema =
	 * csvSchemaBuilder.build().withHeader();
	 * System.out.println("csvSchema= "+csvSchema); CsvMapper csvMapper = new
	 * CsvMapper(); csvMapper.writerFor(JsonNode.class) .with(csvSchema)
	 * .writeValue(new File("src/main/resources/orderLines.csv"), jsonTree);
	 * 
	 * }
	 */

	public static void dataToCsv(JSONArray events_arr) throws IOException {
		FileWriter writer;
		try {

			writer = new FileWriter("C:\\Users\\gulja\\eclipse-workspace\\test_work\\src\\main\\resources\\test.csv");
			List<String> dataToFile = new ArrayList<>();
			// columns, give names from your data
			dataToFile.add("event-id");
			dataToFile.add("category_id");
			dataToFile.add("magnitude");
			dataToFile.add("date");
			dataToFile.add("lat");
			dataToFile.add("long");

			// writing columns line to file
			writer.write(dataToFile.stream().collect(Collectors.joining(",")));
			writer.write("\n"); // newline
			// data
			dataToFile.clear(); // empty columns line
			int index = 0;
			JSONObject eventObj, categoryObj, geoObj = null;
			JSONArray categories_arr, geo_arr = null;
			String[] coordinates = null;
			String str_replace = "";
			int totalSize = events_arr.size();
			while (index < totalSize) {
				eventObj = (JSONObject) events_arr.get(index);
				categories_arr = (JSONArray) eventObj.get("categories");
				categoryObj = (JSONObject) categories_arr.get(0);
				geo_arr = (JSONArray) eventObj.get("geometry");
				if (geo_arr.size() > 1) {
					for (int j = 0; j < geo_arr.size(); j++) {

						geoObj = (JSONObject) geo_arr.get(j);

						dataToFile.add(String.valueOf(eventObj.get("id")));
						dataToFile.add(String.valueOf(categoryObj.get("id")));
						dataToFile.add(String.valueOf(geoObj.get("magnitudeValue")));
						dataToFile.add(String.valueOf(geoObj.get("date")));
						coordinates = String.valueOf(geoObj.get("coordinates")).split("[,]", 0);
						str_replace = coordinates[0].replace("[", ""); // strNew is 'bcdDCBA123'
						dataToFile.add(str_replace);
						str_replace = coordinates[1].replace("]", ""); // strNew is 'bcdDCBA123'
						dataToFile.add(str_replace);
						// System.out.println("cord= "+String.valueOf(geoObj.get("coordinates")));
						// writing columns line to file
						writer.write(dataToFile.stream().collect(Collectors.joining(",")));
						writer.write("\n"); // newline
						dataToFile.clear();
						index++;

					}
				} else {
					geoObj = (JSONObject) geo_arr.get(0);
					dataToFile.add(String.valueOf(eventObj.get("id")));
					dataToFile.add(String.valueOf(categoryObj.get("id")));
					dataToFile.add(String.valueOf(geoObj.get("magnitudeValue")));
					dataToFile.add(String.valueOf(geoObj.get("date")));
					coordinates = String.valueOf(geoObj.get("coordinates")).split("[,]", 0);
					str_replace = coordinates[0].replace("[", ""); // strNew is 'bcdDCBA123'
					dataToFile.add(str_replace);
					str_replace = coordinates[1].replace("]", ""); // strNew is 'bcdDCBA123'
					dataToFile.add(str_replace);

					// writing columns line to file
					writer.write(dataToFile.stream().collect(Collectors.joining(",")));
					writer.write("\n"); // newline
					dataToFile.clear();
					index++;
				}
				// System.out.println("geo_arr= "+geo_arr.size());

			}

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void JsonToCsvTest(JSONArray events_arr) throws IOException {
		String str = events_arr.toString();

		// System.out.println("str= "+fileurl);

		JFlat flatMe = new JFlat(str);
		try {
			flatMe.json2Sheet().headerSeparator().write2csv("src/main/resources/eventsdata.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// flatMe.json2Sheet().headerSeparator().write2csv("src/main/resources/events.csv");

		// flatMe.json2Sheet().write2csv("src/main/resources/nasaevents.csv", '|');

		System.out.println("the end ");

	}

}
