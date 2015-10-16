package com.对比重写方法;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * 工具类 
 * 		1.获取所有父类
 * 		2.获取所有可重写的方法
 * 		3.获取所有已重写的方法
 * @author dzl
 *
 */
public class OverrideClassOrMethodUtils {
	
	/**
	 * 获取一个类的所有父类
	 */
	public static ArrayList<Class<?>> getAllSuperClass(Class<?> clazz){
		ArrayList<Class<?>> classList = new ArrayList<Class<?>>();
		Class<?> superClass = clazz.getSuperclass();
		while (true) {
			if (superClass == null) {
				break;
			}
			System.out.println("resp1onse :superClass = " + superClass);
			classList.add(superClass);
			superClass = superClass.getSuperclass();
		}
		return classList;
		
	}

	/**
	 * 获取一个类的所有可以重写的方法（父类public、protect 及同包下的重写）
	 */
	public static ArrayList<Method> getAllSuperClassOverrideMethods(Class<?> clazz){
		ArrayList<Method> superMethodList = new ArrayList<Method>();
		Class<?> superClass = clazz.getSuperclass();
		while (true) {
			if (superClass == null) {
				break;
			}
			
			for (Method m : superClass.getDeclaredMethods()) {
				if (((m.getModifiers() & Modifier.PUBLIC) != 0) 
						|| ((m.getModifiers() & Modifier.PROTECTED) != 0)){
					
					superMethodList.add(m);
					continue;
				}
				
				if ( (superClass.getPackage().equals(clazz.getPackage())) 
						&& ((m.getModifiers() & Modifier.PRIVATE) == 0) ) {
					superMethodList.add(m);
					continue;
				}
				
			}
			superClass = superClass.getSuperclass();
		}
		return superMethodList;
		
	}

	/**
	 * 获取一个类中所有已经重写父类的方法
	 */
	public static ArrayList<Method> getAllOverridedMethods(Class<?> clazz){
		
		ArrayList<Method> methodList = new ArrayList<Method>();
		Method[] methods = clazz.getDeclaredMethods();
		ArrayList<Method> superClassMethods = getAllSuperClassOverrideMethods(clazz);
		
		for (Method m : methods) {
			if ((m.getModifiers() & Modifier.PRIVATE) == 0){
				for (Method override : superClassMethods) {
					if (m.getName().equals(override.getName())) {
						System.out.println("resp1onse : override Method = " + m.getName() + "; Class = " + override.getDeclaringClass().getName());
						methodList.add(m);
						break;
					}
				}
			}
		}
		return methodList;
	}

}
