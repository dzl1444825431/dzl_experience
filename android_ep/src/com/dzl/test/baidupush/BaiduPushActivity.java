package com.dzl.test.baidupush;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.dzl.test.R;

public class BaiduPushActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_baidu_push);
		PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,
				Utils.getMetaValue(BaiduPushActivity.this, "api_key"));
	}

}
