package com;

import java.util.Calendar;
import java.util.Date;

public class TimeTest {
	
	public static void main(String[] args) {
		
		Date date = new Date();
		long currentTimeMillis = System.currentTimeMillis();
		
		long time = date.getTime();
		time = 1465174566699L;
		currentTimeMillis = 1465174566700L;
		System.out.println("resp1onse : date = " + time);
		System.out.println("resp1onse : date = " + currentTimeMillis);
		//1465174566699
		//1465174566700
		boolean sameDate = isSameDate(time / 1000, currentTimeMillis / 1000);
		System.out.println("resp1onse : sameDate = " + sameDate);
		
	}
	
	/**
	 * 比较是否是同一天
	 * second1: 以秒为单位
	 */
	public static boolean isSameDate(long second1, long second2){
		
		long date = 24L * 60 * 60;
		
		if (second1 - second2 >= date || second2 - second1 >= date) {
			//间隔超出一天
			return false;
		}
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(second1 * 1000);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(second2 * 1000);
		
		return (calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH));
	}

}
