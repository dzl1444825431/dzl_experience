package com.google.gsonTest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class GsonTest {
	
	public static void main(String[] args) {
		
		Gson gson = new Gson();
		
		String jsonStr = "{\"a\":2}";
		System.out.println("resp1onse : ");
		gson.fromJson(jsonStr, Object.class);
		System.out.println("resp1onse : 1 = " + 1);
		
		JsonElement j;
		
	}

}
