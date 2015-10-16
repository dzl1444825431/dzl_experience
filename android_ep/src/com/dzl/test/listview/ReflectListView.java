package com.dzl.test.listview;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListAdapter;

public class ReflectListView extends AbsListView {

	private Field mAdapter;

	public ReflectListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
	}

	public ReflectListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		try {
			mAdapter = AbsListView.class.getDeclaredField("mAdapter");
			mAdapter.setAccessible(true);
			System.out.println("resp1onse : mAdapter = " + mAdapter.get(this));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
	}

	public ReflectListView(Context context) {
		super(context);
		
	}

	@Override
	public ListAdapter getAdapter() {
		return null;
	}

	@Override
	public void setSelection(int position) {
		
	}

}
