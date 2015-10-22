package com.dzl.test.install;

import java.util.ArrayList;
import java.util.List;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.dzl.test.BaseActivity;

/**
 * adb 批量卸载第三方apk 不包括sd上的apk 和预装的apk
 * @author Administrator
 *
 */
public class InstallApkActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		packList.add("com.tencent.mm");
		packList.add("com.duowan.mobile");
		packList.add("com.qihoo.appstore");
		packList.add("com.eg.android.AlipayGphone");
		packList.add("com.tencent.android.qqdownloader");
		packList.add("com.ifeng.news2");
		packList.add("net.csdn.csdnplus");
		packList.add("com.tencent.mobileqq");
		packList.add("com.dzl.test");
		packList.add("com.yy.a.liveworld");
		packList.add("com.sina.weibo");
		packList.add("cn.zhongyongapp.android");
		packList.add("com.kugou.android");
		packList.add("com.qihoo.yunpan");
		packList.add("ccom.maiziedu.app");

		
		PackageManager pm = getPackageManager();
		List<ApplicationInfo> list = pm.getInstalledApplications(0);
		//int length = list.size();
		for (ApplicationInfo app : list) {
			
			/*if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
				System.out.println("resp1onse system : " + app.packageName + " " + app.flags + " " + app.loadLabel(pm));
			}else {
				System.out.println("resp1onse other : " + app.packageName + " " + app.flags + " " + app.loadLabel(pm));
			}*/
			
			if (packList.contains(app.packageName)) {
				continue;
			}
			
			if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0){
				System.out.println("resp1onse sd adb uninstall " + app.packageName + " <name-" +  pm.getApplicationLabel(app) + "-name>" );
			}else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
//				System.out.println("resp1onse other : " + app.packageName + " " + app.flags + " " + app.loadLabel(pm));
				System.out.println("resp1onse adb uninstall " + app.packageName + " <name-" +  pm.getApplicationLabel(app) + "-name>" );
			}
			
			
			
			
		}
		
		//System.out.println("resp1onse : " + getString2());
		
		
		getSystemApp();
		getOtherApp();
		getInstallSDApp();
		getInstallMemoryApp();
		
		
	}
	
	/**
	 * 安装在内存上应用 
	 */
	private void getInstallMemoryApp() {
		
		PackageManager pm = getPackageManager();
		List<ApplicationInfo> list = pm.getInstalledApplications(0);
		
		int i = 0;
		for (ApplicationInfo app : list) {
			
			if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == 0) {
				i++;
				System.out.println("resp1onse : 安装在内存上应用 getInstallMemoryApp app.packageName = " + app.packageName +" app.processName = " + app.processName + " app.loadLabel(pm) = " + app.loadLabel(pm));
			}
			
		}
		System.out.println("resp1onse : 安装在内存上应用 getInstallMemoryApp size = " + i);
		
	}
	/**
	 * 安装在SD上应用 
	 */
	private void getInstallSDApp() {
		
		PackageManager pm = getPackageManager();
		List<ApplicationInfo> list = pm.getInstalledApplications(0);
		
		int i = 0;
		for (ApplicationInfo app : list) {
			
			if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
				i++;
				System.out.println("resp1onse : 安装在SD上应用 getInstallSDApp app.packageName = " + app.packageName +" app.processName = " + app.processName + " app.loadLabel(pm) = " + app.loadLabel(pm));
			}
			
		}
		System.out.println("resp1onse : 安装在SD上应用 getInstallSDApp size = " + i);
		
	}
	
	/**
	 * 第三方应用
	 */
	private void getOtherApp() {
		
		PackageManager pm = getPackageManager();
		List<ApplicationInfo> list = pm.getInstalledApplications(0);
		
		int i = 0;
		for (ApplicationInfo app : list) {
			
			if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				i++;
				System.out.println("resp1onse : 第三方应用 getOtherApp app.packageName = " + app.packageName +" app.processName = " + app.processName + " app.loadLabel(pm) = " + app.loadLabel(pm));
			}
			
		}
		System.out.println("resp1onse : 第三方应用 getOtherApp size = " + i);
	}
	
	/**
	 * 预装应用
	 */
	private void getSystemApp() {
		
		PackageManager pm = getPackageManager();
		List<ApplicationInfo> list = pm.getInstalledApplications(0);
		
		int i = 0;
		for (ApplicationInfo app : list) {
			
			if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
				i++;
				System.out.println("resp1onse : 预装应用 getSystemApp app.packageName = " + app.packageName +" app.processName = " + app.processName + " app.loadLabel(pm) = " + app.loadLabel(pm));
			}
			
		}
		System.out.println("resp1onse : 预装应用 getSystemApp size = " + i);
	}
	//public native String getString2();
	
	ArrayList<String> packList = new ArrayList<String>(16);
	

}
