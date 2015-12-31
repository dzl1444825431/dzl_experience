package com.common_dzl;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class 读取properties {

	public static void main(String[] args) {
		
		String path = "/com/common_dzl/android_dzl.properties";

		String pre = "";
		String next = "";
		
		readFile("src" + path, 2, "map.put(\"", "\", \"\");");// 1:style  other: pre next
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * android layout文件 属性 转成 style
	 * @param array
	 * //<item name="android:windowFrame">@null</item>
	 */
	private static void androidToStyle(String[] array) {
		
		if (array.length == 2) {
			System.out.println("<item name=\"" + array[0].trim() + "\">" + array[1].trim().replaceAll("\"", "") + "</item>");
		}
		
	}
	
	/**
	 * android layout文件 属性 转成 style
	 * @param array
	 * //<item name="android:windowFrame">@null</item>
	 * @param next 
	 * @param pre 
	 */
	private static void androidToUrl(String[] array, String pre, String next) {
		
		System.out.println(pre + array[0].trim() + next);
		
	}
	
	static void readProperties(String path) {
		Properties prop = new Properties();
		try {
			InputStream in = Object.class.getResourceAsStream(path);

			prop.load(in);

			Enumeration<Object> keys = prop.keys();
//			Collection<Object> values = prop.values();
			
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key + " = " + value);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void readFile(String filePath, int type, String pre, String next) {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			
			String line = "";
			
			if (type == 1) {
				while ((line = br.readLine()) != null) {
					String[] array = line.split("=");
					androidToStyle(array);
				}
			}else {
				
				while ((line = br.readLine()) != null) {
					String[] array = line.split("=");
					androidToUrl(array, pre, next);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			close(br);
		}
	}

	
	private static void close(Closeable br) {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
