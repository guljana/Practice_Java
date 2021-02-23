package com.project.tasks;

import java.util.ArrayList;
import java.util.List;

public class Toolchanger {

	public static void main(String[] args) {
		List<String> tools = new ArrayList<String>();
		tools.add("ballendmill");
		tools.add("facemill");
		tools.add("keywaycutter");
		tools.add("slotdrill");
		System.out.println(toolchanger(tools, 1, "slotdrill"));
	}
	public static int toolchanger(List<String> tools, int startIndex, String target) {

		int leftmove=0;
		int rightmove=0;
		
		for (int i = startIndex; i <= tools.size(); i++) {
			
			if (tools.get(i).equalsIgnoreCase(target)) {
				return rightmove;
			}if(startIndex== 0) {
				if (tools.get(tools.size()-1).equalsIgnoreCase(target)) {
					rightmove ++;
					 break;
				} 
			}else {
				if (tools.get(i - 1).equalsIgnoreCase(target)) {
					rightmove ++;
					 break;
				} 
			}if(i == (tools.size()-1)) {
				if (tools.get(0).equalsIgnoreCase(target)) {
			    	 rightmove ++;
	                 break;
				}
			}else {
				if (tools.get(i + 1).equalsIgnoreCase(target)) {
			    	 rightmove ++;
	                 break;
				}
			}
				rightmove++;
			
		}
		
		for (int i = startIndex; i <= 0; i--) {
			if (tools.get(i).equalsIgnoreCase(target)) {
				return leftmove;
			}if(startIndex== 0) {
				if (tools.get(tools.size()-1).equalsIgnoreCase(target)) {
					leftmove ++;
					 break;
				} 
			}else {
				if (tools.get(i - 1).equalsIgnoreCase(target)) {
					leftmove ++;
					 break;
				} 
			}
		     if (tools.get(i + 1).equalsIgnoreCase(target)) {
		    	 leftmove ++;
                 break;
			}	else {	
				leftmove++;
			}
		}
		
		
		if (leftmove < rightmove && leftmove!=0)
			return leftmove;
		else 
			return rightmove;

	}

}
