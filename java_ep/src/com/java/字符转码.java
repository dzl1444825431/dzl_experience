package com.java;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.omg.IOP.Encoding;

public class 字符转码 {

	public static void main(String[] args) {
		String s = "清山";
		
		convertionString(s);
		
	}

	public static void convertionString(String s) {
		try {

			byte[] b = s.getBytes("gbk");// 编码
			String sa = new String(b, "gbk");// 解码:用什么字符集编码就用什么字符集解码
			System.out.println("resp1onse :gbk s = " + s +" sa = " + sa +" byte = " + Arrays.toString(b));

			b = sa.getBytes("utf-8");// 编码
			sa = new String(b, "utf-8");// 解码
			System.out.println("resp1onse :utf-8 s = " + s +" sa = " + sa +" byte = " + Arrays.toString(b));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	

}
