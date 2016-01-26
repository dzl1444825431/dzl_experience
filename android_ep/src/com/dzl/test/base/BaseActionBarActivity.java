package com.dzl.test.base;

import com.dzl.test.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class BaseActionBarActivity extends AppCompatActivity {
	
	//若想设置title居中，可另新建View 布局在内部，设置layout_gravity属性如center
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_base_actionbar);
		
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		
		toolbar.setTitle("title");
		
		setSupportActionBar(toolbar);
		
		// Navigation Icon 要設定在 setSupoortActionBar 才有作用
		// 否則會出現 back button
		toolbar.setNavigationIcon(R.drawable.seek_thumb);
		
		//若设置了setOnMenuItemClickListener 则 onOptionsItemSelected 不起作用
		//无论setOnMenuItemClickListener返回true | false）
		toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				System.out.println("resp1onse : arg0 = " + arg0.getOrder());
				return true;
			}
		});
		
		
		toolbar.setNavigationOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("resp1onse : onclick	 = ");
				finish();
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		System.out.println("resp1onse : item.getTitle = " + item.getTitle());
		return super.onOptionsItemSelected(item);
	}

}
