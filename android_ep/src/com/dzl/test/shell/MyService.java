package com.dzl.test.shell;

import java.util.UUID;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	CommandReceiver cmdReceiver;
	boolean flag;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		cmdReceiver = new CommandReceiver();
		flag = true;
		Log.i("onStartCommand", "onCreate=");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		this.unregisterReceiver(cmdReceiver);// 取消BroadcastReceiver
		super.onDestroy();
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("onStartCommand", "onStartCommand=");
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("AAAAA");
		registerReceiver(cmdReceiver, intentFilter);
		doJob();// 调用方法启动线程

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	// 接受广播
	private class CommandReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int cmd = intent.getIntExtra("cmd", -1);
			if (cmd == MainService.CMD_STOP_SERVICE) {// 如果等于0
				flag = false;// 停止线程
				stopSelf();// 停止服务
			}
		}

	}

	public void doJob() {
		new Thread() {
			@Override
			public void run() {
				while (flag) {// 如果==true执行发送广播
					try {
						Thread.sleep(1000);// 休眠1秒
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Log.i("onStartCommand", "run=");
					Intent intent = new Intent();
					intent.setAction("AAAAA");
					intent.putExtra("data", UUID.randomUUID() + "");
					sendBroadcast(intent);// 发送广播名称aaaaa 参数名字data

				}
			}
		}.start();
	}
}