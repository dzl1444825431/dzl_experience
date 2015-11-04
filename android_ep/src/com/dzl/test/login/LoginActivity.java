package com.dzl.test.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;
import com.dzl.test.password.PasswordActivity;
import com.dzl.test.register.RegisterActivity;

public class LoginActivity extends BaseActivity {
	
	private EditText editText1;
	private EditText editText2;
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView5;
	private TextView textView6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_login);
		
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView5 = (TextView) findViewById(R.id.textView5);
		textView6 = (TextView) findViewById(R.id.textView6);
		
		setOnClickListener(textView5);
		setOnClickListener(textView6);
		
		
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		
		Intent intent;
		switch (v.getId()) {

		
		case R.id.textView5:
			intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.textView6:
			intent = new Intent(this, PasswordActivity.class);
			startActivity(intent);
			break;
		case R.id.view0:
			break;
		default:
			break;
		}
		
	}

}
