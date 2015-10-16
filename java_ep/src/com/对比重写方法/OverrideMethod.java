package com.对比重写方法;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import com.download.AA;

public class OverrideMethod {

	public static void main(String[] args) {
		/*// override(A.class, B.class);
		for (Method m : B.class.getMethods()) {
			System.out.println("resp1onse : " + m.getName() + Modifier.toString(m.getModifiers()));
		}
		System.out.println("resp1onse : =============");
		for (Method m : B.class.getDeclaredMethods()) {
			System.out.println("resp1onse : " + m.getName()+ Modifier.toString(m.getModifiers()));
		}
		System.out.println("resp1onse : =============");
		for (Class<?> m : B.class.getClasses()) {
			System.out.println("resp1onse : " + m.getName());
		}
		System.out.println("resp1onse : =============");
		for (Class<?> m : B.class.getDeclaredClasses()) {
			System.out.println("resp1onse : " + m.getName());
		}
		//findAllSuperClass(B.class);
		System.out.println("resp1onse : =============");
		
		//System.out.println("resp1onse : " + getAllSuperClassMethod(B.class));
		System.out.println("resp1onse : =============11111111");
		*/
		getAllOverridedMethods(B.class);
		
//		Package[] pack = Package.getPackages();
//		for (Package package1 : pack) {
//			System.out.println("resp1onse : " + package1.getName());
//		}
//		
//		for (Class<?> c : OverrideMethod.class.getClasses()) {
//			System.out.println("resp1onse : " + c);
//		}

	}

	class A extends AA {
		void a() {

		}

		void b() {

		}
		
		
	}

	class B extends A {
		void a() {
			b2();
		}

		void b1() {

		}
		private void b2() {
			
		}
		
		@Override
		protected void aa1() {
			super.aa1();
		}
	}

	public static void findAllSuperClass(Class<?> clazz) {
		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null) {
			System.out.println(superClass.getName());
			findAllSuperClass(superClass);
		}
	}
	
	ArrayList<Class<?>> getAllSuperClass(Class<?> clazz){
		ArrayList<Class<?>> classList = new ArrayList<Class<?>>();
		Class<?> superClass = clazz.getSuperclass();
		while (true) {
			if (superClass == null) {
				break;
			}
			classList.add(superClass);
			superClass = superClass.getSuperclass();
		}
		return classList;
		
	}
	
	/**
	 * 获取一个类的所有可重写的方法（父类public、protect 及同包下的重写）
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
