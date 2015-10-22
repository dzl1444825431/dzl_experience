package com.dzl.test;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;


public class BaseActivity extends Activity implements OnClickListener {
	
	protected static final int G = View.GONE;
	protected static final int V = View.VISIBLE;

	protected void setText(TextView v, CharSequence text) {
		if (v == null) {
			return;
		}
		v.setText(text);
	}
	
	protected void setOnClickListener(View v) {
		if (v == null) {
			return;
		}
		v.setOnClickListener(this);
	}

	protected void setViewVisible(View v, int visibility) {
		if (v == null) {
			return;
		}
		v.setVisibility(visibility);
	}

	protected void setImageViewSource(ImageView v, int resId, View parent) {
		if (v == null) {
			return;
		}
		if (parent != null) {
			parent.setVisibility(V);
		}
		v.setImageResource(resId);
	}

	@Override
	public void onClick(View v) {

	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

}
