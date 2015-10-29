package com.dzl.test.progressBar;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.dzl.test.R;

public class ProgressBarLoadingActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progressbar_loading);
		
		
		Dialog dialog = new Dialog(this, R.style.LoadingDialog);//LoadingDialog 设置dialog样式
		ProgressBar progressBar = new ProgressBar(this);
		dialog.setContentView(progressBar);
		dialog.show();
		
	}

}
