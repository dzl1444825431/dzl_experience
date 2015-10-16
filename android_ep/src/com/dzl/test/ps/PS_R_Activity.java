package com.dzl.test.ps;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;

public class PS_R_Activity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i = 50;
				Runtime runtime = Runtime.getRuntime();
				while(i-- > 0){
					System.out.println("resp1onse : i = " + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ps("ps", runtime);
				}
			}
		}).start();
	}
	
private int ps(String code, Runtime runtime) {
		
		BufferedReader br = null;
		try {
			Process p = runtime.exec(code);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String result = null;
			while ((result = br.readLine()) != null) {
				
				String[] array = result.trim().split("\\s+");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(br);
		}
		
		
		return -2;
	}

	/**
	 * 关闭流
	 */
	void close(Closeable closeable) {
		if (closeable != null) {
	
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		}
	}


}
