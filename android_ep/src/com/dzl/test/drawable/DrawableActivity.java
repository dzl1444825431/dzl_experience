package com.dzl.test.drawable;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class DrawableActivity extends BaseActivity {
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			default:
				break;
			}
		};
	};
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_drawable);
		
		View view1 = findViewById(R.id.view1);
		View view2 = findViewById(R.id.view2);
		View view3 = findViewById(R.id.view3);
		
		final ColorDrawable colorDrawable1 = new ColorDrawable();
		final ColorDrawable colorDrawable2 = new ColorDrawable(0xffff0000);
		final ColorDrawable colorDrawable3 = new ColorDrawable();
		
		view1.setBackground(colorDrawable1);
		view2.setBackground(colorDrawable2);
		view3.setBackground(colorDrawable3);
		
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				colorDrawable1.setColor(0xff18b4ed);
				colorDrawable3.setColor(0xff18b4ed);
				colorDrawable3.setAlpha(25);
				
				System.out.println("resp1onse : " + colorDrawable1.getOpacity());
				System.out.println("resp1onse : " + colorDrawable2.getOpacity());
				System.out.println("resp1onse : " + colorDrawable3.getOpacity());
				
			}
		}, 5000);
		
	}

}
