package com.common_dzl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonFileToMap {
	
	public static void main(String[] args) {
		Map<String, String> map = readFileJsonToMap("src/com/common_dzl/haoyangde.txt");
	}

	static Map<String, String> readFileJsonToMap(String filePath) {
		Map<String, String> map = new HashMap<String, String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			
			String line = "";
			String trimLine = "";
			int index = -1;
			while ((line = br.readLine()) != null) {
				trimLine = line.trim();
				if (trimLine.length() > 0) {
					index = trimLine.indexOf("=");
					if (index > 0) {
						String key = trimLine.substring(0, index);
						String value = trimLine.substring(index + 1);
						map.put(key, value);
						System.out.println("resp1onse : key = " + key +" value = " + value);
					}
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
}
