package com.java.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class TestUtil {
//	public static float formatFloat(float f, int bit){
//		BigDecimal bd = new BigDecimal(f);
//		bd = bd.setScale(bit,BigDecimal.ROUND_DOWN);
//		System.out.println(f + "==========" + bd);
//		return bd.floatValue();
//	}
//	
//	public static String formatFloat2(float f, int bit){
//		
//		DecimalFormat df = new DecimalFormat("#.00");
//		
//		return df.format(f);
//	}
	
	public static String formatFloat3(float f, int bit){
		
		System.out.println(Float.toString(f));
		System.out.println();
		String src = String.valueOf(f);
		if (f < 0f)  return src;
		
		int I = src.indexOf(".");
		if (bit < 1)  return src;
		else if (bit == 1)  return src.substring(0,I + 2);
		
		int N = src.length();
	
		if (f < 1f) {
			String before = "0.";
			String after = "";
			for (int i = 0; i < bit; i++) {
				f *= 10;
				after += "0";
			}
			int mul = (int) f;
			if (mul == 0) return before + after;
			return before + mul;
		}
		String sub;
		if (I + bit < N) {
			sub = src.substring(0,I + bit + 1);
		}else if (I + bit == N) {
			sub = src + "0";
		}else {
			sub = src;
			for (int i = 0; i < I + bit - N + 1; i++) {
				sub += "0";
			}
		}
		
		return sub;
	}
	
	public static void main(String[] args) {
		System.out.println("resp1onse : " + System.currentTimeMillis());
		System.out.println("resp1onse : " + System.nanoTime());
		long t = System.nanoTime();
		long t1 = System.currentTimeMillis();
		System.out.println("resp1onse : " + (System.nanoTime() - System.currentTimeMillis()));
		System.out.println("resp1onse : " + (System.nanoTime() - t));
		System.out.println("resp1onse : " + (System.currentTimeMillis() - t1));
//		System.out.println(formatFloat(100.359f,2));
//		float ff = formatFloat(100.31f,2);
//		System.out.println(ff);
//		System.out.println(formatFloat(100.31f,2));
//		System.out.println(formatFloat(100.35f,2));
//		System.out.println(formatFloat(100.36f,2));
//		System.out.println(formatFloat(1f,2));
//		
////		DecimalFormat df = new DecimalFormat("#.00000");
////		System.out.println( df.format("2.99976"));
//		NumberFormat n = NumberFormat.getInstance();
//		
//		DecimalFormat df1 = new DecimalFormat("####.000");
//		System.out.println(df1.format(1234.561));
//		
//		DecimalFormat df2 = new DecimalFormat("####.0#");
//		System.out.println(df2.format(1234.56111));
//		
//		
//		
//		System.out.println(Float.MAX_VALUE);
//		System.out.println((Float.MAX_VALUE+"").length());
//		
//		
//		System.out.println(formatFloat2(100.31f,2));
//		System.out.println(formatFloat2(100.35f,2));
//		System.out.println(formatFloat2(100.36f,2));
//		System.out.println(formatFloat2(1000050.01f,2));
//		System.out.println(formatFloat2(1000050.000001f,2));
//		System.out.println(formatFloat2(0.000001f,2));
//		System.out.println(formatFloat2(0.1f,2));
//		System.out.println(formatFloat2(0.11f,2));
//		System.out.println(formatFloat2(1f,2));
//		
//		
//		double x=23.5455; 
//		NumberFormat ddf1=NumberFormat.getNumberInstance() ; 
//
//		ddf1.setMaximumFractionDigits(2); 
//		String s= ddf1.format(x) ; 
//		System.out.print(s);
		
//		System.out.println(formatFloat3(100.31f,2));
//		System.out.println(formatFloat3(100.35f,2));
//		System.out.println(formatFloat3(100.36f,2));
//		System.out.println(formatFloat3(1000050.01f,2));
//		System.out.println(formatFloat3(1000050.000001f,2));
//		System.out.println(formatFloat3(0.000001f,2));
//		System.out.println(formatFloat3(0.00001f,2));
//		System.out.println(formatFloat3(0.0001f,2));
//		System.out.println(formatFloat3(0.1f,2));
//		System.out.println(formatFloat3(0.11f,2));
//		System.out.println(formatFloat3(1f,2));
//		System.out.println(formatFloat3(0f,2));
//		
//		System.out.println("===============================");
//		
//		System.out.println(formatFloat3(100.31f,2));
//		System.out.println(formatFloat3(100.35f,2));
//		System.out.println(formatFloat3(100.36f,2));
//		System.out.println(formatFloat3(1000050.01f,2));
//		System.out.println(formatFloat3(1000050.000001f,2));
//		
		
		System.out.println(1024>>>1);
		System.out.println(1024>>>2);
		System.out.println(formatFloat3(0.000001f,2));
		System.out.println(formatFloat3(0.00001f,2));
		System.out.println(formatFloat3(0.0001f,2));
		System.out.println(formatFloat3(0.1f,2));
		System.out.println(formatFloat3(0.11f,2));
		System.out.println(formatFloat3(1f,2));
		System.out.println(formatFloat3(10f,2));
		System.out.println(formatFloat3(11.1f,2));
		System.out.println(formatFloat3(11.91f,2));
		System.out.println(formatFloat3(1.00000f,2));
		System.out.println(formatFloat3(1.00001f,2));
		System.out.println(formatFloat3(1.08001f,2));
		System.out.println(formatFloat3(111111.08001f,2));
		System.out.println(formatFloat3(0f,2));
		
//		System.out.println(String.valueOf(0.0000001f));
//		String m = String.valueOf(0.0000001f);
//		System.out.println(m.indexOf("."));
//		System.out.println(String.valueOf(100000.0000001f));
//		m = String.valueOf(100000.0000001f);
//		System.out.println(m.indexOf("."));
		
	}
	
	

}
