package com.java.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateFindViewById {

	static void getFindViewId(String filePath, Map<String, String> map, int... a) {
		File sourceFile = new File(filePath);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			
			String line = "";
			String trimLine = "";
			
			Pattern pattern = Pattern.compile("android:id=\"@\\+id/(.*)\"");
			Matcher matcher = null;
			String noteName = null;
			String id = null;
			while ((line = br.readLine()) != null) {
				trimLine = line.trim();
				matcher = pattern.matcher(trimLine);
				if (trimLine.startsWith("<")) {
					noteName = trimLine.split(" ")[0].replace("<", "");
//					System.out.println("respone : noteName = " + noteName);
				}
				
				while(matcher.find()){
					id = matcher.group(1);
//					System.out.println(id + "\t\t" + noteName);
					
					if (noteName.contains(".")) {
						noteName = noteName.substring(noteName.lastIndexOf(".") + 1, noteName.length());
					}
					map.put(id, noteName);
					
					break;
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
		
		
		if (a.length > 2) {
			sysoActivity(map);
			
			sysoFragment(filePath, map);
			
			sysoAdapter(map);
			
			sysoLocal(map);
		}else {
			
			if (a.length == 1) {
				
				if (a[0] == 1) {
					sysoActivity(map);
				}else if (a[0] == 2) {
					sysoFragment(filePath, map);
				}else if (a[0] == 3) {
					sysoAdapter(map);
				}else {
					sysoFragment(filePath, map);
					sysoAdapter(map);
					sysoLocal(map);
				}
			}else {
				sysoLocal(map);
			}
			
			
			
		}
		
		sysoOnClickListener(map);
	}


	/**
	 * 局部
	 * @param map
	 */
	private static void sysoLocal(Map<String, String> map) {
		System.out.println("\n\n\n" );
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println(entry.getValue() + " " + entry.getKey() + " = (" + entry.getValue() + ") convertView.findViewById(R.id." + entry.getKey() + ");");
		}
	}


	/**
	 * holder
	 * @param map
	 */
	private static void sysoAdapter(Map<String, String> map) {
		System.out.println("\n\n\n" );
		
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println("holder." + entry.getKey() + " = (" + entry.getValue() + ") convertView.findViewById(R.id." + entry.getKey() + ");");
		}
		
		System.out.println("");
		System.out.println("convertView.setTag(holder);");
		System.out.println("} else {");
		System.out.println("");
		System.out.println("holder = (Holder) convertView.getTag();");
		System.out.println("}");
		System.out.println("");
		System.out.println("return convertView;");
		System.out.println("}");
		System.out.println("");
		System.out.println("class Holder {");
		System.out.println("");
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println(entry.getValue() + " " + entry.getKey() + ";");
		}
		System.out.println("");
		System.out.println("}");
	}


	/**
	 * OnClickListener
	 * @param map
	 */
	private static void sysoOnClickListener(Map<String, String> map) {
		System.out.println("\n\n\n" );
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println("setOnClickListener(" + entry.getKey() + ");");
		}
		
		System.out.println("\n\n\n" );
		System.out.println("	@Override");
		System.out.println("	public void onClick(View v) {");
		System.out.println("		super.onClick(v);");
		
		System.out.println("		switch (v.getId()) {\n");
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println("		case R.id." + entry.getKey() + ":\n			break;");
		}
		System.out.println("		default:\n			break;\n		}\n");
		
		System.out.println("	");
		System.out.println("	}");
	}

	/**
	 * Activity
	 * @param map
	 */
	private static void sysoActivity(Map<String, String> map) {
		System.out.println("\n\n\n" );
		
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println("private " + entry.getValue() + " " + entry.getKey() + ";");
		}
		System.out.println("\n\n\n" );
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println(entry.getKey() + " = (" + entry.getValue() + ") findViewById(R.id." + entry.getKey() + ");");
		}
	}


	/**
	 * Fragment
	 * @param map
	 */
	private static void sysoFragment(String filePath, Map<String, String> map) {
		System.out.println("\n\n\n" );
		
		System.out.println("private View main_layout;");
		System.out.println("private Activity activity;");
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println("private " + entry.getValue() + " " + entry.getKey() + ";");
		}
		System.out.println("\n\n");
		
		System.out.println("@Override");
		System.out.println("public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {");
		System.out.println("	");
		System.out.println("	if (main_layout == null) {");
		System.out.println("		activity = getActivity();");
		System.out.println("		main_layout = inflater.inflate(R.layout." + filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length() - 4) + ", null);");
		System.out.println("	");
		for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			System.out.println(entry.getKey() + " = (" + entry.getValue() + ") main_layout.findViewById(R.id." + entry.getKey() + ");");
		}
		
		System.out.println("		");
		System.out.println("	}");
		System.out.println("	");
		System.out.println("	return main_layout;");
		System.out.println("}");
		System.out.println("");
		
		System.out.println("\n\n");
		
		System.out.println("@Override");
		System.out.println("public void onResume() {");
		System.out.println("	super.onResume();");
		System.out.println("	");
		System.out.println("}");
		System.out.println("");
		
		
	}
	
	
	/**
	 * 获取xml各结点标签元素名
	 * @param filePath
	 */
	static void getTag(String filePath){
		
		File sourceFile = new File(filePath);
		FileReader fr = null;
		BufferedReader br = null;
		
		Set<String> tags = new HashSet<String>();
		
		try {
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			
			String line = "";
			
			Pattern pattern = Pattern.compile("<[\\w\\.]+");
			Matcher matcher = null;
			while ((line = br.readLine()) != null) {
				matcher = pattern.matcher(line);
				while(matcher.find()){
//					System.out.println("resp1onse : v = " + matcher.group(0));
					tags.add(matcher.group(0));
					break;
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
		System.out.println("\nresp1onse : filePath = " + filePath);
		System.out.println("resp1onse : tags = " + tags);
	}
	
	public static void main(String[] args) {
		
//		getTag("D:\\baiduYun\\dzl_github\\yunserver\\ecmobile_dzl\\res\\layout\\a0_signin.xml");
//		
//		String aString = "aa    <com.dzl.View  aaa";
//		String aString1 = "<View   aaa";
//		Pattern pattern = Pattern.compile("<[\\w\\.]*");
//		Matcher matcher = pattern.matcher(aString);
//		
//		if (matcher.find()) {
//			System.out.println("resp1onse : matcher.group(0) = " + matcher.group(0));
//		}
//		System.out.println("resp1onse : matcher.group(0) = " + false);
	}
	
}
