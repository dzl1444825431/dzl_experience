package com.java.test;

public class EffectiveJava {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis(); 
		//Long sum = 0L;要当心无意识的自动拆装箱   优先使用基本类型  Long 与 long
		long sum = 0L;
		int max = Integer.MAX_VALUE;
		for (int i = 0; i < max; i++) {
			sum += i;
		}
		System.out.println("resp1onse : " + sum);
		System.out.println("resp1onse : " + (System.currentTimeMillis() - startTime));
		
	}
	/*
resp1onse : 2305843005992468481
resp1onse : 21718

resp1onse : 2305843005992468481
resp1onse : 21338

resp1onse : 2305843005992468481
resp1onse : 1194

	 */
	
}
