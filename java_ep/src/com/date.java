package com;

import java.util.Calendar;
import java.util.Date;

public class date {
	
	public static void main(String[] args) {
		Date date = new Date();
		Date date2 = new Date(1443594615000L);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(1443594615000l);
		
		System.out.println("resp1onse : " + System.currentTimeMillis());
		System.out.println("resp1onse : " + date.getTime());
		System.out.println("resp1onse : " + date);
		System.out.println("resp1onse : " + date2);
	}

}
