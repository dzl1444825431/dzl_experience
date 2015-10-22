package com.dzl.test.webview;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class WebViewDestopActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setShutcut();
		hasShortcut();
//		getLauncherPkgName(this);
//		printDeskShortcutApps(this);
	}

	private static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
	private static final String ACTION_UNINSTALL_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT";

	/**
	 * 是否可以有多个快捷方式的副本
	 */
	static final String EXTRA_SHORTCUT_DUPLICATE = "duplicate";
	private static final String PACKAGE_NAME = null;

	void setShutcut() {

		Intent shortcutIntent = new Intent(ACTION_INSTALL_SHORTCUT);
		shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
				"测试webviewShutcut");
		// 是否可以有多个快捷方式的副本，参数如果是true就可以生成多个快捷方式，如果是false就不会重复添加
		shortcutIntent.putExtra(EXTRA_SHORTCUT_DUPLICATE, false);
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);

		// 要添加的应用程序的ComponentName，即应用程序包名+activity的名字
		intent.setComponent(new ComponentName(this.getPackageName(),
				"com.dzl.test.webview.WebViewDestopActivity"));

		shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
		shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				Intent.ShortcutIconResource.fromContext(this,
						R.drawable.ic_launcher));
		sendBroadcast(shortcutIntent);
	}

	void deteleShutcut() {

		
		 /* Intent intent = new Intent(ACTION_UNINSTALL_SHORTCUT);
		  intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, appName); //
		 // 要删除的应用程序的ComponentName，即应用程序包名+activity的名字 ComponentName comp = new
		  ComponentName( info.activityInfo.packageName,
		  info.activityInfo.name);
		 intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent()
		  .setComponent(comp).setAction("android.intent.action.MAIN"));
		  sendBroadcast(intent);*/
		 

	}

	protected String getAuthorityFromPermission(Context context, String permission) {
		if (permission == null)
			return null;
		List<PackageInfo> packs = context.getPackageManager()
				.getInstalledPackages(PackageManager.GET_PROVIDERS);
		if (packs != null) {
			for (PackageInfo pack : packs) {
				ProviderInfo[] providers = pack.providers;
				if (providers != null) {
					for (ProviderInfo provider : providers) {
						if (permission.equals(provider.readPermission))
							return provider.authority;
						if (permission.equals(provider.writePermission))
							return provider.authority;
					}
				}
			}
		}

		return null;
	}
	
	/**
	 * 判断是否已有快捷方式
	 */
	protected boolean hasShortcut(Context context,String shortCutName) {
        boolean has = false;
        final ContentResolver cr = context.getContentResolver();
        final String AUTHORITY = getAuthorityFromPermission(context, "com.android.launcher.permission.READ_SETTINGS");

        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites?notify=true");
        
        String shortCutString ="";
		//确认content Provider中是否有快捷键信息
        Cursor c = cr.query(CONTENT_URI,
                            new String[] {"title","iconResource" },
                            "title=?",
                            new String[] {shortCutString .trim()}, 
                            null);
        if(c != null && c.getCount() > 0){
            has= true ;
        }
        return has;
    }

	/**
	 * 添加快捷方式
	 */
	protected void addShortCut(Context mContext)
    {              

        boolean has = hasShortcut(mContext, "appName");

        if(has)
        {
            return;
        }

        Intent shortCutIntent = null;
        int shortCutNameId = R.string.app_name;
        int shortCutIconId = R.drawable.ic_launcher;
        String pkg = PACKAGE_NAME;

        boolean installed = isInstalledApp(mContext);


        if(installed)
        {

            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(pkg);          
            List<ResolveInfo> apps = mContext.getPackageManager().queryIntentActivities(resolveIntent, PackageManager.GET_ACTIVITIES);
            

            shortCutIntent = new Intent();
            if((apps != null) && (apps.size() != 0))
            {
                shortCutIntent.setComponent(new ComponentName(pkg, apps.get(0).activityInfo.name));
            }
        }
        else 
        {
            shortCutIntent = new Intent(mContext.getApplicationContext(), WebViewDestopActivity.class);
        }
        

        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");  
        
        //快捷方式的名称  
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, mContext.getString(shortCutNameId));  
        shortcut.putExtra("duplicate", false); //不允许重复创建  
 
        //快捷方式的图标  
        ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(mContext.getApplicationContext(), shortCutIconId);  
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);  
        
        //程序入口
        shortCutIntent.setAction(Intent.ACTION_MAIN);
        shortCutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortCutIntent);

        mContext.sendBroadcast(shortcut);   
    }
	
	protected boolean isInstalledApp(Context mContext) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 删除快捷方式
	 */
	protected void deleteShortCut(Context mContext)
    {
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        Intent shortCutIntent = new Intent(mContext.getApplicationContext(), WebViewDestopActivity.class);
        shortCutIntent.setAction(Intent.ACTION_MAIN);
        shortCutIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        // 快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, mContext.getString(R.string.app_name));

        // 注意: ComponentName的第二个参数必须是完整的类名（包名+类名），否则无法删除快捷方式
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortCutIntent);

        mContext.sendBroadcast(shortcut);
    }
	
	
	/**
	  * 是否已创建快捷方式
	  * @return
	  */
	 protected boolean hasShortcut()
	 {
		 
	        boolean isInstallShortcut = false;
	        String uriStr = null;
	        if (android.os.Build.VERSION.SDK_INT < 8) {
	        	uriStr = "com.android.launcher";
	        } else {
	        	uriStr = "com.android.launcher2";
	        }
	        uriStr = getLauncherPkgName(this);
//	        final String AUTHORITY ="com.android.launcher.settings2";
	        
	        final Uri CONTENT_URI = Uri.parse("content://" + uriStr + ".settings/favorites?notify=true");
//	        Cursor c = getContentResolver().query(CONTENT_URI, new String[] {"title", "iconResource" }, "title=?", new String[] {getString(R.string.app_name).trim()}, null);
	        Cursor c = getContentResolver().query(CONTENT_URI, new String[] {"title", "iconResource" }, null, null, null);
	        if (c != null) {
				
	        	while (c.moveToNext()) {
	        		System.out.println("resp1onse : " + c.getColumnIndex("title") + "   " + c.getString(1));
	        	}
			}
	        System.out.println("resp1onse : " + (c==null));
	        /*if(null != c && c.getCount() > 0)
	        {
	            isInstallShortcut = true ;
	        }*/
	        
	        return isInstallShortcut;
	 }
	 
	 
	 
	 public static void printDeskShortcutApps(Context context) {

         String launcherPkgName = getLauncherPkgName(context);
         if (launcherPkgName == null) {
              return ;
         }

         PackageManager pm = context.getPackageManager();

        List<PackageInfo> packs = pm.getInstalledPackages(0);

       for (PackageInfo info: packs ) {
             String lableName = info.applicationInfo.loadLabel(pm).toString();
             if (hasShortcut(context, lableName, launcherPkgName)) {
                      String pkgName = info.packageName;     
                      Log.i("test", "pkgName = " + pkgName);

               }

       }
       
	 }
	 
	 /*
	 * 获取launcherApp
	 */
	 private static String getLauncherPkgName(Context context) {
	              ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	               List<RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
	               for (RunningAppProcessInfo info: list) {
	                     String pkgName = info.processName;
	                     if (pkgName.contains("launcher") && pkgName.contains("android")) {
	                          System.out.println("resp1onse : " + pkgName);
	                          return pkgName;
	                      }

	                }
	                return null;
	 }

	 
	 /**
	 * 判断是否存在快捷方式（升级版本）
	 * */
	 private static boolean hasShortcut(Context context, String lableName,String launcherPkgName) {

	        String url = "";
	        url = "content://" + launcherPkgName + ".settings/favorites?notify=true";

	        ContentResolver resolver = context.getContentResolver();
	        Cursor cursor = resolver.query(Uri.parse(url), null, "title=?",
	              new String[] { lableName }, null);

	        if (cursor == null) {
	            return false;
	         }

	        if (cursor.getCount()>0) {
	              cursor.close();
	             return true;
	         }else {
	             cursor.close();
	             return false;
	        }
	 }
	 
}
