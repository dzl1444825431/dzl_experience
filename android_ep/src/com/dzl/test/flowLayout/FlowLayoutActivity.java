package com.dzl.test.flowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class FlowLayoutActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_flowlayout);
		
		initView();
		
	}
	
	private void initView() {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		
		
		ArrayList<String> keys = new ArrayList<String>();
		//[众划算, 微信, QQ, 手机百度, 陌陌, 切水果]
		keys.add("众划算");
		keys.add("微信");
		keys.add("QQ");
		keys.add("手机百度");
		keys.add("陌陌");
		keys.add("切水果");
		
		FlowLayout flowLayout = (FlowLayout) findViewById(R.id.fl_keywords);
		for (int i = 0; i < keys.size(); i++) {
			TextView textView = new TextView(this);
			textView.setPadding(30, 15, 30, 10);
			textView.setBackgroundResource(R.drawable.box_search_item);
			textView.setOnClickListener(this);
			textView.setText(keys.get(i));
			FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(
					FlowLayout.LayoutParams.WRAP_CONTENT, 
					FlowLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(22, 0, 22, 0);
			textView.setLayoutParams(params);
			flowLayout.addView(textView);
		}
//		android:layout_marginLeft="15dp"
//		        android:layout_marginTop="15dp"
//		        android:background="@drawable/box_search_item"
//		        android:paddingBottom="10dp"
//		        android:paddingLeft="20dp"
//		        android:paddingRight="20dp"
//		        android:paddingTop="10dp"
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

}
