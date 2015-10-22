package com.dzl.test.volley;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class VolleyActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_volley);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startVollery();
			}
		});
		startVollery();
	}

	
	private void startVollery() {
		
	}

}
