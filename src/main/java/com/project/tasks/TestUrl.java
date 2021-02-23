package com.project.tasks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
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

public class TestUrl {
	static List<String> activeUsers = new LinkedList<String>();

	public static void main(String[] args) {

		 int threshold = 10;
		 String page="1";
			for (int i = 1; i < 3; i++) {

         String  endpoint="https://jsonmock.hackerrank.com/api/article_users?page="+i;
         activeUsers = getUsernames(endpoint,threshold);
			}
			System.out.println("main: "+ activeUsers.size());

	}

	public static List<String> getUsernames(String request,int threshold) {
		String inline = "";

		try {
			URL url = new URL(request);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			else {
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();// string builder
				}

				sc.close();
			}
			JSONParser parse = new JSONParser();
			JSONObject jobj = (JSONObject) parse.parse(inline);
			JSONArray userdata = (JSONArray) jobj.get("data");
			for (int i = 0; i < userdata.size(); i++) {
				JSONObject userObj = (JSONObject) userdata.get(i);
				String username = (String) userObj.get("username");
				Long submission_count = (Long) userObj.get("submission_count");
				if (submission_count > threshold) {
					activeUsers.add(username);
				}
				System.out.println("username: " + username + "submission_count: " + submission_count);

			}

			conn.disconnect();
			System.out.println("toatal: "+ activeUsers.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return activeUsers;
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
