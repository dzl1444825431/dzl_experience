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
public class AddDebugLogUseRegex {

	public static void main(String[] args) {
//		String source = "D:\\work\\android\\sources\\libs\\actionbarsherlock\\src\\com\\actionbarsherlock\\internal\\widget\\AbsActionBarView.java";
		String source = "D:\\android\\workspace\\java\\src\\com\\java\\test\\TestClass.java";

		addLogforMethods(source);
//		bufferReader(source);

	}
	
	

	static void addLogforMethods(String source) {
		File sourceFile = new File(source);
		File targeFile = null;
		String target = source.replace(sourceFile.getName(), sourceFile.getName() + "2");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		String className = sourceFile.getName().replace(".java", "");
//		String subClassName = sourceFile.getName().replace(".java", "");
		Pattern emptyLine = Pattern.compile("^(\\s*)$");
		Pattern singleComment = Pattern.compile("^(\\s*[/]{2})");
		Pattern chunkCommentStart = Pattern.compile("^(\\s*/\\*)");
		Pattern chunkCommentEnd = Pattern.compile("(\\*/)$");
		Pattern singleChunkComment = Pattern.compile("(^(\\s*/\\*)).*((\\*/\\s*)$)");
		
		Pattern classReg = Pattern.compile("(^(\\s*(public|protected|private)?\\s*(static\\s*)?(final\\s*|abstract\\s*)?(static)?))\\s*class\\s*(\\w*)\\s*.*((\\{\\s*)$)");
		
		Pattern singleMethod = Pattern.compile("(.*)\\{(\\s*(super\\(|this\\(|return|throw\\s)?)?\\s*.*((\\}\\s*)$)");
//		Pattern chunkMethodReturn = Pattern.compile(".*\\{(\\s*((return\\()|(throw\\())?)?.*((\\}\\s*)$)");
		
		Pattern method = Pattern.compile("(^\\s*(public\\s*|protected\\s*|private))(((\\{\\s*)(\\s*[/]{2}.*)?)$)");
		Pattern methodStart = Pattern.compile("(^(?!.*(class\\s|interface\\s)).*)(((\\{\\s*)(\\s*[/]{2}.*)?)$)");
		Pattern methodEnd = Pattern.compile(".*((\\}\\s*)(\\s*[/]{2}.*)?$)");
		Matcher mathcer = null;
		
		
		
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			targeFile = new File(target);
			fw = new FileWriter(targeFile);
			bw = new BufferedWriter(fw);

			String line = "";
			String methodLine = "";
			String trimLine = "";
			String beforeLine = "";
			String subClassName = "";
			String methodName = "";
			StringBuffer chunkMethodName = new StringBuffer();

			int i = 0;
			boolean isChunkComment = false;
			boolean isChunkMethod = false;
			boolean isMethodEnd = false;
			boolean isChunkMethodStart = false;
			int methodStartCount = 0;
			int classCount = 0;
			
			out:while ((line = br.readLine()) != null) {
				
				i++;
				
				//=========== 空行 原样读写 ===========
				mathcer = emptyLine.matcher(line);
				while (mathcer.find()) {
					//System.out.println(mathcer.group() + "==emptyLine " + i);
					bw.write(line + "\r\n");
					continue out;
				}
				
				//======== 注释start ===========
				//单行块注释
				mathcer = singleChunkComment.matcher(line);
				while (mathcer.find()) {
					bw.write(line + "\r\n");
					isChunkComment = false;
					continue out;
				}
				
				if (isChunkComment) {
					mathcer = chunkCommentEnd.matcher(line);
					while (mathcer.find()) {
						bw.write(line + "\r\n");
						isChunkComment = false;
						continue out;
					}
				}
				
				mathcer = chunkCommentStart.matcher(line);
				while (mathcer.find()) {
					bw.write(line + "\r\n");
					isChunkComment = true;
					continue out;
				}
				
				if (isChunkComment) {
					bw.write(line + "\r\n");
					continue out;
				}
				
				mathcer = singleComment.matcher(line);
				while (mathcer.find()) {
					bw.write(line + "\r\n");
					continue out;
				}
				//===== 注释end ===========
				
				
				
				//bw.write(line + "\r\n");
				beforeLine = line;
				trimLine = line.trim();
				
				//==========有效代码 start===========
				//单行
				mathcer = singleMethod.matcher(line);
				while (mathcer.find()) {
					//System.out.println(mathcer.group() + "==singleMethod " + i);
//					System.out.println(mathcer.group(2) + "" + i);
//					System.out.println(mathcer.group(3) + "" + i);
					bw.write("\r\n");
					if ("super(".equals(mathcer.group(3)) ||"this(".equals(mathcer.group(3))) {
						bw.write(line.replace("}","\r\n\t\tSystem.out.println(\"resp1onse "
								+ className + ":"
								+ mathcer.group(1) + " \");\r\n\t}") + "\r\n");
						
					}else {
						bw.write(line.replace("{","{\r\n\t\tSystem.out.println(\"resp1onse " 
								+ className + ":"
								+ mathcer.group(1) + " \");\r\n\t\t").replace("}", "\r\n\t}\r\n"));
					}
					continue out;
				}
				//bw.write(line + "\r\n");
				
				//多行  start
				if (isChunkMethod) {
					//chunkMethodName.append(trimLine);
					if (isChunkMethodStart) {
						isChunkMethodStart = false;
						if (trimLine.startsWith("super(") || trimLine.startsWith("this(")) {
							bw.write(line + "\r\n");
							bw.write("{\t\tSystem.out.println(\"resp1onse " 
									+ className + ":"
									+ methodName + " \");\r\n");
						}else {
							bw.write("{\t\tSystem.out.println(\"resp1onse " 
									+ className + ":"
									+ methodName + " \");\r\n");
							bw.write(line + "\r\n");
						}
					}
					
					mathcer = methodEnd.matcher(line);
					while (mathcer.find()) {
						methodStartCount--;
						if (methodStartCount == 0) {
							isChunkMethod = false;
						}
						//System.out.println(i + "method end " + mathcer.group() + methodStartCount);
						continue out;
					}
				}
				
				
				
				mathcer = methodStart.matcher(line);
				while (mathcer.find()) {
					methodStartCount++;
					//chunkMethodName.setLength(0);
					isChunkMethod = true;
					isChunkMethodStart = true;
					methodName = mathcer.group(1);
					System.out.println(i + "method start " + mathcer.group(1) + methodStartCount);
					bw.write(line + "\r\n");
					continue out;
				}
				//多行  end
				//bw.write(line + "\r\n");
				
				//类名行
				mathcer = classReg.matcher(line);
				while (mathcer.find()) {
					classCount++;
					if (classCount > 1) {
						subClassName = mathcer.group(7);
					}
//					System.out.println(mathcer.group() + "==class " + i);
//					System.out.println(mathcer.group(7) + "==class " + i);
					//isSingleMethod = true;
					continue out;
				}
				
				//bw.write(line + "\r\n");
				
				
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

	
	static void bufferReader(String source){
		File sourceFile = new File(source);
		String target = source.replace(sourceFile.getName(), sourceFile.getName() + "2");
		File targeFile =  new File(target);
		 // 复制文件  
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try {  
            in = new FileInputStream(sourceFile);  
            out = new FileOutputStream(targeFile);  
            byte[] buffer = new byte[(int) sourceFile.length()];  
            StringBuffer sBuffer = new StringBuffer();
  
            while ((byteread = in.read(buffer)) != -1) {
            	System.out.println("resp1onse1: " + byteread);
            	sBuffer.append(buffer);
                //out.write(buffer, 0, byteread);  
            }  
            System.out.println("resp1onse1: " + sBuffer.toString());
        } catch (FileNotFoundException e) {  
        } catch (IOException e) {  
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	}
	
	
	/**
	 * int byteread = 0; // 读取的字节数  
			 
			StringBuffer stringBuffer = new StringBuffer();
			
	        char[] cbuf = new char[(int) sourceFile.length()] ;
			while ((byteread = br.read(cbuf)) != -1) {
	        	stringBuffer.append(cbuf);
	        }
			System.out.println("resp1onse1: " + stringBuffer);
			
			if ("1".equals("1")) {
				return;
			}
			
			if (sourceFile.length() > Integer.MAX_VALUE) {
			return;
		}
	 */
	
}
