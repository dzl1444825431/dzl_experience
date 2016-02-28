package com.dzl.test.volleyxml;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 发送网络请求
 * 
 * @author dzl 2016年01月09日
 */
public class NetUtil {
	private RequestQueue jsonRequestQueue;
	private static NetUtil instance = null;

	/**
	 * 构造函数
	 */
	private NetUtil(Context context) {
		this.jsonRequestQueue = Volley.newRequestQueue(context);
	}

	/**
	 * 单例模式
	 */
	public static NetUtil getInstance(Context context) {
		if (instance == null) {
			synchronized (NetUtil.class) {
				if (instance == null) {
					instance = new NetUtil(context);
				}
			}
		}
		return instance;
	}

	/**
	 * 得到json的请求队列
	 */
	public RequestQueue getJsonRequestQueue() {
		return jsonRequestQueue;
	}
}
