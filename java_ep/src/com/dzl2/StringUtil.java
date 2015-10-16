package com.dzl2;

import java.io.File;
import java.security.MessageDigest;


public class StringUtil {
	
	/***
	 * MD5加码 生成32位md5码
	 */
	public static String encrypt(String inStr) {
		try {
			byte[] btInput = inStr.getBytes("utf-8");
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(val));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}

	}
	
	public static void main(String[] args) {
		File file = new File("D:\\ZhongYong\\appdownload\\aa");
		for (File f : file.listFiles()) {
			f.renameTo(new File("D:\\ZhongYong\\appdownload\\" + StringUtil.encrypt(f.getName().replace(".apk", ""))+ ".apk"));
			//break;
		}
	}
	
}
