package com.common_dzl;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Properties;

public class 读取properties {

	public static void main(String[] args) {
		
		String path = "/com/common_dzl/android_dzl.properties";

		String pre = "";
		String next = "";
		
//		readFile("src" + path, 2, "map.put(\"", "\", \"\");");// 1:style  other: pre next
		
		setTimeTextArray();
	}
























	/**
	 * 设置时间点 | 2016-01-06
	 */
	public static void setTimeTextArray() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd HH:mm");
		Calendar calendar = Calendar.getInstance();
		System.out.println("resp1onse : calendar = " + calendar);
		System.out.println("resp1onse :" + calendar.getTime().getTime() / 1000 + " = " + simpleDateFormat.format(calendar.getTime()));
//		long time = 1452091822;
//		long time = 1452094900;
		long time = 1452504840;
		calendar.setTimeInMillis(time  * 1000);
		System.out.println("resp1onse : calendar = " + calendar);
		System.out.println("resp1onse :" + time + " = " + simpleDateFormat.format(calendar.getTime()));
		
		time = 1452504600;
		calendar.setTimeInMillis(time  * 1000);
		System.out.println("resp1onse : calendar = " + calendar);
		System.out.println("resp1onse :" + time + " = " + simpleDateFormat.format(calendar.getTime()));
		time = 1452506400;
		calendar.setTimeInMillis(time  * 1000);
		System.out.println("resp1onse : calendar = " + calendar);
		System.out.println("resp1onse :" + time + " = " + simpleDateFormat.format(calendar.getTime()));
		int server_day = calendar.get(Calendar.DAY_OF_MONTH);
//		int server_hour = calendar.get(Calendar.HOUR_OF_DAY);
		int server_minute = calendar.get(Calendar.MINUTE);
		
		System.out.println("resp1onse :" + time + " = " + simpleDateFormat.format(calendar.getTime()));
		
		if (server_minute >= 30) {
			calendar.set(Calendar.MINUTE, 30);
		}else {
			calendar.set(Calendar.MINUTE, 0);
		}
		
		//calendar.set(Calendar.HOUR_OF_DAY, 2);
		
		if (calendar.get(Calendar.HOUR_OF_DAY) >= 23) {
			
			calendar.add(Calendar.HOUR_OF_DAY, 9);
			calendar.set(Calendar.MINUTE, 0);
		}else if (calendar.get(Calendar.HOUR_OF_DAY) < 8) {
			calendar.add(Calendar.HOUR_OF_DAY, 8 - calendar.get(Calendar.HOUR_OF_DAY) );
			calendar.set(Calendar.MINUTE, 0);
		}
		
		
//		System.out.println("resp1onse : format1 = " + format);
//		System.out.println("resp1onse : format2 = " + calendar.get(Calendar.HOUR_OF_DAY));
//		System.out.println("resp1onse : format2 = " + calendar.getTimeInMillis());
		
		String[] aa = new String[32];
		long[] times = new long[31];
		
