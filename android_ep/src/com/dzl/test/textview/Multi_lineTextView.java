package com.dzl.test.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class Multi_lineTextView extends View {

	private TextPaint tp;
	private StaticLayout staticLayout;

	public Multi_lineTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Multi_lineTextView(Context context) {
		super(context);
		init();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(staticLayout.getWidth(), staticLayout.getHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		staticLayout.draw(canvas);
		canvas.restore();
	}

	private void init() {
		tp = new TextPaint();
		tp.setColor(Color.BLUE);
		tp.setStyle(Style.FILL);
		tp.setTextSize(50);
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		String message = "paint,draw paint指用颜色画,如油画颜料、水彩或者水墨画,而draw 通常指用铅笔、钢笔或者粉笔画,后者一般并不涂上颜料。两动词的相应名词分别为p";
		staticLayout = new StaticLayout(message, tp, metrics.widthPixels , Alignment.ALIGN_NORMAL, 1.0f,
				0.0f, false);
		
	}

}
