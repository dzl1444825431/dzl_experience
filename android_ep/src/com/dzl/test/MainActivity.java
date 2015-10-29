package com.dzl.test;

//import OverrideClassOrMethodUtils.ScannerUtils;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.Button;
import android.widget.ListView;

import com.dzl.test.anim.AnimActivity;
import com.dzl.test.animator.AnimatorActivity;
import com.dzl.test.baiduMap.BaiduMapActivity;
import com.dzl.test.baidupush.BaiduPushActivity;
import com.dzl.test.bitmap.BitMapActivity;
import com.dzl.test.canvas.CanvasActivity;
import com.dzl.test.countFile.CountFileActivity;
import com.dzl.test.cpu.CpuActivity;
import com.dzl.test.drawable.DrawableActivity;
import com.dzl.test.gallery.GalleryActivity;
import com.dzl.test.gallery.SpinnerActivity;
import com.dzl.test.install.InstallApkActivity;
import com.dzl.test.installResultTest.InstallResultActivity;
import com.dzl.test.linearLayout.LinearLayoutActivity;
import com.dzl.test.listview.ListViewActivity;
import com.dzl.test.matrix2.MatrixActivity;
import com.dzl.test.md5.Md5CopyFileActivity;
import com.dzl.test.memory.MemoryActivity;
import com.dzl.test.phoneInfo.PhoneInfoActivity;
import com.dzl.test.popupWindow.PopupWindowActivity;
import com.dzl.test.progressBar.ProgressBarActivity;
import com.dzl.test.progressBar.ProgressBarLoadingActivity;
import com.dzl.test.progressCheck.CheckAppRunningActivity;
import com.dzl.test.ps.PS_R_Activity;
import com.dzl.test.relativeLayout.RelativeLayoutActivity;
import com.dzl.test.shell.PsShellActivity;
import com.dzl.test.sim.Sim_CpuActivity;
import com.dzl.test.textview.Multi_lineTextViewActivity;
import com.dzl.test.textview.TextViewActivity;
import com.dzl.test.typeArray.TypeArrayActivity;
import com.dzl.test.util.ClassMethodFieldUtilActivity;
import com.dzl.test.view.ViewActivity;
import com.dzl.test.view.ViewActivity2;
import com.dzl.test.viewPager.ViewPagerActivity;
import com.dzl.test.volley.VolleyActivity;
import com.dzl.test.webview.WebViewActivity;
import com.dzl.test.webview.WebViewDestopActivity;
import com.dzl.test.webview.WebkitPackageActivity;

public class MainActivity extends BaseActivity implements OnClickListener {

	private LayoutParams params_match;
	private LayoutParams params_wrap;

	Class<?>[] clz = {
			InstallApkActivity.class,
			TestEmulatorActivity.class,
			TestServiceActivity.class,
			BatchInstallActivity.class,
			BatchUninstallActivity.class,
			Md5CopyFileActivity.class,
			InstallResultActivity.class,
			WebViewActivity.class,
			WebViewDestopActivity.class,
			CountFileActivity.class,
			WebkitPackageActivity.class,
			PopupWindowActivity.class,
			AnimatorActivity.class,
			BitMapActivity.class,
			MemoryActivity.class,
			DrawableActivity.class,
			ViewActivity.class,
			ViewActivity2.class,
			AnimActivity.class,
			CpuActivity.class,
			MatrixActivity.class,
			TypeArrayActivity.class,
//			DrawableActivity.class,
			CanvasActivity.class,
			ViewPagerActivity.class,
			TextViewActivity.class,
			Multi_lineTextViewActivity.class,
			ProgressBarActivity.class,
			LinearLayoutActivity.class,
			RelativeLayoutActivity.class,
			CheckAppRunningActivity.class,
			ListViewActivity.class,
			GalleryActivity.class,
			SpinnerActivity.class,
			ClassMethodFieldUtilActivity.class,
			VolleyActivity.class,
			PsShellActivity.class,
			MainActivity1.class,
			Sim_CpuActivity.class,
			BaiduMapActivity.class,
			BaiduPushActivity.class,
			PS_R_Activity.class,
			PhoneInfoActivity.class,
			ProgressBarLoadingActivity.class,
	};
	
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		params_match = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params_wrap = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		listView = new ListView(this);
		listView.setLayoutParams(params_match);

		setContentView(listView);

		ArrayList<Class<?>> classes = new ArrayList<Class<?>>(clz.length);

		for (int i = clz.length - 1; i >= 0; i--) {
			classes.add(clz[i]);
		}
		listView.setDivider(null);

		listView.setAdapter(new BaseAdapterDzl<Class<?>>(this, classes) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Holder holder;
				if (convertView == null) {
					convertView = new Button(mActivity);
					convertView.setId(position + 2000);
					holder = new Holder();
					holder.button = (Button) convertView.findViewById(position + 2000);
					convertView.setTag(holder);
					convertView.setLayoutParams(params_wrap);
				} else {

					holder = (Holder) convertView.getTag();
				}

				final int p = position;
				final Class<?> cls = mData.get(p);
				holder.button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						startActivity(new Intent(mActivity, cls));
					}
				});
				holder.button.setText(cls.getSimpleName());
				return convertView;
			}
		});

	}

	class Holder {
		Button button;
	}

	@Override
	public void onClick(View v) {

	}

}
