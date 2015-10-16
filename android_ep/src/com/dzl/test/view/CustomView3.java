//package com.dzl.test.view;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Paint.FontMetricsInt;
//import android.graphics.Rect;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.util.AttributeSet;
//import android.view.View;
//
//import com.dzl.test.R;
//
//public class CustomView3 extends View {
//
//	private int textColor;
//	private float textSize;
//	private String text;
//	private Paint paint;
//	float degrees = 0;
//	private Drawable drawable;
//	int mLevel = 0;
//	private int pleft;
//	private int ptop;
//	private int textWidth;
//	private int textHeight;
//
//	public CustomView3(Context context) {
//		super(context);
//
//	}
//
//	public CustomView3(Context context, AttributeSet attrs, int defStyleAttr) {
//		super(context, attrs, defStyleAttr);
//	}
//
//	public CustomView3(Context context, AttributeSet attrs) {
//		super(context, attrs);
//
//		// Resources res = getResources();
//		// DisplayMetrics displayMetrics = res.getDisplayMetrics();
//		// //System.out.println("resp1one : getDisplayMetrics().density = " +
//		// displayMetrics.density + " " + displayMetrics.densityDpi);
//		// //System.out.println("resp1one : displayMetrics = " + displayMetrics);
//
//		TypedArray typedArray = context.obtainStyledAttributes(attrs,
//				R.styleable.customText);
//
//		textColor = typedArray.getColor(R.styleable.customText_textColor, 0);
//		textSize = typedArray.getDimension(R.styleable.customText_textSize, 16);
//		text = typedArray.getString(R.styleable.customText_text);
//
//		drawable = typedArray.getDrawable(R.styleable.customText_drawable);
//
//		if (text == null) {
//			text = "";
//		}
//
//		//System.out.println("resp1one : textColor = " + textColor + " textSize = " + textSize);
//
//		typedArray.recycle();
//		paint = new Paint();
//		paint.setColor(textColor);
//		paint.setTextSize(textSize);
//
//		//System.out.println("resp1one : drawable == null " + (drawable == null));
//
//		drawable.setCallback(this);
//		// drawable.setLayoutDirection(getLayoutDirection());
//		if (drawable.isStateful()) {
//			drawable.setState(getDrawableState());
//		}
//		drawable.setVisible(getVisibility() == VISIBLE, false);
//		// drawable.setLevel(mLevel );
//
//		Rect rect = new Rect();
//		paint.getTextBounds(text, 0, text.length(), rect);
//		textWidth = rect.width();
//		textHeight = rect.height();
//
//		FontMetricsInt fontMetrics = paint.getFontMetricsInt();
//		textHeight = fontMetrics.descent - fontMetrics.ascent;
//		//System.out.println("resp1one : textWidth = " + textWidth + "  textHeight = " + textHeight);
//
//		// 3. 精确计算文字宽高
//		// int textWidth = getTextWidth(paint, text);
//
//		getTextWidthAndHeight(paint, text);
//		//System.out.println("resp1one : textWidth 3 = " + textWidth + "  textHeight = " + textHeight);
//
//	}
//
//	public void getTextWidthAndHeight(Paint paint, String str) {
//		textWidth = 0;
//		if (str != null && str.length() > 0) {
//			int len = str.length();
//			float[] widths = new float[len];
//			// float[] heights = new float[len];
//			paint.getTextWidths(str, widths);
//
//			for (int j = 0; j < len; j++) {
//				textWidth += (int) Math.ceil(widths[j]);
//				// textHeight = (int) Math.max(textHeight, heights[j]);
//			}
//		}
//	}
//
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//		int w = drawable.getIntrinsicWidth();
//		int h = drawable.getIntrinsicHeight();
//		//System.out.println("resp1one : drawable.getIntrinsicWidth = " + w + " " + h);
//
//		if (w <= 0)
//			w = 1;
//		if (h <= 0)
//			h = 1;
//
//		w += textWidth;
//		h += textHeight;
//
//		pleft = getPaddingLeft();
//		int pright = getPaddingRight();
//		ptop = getPaddingTop();
//		int pbottom = getPaddingBottom();
//
//		final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
//		final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
//
//		//System.out.println("resp1one : widthSpecMode = " + widthSpecMode	+ " heightSpecMode = " + heightSpecMode);
//
//		w += pleft + pright;
//		h += ptop + pbottom;
//
//		int suggestedMinimumWidth = getSuggestedMinimumWidth();
//		w = Math.max(w, suggestedMinimumWidth);
//		int suggestedMinimumHeight = getSuggestedMinimumHeight();
//		h = Math.max(h, suggestedMinimumHeight);
//
//		//System.out.println("resp1one : suggestedMinimumWidth = "	+ suggestedMinimumWidth + " " + suggestedMinimumHeight);
//
//		int heightSize = 0;
//		int widthSize = 0;
//
//		widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
//		heightSize = resolveSizeAndState(h, heightMeasureSpec, 0);
//
//		//System.out.println("resp1one : widthSize = " + widthSize	+ " heightSize = " + heightSize);
//
//		setMeasuredDimension(widthSize, heightSize);
//	}
//
//	@Override
//	protected void onDraw(Canvas canvas) {
//		// super.onDraw(canvas);
//
//		// Paint paint = new Paint();
//		// int color = 0xffcccccc;
//		// paint.setColor(color);
//		// canvas.drawRect(20, 20, 80, 80, paint);
//		//
//		// color = 0xffff0000;
//		// paint.setColor(color);
//		// canvas.drawRect(90, 90, 95, 95, paint);
//		// paint.setTextSize(40);
//		// canvas.drawText("测试111", 100, 100, paint);
//
//		// Paint paint = new Paint();
//		// paint.setColor(textColor);
//		// paint.setTextSize(textSize);
//		// // canvas.drawText(text, 10, 10, paint);
//		// canvas.drawText(text, 0, text.length(), 30, 30, paint);
//		// canvas.drawPaint(paint);
//
//		// canvas.save();
//		// canvas.translate(25, 25);
//		// canvas.rotate(degrees++ , 50, 50);
//		// canvas.drawRect(0, 0, 100, 100, paint);
//		//
//		// canvas.restore();
//		// invalidate();
//
//		// if (drawable != null) {
//		// drawable.jumpToCurrentState();
//		// int saveCount = canvas.getSaveCount();
//		// canvas.save();
//		// canvas.translate(100, 100);
//		// drawable.draw(canvas);
//		// canvas.restoreToCount(saveCount);
//		// }
//		// //System.out.println("resp1one : CustomView3.onDraw()");
//		//
//		// canvas.drawColor(0xffff0000);
//		// paint.setColor(0xffff5a1b);
//		// canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
//		// Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
//		// // canvas.drawBitmap(bitmap , pleft, ptop, paint);
//		// paint.setColor(textColor);
//
//		drawable.setBounds(getWidth() - drawable.getIntrinsicWidth(),
//				getHeight() - drawable.getIntrinsicHeight(), getWidth(),
//				getHeight());
//		drawable.draw(canvas);
//
//		//
//		//
//		//
//		int x2 = (getWidth() - textWidth) / 2;
//		int y2 = getHeight() - textHeight;
//		if (y2 <= 0) {
//			y2 = getHeight();
//		} else {
//			y2 = (getHeight() + textHeight) / 2;
//		}
//
//		x2 = x2 >= 0 ? x2 : 0;
//		// y2 = y2 >= 0 ? y2 : getHeight();
//
//		// canvas.drawText(text, x2, y2, paint);
//		// canvas.drawText(text, x, y, paint);
//
//		Rect targetRect = new Rect(0, 0, getWidth(), getHeight());
//		FontMetricsInt fontMetrics = paint.getFontMetricsInt();
//		//System.out.println("resp1one : " + fontMetrics);
//		// int baseline = 0 + (getHeight() - 0 - fontMetrics.bottom +
//		// fontMetrics.top) / 2 - fontMetrics.top;
//		int baseline = targetRect.top
//				+ (targetRect.bottom - targetRect.top - fontMetrics.bottom + fontMetrics.top)
//				/ 2 - fontMetrics.top;
//		// 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
//		paint.setTextAlign(Paint.Align.CENTER);
//		canvas.drawText(text, targetRect.centerX(), baseline, paint);
//		//System.out.println("resp1one : x2 = " + x2 + " " + y2
////				+ " getHeight() = " + getHeight() + " baseline = " + baseline);
//		//
//		//
//		// int baseline2 = targetRect.top +
//		// (targetRect.bottom - targetRect.top - fontMetrics.bottom +
//		// fontMetrics.top) / 2
//		// - fontMetrics.top;
//		//
//		// //System.out.println("resp1one : " + targetRect);
//		// //System.out.println("resp1one : centerX = " + targetRect.centerX() +
//		// " centerY = " + targetRect.centerY());
//
//		drawStoke(canvas);
//	}
//
//	private void drawStoke(Canvas canvas) {
//		// paint.setColor(0xffff0000);
//		// canvas.drawLine(0, 0, getWidth() - 1, 0, paint);
//		// canvas.drawLine(0, 0, 0, getHeight() - 1, paint);
//		// canvas.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1,
//		// paint);
//		// canvas.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1,
//		// paint);
//
//		Rect rec = canvas.getClipBounds();
//		rec.bottom--;
//		rec.right--;
//		Paint paint = new Paint();
//		paint.setColor(0xffff0000);
//		paint.setStyle(Paint.Style.STROKE);
//		canvas.drawRect(rec, paint);
//
//	}
//
//}
