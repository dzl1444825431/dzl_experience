package com.优化;

public class NullTest {
	int a;
	int a1;
	int a2;
	int a3;
	int a4;
	double a5;
	float a6;
	Object a7;
	
	
	public static void main(String[] args) {
		NullTest object = null;
		long start = System.currentTimeMillis();
		for (int i = 0; i < 2000000000; i++) {
			if (object == null) {
				
			}
		}
		
		long start1 = System.currentTimeMillis();
		for (int i = 0; i < 2000000000; i++) {
			if (null == object) {
				
			}
			
			
		}
		long start2 = System.currentTimeMillis();
		System.out.println("resp1onse : " + (start1 - start));
		System.out.println("resp1onse : " + (start2 - start1));
	}

}