		String text = "";
		for (int i = 0; i < times.length; i++) {
			
			if (calendar.get(Calendar.DAY_OF_MONTH) != server_day) {
				
				System.out.println("resp1onse : format = 明天" + simpleDateFormat.format(calendar.getTime()));
				text = "明天" + simpleDateFormat.format(calendar.getTime());
			}else {
				text = simpleDateFormat.format(calendar.getTime());
				
				System.out.println("resp1onse : format = " + simpleDateFormat.format(calendar.getTime()));
			}
			
			times[i] = calendar.getTime().getTime();
			calendar.add(Calendar.MINUTE, 30);
			
			aa[i + 1] = text + "~" + simpleDateFormat.format(calendar.getTime());
			if (calendar.get(Calendar.HOUR_OF_DAY) >= 23) {
				
				calendar.add(Calendar.HOUR_OF_DAY, 9);
				calendar.set(Calendar.MINUTE, 0);
			}else if (calendar.get(Calendar.HOUR_OF_DAY) < 8) {
				calendar.add(Calendar.HOUR_OF_DAY, 8 - calendar.get(Calendar.HOUR_OF_DAY) );
				calendar.set(Calendar.MINUTE, 0);
			}
			
		}
		System.out.println("resp1onse : aa = " + Arrays.toString(aa));
	}
	/**
	 * 设置时间点 | 2016-01-06
	 */
	public static void setTimeTextArray2() {
		Calendar calendar = Calendar.getInstance();
		System.out.println("resp1onse : calendar = " + calendar);
//		long time = 1452091822;
//		long time = 1452094900;
		long time = 1452065728;
		time = 1452097188;
		time = 1452070353;//本机
		calendar.setTimeInMillis(time  * 1000);
		System.out.println("resp1onse : calendar = " + calendar);
		
		System.out.println("resp1onse : v = " + calendar.get(Calendar.YEAR));
		System.out.println("resp1onse : v = " + calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println("resp1onse : v = " + calendar.get(Calendar.MONTH) + 1);
		System.out.println("resp1onse : server_hour = " + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("resp1onse : server_minute = " + calendar.get(Calendar.MINUTE));
		
		String[] aa = new String[31];
		int a = 8;
		String b = "0";
		String b1 = ":00";
		String b2 = ":30";
		String hour = "";
		int server_day = calendar.get(Calendar.DAY_OF_MONTH);
		int server_hour = calendar.get(Calendar.HOUR_OF_DAY);
		int server_minute = calendar.get(Calendar.MINUTE);
		
		if (server_minute >= 30) {
			calendar.set(Calendar.MINUTE, 30);
		}else {
			calendar.set(Calendar.MINUTE, 0);
		}
		
		//calendar.set(Calendar.HOUR_OF_DAY, 2);
		
		if (calendar.get(Calendar.HOUR_OF_DAY) >= 23) {
			
			calendar.add(Calendar.HOUR_OF_DAY, 9);
			calendar.set(Calendar.MINUTE, 0);
		}else if (calendar.get(Calendar.HOUR_OF_DAY) < 8) {
			calendar.add(Calendar.HOUR_OF_DAY, 8 - calendar.get(Calendar.HOUR_OF_DAY) );
			calendar.set(Calendar.MINUTE, 0);
		}
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		String format = simpleDateFormat.format(calendar.getTime());
//		System.out.println("resp1onse : format1 = " + format);
//		System.out.println("resp1onse : format2 = " + calendar.get(Calendar.HOUR_OF_DAY));
//		System.out.println("resp1onse : format2 = " + calendar.getTimeInMillis());
		long[] times = new long[31];
		
		for (int i = 0; i < times.length; i++) {
			
			if (calendar.get(Calendar.DAY_OF_MONTH) != server_day) {
				
				System.out.println("resp1onse : format = 明天" + simpleDateFormat.format(calendar.getTime()));
			}else {
				
			}
			
			System.out.println("resp1onse : format = " + simpleDateFormat.format(calendar.getTime()));
			calendar.add(Calendar.MINUTE, 30);
			times[i] = calendar.getTime().getTime();
			if (calendar.get(Calendar.HOUR_OF_DAY) >= 23) {
				
				calendar.add(Calendar.HOUR_OF_DAY, 9);
				calendar.set(Calendar.MINUTE, 0);
			}else if (calendar.get(Calendar.HOUR_OF_DAY) < 8) {
				calendar.add(Calendar.HOUR_OF_DAY, 8 - calendar.get(Calendar.HOUR_OF_DAY) );
				calendar.set(Calendar.MINUTE, 0);
			}
		}
		
//		int index = 1;
//		String prefix = "";
//		server_minute = 59;
//		
//		if (server_hour >= 8 || server_hour < 23) {
//			
//			for (int i = server_hour; i < 23; i++) {
//				hour = i < 10 ? (b + i) : (i + "");
//				
//				if (server_hour > i) {
//					prefix = "明天";
//					aa[index++] = prefix + hour + b1 + "~" + hour + b2;
//					aa[index++] = prefix + hour + b2 + "~" + ((i + 1) < 10 ? (b + (i + 1)) : (i + 1))  + b1;
//				}else {
//					if (server_hour == i) {
//						prefix = "明天";
//						
//						if (server_minute < 30) {
//							prefix = "";
//							aa[index++] = prefix + hour + b1 + "~" + hour + b2;
//							aa[index++] = prefix + hour + b2 + "~" + ((i + 1) < 10 ? (b + (i + 1)) : (i + 1))  + b1;
//						}else {
//							aa[index++] = prefix + hour + b1 + "~" + hour + b2;
//							prefix = "";
//							aa[index++] = prefix + hour + b2 + "~" + ((i + 1) < 10 ? (b + (i + 1)) : (i + 1))  + b1;
//						}
//						
//					}else {
//						prefix = "";
//						
//						aa[index++] = prefix + hour + b1 + "~" + hour + b2;
//						aa[index++] = prefix + hour + b2 + "~" + ((i + 1) < 10 ? (b + (i + 1)) : (i + 1))  + b1;
//					}
//				}
//				
//			}
//		}
//		
//		
//		System.out.println("resp1onse : aa = " + Arrays.toString(aa));
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
					if (line.trim().length() > 0) {
						
						String[] array = line.split("=");
						androidToStyle(array);
					}
				}
			}else {
				
				while ((line = br.readLine()) != null) {
					if (line.trim().length() > 0) {
						
						String[] array = line.split("=");
						androidToUrl(array, pre, next);
					}
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
