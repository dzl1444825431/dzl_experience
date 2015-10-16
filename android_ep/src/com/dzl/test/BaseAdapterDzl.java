package com.dzl.test;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * adapter 的一个基类
 *    主要用于简化代码，抽离业务，更着重于业务逻辑
 * @author dzl 2015年7月27日
 */
public abstract class BaseAdapterDzl<T> extends BaseAdapter {

	protected List<T> mData;
	protected Activity mActivity;
	protected LayoutInflater mInflater;
	
	public BaseAdapterDzl(Activity activity, List<T> data){
		this.mData = data;
		this.mActivity = activity;
		this.mInflater = LayoutInflater.from(activity);
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

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);

}
