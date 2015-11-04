package com.dzl.test.slidingMenu;

import android.os.Bundle;
import android.view.KeyEvent;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class SlidingMenuActivity extends BaseActivity {

	SlidingMenu slidingMenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slidingmenu);
		
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setMenu(R.layout.slidingmenu);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
		
//		slidingMenu.setMode(SlidingMenu.LEFT);//设置左滑菜单
//		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置滑动的屏幕范围，该设置为全屏区域都可以滑动
//		slidingMenu.setShadowDrawable(R.drawable.shadow);//设置阴影图片
//		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
//		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
//		slidingMenu.setBehindWidth(400);//设置SlidingMenu菜单的宽度
//		slidingMenu.setFadeDegree(0.35f);//SlidingMenu滑动时的渐变程度
//		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);//使SlidingMenu附加在Activity上
//		slidingMenu.setMenu(R.layout.menu_layout);//设置menu的布局文件
//		slidingMenu.toggle();//动态判断自动关闭或开启SlidingMenu
//		slidingMenu.showMenu();//显示SlidingMenu
//		slidingMenu.showContent();//显示内容
//		slidingMenu.setOnOpenListener(onOpenListener);//监听slidingmenu打开
//
//		slidingMenu.setOnOpenedListener(onOpenedlistener);//监听slidingmenu打开后
//
//		slidingMenu.OnCloseListener(OnClosedListener);//监听slidingmenu关闭时事件
//
//
//		slidingMenu.OnClosedListener(OnClosedListener);//监听slidingmenu关闭后事件
//
//		//左右都可以划出SlidingMenu菜单只需要设置
//		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);//属性，然后设置右侧菜单的布局文件
//		slidingMenu.setSecondaryMenu(R.layout.menu_fram2);//设置右侧菜单
//
//		slidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);//右侧菜单的阴影图片
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			slidingMenu.toggle(true);
			return false;

		default:
			System.out.println("resp1one : 1 = " + 1);
			break;
		}
		System.out.println("resp1one : 2 = " + 2);
		return super.onKeyDown(keyCode, event);
	}
	

}