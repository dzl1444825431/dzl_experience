package com.java.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		addLogforMethods3("D:\\work\\workspace_eclipse\\push\\src\\com\\handmark\\pulltorefresh\\MainActivity.java");
//		addLogforMethods3("D:\\work\\workspace_eclipse\\push\\src\\com\\handmark\\pulltorefresh\\library\\PullToRefreshListView.java");
	}

	/**
	 * 使用正则匹配方法声明
	 *   分单行方法、多行方法、构造方法
	 *   再根据大括号的匹对来进行输入输出
	 */
	
	static void addLogforMethods3(String source) {
		File sourceFile = new File(source);
		File targeFile = null;
		String target = source.replace(sourceFile.getName(),
				sourceFile.getName() + "2");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		String className = sourceFile.getName().replace(".java", "");
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			targeFile = new File(target);
			fw = new FileWriter(targeFile);
			bw = new BufferedWriter(fw);

			String line = "";
			String methodNameLine = "";
			String methodinternalLine = "";
			String trimLine = "";


			Pattern pattern = Pattern
					.compile("(static|protected|private|void|public)+.*\\(.*\\)\\s*\\{");
			Matcher matcher = null;
//			List<String> method = new ArrayList<String>();
//			List<Integer> braceSign = new ArrayList<Integer>();
			int bSign = 0;
			int internalNum = 0;

			boolean isMethod = false;//判断是否是方法
			boolean isMethodFirstLine = false;//判断是否是方法
			boolean isVoidMethod = false;//判断是否是无返回方法
			boolean isConstructMethod = false;//判断是否构造方法
			boolean isInternalMethod = false;//判断匿名内部方法

			// 单行、抽象、构造方法、子类构造方法、成员变量 接口 类
			while ((line = br.readLine()) != null) {
				trimLine = line.trim();
				
				
				if (isMethod) {
					
					if (trimLine.indexOf("{") != -1) {
						bSign++;
						
						matcher = pattern.matcher(trimLine);
						
						if (matcher.find()) {//方法嵌套
							isInternalMethod = true;
							methodinternalLine = trimLine;
							internalNum = bSign - 1;
							
							bw.write(line + "\r\n");
							bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodinternalLine + " start internal " + bSign + "\");\r\n");
							continue;
						}
						
					}
					
					if (trimLine.indexOf("}") != -1) {
						bSign--;
						
						if (isInternalMethod && (internalNum == bSign)) {
							isInternalMethod = false;
							bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodinternalLine + " end internal " + (bSign + 1) + "\");\r\n");
						}
						
						if (bSign == 0) {
							if (isVoidMethod) {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " end void \");\r\n");
							}
							
							if (isConstructMethod) {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " end contruct \");\r\n");
							}
							
							bw.write(line + "\r\n");
							isMethod = false;
							isVoidMethod = false;
							isMethodFirstLine = false;
							isConstructMethod = false;
							continue;
						}
						
					}
					
					if (isMethodFirstLine) {//方法首行
						
						isMethodFirstLine = false;
						
						if (isConstructMethod) {
							if (trimLine.startsWith("super") || trimLine.startsWith("this")) {
								bw.write(line + "\r\n");
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " start construct \");\r\n");
								continue;
							}else {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " start construct \");\r\n");
								bw.write(line + "\r\n");
								continue;
							}
						}
						
						if (!isVoidMethod) {//有返回值方法
							bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " start return \");\r\n");
							if (trimLine.startsWith("return")) {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " end return \");\r\n");
							}
							bw.write(line + "\r\n");
							continue;
						}
					}else {
						
						
						if (bSign == 1) {//方法结束
							
							if (trimLine.startsWith("return") || trimLine.startsWith("throw")) {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " end return \");\r\n");
								bw.write(line + "\r\n");
								continue;
							}
							
						}
						
						if (trimLine.startsWith("return") || trimLine.startsWith("throw")) {
							bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " end return \");\r\n");
						}
						
					}
					
					
					bw.write(line + "\r\n");
					continue;
					
				}else {
					
					matcher = pattern.matcher(trimLine);
					
					if (matcher.find()) {
						
						if (trimLine.indexOf("new ") != -1) {
							bw.write(line + "\r\n");
							continue;
						}
						methodNameLine = trimLine;
						
						if (trimLine.indexOf("}") != -1) {//单行
							isMethod = false;
							isVoidMethod = false;
							bw.write(line.replace("}", " System.out.println(\"resp1onse " + className + ": " + methodNameLine + " single\");}") + "\r\n");
							continue;
						}
						
						isMethod = true;
						isMethodFirstLine = true;
						
						bSign = 1;
						
						if (trimLine.indexOf("void") != -1) {//void方法
							isVoidMethod = true;
							bw.write(line + "\r\n");
							bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " start void \");\r\n");
							
							continue;
						}

						
						String[] contruct = trimLine.substring(0,trimLine.indexOf("("))
								.replace("public", "")
								.replace("protected", "")
								.replace("private", "").trim().split(" ");
						
						if (contruct.length == 1) {//构造方法只能有访问控制修饰词
							isConstructMethod = true;
						}
						
						bw.write(line + "\r\n");
						continue;
						
					}else {
						
						bw.write(line + "\r\n");
						continue;
					}
				}

				
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
	
}
