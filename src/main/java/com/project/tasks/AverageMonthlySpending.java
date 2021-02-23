package com.project.tasks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AverageMonthlySpending {
	
	public static void main (String args[]) {
		
		getUserTransaction(4, "", "");
	}
	
	public static List<Integer> getUserTransaction(int uid, String txnType, String monthYear) {
		String inline = "";
		String endPoint="https://jsonmock.hackerrank.com/api/transactions/search?userId="+uid;
		ArrayList<Integer> titles=new ArrayList<Integer>();
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
		//System.out.println("inline: " +inline);

		JSONParser parse = new JSONParser();
		JSONObject jobj = (JSONObject) parse.parse(inline);
		JSONArray userdata = (JSONArray) jobj.get("data");
		Calendar c = Calendar.getInstance(TimeZone.getDefault());

		for (int i = 0; i < userdata.size(); i++) {
			JSONObject userObj = (JSONObject) userdata.get(i);
			Long transaction_id = (Long) userObj.get("id");
			String tranType = (String) userObj.get("txnType");
			Long timestamp = (Long) userObj.get("timestamp");
			String amount = (String) userObj.get("amount");
			c.setTimeInMillis(timestamp);
			c.add(Calendar.MONTH, -2);

		   
			//System.out.println("timestamp: " + c.get + " amount : " + amount);

		}

		conn.disconnect();
		System.out.println("total: "+ titles.size());
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return titles;
		
		
    }

}
