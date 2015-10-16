package com.dzl.test.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsSpinner;
import android.widget.AdapterView;

public class ReflectUtil_generate {

	static HashMap<String,Integer> set_field = new HashMap<String,Integer>();
	static HashMap<String,Integer> set_method = new HashMap<String,Integer>();
	static Integer set_field_count = 0;
	static Integer set_method_count = 0;

	public static void main1(String[] args) {
		String path = "/storage/sdcard1/code_generete_view.txt";
		reflectFile(path, View.class);
		String path2 = "/storage/sdcard1/code_generete_view2.txt";
		reflectFile(path2, AbsSpinner.class, AdapterView.class, ViewGroup.class);

	}

	private static void reflectFile(String path, Class<?>... clz ) {
		if (clz == null) {
			return;
		}
		File file = new File(path);

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (int i = 0; i < clz.length; i++) {
				reflect(clz[i], bw);
			}
			
//			reflect(AbsSpinner.class, bw);
//			reflect(AdapterView.class, bw);
//			reflect(ViewGroup.class, bw);
//			reflect(View.class, bw);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {

				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static void reflect(Class<?> clz, BufferedWriter bw) throws Exception {

		initMethod(clz, bw);
		initField(clz, bw);

	}

	private static void initField(Class<?> clz, BufferedWriter bw) throws Exception {
		Field[] fields = clz.getDeclaredFields();
		String clzName = clz.getSimpleName();

		set_field_count++;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];

			int mod = f.getModifiers();
			String name = f.getName();
			Integer value = set_field.get(name);
			if (value != null && value - set_field_count > 0) {
				continue;
			}
			set_field.put(name,set_field_count + 1);

			if (!Modifier.isAbstract(mod) && !Modifier.isFinal(mod)) {

				bw.write("resp1one : public static Field field_" + clzName + "_" + name + ";\n");
			}
		}
		

		bw.write("resp1one : \n");
		bw.write("resp1one : private static void initField_" + clzName + "(Class<?> clz) {\n");
		bw.write("resp1one : Field[] fields = clz.getDeclaredFields();\n");
		bw.write("resp1one : for (Field f : fields) {\n");
		bw.write("resp1one : \n");

