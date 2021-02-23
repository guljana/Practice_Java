package com.project.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MaximumCharacter{
	
	public static void main(String[] args) {
	
	maximumOccurringCharacter("helloworld");
}
    public static char maximumOccurringCharacter(String text) {
    	
    	LinkedHashMap<Character, Integer> occurrences  = new LinkedHashMap<Character, Integer>(); 
    	char max='a';
        int total=0;

    	for(int i=0;i<text.length();i++)
    	{
    		 char c = text.charAt(i);
    		 occurrences.put(c, occurrences.getOrDefault(c, 0) + 1);
    	}
    	
    	Map.Entry<Character, Integer> maxEntry = null;

    	for (Map.Entry<Character, Integer> entry : occurrences.entrySet())
    	{
    	    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
    	    {
    	        maxEntry = entry;
    	    }
    	}	
    	
//    	System.out.println("maxEntry= "+ maxEntry.getValue());
	return maxEntry.getKey();

}

}
