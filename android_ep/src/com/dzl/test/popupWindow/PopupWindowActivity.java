package com.dzl.test.popupWindow;


import com.dzl.test.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;

public class PopupWindowActivity extends Activity {
	MyPopupWindow myPopupWindow;
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		myPopupWindow = new MyPopupWindow(this);
		button = new Button(this);
		button.setText("popupWindow Show");
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myPopupWindow.show(button);
			}
		});
		
		setContentView(button);
		
		
	}
	
	
	class MyPopupWindow extends PopupWindow {
		
		public MyPopupWindow(Context context){
			super(context);
			
			setFocusable(true);
			setTouchable(true);
			setOutsideTouchable(true);
			setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
			setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
			setContentView(LayoutInflater.from(context).inflate(R.layout.activity_main, null));
			
		}
		
		void show(View anchor){
			setBackgroundDrawable(new ColorDrawable(Color.RED));
			showAtLocation(anchor, Gravity.NO_GRAVITY, 300, 500);
		}
	}
	
	
}
