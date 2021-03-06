package com.dzl.test.activity;

import java.util.ArrayList;
import java.util.List;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ActivityActivity extends BaseActivity {

	private Button button1;
	private TextView textView1;
	private TextView textView2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_activity);
//		System.out.println("resp1onse : Environment.getExternalStorageDirectory(); = " 
//		+ Environment.getExternalStoragePublicDirectory("DCIM"));

		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		button1 = (Button) findViewById(R.id.button1);
		
		setOnClickListener(button1);
		
		listAllActivities();
		
		listPackageActivitys(getPackageName());
		
	}

	private String listPackageActivitys(String packageName) {
		StringBuffer activitys = new StringBuffer();
		
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			if (packageInfo != null) {
				ActivityInfo[] activityInfos = packageInfo.activities;
				if (activityInfos != null) {
					for (ActivityInfo activityInfo : activityInfos) {
						System.out.println("resp1onse : activityInfo = " + activityInfo.name);
						activitys.append(activityInfo.name + "\n");
					}
				}
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return activitys.toString();
		
	}
	
	/**
	 * 列出所有包的aitivitys
	 */
	public void listAllActivities()
    {
        try {
			List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
			
			System.out.println("resp1onse : packages.size() = " + packages.size());
			
			for(PackageInfo pack : packages)
			{
			    ActivityInfo[] activityInfo = getPackageManager().getPackageInfo(pack.packageName, PackageManager.GET_ACTIVITIES).activities;
			    System.out.println("resp1onse : " +  pack.packageName + " has total " + ((activityInfo==null)?0:activityInfo.length) + " activities");
			    if(activityInfo!=null)
			    {
			        for(int i=0; i<activityInfo.length; i++)
			        {
			            System.out.println("resp1onse : v = PC" + pack.packageName + " ::: " + activityInfo[i].name);
			        }
			    }
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void onClick(View v) {
		super.onClick(v);
		
		switch (v.getId()) {
		case R.id.button1:
			textView1.setText(getActivities(this).toString());
			textView2.setText(listPackageActivitys(getPackageName()));
			break;

		default:
			break;
		}
	}
	
	
	/**
	 * 获取所有主activity
	 * 
	 * @param ctx
	 * @return
	 */
	public static ArrayList<String> getActivities(Context ctx) {
		ArrayList<String> result = new ArrayList<String>();
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.setPackage(ctx.getPackageName());
		for (ResolveInfo info : ctx.getPackageManager().queryIntentActivities(intent, 0)) {
			result.add(info.activityInfo.name);
		}
		return result;
	}
	
	/**
	 * 获取所有主activity
	 * 
	 * @param ctx
	 * @return
	 */
	public static ArrayList<String> getActivitiesDefault(Context ctx) {
		ArrayList<String> result = new ArrayList<String>();
		Intent intent = new Intent(Intent.ACTION_DEFAULT, null);
		intent.setPackage(ctx.getPackageName());
		for (ResolveInfo info : ctx.getPackageManager().queryIntentActivities(intent, 0)) {
			result.add(info.activityInfo.name);
		}
		return result;
	}

	/**
	 * 检查有没有应用程序来接受处理你发出的intent
	 * 
	 * @param context
	 * @param action
	 * @return
	 */
	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	/**
	 * Dip转px
	 * 
	 * @param ctx
	 * @param dip
	 * @return
	 */
	public static int dipToPX(final Context ctx, float dip) {
		return (int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, ctx.getResources().getDisplayMetrics());
	}
	
	/**
	 * 从底部显示view
	 * 类型一键分享动画显示 动画隐藏
	 */
	protected void showView() {
        final FrameLayout relativeLayout = new FrameLayout(this);
        Window window = this.getWindow();
//        window.setGravity(Gravity.BOTTOM);
//        window.setWindowAnimations(R.style.view_bottom);
        final ViewGroup decorView = (ViewGroup) window.getDecorView();
        // 把图层添加到顶层窗口中
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.BOTTOM;
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setBackgroundColor(0x99000000);

        final TextView textView = new TextView(this);
        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
        textView.setLayoutParams(layoutParams1);
        textView.setBackgroundColor(0xffffffff);
        textView.setText("窗口我来了窗口我来了窗口我来了窗口我来了窗口我来了窗口我来了窗口我来了窗口我来了窗口我来了窗口我来了窗口我来了窗口我来了窗口我来了窗口我来了");
        layoutParams1.gravity=Gravity.BOTTOM;

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.view_enter);
        textView.startAnimation(animation);

        relativeLayout.addView(textView);
        final Animation animation_exit = AnimationUtils.loadAnimation(ActivityActivity.this, R.anim.view_exit);
        animation_exit.setDuration(600);
        animation_exit.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setViewVisible(relativeLayout,G);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.startAnimation(animation_exit);

            }
        });
        decorView.addView(relativeLayout);

    }
}
