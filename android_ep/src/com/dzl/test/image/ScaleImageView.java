package com.dzl.test.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 根据分辨率去缩放图片
 * 
 * @author lxl
 * 
 */
public class ScaleImageView extends ImageView /*implements ViewTreeObserver.OnGlobalLayoutListener*/ {
	private final Matrix baseMatrix = new Matrix();
	private final Matrix drawMatrix = new Matrix();
	private final Matrix suppMatrix = new Matrix();
	private boolean isfirst = true;

	public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public ScaleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private int screenWidth;

	public ScaleImageView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		WindowManager m = ((Activity) context).getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		screenWidth = d.getWidth();
	}

	/**
	 * 依据图片宽高比例，设置图像初始缩放等级和位置
	 */
	public void configPosition() {
		isfirst = false;
		super.setScaleType(ScaleType.MATRIX);
		float scale = 1.0f;
		if (screenWidth == 480 || screenWidth == 320 || screenWidth == 240) {// hdpi和mdpi、ldpi
			scale = 0.8f;
		} else {
			return;
		}
		Drawable d = getDrawable();
		if (d == null) {
			return;
		}
		float viewWidth = getWidth();
		float viewHeight = getHeight();
		// background的区域
		if (this.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.getLayoutParams();
			viewWidth = (int) (viewWidth * scale);
			viewHeight = (int) (viewHeight * scale);
			params.width = (int) viewWidth;
			params.height = (int) viewHeight;
			this.setLayoutParams(params);
		} else if (this.getLayoutParams() instanceof LinearLayout.LayoutParams) {
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) this.getLayoutParams();
			viewWidth = (int) (viewWidth * scale);
			viewHeight = (int) (viewHeight * scale);
			params.width = (int) viewWidth;
			params.height = (int) viewHeight;
			this.setLayoutParams(params);
		}
		// //src的区域
		final int drawableWidth = d.getIntrinsicWidth();
		final int drawableHeight = d.getIntrinsicHeight();
		baseMatrix.reset();
		// 缩放
		baseMatrix.postScale(scale, scale);
		// 移动居中
		baseMatrix.postTranslate((viewWidth - drawableWidth * scale) / 2, (viewHeight - drawableHeight * scale) / 2);
		resetMatrix();
	}

	/**
	 * Resets the Matrix back to FIT_CENTER, and then displays it.s
	 */
	private void resetMatrix() {
		if (suppMatrix == null) {
			return;
		}
		suppMatrix.reset();
		setImageMatrix(getDisplayMatrix());
	}

	protected Matrix getDisplayMatrix() {
		drawMatrix.set(baseMatrix);
		drawMatrix.postConcat(suppMatrix);
		return drawMatrix;
	}

//	@Override
//	public void onGlobalLayout() {
//		// 调整视图位置
//		if (isfirst) {
//			configPosition();
//		}
//	}
//
//	@Override
//	protected void onAttachedToWindow() {
//		super.onAttachedToWindow();
//		getViewTreeObserver().addOnGlobalLayoutListener(this);
//	}
//
//	@Override
//	protected void onDetachedFromWindow() {
//		super.onDetachedFromWindow();
//		getViewTreeObserver().removeGlobalOnLayoutListener(this);
//	}
}
