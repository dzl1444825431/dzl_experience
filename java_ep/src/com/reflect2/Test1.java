package com.reflect2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.reflect.UserBean;

public class Test1 {

	public static void main(String[] args) throws Exception {

		/*
		 * String[] arr = { "mAdapter" , "mStackFromBottom" , "mFirstPosition" ,
		 * "mItemCount" , "mDataSetObserver" , "mRecycler" ,
		 * "mOldSelectedPosition" , "mOldSelectedRowId" , "mSelectedPosition" ,
		 * "mGroupFlags" , "mBlockLayoutRequests" , "mDataChanged" ,
		 * "mNextSelectedPosition" , "mInLayout" , "mWidthMeasureSpec" ,
		 * "mIsScrap" , "mCachingActive" , "mSyncPosition" , "mSpecificTop" ,
		 * "mTouchMode" , "OldItemCount" , "mCachingStarted" };
		 */
		String[] arr = { "mGroupFlags", "mSelectedPosition", "mItemCount", "mInLayout", "mRecycler", "mFirstPosition",
				"mDataChanged", "mNeedSync", "mAdapter", "mHeightMeasureSpec", "mWidthMeasureSpec", "mParent",
				"mOnItemLongClickListener", };

		String[] methods = {

		"selectionChanged", "setNextSelectedPositionInt", "checkSelectionChanged", "isLayoutRtl",
				"setSelectedPositionInt", };

		for (String str : arr) {
			// System.out.println("field_" + str
			// +" = AbsListView.class.getDeclaredField(\"" + str + "\");");
			// System.out.println("field_" + str +".setAccessible(true);");
			System.out.println("private Field field_" + str + ";");
			System.out.println();
			// field_mAdapter = AbsListView.class.getDeclaredField("mAdapter");
			// field_mAdapter.setAccessible(true);
		}

		for (String str : arr) {
			System.out.println("field_" + str + " = AbsListView.class.getDeclaredField(\"" + str + "\");");
			System.out.println("field_" + str + ".setAccessible(true);");
		}

		System.out.println();
		for (String str : arr) {
			System.out.println("field_" + str + ".setAccessible(true);");
		}

		System.out.println();
		for (String str : arr) {
			System.out.println("}else if (field.getName().equals(\"" + str + "\")) {");
			System.out.println("field_" + str + " = field;");
			System.out.println("field_" + str + ".setAccessible(true);");
		}

		System.out.println();
		// method
		for (String str : methods) {
			System.out.println("private Method method_" + str + ";");
			System.out.println();
		}

		System.out.println();
		for (String str : methods) {
			System.out.println("}else if (m.getName().equals(\"" + str + "\")) {");
			System.out.println("method_" + str + " = m;");
			System.out.println("method_" + str + ".setAccessible(true);");
		}

		// m2();
	}

	// }else if (field.getName().equals("")) {
	//
	// }

	private static void m2() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
		/*
		 * 实列化类 方法1
		 */
		// String classPath = "com.whbs.bean.UserBean";
		// Class cla = Test1.class.getClassLoader().loadClass(classPath);
		// Object ob = cla.newInstance();

		/*
		 * 实列化类 方法2
		 */
		UserBean bean = new UserBean();
		bean.setId(100);
		bean.setAddress("武汉");

		// 得到类对象
		Class userCla = bean.getClass();
		Field field = userCla.getDeclaredField("address");
		field.setAccessible(true);
		System.out.println("resp1onse : " + field);
		System.out.println("resp1onse : " + field.get(bean));
		/*
		 * 得到类中的所有属性集合
		 */
		Field[] fs = userCla.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			Object val = f.get(bean);// 得到此属性的值

			System.out.println("name:" + f.getName() + "\t value = " + val);

			String type = f.getType().toString();// 得到此属性的类型
			if (type.endsWith("String")) {
				System.out.println(f.getType() + "\t是String");
				f.set(bean, "12"); // 给属性设值
			} else if (type.endsWith("int") || type.endsWith("Integer")) {
				System.out.println(f.getType() + "\t是int");
				f.set(bean, 12); // 给属性设值
			} else {
				System.out.println(f.getType() + "\t");
			}

		}

		/*
		 * 得到类中的方法
		 */
		Method[] methods = userCla.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().startsWith("get")) {
				System.out.print("methodName:" + method.getName() + "\t");
				System.out.println("value:" + method.invoke(bean));// 得到get 方法的值
			}
		}
	}

}