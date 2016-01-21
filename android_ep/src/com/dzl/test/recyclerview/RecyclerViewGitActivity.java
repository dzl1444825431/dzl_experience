package com.dzl.test.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;
import com.dzl.test.recyclerview.git.EndlessRecyclerOnScrollListener;
import com.dzl.test.recyclerview.git.HeaderAndFooterRecyclerViewAdapter;
import com.dzl.test.recyclerview.git.HeaderSpanSizeLookup;
import com.dzl.test.recyclerview.git.LoadingFooter;
import com.dzl.test.recyclerview.git.RecyclerViewStateUtils;

public class RecyclerViewGitActivity extends BaseActivity implements OnRefreshListener {
	
	private RecyclerView recycler_view;
	private SwipeRefreshLayout swipe_refreshlayout;
	
	private List<RecyclerViewData> mData;
	private RecyclerViewNormalAdapter adapter;
	private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter = null;
	private boolean isLast = true;
	int count = 0;
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mHeaderAndFooterRecyclerViewAdapter.notifyDataSetChanged();
			if (count++ > 5) {
				isLast = false;
				RecyclerViewStateUtils.setFooterViewState(recycler_view, LoadingFooter.State.TheEnd);
			}else {
				
				RecyclerViewStateUtils.setFooterViewState(recycler_view, LoadingFooter.State.Normal);
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_recyclerview);
		
		initView();
		initData();
		
		requestData();
	}
	
	
	private void initData() {
		mData = new ArrayList<RecyclerViewData>();
//		addData();
		adapter = new RecyclerViewNormalAdapter(this, mData);
		mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
		recycler_view.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
		
		final int spanCount = 2;
		GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);//一行放几个
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		layoutManager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) recycler_view.getAdapter(), layoutManager.getSpanCount()));
		recycler_view.setHasFixedSize(true);
		recycler_view.setLayoutManager(layoutManager);
		
		recycler_view.addOnScrollListener(new EndlessRecyclerOnScrollListener(){
			@Override
			public void onLoadNextPage(View view) {
				onLoadMore(view);
			}
		});
		swipe_refreshlayout.setEnabled(false);
		swipe_refreshlayout.setRefreshing(false);
		swipe_refreshlayout.setOnRefreshListener(this); 
		
	}


	private void addData() {
		for (int i = 0; i < 20; i++) {
			RecyclerViewData data = new RecyclerViewData();
			data.setTitle("Java" + i);
			data.setDesc("jvm" + i);
			mData.add(data);
		}
	}

	protected void onLoadMore(View view) {
		LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(recycler_view);
        if(state == LoadingFooter.State.Loading) {
            Log.d("@Cundong", "the state is Loading, just wait..");
            return;
        }

        if (isLast) {
            // loading more
            RecyclerViewStateUtils.setFooterViewState(this, recycler_view, 15, LoadingFooter.State.Loading, null);
            requestData();
        } else {
            //the end
            RecyclerViewStateUtils.setFooterViewState(this, recycler_view, 15, LoadingFooter.State.TheEnd, null);
        }
	}

	/**
     * 请求网络
     */
    private void requestData() {
    	
    	 new Thread() {

             @Override
             public void run() {
            	 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
            	 
            	 addData();
            	 handler.sendEmptyMessage(1);
             }
    	 }.start();
    	
    }

	private void initView() {
		recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
		swipe_refreshlayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshlayout);
		
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
	}


	@Override
	public void onRefresh() {
		
	} 
	
	
	

}
