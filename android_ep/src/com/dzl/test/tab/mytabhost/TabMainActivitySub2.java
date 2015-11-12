package com.dzl.test.tab.mytabhost;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TabMainActivitySub2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("TabMainActivitySub2 22222222222222");
		setContentView(textView);
	}


}
