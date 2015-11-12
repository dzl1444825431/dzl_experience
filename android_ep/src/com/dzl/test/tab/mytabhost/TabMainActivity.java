package com.dzl.test.tab.mytabhost;

import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.dzl.test.R;

public class TabMainActivity extends TabActivity {
	public List<ImageView> imageList = new ArrayList<ImageView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tab_main);
		TabHost host = getTabHost();
		TabSpec tab1 = host.newTabSpec("tab1");
		TabSpec tab2 = host.newTabSpec("tab2");
		TabSpec tab3 = host.newTabSpec("tab3");
		
		LinearLayout view1 = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_tab_space_view, null);
		ImageView image1 =(ImageView)view1.findViewById(R.id.image123);
		image1.setImageResource(R.drawable.icon_2_n);
		TextView text1 = (TextView) view1.findViewById(R.id.TextView1);
		text1.setText("My");
		Intent customerInfoIntent = new Intent(this, TabMainActivitySub2.class);
		tab1.setIndicator(view1).setContent(customerInfoIntent);
		
		
		LinearLayout view2 = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_tab_space_view, null);
		ImageView image2 =(ImageView)view2.findViewById(R.id.image123);
		image2.setImageResource(R.drawable.icon_3_n);
		TextView text2 = (TextView) view2.findViewById(R.id.TextView1);
		text2.setText("you");
		Intent customerInfoIntent2 = new Intent(this, TabMainActivitySub1.class);
		tab2.setIndicator(view2).setContent(customerInfoIntent2);
		
		
		LinearLayout view3 = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_tab_space_view, null);
		ImageView image3 =(ImageView)view3.findViewById(R.id.image123);
		image3.setImageResource(R.drawable.icon_4_n);
		TextView text3 = (TextView) view3.findViewById(R.id.TextView1);
		text3.setText("our");
		tab3.setIndicator(view3).setContent(customerInfoIntent);
		host.addTab(tab1);
		host.addTab(tab2);
		host.addTab(tab3);
		
	}

	

}
