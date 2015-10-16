package com.dzl.test.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import com.dzl.test.R;

public class WebViewActivity extends Activity {
	
	String data =   "<html>"
			+ "        <script language=\"javascript\">"
			+ "                    alert(\"1\");"
			+ "            /* This function is invoked by the activity */"
			+ "                function wave() {"
			+ "                    alert(\"1\");"
			//+ "                        document.getElementById(\"droid\").src=\"android_waving.png\";"
			+ "                        alert(\"2\");"
			+ "                }"
			+ "                 /* This function is invoked by the activity */"
			+ "                function waveBack() {"
			+ "                    alert(\"1\");"
			//+ "                        document.getElementById(\"droid\").src=\"android_normal.png\";"
			+ "                        alert(\"2\");"
			+ "                }"
			+ "        </script>"
			+ "        <body>"
			+ "            <!-- Calls into the javascript interface for the activity -->"
			+ "            <a onClick=\"window.demo.callAndroid()\"><div style=\"width:80px;"
			+ "                        margin:0px auto;"
			+ "                        padding:10px;"
			+ "                        text-align:center;"
			+ "                        border:2px solid #202020;\" >"
			+ "                                <br>"
			+ "                                Click me!"
			+ "                </div></a>"
			+ "        </body>"
			+ "</html>";
	WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		webView = new WebView(this);

        //demo1();
        demo2();
        
		
	}

	@SuppressLint("JavascriptInterface")
	private void demo2() {
		setContentView(R.layout.webview);
		webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
        webView.setWebChromeClient(new MyWebChromeClient());
        // 将一个java对象绑定到一个javascript对象中,javascript对象名就是interfaceName,作用域是Global.
        webView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
//        webView.loadUrl("file:///android_asset/demo.html");
        webView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
        // 通过应用中按钮点击触发JS函数响应
        Button mCallJS = (Button) findViewById(R.id.mCallJS);
        mCallJS.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                        webView.loadUrl("javascript:wave()");
                }
        });
	}
	private void demo1() {
//		如果访问的页面中有Javascript，则webview必须设置支持Javascript。
		webView.getSettings().setJavaScriptEnabled(true);  
//        触摸焦点起作用
        webView.requestFocus();
//        取消滚动条
//        this.setScrollBarStyle(WebViewSCROLLBARS_OUTSIDE_OVERLAY);
		setContentView(webView);
        webView.setWebChromeClient(new WebChromeClient());
        
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(70);
        webView.setHorizontalScrollbarOverlay(true);
		
        webView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
        /*webView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");

		 private final class DemoJavaScriptInterface

		     {
		 &nbsp;DemoJavaScriptInterface(){}

		      public void clickonAndroid( final String order){
		          mHandler.post(newRunnable(){
		              @Override
		              public void run(){
		                        jsonText="{"name":""+order+""}";
		                 wv.loadUrl("javascript:wave("+jsonText+")");
		              }
		          });
		      }
		 }*/
	}
	
	private int i = 0;
	public Handler mHandler = new Handler();

    final class DemoJavaScriptInterface {
            DemoJavaScriptInterface() {
            }
            /**
             * This is not called on the UI thread. Post a runnable to invoke
             * loadUrl on the UI thread.
             */
            public void callAndroid() {
                    mHandler .post(new Runnable() {
                            public void run() {
                                    if (i % 2 == 0) {
                                            webView.loadUrl("javascript:wave()");
                                    } else {
                                            webView.loadUrl("javascript:waveBack()");
                                    }
                                    i++;
                            }
                    });
            }
    }

    /**
     * Provides a hook for calling "alert" from javascript. Useful for debugging
     * your javascript.
     */
    private String LOG_TAG = "RESP";
    final class MyWebChromeClient extends WebChromeClient {

			@Override
            public boolean onJsAlert(WebView view, String url, String message,
                            JsResult result) {
                    Log.d(LOG_TAG, message);
                    result.confirm();
                    return true;
            }
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	
    	if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
    	     webView.goBack();
    	     return true;
    	  }
    	return super.onKeyDown(keyCode, event);
    	}


}
