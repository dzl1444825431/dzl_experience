package com.dzl.test.password;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.dzl.test.R;

public class PasswordActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_forget_password);
		
	}

}
