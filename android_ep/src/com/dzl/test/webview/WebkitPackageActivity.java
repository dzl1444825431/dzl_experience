package com.dzl.test.webview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dzl.test.BaseActivity;

public class WebkitPackageActivity extends BaseActivity {

	private WebView webView;
	CookieManager cookieManager;
//	WebChromeClient webChromeClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		webView = new WebView(this);
		setContentView(webView);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
		webSettings.setAllowFileAccess(true); // 允许访问文件
		webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
		webSettings.setSupportZoom(true); // 支持缩放

//		setDownloadListener();
		setWebChromeClient();
		setWebViewClient();
		String url = "http://crossapp.9miao.com/downloaddetail_1044.html";
		webView.loadUrl(url);
		cookieManager = CookieManager.getInstance();
		String cookie = cookieManager.getCookie("cookie");

		System.out.println("resp1onse : cookie = " + cookie);
		if (cookie != null) {

		} else {
			cookie = "XXX";
		}

		cookieManager.setCookie("cookie", cookie);
		System.out.println("resp1onse : cookie = " + cookie);
	}

	private void setWebViewClient() {
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.shouldOverrideUrlLoading()");
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onPageStarted()");
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onPageFinished()");
				super.onPageFinished(view, url);
			}

			@Override
			public void onLoadResource(WebView view, String url) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onLoadResource()");
				super.onLoadResource(view, url);
			}

			@Override
			public WebResourceResponse shouldInterceptRequest(WebView view,
					String url) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.shouldInterceptRequest()");
				return super.shouldInterceptRequest(view, url);
			}

			@Override
			public void onTooManyRedirects(WebView view, Message cancelMsg,
					Message continueMsg) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onTooManyRedirects()");
				super.onTooManyRedirects(view, cancelMsg, continueMsg);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onReceivedError()");
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

			@Override
			public void onFormResubmission(WebView view, Message dontResend,
					Message resend) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onFormResubmission()");
				super.onFormResubmission(view, dontResend, resend);
			}

			@Override
			public void doUpdateVisitedHistory(WebView view, String url,
					boolean isReload) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.doUpdateVisitedHistory()");
				super.doUpdateVisitedHistory(view, url, isReload);
			}

			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onReceivedSslError()");
				super.onReceivedSslError(view, handler, error);
			}

			@Override
			public void onReceivedHttpAuthRequest(WebView view,
					HttpAuthHandler handler, String host, String realm) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onReceivedHttpAuthRequest()");
				super.onReceivedHttpAuthRequest(view, handler, host, realm);
			}

			@Override
			public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.shouldOverrideKeyEvent()");
				return super.shouldOverrideKeyEvent(view, event);
			}

			@Override
			public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onUnhandledKeyEvent()");
				super.onUnhandledKeyEvent(view, event);
			}

			@Override
			public void onScaleChanged(WebView view, float oldScale,
					float newScale) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onScaleChanged()");
				super.onScaleChanged(view, oldScale, newScale);
			}

			@SuppressLint("NewApi")
			@Override
			public void onReceivedLoginRequest(WebView view, String realm,
					String account, String args) {
				System.out
						.println("resp1onse WebkitPackageActivity.onCreate(...).new WebViewClient() {...}.onReceivedLoginRequest()");
				super.onReceivedLoginRequest(view, realm, account, args);
			}

		});
	}

	private void setWebChromeClient() {
		WebChromeClient webChromeClient;
		webChromeClient = new WebChromeClient() {
			public void openFileChooser(ValueCallback<Uri> uploadMsg) {
				System.out
						.println("resp1onse MainActivity: public void openFileChooser(ValueCallback<Uri> uploadMsg) { start internal 7");
				System.out
						.println("resp1onse MainActivity: public void openFileChooser(ValueCallback<Uri> uploadMsg) { end internal 7");
			}

			@Override
			public Bitmap getDefaultVideoPoster() {
				System.out
						.println("resp1onse MainActivity: public Bitmap getDefaultVideoPoster() { start internal 7");
				System.out
						.println("resp1onse MainActivity: private void buildComponents() { end return if ");
				return super.getDefaultVideoPoster();
			}

			@Override
			public View getVideoLoadingProgressView() {
				System.out
						.println("resp1onse : getVideoLoadingProgressView " + 1);
				return super.getVideoLoadingProgressView();
			}

			public void onShowCustomView(View view,
					WebChromeClient.CustomViewCallback callback) {
				System.out
						.println("resp1onse MainActivity: public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) { start internal 7");
				System.out
						.println("resp1onse MainActivity: public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) { end internal 7");
			}

			@Override
			public void onHideCustomView() {
				System.out
						.println("resp1onse MainActivity: public void onHideCustomView() { start internal 7");
				System.out
						.println("resp1onse MainActivity: public void onHideCustomView() { end internal 7");
			}

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				System.out.println("resp1onse : onProgressChanged = "
						+ newProgress);
			}

			@Override
			public void onReceivedIcon(WebView view, Bitmap icon) {
				System.out
						.println("resp1onse MainActivity: public void onReceivedIcon(WebView view, Bitmap icon) { start internal 7");
				super.onReceivedIcon(view, icon);
				System.out
						.println("resp1onse MainActivity: public void onReceivedIcon(WebView view, Bitmap icon) { end internal 7");
			}

			@Override
			public boolean onCreateWindow(WebView view, final boolean dialog,
					final boolean userGesture, final Message resultMsg) {
				System.out
						.println("resp1onse MainActivity: public boolean onCreateWindow(WebView view, final boolean dialog, final boolean userGesture, final Message resultMsg) { start internal 7");

				System.out
						.println("resp1onse MainActivity: private void buildComponents() { end return if ");
				return true;
			}

			@Override
			public void onReceivedTitle(WebView view, String title) {
				System.out.println("resp1onse : onReceivedTitle = " + title);
				super.onReceivedTitle(view, title);
			}

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				System.out
						.println("resp1onse MainActivity: public boolean onJsAlert(WebView view, String url, String message, final JsResult result) { start internal 7");
				System.out
						.println("resp1onse MainActivity: private void buildComponents() { end return if ");
				return true;
			}

			@Override
			public boolean onJsConfirm(WebView view, String url,
					String message, final JsResult result) {
				System.out
						.println("resp1onse MainActivity: public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) { start internal 7");
				System.out
						.println("resp1onse MainActivity: private void buildComponents() { end return if ");
				return true;
			}

			@Override
			public boolean onJsPrompt(WebView view, String url, String message,
					String defaultValue, final JsPromptResult result) {
				System.out
						.println("resp1onse MainActivity: public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) { start internal 7");
				System.out
						.println("resp1onse MainActivity: private void buildComponents() { end return if ");
				return true;

			}
		};
		webView.setWebChromeClient(webChromeClient);
	}

	private void setDownloadListener() {
		webView.setDownloadListener(new DownloadListener() {

			@Override
			public void onDownloadStart(String url, String userAgent,
					String contentDisposition, String mimetype,
					long contentLength) {
				System.out.println("resp1onse : onDownloadStart " + url + " " + contentLength
						+ " " + mimetype + " " + contentDisposition);
			}
		});
	}

	@Override
	protected void onStop() {
		super.onStop();
//		webChromeClient.get
	}

}
