package com.android;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Bat批量复制文件 {
	
	static String[] title = {
//			"AboutActivity",
//			"AddressActivity",
//			"CallCenterActivity",
//			"CommentActivity",
//			"ForgetPasswordActivity",
//			"GuideActivity",
//			"LocationActivity",
//			"LocationManulActivity",
//			"LoginActivity",
//			"OrdersActivity",
//			"OrdersConfirmActivity",
//			"OrdersWaitActivity",
//			"PayActivity",
//			"ProductNewActivity",
//			"ProductSpecialActivity",
//			"RegisterActivity",
//			"SearchActivity",
//			"SettingActivity",
//			"StartActivity",
//			"StoreActivity",
//			"UpdatePasswordActivity",
//			"UserActivity",
		"dialog_product_detail.xml",
		"dialog_photo.xml",
		"dialog_username.xml",
		"dialog_sex.xml",
		"dialog_city.xml",
	};
	
	static String srcPath = "D:\\work\\haoyangde\\haoyangde\\res\\layout\\item_guide.xml";
	static String descPath = srcPath.substring(0, srcPath.lastIndexOf("\\"));
	
	public static void main(String[] args) {
		
//		outActivity();
		
		
		copy(srcPath, descPath, title);
	}



	public static void outActivity() {
		for (String t : title) {
			System.out.println("<activity android:name=\"com.zhs.haoyangde.activity." + t + "\" />");
		}
	}
	
	
	
	public static void copy(String srcPath,String descPath, String[] fileNames){
		File file = new File(srcPath);
		InputStream inputStream = null;
		if (!file.exists()) {
			System.out.println(srcPath + "文件不存在");
			return;
		}
		
		byte b[] = new byte[(int) file.length()];
		try {
			inputStream = new FileInputStream(file);
			inputStream.read(b);
			close(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			close(inputStream);
		}
		
		for (String f : fileNames) {
			writeFile(descPath + File.separator + f, b);
		}
		
		
	}
	public static void writeFile(String path, byte[] b){
		File file = new File(path);
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			outputStream.write(b);
			close(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
			close(outputStream);
		}
	}
	
	public static void close(Closeable closeable){
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
