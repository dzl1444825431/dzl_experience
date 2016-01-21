package com.dzl.test.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class RecyclerViewCustomActivity extends BaseActivity implements OnRefreshListener {

	private RecyclerView recycler_view;
	private SwipeRefreshLayout swipe_refreshlayout;

	private List<RecyclerViewData> mData;
	private RecyclerViewNormalAdapter2 adapter;

	int count = 0;

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			
			if (count++ > 5) {
				// handler.sendEmptyMessageDelayed(1, 4000);
				adapter.setFooterLoading(1);
				adapter.notifyItemChanged(adapter.getItemCount());
			}else {
				
				addData();
				adapter.notifyDataSetChanged();
				
			}
		};
	};
	protected int lastVisibleItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_recyclerview);

		initView();
		initData();

		// handler.sendEmptyMessageDelayed(1, 2000);
	}

	@SuppressWarnings("deprecation")
	private void initData() {
		mData = new ArrayList<RecyclerViewData>();
		// addData();
		adapter = new RecyclerViewNormalAdapter2(this, mData);
		recycler_view.setAdapter(adapter);

		final int spanCount = 2;
		GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);// 一行放几个
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		layoutManager.setSpanSizeLookup(new SpanSizeLookup() {

			@Override
			public int getSpanSize(int arg0) {
				if (adapter.getItemViewType(arg0) == RecyclerViewNormalAdapter2.TYPE_FOOTER) {
					return spanCount;
				}
				return 1;
			}
		});

		swipe_refreshlayout.setColorSchemeColors(0xff323232, 0xffcccccc,
				0xffff0000, 0xff0000ff);
		 swipe_refreshlayout.setOnRefreshListener(this);

		recycler_view.setHasFixedSize(true);
		recycler_view.setLayoutManager(layoutManager);

		recycler_view.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView,
					int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				// System.out.println("resp1onse : newState = " + newState);
				if (newState == RecyclerView.SCROLL_STATE_IDLE
						&& lastVisibleItem + 1 == adapter.getItemCount()) {
//					swipe_refreshlayout.setRefreshing(true);

					adapter.setFooterLoading(0);
					adapter.notifyItemChanged(adapter.getItemCount());
					// 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
					handler.sendEmptyMessageDelayed(0, 3000);
				}
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				lastVisibleItem = ((GridLayoutManager) recyclerView
						.getLayoutManager()).findLastVisibleItemPosition();
				
				swipe_refreshlayout.setEnabled(((GridLayoutManager) recyclerView
						.getLayoutManager()).findFirstCompletelyVisibleItemPosition() == 0);
			}

		});

		adapter.setFooterLoading(0);
		adapter.notifyItemChanged(adapter.getItemCount());
		
	}

	private void addData() {
		int current = mData.size();
		int length = current + 20;
		for (int i = current; i < length; i++) {
			RecyclerViewData data = new RecyclerViewData();
			data.setTitle("Java" + i);
			data.setDesc("jvm" + i);
			mData.add(data);
		}
	}

	private void initView() {
		recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
		swipe_refreshlayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshlayout);
	}

	@Override
	public void onRefresh() {
		System.out.println("resp1onse : onRefresh = " );
	}

}
