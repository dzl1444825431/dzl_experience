package com.dzl.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TestServiceActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		startService(new Intent(this,CheckAppRunningService.class));
	}

}
