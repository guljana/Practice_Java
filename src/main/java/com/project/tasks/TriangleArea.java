package com.project.tasks;

import java.util.ArrayList;
import java.util.List;

public class TriangleArea {

	public static void main(String[] args) {
		List<Integer> x = new ArrayList<Integer>();
		List<Integer> y = new ArrayList<Integer>();

		x.add(0);
		x.add(3);
		x.add(6);
		y.add(0);
		y.add(3);
		y.add(0);	
		System.out.println(getTriangleArea(x,y));
	}
    public static int getTriangleArea(List<Integer> x, List<Integer> y) {
    	
    	double formula=0;
    	formula=0.5*( (x.get(0)*(y.get(1)-y.get(2))) + (x.get(1)*(y.get(2)-y.get(0))) + (x.get(2)*(y.get(0)-y.get(1))) );
    	int area=(int)formula;  

		return Math.abs(area);
	}
}

