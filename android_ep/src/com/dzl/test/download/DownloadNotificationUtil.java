package com.dzl.test.download;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.Toast;

/**
 * 显示通知
 * @author dzl 2015年12月25日
 */
public class DownloadNotificationUtil {
	
	private final int id;
	private Context mContext;
	private String filePath;
	private NotificationManager notificationManager;
	private Builder mBuilder;
	private CharSequence appLabel;
	private boolean install;

	public DownloadNotificationUtil(Context context, String filePath) {
		super();
		this.mContext = context.getApplicationContext();
		this.filePath = filePath;
		id = 1001;
		notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationBuilder();
	}

	public void setInstall(boolean install) {
		this.install = install;
	}


	/**
	 * 设置通知
	 */
	@SuppressLint("InlinedApi") 
	private void createNotificationBuilder() {
		
		mBuilder = new NotificationCompat.Builder(mContext);  
		appLabel = mContext.getPackageManager().getApplicationLabel(mContext.getApplicationInfo());
		mBuilder.setContentTitle(appLabel)//设置通知栏标题  
		.setProgress(100, 0, false)
//	    .setContentIntent(getPendingIntent() //设置通知栏点击意图  
//	  	.setNumber(number) //设置通知集合的数量  
	    .setTicker("开始下载" + appLabel) //通知首次出现在通知栏，带上升动画效果的  
	    .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间  
	    .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级  
//	  	.setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消    
	    .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)  
	    .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合  
	    //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission  
	    .setSmallIcon(mContext.getApplicationInfo().icon)//设置通知小ICON  
	    ;
		
	}
	
	/**
	 * 创建并显示通知
	 */
	public void setNotification(int progress, boolean fail){
		
		if (fail) {
			mBuilder.setContentText("下载失败！请重新下载");
			
		}else {
			mBuilder.setProgress(100, progress, false);
			if (progress >= 100) {
				mBuilder.setContentText("进度: " + progress +  "%, 点击安装!")
						.setContentIntent(getPendingIntent()); //设置通知栏点击意图  
				if (install) {
					mContext.startActivity(setIntent());
				}
				Toast.makeText(mContext, appLabel + "下载完成！", Toast.LENGTH_SHORT).show();
			}else {
				mBuilder.setContentText("进度: " + progress +  "% ");
			}
		}
		
		Notification notification = mBuilder.build();
        notificationManager.notify(id, notification);
	}
	
	/**
	 * 通知栏点击回调事件, 点击安装
	 */
	private PendingIntent getPendingIntent() {
		if (filePath == null) {
			return null;
		}
		
		Intent intent = setIntent();
		return PendingIntent.getActivity(mContext, 0, intent, 0);
	}

	private Intent setIntent() {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		
		Uri uri = Uri.fromFile(new File(filePath));
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		return intent;
	}

}
