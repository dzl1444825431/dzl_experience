package com.java.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Llog {

	private static HashMap<String, String> resMap = new HashMap<String, String>(1024);
	public static Map<String,Object> map = new TreeMap<String,Object>();
	
	public static void main(String[] args) {
		//String source = "D:/workspace/demo/java/src/com/java/test/TestClass.java";
		//String source = "D:/work/android/sources/app/src/cn/zhongyongapp/activity/AboutActivity.java";
		//String source = "D:/work/android/sources/app/src/cn/zhongyongapp/activity/APPCommentActivity.java";
		/*String source = "D:/work/android/sources/app/res/values/color.xml";
		//addLogforMethods(source);
		//getChineseString(source);
		getColors(source);
		System.out.println(map);
		
		sortTreeMapValue();
		
		String[] split = colorString.split("-->");
		map.clear();
		for (String str : split) {
			map.put(str+"-->", "\n");
		}
		System.out.println(map);*/
		
//		addLogforMethods3("D:\\work\\workspace_eclipse\\miFile\\src\\org\\swiftp\\ProxyDataSocketFactory.java");
//		addLogforMethods3("D:\\baiduYun\\baiduYun-dzl-office-150810\\test\\src\\com\\dzl\\test\\viewPager\\ViewPagerFragment.java");
//		addLogforMethods3("D:\\baiduYun\\baiduYun-dzl-office-150810\\test\\src\\com\\dzl\\test\\textview\\CustomTextView.java");
		addLogforMethods3("D:\\baiduYun\\dzl_github\\1dianyun\\yidianyun\\yidianyun_dzl\\src\\com\\dzl\\zyyidianyun\\ui\\HorizontalListView.java");
//		replaceResourceIntToResourceType("D:\\baiduYun\\baiduYun-dzl-office-150810\\dianyixia-dzl\\src\\com\\dingzai\\dianyixia\\ui\\StartupActivity.java");
//		replaceResourceIntToResourceType("D:\\baiduYun\\baiduYun-dzl-office-150810\\test\\src\\com\\dzl\\test\\viewPager\\ViewPagerFragment.java");
//String a = "e			} catch (Exception ee) {}e";
//System.out.println("resp1onse : ind=" + indexOf(a, "e"));
//System.out.println("resp1onse : ind1=" + indexOf("", "e"));
//System.out.println("resp1onse : ind2=" + indexOf("e", "e"));
//System.out.println("resp1onse : " + a.split("}").length);
		System.out.println("resp1onse : OK ");
	}

	
	
	static void getImportClasses(String source){
		File sourceFile = new File(source);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);

			String line = "";
			String trimLine = "";
			
			while ((line = br.readLine()) != null) {
								
				trimLine = line.trim();
				
				if (trimLine.startsWith("import")) {
					//System.out.println("respone : " + trimLine);
					if (map.containsKey(trimLine)) {
						map.put(trimLine, (Integer)map.get(trimLine) + 1);
					}else {
						map.put(trimLine, 1);
					}
					continue;
				}
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			targeFile = new File(target);
			fw = new FileWriter(targeFile);
			bw = new BufferedWriter(fw);

			String line = "";
			String methodLine = "";
			String trimLine = "";
//			boolean isContructMethod = false;
//			boolean isContructMethodEnd = false;
			//boolean isSingleLineMethod = false;
			
			boolean isMethodStart = false;
			boolean isSingleDeclarationMethod = false;
			boolean isDoubleDeclarationMethod = false;
			
			//单行、抽象、构造方法、子类构造方法、成员变量 接口 类
			while ((line = br.readLine()) != null) {
				trimLine = line.trim();
				
				if (trimLine.equals("}")) {
					isMethodStart = false;
					isSingleDeclarationMethod = false;
					isDoubleDeclarationMethod = false;
					bw.write(line + "\r\n");
					continue;
				}
				
				if (trimLine.startsWith("throw") || (trimLine.contains("if") && (!isSingleDeclarationMethod)) ) {
					isMethodStart = false;
					isDoubleDeclarationMethod = false;
					isSingleDeclarationMethod = false;
					
					bw.write(line + "\r\n");
					continue;
				}
				
				if (isSingleDeclarationMethod) {
					isMethodStart = false;
					isSingleDeclarationMethod = false;
					isDoubleDeclarationMethod = false;
					//System.out.println("resp1onse: isSingleDeclarationMethod" + line);
					if (trimLine.startsWith("super") || trimLine.startsWith("this(")) {
						//System.out.println("resp1onse: " + 111);
						bw.write(line + "\r\n");
						bw.write("\t\tSystem.out.println(\"resp1onse " +className + ":"
								+ methodLine.substring(0, methodLine.length() - 1) + " \");\r\n");
					}else {
						//System.out.println("resp1onse: " + 2222);
						bw.write("\t\tSystem.out.println(\"resp1onse " +className + ":"
								+ methodLine.substring(0, methodLine.length() - 1) + " \");\r\n");
						bw.write(line + "\r\n");
					}
					
					continue;
				}
				
				if (isDoubleDeclarationMethod) {
					if (trimLine.endsWith("{")) {//单行声明
						methodLine = line;
						isSingleDeclarationMethod = true;//多行方法与单行方法的共性：方法体 判断逻辑可共用
						isDoubleDeclarationMethod = false;
					}
					bw.write(line + "\r\n");
					continue;
				}
				
				
				if (       trimLine.startsWith("*") 
						|| trimLine.startsWith("/")
						|| trimLine.startsWith("@")
						|| (
								trimLine.endsWith(";") 
								/*&& (!trimLine.startsWith("return "))
								&& (!trimLine.startsWith("super("))
								&& (!trimLine.startsWith("this("))*/
							)
					)
				{
				
					bw.write(line + "\r\n");
					continue;
				}
				
				
				
				
				
				
				if (trimLine.contains("(")) {
					//System.out.println("resp1onse: line.contains('(' " + line);
					if (trimLine.contains("=") || trimLine.contains("new ") || trimLine.startsWith("switch")) {
						bw.write(line + "\r\n");
						continue;
					}
					
					isMethodStart = true;
					if (trimLine.endsWith("{")) {//单行声明
						bw.write(line + "\r\n");
						isSingleDeclarationMethod = true;
						methodLine = line;
						continue;
					}else if (trimLine.endsWith("}")) {//单行方法
						int i = -1;
						if (line.contains("return")) {
							i = line.indexOf("return");
						}else {
							i = line.indexOf("}");
						}
						
						bw.write(line.substring(0,i) + "\r\n");
						bw.write("\t\tSystem.out.println(\"resp1onse " +className + ":" + line.substring(0,i) + " \");\r\n");
						bw.write("\t\t" + line.substring(i,line.length()) + "\r\n");
						isMethodStart = false;
					}else {//多行
						
						if (!(trimLine.contains("class ") || trimLine.contains("interface "))) {
							isDoubleDeclarationMethod = true;
							methodLine = line;
						}
						bw.write(line + "\r\n");
						continue;
					}
					
				}else {
					bw.write(line + "\r\n");
					continue;
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
	
	
	static void addLogforMethods2(String source) {
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
			String methodLine = "";
			String trimLine = "";
			String previousLine = "";
//			boolean isContructMethod = false;
//			boolean isContructMethodEnd = false;
			//boolean isSingleLineMethod = false;
			
			boolean isMethodStart = false;
			boolean isSingleDeclarationMethod = false;
			boolean isDoubleDeclarationMethod = false;
			boolean isVoidMethod = false;
			boolean endLineBreak = false;
			
			//单行、抽象、构造方法、子类构造方法、成员变量 接口 类
			while ((line = br.readLine()) != null) {
				trimLine = line.trim();
				
				if (trimLine.startsWith("return") ) {
					bw.write("\t\tSystem.out.println(\"resp1onse " +className + ":"
							+ methodLine.replace("\"", "'") + " end return--- \");\r\n");
				}
				
				if (trimLine.startsWith("throw") || trimLine.startsWith("if")|| (trimLine.startsWith("break") || trimLine.startsWith("return") )) {
					bw.write(line + "\r\n");
					endLineBreak = true;
					continue;
				}
				
				if (trimLine.equals("}")) {
					
					if (line.length() > 1 && line.indexOf("}") == methodLine.indexOf("p") && !endLineBreak ) {
						bw.write("\t\tSystem.out.println(\"resp1onse " +className + ":"
								+ methodLine.replace("\"", "'") + " end--- \");\r\n");
					}
					
					bw.write(line + "\r\n");
					isMethodStart = false;
					isSingleDeclarationMethod = false;
					isDoubleDeclarationMethod = false;
					continue;
				}
				
				previousLine = line;
				endLineBreak = false;
				
				if (!isSingleDeclarationMethod) {
					isMethodStart = false;
					isDoubleDeclarationMethod = false;
					isSingleDeclarationMethod = false;
				}
				
				previousLine = line;
				if (isSingleDeclarationMethod) {
					isMethodStart = false;
					isSingleDeclarationMethod = false;
					isDoubleDeclarationMethod = false;
					//System.out.println("resp1onse: isSingleDeclarationMethod" + line);
					if (trimLine.startsWith("super") || trimLine.startsWith("this(")) {
						//System.out.println("resp1onse: " + 111);
						bw.write(line + "\r\n");
						bw.write("\t\tSystem.out.println(\"resp1onse " +className + ":"
								+ methodLine.substring(0, methodLine.length() - 1) + " start \");\r\n");
					}else {
						//System.out.println("resp1onse: " + 2222);
						bw.write("\t\tSystem.out.println(\"resp1onse " +className + ":"
								+ methodLine.substring(0, methodLine.length() - 1) + " start \");\r\n");
						bw.write(line + "\r\n");
					}
					
					continue;
				}
				
				if (isDoubleDeclarationMethod) {
					if (trimLine.endsWith("{")) {//单行声明
						methodLine = line;
						isVoidMethod = methodLine.contains("void");
						isSingleDeclarationMethod = true;//多行方法与单行方法的共性：方法体 判断逻辑可共用
						isDoubleDeclarationMethod = false;
					}
					bw.write(line + "\r\n");
					continue;
				}
				
				
				if (       trimLine.startsWith("*") 
						|| trimLine.startsWith("/")
						|| trimLine.startsWith("@")
						|| (
								trimLine.endsWith(";") 
								/*&& (!trimLine.startsWith("return "))
								&& (!trimLine.startsWith("super("))
								&& (!trimLine.startsWith("this("))*/
							)
					)
				{
					bw.write(line + "\r\n");
					continue;
				}
				
								
				if (trimLine.contains("(")) {
					//System.out.println("resp1onse: line.contains('(' " + line);
					if (trimLine.contains("=") || trimLine.contains("new ") || trimLine.startsWith("switch")) {
						bw.write(line + "\r\n");
						continue;
					}
					
					isMethodStart = true;
					if (trimLine.endsWith("{")) {//单行声明
						bw.write(line + "\r\n");
						isSingleDeclarationMethod = true;
						methodLine = line;
						isVoidMethod = methodLine.contains("void");
						continue;
					}else if (trimLine.endsWith("}")) {//单行方法
						int i = -1;
						if (line.contains("return")) {
							i = line.indexOf("return");
						}else {
							i = line.indexOf("}");
						}
						
						bw.write(line.substring(0,i) + "\r\n");
						bw.write("\t\tSystem.out.println(\"resp1onse " +className + ":" + line.substring(0,i) + " \");\r\n");
						bw.write("\t\t" + line.substring(i,line.length()) + "\r\n");
						isMethodStart = false;
					}else {//多行
						
						if (!(trimLine.contains("class ") || trimLine.contains("interface "))) {
							isDoubleDeclarationMethod = true;
							methodLine = line;
							isVoidMethod = methodLine.contains("void");
						}
						bw.write(line + "\r\n");
						continue;
					}
					
				}else {
					bw.write(line + "\r\n");
					if (isMethodStart) {}
					continue;
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
			String previousLine = "";//前一行
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
				
				if (trimLine.length() == 0 || trimLine.charAt(0) == '/' || trimLine.charAt(0) == '*') {
					bw.write(line + "\r\n");
					continue;
				}
				
				if (isMethod) {
					
					if (trimLine.indexOf("{") != -1) {
						
						bSign += indexOf(trimLine, "{");
						
						matcher = pattern.matcher(trimLine);
						
						if (matcher.find()) {//方法嵌套
							isInternalMethod = true;
							methodinternalLine = trimLine;
							internalNum = bSign - 1;
							previousLine = trimLine;
							
							bw.write(line + "\r\n");
							bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodinternalLine + " start internal " + bSign + "\");\r\n");
							continue;
						}
						
					}
					
					if (trimLine.indexOf("}") != -1) {
						
						bSign -= indexOf(trimLine, "}");
						
						if (isInternalMethod && (internalNum == bSign)) {
							isInternalMethod = false;
							if (!previousLine.startsWith("return")) {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodinternalLine + " end internal " + (bSign + 1) + "\");\r\n");
							}
						}
						
						if (bSign == 0) {
							if (isVoidMethod && !previousLine.startsWith("return")) {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " end void \");\r\n");
							}
							
							if (isConstructMethod) {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " end constructor \");\r\n");
							}
							
							bw.write(line + "\r\n");
							isMethod = false;
							isVoidMethod = false;
							isMethodFirstLine = false;
							isConstructMethod = false;
							previousLine = trimLine;
							continue;
						}
						
					}
					
					if (isMethodFirstLine) {//方法首行
						
						isMethodFirstLine = false;
						
						if (isConstructMethod) {
							if (trimLine.startsWith("super") || trimLine.startsWith("this")) {
								bw.write(line + "\r\n");
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " start constructor \");\r\n");
								previousLine = trimLine;
								continue;
							}else {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " start constructor \");\r\n");
								bw.write(line + "\r\n");
								previousLine = trimLine;
								continue;
							}
						}
						
						if (!isVoidMethod) {//有返回值方法
							bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " start return \");\r\n");
							if (trimLine.startsWith("return")) {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " end return \");\r\n");
							}
							bw.write(line + "\r\n");
							previousLine = trimLine;
							continue;
						}
					}else {
						
						
						if (bSign == 1) {//方法结束
							
							if (trimLine.startsWith("return") || trimLine.startsWith("throw")) {
								
								if ((!previousLine.startsWith("if")) && (!previousLine.contains("{"))) {
									bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " end return  =1\");\r\n");
								}
								bw.write(line + "\r\n");
								previousLine = trimLine;
								continue;
							}
							
						}else {
							
							if ((trimLine.startsWith("return") || trimLine.startsWith("throw")) && (!previousLine.startsWith("if")) && (!previousLine.contains("{")) ) {
								bw.write("\t\tSystem.out.println(\"resp1onse " + className + ": " + methodNameLine + " end return if \");\r\n");
							}
							
						}
						
						
					}
					
					
					bw.write(line + "\r\n");
					previousLine = trimLine;
					continue;
					
				}else {
					
					
					
					matcher = pattern.matcher(trimLine);
					previousLine = trimLine;
					
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
						
						if (contruct.length == 1) {//构造方法  只能有访问控制修饰词
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
	
	static void getChineseString(String source) {
		System.out.println("resp1onse : " + source);
		File sourceFile = new File(source);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			
			String line = "";
			String trimLine = "";
			
			Pattern pattern = Pattern.compile("\"[\\u4e00-\\u9fa5]+(.*)\"");
			Matcher matcher = null;
			while ((line = br.readLine()) != null) {
				trimLine = line.trim();
				
				matcher = pattern.matcher(trimLine);
				
				while(matcher.find()){
					System.out.println("<string name=\"java_\">" + matcher.group().replaceAll("\"", "") + "</string>");
				}
				
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}



	static void getColors(String source) {
		File sourceFile = new File(source);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			
			String line = "";
			String trimLine = "";
			
			Pattern pattern = Pattern.compile("<.*>(#.*)</color>");
			Matcher matcher = null;
			while ((line = br.readLine()) != null) {
				trimLine = line.trim();
				
				matcher = pattern.matcher(trimLine);
				
				while(matcher.find()){
					String key = matcher.group(1).toLowerCase();
					//System.out.println(key);
					if (map.containsKey(key)) {
						map.put(key, (Integer)map.get(key) + 1);
					}else {
						map.put(key, 1);
					}
					continue;
				}
				
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void sortTreeMapValue() {
		List<String> valueAndKeys = new ArrayList<String>();
		int i = 1;
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while(true){
			while (it.hasNext()) {
				String key = (String) it.next();
				if ((int)map.get(key) == i) {
					valueAndKeys.add(map.get(key) + " = " + key);
					int len = key.length();
					System.out.println("<color name=\"\">" + key + "</color>\t" + (len==9?"":"\t") + "<!--  -->");
					it.remove();
				}
				
			}
			if (keySet.size() == 0) {
				break;
			}
			it = keySet.iterator();
			i++;
		}
		
		System.out.println(valueAndKeys);
	}

	/**
	 * 查找字符串中子字符串的个数
	 */
	protected static int indexOf(final String sourceStr, final String subStr){
		
		int count = 0;
		int i = 0;
		
		for (;;) {
			i = sourceStr.indexOf(subStr, i);
			
			if (i == -1) {
				break;
			}
			i++;
			count++;
			
		}
		
		return count;
	}
	
	
	static String colorString = "<color name=\"black\">#000000</color>		<!--  -->"
			+"<color name=\"black_50p\">#7f000000</color>	<!--  -->"
			+"<color name=\"black_87p\">#e0000000</color>	<!--  -->"
			+"<color name=\"black_32\">#323232</color>		<!--  -->"
			
			+"<color name=\"green_009688\">#009688</color>		<!--  -->"
			+"<color name=\"green_79c232\">#79c232</color>		<!--  -->"
			
			+"<color name=\"white\">#ffffff</color>		<!--  -->"
			+"<color name=\"white_10p\">#16ffffff</color>	<!--  -->"
			+"<color name=\"white_25p\">#3fffffff</color>	<!--  -->"
			+"<color name=\"white_35p\">#59ffffff</color>	<!--  -->"
			
			+"<color name=\"blue_6593cb\">#6593cb</color>		<!--  -->"
			+"<color name=\"blue_75b1d6\">#75b1d6</color>		<!--  -->"
			
			+"<color name=\"gray_a9\">#a9a9a9</color>		<!--  -->"
			+"<color name=\"gray_aa\">#aaaaaa</color>		<!--  -->"
			+"<color name=\"gray_ad\">#adadad</color>		<!--  -->"
			+"<color name=\"gray_b3\">#b3b3b3</color>		<!--  -->"
			+"<color name=\"gray_b4\">#b4b4b4</color>		<!--  -->"
			+"<color name=\"gray_e6\">#c6c6c6</color>		<!--  -->"
			+"<color name=\"gray_dfdfde\">#dfdfde</color>		<!--  -->"
			+"<color name=\"gray_e3\">#e3e3e3</color>		<!--  -->"
			
			+"<color name=\"white_f0f0f0\">#f0f0f0</color>		<!--  -->"
			+"<color name=\"white_f8\">#f8f8f8</color>		<!--  -->"
			+"<color name=\"red_fecfc8\">#fecfc8</color>		<!--  -->"
			+"<color name=\"red_fed1ca\">#fed1ca</color>		<!--  -->"
			+"<color name=\"white_fe\">#fefefe</color>		<!--  -->"
			+"<color name=\"red_ff644f\">#ff644f</color>		<!--  -->"
			+"<color name=\"yellow_ffc046\">#ffc046</color>		<!--  -->"
			+"<color name=\"yellow_ffff00\">#ffff00</color>		<!--  -->"
			+"<color name=\"transparent\">#00000000</color>	<!--  -->"
			+"<color name=\"green_3c983c\">#3c983c</color>		<!--  -->"
			+"<color name=\"white_50p\">#7fffffff</color>	<!--  -->"
			+"<color name=\"gray_88\">#888888</color>		<!--  -->"
			+"<color name=\"white_60p\">#99ffffff</color>	<!--  -->"
			+"<color name=\"green_9ecb3e\">#9ecb3e</color>		<!--  -->"
			+"<color name=\"white_70p\">#b2ffffff</color>	<!--  -->"
			+"<color name=\"gray_d7\">#d7d7d7</color>		<!--  -->"
			+"<color name=\"gray_d9\">#d9d9d9</color>		<!--  -->"
			+"<color name=\"white_eb\">#ebebeb</color>		<!--  -->"
			+"<color name=\"white_feefef\">#feefef</color>		<!--  -->"
			+"<color name=\"gray_80\">#808080</color>		<!--  -->"
			+"<color name=\"gray_db\">#dbdbdb</color>		<!--  -->"
			+"<color name=\"white_90p\">#e5ffffff</color>	<!--  -->"
			+"<color name=\"white_f9\">#f9f9f9</color>		<!--  -->"
			+"<color name=\"yellow_ffd544\">#ffd544</color>		<!--  -->"
			+"<color name=\"gray_bc\">#bcbcbc</color>		<!--  -->"
			+"<color name=\"red_f2341b\">#f2341b</color>		<!--  -->"
			+"<color name=\"gray_e6\">#e6e6e6</color>		<!--  -->"
			+"<color name=\"red_f24949\">#f24949</color>		<!--  -->"
			+"<color name=\"white_f3\">#f3f3f3</color>		<!--  -->"
			+"<color name=\"black_64\">#646464</color>		<!--  -->"
			+"<color name=\"gray_c8\">#c8c8c8</color>		<!--  -->"
			+"<color name=\"gray_99\">#999999</color>		<!--  -->"
			
			
			+"<color name=\"red\">#ff5a44</color>		<!--  -->"
			+"<color name=\"red_e64d4a\">#e64d4a</color>		<!--  -->"
			+"<color name=\"red_d74d3a\">#d74d3a</color>		<!--  -->"
			;
	
	/**
	 * 使用正则表达式
	 *     匹配int数据 替换为 anroid res文件名称类型
	 * 	  ps: public static final int container_transition_in = 2130968576;
	 *        把 源码中2130968576 替换为 R.layout.container_transition_in
	 */
	
	static void replaceResourceIntToResourceType(String source) {
		putResMap();//加入匹配数据
		
		File sourceFile = new File(source);
		if (sourceFile.getName().equals("R.java")) {
			return;
		}
		File targeFile = null;
		String target = source.replace(sourceFile.getName(),
				sourceFile.getName() + "2");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			targeFile = new File(target);
			fw = new FileWriter(targeFile);
			bw = new BufferedWriter(fw);

			String line = "";
			Pattern pattern = Pattern
					.compile("[\\d]{10}");
			Matcher matcher = null;
			String key = null;
			String value = null;
			while ((line = br.readLine()) != null) {
				if (line.length() < 10) {
					bw.write(line + "\r\n");
					continue;
				}
				matcher = pattern.matcher(line);
				
				if (matcher.find()) {
					key = matcher.group();
					value = resMap.get(key);
					System.out.println("resp1onse : " + key + "   " +  value + " " + source);
					if (value != null) {
						line = line.replace(key, value);
					}
					bw.write(line + "\r\n");
					continue;
				}
				bw.write(line + "\r\n");
				continue;
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
	
	static void putResMap(){
		resMap.put("2130968576","R.anim.container_transition_in");
		resMap.put("2130968577","R.anim.dialog_enter");
		resMap.put("2130968578","R.anim.dialog_enter_anim");
		resMap.put("2130968579","R.anim.dialog_enter_anim_up");
		resMap.put("2130968580","R.anim.dialog_exit");
		resMap.put("2130968581","R.anim.dialog_exit_anim");
		resMap.put("2130968582","R.anim.dialog_exit_anim_up");
		resMap.put("2130968583","R.anim.jump_ac_top20");
		resMap.put("2130968584","R.anim.page_slide_left_in");
		resMap.put("2130968585","R.anim.page_slide_left_out");
		resMap.put("2130968586","R.anim.page_slide_right_in");
		resMap.put("2130968587","R.anim.page_slide_right_out");
		resMap.put("2130968588","R.anim.push_layout_slide_out");
		resMap.put("2130968589","R.anim.scale_inifite");
		resMap.put("2130968590","R.anim.scale_object_animator");
		resMap.put("2130968591","R.anim.scale_view");
		resMap.put("2130968592","R.anim.scale_with_alpha");
		resMap.put("2130968593","R.anim.slide_bottom_top_");
		resMap.put("2130968594","R.anim.slide_down_");
		resMap.put("2130968595","R.anim.slide_down_in");
		resMap.put("2130968596","R.anim.slide_down_out");
		resMap.put("2130968597","R.anim.slide_from_left_gone");
		resMap.put("2130968598","R.anim.slide_from_right_show");
		resMap.put("2130968599","R.anim.slide_in_from_bottom");
		resMap.put("2130968600","R.anim.slide_in_from_top");
		resMap.put("2130968601","R.anim.slide_out_to_bottom");
		resMap.put("2130968602","R.anim.slide_out_to_top");
		resMap.put("2130968603","R.anim.slide_top_bottom_");
		resMap.put("2130968604","R.anim.slide_up_");
		resMap.put("2130968605","R.anim.slide_up_down_");
		resMap.put("2130968606","R.anim.transition_");
		resMap.put("2130968607","R.anim.transition_in");
		resMap.put("2130968608","R.anim.transition_out");
		resMap.put("2130968609","R.anim.translate_object_animator");
		resMap.put("2130968610","R.anim.zoom_in_anim");
		resMap.put("2131361792","R.array.country_code");
		resMap.put("2130771973","R.attr.SwipeBackLayoutStyle");
		resMap.put("2130772002","R.attr.border_color");
		resMap.put("2130772001","R.attr.border_width");
		resMap.put("2130772006","R.attr.centered");
		resMap.put("2130772031","R.attr.ci_animator");
		resMap.put("2130772032","R.attr.ci_drawable");
		resMap.put("2130772029","R.attr.ci_height");
		resMap.put("2130772030","R.attr.ci_margin");
		resMap.put("2130772028","R.attr.ci_width");
		resMap.put("2130772035","R.attr.contentView");
		resMap.put("2130772000","R.attr.corner_radius");
		resMap.put("2130772004","R.attr.default_background");
		resMap.put("2130772033","R.attr.dividerWidth");
		resMap.put("2130771969","R.attr.edge_flag");
		resMap.put("2130771968","R.attr.edge_size");
		resMap.put("2130772010","R.attr.fillColor");
		resMap.put("2130772016","R.attr.gapWidth");
		resMap.put("2130772034","R.attr.headerView");
		resMap.put("2130772037","R.attr.isHeaderParallax");
		resMap.put("2130772015","R.attr.lineWidth");
		resMap.put("2130772003","R.attr.mutate_background");
		resMap.put("2130772005","R.attr.oval");
		resMap.put("2130772011","R.attr.pageColor");
		resMap.put("2130772019","R.attr.pstsDividerColor");
		resMap.put("2130772022","R.attr.pstsDividerPadding");
		resMap.put("2130772017","R.attr.pstsIndicatorColor");
		resMap.put("2130772020","R.attr.pstsIndicatorHeight");
		resMap.put("2130772024","R.attr.pstsScrollOffset");
		resMap.put("2130772026","R.attr.pstsShouldExpand");
		resMap.put("2130772025","R.attr.pstsTabBackground");
		resMap.put("2130772023","R.attr.pstsTabPaddingLeftRight");
		resMap.put("2130772027","R.attr.pstsTextAllCaps");
		resMap.put("2130772018","R.attr.pstsUnderlineColor");
		resMap.put("2130772021","R.attr.pstsUnderlineHeight");
		resMap.put("2130771990","R.attr.ptrAdapterViewBackground");
		resMap.put("2130771986","R.attr.ptrAnimationStyle");
		resMap.put("2130771980","R.attr.ptrDrawable");
		resMap.put("2130771992","R.attr.ptrDrawableBottom");
		resMap.put("2130771982","R.attr.ptrDrawableEnd");
		resMap.put("2130771981","R.attr.ptrDrawableStart");
		resMap.put("2130771991","R.attr.ptrDrawableTop");
		resMap.put("2130771975","R.attr.ptrHeaderBackground");
		resMap.put("2130771977","R.attr.ptrHeaderSubTextColor");
		resMap.put("2130771984","R.attr.ptrHeaderTextAppearance");
		resMap.put("2130771976","R.attr.ptrHeaderTextColor");
		resMap.put("2130771988","R.attr.ptrListViewExtrasEnabled");
		resMap.put("2130771978","R.attr.ptrMode");
		resMap.put("2130771983","R.attr.ptrOverScroll");
		resMap.put("2130771974","R.attr.ptrRefreshableViewBackground");
		resMap.put("2130771989","R.attr.ptrRotateDrawableWhilePulling");
		resMap.put("2130771987","R.attr.ptrScrollingWhileRefreshingEnabled");
		resMap.put("2130771979","R.attr.ptrShowIndicator");
		resMap.put("2130771985","R.attr.ptrSubHeaderTextAppearance");
		resMap.put("2130772012","R.attr.radius");
		resMap.put("2130772007","R.attr.selectedColor");
		resMap.put("2130771972","R.attr.shadow_bottom");
		resMap.put("2130771970","R.attr.shadow_left");
		resMap.put("2130771971","R.attr.shadow_right");
		resMap.put("2130772013","R.attr.snap");
		resMap.put("2130772014","R.attr.strokeColor");
		resMap.put("2130772008","R.attr.strokeWidth");
		resMap.put("2130772009","R.attr.unselectedColor");
		resMap.put("2130771994","R.attr.vpiCirclePageIndicatorStyle");
		resMap.put("2130771995","R.attr.vpiIconPageIndicatorStyle");
		resMap.put("2130771996","R.attr.vpiLinePageIndicatorStyle");
		resMap.put("2130771998","R.attr.vpiTabPageIndicatorStyle");
		resMap.put("2130771997","R.attr.vpiTitlePageIndicatorStyle");
		resMap.put("2130771999","R.attr.vpiUnderlinePageIndicatorStyle");
		resMap.put("2130771993","R.attr.windowSize");
		resMap.put("2130772036","R.attr.zoomView");
		resMap.put("2131558400","R.bool.default_circle_indicator_centered");
		resMap.put("2131558401","R.bool.default_circle_indicator_snap");
		resMap.put("2131558402","R.bool.default_line_indicator_centered");
		resMap.put("2131558403","R.bool.default_title_indicator_selected_bold");
		resMap.put("2131558404","R.bool.default_underline_indicator_fades");
		resMap.put("2131427346","R.color.action_bar_co");
		resMap.put("2131427353","R.color.addto_on_gray");
		resMap.put("2131427354","R.color.addto_red");
		resMap.put("2131427362","R.color.background_co");
		resMap.put("2131427382","R.color.background_tab_pressed");
		resMap.put("2131427380","R.color.bg_grey");
		resMap.put("2131427329","R.color.black");
		resMap.put("2131427385","R.color.black_trans");
		resMap.put("2131427340","R.color.blue");
		resMap.put("2131427341","R.color.blue_dan");
		resMap.put("2131427368","R.color.blue_notice");
		resMap.put("2131427342","R.color.circle_co");
		resMap.put("2131427337","R.color.darker_gray");
		resMap.put("2131427393","R.color.default_circle_indicator_fill_color");
		resMap.put("2131427394","R.color.default_circle_indicator_page_color");
		resMap.put("2131427395","R.color.default_circle_indicator_stroke_color");
		resMap.put("2131427396","R.color.default_line_indicator_selected_color");
		resMap.put("2131427397","R.color.default_line_indicator_unselected_color");
		resMap.put("2131427398","R.color.default_title_indicator_footer_color");
		resMap.put("2131427399","R.color.default_title_indicator_selected_color");
		resMap.put("2131427400","R.color.default_title_indicator_text_color");
		resMap.put("2131427401","R.color.default_underline_indicator_selected_color");
		resMap.put("2131427369","R.color.dis_post_co");
		resMap.put("2131427366","R.color.exp_money_co");
		resMap.put("2131427359","R.color.from_txt_co");
		resMap.put("2131427339","R.color.gray");
		resMap.put("2131427377","R.color.gray_co");
		resMap.put("2131427376","R.color.gray_text");
		resMap.put("2131427332","R.color.green");
		resMap.put("2131427365","R.color.header_bg");
		resMap.put("2131427374","R.color.header_finish");
		resMap.put("2131427378","R.color.hint_color");
		resMap.put("2131427386","R.color.home_black_co");
		resMap.put("2131427348","R.color.home_red");
		resMap.put("2131427347","R.color.home_title_co");
		resMap.put("2131427373","R.color.juhong");
		resMap.put("2131427333","R.color.lighter_gray");
		resMap.put("2131427338","R.color.lighter_gray2");
		resMap.put("2131427334","R.color.lighter_gray_co");
		resMap.put("2131427345","R.color.lighter_green");
		resMap.put("2131427371","R.color.login_bg");
		resMap.put("2131427364","R.color.more_bg");
		resMap.put("2131427372","R.color.possible_result_points");
		resMap.put("2131427360","R.color.public_bg_co");
		resMap.put("2131427361","R.color.public_gray");
		resMap.put("2131427349","R.color.qingse");
		resMap.put("2131427328","R.color.red");
		resMap.put("2131427351","R.color.red_tab_strip");
		resMap.put("2131427357","R.color.result_view");
		resMap.put("2131427375","R.color.room_bg_color");
		resMap.put("2131427381","R.color.room_bg_darker");
		resMap.put("2131427402","R.color.rounded_red_normal");
		resMap.put("2131427403","R.color.rounded_red_pressed");
		resMap.put("2131427404","R.color.search_text_color_selector");
		resMap.put("2131427405","R.color.so_blacktrans15");
		resMap.put("2131427406","R.color.so_blacktrans5");
		resMap.put("2131427407","R.color.so_gray");
		resMap.put("2131427408","R.color.so_white5");
		resMap.put("2131427409","R.color.so_white_border");
		resMap.put("2131427379","R.color.text_list_title");
		resMap.put("2131427367","R.color.top_left_co");
		resMap.put("2131427363","R.color.track_right");
		resMap.put("2131427352","R.color.tran_purple");
		resMap.put("2131427343","R.color.transparent");
		resMap.put("2131427410","R.color.turn_blue3");
		resMap.put("2131427411","R.color.turn_grey");
		resMap.put("2131427412","R.color.turn_trans_grey_bl");
		resMap.put("2131427413","R.color.turn_trans_grey_br");
		resMap.put("2131427358","R.color.viewfinder_frame");
		resMap.put("2131427355","R.color.viewfinder_laser");
		resMap.put("2131427356","R.color.viewfinder_mask");
		resMap.put("2131427383","R.color.vpi__background_holo_dark");
		resMap.put("2131427384","R.color.vpi__background_holo_light");
		resMap.put("2131427389","R.color.vpi__bright_foreground_disabled_holo_dark");
		resMap.put("2131427390","R.color.vpi__bright_foreground_disabled_holo_light");
		resMap.put("2131427387","R.color.vpi__bright_foreground_holo_dark");
		resMap.put("2131427388","R.color.vpi__bright_foreground_holo_light");
		resMap.put("2131427391","R.color.vpi__bright_foreground_inverse_holo_dark");
		resMap.put("2131427392","R.color.vpi__bright_foreground_inverse_holo_light");
		resMap.put("2131427330","R.color.white");
		resMap.put("2131427350","R.color.white_DD");
		resMap.put("2131427335","R.color.white_tra");
		resMap.put("2131427336","R.color.white_tra2");
		resMap.put("2131427344","R.color.window_trans_bg");
		resMap.put("2131427331","R.color.yellow");
		resMap.put("2131427370","R.color.yellow_pressed");
		resMap.put("2131230739","R.dimen.bottom_bar_height");
		resMap.put("2131230740","R.dimen.default_circle_indicator_radius");
		resMap.put("2131230741","R.dimen.default_circle_indicator_stroke_width");
		resMap.put("2131230743","R.dimen.default_line_indicator_gap_width");
		resMap.put("2131230742","R.dimen.default_line_indicator_line_width");
		resMap.put("2131230744","R.dimen.default_line_indicator_stroke_width");
		resMap.put("2131230745","R.dimen.default_title_indicator_clip_padding");
		resMap.put("2131230747","R.dimen.default_title_indicator_footer_indicator_height");
		resMap.put("2131230748","R.dimen.default_title_indicator_footer_indicator_underline_padding");
		resMap.put("2131230746","R.dimen.default_title_indicator_footer_line_height");
		resMap.put("2131230749","R.dimen.default_title_indicator_footer_padding");
		resMap.put("2131230750","R.dimen.default_title_indicator_text_size");
		resMap.put("2131230751","R.dimen.default_title_indicator_title_padding");
		resMap.put("2131230752","R.dimen.default_title_indicator_top_padding");
		resMap.put("2131230731","R.dimen.float_view_height");
		resMap.put("2131230732","R.dimen.float_view_height2");
		resMap.put("2131230733","R.dimen.float_view_margin");
		resMap.put("2131230734","R.dimen.float_view_margin2");
		resMap.put("2131230736","R.dimen.float_view_margin2_landspace");
		resMap.put("2131230735","R.dimen.float_view_margin_landspace");
		resMap.put("2131230723","R.dimen.header_footer_left_right_padding");
		resMap.put("2131230724","R.dimen.header_footer_top_bottom_padding");
		resMap.put("2131230737","R.dimen.home_child_edit_width");
		resMap.put("2131230721","R.dimen.indicator_corner_radius");
		resMap.put("2131230722","R.dimen.indicator_internal_padding");
		resMap.put("2131230720","R.dimen.indicator_right_padding");
		resMap.put("2131230728","R.dimen.my_game_margin");
		resMap.put("2131230729","R.dimen.quit_screen_bar_margin");
		resMap.put("2131230730","R.dimen.quit_screen_bar_margin2");
		resMap.put("2131230727","R.dimen.rec_game_codius");
		resMap.put("2131230738","R.dimen.top_bar_height");
		resMap.put("2131230725","R.dimen.window_count_margin");
		resMap.put("2131230726","R.dimen.window_count_margin2");
		resMap.put("2130837504","R.drawable.actionbar_btn_bg_black");
		resMap.put("2130837505","R.drawable.actionbar_btn_bg_light");
		resMap.put("2130837506","R.drawable.anim_loading");
		resMap.put("2130837507","R.drawable.bar_btn_back");
		resMap.put("2130837508","R.drawable.bar_btn_backward");
		resMap.put("2130837509","R.drawable.bar_btn_backward_2");
		resMap.put("2130837510","R.drawable.bar_btn_forward");
		resMap.put("2130837511","R.drawable.bar_btn_menu");
		resMap.put("2130837512","R.drawable.bar_btn_more");
		resMap.put("2130837513","R.drawable.bar_btn_share");
		resMap.put("2130837514","R.drawable.btn_collect_more");
		resMap.put("2130837515","R.drawable.btn_collect_small2");
		resMap.put("2130837516","R.drawable.btn_collected_more");
		resMap.put("2130837517","R.drawable.btn_collected_small2");
		resMap.put("2130837518","R.drawable.btn_more_v");
		resMap.put("2130837519","R.drawable.btn_red_selector");
		resMap.put("2130837652","R.drawable.chat_float_pressed");
		resMap.put("2130837520","R.drawable.click_cancel_change");
		resMap.put("2130837521","R.drawable.click_changecolor");
		resMap.put("2130837522","R.drawable.default_ptr_flip");
		resMap.put("2130837523","R.drawable.default_ptr_rotate");
		resMap.put("2130837524","R.drawable.default_video_poster");
		resMap.put("2130837525","R.drawable.dialog_bottom_holo_dark");
		resMap.put("2130837526","R.drawable.download_icon_notifications");
		resMap.put("2130837527","R.drawable.float_btn_fullscreen_off");
		resMap.put("2130837528","R.drawable.float_btn_fullscreen_on");
		resMap.put("2130837529","R.drawable.float_btn_refresh");
		resMap.put("2130837530","R.drawable.guide_icon_1");
		resMap.put("2130837531","R.drawable.guide_icon_tapit");
		resMap.put("2130837532","R.drawable.guide_shishouqi");
		resMap.put("2130837533","R.drawable.ic_action_search");
		resMap.put("2130837534","R.drawable.ic_launcher");
		resMap.put("2130837535","R.drawable.icon_alert_notifications");
		resMap.put("2130837536","R.drawable.icon_app_default");
		resMap.put("2130837537","R.drawable.icon_cate_game");
		resMap.put("2130837538","R.drawable.icon_detail_arrow");
		resMap.put("2130837539","R.drawable.icon_followed_2");
		resMap.put("2130837540","R.drawable.icon_logo_openning");
		resMap.put("2130837541","R.drawable.icon_nav_logo");
		resMap.put("2130837542","R.drawable.icon_next_arrow");
		resMap.put("2130837543","R.drawable.icon_notification_close");
		resMap.put("2130837544","R.drawable.icon_portrait_n_150");
		resMap.put("2130837545","R.drawable.icon_successful");
		resMap.put("2130837546","R.drawable.indicator_arrow");
		resMap.put("2130837547","R.drawable.indicator_bg_bottom");
		resMap.put("2130837548","R.drawable.indicator_bg_top");
		resMap.put("2130837549","R.drawable.list_blue_selector_");
		resMap.put("2130837550","R.drawable.list_voice_selector_");
		resMap.put("2130837551","R.drawable.logo_corner");
		resMap.put("2130837552","R.drawable.menu_icon_about");
		resMap.put("2130837553","R.drawable.menu_icon_addto");
		resMap.put("2130837554","R.drawable.menu_icon_addto_on");
		resMap.put("2130837555","R.drawable.menu_icon_favorites");
		resMap.put("2130837556","R.drawable.menu_icon_fullscreen_off");
		resMap.put("2130837557","R.drawable.menu_icon_fullscreen_on");
		resMap.put("2130837558","R.drawable.menu_icon_recent");
		resMap.put("2130837559","R.drawable.menu_icon_refresh");
		resMap.put("2130837560","R.drawable.menu_icon_refresh_stop");
		resMap.put("2130837561","R.drawable.menu_icon_screenshot");
		resMap.put("2130837562","R.drawable.menu_icon_shareon");
		resMap.put("2130837563","R.drawable.menu_icon_update");
		resMap.put("2130837564","R.drawable.my_progress_indeterminate");
		resMap.put("2130837565","R.drawable.mygame_icon_nogames");
		resMap.put("2130837566","R.drawable.nav_icon_search");
		resMap.put("2130837567","R.drawable.no_list_selector_");
		resMap.put("2130837568","R.drawable.pr_00000");
		resMap.put("2130837569","R.drawable.pr_00001");
		resMap.put("2130837570","R.drawable.pr_00002");
		resMap.put("2130837571","R.drawable.pr_00003");
		resMap.put("2130837572","R.drawable.pr_00004");
		resMap.put("2130837573","R.drawable.pr_00005");
		resMap.put("2130837574","R.drawable.pr_00006");
		resMap.put("2130837575","R.drawable.pr_00007");
		resMap.put("2130837576","R.drawable.pr_00008");
		resMap.put("2130837577","R.drawable.pr_00009");
		resMap.put("2130837578","R.drawable.pr_00010");
		resMap.put("2130837579","R.drawable.pr_00011");
		resMap.put("2130837580","R.drawable.pr_00012");
		resMap.put("2130837581","R.drawable.pr_00013");
		resMap.put("2130837582","R.drawable.pr_00014");
		resMap.put("2130837583","R.drawable.pr_00015");
		resMap.put("2130837584","R.drawable.pr_00016");
		resMap.put("2130837585","R.drawable.pr_00017");
		resMap.put("2130837586","R.drawable.pr_00018");
		resMap.put("2130837587","R.drawable.pr_00019");
		resMap.put("2130837588","R.drawable.pr_00020");
		resMap.put("2130837589","R.drawable.pr_00021");
		resMap.put("2130837590","R.drawable.pr_00022");
		resMap.put("2130837591","R.drawable.pr_00023");
		resMap.put("2130837592","R.drawable.pr_00024");
		resMap.put("2130837593","R.drawable.pr_00025");
		resMap.put("2130837594","R.drawable.pr_00026");
		resMap.put("2130837595","R.drawable.pr_00027");
		resMap.put("2130837596","R.drawable.pr_00028");
		resMap.put("2130837597","R.drawable.pr_00029");
		resMap.put("2130837598","R.drawable.pr_00030");
		resMap.put("2130837599","R.drawable.pr_00031");
		resMap.put("2130837600","R.drawable.pr_00032");
		resMap.put("2130837601","R.drawable.pr_00033");
		resMap.put("2130837602","R.drawable.pr_00034");
		resMap.put("2130837603","R.drawable.pr_00035");
		resMap.put("2130837604","R.drawable.pr_00036");
		resMap.put("2130837605","R.drawable.pr_00037");
		resMap.put("2130837606","R.drawable.pr_00038");
		resMap.put("2130837607","R.drawable.pr_00039");
		resMap.put("2130837608","R.drawable.pr_00040");
		resMap.put("2130837609","R.drawable.pr_00041");
		resMap.put("2130837610","R.drawable.pr_00042");
		resMap.put("2130837611","R.drawable.pr_00043");
		resMap.put("2130837612","R.drawable.pr_00044");
		resMap.put("2130837613","R.drawable.pr_00045");
		resMap.put("2130837614","R.drawable.pr_00046");
		resMap.put("2130837615","R.drawable.pr_00047");
		resMap.put("2130837616","R.drawable.pr_00048");
		resMap.put("2130837617","R.drawable.pr_00049");
		resMap.put("2130837618","R.drawable.pr_00050");
		resMap.put("2130837619","R.drawable.pr_00051");
		resMap.put("2130837620","R.drawable.progress_horizontal");
		resMap.put("2130837621","R.drawable.recommended_bg_bubble");
		resMap.put("2130837622","R.drawable.search_clear_normal");
		resMap.put("2130837623","R.drawable.settings_about_logo");
		resMap.put("2130837624","R.drawable.shadow_bottom");
		resMap.put("2130837625","R.drawable.shadow_left");
		resMap.put("2130837626","R.drawable.shadow_right");
		resMap.put("2130837627","R.drawable.share_btn_mail_large");
		resMap.put("2130837628","R.drawable.share_btn_message_large");
		resMap.put("2130837629","R.drawable.share_btn_qq_large");
		resMap.put("2130837630","R.drawable.share_btn_qzone_large");
		resMap.put("2130837631","R.drawable.share_btn_save_large");
		resMap.put("2130837632","R.drawable.share_btn_weibo_large");
		resMap.put("2130837633","R.drawable.share_btn_weixin2_large");
		resMap.put("2130837634","R.drawable.share_btn_weixin_large");
		resMap.put("2130837635","R.drawable.share_icon_clip");
		resMap.put("2130837636","R.drawable.share_icon_logo");
		resMap.put("2130837637","R.drawable.share_icon_mask");
		resMap.put("2130837638","R.drawable.sign_assure_change");
		resMap.put("2130837639","R.drawable.sign_cancel_grey");
		resMap.put("2130837640","R.drawable.sign_cancel_white");
		resMap.put("2130837641","R.drawable.sign_right_grey");
		resMap.put("2130837642","R.drawable.sign_right_white");
		resMap.put("2130837643","R.drawable.spinner_76_inner_holo");
		resMap.put("2130837644","R.drawable.ssq_logo_144");
		resMap.put("2130837645","R.drawable.theme_icon_search_normal");
		resMap.put("2130837646","R.drawable.voice_btn_float");
		resMap.put("2130837647","R.drawable.voice_btn_float_pressed");
		resMap.put("2130837648","R.drawable.voice_icon_float_normal");
		resMap.put("2130837649","R.drawable.web_progress_horizontal");
		resMap.put("2130837650","R.drawable.wheel_bg");
		resMap.put("2130837651","R.drawable.wheel_val");
		resMap.put("2131296461","R.id.JavaScriptPromptInput");
		resMap.put("2131296460","R.id.JavaScriptPromptMessage");
		resMap.put("2131296259","R.id.all");
		resMap.put("2131296305","R.id.app_icon");
		resMap.put("2131296403","R.id.banners_img");
		resMap.put("2131296402","R.id.banners_viewpager");
		resMap.put("2131296301","R.id.below_header");
		resMap.put("2131296263","R.id.both");
		resMap.put("2131296260","R.id.bottom");
		resMap.put("2131296413","R.id.bottom_view");
		resMap.put("2131296295","R.id.btnLayout");
		resMap.put("2131296342","R.id.btn_add");
		resMap.put("2131296373","R.id.btn_open");
		resMap.put("2131296380","R.id.btn_right_layout");
		resMap.put("2131296398","R.id.cancelBtn");
		resMap.put("2131296368","R.id.cancle");
		resMap.put("2131296348","R.id.cb_confirm");
		resMap.put("2131296440","R.id.centerLayout");
		resMap.put("2131296404","R.id.circleIndicator");
		resMap.put("2131296335","R.id.container_layout");
		resMap.put("2131296462","R.id.content");
		resMap.put("2131296303","R.id.content_pager");
		resMap.put("2131296292","R.id.dingzai_email");
		resMap.put("2131296264","R.id.disabled");
		resMap.put("2131296400","R.id.edt_search");
		resMap.put("2131296464","R.id.fl_inner");
		resMap.put("2131296270","R.id.flip");
		resMap.put("2131296367","R.id.grid_game");
		resMap.put("2131296405","R.id.grid_others");
		resMap.put("2131296362","R.id.grid_share");
		resMap.put("2131296256","R.id.gridview");
		resMap.put("2131296438","R.id.headerLayout");
		resMap.put("2131296393","R.id.homeBtn");
		resMap.put("2131296317","R.id.iamfather");
		resMap.put("2131296456","R.id.icon");
		resMap.put("2131296323","R.id.icon_openning");
		resMap.put("2131296383","R.id.imgAppIcon");
		resMap.put("2131296441","R.id.imgArrow");
		resMap.put("2131296452","R.id.imgIcon");
		resMap.put("2131296439","R.id.imgPortrait");
		resMap.put("2131296379","R.id.inclue_edit");
		resMap.put("2131296296","R.id.iv_back_icon");
		resMap.put("2131296388","R.id.iv_backward");
		resMap.put("2131296446","R.id.iv_cell");
		resMap.put("2131296314","R.id.iv_col");
		resMap.put("2131296356","R.id.iv_collect");
		resMap.put("2131296401","R.id.iv_del");
		resMap.put("2131296384","R.id.iv_floating");
		resMap.put("2131296455","R.id.iv_followed");
		resMap.put("2131296392","R.id.iv_forward");
		resMap.put("2131296360","R.id.iv_full_screen");
		resMap.put("2131296274","R.id.iv_game_icon");
		resMap.put("2131296448","R.id.iv_go");
		resMap.put("2131296311","R.id.iv_guide_cover");
		resMap.put("2131296447","R.id.iv_icon");
		resMap.put("2131296333","R.id.iv_loading_icon");
		resMap.put("2131296337","R.id.iv_logo");
		resMap.put("2131296459","R.id.iv_photo");
		resMap.put("2131296358","R.id.iv_refresh");
		resMap.put("2131296308","R.id.iv_remove");
		resMap.put("2131296399","R.id.iv_search2");
		resMap.put("2131296390","R.id.iv_share");
		resMap.put("2131296458","R.id.iv_short_ad");
		resMap.put("2131296281","R.id.iv_subject_bg");
		resMap.put("2131296315","R.id.iv_tapit");
		resMap.put("2131296300","R.id.iv_unupgrade");
		resMap.put("2131296434","R.id.iv_url_icon");
		resMap.put("2131296385","R.id.iv_voice_float");
		resMap.put("2131296278","R.id.iv_xie");
		resMap.put("2131296432","R.id.jiangyou");
		resMap.put("2131296261","R.id.left");
		resMap.put("2131296345","R.id.line");
		resMap.put("2131296283","R.id.line1");
		resMap.put("2131296451","R.id.line_view");
		resMap.put("2131296310","R.id.listview");
		resMap.put("2131296273","R.id.ll_back_layout");
		resMap.put("2131296408","R.id.ll_bottom_layout");
		resMap.put("2131296350","R.id.ll_cancel");
		resMap.put("2131296355","R.id.ll_col");
		resMap.put("2131296313","R.id.ll_collect");
		resMap.put("2131296294","R.id.ll_container_layout");
		resMap.put("2131296359","R.id.ll_full_screen");
		resMap.put("2131296406","R.id.ll_game_like_layout");
		resMap.put("2131296425","R.id.ll_gameraiders_layout");
		resMap.put("2131296433","R.id.ll_icon_layout");
		resMap.put("2131296346","R.id.ll_layout");
		resMap.put("2131296286","R.id.ll_loading_layout");
		resMap.put("2131296442","R.id.ll_name");
		resMap.put("2131296318","R.id.ll_no_data_layout");
		resMap.put("2131296376","R.id.ll_parent");
		resMap.put("2131296422","R.id.ll_person_layout");
		resMap.put("2131296366","R.id.ll_quit");
		resMap.put("2131296365","R.id.ll_quit_clear");
		resMap.put("2131296371","R.id.ll_randomapp_layout");
		resMap.put("2131296453","R.id.ll_rank");
		resMap.put("2131296416","R.id.ll_rec_app_layout");
		resMap.put("2131296419","R.id.ll_rec_game_layout");
		resMap.put("2131296357","R.id.ll_refresh");
		resMap.put("2131296414","R.id.ll_search_keywords_layout");
		resMap.put("2131296361","R.id.ll_share");
		resMap.put("2131296457","R.id.ll_short_ad");
		resMap.put("2131296351","R.id.ll_sure");
		resMap.put("2131296354","R.id.ll_top");
		resMap.put("2131296428","R.id.ll_webpage_layout");
		resMap.put("2131296331","R.id.loading_container");
		resMap.put("2131296339","R.id.logo_randomapp");
		resMap.put("2131296276","R.id.lv_favorite");
		resMap.put("2131296407","R.id.lv_game_covers");
		resMap.put("2131296327","R.id.mListView");
		resMap.put("2131296299","R.id.mMoreBtn");
		resMap.put("2131296410","R.id.mSearchBgView");
		resMap.put("2131296412","R.id.mSearchEditText");
		resMap.put("2131296284","R.id.mTrackListView");
		resMap.put("2131296320","R.id.mWebViewRF");
		resMap.put("2131296265","R.id.manualOnly");
		resMap.put("2131296394","R.id.menuBtn");
		resMap.put("2131296391","R.id.nextBtn");
		resMap.put("2131296431","R.id.nothing_haha");
		resMap.put("2131296369","R.id.other_grid");
		resMap.put("2131296293","R.id.parent");
		resMap.put("2131296375","R.id.password_edit");
		resMap.put("2131296424","R.id.person_list");
		resMap.put("2131296387","R.id.previousBtn");
		resMap.put("2131296436","R.id.progress");
		resMap.put("2131296470","R.id.progress_indicator");
		resMap.put("2131296364","R.id.progressbar_default");
		resMap.put("2131296352","R.id.progressbar_text");
		resMap.put("2131296266","R.id.pullDownFromTop");
		resMap.put("2131296267","R.id.pullFromEnd");
		resMap.put("2131296268","R.id.pullFromStart");
		resMap.put("2131296269","R.id.pullUpFromBottom");
		resMap.put("2131296465","R.id.pull_to_refresh_image");
		resMap.put("2131296466","R.id.pull_to_refresh_progress");
		resMap.put("2131296468","R.id.pull_to_refresh_sub_text");
		resMap.put("2131296467","R.id.pull_to_refresh_text");
		resMap.put("2131296427","R.id.raiders_game_list");
		resMap.put("2131296418","R.id.rec_app_list");
		resMap.put("2131296421","R.id.rec_game_list");
		resMap.put("2131296262","R.id.right");
		resMap.put("2131296332","R.id.rl_bottom_bar");
		resMap.put("2131296312","R.id.rl_circle");
		resMap.put("2131296353","R.id.rl_col_all");
		resMap.put("2131296329","R.id.rl_container_layout");
		resMap.put("2131296302","R.id.rl_contaner");
		resMap.put("2131296386","R.id.rl_floatmenu_layout");
		resMap.put("2131296279","R.id.rl_header");
		resMap.put("2131296449","R.id.rl_layout");
		resMap.put("2131296437","R.id.rl_memebers_list_layout");
		resMap.put("2131296309","R.id.rl_menu");
		resMap.put("2131296336","R.id.rl_menu_layout");
		resMap.put("2131296285","R.id.rl_nothing");
		resMap.put("2131296272","R.id.rl_parent");
		resMap.put("2131296321","R.id.rl_picture_border");
		resMap.put("2131296322","R.id.rl_picture_imgview");
		resMap.put("2131296306","R.id.rl_push_info_layout");
		resMap.put("2131296304","R.id.rl_push_layout");
		resMap.put("2131296324","R.id.rl_rec_banners");
		resMap.put("2131296298","R.id.rl_right_layout");
		resMap.put("2131296397","R.id.rl_search_bar");
		resMap.put("2131296411","R.id.rl_search_bar2");
		resMap.put("2131296382","R.id.rl_search_layout");
		resMap.put("2131296328","R.id.rl_top");
		resMap.put("2131296334","R.id.rl_top_bar");
		resMap.put("2131296287","R.id.rl_top_line");
		resMap.put("2131296271","R.id.rotate");
		resMap.put("2131296415","R.id.scroll_list");
		resMap.put("2131296280","R.id.scroll_view");
		resMap.put("2131296258","R.id.scrollview");
		resMap.put("2131296409","R.id.search_list");
		resMap.put("2131296291","R.id.send_feedback_to");
		resMap.put("2131296389","R.id.shareBtn");
		resMap.put("2131296372","R.id.slot_randomapp");
		resMap.put("2131296469","R.id.swipe");
		resMap.put("2131296319","R.id.swipe_container");
		resMap.put("2131296297","R.id.tabs");
		resMap.put("2131296363","R.id.third");
		resMap.put("2131296463","R.id.time");
		resMap.put("2131296347","R.id.title");
		resMap.put("2131296326","R.id.track_line");
		resMap.put("2131296445","R.id.tvLikes");
		resMap.put("2131296443","R.id.tvName");
		resMap.put("2131296377","R.id.tvSubTitle");
		resMap.put("2131296378","R.id.tvTitle");
		resMap.put("2131296381","R.id.tv_confirm");
		resMap.put("2131296282","R.id.tv_content");
		resMap.put("2131296444","R.id.tv_des");
		resMap.put("2131296316","R.id.tv_desc");
		resMap.put("2131296340","R.id.tv_first");
		resMap.put("2131296426","R.id.tv_game_raiders");
		resMap.put("2131296435","R.id.tv_header_title");
		resMap.put("2131296325","R.id.tv_hot_rec");
		resMap.put("2131296450","R.id.tv_name");
		resMap.put("2131296423","R.id.tv_person_title");
		resMap.put("2131296307","R.id.tv_push_title");
		resMap.put("2131296338","R.id.tv_random_app");
		resMap.put("2131296370","R.id.tv_random_p");
		resMap.put("2131296454","R.id.tv_rank");
		resMap.put("2131296417","R.id.tv_rec_app_title");
		resMap.put("2131296420","R.id.tv_rec_game_title");
		resMap.put("2131296341","R.id.tv_second");
		resMap.put("2131296277","R.id.tv_see_more");
		resMap.put("2131296396","R.id.tv_tab_count");
		resMap.put("2131296344","R.id.tv_title");
		resMap.put("2131296275","R.id.tv_title_view");
		resMap.put("2131296429","R.id.tv_webpage");
		resMap.put("2131296374","R.id.username_edit");
		resMap.put("2131296290","R.id.version");
		resMap.put("2131296288","R.id.versionLayout");
		resMap.put("2131296349","R.id.view");
		resMap.put("2131296343","R.id.view_bg_layout");
		resMap.put("2131296289","R.id.waddr");
		resMap.put("2131296430","R.id.webpage_list");
		resMap.put("2131296257","R.id.webview");
		resMap.put("2131296330","R.id.webview_container");
		resMap.put("2131296395","R.id.windowBtn");
		resMap.put("2131034112","R.interpolator.decelerate_cubic");
		resMap.put("2131034113","R.interpolator.decelerate_quint");
		resMap.put("2130903040","R.layout.ac_more_user_favorities");
		resMap.put("2130903041","R.layout.ac_share_game");
		resMap.put("2130903042","R.layout.ac_shortcut_group");
		resMap.put("2130903043","R.layout.ac_subject_info");
		resMap.put("2130903044","R.layout.activity_about");
		resMap.put("2130903045","R.layout.activity_discover_list");
		resMap.put("2130903046","R.layout.activity_discover_manager");
		resMap.put("2130903047","R.layout.activity_game");
		resMap.put("2130903048","R.layout.activity_game_manager");
		resMap.put("2130903049","R.layout.activity_main");
		resMap.put("2130903050","R.layout.activity_refresh_test");
		resMap.put("2130903051","R.layout.activity_share_picture");
		resMap.put("2130903052","R.layout.activity_startup");
		resMap.put("2130903053","R.layout.activity_subject_list");
		resMap.put("2130903054","R.layout.activity_user_favorite");
		resMap.put("2130903055","R.layout.activity_webview");
		resMap.put("2130903056","R.layout.activity_webview_container");
		resMap.put("2130903057","R.layout.dialog_add_random_app");
		resMap.put("2130903058","R.layout.dialog_clear_common");
		resMap.put("2130903059","R.layout.dialog_col_success");
		resMap.put("2130903060","R.layout.dialog_common");
		resMap.put("2130903061","R.layout.dialog_game_feature");
		resMap.put("2130903062","R.layout.dialog_guide_one");
		resMap.put("2130903063","R.layout.dialog_loading");
		resMap.put("2130903064","R.layout.dialog_quit_web_common");
		resMap.put("2130903065","R.layout.dialog_quite_common");
		resMap.put("2130903066","R.layout.dialog_rec_game");
		resMap.put("2130903067","R.layout.dialog_share");
		resMap.put("2130903068","R.layout.dialog_show_random_app");
		resMap.put("2130903069","R.layout.dialog_small_loading");
		resMap.put("2130903070","R.layout.dialog_update");
		resMap.put("2130903071","R.layout.http_authentication_dialog");
		resMap.put("2130903072","R.layout.include_back_header");
		resMap.put("2130903073","R.layout.include_clear_history_layout");
		resMap.put("2130903074","R.layout.include_floatview_app_icon");
		resMap.put("2130903075","R.layout.include_floatview_layout");
		resMap.put("2130903076","R.layout.include_floatview_menu_layout");
		resMap.put("2130903077","R.layout.include_footer_layout");
		resMap.put("2130903078","R.layout.include_rec_banners_layout");
		resMap.put("2130903079","R.layout.include_search_layout");
		resMap.put("2130903080","R.layout.include_show_nothing");
		resMap.put("2130903081","R.layout.include_webview_header");
		resMap.put("2130903082","R.layout.item_categorys");
		resMap.put("2130903083","R.layout.item_col_content");
		resMap.put("2130903084","R.layout.item_guess_game");
		resMap.put("2130903085","R.layout.item_list_game_search");
		resMap.put("2130903086","R.layout.item_list_history");
		resMap.put("2130903087","R.layout.item_list_search");
		resMap.put("2130903088","R.layout.item_menu");
		resMap.put("2130903089","R.layout.item_random_app");
		resMap.put("2130903090","R.layout.item_recommend_game");
		resMap.put("2130903091","R.layout.item_share");
		resMap.put("2130903092","R.layout.item_short_ad");
		resMap.put("2130903093","R.layout.item_single_smallphoto");
		resMap.put("2130903094","R.layout.item_user_favorities");
		resMap.put("2130903095","R.layout.javascript_prompt_dialog");
		resMap.put("2130903096","R.layout.notification");
		resMap.put("2130903097","R.layout.pull_to_refresh_header_horizontal");
		resMap.put("2130903098","R.layout.pull_to_refresh_header_vertical");
		resMap.put("2130903099","R.layout.swipeback_layout");
		resMap.put("2130903100","R.layout.video_loading_progress");
		resMap.put("2131099742","R.string.ApplicationNameUrl");
		resMap.put("2131099767","R.string.Commons_Cancel");
		resMap.put("2131099777","R.string.Commons_ClearCache");
		resMap.put("2131099779","R.string.Commons_ClearCookies");
		resMap.put("2131099775","R.string.Commons_ClearFormData");
		resMap.put("2131099773","R.string.Commons_ClearHistory");
		resMap.put("2131099778","R.string.Commons_ClearingCache");
		resMap.put("2131099780","R.string.Commons_ClearingCookies");
		resMap.put("2131099776","R.string.Commons_ClearingFormData");
		resMap.put("2131099774","R.string.Commons_ClearingHistory");
		resMap.put("2131099784","R.string.Commons_Close");
		resMap.put("2131099785","R.string.Commons_Continue");
		resMap.put("2131099792","R.string.Commons_HistoryBookmarksExportSDCardConfirmation");
		resMap.put("2131099794","R.string.Commons_HistoryBookmarksExportSDCardDoneMessage");
		resMap.put("2131099793","R.string.Commons_HistoryBookmarksExportSDCardDoneTitle");
		resMap.put("2131099795","R.string.Commons_HistoryBookmarksExportSDCardFailedTitle");
		resMap.put("2131099796","R.string.Commons_HistoryBookmarksFailedMessage");
		resMap.put("2131099797","R.string.Commons_HistoryBookmarksImportSDCardFailedTitle");
		resMap.put("2131099772","R.string.Commons_JavaScriptDialog");
		resMap.put("2131099770","R.string.Commons_No");
		resMap.put("2131099771","R.string.Commons_NoUndoMessage");
		resMap.put("2131099766","R.string.Commons_Ok");
		resMap.put("2131099768","R.string.Commons_PleaseWait");
		resMap.put("2131099809","R.string.Commons_Proceed");
		resMap.put("2131099782","R.string.Commons_SDCardErrorNoSDMsg");
		resMap.put("2131099783","R.string.Commons_SDCardErrorSDUnavailable");
		resMap.put("2131099781","R.string.Commons_SDCardErrorTitle");
		resMap.put("2131099790","R.string.Commons_SslExpired");
		resMap.put("2131099789","R.string.Commons_SslIDMismatch");
		resMap.put("2131099791","R.string.Commons_SslNotYetValid");
		resMap.put("2131099788","R.string.Commons_SslUntrusted");
		resMap.put("2131099786","R.string.Commons_SslWarning");
		resMap.put("2131099787","R.string.Commons_SslWarningsHeader");
		resMap.put("2131099769","R.string.Commons_Yes");
		resMap.put("2131099745","R.string.Constants_SearchUrlWikipedia");
		resMap.put("2131099746","R.string.DATE_FORMAT_ISO8601");
		resMap.put("2131099804","R.string.DownloadListActivity_Aborted");
		resMap.put("2131099803","R.string.DownloadListActivity_Empty");
		resMap.put("2131099805","R.string.DownloadListActivity_Finished");
		resMap.put("2131099802","R.string.DownloadListActivity_Title");
		resMap.put("2131099801","R.string.DownloadNotification_DownloadCanceled");
		resMap.put("2131099800","R.string.DownloadNotification_DownloadComplete");
		resMap.put("2131099799","R.string.DownloadNotification_DownloadInProgress");
		resMap.put("2131099798","R.string.DownloadNotification_DownloadStart");
		resMap.put("2131099806","R.string.HttpAuthenticationDialog_DialogTitle");
		resMap.put("2131099808","R.string.HttpAuthenticationDialog_Password");
		resMap.put("2131099807","R.string.HttpAuthenticationDialog_UserName");
		resMap.put("2131099750","R.string.Main_DownloadErrorMsg");
		resMap.put("2131099749","R.string.Main_DownloadFinishedMsg");
		resMap.put("2131099748","R.string.Main_DownloadStartedMsg");
		resMap.put("2131099747","R.string.Main_FileChooserPrompt");
		resMap.put("2131099751","R.string.Main_ShareChooserTitle");
		resMap.put("2131099753","R.string.Main_VndErrorMessage");
		resMap.put("2131099752","R.string.Main_VndErrorTitle");
		resMap.put("2131099756","R.string.StartPage_Bookmarks");
		resMap.put("2131099757","R.string.StartPage_History");
		resMap.put("2131099758","R.string.StartPage_Search");
		resMap.put("2131099759","R.string.StartPage_SearchButton");
		resMap.put("2131099755","R.string.StartPage_Welcome");
		resMap.put("2131099893","R.string.add_random_app_to_home");
		resMap.put("2131099892","R.string.app_");
		resMap.put("2131099648","R.string.app_name");
		resMap.put("2131099901","R.string.app_open");
		resMap.put("2131099733","R.string.baidutieba");
		resMap.put("2131099688","R.string.baidutieba_client_inavailable");
		resMap.put("2131099723","R.string.bluetooth");
		resMap.put("2131099659","R.string.cancel");
		resMap.put("2131099761","R.string.choose_finish");
		resMap.put("2131099760","R.string.choose_game");
		resMap.put("2131099832","R.string.clear_all");
		resMap.put("2131099820","R.string.clear_history");
		resMap.put("2131099896","R.string.col_succeed");
		resMap.put("2131099813","R.string.confirm");
		resMap.put("2131099880","R.string.d_");
		resMap.put("2131099883","R.string.d_s");
		resMap.put("2131099902","R.string.delete_favorities");
		resMap.put("2131099830","R.string.delete_root_res");
		resMap.put("2131099704","R.string.douban");
		resMap.put("2131099714","R.string.dropbox");
		resMap.put("2131099699","R.string.email");
		resMap.put("2131099706","R.string.evernote");
		resMap.put("2131099876","R.string.exit_sure");
		resMap.put("2131099695","R.string.facebook");
		resMap.put("2131099732","R.string.facebookmessenger");
		resMap.put("2131099900","R.string.find_a_app");
		resMap.put("2131099673","R.string.finish");
		resMap.put("2131099712","R.string.flickr");
		resMap.put("2131099709","R.string.foursquare");
		resMap.put("2131099815","R.string.fuzhi_link");
		resMap.put("2131099891","R.string.game_");
		resMap.put("2131099853","R.string.game_add");
		resMap.put("2131099852","R.string.game_added");
		resMap.put("2131099890","R.string.get_luck");
		resMap.put("2131099817","R.string.go_app_center");
		resMap.put("2131099679","R.string.google_plus_client_inavailable");
		resMap.put("2131099708","R.string.googleplus");
		resMap.put("2131099898","R.string.guess_you_love");
		resMap.put("2131099827","R.string.guide_add_to_home");
		resMap.put("2131099825","R.string.guide_one_content1");
		resMap.put("2131099826","R.string.guide_one_content2");
		resMap.put("2131099823","R.string.guide_one_title");
		resMap.put("2131099824","R.string.guide_two_title");
		resMap.put("2131099879","R.string.h_");
		resMap.put("2131099882","R.string.h_s");
		resMap.put("2131099649","R.string.hello_world");
		resMap.put("2131099816","R.string.hint_nothing");
		resMap.put("2131099828","R.string.hint_random_app");
		resMap.put("2131099851","R.string.hint_unwifi");
		resMap.put("2131099899","R.string.hot_rec");
		resMap.put("2131099822","R.string.input_search_content");
		resMap.put("2131099716","R.string.instagram");
		resMap.put("2131099682","R.string.instagram_client_inavailable");
		resMap.put("2131099731","R.string.instapager_email_or_password_incorrect");
		resMap.put("2131099658","R.string.instapager_login_html");
		resMap.put("2131099726","R.string.instapaper");
		resMap.put("2131099727","R.string.instapaper_email");
		resMap.put("2131099729","R.string.instapaper_login");
		resMap.put("2131099730","R.string.instapaper_logining");
		resMap.put("2131099728","R.string.instapaper_pwd");
		resMap.put("2131099819","R.string.is_clear_history");
		resMap.put("2131099810","R.string.is_download");
		resMap.put("2131099818","R.string.is_quit_app");
		resMap.put("2131099698","R.string.kaixin");
		resMap.put("2131099721","R.string.kakaostory");
		resMap.put("2131099686","R.string.kakaostory_client_inavailable");
		resMap.put("2131099720","R.string.kakaotalk");
		resMap.put("2131099685","R.string.kakaotalk_client_inavailable");
		resMap.put("2131099743","R.string.kefu_email");
		resMap.put("2131099722","R.string.line");
		resMap.put("2131099684","R.string.line_client_inavailable");
		resMap.put("2131099707","R.string.linkedin");
		resMap.put("2131099666","R.string.list_friends");
		resMap.put("2131099744","R.string.loading");
		resMap.put("2131099762","R.string.love_game");
		resMap.put("2131099878","R.string.m_");
		resMap.put("2131099881","R.string.m_s");
		resMap.put("2131099859","R.string.menu_about");
		resMap.put("2131099856","R.string.menu_add_screenshots");
		resMap.put("2131099857","R.string.menu_added_screenshots");
		resMap.put("2131099860","R.string.menu_fav");
		resMap.put("2131099863","R.string.menu_full_screen");
		resMap.put("2131099864","R.string.menu_quit_full_screen");
		resMap.put("2131099862","R.string.menu_refresh");
		resMap.put("2131099858","R.string.menu_screenshots_show");
		resMap.put("2131099650","R.string.menu_settings");
		resMap.put("2131099861","R.string.menu_update");
		resMap.put("2131099719","R.string.mingdao");
		resMap.put("2131099737","R.string.mingdao_share_content");
		resMap.put("2131099662","R.string.multi_share");
		resMap.put("2131099703","R.string.neteasemicroblog");
		resMap.put("2131099763","R.string.new_user");
		resMap.put("2131099867","R.string.new_version");
		resMap.put("2131099866","R.string.next");
		resMap.put("2131099888","R.string.not_support_screenshot");
		resMap.put("2131099765","R.string.other_login");
		resMap.put("2131099764","R.string.phone_login");
		resMap.put("2131099711","R.string.pinterest");
		resMap.put("2131099681","R.string.pinterest_client_inavailable");
		resMap.put("2131099725","R.string.pocket");
		resMap.put("2131099668","R.string.pull_to_refresh");
		resMap.put("2131099655","R.string.pull_to_refresh_from_bottom_pull_label");
		resMap.put("2131099657","R.string.pull_to_refresh_from_bottom_refreshing_label");
		resMap.put("2131099656","R.string.pull_to_refresh_from_bottom_release_label");
		resMap.put("2131099652","R.string.pull_to_refresh_pull_label");
		resMap.put("2131099654","R.string.pull_to_refresh_refreshing_label");
		resMap.put("2131099653","R.string.pull_to_refresh_release_label");
		resMap.put("2131099710","R.string.qq");
		resMap.put("2131099680","R.string.qq_client_inavailable");
		resMap.put("2131099811","R.string.quit");
		resMap.put("2131099812","R.string.quit_clear");
		resMap.put("2131099691","R.string.qzone");
		resMap.put("2131099839","R.string.realte_tragedy");
		resMap.put("2131099849","R.string.rec_classical");
		resMap.put("2131099847","R.string.rec_popular");
		resMap.put("2131099848","R.string.rec_rank");
		resMap.put("2131099670","R.string.refreshing");
		resMap.put("2131099836","R.string.relate_app");
		resMap.put("2131099837","R.string.relate_game");
		resMap.put("2131099838","R.string.relate_user");
		resMap.put("2131099840","R.string.relate_website");
		resMap.put("2131099669","R.string.release_to_refresh");
		resMap.put("2131099697","R.string.renren");
		resMap.put("2131099834","R.string.research");
		resMap.put("2131099850","R.string.research_enter");
		resMap.put("2131099877","R.string.s_");
		resMap.put("2131099903","R.string.save_succeed");
		resMap.put("2131099835","R.string.search_empty");
		resMap.put("2131099833","R.string.search_game_app");
		resMap.put("2131099897","R.string.see_more");
		resMap.put("2131099672","R.string.select_a_friend");
		resMap.put("2131099665","R.string.select_one_plat_at_least");
		resMap.put("2131099754","R.string.send_your_feedback_to");
		resMap.put("2131099671","R.string.shake2share");
		resMap.put("2131099674","R.string.share");
		resMap.put("2131099831","R.string.share_");
		resMap.put("2131099841","R.string.share_QQ");
		resMap.put("2131099664","R.string.share_canceled");
		resMap.put("2131099663","R.string.share_completed");
		resMap.put("2131099885","R.string.share_default_title");
		resMap.put("2131099667","R.string.share_failed");
		resMap.put("2131099884","R.string.share_from_dianyixia");
		resMap.put("2131099821","R.string.share_game_andr_app");
		resMap.put("2131099855","R.string.share_main_content");
		resMap.put("2131099886","R.string.share_msg");
		resMap.put("2131099887","R.string.share_save_pic");
		resMap.put("2131099889","R.string.share_screenshort_failure");
		resMap.put("2131099865","R.string.share_screenshots");
		resMap.put("2131099661","R.string.share_to");
		resMap.put("2131099845","R.string.share_to_QQ");
		resMap.put("2131099846","R.string.share_to_QZONE");
		resMap.put("2131099734","R.string.share_to_baidutieba");
		resMap.put("2131099854","R.string.share_to_facebook");
		resMap.put("2131099738","R.string.share_to_mingdao");
		resMap.put("2131099842","R.string.share_to_moments");
		resMap.put("2131099736","R.string.share_to_qq");
		resMap.put("2131099735","R.string.share_to_qzone");
		resMap.put("2131099739","R.string.share_to_qzone_default");
		resMap.put("2131099843","R.string.share_to_wechat_fri");
		resMap.put("2131099844","R.string.share_to_weibo");
		resMap.put("2131099660","R.string.sharing");
		resMap.put("2131099700","R.string.shortmessage");
		resMap.put("2131099689","R.string.sinaweibo");
		resMap.put("2131099701","R.string.sohumicroblog");
		resMap.put("2131099702","R.string.sohusuishenkan");
		resMap.put("2131099690","R.string.tencentweibo");
		resMap.put("2131099829","R.string.tips");
		resMap.put("2131099651","R.string.title_activity_main");
		resMap.put("2131099713","R.string.tumblr");
		resMap.put("2131099814","R.string.tv_cancel");
		resMap.put("2131099696","R.string.twitter");
		resMap.put("2131099871","R.string.upgrade_Later");
		resMap.put("2131099872","R.string.upgrade_Now");
		resMap.put("2131099870","R.string.upgrade_Reminder");
		resMap.put("2131099874","R.string.upgrade_current");
		resMap.put("2131099868","R.string.upgrade_start");
		resMap.put("2131099873","R.string.upgrade_update");
		resMap.put("2131099869","R.string.upgrade_version");
		resMap.put("2131099875","R.string.uploaddone");
		resMap.put("2131099740","R.string.use_login_button");
		resMap.put("2131099894","R.string.user_favorities");
		resMap.put("2131099895","R.string.user_recent");
		resMap.put("2131099741","R.string.version");
		resMap.put("2131099715","R.string.vkontakte");
		resMap.put("2131099676","R.string.website");
		resMap.put("2131099692","R.string.wechat");
		resMap.put("2131099678","R.string.wechat_client_inavailable");
		resMap.put("2131099694","R.string.wechatfavorite");
		resMap.put("2131099693","R.string.wechatmoments");
		resMap.put("2131099675","R.string.weibo_oauth_regiseter");
		resMap.put("2131099677","R.string.weibo_upload_content");
		resMap.put("2131099724","R.string.whatsapp");
		resMap.put("2131099687","R.string.whatsapp_client_inavailable");
		resMap.put("2131099717","R.string.yixin");
		resMap.put("2131099683","R.string.yixin_client_inavailable");
		resMap.put("2131099718","R.string.yixinmoments");
		resMap.put("2131099705","R.string.youdao");


	}
}
