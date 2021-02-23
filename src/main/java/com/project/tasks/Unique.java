package com.project.tasks;

public class Unique {
	public static void main(String[] args) {
		System.out.println(getUniqueCharacter("hackthegame"));
	}
	public static int getUniqueCharacter(String s) {
		Boolean isfound = false;
		if (s.isEmpty())
			return -1;
		for (int i = 0; i < s.length(); i++) {
			isfound = false;
			inner: for (int j = 0; j < s.length(); j++) {
				if (i != j) {
					if (s.charAt(i) == s.charAt(j)) {
						isfound = true;
						break inner;
					}
				}
			}
			if (!isfound) {
				return i + 1;
			}
		}
		return -1;
	}
}
