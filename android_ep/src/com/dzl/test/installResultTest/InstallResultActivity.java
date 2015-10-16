package com.dzl.test.installResultTest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

public class InstallResultActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		installNormal(Environment.getExternalStorageDirectory().getPath()
				+ "/ZhongYong/appdownload/" + "ditto视频.apk");
	}
	
	void installNormal(String filePath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(i,-1);
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//super.onActivityResult(requestCode, resultCode, data);
		System.out.println("resp1onse : requestCode=" + requestCode + " resultCode=" + resultCode + " Intent=" + data);
	}

}
