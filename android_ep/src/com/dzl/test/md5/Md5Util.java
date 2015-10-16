package com.dzl.test.md5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

import android.os.Environment;

public class Md5Util {

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

	public static void md5ForFileName() {
		String path = Environment.getExternalStorageDirectory().getPath()
				+ "/ZhongYong/appdownload/";
		String path2 = Environment.getExternalStorageDirectory().getPath()
				+ "/ZhongYong/md5/";
		File file = new File(path);
		System.out.println("resp1onse : " + file.getAbsolutePath() + "\ngetName=" + file.getName());
		long startTime = System.currentTimeMillis();
		for (File f : file.listFiles()) {
			/*
			 * f.renameTo(new File(path +
			 * StringUtil.encrypt(f.getName().replace(".apk", "")) + ".apk"));
			 */
			System.out.println("resp1onse : File = " + f.getName());
			File targetFile = new File(path2 + Md5Util.encrypt(f.getName().replace(".apk", "")) + ".apk");
			if (targetFile.exists()) {
				continue;
			}
			copyWithFileStream( f, targetFile);
//			 break;
		}
		
		System.out.println("resp1onse : useTime = " + (System.currentTimeMillis() - startTime));

	}

	static void copyWithChannels(File aSourceFile, File aTargetFile) {

		FileChannel inChannel = null;
		FileChannel outChannel = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {

			try {
				inStream = new FileInputStream(aSourceFile);
				inChannel = inStream.getChannel();
				outStream = new FileOutputStream(aTargetFile);
				outChannel = outStream.getChannel();
				long bytesTransferred = 0;

				while (bytesTransferred < inChannel.size()) {
					bytesTransferred += inChannel.transferTo(0,
							inChannel.size(), outChannel);
				}
			} finally {

				closeIO(inChannel);
				closeIO(outChannel);
				closeIO(inStream);
				closeIO(outStream);
				
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	static void copyWithBuffer(File aSourceFile, File aTargetFile) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {

			br = new BufferedReader(new FileReader(aSourceFile));
			bw = new BufferedWriter(new FileWriter(aTargetFile));

			char[] buf = new char[4096];
			int len = 0;
			while ((len = br.read(buf)) != -1) {
				bw.write(buf, 0, len);
			}

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeIO(br);
			closeIO(bw);
		}
	}

	static void copyWithFileStream(File aSourceFile, File aTargetFile) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(aSourceFile);
			fos = new FileOutputStream(aTargetFile);
			bis = new BufferedInputStream(fis);
	        bos = new BufferedOutputStream(fos);
			
			byte[] buffer = new byte[1024 * 1024 * 8];
			int len = 0;
			while ((len = bis.read(buffer)) != -1) {
				System.out.println("resp1onse : " + len);
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeIO(bos);
			closeIO(bis);
			closeIO(fos);
			closeIO(fis);
		}
	}

	static void closeIO(Closeable closeable) {

		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			closeable = null;
			
		}
	}

}
