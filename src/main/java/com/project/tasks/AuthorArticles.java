package com.project.tasks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AuthorArticles {
	
	public static void main(String args[]) {
		
		getArticleTitles("epaga");
	}
	
	public static List<String> getArticleTitles(String author){
		String inline = "";
		String pagecount="1";
		String endPoint="https://jsonmock.hackerrank.com/api/articles?author="+author+"&page="+pagecount;
		ArrayList<String> titles=new ArrayList<String>();
		try {
		URL url = new URL(endPoint);
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
			String title = (String) userObj.get("title");
			String story_title = (String) userObj.get("story_title");
		     if(title != null) {
		    	 titles.add(title);
		     }else if (story_title != null) {
		    	 titles.add(story_title);

		     }
			System.out.println("title: " + title + " story_title: " + story_title);

		}

		conn.disconnect();
		System.out.println("total: "+ titles.size());
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return titles;
		
	}

}
