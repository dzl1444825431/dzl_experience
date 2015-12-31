package com.dzl.test.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;
import com.dzl.test.download.DownLoadUtil.OnProgressListener;
import com.dzl.test.download.UpLoadFileUtil.OnUpLoadFileListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

public class DownLoadActivity extends BaseActivity {

	private View button;
	private TextView textView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_download);

		initView();

	}

	private void initView() {
		button = findViewById(R.id.button1);
		findViewById(R.id.button3).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				uploadFile("http://192.168.61.167:86/user_info/edit_avatar", "/storage/sdcard1/zhongyong/aa.apk");
			}
		});
		findViewById(R.id.button4).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				uploadFile("http://192.168.61.167:86/user_info/edit_avatar", "/storage/sdcard1/DCIM/user_logo_yidian.jpg");
			}
		});
		findViewById(R.id.button5).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		textView = (TextView) findViewById(R.id.button2);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				downloadClick(v);
			}
		});
		
		
		try {  
            JSONObject jsonObject = new JSONObject();  
            jsonObject.put("username", "ryantang");  
            StringEntity stringEntity = new StringEntity(jsonObject.toString());  
            new AsyncHttpClient().post(DownLoadActivity.this, "http://api.com/login", stringEntity, "application/json", new JsonHttpResponseHandler(){  

                @Override  
                public void onSuccess(JSONObject jsonObject) {  
                    super.onSuccess(jsonObject);  
                      
                }  
                  
            });  
        } catch (JSONException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

	public void downloadClick(final View view) {
		String url = "http://113.105.73.149/dd.myapp.com/16891/4C4D9D24293908B5D3EF5C05AA239B19.apk?mkey=5679d8abc1a31b42&f=f24&fsname=com.zaijiadd.customer_2.2.2_20.apk&p=.apk";
		final String file = "/storage/sdcard1/zhongyong/aa.apk";

		DownLoadUtil.downloadOneKey(DownLoadActivity.this, url, file, true, new OnProgressListener() {
			
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				int progress = bytesWritten * 100 / totalSize;
				textView.setText(progress + " %");
				
			}
		});
		
	}
	
	// 上传：
	public void uploadFile(String url, String path) {
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("uid", "203");
		params.put("client_type", "1");
		params.put("unique_code", "8cd3824033ebda52d54b0b01ba45f674");
		params.put("sign", "ebda52d54b0b01ba45febda52d54b0b01ba45f");
		params.put("app_version", "1.0");
		
		UpLoadFileUtil.upLoadFile(url, path, "avatar", params, new OnUpLoadFileListener() {
			
			@Override
			public void onSuccess(int statusCode, JSONObject response) {
				
			}
			
			@Override
			public void onStart() {
				
			}
			
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				
			}
			
			@Override
			public void onFinish() {
				
			}
			
			@Override
			public void onFailure(int statusCode, Throwable e, JSONObject errorResponse) {
				
			}
		});
		
		
	}

}
