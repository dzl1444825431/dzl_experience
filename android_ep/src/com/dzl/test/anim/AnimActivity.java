package com.dzl.test.anim;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;


public class AnimActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.animactivity_main);
		final TextView tv = (TextView) findViewById(R.id.textView1);
		
		final Animation mAnimationRight = AnimationUtils.loadAnimation(
                this, R.anim.scale);
//        tv.setAnimation(mAnimationRight);
        
        tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tv.startAnimation(mAnimationRight);
			}
		});
		
	}

}
