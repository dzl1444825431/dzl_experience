package com.dzl.test.progressBar;

import android.app.Activity;
import android.os.Bundle;

import com.dzl.test.R;

public class ProgressBarActivity extends Activity {
	
	private com.dzl.test.progressBar.CustomProgressBar3 customProgressBar3;
	private int progress = 0;
	private boolean isDestroy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_progressbar);
		
		startProgress();
	}

	void startProgress() {
		customProgressBar3 = (CustomProgressBar3) findViewById(R.id.progressBar3);
		progress  = customProgressBar3.getProgress();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while (!isDestroy) {
					customProgressBar3.setProgress(++progress, false);
					if (progress >= 100) {
						progress = 0;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		isDestroy = true;
	}

}
