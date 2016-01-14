package com.java;

import java.util.ArrayList;

import javax.xml.stream.Location;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonException {
	
	public static void main(String[] args) {
		JSONObject response;
		try {
			response = new JSONObject("{\"data\":{\"store_list\":[\"0m\"],\"code\":201},\"output\":\"小区定位读取成功\",\"box_name\":\"gps_get_store\",\"success\":true}");
			ArrayList<Location> locations = new Gson().fromJson(response.optJSONObject("data").optJSONArray("store_list").toString(), new TypeToken<ArrayList<Location>>(){}.getType());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
