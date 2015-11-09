package com.java.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateFindViewById {

	static void getFindViewId(String filePath, Map<String, String> map) {
		File sourceFile = new File(filePath);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			
			String line = "";
			String trimLine = "";
			
			Pattern pattern = Pattern.compile("android:id=\"@\\+id/(.*)\"");
			Matcher matcher = null;
			String noteName = null;
			String id = null;
			while ((line = br.readLine()) != null) {
				trimLine = line.trim();
				matcher = pattern.matcher(trimLine);
				if (trimLine.startsWith("<")) {
					noteName = trimLine.split(" ")[0].replace("<", "");
//					System.out.println("respone : noteName = " + noteName);
				}
				
				while(matcher.find()){
					id = matcher.group(1);
					System.out.println(id + "\t\t" + noteName);
					map.put(id, noteName);
					
					break;
				}
				
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("\n\nrespone : activity 、 fragment   private 变量 -------------------------------\n" );
		
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println("private " + entry.getValue() + " " + entry.getKey() + ";");
		}
		
		System.out.println("\n\nrespone :holder  变量 -------------------------------\n" );
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println(entry.getValue() + " " + entry.getKey() + ";");
		}
		
		System.out.println("\n\nrespone : activity 、 fragment      findViewById -------------------------------\n" );
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println(entry.getKey() + " = (" + entry.getValue() + ") findViewById(R.id." + entry.getKey() + ");");
		}
		
		System.out.println("\n\nrespone : adapter holder      findViewById -------------------------------\n" );
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println("holder." + entry.getKey() + " = (" + entry.getValue() + ") convertView.findViewById(R.id." + entry.getKey() + ");");
		}
		
		System.out.println("\n\nrespone : 局部 findViewById -------------------------------\n" );
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println(entry.getValue() + " " + entry.getKey() + " = (" + entry.getValue() + ") convertView.findViewById(R.id." + entry.getKey() + ");");
		}
		
		System.out.println("\n\nrespone : 局部 findViewById -------------------------------\n" );
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println(entry.getValue() + " " + entry.getKey() + " = (" + entry.getValue() + ") convertView.findViewById(R.id." + entry.getKey() + ");");
		}
		
		System.out.println("\n\nrespone : setOnClickListener(View) -------------------------------\n" );
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println("setOnClickListener(" + entry.getKey() + ");");
		}
		
		System.out.println("\n\nrespone : onClickListener case id: break; -------------------------------\n" );
		
		System.out.println("switch (v.getId()) {\n");
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println("case R.id." + entry.getKey() + ":\n\tbreak;");
		}
		System.out.println("default:\n\tbreak;\n}\n");
		
	}
	
}
