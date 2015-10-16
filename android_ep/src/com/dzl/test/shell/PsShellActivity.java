package com.dzl.test.shell;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.dzl.test.R;

public class PsShellActivity extends Activity implements OnClickListener {
	
	private Button button;
	private ActivityManager am;
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_ps_shell);
		button = (Button) findViewById(R.id.button1);
		editText = (EditText) findViewById(R.id.button2);
		editText.clearFocus();
		am = (ActivityManager) PsShellActivity.this.getSystemService(Context.ACTIVITY_SERVICE);
		if (getServiceRunning()) {
			button.setText("停止ps服务");
		}
		button.setOnClickListener(this);
		
	}


	private boolean getServiceRunning() {
		List<RunningServiceInfo> services = am.getRunningServices(1000);
		System.out.println("resp1onse : am.getRunningServices.size() " + services.size());
		String serviceName = PsShellService.class.getName();
		System.out.println("resp1onse : PsShellService.class.getName() = " + serviceName);
		for (RunningServiceInfo info : services) {
//			System.out.println("resp1onse : info.service.getClassName() = " + info.service.getClassName());
			if(serviceName.equals(info.service.getClassName())){
				return true;
			}
		}
		
		return false;
	}


	@Override
	public void onClick(View v) {
		if (getServiceRunning()) {
			System.out.println("resp1onse : 停止ps服务");
//			this.stopService(
//					new Intent(PsShellActivity.this,PsShellService.class));
			this.stopService(
					new Intent("com.dzl.test.shell.PsShellService"));
//			Intent intent = new Intent();
//			intent.setAction("AAAAA");
//			intent.putExtra("cmd", PsShellService.CMD_STOP_SERVICE);
//			sendBroadcast(intent);
			button.setText("启动ps服务");
			
		}else {
			
			Intent intent = new Intent(PsShellActivity.this,PsShellService.class);
			Editable text = editText.getText();
			if (text != null && text.toString().trim().length() > 0) {
				intent.putExtra("code", text.toString().trim());
			}
			startService(intent);
			button.setText("停止ps服务");
		}
		
	}

}
