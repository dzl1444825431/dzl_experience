package com.dzl.test.md5;

import android.app.Activity;
import android.os.Bundle;

public class Md5CopyFileActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Md5Util.md5ForFileName();
			}
		}).start();
	}

}


