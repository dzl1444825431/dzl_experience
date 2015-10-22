package com.dzl.test.listview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dzl.test.BaseActivity;
import com.dzl.test.Dzl_BaseAdapter;
import com.dzl.test.R;

public class ListViewActivity extends BaseActivity {
	
	private com.dzl.test.listview.ListView listView;
	private ListAdapter adapter;
	private ArrayList<String> data;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		System.out.println("resp1onse ListViewActivity: protected void onCreate(Bundle savedInstanceState) { start void ");
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_listview);
		listView = (ListView) findViewById(R.id.listview);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				data = new ArrayList<String>();
				for (int i = 0; i < 10; i++) {
					data.add("" + i);
				}
				adapter = new ListViewAdapter(data, ListViewActivity.this);
				listView.setAdapter(adapter);
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(ListViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});
//		System.out.println("resp1onse ListViewActivity: protected void onCreate(Bundle savedInstanceState) { end void ");
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(ListViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}
	
	class ListViewAdapter extends Dzl_BaseAdapter<String>{

		public ListViewAdapter(List<String> data, Context context) {
			super(data, context);
		System.out.println("resp1onse ListViewActivity ListViewAdapter : public ListViewAdapter(List<String> data, Context context) { start constructor ");
			
		System.out.println("resp1onse ListViewActivity ListViewAdapter : public ListViewAdapter(List<String> data, Context context) { end constructor ");
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		System.out.println("resp1onse ListViewActivity ListViewAdapter : public View getView(int position, View convertView, ViewGroup parent) { start return ");
			Holder holder;
			if (convertView == null) {
				holder = new Holder();
				convertView = mInflater.inflate(R.layout.listview_item, null, false);;
				holder.tv = (TextView) convertView.findViewById(R.id.textView1);
				convertView.setTag(holder);
			}else {
				holder = (Holder) convertView.getTag();
			}
			holder.tv.setText(data.get(position));
		System.out.println("resp1onse ListViewActivity ListViewAdapter : public View getView(int position, View convertView, ViewGroup parent) { end return  =1");
			return convertView;
		}
		
	}
	
	class Holder {
		TextView tv;
	}

}
