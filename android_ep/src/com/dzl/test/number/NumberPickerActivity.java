package com.dzl.test.number;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class NumberPickerActivity extends BaseActivity {
	
	private NumberPicker picker;
	private String[] displayedValues = {
			"aa1",
			"aa2",
			"aa3",
			"aa4",
			"aa5",
			"aa6",
//			"aa7",
			};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_numberpicker);
		picker = (NumberPicker) findViewById(R.id.number);
		
		picker.setMinValue(0);
		picker.setMaxValue(5);
		
//		picker.setWrapSelectorWheel(false);
		picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		picker.setDisplayedValues(displayedValues);
		picker.setValue(2);
		picker.setOnValueChangedListener(new OnValueChangeListener() {
			

			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				System.out.println("resp1one : oldVal = " + oldVal +" newVal = " + newVal);
			}
		});
//		TextView child;
//		child = new TextView(this);
//		child.setText("我要要 ");
//		picker.addView(child, 1);
	}

}
