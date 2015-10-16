package com.dzl.test;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


/**
 * adapter 的一个基类
 *    主要用于简化代码，抽离业务，更着重于业务逻辑
 * @author dzl 2015年7月27日
 */
public abstract class Dzl_BaseAdapter<T> extends BaseAdapter {

	protected List<T> mData;
	protected Context mContext;
	protected LayoutInflater mInflater;
	
	public Dzl_BaseAdapter(List<T> data, Context context){
		this.mData = data;
		System.out.println("resp1onse Dzl_BaseAdapter: public Dzl_BaseAdapter(List<T> data, Context context){ start constructor ");
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
		System.out.println("resp1onse Dzl_BaseAdapter: public Dzl_BaseAdapter(List<T> data, Context context){ end constructor ");
	}
	
	@Override
	public int getCount() {
		System.out.println("resp1onse Dzl_BaseAdapter: public int getCount() { start return ");
		System.out.println("resp1onse Dzl_BaseAdapter: public int getCount() { end return ");
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		System.out.println("resp1onse Dzl_BaseAdapter: public T getItem(int position) { start return ");
		System.out.println("resp1onse Dzl_BaseAdapter: public T getItem(int position) { end return ");
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		System.out.println("resp1onse Dzl_BaseAdapter: public long getItemId(int position) { start return ");
		System.out.println("resp1onse Dzl_BaseAdapter: public long getItemId(int position) { end return ");
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);

	@Override
	public boolean hasStableIds() {
		System.out.println("resp1onse Dzl_BaseAdapter.hasStableIds()");
		return super.hasStableIds();
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		System.out.println("resp1onse Dzl_BaseAdapter.registerDataSetObserver()");
		super.registerDataSetObserver(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		System.out.println("resp1onse Dzl_BaseAdapter.unregisterDataSetObserver()");
		super.unregisterDataSetObserver(observer);
	}

	@Override
	public void notifyDataSetChanged() {
		System.out.println("resp1onse Dzl_BaseAdapter.notifyDataSetChanged()");
		super.notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetInvalidated() {
		System.out.println("resp1onse Dzl_BaseAdapter.notifyDataSetInvalidated()");
		super.notifyDataSetInvalidated();
	}

	@Override
	public boolean areAllItemsEnabled() {
		System.out.println("resp1onse Dzl_BaseAdapter.areAllItemsEnabled()");
		return super.areAllItemsEnabled();
	}

	@Override
	public boolean isEnabled(int position) {
		System.out.println("resp1onse Dzl_BaseAdapter.isEnabled()");
		return super.isEnabled(position);
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		System.out.println("resp1onse Dzl_BaseAdapter.getDropDownView()");
		return super.getDropDownView(position, convertView, parent);
	}

	@Override
	public int getItemViewType(int position) {
		System.out.println("resp1onse Dzl_BaseAdapter.getItemViewType()");
		return super.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		System.out.println("resp1onse Dzl_BaseAdapter.getViewTypeCount()");
		return super.getViewTypeCount();
	}

	@Override
	public boolean isEmpty() {
		System.out.println("resp1onse Dzl_BaseAdapter.isEmpty()");
		return super.isEmpty();
	}
	
}
