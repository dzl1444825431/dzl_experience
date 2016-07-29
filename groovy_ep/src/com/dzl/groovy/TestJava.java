package com.dzl.groovy;


public class TestJava {
	
	public static void main(String[] args) {
		
		int size = 1400 + 1;
		double dip = 0.5;
		for (int i = 1; i < size; i++) {
			System.out.println(String.format("<dimen name=\"_%dpx\">%fdp</dimen>", i, dip));
		}
		
	}

}
