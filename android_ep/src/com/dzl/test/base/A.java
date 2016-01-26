package com.dzl.test.base;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public class A extends BaseAdapter_Common<A.Holder, B> {

	public A(Activity activity, List<B> data) {
		super(activity, data);

	}

	@Override
	public Holder onCreateViewHolder(ViewGroup parent, int position) {
		return null;
	}

	@Override
	public void onBindViewHolder(Holder holder, int position) {

	}

	class Holder extends BaseAdapter_Common.ViewHolder {

		public Holder(View view) {
			super(view);
		}

	}

}
