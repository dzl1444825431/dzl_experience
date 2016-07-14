package com;

public class TestMain {

	public static void main(String[] args) {
		String str = "我是谁,我是牛B,我要崛起";

		print(str, 14);

	}

	public static void print(String str, int split_length) {
		if (str == null) {
			return;
		}

		int length = str.length();
		if (split_length >= length) {
			System.out.println("resp1onse : response = " + str);
			return;
		}

		for (int i = 0; i < length; i += split_length) {
			if (i + split_length >= length) {
				System.out.println("resp1onse : response = " + str.substring(i));
			} else {
				System.out.println("resp1onse : response = " + str.substring(i, i + split_length));
			}
		}

	}
}
