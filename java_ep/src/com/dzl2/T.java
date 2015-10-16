package com.dzl2;

public class T {
	public static void main1(String[] args) {
		java.util.List l = new java.util.ArrayList();
		for (int i = 10000; i < 100000; i++) {
			try {
				l.add(new int[100_000_000]);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("resp1onse : " + (0b11 | 0b10));
	}

}
