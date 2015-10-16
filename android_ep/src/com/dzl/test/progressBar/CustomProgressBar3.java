package com.dzl.test.progressBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
//import android.view.RemotableViewMethod;
import android.view.View;
import android.widget.RemoteViews.RemoteView;

import com.dzl.test.R;

@RemoteView
public class CustomProgressBar3 extends View {
	private static final int MAX_LEVEL = 10000;

	int mMinWidth;
	int mMaxWidth;
	int mMinHeight;
	int mMaxHeight;

	private int mProgress;
	private int mSecondaryProgress;
	private int mMax;

	private Drawable mProgressDrawable;
	private RefreshProgressRunnable mRefreshProgressRunnable;
	private long mUiThreadId;

	private final int R_id_progress = R.id.progress;
	private final int R_id_secondaryProgress = R.id.secondaryProgress;

	public CustomProgressBar3(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	
		System.out.println("resp1onse CustomProgressBar3: public CustomProgressBar3(Context context, AttributeSet attrs) { start constructor ");
		mUiThreadId = Thread.currentThread().getId();
		mMax = 100;
		mProgress = 0;
		mSecondaryProgress = 0;
		mMinWidth = 24;
		mMaxWidth = 48;
		mMinHeight = 24;
		mMaxHeight = 48;

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar);

		Drawable drawable = a.getDrawable(R.styleable.CustomProgressBar_progressDrawable);
		if (drawable != null) {
			setProgressDrawable(drawable);
		}
//		setProgress(a.getInt(R.styleable.CustomProgressBar_progress, mProgress), false);
//		setSecondaryProgress(a.getInt(R.styleable.CustomProgressBar_secondaryProgress, mSecondaryProgress));

