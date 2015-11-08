package com;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
//		m1();
		
		Date d = new Date(1445645742354L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("resp1one : " + format.format(d));
		
	}

	private static void m1() {
		List<String> imgEntityList = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			imgEntityList.add("" + i);
		}
		System.out.println("resp1onse : " + imgEntityList);
		imgEntityList = imgEntityList.subList(0, 3);
		System.out.println("resp1onse : " + imgEntityList);
	}

}
