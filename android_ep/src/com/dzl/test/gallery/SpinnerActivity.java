package com.dzl.test.gallery;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class SpinnerActivity extends BaseActivity {
	/** Called when the activity is first created. */
	private List<String> list = new ArrayList<String>();
	private TextView myTextView;
	private Spinner mySpinner;
	private ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);
		
		list.add("北京");
		list.add("上海");
		list.add("深圳");
		list.add("福州");
		list.add("厦门.");
		list.add("厦门..");
		list.add("厦门...");
		list.add("厦门....");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门01");
		list.add("厦门a");
		list.add("厦门b");
		list.add("厦门c");
		list.add("厦门d");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门11");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门-");
		list.add("厦门+");
		list.add("厦门*");
		list.add("厦门");
		list.add("厦门");
		list.add("厦门9");
		list.add("厦门8");
		list.add("厦门7");
		list.add("厦门6");
		list.add("厦门5");
		list.add("厦门4");
		list.add("厦门3");
		list.add("厦门2");
		list.add("厦门1");
		list.add("厦门0");
		myTextView = (TextView) findViewById(R.id.TextView_city);
		mySpinner = (Spinner) findViewById(R.id.Spinner_city);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		mySpinner.setAdapter(adapter);
		
		mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				/* 将所选mySpinner 的值带入myTextView 中 */
				myTextView.setText("您选择的是：" + adapter.getItem(arg2));
				/* 将mySpinner 显示 */
				arg0.setVisibility(View.VISIBLE);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				myTextView.setText("NONE");
				arg0.setVisibility(View.VISIBLE);
			}
		});
		/* 下拉菜单弹出的内容选项触屏事件处理 */
		mySpinner.setOnTouchListener(new Spinner.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				/**
                 * 
                 */
				return false;
			}
		});
		/* 下拉菜单弹出的内容选项焦点改变事件处理 */
		mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {

			}
		});
	}
}
