package com.dzl.test.viewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.dzl.test.R;

public class ViewPagerActivity extends FragmentActivity {

	private ViewPager viewPager;
	private PagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("resp1onse ViewPagerActivity: protected void onCreate(Bundle savedInstanceState) { start void ");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_viewpager);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		FragmentManager a = getSupportFragmentManager();
		adapter = new FragmentPagerAdapter(a) {
			
			private String[] TITLES = new String[]{"发现","应用","游戏","榜单","补贴"};

			@Override
			public CharSequence getPageTitle(int position) {
				
				return TITLES[position];
			}

			@Override
			public int getCount() {
		System.out.println("resp1onse ViewPagerActivity: public int getCount() { start internal 3");
				return TITLES.length;
			}

			@Override
			public Fragment getItem(int arg0) {
		System.out.println("resp1onse ViewPagerActivity: public Fragment getItem(int arg0) { start internal 3");
				Fragment fragment;
				switch (arg0) {
				case 0:
					fragment = new ViewPagerFragment();
					break;
				case 1:
					fragment = new ViewPagerFragment4();
					break;
				case 2:
					fragment = new ViewPagerFragment5();
					break;
				case 3:
					fragment = new ViewPagerFragment6();
					break;
				case 4:
					fragment = new ViewPagerFragment7();
					break;

				default:
					fragment = new ViewPagerFragment();
					break;
				}
		System.out.println("resp1onse ViewPagerActivity: protected void onCreate(Bundle savedInstanceState) { end return if ");
				return fragment;
			}
		};
		viewPager.setAdapter(adapter);

		System.out.println("resp1onse ViewPagerActivity: protected void onCreate(Bundle savedInstanceState) { end void ");
	}

}
