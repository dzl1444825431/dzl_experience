package com.dzl.test.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class RecyclerViewActivity extends BaseActivity {
	
	private RecyclerView recycler_view;
	private SwipeRefreshLayout swipe_refreshlayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_recyclerview);
		
		initView();
		initData();
	}
	
	List<RecyclerViewData> mData;
	RecyclerViewAdapter adapter;
	private void initData() {
		mData = new ArrayList<RecyclerViewData>();
		for (int i = 0; i < 4; i++) {
			RecyclerViewData data = new RecyclerViewData();
			data.setTitle("Java" + i);
			data.setDesc("jvm" + i);
			mData.add(data);
		}
		adapter = new RecyclerViewAdapter(this, mData);
		recycler_view.setAdapter(adapter);
		
	}

	private void initView() {
		recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
		swipe_refreshlayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshlayout);
		
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		
		recycler_view.setLayoutManager(layoutManager);
//		final int spanCount = 3;
//		GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);//一行放几个
//		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//		layoutManager.setSpanSizeLookup(new SpanSizeLookup() { //占几个格
//			
//			@Override
//			public int getSpanSize(int arg0) {
//				if (adapter.getItemViewType(arg0) ==  RecyclerViewAdapter.HEAD) {
//					return 3;
//				}
//				return 1;
//			}
//		});
//		
//		recycler_view.setLayoutManager(layoutManager);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
	} 
	
	
	

}