		a.recycle();
		System.out.println("resp1onse CustomProgressBar3: public CustomProgressBar3(Context context, AttributeSet attrs) { end constructor ");
	}

	void setProgressDrawable(Drawable d) {
		System.out.println("resp1onse CustomProgressBar3: public void setProgressDrawable(Drawable d) { start void ");
		if (d != null) {
			d.setCallback(this);

			// Make sure the ProgressBar is always tall enough
			int drawableHeight = d.getMinimumHeight();
			if (mMaxHeight < drawableHeight) {
				mMaxHeight = drawableHeight;
				requestLayout();
			}
		}
		mProgressDrawable = d;
		// postInvalidate();
		System.out.println("resp1onse CustomProgressBar3: public void setProgressDrawable(Drawable d) { end void ");
	}

	@Override
	protected boolean verifyDrawable(Drawable who) {
		return who == mProgressDrawable;
	}

	private class RefreshProgressRunnable implements Runnable {

		private int mId;
		private int mProgress;
		private boolean mFromUser;

		RefreshProgressRunnable(int id, int progress, boolean fromUser) {
			mId = id;
			mProgress = progress;
			mFromUser = fromUser;
		}

		public void run() {
			System.out.println("resp1onse CustomProgressBar3: public void run() { start void ");
			doRefreshProgress(mId, mProgress, mFromUser);
			// Put ourselves back in the cache when we are done
			mRefreshProgressRunnable = this;
			System.out.println("resp1onse CustomProgressBar3: public void run() { end void ");
		}

		public void update(int id, int progress, boolean fromUser) {
			System.out.println("resp1onse CustomProgressBar3: public void setup(int id, int progress, boolean fromUser) { start void ");
			mId = id;
			mProgress = progress;
			mFromUser = fromUser;
			System.out.println("resp1onse CustomProgressBar3: public void setup(int id, int progress, boolean fromUser) { end void ");
		}

	}

	private synchronized void doRefreshProgress(int id, int progress, boolean fromUser) {
		System.out
				.println("resp1onse CustomProgressBar3: private synchronized void doRefreshProgress(int id, int progress, boolean fromUser) { start void id = "
						+ id);
		float scale = mMax > 0 ? (float) progress / (float) mMax : 0;
		final Drawable d = mProgressDrawable;
		if (d != null) {
			Drawable progressDrawable = null;

			if (d instanceof LayerDrawable) {
				progressDrawable = ((LayerDrawable) d).findDrawableByLayerId(id);
			}

			final int level = (int) (scale * MAX_LEVEL);
			(progressDrawable != null ? progressDrawable : d).setLevel(level);
		} else {
			invalidate();
		}

		if (id == R_id_progress) {
			// onProgressRefresh(scale, fromUser);
		}
		System.out.println("resp1onse CustomProgressBar3: private synchronized void doRefreshProgress(int id, int progress, boolean fromUser) { end void ");
	}

	private synchronized void refreshProgress(int id, int progress, boolean fromUser) {
		System.out.println("resp1onse CustomProgressBar3: private synchronized void refreshProgress(int id, int progress, boolean fromUser) { start void ");
		if (mUiThreadId == Thread.currentThread().getId()) {
			doRefreshProgress(id, progress, fromUser);
		} else {
			RefreshProgressRunnable r;
			if (mRefreshProgressRunnable != null) {
				// Use cached RefreshProgressRunnable if available
				r = mRefreshProgressRunnable;
				// Uncache it
				mRefreshProgressRunnable = null;
				r.update(id, progress, fromUser);
			} else {
				// Make a new one
				r = new RefreshProgressRunnable(id, progress, fromUser);
			}
			post(r);
		}
		System.out.println("resp1onse CustomProgressBar3: private synchronized void refreshProgress(int id, int progress, boolean fromUser) { end void ");
	}

	synchronized void setProgress(int progress, boolean fromUser) {
		System.out.println("resp1onse CustomProgressBar3: synchronized void setProgress(int progress, boolean fromUser) { start void ");

		if (progress < 0) {
			progress = 0;
		}

		if (progress > mMax) {
			progress = mMax;
		}

		if (progress != mProgress) {
			mProgress = progress;
			refreshProgress(R_id_progress, mProgress, fromUser);
		}
		System.out.println("resp1onse CustomProgressBar3: synchronized void setProgress(int progress, boolean fromUser) { end void ");
	}

	public synchronized void setSecondaryProgress(int secondaryProgress) {
		System.out.println("resp1onse CustomProgressBar3: public synchronized void setSecondaryProgress(int secondaryProgress) { start void ");

		if (secondaryProgress < 0) {
			secondaryProgress = 0;
		}

		if (secondaryProgress > mMax) {
			secondaryProgress = mMax;
		}

		if (secondaryProgress != mSecondaryProgress) {
			mSecondaryProgress = secondaryProgress;
			refreshProgress(R_id_secondaryProgress, mSecondaryProgress, false);
		}
		System.out.println("resp1onse CustomProgressBar3: public synchronized void setSecondaryProgress(int secondaryProgress) { end void ");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		System.out.println("resp1onse CustomProgressBar3: protected void onSizeChanged(int w, int h, int oldw, int oldh) { start void ");
		// onDraw will translate the canvas so we draw starting at 0,0
		int right = w - getPaddingRight() - getPaddingLeft();
		int bottom = h - getPaddingBottom() - getPaddingTop();

		mProgressDrawable.setBounds(0, 0, right, bottom);
		System.out.println("resp1onse CustomProgressBar3: protected void onSizeChanged(int w, int h, int oldw, int oldh) { end void ");
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		System.out.println("resp1onse CustomProgressBar3: protected synchronized void onDraw(Canvas canvas) { start void ");
		super.onDraw(canvas);

		Drawable d = mProgressDrawable;
		if (d != null) {
			canvas.save();
			canvas.translate(getPaddingLeft(), getPaddingTop());
			d.draw(canvas);
			canvas.restore();
		}
		System.out.println("resp1onse CustomProgressBar3: protected synchronized void onDraw(Canvas canvas) { end void ");
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("resp1onse CustomProgressBar3: protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { start void ");
		Drawable d = mProgressDrawable;

		int dw = 0;
		int dh = 0;
		if (d != null) {
			dw = Math.max(mMinWidth, Math.min(mMaxWidth, d.getIntrinsicWidth()));
			dh = Math.max(mMinHeight, Math.min(mMaxHeight, d.getIntrinsicHeight()));
		}
		dw += getPaddingLeft() + getPaddingRight();
		dh += getPaddingTop() + getPaddingBottom();

		setMeasuredDimension(resolveSize(dw, widthMeasureSpec), resolveSize(dh, heightMeasureSpec));
		System.out.println("resp1onse CustomProgressBar3: protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { end void ");
	}

	public synchronized int getProgress() {
		return mProgress;
	}

}
