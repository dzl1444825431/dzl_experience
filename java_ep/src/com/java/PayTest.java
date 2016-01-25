package com.java;

public class PayTest {
	
	public static void main(String[] args) {
		String param = null;
		String url = "https://api.mch.weixin.qq.com/pay/orderquery";
		String sendPost = HttpRequest.sendPost(url, param);
		System.out.println("resp1one : sendPost = " + sendPost);
	}

}
