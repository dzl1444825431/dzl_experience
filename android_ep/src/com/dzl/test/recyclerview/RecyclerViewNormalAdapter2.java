package com.dzl.test.recyclerview;

import java.util.List;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dzl.test.R;

public class RecyclerViewNormalAdapter2 extends
		RecyclerView.Adapter<ViewHolder> {

	private List<RecyclerViewData> mData;
	private Activity mActivity;
	private LayoutInflater mInflater;

	public static final int TYPE_ITEM = 0;
	public static final int TYPE_FOOTER = 1;
	private int state;

	public RecyclerViewNormalAdapter2(Activity activity,
			List<RecyclerViewData> data) {
		super();
		this.mActivity = activity;
		this.mData = data;
		this.mInflater = LayoutInflater.from(mActivity);
	}

	@Override
	public int getItemViewType(int position) {
		// 最后一个item设置为footerView
		if (position + 1 == getItemCount()) {
			return TYPE_FOOTER;
		} else {
			return TYPE_ITEM;
		}
	}

	@Override
	public int getItemCount() {
		return mData.size() + 1;
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int position) {

		if (arg0 instanceof Holder) {

			RecyclerViewData recyclerViewData = mData.get(position);
			((Holder) arg0).setText(recyclerViewData.getTitle(),
					recyclerViewData.getDesc());
		}else if (arg0 instanceof FooterViewHolder) {
			((FooterViewHolder) arg0).changeState(state);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int viewType) {
		ViewHolder holder = null;

		if (viewType == TYPE_ITEM) {
			View view = mInflater.inflate(R.layout.item_recycler_content,null);
			holder = new Holder(view);
			view.setLayoutParams(new RecyclerView.LayoutParams(
					RecyclerView.LayoutParams.MATCH_PARENT,
					RecyclerView.LayoutParams.WRAP_CONTENT));
		} else if (viewType == TYPE_FOOTER) { // type == TYPE_FOOTER

			View view = mInflater.inflate(R.layout.item_recycler_loading, null);
			holder = new FooterViewHolder(view);
			view.setLayoutParams(new RecyclerView.LayoutParams(
					RecyclerView.LayoutParams.MATCH_PARENT,
					120));
		}

		return holder;
	}

	class Holder extends ViewHolder {

		TextView textView1;
		TextView textView2;

		public Holder(View itemView) {
			super(itemView);
			textView1 = (TextView) itemView.findViewById(R.id.textView1);
			textView2 = (TextView) itemView.findViewById(R.id.textView2);
		}

		public void setText(String title, String desc) {
			textView1.setText(title);
			textView2.setText(desc);
		}
	}

	class FooterViewHolder extends ViewHolder {

		ProgressBar loading_progress;
		TextView loading_text;
		LinearLayout loading_view;

		public FooterViewHolder(View itemView) {
			super(itemView);
			
			loading_progress = (ProgressBar) itemView.findViewById(R.id.loading_progress);
			loading_text = (TextView) itemView.findViewById(R.id.loading_text);
			loading_view = (LinearLayout) itemView.findViewById(R.id.loading_view);
		}
		
		
		public void changeState(int state){
			if (state == 0) {
				setLoading();
			}else {
				setEnd();
			}
		}
		
		public void setLoading(){
			loading_progress.setVisibility(View.VISIBLE);
			loading_text.setText("加载中...");
		}
		
		public void setEnd(){
			loading_progress.setVisibility(View.GONE);
			loading_text.setText("到底啦...");
		}

	}
	
	public void setFooterLoading(int state){
		this.state = state;
		
	}

}
