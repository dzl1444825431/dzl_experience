package com.dzl.test.recyclerview;

import java.util.List;

import com.dzl.test.R;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewNormalAdapter extends RecyclerView.Adapter<ViewHolder> {

	private List<RecyclerViewData> mData;
	private Activity mActivity;
	private LayoutInflater mInflater;

	public RecyclerViewNormalAdapter(Activity activity,
			List<RecyclerViewData> data) {
		super();
		this.mActivity = activity;
		this.mData = data;
		this.mInflater = LayoutInflater.from(mActivity);
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int position) {

		RecyclerViewData recyclerViewData = mData.get(position);
		((Holder) arg0).setText(recyclerViewData.getTitle(),
				recyclerViewData.getDesc());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int position) {
		ViewHolder holder = null;
		View view = mInflater.inflate(R.layout.item_recycler_content, null);
		holder = new Holder(view);

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

}
