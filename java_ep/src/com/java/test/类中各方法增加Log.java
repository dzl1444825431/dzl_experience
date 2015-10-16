package com.java.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class 类中各方法增加Log {

	public static void main(String[] args) {
		String source = "D:\\android\\workspace\\testView\\src\\com\\LinearLayout2.java";

		addLogforMethods(source);

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
			String contructLine = "";
			boolean isContructMethod = false;
			while ((line = br.readLine()) != null) {

				bw.write(line + "\r\n");

				if (isContructMethod) {
					isContructMethod = false;
					bw.write("\t\tSystem.out.println(\"resp1onse: "
							+ contructLine.substring(0, contructLine.length() - 1) + " \");"
							+ "\r\n");
					continue;
				}
				
				if ((line.contains("public") || line.contains("private")
								|| line.contains("void") || line
									.contains("protected"))
						&& !line.contains("class")
						&& !line.contains("interface")) 
				{
					if (line.contains("{")) {
						
						if (!line.contains(className)) {
							isContructMethod = false;
							bw.write("\t\tSystem.out.println(\"resp1onse: "
									+ line.substring(0, line.length() - 1) + " \");"
									+ "\r\n");
							continue;
						}
						contructLine = line;
						isContructMethod = true;
						
					}else {
						contructLine = line;
						isContructMethod = true;
						continue;
					}
					
//					if (!line.contains(className)) {
//						isContructMethod = false;
//						bw.write("\t\tSystem.out.println(\"resp1onse: "
//								+ line.substring(0, line.length() - 1) + " \");"
//								+ "\r\n");
//						continue;
//					}
//					contructLine = line;
//					isContructMethod = true;
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