		set_field_count++;
		boolean isFirst = true;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			int mod = f.getModifiers();
			String name = f.getName();
			if (set_field.get(name) - set_field_count > 0) {
				continue;
			}
			set_field.put(name,set_field_count + 1);
			if (!Modifier.isAbstract(mod) && !Modifier.isFinal(mod)) {

				bw.write("resp1one : \n");
				if (isFirst) {
					isFirst = false;
					bw.write("resp1one : if (f.getName().equals(\"" + name + "\")) {\n");
					bw.write("resp1one : field_" + clzName + "_" + name + " = f;\n");
					bw.write("resp1one : field_" + clzName + "_" + name + ".setAccessible(true);\n");
				} else {
					bw.write("resp1one : }else if (f.getName().equals(\"" + name + "\")) {\n");
					bw.write("resp1one : field_" + clzName + "_" + name + " = f;\n");
					bw.write("resp1one : field_" + clzName + "_" + name + ".setAccessible(true);\n");

				}

			}
		}
		bw.write("resp1one : }\n");
		bw.write("resp1one : }\n");
		bw.write("resp1one : }\n");

		bw.write("resp1one : \n");
		set_field_count++;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			int mod = f.getModifiers();
			String name = f.getName();
			if (set_field.get(name) - set_field_count > 0) {
				continue;
			}
			set_field.put(name,set_field_count + 1);
			if (!Modifier.isAbstract(mod) && !Modifier.isFinal(mod)) {

				String simpleName = f.getType().getSimpleName();
				if (simpleName.equals("boolean")) {

					bw.write("resp1one : public static boolean getField_" + clzName + "_" + name + "(Object obj){\n");
					bw.write("resp1one : 	boolean o = false;\n");
					bw.write("resp1one : 	try {\n");
					bw.write("resp1one : 		o = (Boolean)field_" + clzName + "_" + name + ".get(obj);\n");
					bw.write("resp1one : 	} catch (Exception e) {\n");
					bw.write("resp1one : 		e.printStackTrace();\n");
					bw.write("resp1one : 	}\n");
					bw.write("resp1one : 	return o;\n");
					bw.write("resp1one : }\n");
				} else if (simpleName.equals("int")) {
					bw.write("resp1one : public static int getField_" + clzName + "_" + name + "(Object obj){\n");
					bw.write("resp1one : 	int o = -1;\n");
					bw.write("resp1one : 	try {\n");
					bw.write("resp1one : 		o = (Integer)field_" + clzName + "_" + name + ".get(obj);\n");
					bw.write("resp1one : 	} catch (Exception e) {\n");
					bw.write("resp1one : 		e.printStackTrace();\n");
					bw.write("resp1one : 	}\n");
					bw.write("resp1one : 	return o;\n");
					bw.write("resp1one : }\n");
				} else if (simpleName.equals("long")) {
					bw.write("resp1one : public static long getField_" + clzName + "_" + name + "(Object obj){\n");
					bw.write("resp1one : 	long o = -1;\n");
					bw.write("resp1one : 	try {\n");
					bw.write("resp1one : 		o = (Long)field_" + clzName + "_" + name + ".get(obj);\n");
					bw.write("resp1one : 	} catch (Exception e) {\n");
					bw.write("resp1one : 		e.printStackTrace();\n");
					bw.write("resp1one : 	}\n");
					bw.write("resp1one : 	return o;\n");
					bw.write("resp1one : }\n");
				} else if (simpleName.equals("float")) {
					bw.write("resp1one : public static float getField_" + clzName + "_" + name + "(Object obj){\n");
					bw.write("resp1one : 	float o = -1f;\n");
					bw.write("resp1one : 	try {\n");
					bw.write("resp1one : 		o = (Float)field_" + clzName + "_" + name + ".get(obj);\n");
					bw.write("resp1one : 	} catch (Exception e) {\n");
					bw.write("resp1one : 		e.printStackTrace();\n");
					bw.write("resp1one : 	}\n");
					bw.write("resp1one : 	return o;\n");
					bw.write("resp1one : }\n");
				} else {
					bw.write("resp1one : public static " + simpleName + " getField_" + clzName + "_" + name + "(Object obj){\n");
					bw.write("resp1one : 	" + simpleName + " o = null;\n");
					bw.write("resp1one : 	try {\n");
					bw.write("resp1one : 		o = (" + simpleName + ")field_" + clzName + "_" + name + ".get(obj);\n");
					bw.write("resp1one : 	} catch (Exception e) {\n");
					bw.write("resp1one : 		e.printStackTrace();\n");
					bw.write("resp1one : 	}\n");
					bw.write("resp1one : 	return o;\n");
					bw.write("resp1one : }\n");
				}

				bw.write("resp1one : \n");
				bw.write("resp1one : public static void setField_" + clzName + "_" + name + "(Object obj, Object value){\n");
				bw.write("resp1one : 	try {\n");
				bw.write("resp1one : 		field_" + clzName + "_" + name + ".set(obj, value);\n");
				bw.write("resp1one : 	} catch (Exception e) {\n");
				bw.write("resp1one : 		e.printStackTrace();\n");
				bw.write("resp1one : 	}\n");
				bw.write("resp1one : }\n");
				bw.write("resp1one : \n");
			}
		}
		bw.write("resp1one : \n");

	}

	private static void initMethod(Class<?> clz, BufferedWriter bw) throws Exception {

		Method[] methods = clz.getDeclaredMethods();
		String clzName = clz.getSimpleName();
		bw.write("resp1one : \n\n");
		set_method_count++;
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			int mod = m.getModifiers();
			String name = m.getName();
			Integer value = set_method.get(name);
			if (value != null && value  - set_method_count > 0) {
				continue;
			}
			set_method.put(name,set_method_count + 1);
			if (!Modifier.isAbstract(mod) && !Modifier.isFinal(mod)) {

				bw.write("resp1one : public static Method method_" + clzName + "_" + name + ";\n");
			}
		}

		bw.write("resp1one : \n");
		bw.write("resp1one : private static void initMethod_" + clzName + "(Class<?> clz) {\n");
		bw.write("resp1one : Method[] methods = clz.getDeclaredMethods();\n");
		bw.write("resp1one : for (Method m : methods) {\n");
		bw.write("resp1one : \n");
		
		set_method_count++;
		boolean isFirst = true;
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			int mod = m.getModifiers();
			String name = m.getName();
			if (set_method.get(name) - set_method_count > 0) {
				continue;
			}
			set_method.put(name,set_method_count + 1);
			if (!Modifier.isAbstract(mod) && !Modifier.isFinal(mod)) {

				if (isFirst) {
					isFirst = false;
					bw.write("resp1one : if (m.getName().equals(\"" + name + "\")) {\n");
					bw.write("resp1one : method_" + clzName + "_" + name + " = m;\n");
					bw.write("resp1one : method_" + clzName + "_" + name + ".setAccessible(true);\n");
					bw.write("resp1one : \n");
				} else {

					bw.write("resp1one : }else if (m.getName().equals(\"" + name + "\")) {\n");
					bw.write("resp1one : method_" + clzName + "_" + name + " = m;\n");
					bw.write("resp1one : method_" + clzName + "_" + name + ".setAccessible(true);\n");
					bw.write("resp1one : \n");
				}

			}
		}

		bw.write("resp1one : }\n");
		bw.write("resp1one : }\n");
		bw.write("resp1one : }\n");

		bw.write("resp1one : \n");
		
		set_method_count++;
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			int mod = m.getModifiers();
			String name = m.getName();
			
			if (set_method.get(name) - set_method_count > 0) {
				continue;
			}
			set_method.put(name,set_method_count + 1);
			if (!Modifier.isAbstract(mod) && !Modifier.isFinal(mod)) {

				String returnType = m.getReturnType().getSimpleName();
				if (returnType.equals("void")) {

					bw.write("resp1one : public static void methodInvoke_" + clzName + "_" + name + "(Object obj){\n");
					bw.write("resp1one : try {\n");
					bw.write("resp1one : method_" + clzName + "_" + name + ".invoke(obj);\n");
					bw.write("resp1one : } catch (Exception e) {\n");
					bw.write("resp1one : e.printStackTrace();\n");
					bw.write("resp1one : }\n");
					bw.write("resp1one : }\n");
					bw.write("resp1one : \n");
				} else {

					if (returnType.equals("boolean")) {
						bw.write("resp1one : public static boolean methodInvoke_" + clzName + "_" + name + "(Object obj){\n");
						bw.write("resp1one :  boolean o = false;\n");
						bw.write("resp1one : try {\n");
						bw.write("resp1one : o = (Boolean)method_" + clzName + "_" + name + ".invoke(obj);\n");
						bw.write("resp1one : } catch (Exception e) {\n");
						bw.write("resp1one : e.printStackTrace();\n");
						bw.write("resp1one : }\n");
						bw.write("resp1one : return o;\n");
						bw.write("resp1one : }\n");
						bw.write("resp1one : \n");

					} else if (returnType.equals("int")) {
						bw.write("resp1one : public static int methodInvoke_" + clzName + "_" + name + "(Object obj){\n");
						bw.write("resp1one :  int o = -1;\n");
						bw.write("resp1one : try {\n");
						bw.write("resp1one : o = (Integer)method_" + clzName + "_" + name + ".invoke(obj);\n");
						bw.write("resp1one : } catch (Exception e) {\n");
						bw.write("resp1one : e.printStackTrace();\n");
						bw.write("resp1one : }\n");
						bw.write("resp1one : return o;\n");
						bw.write("resp1one : }\n");
						bw.write("resp1one : \n");
					} else if (returnType.equals("float")) {
						bw.write("resp1one : public static float methodInvoke_" + clzName + "_" + name + "(Object obj){\n");
						bw.write("resp1one :  float o = -1;\n");
						bw.write("resp1one : try {\n");
						bw.write("resp1one : o = (Float)method_" + clzName + "_" + name + ".invoke(obj);\n");
						bw.write("resp1one : } catch (Exception e) {\n");
						bw.write("resp1one : e.printStackTrace();\n");
						bw.write("resp1one : }\n");
						bw.write("resp1one : return o;\n");
						bw.write("resp1one : }\n");
						bw.write("resp1one : \n");
					} else if (returnType.equals("long")) {
						bw.write("resp1one : public static long methodInvoke_" + clzName + "_" + name + "(Object obj){\n");
						bw.write("resp1one :  long o = -1;\n");
						bw.write("resp1one : try {\n");
						bw.write("resp1one : o = (Long)method_" + clzName + "_" + name + ".invoke(obj);\n");
						bw.write("resp1one : } catch (Exception e) {\n");
						bw.write("resp1one : e.printStackTrace();\n");
						bw.write("resp1one : }\n");
						bw.write("resp1one : return o;\n");
						bw.write("resp1one : }\n");
						bw.write("resp1one : \n");

					} else {
						bw.write("resp1one : public static " + returnType + " methodInvoke_" + clzName + "_" + name + "(Object obj){\n");
						bw.write("resp1one :  " + returnType + " o = null;\n");
						bw.write("resp1one : try {\n");
						bw.write("resp1one : o = (" + returnType + ")method_" + clzName + "_" + name + ".invoke(obj);\n");
						bw.write("resp1one : } catch (Exception e) {\n");
						bw.write("resp1one : e.printStackTrace();\n");
						bw.write("resp1one : }\n");
						bw.write("resp1one : return o;\n");
						bw.write("resp1one : }\n");
						bw.write("resp1one : \n");

					}

				}

			}
		}
		bw.write("resp1one : \n");

	}

}
