package com.dzl.test.download;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.Header;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 下载工具
 * @author dzl 2015年12月25日
 */
public class DownLoadUtil {

	public DownLoadUtil() {
		super();
	}

	/**
	 * 下载状态、进度监听
	 * @author dzl 2015年12月25日
	 */
	public interface OnDownloadEvent {
		
		public void onStart();
		public void onProgress(int bytesWritten, int totalSize);
		public void onSuccess(int arg0, Header[] arg1, byte[] arg2);
		public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3);
		public void onFinish();

	}
	
	/**
	 * 只进度监听
	 * @author dzl 2015年12月25日
	 */
	public interface OnProgressListener {
		public void onProgress(int bytesWritten, int totalSize);
	}
	
	public void download(final String url, final OnDownloadEvent onDownloadEvent) {
		download(url, onDownloadEvent, null);
	}
	
	public void download(final String url, final OnDownloadEvent onDownloadEvent, final OnProgressListener OnProgressListener) {
		
		if (onDownloadEvent == null) {
			throw new RuntimeException(" onDownloadEvent cannot bu null!");
		}
		AsyncHttpResponseHandler ar;
		
		if (OnProgressListener != null) {
			ar = new AsyncHttpResponseHandler() {

				@Override
				public void onFailure(int arg0, Header[] arg1, byte[] arg2,
						Throwable arg3) {
					onDownloadEvent.onFailure(arg0, arg1, arg2, arg3);
				}

				@Override
				public void onFinish() {
					onDownloadEvent.onFinish();
				}

				@Override
				public void onProgress(int bytesWritten, int totalSize) {
					onDownloadEvent.onProgress(bytesWritten, totalSize);
					OnProgressListener.onProgress(bytesWritten, totalSize);
				}

				@Override
				public void onStart() {
					onDownloadEvent.onStart();
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
					onDownloadEvent.onSuccess(arg0, arg1, arg2);
					
				}

			};
			
		}else {
			ar = new AsyncHttpResponseHandler() {

				@Override
				public void onFailure(int arg0, Header[] arg1, byte[] arg2,
						Throwable arg3) {
					onDownloadEvent.onFailure(arg0, arg1, arg2, arg3);
				}

				@Override
				public void onFinish() {
					onDownloadEvent.onFinish();
				}

				@Override
				public void onProgress(int bytesWritten, int totalSize) {
					onDownloadEvent.onProgress(bytesWritten, totalSize);
				}

				@Override
				public void onStart() {
					onDownloadEvent.onStart();
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
					onDownloadEvent.onSuccess(arg0, arg1, arg2);
					
				}

			};
		}
		
		
		new AsyncHttpClient().get(url, ar);
	}
	
	/**
	 * 一键下载，显示通知、点击安装
	 * @param context
	 * @param url
	 * @param filePath
	 */
	public static void downloadOneKey(Context context, final String url, final String filePath, boolean install, final OnProgressListener OnProgressListener){
		final DownloadNotificationUtil notificationUtil = new DownloadNotificationUtil(context, filePath);
		notificationUtil.setInstall(install);
		if (OnProgressListener == null) {
			new DownLoadUtil().download(url, new OnDownloadEvent() {
				
				@Override
				public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
					DownloadFileUtil.writeFile(filePath, arg2);
				}
				
				@Override
				public void onStart() {
					notificationUtil.setNotification(0, false);
				}
				
				@Override
				public void onProgress(int bytesWritten, int totalSize) {
					notificationUtil.setNotification(bytesWritten * 100 / totalSize, false);
				}
				
				@Override
				public void onFinish() {
					
				}
				
				@Override
				public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
					notificationUtil.setNotification(-1, false);
				}
			});
		}else {
			new DownLoadUtil().download(url, new OnDownloadEvent() {
				
				@Override
				public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
					DownloadFileUtil.writeFile(filePath, arg2);
				}
				
				@Override
				public void onStart() {
					notificationUtil.setNotification(0, false);
				}
				
				@Override
				public void onProgress(int bytesWritten, int totalSize) {
					notificationUtil.setNotification(bytesWritten * 100 / totalSize, false);
					OnProgressListener.onProgress(bytesWritten, totalSize);
				}
				
				@Override
				public void onFinish() {
					
				}
				
				@Override
				public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
					notificationUtil.setNotification(-1, false);
				}
			});
		}
		
	}
	
	/**
	 * 一键下载，显示通知、点击安装
	 * @param context
	 * @param url
	 * @param filePath
	 */
	public static void downloadOneKey(Context context, final String url, final String filePath){
		downloadOneKey(context, url, filePath, false, null);
	}
	
	public static class DownloadFileUtil {
		
		public static boolean writeFile(String filePath, byte[] buffer){
			
			FileOutputStream oStream = null;
			try {
				oStream = new FileOutputStream(filePath);
				oStream.write(buffer);
				oStream.flush();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if (oStream != null) {
					try {
						oStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return false;
		}
	}

}
