package com.android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 生成匹配R与Id {
	
	private static Map<String, String> map = new TreeMap<String, String>();

	public static void main(String[] args) {
		//D:\baiduYun\dzl_github\yunserver\xiaoerduo\src\com\hengeasy\guamu\droid\R.java
		
		String rFile = "D:\\baiduYun\\dzl_github\\yunserver\\xiaoerduo\\src\\com\\hengeasy\\guamu\\droid\\R.java";
		String filePath = "D:\\baiduYun\\dzl_github\\yunserver\\xiaoerduo\\src\\com\\hengeasy\\guamu\\droid\\recruit\\adapter\\a.java";
		
		
		writeFileIds(rFile, filePath);
		
	}
	
	public static void writeFileIds(String rFile, String filePath) {
		
		if (map.size() <= 0) {
			getR_File_List(rFile);
		}
		writeFileIds(filePath);
		
	}

	private static void writeFileIds(String filePath) {
		
		if (filePath.endsWith("R.java")) {
			return;
		}
		
		File sourceFile = new File(filePath);
		FileReader fr = null;
		BufferedReader br = null;
		
		File targeFile = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		String target = filePath.replace(sourceFile.getName(),sourceFile.getName() + "2");
		boolean isNeetToReName = false;
		
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			
			String line = "";
			String trimLine = "";
			
			Pattern pattern = Pattern.compile("[\\d]{10}");
			Matcher matcher = null;
			
			StringBuffer sb = new StringBuffer();
			boolean isFind = false;
			
			while ((line = br.readLine()) != null) {
				trimLine = line.trim();
				matcher = pattern.matcher(trimLine);
				
				while(isFind = matcher.find()){
					String group = matcher.group(0);
					if(map.containsKey(group)){
						isNeetToReName = true;
						System.out.println("resp1onse : matcher.group(1) = " + group +" map.get(key) = " + map.get(group));
					}
					sb.append(line + "//" +  map.get(group) + "\r\n");
					break;
				}
				
				if (!isFind) {
					sb.append(line + "\r\n");
				}
				
			}
			
			if (isNeetToReName) {
				targeFile = new File(target);
				fw = new FileWriter(targeFile);
				bw = new BufferedWriter(fw);
				bw.write(sb.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			close(br);
			close(fr);
			close(bw);
			close(bw);
			
		}
		
		if (isNeetToReName && targeFile != null && targeFile.exists()) {
			sourceFile.delete();
			targeFile.renameTo(sourceFile);
		}
	}
	
	

	private static void getR_File_List(String filePath) {
		File sourceFile = new File(filePath);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			
			String line = "";
			String trimLine = "";
			
			Pattern pattern = Pattern.compile("int ([\\w]*) = ([\\d]{10})");
			Matcher matcher = null;
			String subClassName = null;
			String classReg = "public static final class";
			
			boolean isFind = false;
			while ((line = br.readLine()) != null) {
				trimLine = line.trim();
				matcher = pattern.matcher(trimLine);
				
				while(isFind = matcher.find()){
					map.put(matcher.group(2), "R." + subClassName + "." + matcher.group(1));
					break;
				}
				
				if (!isFind && trimLine.indexOf(classReg) != -1) {
					subClassName = trimLine.replace(classReg, "").replace("{", "").trim();
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(br);
			close(fr);
		}
	}
	
	/**
	 * 关闭流
	 * @param closeable
	 */
	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
