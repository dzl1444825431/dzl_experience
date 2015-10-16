package com.java.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileCopy333 {

	public static void main(String[] args) {
		File file = new File(
				"D:\\work\\android\\sources\\libs\\actionbarsherlock");

		li(file);


	}

	private static void li(File file) {
		File[] listFiles = file.listFiles();

		for (int i = 0; i < listFiles.length; i++) {
			if (listFiles[i].isDirectory()) {
				li(listFiles[i]);
			} else {
				if (listFiles[i].getName().endsWith("java")) {
					copy(listFiles[i], "D:\\ww\\" + listFiles[i].getName());
				}
			}

		}
	}

	private static void copy(File source, String target) {
		// System.out.println(111111);
		FileChannel in = null;
		FileChannel out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			inStream = new FileInputStream(source);

			outStream = new FileOutputStream(target);
			in = inStream.getChannel();
			out = outStream.getChannel();
			in.transferTo(0, in.size(), out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(inStream);
			close(in);
			close(outStream);
			close(out);
		}
	}

	private static void close(FileOutputStream outStream) {
		if (outStream != null) {
			try {
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void close(FileChannel in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void close(FileInputStream inStream) {

		if (inStream != null) {
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
