package com.dzl.test.util;

import android.os.Bundle;

import com.dzl.test.BaseActivity;

public class ClassMethodFieldUtilActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ReflectUtil_generate.main1(null);
			}
		}).run();

	}

	
}
