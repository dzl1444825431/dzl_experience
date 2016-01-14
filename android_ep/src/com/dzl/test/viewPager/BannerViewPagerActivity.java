package com.dzl.test.viewPager;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class BannerViewPagerActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_banner_viewpager);
		
		initView();
		
	}
	
	private void initView() {
		
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
	}
	
	public class OnPageChageListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int arg0) {
			
		}
		
	}

}
