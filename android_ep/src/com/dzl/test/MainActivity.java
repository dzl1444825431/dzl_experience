package com.dzl.test;

//import OverrideClassOrMethodUtils.ScannerUtils;
import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.Button;
import android.widget.ListView;

import com.dzl.test.activity.ActivityActivity;
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
import com.dzl.test.login.LoginActivity;
import com.dzl.test.matrix2.MatrixActivity;
import com.dzl.test.md5.Md5CopyFileActivity;
import com.dzl.test.memory.MemoryActivity;
import com.dzl.test.password.PasswordActivity;
import com.dzl.test.phoneInfo.PhoneInfoActivity;
import com.dzl.test.popupWindow.PopupWindowActivity;
import com.dzl.test.progressBar.ProgressBarActivity;
import com.dzl.test.progressBar.ProgressBarLoadingActivity;
import com.dzl.test.progressCheck.CheckAppRunningActivity;
import com.dzl.test.ps.PS_R_Activity;
import com.dzl.test.register.RegisterActivity;
import com.dzl.test.relativeLayout.RelativeLayoutActivity;
import com.dzl.test.shell.PsShellActivity;
import com.dzl.test.sim.Sim_CpuActivity;
import com.dzl.test.slidingMenu.SlidingMenuActivity;
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

	
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		params_match = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params_wrap = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		listView = new ListView(this);
		listView.setLayoutParams(params_match);

		setContentView(listView);

		ArrayList<Class<?>> classes = new ArrayList<Class<?>>(50);
		ArrayList<Class<?>> activitys = listPackageActivitys(getPackageName());
		
		if (activitys == null || activitys.size() == 0) {
			
			for (int i = clz.length - 1; i >= 0; i--) {
				classes.add(clz[i]);
			}
			
		}else {
			int len = activitys.size();
			for (int i = len - 1; i >= 0; i--) {
				classes.add(activitys.get(i));
			}
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
	
	
	private ArrayList<Class<?>> listPackageActivitys(String packageName) {
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>(50);
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			if (packageInfo != null) {
				ActivityInfo[] activityInfos = packageInfo.activities;
				if (activityInfos != null) {
					for (ActivityInfo activityInfo : activityInfos) {
						
						
						String name = activityInfo.name;
						try {
							Class<?> c = Class.forName(name);
							classes.add(c);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return classes;
		
	}

	class Holder {
		Button button;
	}

	@Override
	public void onClick(View v) {

	}
	
	
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
			LoginActivity.class,
			RegisterActivity.class,
			PasswordActivity.class,
			SlidingMenuActivity.class,
			ActivityActivity.class,
	};
	

}
