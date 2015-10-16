package com.dzl.test.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import com.dzl.test.R;

@SuppressLint("NewApi")
public class AnimatorActivity extends Activity {
	
	private View tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.animator);
		tv = findViewById(R.id.textView1);
		
		final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(
        		tv, View.SCALE_X, new float[] { 0.0F, 1.1F }))
        		.with(ObjectAnimator.ofFloat(
        				tv, View.SCALE_Y, new float[] { 0.0F, 1.1F }));
        animatorSet.setDuration(5000L);
        animatorSet.setInterpolator(new OvershootInterpolator(5.2F));
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter()
        {
          public void onAnimationEnd(Animator animator)
          {
            System.out.println("respone : " + "end");
//            animatorSet.start();
          }
        });
        animatorSet.start();
	}

}
