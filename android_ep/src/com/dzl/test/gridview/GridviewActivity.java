package com.dzl.test.gridview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.dzl.test.BaseActivity;
import com.dzl.test.BaseAdapterDzl;
import com.dzl.test.R;

public class GridviewActivity extends BaseActivity {

	private GridView item_gridview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_gridview);

		initView();

	}

	private void initView() {
		final List<String> keyworlds = new ArrayList<String>();
		keyworlds.add("a1");
		keyworlds.add("a2");
		keyworlds.add("a3");
		keyworlds.add("a4");
		keyworlds.add("a5");
		keyworlds.add("a6");
		keyworlds.add("a6");
		item_gridview = (GridView) findViewById(R.id.item_gridview);
		item_gridview.setAdapter(new SearchGridViewAdapter(this, keyworlds));
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

	class SearchGridViewAdapter extends BaseAdapterDzl<String> {

		public SearchGridViewAdapter(Activity activity, List<String> data) {
			super(activity, data);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			GridHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_search_gridview, null, false);
				holder = new GridHolder();
				convertView.setTag(holder);
				holder.item_title = (TextView) convertView.findViewById(R.id.item_gridview_content);

			} else {
				holder = (GridHolder) convertView.getTag();
			}

			final String searchObject = mData.get(position);
			holder.item_title.setText(searchObject);

			return convertView;
		}

	}

	class GridHolder {
		TextView item_title;
	}

}
