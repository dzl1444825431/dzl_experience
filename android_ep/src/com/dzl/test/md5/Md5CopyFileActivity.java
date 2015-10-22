package com.dzl.test.md5;

import android.os.Bundle;

import com.dzl.test.BaseActivity;

public class Md5CopyFileActivity extends BaseActivity {
	
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


