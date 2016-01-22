package com.dzl.test.viewPager.v0122;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class ViewPagerActivity extends BaseActivity {
	
	private CirclePageIndicator indicator;
	private AutoScrollViewPager viewPager;
	List<ImageEntitiy> list;
	private PagerAdapter adapter;
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			setStart(msg.what);
			
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_viewpager2);
		
		initView();
		
	}
	
	private void initView() {
		
		indicator = (CirclePageIndicator) findViewById(R.id.indicator);
		viewPager = (AutoScrollViewPager) findViewById(R.id.viewpager);
		list = new ArrayList<ImageEntitiy>();
		adapter = new HeaderImageEntitiyAdapter(this, list);
		viewPager.setAdapter(adapter);
		
//        setStart();
//		setStart(0);
		
        handler.sendEmptyMessageDelayed(0, 3 * 1000);
        handler.sendEmptyMessageDelayed(1, 15 * 1000);
        handler.sendEmptyMessageDelayed(2, 30 * 1000);
        
	}

	private void setStart(int add) {
		
		if (add == 0) {
			list.clear();
		}
		
		int size = list.size();
		
		for (int i = size; i < size + 3; i++) {
			
			ImageEntitiy imageEntitiy = new ImageEntitiy();
			imageEntitiy.setText("viewpage " + i);
			if (i == 0) {
				imageEntitiy.setUrl("http://h.hiphotos.baidu.com/image/pic/item/dc54564e9258d1093cf78e5cd558ccbf6d814dc3.jpg");
			}else if (i == 1) {
				imageEntitiy.setUrl("http://a.hiphotos.baidu.com/image/pic/item/279759ee3d6d55fb924d52c869224f4a21a4dd50.jpg");
				
			}else if (i == 2) {
				imageEntitiy.setUrl("http://e.hiphotos.baidu.com/image/pic/item/738b4710b912c8fc7778d223f8039245d7882150.jpg");
				
			}else if (i == 3) {
				imageEntitiy.setUrl("http://b.hiphotos.baidu.com/image/pic/item/77c6a7efce1b9d168427be8ff7deb48f8d54647f.jpg");
				
			}else if (i == 4) {
				imageEntitiy.setUrl("http://d.hiphotos.baidu.com/image/pic/item/f3d3572c11dfa9ec994471f966d0f703908fc1f1.jpg");
				
			}else if (i == 5) {
				imageEntitiy.setUrl("http://f.hiphotos.baidu.com/image/pic/item/32fa828ba61ea8d360a750ea930a304e241f5852.jpg");
				
			}
			
			list.add(imageEntitiy);
		}
		
		
		if (add == 2) {
//			list.clear();
		}
		
		adapter.notifyDataSetChanged();
		indicator.setViewPager(viewPager);
		
//		indicator.notifyDataSetChanged();
		
        if (list.size() > 0) {
        	viewPager.stopAutoScroll();
        	viewPager.setInterval(3 * 1000);
        	
        	viewPager.setCurrentItem(0);
            viewPager.startAutoScroll();
        }else {
			viewPager.stopAutoScroll();
//			viewPager.scrollOnce();
		}
        
        
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
	} 
	
	
	
	
	public class ImageEntitiy{

		private String url;
		private String text;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
		
	}
	
}
