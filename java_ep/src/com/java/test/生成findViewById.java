package com.java.test;

import java.util.Map;
import java.util.TreeMap;


public class 生成findViewById {
	
	static Map<String, String> map = new TreeMap<String, String>();
	
	public static void main(String[] args) {
		
		//xml 文件路径
		String filePath = "D:\\baiduYun\\dzl_github\\yunserver\\lizhireader\\res\\layout\\"
				+ "activity_main.xml";
		
		
		GenerateFindViewById.getFindViewId(filePath, map);
		
		
	}

	
}
