package com.java.test;

import java.util.Map;
import java.util.TreeMap;


public class 生成findViewById {
	
	static Map<String, String> map = new TreeMap<String, String>();
	
	public static void main(String[] args) {
		
		
		//xml 文件路径
		String filePath = "D:\\baiduYun\\dzl_github\\yizego\\yizego\\app\\src\\main\\res\\layout\\"
				+ "frag_shop_shop.xml";
		
//		filePath = "D:\\work\\haoyangde\\haoyangde\\res\\layout\\"
//				+ "activity_product_detail.xml";
		
		filePath = "D:\\baiduYun\\dzl_github\\yizego\\nongye\\app\\src\\main\\res\\layout\\"
				+ "activity_product_detail.xml";
		
		int[] a = new int[1];
		String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());
		if (fileName.startsWith("act")) {
			a[0] = 1;
			
		}else if (fileName.startsWith("frag")) {
			a[0] = 2;
			
		}else if (fileName.startsWith("item")) {
			a[0] = 3;
		}
		
		GenerateFindViewById.getFindViewId(filePath, map, a);
		
		
	}

	
}
