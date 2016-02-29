package com.java.test;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class 非递归增加SystemOut {

	private static String suffix = ".java";
	private static Set<String> tags = new HashSet<String>();
	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		//scanDirNoRecursion("D:\\work\\android\\sources\\libs\\actionbarsherlock\\src");
		//scanDirNoRecursion("D:\\work\\workspace_eclipse\\xutils\\src");
		//scanDirNoRecursion("D:\\work\\workspace_eclipse\\xutils\\src\\com\\lidroid\\xutils\\sample2");
		//scanDirNoRecursion("D:\\work\\workspace_eclipse\\xutils2\\src\\com\\lidroid\\xutils\\sample2");
		//scanDirNoRecursion("D:\\work\\workspace_eclipse\\xutils");
//		scanDirNoRecursion("D:\\work\\android\\sources\\app\\src");
//		scanDirNoRecursion("D:\\work\\workspace_eclipse\\push");
//		scanDirNoRecursion("D:\\work\\workspace_eclipse\\miFile");
//		scanDirNoRecursion("D:\\work\\workspace_eclipse\\app_Browser\\src");
//		scanDirNoRecursion("D:\\work\\workspace_eclipse\\zirco-browser\\src");
//		scanDirNoRecursion("D:\\baiduYun\\baiduYun-dzl-office-150810\\dianyixia-dzl\\src\\com\\dingzai\\dianyixia");
//		scanDirNoRecursion("D:\\baiduYun\\baiduYun-dzl-office-150810\\dianyixia-dzl");
//		scanDirNoRecursion("D:\\baiduYun\\baiduYun-dzl-office-150810\\test\\src\\com\\dzl\\test\\viewPager");
		
//		scanDirNoRecursion("D:\\baiduYun\\dzl_github\\yunserver\\xiaoerduo\\src\\com\\ireadercity");
		suffix = ".xml";
		scanDirNoRecursion("D:\\baiduYun\\dzl_github\\apk\\shucainongyewang\\res\\layout");
		System.out.println("resp1onse1: " + (System.currentTimeMillis() - a));
		System.out.println(Llog.map);
		System.out.println("resp1onse total : tags = " + tags);
	}

	private static void operateMethod(File file) {
//		Llog.addLogforMethods(file.getAbsolutePath());//增加 SystemOut
//		Llog.addLogforMethods2(file.getAbsolutePath());//增加 SystemOut2
//		Llog.addLogforMethods3(file.getAbsolutePath());//增加 SystemOut3 2015-07-30 ok
//		Llog.replaceResourceIntToResourceType(file.getAbsolutePath());//替换资源文件int类型 转变为 layout等类型 2015-08-11 ok
//		Llog.getImportClasses(file.getAbsolutePath());//获取引用的API
//		Llog.getChineseString(file.getAbsolutePath());//获取所有中文字符
		
		
		tags.addAll(GenerateFindViewById.getTag(file.getAbsolutePath()));//获取xml各结点标签元素名
		
//		com.android.生成匹配R与Id.writeFileIds(rFile, file.getAbsolutePath());//获取所有中文字符
	}
	
	final static String rFile = "D:\\baiduYun\\dzl_github\\yunserver\\xiaoerduo\\src\\com\\ireadercity\\R.java";
	// 非递归
	/**
	 * @param path
	 */
	public static void scanDirNoRecursion(String path) {
		LinkedList<File> list = new LinkedList<File>();
		File dir = new File(path);
//		File file[] = dir.listFiles();
		
		
		for (File f : dir.listFiles()) {
			if (f.isDirectory())
				list.add(f);
			else {
				if (f.getName().endsWith(suffix)) {
					operateMethod(f);
				}
			}
		}
		
		File tmp;
		//File[] files;
		while (!list.isEmpty()) {
			tmp = (File) list.removeFirst();// 首个目录
			//System.out.println("resp1onse=====================1: " + tmp);
			if (tmp.isDirectory()) {
//				files = tmp.listFiles();
//				if (files == null)
//					continue;
				for (File f : tmp.listFiles()) {
					if (f.isDirectory())
						list.add(f);// 目录则加入目录列表，关键
					else {
						if (f.getName().endsWith(suffix)) {
							//System.out.println("resp1onse1:aa " + j + "  " + files[j]);
							operateMethod(f);
						}
					}
				}
				/*for (int j = 0; j < files.length; j++) {
					if (files[j].isDirectory())
						list.add(files[j]);// 目录则加入目录列表，关键
					else {
						if (files[j].getName().endsWith(".java")) {
							//System.out.println("resp1onse1:aa " + j + "  " + files[j]);
							operateMethod(files[j]);
						}
					}
				}*/
			} else {
				if (tmp.getName().endsWith(suffix)) {
					//System.out.println("tem=====" + tmp.getAbsolutePath());
					operateMethod(tmp);
				}
			}
		}
	}

	

}
