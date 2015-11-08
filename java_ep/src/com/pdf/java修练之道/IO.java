package com.pdf.java修练之道;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IO {
	
	public static void main(String[] args) {
		Path p = Paths.get("D:\\test\\aa");
		try {
			Files.createFile(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
