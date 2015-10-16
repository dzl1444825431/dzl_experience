package com.dzl.test.gallery;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.dzl.test.BaseAdapterDzl;
import com.dzl.test.R;

public class GalleryActivity extends Activity {

	private com.dzl.test.gallery.Gallery2 gallery;
	private BaseAdapter adapter;
	private Button button;
	private DisplayMetrics dm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_gallery);

		gallery = (com.dzl.test.gallery.Gallery2) findViewById(R.id.gallery);
		button = (Button) findViewById(R.id.button1);
		dm = getResources().getDisplayMetrics();
		
		MarginLayoutParams lp = (MarginLayoutParams) gallery.getLayoutParams();
		lp.setMargins(0,
				lp.topMargin, lp.rightMargin, lp.bottomMargin);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LocationMethod();
			}
		});
		List<Integer> data = new ArrayList<Integer>();
		data.add(R.drawable.girl1);
		data.add(R.drawable.girl2);
		data.add(R.drawable.girl3);
		data.add(R.drawable.girl4);
		data.add(R.drawable.girl5);
		data.add(R.drawable.girl6);
		data.add(R.drawable.girl7);
		data.add(R.drawable.girl8);

		adapter = new ImageAdapter(this, data);
		gallery.setAdapter(adapter);

//		gallery.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(GalleryActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//			}
//		});
	}

	public Location getLocation() {// 获取Location通过LocationManger获取！
		LocationManager locManger = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location loc = locManger.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (loc == null) {
			loc = locManger.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		return loc;
	}

	public void LocationMethod() {// Location常用方法简介

		Location loc = getLocation();
		if (loc != null) {

			System.out.println("resp1onse : loc.distanceTo = " + loc.distanceTo(loc));// float
			System.out.println("resp1onse : getAltitude = " + loc.getAltitude());// double
			// 海拔高度
			System.out.println("resp1onse : getLatitude = " + loc.getLatitude());// double
			System.out.println("resp1onse : getLongitude = " + loc.getLongitude());// double
			System.out.println("resp1onse : getSpeed = " + loc.getSpeed());// float
		}
	}

	class ImageAdapter extends BaseAdapterDzl<Integer> {

		public ImageAdapter(Activity activity, List<Integer> data) {
			super(activity, data);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_gallery, null, false);
				holder = new Holder();
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				convertView.setTag(holder);
			} else {

				holder = (Holder) convertView.getTag();
			}

			holder.img.setBackgroundResource(mData.get(position));

			return convertView;
		}

	}

	class Holder {

		ImageView img;
	}

}
