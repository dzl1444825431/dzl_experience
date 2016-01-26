package com.dzl.test.base;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseAdapter_Common<VH extends BaseAdapter_Common.ViewHolder, T> extends BaseAdapter {

	protected Activity mActivity;
	protected List<T> mData;
	protected LayoutInflater mInflater;

	public BaseAdapter_Common(Activity activity, List<T> data) {
		this.mActivity = activity;
		this.mData = data;
		this.mInflater = LayoutInflater.from(mActivity);
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		VH holder = null;
		if (convertView == null) {
			holder = onCreateViewHolder(viewGroup, position);
			holder.itemView.setTag(holder);
		} else {
			holder = (VH) convertView.getTag();
		}

		onBindViewHolder(holder, position);
		return holder.itemView;
	}

	public View inflate(int resLayout, ViewGroup parent) {
		return mInflater.inflate(resLayout, parent, false);
	}

	public abstract VH onCreateViewHolder(ViewGroup parent, int position);

	public abstract void onBindViewHolder(VH holder, int position);

	public static class ViewHolder {

		protected View itemView;

		public ViewHolder(View itemView) {
			this.itemView = itemView;
		}
	}

}
