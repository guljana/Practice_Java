package com.project.tasks;

import java.util.ArrayList;
import java.util.List;

public class MillingMachine {

	public static void main(String[] args) {
		List<String> tools = new ArrayList<String>();
		tools.add("ballendmill");
		tools.add("keywaycutter");
		tools.add("slotdrill");
		tools.add("facemill");
		System.out.println(toolchanger(tools, 1, "ballendmill"));
	}
	public static int toolchanger(List<String> tools, int startIndex, String target) {

		if ((tools.get(startIndex)) == target)
			return 0; // means already on target, no need to move
		int rightmoves = 1;
		int leftmoves = 1;
		int nStart = startIndex;
		if (startIndex == tools.size() - 1) {
			nStart = 0;
		} else {
			nStart = startIndex + 1;
		}
		while (nStart != startIndex) {
			if (tools.get(startIndex) == target) {
				break;
			}
			rightmoves++;
			if (startIndex != 0 && nStart == (tools.size() - 1)) { // -1 because of 0 based index
				nStart = 0;
			} else {
				nStart++;
			}
		}
		leftmoves = 1;
		nStart = startIndex; // reset for counting left side moves

		while (nStart != startIndex) {
			if (tools.get(startIndex) == target) {
				break;
			}
			leftmoves++;
			if (startIndex != (tools.size() - 1) && nStart == 0) { // -1 because of 0 based index
				nStart = (tools.size() - 1);
			} else {
				nStart--;
			}
		}
		if (leftmoves < rightmoves)
			return leftmoves;
		else
			return rightmoves;
	}
}
