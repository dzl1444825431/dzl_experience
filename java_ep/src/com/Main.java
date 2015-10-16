package com;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		List<String> imgEntityList = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			imgEntityList.add("" + i);
		}
		System.out.println("resp1onse : " + imgEntityList);
		imgEntityList = imgEntityList.subList(0, 3);
		System.out.println("resp1onse : " + imgEntityList);
	}

}
