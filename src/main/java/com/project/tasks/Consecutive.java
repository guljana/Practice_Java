package com.project.tasks;

public class Consecutive {

	public static void main(String[] args) {

		System.out.println(getSubstringCount("001100011"));
	}

	public static int getSubstringCount(String s) {
		if (s.isEmpty())
			return 0;

		int numberConsecutiveOnes = 0;
		int numberConsecutiveZeroes = 0;
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				if (i - 1 >= 0 && s.charAt(i - 1) == '1') {
					numberConsecutiveZeroes = 0;
				}
				numberConsecutiveZeroes++;
				if (numberConsecutiveZeroes <= numberConsecutiveOnes) {
					result++;
				}
			} else {
				if (i - 1 >= 0 && s.charAt(i - 1) == '0') {
					numberConsecutiveOnes = 0;
				}
				numberConsecutiveOnes++;
				if (numberConsecutiveOnes <= numberConsecutiveZeroes) {
					result++;
				}
			}
		}

		return result;
	}
}
