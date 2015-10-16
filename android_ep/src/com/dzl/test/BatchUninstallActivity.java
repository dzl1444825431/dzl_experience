package com.dzl.test;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;

public class BatchUninstallActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("pm uninstall com.apprunning.dzl");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
