package com.dzl.test.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.dzl.test.R;

public class Multi_lineTextViewChange extends View {

	TextPaint textPaint = null;
	StaticLayout staticLayout = null;
	Paint paint = null;
	int width = 100;
	int height = 0;
	String txt = null;
	boolean running = false;
	private DisplayMetrics displayMetrics;

	public Multi_lineTextViewChange(Context context) {
		super(context);
		init();
	}

	private void init() {
		textPaint = new TextPaint();
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(12);
		txt = getResources().getString(R.string.my_text);
		staticLayout = new StaticLayout(txt, textPaint, width, Alignment.ALIGN_NORMAL, 1, 0, false);
		height = staticLayout.getHeight();
		paint = new Paint();
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.RED);
		displayMetrics = getResources().getDisplayMetrics();
	}

	public Multi_lineTextViewChange(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		int w = getWidth();
		if (w > 0) {
			width = w;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(displayMetrics.widthPixels, staticLayout.getHeight());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			running = !running;
			if (running) {
				new Thread() {
					public void run() {
						while (running) {
							width++;
							staticLayout = new StaticLayout(txt, textPaint, width, Alignment.ALIGN_NORMAL, 1, 0, false);
							height = staticLayout.getHeight();
							postInvalidate();
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if (width >= 300) {
								width = 50;
							}
						}
					};
				}.start();
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.translate(50, 50);
		staticLayout.draw(canvas);
		canvas.drawRect(0, 0, width, height, paint);
		super.onDraw(canvas);

	}

}
