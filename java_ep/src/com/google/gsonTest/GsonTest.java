package com.google.gsonTest;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class GsonTest {
	
	
	public static void main(String[] args) {
		
//		test1();

//		test2();
		
	}

	private static void test2() {
		Gson gson = new Gson();
		int[] ints = {1, 2, 3, 4, 5};
		String[] strings = {"abc", "def", "ghi"};

//		(Serialization)
		gson.toJson(ints);    // ==> prints [1,2,3,4,5]
		gson.toJson(strings);  //==> prints ["abc", "def", "ghi"]

		//(Deserialization)
		int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class); 
		ArrayList<String> ints3 = gson.fromJson("[1,2,3,4,5]", new TypeToken<ArrayList<String>>() {
		}.getType()); 
		
		System.out.println("resp1onse : ints2 = " + Arrays.toString(ints2));
		System.out.println("resp1onse : ints3 = " + ints3);
	}

	private static void test1() {
		Gson gson1 = new Gson();
		
		String jsonStr = "{\"a\":2}";
		System.out.println("resp1onse : ");
		gson1.fromJson(jsonStr, Object.class);
		System.out.println("resp1onse : 1 = " + 1);
		
		JsonElement j;
		
		
//		(Serialization)
		Gson gson = new Gson();
		System.out.println("1resp1onse : 1 = " + gson.toJson(1));            //==> prints 1
		System.out.println("1resp1onse : 1 = " + gson.toJson("abcd"));       //==> prints "abcd"
		System.out.println("1resp1onse : 1 = " + gson.toJson(new Long(10))); //==> prints 10
		int[] values = { 1 };
		System.out.println("1resp1onse : 1 = " + gson.toJson(values));       //==> prints [1]

//		(Deserialization)
		int one = gson.fromJson("1", int.class);
		Integer one1 = gson.fromJson("1", Integer.class);
		Long one2 = gson.fromJson("1", Long.class);
		Boolean false1 = gson.fromJson("false", Boolean.class);
		String str = gson.fromJson("\"abc\"", String.class);
//		String anotherStr = gson.fromJson("[\"abc\"]", String.class);
	}

}
