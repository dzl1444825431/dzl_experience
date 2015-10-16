package com.java.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 */
public class AddDebugLogUseRegex2 {

	public static void main(String[] args) {
		// String source =
		// "D:\\work\\android\\sources\\libs\\actionbarsherlock\\src\\com\\actionbarsherlock\\internal\\widget\\AbsActionBarView.java";
		String source = "D:\\android\\workspace\\java\\src\\com\\java\\test\\TestClass2.java";

		// addLogforMethods(source);
		bufferReader(source);

	}

	static void addLogforMethods(String source) {
		File sourceFile = new File(source);
		File targeFile = null;
		String target = source.replace(sourceFile.getName(),
				sourceFile.getName() + "2");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		String className = sourceFile.getName().replace(".java", "");
		// String subClassName = sourceFile.getName().replace(".java", "");
		Pattern emptyLine = Pattern.compile("^(\\s*)$");
		Pattern singleComment = Pattern.compile("^(\\s*[/]{2})");
		Pattern chunkCommentStart = Pattern.compile("^(\\s*/\\*)");
		Pattern chunkCommentEnd = Pattern.compile("(\\*/)$");
		Pattern singleChunkComment = Pattern
				.compile("(^(\\s*/\\*)).*((\\*/\\s*)$)");

		Pattern classReg = Pattern
				.compile("(^(\\s*(public|protected|private)?\\s*(static\\s*)?(final\\s*|abstract\\s*)?(static)?))\\s*class\\s*(\\w*)\\s*.*((\\{\\s*)$)");

		Pattern singleMethod = Pattern
				.compile("(.*)\\{(\\s*(super\\(|this\\(|return|throw\\s)?)?\\s*.*((\\}\\s*)$)");
		// Pattern chunkMethodReturn =
		// Pattern.compile(".*\\{(\\s*((return\\()|(throw\\())?)?.*((\\}\\s*)$)");

		Pattern method = Pattern
				.compile("(^\\s*(public\\s*|protected\\s*|private))(((\\{\\s*)(\\s*[/]{2}.*)?)$)");
		Pattern methodStart = Pattern
				.compile("(^(?!.*(class\\s|interface\\s)).*)(((\\{\\s*)(\\s*[/]{2}.*)?)$)");
		Pattern methodEnd = Pattern.compile(".*((\\}\\s*)(\\s*[/]{2}.*)?$)");
		Matcher mathcer = null;

		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			targeFile = new File(target);
			fw = new FileWriter(targeFile);
			bw = new BufferedWriter(fw);

			String line = "";

			while ((line = br.readLine()) != null) {

				bw.write(line);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		sourceFile.delete();
		targeFile.renameTo(sourceFile);
	}

	static void bufferReader(String source) {
		File sourceFile = new File(source);

		FileReader fr = null;
		BufferedReader br = null;
		StringBuffer sBuffer = new StringBuffer();

		try {
			// out = new FileOutputStream(targeFile);
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			char[] buffer = new char[(int) sourceFile.length()];

			int i = 0;
			int b = 0;
			while ((b = br.read(buffer)) != -1) {
				i++;
				sBuffer.append(buffer);
			}
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Pattern p = Pattern.compile("[\\s\\S]*?\\{[\\s\\S]*?\\}");
//		Matcher m = p.matcher(sBuffer.toString().replace("\r\n", "nn"));
		Matcher m = p.matcher(sBuffer.toString());
//		String[] split = p.split(sBuffer.toString());
//		System.out.println("resp1onse1:split.length " + split.length);
//		for (int i = 0; i < split.length; i++) {
//			System.out.println("resp1onse1: " + split[i]);
//		}
		int N = 0;
//		while (m.find()) {
//			N = aa(m, N);
//		}
		N = aa(m, N);
		System.out.println("resp1onse1: N = " + N);
		
	}

	private static int aa(Matcher m, int N) {
		while (m.find()) {
			N++;
			System.out.println("resp1onse1: " + N + " " + m.group());
			System.out.println("resp1onse1: " + "===================" + m.groupCount());
			for (int i = 0; i < m.groupCount(); i++) {
				System.out.println("resp1onse1: " + m.group(i));
			}
		}
		return N;
	}

}
