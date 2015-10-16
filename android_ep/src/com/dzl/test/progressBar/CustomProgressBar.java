package com.dzl.test.progressBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.dzl.test.R;

public class CustomProgressBar extends View {

	private Drawable mIndeterminateDrawable;

	private boolean mShouldStartAnimationDrawable;

	public CustomProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);

		final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar);

		final Drawable indeterminateDrawable = a.getDrawable(R.styleable.CustomProgressBar_indeterminateDrawable);
		if (indeterminateDrawable != null) {
			mIndeterminateDrawable = indeterminateDrawable;
			mIndeterminateDrawable.jumpToCurrentState();
			mIndeterminateDrawable.setCallback(this);
		}
		a.recycle();

		setAccessibility();
	}

	@SuppressLint("NewApi")
	private void setAccessibility() {

		if (getImportantForAccessibility() == View.IMPORTANT_FOR_ACCESSIBILITY_AUTO) {
			setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_YES);
		}
	}

	@Override
	protected boolean verifyDrawable(Drawable who) {
		return true;
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int wm = MeasureSpec.getMode(widthMeasureSpec);
		int hm = MeasureSpec.getMode(heightMeasureSpec);
		int w = MeasureSpec.getSize(widthMeasureSpec);
		int h = MeasureSpec.getSize(heightMeasureSpec);

		if (wm == MeasureSpec.AT_MOST || hm == MeasureSpec.AT_MOST) {
			w = mIndeterminateDrawable.getIntrinsicWidth();
			h = mIndeterminateDrawable.getIntrinsicHeight();
		}

		mIndeterminateDrawable.setBounds(0, 0, w - getPaddingLeft() - getPaddingRight(), h - getPaddingTop()
				- getPaddingBottom());

		setMeasuredDimension(w, h);
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		final Drawable d = mIndeterminateDrawable;
		if (d != null) {
			final int saveCount = canvas.save();

			canvas.translate(getPaddingLeft(), getPaddingTop());

			d.draw(canvas);
			canvas.restoreToCount(saveCount);
			if (!mShouldStartAnimationDrawable && d instanceof Animatable) {
				((Animatable) d).start();
				mShouldStartAnimationDrawable = true;
			}
		}
	}

}
