package com.dzl.test.progressCheck;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.ProcessErrorStateInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CheckAppRunningActivity extends Activity {

	private ActivityManager am;
	private boolean isRun = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setContentView(R.layout.activity_matrix);

		am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

		getRunningAppProcesses();

		// 获得当前正在运行的service
		List<ActivityManager.RunningServiceInfo> appList2 = am.getRunningServices(100);
		for (ActivityManager.RunningServiceInfo running : appList2) {
			System.out.println("resp1onse :  running.service.getClassName() = " + running.service.getClassName());
		}

		System.out.println("resp1onse ================");

		// 获得当前正在运行的activity
		List<ActivityManager.RunningTaskInfo> appList3 = am.getRunningTasks(1000);
		for (ActivityManager.RunningTaskInfo running : appList3) {
			System.out.println("resp1onse :  running.baseActivity.getClassName() = "
					+ running.baseActivity.getClassName());
		}
		System.out.println("resp1onse ================");

		// 获得最近运行的应用
		List<ActivityManager.RecentTaskInfo> appList4 = am.getRecentTasks(100, 1);
		for (ActivityManager.RecentTaskInfo running : appList4) {
			System.out.println("resp1onse :  running.origActivity.getClassName() = " + running.origActivity);
		}

		run2();
		l();
		getProcessOld();
//		a();
//		run("ps | grep " + "com.h5.diet");
		getRunningTasks();
		
		System.out.println("resp1onse :am.getProcessesInErrorState() ================================================ " );
		List<ProcessErrorStateInfo> errorState = am.getProcessesInErrorState();
		System.out.println("resp1onse : errorState == null " + (errorState == null));
		if (errorState != null) {
			
			for (ProcessErrorStateInfo a : errorState) {
				System.out.println("resp1onse : " + a.condition +" " + 
						a.processName +" " + 
						a.pid +" " + 
						a.uid +" " + 
						a.tag +" " + 
						a.shortMsg +" " + 
						a.longMsg +" " + 
						a.stackTrace);
			}
		}
		
		Button button = new Button(this);
		button.setText("点击开始/停止检测");
		setContentView(button);
		button.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				getRunningTasks();
				getRunningAppProcesses();
				isRun = !isRun;
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						while (isRun) {
							run222();
							getRunningTasks();
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});
		
		compare(null, null);
	}

	private void getRunningAppProcesses() {
		// 获得系统运行的进程
		System.out.println("resp1onse am.getRunningAppProcesses()  ================");
		List<ActivityManager.RunningAppProcessInfo> appList1 = am.getRunningAppProcesses();
		for (RunningAppProcessInfo running : appList1) {
			System.out.println("resp1onse :  running.processName = " + running.processName);
		}
		
	}

	private void getRunningTasks() {
		System.out.println("resp1onse : am.getRunningTasks(1000) ============================== " );
		List<RunningTaskInfo> runningTasks = am.getRunningTasks(1000);
		for (RunningTaskInfo runningTaskInfo : runningTasks) {
			System.out.println("resp1onse : " + runningTaskInfo.id+" " + 
					runningTaskInfo.baseActivity+" " + 
					runningTaskInfo.topActivity+" " + 
					runningTaskInfo.description+" " + 
					runningTaskInfo.numActivities+" " + 
					runningTaskInfo.numRunning );
		}
	}

	private void run2() {
		String[] activePackages;
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			activePackages = getActivePackages();
		} else {
			activePackages = getActivePackagesCompat();
		}
		if (activePackages != null) {
			for (String activePackage : activePackages) {
				System.out.println("resp1onse : activePackage = " + activePackage);
			}
		}
	}

	String[] getActivePackagesCompat() {
		final List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1000);
		final ComponentName componentName = taskInfo.get(0).topActivity;
		final String[] activePackages = new String[1];
		activePackages[0] = componentName.getPackageName();
		return activePackages;
	}

	String[] getActivePackages() {
		final Set<String> activePackages = new HashSet<String>();
		final List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		System.out.println("resp1onse : processInfos.size() = " + processInfos.size());
		for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
			if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				activePackages.addAll(Arrays.asList(processInfo.pkgList));
			}
		}
		return activePackages.toArray(new String[activePackages.size()]);
	}

	RunningAppProcessInfo l() {
		final int PROCESS_STATE_TOP = 2;
		ActivityManager.RunningAppProcessInfo currentInfo = null;
		Field field = null;
		try {
			field = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
		} catch (Exception ignored) {
		}
		List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		System.out.println("resp1onse : processInfos.size() = " + processInfos.size());
		for (ActivityManager.RunningAppProcessInfo app : processInfos) {
			if (app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
					&& app.importanceReasonCode == ActivityManager.RunningAppProcessInfo.REASON_UNKNOWN) {
				Integer state = null;
				try {
					state = field.getInt(app);
				} catch (Exception e) {
				}
				if (state != null && state == PROCESS_STATE_TOP) {
					currentInfo = app;
					break;
				}
			}
		}
		return currentInfo;
	}

	private String getProcess() throws Exception {
		if (Build.VERSION.SDK_INT >= 21) {
			return getProcessNew();
		} else {
			return getProcessOld();
		}
	}

	// API 21 and above
	private String getProcessNew() throws Exception {
		String topPackageName = null;
		// UsageStatsManager usage = (UsageStatsManager)
		// context.getSystemService(Constant.USAGE_STATS_SERVICE);
		// long time = System.currentTimeMillis();
		// List<UsageStats> stats =
		// usage.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time -
		// ONE_SECOND * 10, time);
		// if (stats != null) {
		// SortedMap<Long, UsageStats> runningTask = new
		// TreeMap<Long,UsageStats>();
		// for (UsageStats usageStats : stats) {
		// runningTask.put(usageStats.getLastTimeUsed(), usageStats);
		// }
		// if (runningTask.isEmpty()) {
		// return null;
		// }
		// topPackageName =
		// runningTask.get(runningTask.lastKey()).getPackageName();
		// }
		return topPackageName;
	}

	// API below 21
	// @SuppressWarnings("deprecation")
	private String getProcessOld() {
		String topPackageName = null;

		List<RunningTaskInfo> runningTask = am.getRunningTasks(100);
		System.out.println("resp1onse : runningTask.size() = " + runningTask.size());
		if (runningTask != null) {
			RunningTaskInfo taskTop = runningTask.get(0);
			ComponentName componentTop = taskTop.topActivity;
			topPackageName = componentTop.getPackageName();
		}
		return topPackageName;
	}

	void a() {
		try {
			Process psProcess = Runtime.getRuntime().exec("sh");
			DataOutputStream out = new DataOutputStream(psProcess.getOutputStream());
			InputStream is = psProcess.getInputStream();
			// out.writeBytes("ps | grep 'vpnloader' | cut -c 10-14\n");
			out.writeBytes("ps\n");
			// out.writeBytes("ps");
			out.flush();
			try {
				psProcess.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (is.read() != 0) {
				byte firstByte = (byte) is.read();
				int available = is.available();
				byte[] characters = new byte[available + 1];
				characters[0] = firstByte;
				is.read(characters, 1, available);
				String re = new String(characters);
				System.out.println("resp1onse : ps characters = " + re);
			}
			System.out.println("resp1onse CheckAppRunningActivity.a()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// top命令
	public static final String[] TOP = { "/system/bin/top", "-n", "1" };

	// 现在执行top -n 1，我们只需要第二行（用第二行求得CPU占用率，精确数据）
	// 第一行：User 35%, System 13%, IOW 0%, IRQ 0% // CPU占用率
	// 第二行：User 109 + Nice 0 + Sys 40 + Idle 156 + IOW 0 + IRQ 0 + SIRQ 1 = 306
	// // CPU使用情况
	public synchronized String run(String cmd) {
		String line = "";
		InputStream is = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec(cmd);
			is = proc.getInputStream();
			// 换成BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				System.out.println("resp1onse : line = " + line);
			}
//			do {
//				line = br.readLine();
//				System.out.println("resp1onse : line " + line);
//				// 前面有几个空行
//				if (line.startsWith("User")) {
//					// 读到第一行时，我们再读取下一行
//					line = br.readLine();
//					break;
//				}
//			} while (true);
			if (is != null) {
				br.close();
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	
	public void run222() {
		String[] activePackages;
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			activePackages = getActivePackages2();
		} else {
			activePackages = getActivePackagesCompat2();
		}
		if (activePackages != null) {
			for (String activePackage : activePackages) {
				if (activePackage.equals("com.google.android.calendar")) {
					// Calendar app is launched, do something
				}
			}
		}
		
	}

	String[] getActivePackagesCompat2() {
		final List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
		final ComponentName componentName = taskInfo.get(0).topActivity;
		final String[] activePackages = new String[1];
		activePackages[0] = componentName.getPackageName();
		System.out.println("resp1onse : getActivePackagesCompat2 componentName.getPackageName() = " + componentName.getPackageName());
		return activePackages;
	}

	String[] getActivePackages2() {
		final Set<String> activePackages = new HashSet<String>();
		final List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
			System.out.println("resp1onse : getActivePackages2 processInfo.processName = " + processInfo.processName);
			if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				activePackages.addAll(Arrays.asList(processInfo.pkgList));
			}
		}
		return activePackages.toArray(new String[activePackages.size()]);
	}
	
//	String getTopPackage(){
//	    long ts = System.currentTimeMillis();
//	    UsageStatsManager mUsageStatsManager = (UsageStatsManager)getSystemService("usagestats");
//	    List<UsageStats> usageStats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, ts-1000, ts);
//	    if (usageStats == null || usageStats.size() == 0) {
//	        return NONE_PKG;
//	    }
////	    Collections.sort(usageStats, mRecentComp);
//	    AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
//	    return usageStats.get(0).getPackageName();
//	}
	
	public final int compare(ApplicationInfo a, ApplicationInfo b) {  
        
//        ComponentName aName = a.intent.getComponent();  
//        ComponentName bName = b.intent.getComponent();  
        ComponentName aName = getComponentName();  
        ComponentName bName = getCallingActivity();  
        int aLaunchCount,bLaunchCount;  
        long aUseTime,bUseTime;  
        int result = 0;  
         
         try {  
               
             //获得ServiceManager类  
             Class<?> ServiceManager = Class  
                .forName("android.os.ServiceManager");  
               
             //获得ServiceManager的getService方法  
             Method getService = ServiceManager.getMethod("getService", java.lang.String.class);  
               
             //调用getService获取RemoteService  
             Object oRemoteService = getService.invoke(null, "usagestats");  
               
             //获得IUsageStats.Stub类  
             Class<?> cStub = Class.forName("com.android.internal.app.IUsageStats$Stub");  
             //获得asInterface方法  
             Method asInterface = cStub.getMethod("asInterface", android.os.IBinder.class);  
             //调用asInterface方法获取IUsageStats对象  
             Object oIUsageStats = asInterface.invoke(null, oRemoteService);  
             //获得getPkgUsageStats(ComponentName)方法  
             Method getPkgUsageStats = oIUsageStats.getClass().getMethod("getPkgUsageStats", ComponentName.class);  
             //调用getPkgUsageStats 获取PkgUsageStats对象  
             Object aStats = getPkgUsageStats.invoke(oIUsageStats, aName);  
//             Object bStats = getPkgUsageStats.invoke(oIUsageStats, bName);  
               
             //获得PkgUsageStats类  
             Class<?> PkgUsageStats = Class.forName("com.android.internal.os.PkgUsageStats");  
               
             aLaunchCount = PkgUsageStats.getDeclaredField("launchCount").getInt(aStats);  
//             bLaunchCount = PkgUsageStats.getDeclaredField("launchCount").getInt(bStats);  
             aUseTime = PkgUsageStats.getDeclaredField("usageTime").getLong(aStats);  
//             bUseTime = PkgUsageStats.getDeclaredField("usageTime").getLong(bStats);  
//               
//             if((aLaunchCount>bLaunchCount)||  
//                     ((aLaunchCount == bLaunchCount)&&(aUseTime>bUseTime)))  
//                 result = 1;  
//             else if((aLaunchCount<bLaunchCount)||((aLaunchCount ==  
//                 bLaunchCount)&&(aUseTime<bUseTime)))  
//                 result = -1;  
//             else {  
//                 result = 0;  
//             }  
                 result = 0;  

             } catch (Exception e) {  
              Log.e("###", e.toString(), e);  
             }  

        return result;  
    }  
	
//	private String getProcessNew2() throws Exception {
//	    String topPackageName = null;
//	    UsageStatsManager usage = (UsageStatsManager) context.getSystemService(Constant.USAGE_STATS_SERVICE);
//	    long time = System.currentTimeMillis();
//	    List<UsageStats> stats = usage.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - ONE_SECOND * 10, time);
//	    if (stats != null) {
//	        SortedMap<Long, UsageStats> runningTask = new TreeMap<Long,UsageStats>();
//	        for (UsageStats usageStats : stats) {
//	            runningTask.put(usageStats.getLastTimeUsed(), usageStats);
//	        }
//	        if (runningTask.isEmpty()) {
//	            return null;
//	        }
//	        topPackageName =  runningTask.get(runningTask.lastKey()).getPackageName();
//	    }
//	    return topPackageName;
//	}

}
