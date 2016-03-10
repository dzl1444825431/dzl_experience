package com.dzl.test.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 进度提示框
 * @author dzl 2016年3月10日
 */
public class LoadingMsgDialog extends Dialog {

	private static int WIDTH = 180; // 默认宽度
	private static int HEIGHT = 180;// 默认高度
	
	private int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
	private int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
	
	private TextView tv_msg;
	private GradientDrawable drawable;
	private ProgressBar progressbar;

	public LoadingMsgDialog(Context context, float dimAmount, int style) {
		this(context, WIDTH, HEIGHT, dimAmount, style);
	}

	/**
	 * theme like
	 * 	<style name="loading_msg_dialog" parent="@android:style/Theme.Dialog">
	        <item name="android:windowBackground">@android:color/transparent</item>
	        <item name="android:windowNoTitle">true</item>
	    </style>
	 */
	public LoadingMsgDialog(Context context, int width, int height, float dimAmount, int style) {
		super(context, style);

		addView(context);
		setParams(context, width, height, dimAmount);
		//use like 
		//LoadingMsgDialog dialog = new LoadingMsgDialog(this, 0.3f, R.style.Theme_dialog);
	}

	private void setParams(Context context, int width, int height, float dimAmount) {
		
		Window window = getWindow();
		WindowManager.LayoutParams params = window.getAttributes();

		// set width,height by density and gravity
		params.width = width;
		params.height = height;
		params.gravity = Gravity.CENTER;
		params.dimAmount = dimAmount;

		window.setAttributes(params);
	}

	private void addView(Context context) {

		int dip2px_10 = dip2px(10);
		int dip2px_20 = dip2px(20);
		int dip2px_28 = dip2px(28);
		
		LinearLayout linearlayout = new LinearLayout(context);
		
		FrameLayout.LayoutParams params_1_1 = new FrameLayout.LayoutParams(MATCH_PARENT , MATCH_PARENT);
		params_1_1.gravity = Gravity.CENTER;
		linearlayout.setLayoutParams(params_1_1);
		linearlayout.setOrientation(LinearLayout.VERTICAL);
		linearlayout.setPadding(dip2px_20, dip2px_20, dip2px_20, dip2px_20);
		linearlayout.setGravity(Gravity.CENTER);
		
		drawable = new GradientDrawable();
		drawable.setCornerRadius(10);
		drawable.setColor(0xcc707070);
		setBackgroud(linearlayout);
		

		//进度条
		progressbar = new ProgressBar(context);
		LinearLayout.LayoutParams params_2_2 = new LinearLayout.LayoutParams(dip2px_28, dip2px_28);
		progressbar.setLayoutParams(params_2_2);
		linearlayout.addView(progressbar);
		

		//进度文本
		tv_msg = new TextView(context);
		LinearLayout.LayoutParams params_2_3 = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
		params_2_3.topMargin = dip2px_10;
		tv_msg.setLayoutParams(params_2_3);
		tv_msg.setTextColor(0xfff5f5f5);
		tv_msg.setText("加载中");
		linearlayout.addView(tv_msg);
		
		setContentView(linearlayout);

	}

	@SuppressWarnings("deprecation")
	private void setBackgroud(LinearLayout linearlayout) {
		linearlayout.setBackgroundDrawable(drawable);
	}
	
	public GradientDrawable getBackgroundDrawable() {
		return drawable;
	}
	
	public void setBackgroundColor(int argb){
		drawable.setColor(argb);
	}

	public TextView getMsgTextView() {
		return tv_msg;
	}
	
	public void setTextMsg(CharSequence msg){
		tv_msg.setText(msg);
	}
	
	public ProgressBar getProgressbar() {
		return progressbar;
	}

	private int dip2px(float dipValue) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

}