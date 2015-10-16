package com.dzl.test.util;

import android.app.Activity;
import android.os.Bundle;

public class ClassMethodFieldUtilActivity extends Activity {

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
