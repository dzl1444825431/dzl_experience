package com.dzl.test.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 上传文件
 * 
 * @author dzl 2015年12月26日
 */
public class UpLoadFileUtil {
	
	private UpLoadFileUtil() {
		super();
	}

	/**
	 * 上传
	 */
	public static void upLoadFile(String url, String filePath, String fileKey,
			Map<String, String> params, final OnUpLoadFileListener listener) {

		try {

			RequestParams requestParams = new RequestParams();
			
			if (params != null) {
				for (Iterator<Map.Entry<String, String>> it = params.entrySet()
						.iterator(); it.hasNext();) {
					Entry<String, String> entry = it.next();
					requestParams.put(entry.getKey(), entry.getValue());
				}
			}

			requestParams.put(fileKey, new File(filePath));

			AsyncHttpClient client = new AsyncHttpClient();
			
			if (listener == null) {
				
				client.post(url, requestParams, new JsonHttpResponseHandler() {
					
					@Override
					public void onProgress(int bytesWritten, int totalSize) {
						super.onProgress(bytesWritten, totalSize);
					}
					
					@Override
					public void onStart() {
						super.onStart();
					}

					@Override
					public void onFinish() {
						super.onFinish();
					}

					@Override
					public void onSuccess(int statusCode, JSONObject response) {
						super.onSuccess(statusCode, response);
					}

					@Override
					public void onFailure(int statusCode, Throwable e,
							JSONObject errorResponse) {
						super.onFailure(statusCode, e, errorResponse);
					}

				});
			}else {
				
				client.post(url, requestParams, new JsonHttpResponseHandler() {
					
					@Override
					public void onProgress(int bytesWritten, int totalSize) {
						super.onProgress(bytesWritten, totalSize);
						listener.onProgress(bytesWritten, totalSize);
					}
					
					@Override
					public void onStart() {
						super.onStart();
						listener.onStart();
					}

					@Override
					public void onFinish() {
						super.onFinish();
						listener.onFinish();
					}

					@Override
					public void onSuccess(int statusCode, JSONObject response) {
						super.onSuccess(statusCode, response);
						listener.onSuccess(statusCode, response);
					}

					@Override
					public void onFailure(int statusCode, Throwable e,
							JSONObject errorResponse) {
						super.onFailure(statusCode, e, errorResponse);
						listener.onFailure(statusCode, e, errorResponse);
					}

				});
			}
			

		} catch (FileNotFoundException e) {

		}

	}

	public interface OnUpLoadFileListener {
		public void onSuccess(int statusCode, JSONObject response);
		public void onFailure(int statusCode, Throwable e, JSONObject errorResponse);
		public void onFinish();
		public void onStart();
		public void onProgress(int bytesWritten, int totalSize);
	}

}
