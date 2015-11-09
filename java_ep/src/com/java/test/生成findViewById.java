package com.java.test;

import java.util.Map;
import java.util.TreeMap;


public class 生成findViewById {
	
	static Map<String, String> map = new TreeMap<String, String>();
	
	public static void main(String[] args) {
		
		//xml 文件路径
		String filePath = "D:\\baiduYun\\dzl_github\\yunserver\\guamu\\res\\layout\\"
				+ "item_recruit.xml";
		
		
		GenerateFindViewById.getFindViewId(filePath, map);
		
		
	}

	
}
