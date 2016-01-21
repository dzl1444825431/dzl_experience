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

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
	
	
	private List<RecyclerViewData> mData;
	private Activity mActivity;
	private LayoutInflater mInflater;
	
	public static final int HEAD = 0;
	public static final int ITEM = 1;
	public static final int FOOTER = 2;

	public RecyclerViewAdapter(Activity activity, List<RecyclerViewData> data) {
		super();
		this.mActivity = activity;
		this.mData = data;
		this.mInflater = LayoutInflater.from(mActivity);
	}

	@Override
	public int getItemViewType(int position) {
		System.out.println("resp1onse :getItemViewType position = " + position);
		return position == mData.size() ? HEAD : ITEM;
	}

	@Override
	public int getItemCount() {
		return mData.size() + 1;
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int position) {
		
		System.out.println("resp1onse :onBindViewHolder position = " + position);
		if (arg0 instanceof Holder) {
//			System.out.println("resp1onse :Holder position = " + position);
			if (position >= 0 && position < mData.size()) {
				RecyclerViewData recyclerViewData = mData.get(position);
				((Holder)arg0).setText(recyclerViewData.getTitle(), recyclerViewData.getDesc());
			}
			
		}else if (arg0 instanceof HeaderHolder) {
//			System.out.println("resp1onse :HeaderHolder position = " + position);
		}
		
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int position) {
		int type = getItemViewType(position);
		System.out.println("resp1onse :onCreateViewHolder position = " + position +" getItemViewType(position) = " + type);
		ViewHolder holder = null;
		if (type == HEAD) {
			View view = mInflater.inflate(R.layout.item_recycler_header, null);
			holder = new HeaderHolder(view);
		}else {
			
			View view = mInflater.inflate(R.layout.item_recycler_content, null);
			holder = new Holder(view);
			
		}
		
		
		return holder;
	}
	
	class Holder extends ViewHolder{

		TextView textView1;
		TextView textView2;
		
		public Holder(View itemView) {
			super(itemView);
			textView1 = (TextView) itemView.findViewById(R.id.textView1);
			textView2 = (TextView) itemView.findViewById(R.id.textView2);
		}
		
		public void setText(String title, String desc){
			textView1.setText(title);
			textView2.setText(desc);
		}
	}
	
	class HeaderHolder extends ViewHolder{
		
		public HeaderHolder(View itemView) {
			super(itemView);
		}
		
	}

}
