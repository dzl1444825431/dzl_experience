package com.dzl.test.decorview;

import com.dzl.test.BaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

public class DecorViewActivity extends BaseActivity {

	int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(this);
		setContentView(textView);

		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				add(DecorViewActivity.this);
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	// 添加view 到activity 并添加动画显示出来
	private void add(Activity activity) {

		TextView textView = new TextView(this);
		textView.setText(
				"aaaaaaaaaaaa........................................................aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
						+ ++i);
		textView.setGravity(Gravity.CENTER);
		textView.setBackgroundColor(0x99ff0000);
		LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
		textView.setLayoutParams(params);

		FrameLayout contentView = (FrameLayout) activity.findViewById(android.R.id.content);
		contentView.addView(textView, contentView.getChildCount());
		textView.setOnClickListener(null);

		AnimationSet set = new AnimationSet(true);

		TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
				0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);

		set.addAnimation(translate);
		translate.setDuration(1000);
		set.setFillAfter(true);

		textView.offsetTopAndBottom(-textView.getHeight() / 2);

		textView.startAnimation(set);

	}

}
