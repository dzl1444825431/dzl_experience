package com.dzl.test;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
/**
 * 检测app前台运行
 * @author dzl 16-06-11
 */
public class CheckAppRunningService extends Service {

	private ActivityManager am;
	private PowerManager pm;
	private Handler handler = new Handler(Looper.getMainLooper());
	private String[] otherPackages = {"com.tencent.mm","com.tencent.mobileqq","com.sina.weibo"};
	private String packageName;

	@Override
	public void onCreate() {
		super.onCreate();
		
		am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
		pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
		
		Notification notification = new Notification(R.drawable.ic_launcher, "", System.currentTimeMillis());
		// 设置点击此通知后自动清除  
        Intent intent = new Intent();
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		notification.setLatestEventInfo(this, "众用助手1", "乖乖操作90s，应用补贴不会少！", contentIntent);
        startForeground(1, notification);//强制前台 arg0 表示 id(必须不是0)
        
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		/*final SharedPreferences sp = getApplicationContext().getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE);
		try {
			//加入传感器   强制保存在内存中 不被kill掉进程  （效果待查）
			OrientationEventListener mOrientationListener = 
					new OrientationEventListener(this, SensorManager.SENSOR_DELAY_UI) {
				public void onOrientationChanged(int orientation) {
					
				}
			};
			if (mOrientationListener.canDetectOrientation()) mOrientationListener.enable();
		} catch (Exception e) {
			
		}*/
System.out.println("resp1onse CheckAppRunningService.onStartCommand()");
		//final String packageName = "com.zhongyongapp.android";
		packageName = "com.zhongyongapp.android";
		packageName = "com.zhiyun.feel";
		final int checkAppId = 1;//app Id
		int open_time = 90;//以秒为单位
		final int time = open_time * 1000;//以毫秒为单位
		
		/*if (intent == null || intent.getExtras() == null) {
			int checkState = sp.getInt("checkState", 0);
			if (checkState != 1) {// checkState = 1： 正在检测
				return super.onStartCommand(intent, flags, startId);
			}
			packageName = sp.getString("checkPackageName", "");
			checkAppId = sp.getInt("checkAid", 0);
			open_time = sp.getInt("checkTime", 0);
		}else {
			packageName = intent.getExtras().getString("packageName");
			checkAppId = intent.getExtras().getInt("asa_id");
			open_time = intent.getExtras().getInt("open_time");
			Editor e = sp.edit();
			e.putString("checkPackageName", packageName);
			e.putInt("checkAid", checkAppId);
			e.putInt("checkTime", open_time);
			e.putInt("checkTime", open_time);
			e.putInt("checkState", 1);//checkState = 1： 正在检测
			e.commit();
		}*/
		
		if (TextUtils.isEmpty(packageName) || checkAppId == 0 || open_time == 0) {
			return super.onStartCommand(intent, flags, startId);
		}
		
		final int sleep = 1000;
		//time = open_time * 1000;//以毫秒为单位
		final int totalCount = time / sleep;//总检测次数
		final int timeOut = time * 2;//超时时间
		final int otherTotalCount = totalCount > 20 ? 20 : totalCount;
		//延迟0.5秒执行
		handler.postDelayed(new Runnable() {

			public void run() {
				
				long endTime = System.currentTimeMillis() + timeOut;
				int count = 0;
				int otherCount = 0;
				int status = 0;
				while (true) {
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					/*if (!isScreenOn()) {
						stopSelf();
						break;     //黑屏退出检测
					}*/
					
					status = isForeGround();
					if (status == 1) {
						count++;     //检测到running 次数累加
					}else if (status == 3)   {
						otherCount++;
					}else {
						//不在前台否则退出检测
//						stopSelf();
//						break;
					}
					System.out.println("resp1onse : run count=" + count + " status=" + status + " otherCount=" + otherCount);
					
					//达到检测次数
					if (count >= totalCount) {
						/*Editor e = sp.edit();
						e.putInt("checkState", 0);//正常完成检测
						e.commit();*/
						//ZYApplication.sendSubsidy(checkAppId);
						break;
					}
				
					
					//第三方登录超时、或时间检测超时
					if (otherCount >= otherTotalCount || System.currentTimeMillis() >= endTime) {
						stopSelf();
						break;
					}
				}
				
			}
		},100);
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	/**
	 * 检测app是否在前台运行
	 * IMPORTANCE_FOREGROUND = 100 前台进程
	 * dzl
	 */
	private int isForeGround() {
		try {
			List<RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
			for (RunningAppProcessInfo info : appProcesses) {
				/*if (info.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
					
				}*/
				//BACKGROUND=400 EMPTY=500 FOREGROUND=100 GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
				if (info.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
					//System.out.println("resp1onse : " + info.importance + " " + Arrays.toString(info.pkgList) + " "  + info.processName);
					for (String pName : info.pkgList) {
						if (packageName.equals(pName)) {
							return 1;
						}
						
						for (String otherName : otherPackages) {
							if (otherName.equals(pName)) {
								//System.out.println("resp1onse : otherName=" + otherName);
								return 3;
							}
							
						}
						
					}
					
				}
				
			}
//			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 检测屏幕是否黑屏（包括自动黑屏、按键黑屏） dzl
	 * sdk > 20时可使用 pm.isInteractive()
	 */
	public boolean isScreenOn() {
		return pm.isScreenOn();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
//		android.os.Process.killProcess(android.os.Process.myPid());//结束进程
	}

}