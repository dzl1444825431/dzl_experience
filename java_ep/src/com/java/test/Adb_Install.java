package com.java.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Adb_Install {

	public static void main(String[] args) {
		File file = new File(
				"D:\\360Downloads\\appdownload");

		li(file);


	}

	private static void li(File file) {
		File[] listFiles = file.listFiles();

		for (int i = 0; i < listFiles.length; i++) {
			if (listFiles[i].isDirectory()) {
				//li(listFiles[i]);
			} else {
				System.out.println("adb install " + listFiles[i].getAbsolutePath());
			}

		}
	}

	

}
