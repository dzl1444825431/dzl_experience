package com.dzl.test.shell;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

public class PsShellService extends Service {

	private Handler handler;
	private boolean isStop = false;
	private Runnable r;
	private String code;
	

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		isStop = true;
		handler = new Handler();
		
		String packageName = "com\\.zhongyongapp";
		code = "ps |grep " + packageName;
		
		r = new Runnable() {

			@Override
			public void run() {
				isStop = false;
				System.out.println("resp1onse : code = " + code);
				Runtime runtime = Runtime.getRuntime();
				int sleep = 10000;
				while (!isStop) {
					
					if (sleep < 0) {
						isStop = true;
					}
					
					if (isStop) {
						stopSelf();
						break;
					}
					try {
						sleep -= 500;
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ps(code, runtime);
				}
				
			}
		};
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Bundle extras = intent.getExtras();
		if (extras != null) {
			String str = extras.getString("code");
			if (str != null && str.length() != 0) {
				code = str;
			}
			
		}
		
		System.out.println("resp1onse : PsShellService.onStartCommand()");
		handler.postAtTime(r, 1000);

		return START_STICKY;
	}

	private void ps(String code, Runtime runtime) {
		
		BufferedReader br = null;
		try {
			Process p = runtime.exec(code);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String result = null;
			while ((result = br.readLine()) != null) {
				result = result.substring(result.lastIndexOf(" "), result.length());
				System.out.println("resp1onse : " + result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(br);
		}
	}

	void close(Closeable closeable) {
		if (closeable != null) {

			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	
	@Override
	public void onDestroy() {
		
		System.out.println("resp1onse : PsShellService.onDestroy()");
		super.onDestroy();
		isStop = true;
		handler.removeCallbacks(r);
		
		
	}

}
