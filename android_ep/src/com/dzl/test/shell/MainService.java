package com.dzl.test.shell;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dzl.test.R;

public class MainService extends Activity {
	private TextView textviewService;
	private Button buttonStart, buttonStop;
	public static final int CMD_STOP_SERVICE = 0;
	DataReceiver dateReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		// textviewService=(TextView)findViewById(R.id.textservice);
		// buttonStart=(Button)findViewById(R.id.buttonstart);
		// buttonStop=(Button)findViewById(R.id.buttonstop);
		buttonStart.setOnClickListener(buttonClick);
		buttonStop.setOnClickListener(buttonClick);
	}

	private View.OnClickListener buttonClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == buttonStart) {
				Intent intentService = new Intent(MainService.this, MyService.class);
				startService(intentService);
				Log.i("onStartCommand", "OnClickListener=");
			} else if (v == buttonStop) {
				Intent intent = new Intent();
				intent.setAction("AAAAA");
				intent.putExtra("cmd", CMD_STOP_SERVICE);
				sendBroadcast(intent);
			}

		}
	};

	private class DataReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i("onStartCommand", "接受要更新的广播数据=" + intent.getStringExtra("data"));
			String Date = intent.getStringExtra("data");
			textviewService.setText(Html.fromHtml("<font color='#0066CC'><u>" + "Service的数据为:" + Date + "</font>"));
		}
	}

	@Override
	protected void onStart() {
		dateReceiver = new DataReceiver();
		IntentFilter intentfilter = new IntentFilter();// 创建IntentFilter对象
		intentfilter.addAction("AAAAA");
		registerReceiver(dateReceiver, intentfilter);// 注册Broadcast Receiver
		super.onStart();
	}

	@Override
	protected void onStop() {
		unregisterReceiver(dateReceiver);// 取消注册Broadcast Receiver
		super.onStop();
	}

}
