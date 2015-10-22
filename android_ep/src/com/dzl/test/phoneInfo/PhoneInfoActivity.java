package com.dzl.test.phoneInfo;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.location.LocationClientOption.LocationMode;
import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class PhoneInfoActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_phoneinfo);
		
		button0 = (Button) findViewById(R.id.button0);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		button7 = (Button) findViewById(R.id.button7);
		button8 = (Button) findViewById(R.id.button8);
		button9 = (Button) findViewById(R.id.button9);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView4 = (TextView) findViewById(R.id.textView4);
		textView5 = (TextView) findViewById(R.id.textView5);
		textView6 = (TextView) findViewById(R.id.textView6);
		textView7 = (TextView) findViewById(R.id.textView7);
		textView8 = (TextView) findViewById(R.id.textView8);
		textView9 = (TextView) findViewById(R.id.textView9);
		
		setOnClickListener(button0);
		setOnClickListener(button1);
		setOnClickListener(button2);
		setOnClickListener(button3);
		setOnClickListener(button4);
		setOnClickListener(button5);
		setOnClickListener(button6);
		setOnClickListener(button7);
		setOnClickListener(button8);
		setOnClickListener(button9);
		
		
		getOpenTime();
		getSystemTime();
		getWindowSize();
		getWifiName();
		getNetworkType();
		getMacAddress();
		getIp();
//		getGpsAddress();
		getUniquePsuedoID();
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			textView1.setText(getOpenTime());
			break;
		case R.id.button2:
			textView2.setText(getSystemTime());
			break;
		case R.id.button3:
			textView3.setText(getWindowSize());
			break;
		case R.id.button4:
			textView4.setText(getWifiName());
			break;
		case R.id.button5:
			textView5.setText(getNetworkType());
			break;
		case R.id.button6:
			textView6.setText(getMacAddress());
			break;
		case R.id.button7:
			textView7.setText(getIp());
			break;
		case R.id.button9:
			textView9.setText(getUniquePsuedoID());
			break;
		case R.id.button8:
			getGpsAddress();
			break;
		case R.id.button0:
			textView1.setText(getOpenTime());
			textView2.setText(getSystemTime());
			textView3.setText(getWindowSize());
			textView4.setText(getWifiName());
			textView5.setText(getNetworkType());
			textView6.setText(getMacAddress());
			textView7.setText(getIp());
			getGpsAddress();
			break;

		default:
			break;
		}
	}

	/**
	 * 系统已运行时间（包括休眠）
	 */
	private String getOpenTime() {
		
		System.out.println("resp1onse : SystemClock.elapsedRealtime() 秒 = " + SystemClock.elapsedRealtime() / 1000); 
		System.out.println("resp1onse : SystemClock.elapsedRealtime() 时 = " + SystemClock.elapsedRealtime() /(1000 * 60 * 60));
		long elapsedRealtime = SystemClock.elapsedRealtime();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd HH:mm:ss");
//		dateFormat.format(new Date(elapsedRealtime));
		
		return dateFormat.format(new Date(elapsedRealtime));
	}

	/**
	 * 系统当前时间
	 */
	private String getSystemTime() {
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("resp1onse : getSystemTime = " + dateFormat.format(c.getTime()));
		return dateFormat.format(c.getTime());
	}

	/**
	 * 屏幕大小
	 */
	private String getWindowSize() {
		
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		
		System.out.println("resp1onse : metrics.widthPixels * heightPixels = " + metrics.widthPixels + " * " + metrics.heightPixels);
		System.out.println("resp1onse : metrics.density = " + metrics.density +" metrics.densityDpi = " + metrics.densityDpi +" metrics.scaledDensity = " + metrics.scaledDensity);
		return metrics.widthPixels + " * " + metrics.heightPixels + " : " + metrics.density;
	}

	/**
	 * WiFi名称
	 */
	private String getWifiName() {
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		System.out.println("resp1onse : wifi.getConnectionInfo().getSSID() = " + wifi.getConnectionInfo().getSSID());
		return wifi.getConnectionInfo().getSSID();
	}

	/**
	 * 网络类型（流量2,3,4G、WiFi）
	 */
	private String getNetworkType() {
		
		
		ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		// 获取网络的状态信息，有下面三种方式
		if (connectionManager != null) {

			NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
			if (networkInfo != null) {

				System.out.println("resp1onse : networkInfo.getDetailedState()		   = " + networkInfo.getDetailedState()); // ：获取详细状态。
				System.out.println("resp1onse : networkInfo.getExtraInfo()			   = " + networkInfo.getExtraInfo()); // ：获取附加信息。
				System.out.println("resp1onse : networkInfo.getReason()			       = " + networkInfo.getReason()); // ：获取连接失败的原因。
				System.out.println("resp1onse : networkInfo.getType()				   = " + networkInfo.getType()); // ：获取网络类型(一般为移动或Wi-Fi)。
				System.out.println("resp1onse : networkInfo.getTypeName()			   = " + networkInfo.getTypeName()); // ：获取网络类型名称(一般取值“WIFI”或“MOBILE”)。
				System.out.println("resp1onse : networkInfo.isAvailable()			   = " + networkInfo.isAvailable()); // ：判断该网络是否可用。
				System.out.println("resp1onse : networkInfo.isConnected()			   = " + networkInfo.isConnected()); // ：判断是否已经连接。
				System.out.println("resp1onse : networkInfo.isConnectedOrConnecting() = "+ networkInfo.isConnectedOrConnecting()); // ：判断是否已经连接或正在连接。
				System.out.println("resp1onse : networkInfo.isFailover()			   = " + networkInfo.isFailover()); // ：判断是否连接失败。
				System.out.println("resp1onse : networkInfo.isRoaming()			       = " + networkInfo.isRoaming()); // ：判断是否漫游
				return "" + networkInfo.getType();
			}

		}
		return null;
	}

	/**
	 * WLAN Mac 地址
	 */
	private String getMacAddress() {
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		System.out.println("resp1onse : wifi.getWifiState() = " + wifi.getWifiState());
		System.out.println("resp1onse : wifi.getConnectionInfo() = " + wifi.getConnectionInfo());
		System.out.println("resp1onse : wifi.isWifiEnabled() = " + wifi.isWifiEnabled());
		System.out.println("resp1onse : wifi.getConnectionInfo().getMacAddress() = " + wifi.getConnectionInfo().getMacAddress());
		System.out.println("resp1onse : wifi.getConnectionInfo().getIpAddress() = " + wifi.getConnectionInfo().getIpAddress());
		System.out.println("resp1onse : wifi.getConnectionInfo().getLinkSpeed() = " + wifi.getConnectionInfo().getLinkSpeed());
		System.out.println("resp1onse : wifi.getConnectionInfo().getHiddenSSID() = " + wifi.getConnectionInfo().getHiddenSSID());
		System.out.println("resp1onse : wifi.getConnectionInfo().getRssi() = " + wifi.getConnectionInfo().getRssi());
		System.out.println("resp1onse : wifi.getConnectionInfo().getBSSID() = " + wifi.getConnectionInfo().getBSSID());
		System.out.println("resp1onse : wifi.getConnectionInfo().getSSID() = " + wifi.getConnectionInfo().getSSID());
		System.out.println("resp1onse : wifi.getConnectionInfo().getNetworkId() = " + wifi.getConnectionInfo().getNetworkId());
		return wifi.getConnectionInfo().getMacAddress();
	}

	/**
	 * 手机当前的网络IP
	 */
	private String getIp() {
		String ip = null;
		
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
						System.out.println("resp1onse : ip inetAddress.getHostAddress() = " + inetAddress.getHostAddress());
						ip = inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException ex) {
			System.out.println("resp1onse : ip WifiPreference IpAddress" + ex.toString());
		}
		
		if (ip == null) {
			WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
	        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
	        // 获取32位整型IP地址
	        int ipAddress = wifiInfo.getIpAddress();
	        
	        //返回整型地址转换成“*.*.*.*”地址
	        ip = String.format("%d.%d.%d.%d",
	                (ipAddress & 0xff), (ipAddress >> 8 & 0xff),
	                (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
		}
		
		return ip;
	}

	/**
	 * 手机定位，通过Gps精准定位、大致定位
	 * @param b 
	 */
	private String getGpsAddress() {
			
		if (mLocationClient == null) {
			mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
			mLocationClient.registerLocationListener( new BDLocationListener() {
				
				@Override
				public void onReceiveLocation(BDLocation location) {
					System.out.println("resp1onse : location = " + log(location));
					textView8.setText(log(location));
				}
			});
			mLocationClient.setLocOption(initLocation());
			mLocationClient.start();
		}
		
		return "wait todo";
	}
	
	protected String log(BDLocation location) {
		//Receive Location
        StringBuffer sb = new StringBuffer(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\nerror code : ");
        sb.append(location.getLocType());
        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());
        sb.append("\nradius : ");
        sb.append(location.getRadius());
        if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// 单位：公里每小时
            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// 单位：米
            sb.append("\ndirection : ");
            sb.append(location.getDirection());// 单位度
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append("\ndescribe : ");
            sb.append("gps定位成功");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            //运营商信息
            sb.append("\noperationers : ");
            sb.append(location.getOperators());
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");
        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            sb.append("\ndescribe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
        }
        sb.append("\nlocationdescribe : ");
        sb.append(location.getLocationDescribe());// 位置语义化信息
        List<Poi> list = location.getPoiList();// POI数据
        if (list != null) {
            sb.append("\npoilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\npoi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
        System.out.println("resp1onse : location.toString() = " + sb.toString());
        return sb.toString();
	}

	@Override
    protected void onStop() {
		if (mLocationClient != null) {
			mLocationClient.stop();
		}
        super.onStop();
    }
	
	private LocationClientOption initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");//bd09ll  可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        
        return option;
    }
	
	public BDLocationListener myListener;
	public LocationClient mLocationClient;
	
	private Button button0;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private TextView textView5;
	private TextView textView6;
	private TextView textView7;
	private TextView textView8;
	private TextView textView9;

	//获得独一无二的Psuedo ID
	public String getUniquePsuedoID() {
	       String serial = null;

	       String m_szDevIDShort = "35" + 
	            Build.BOARD.length()%10+ Build.BRAND.length()%10 + 

	            Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 + 

	            Build.DISPLAY.length()%10 + Build.HOST.length()%10 + 

	            Build.ID.length()%10 + Build.MANUFACTURER.length()%10 + 

	            Build.MODEL.length()%10 + Build.PRODUCT.length()%10 + 

	            Build.TAGS.length()%10 + Build.TYPE.length()%10 + 

	            Build.USER.length()%10 ; //13 位
	       System.out.println("resp1onse : Build.BOARD            = " +      Build.BOARD        );
	       System.out.println("resp1onse : Build.BOOTLOADER       = " +      Build.BOOTLOADER   );
	       System.out.println("resp1onse : Build.BRAND            = " +      Build.BRAND        );
	       System.out.println("resp1onse : Build.CPU_ABI          = " +      Build.CPU_ABI      );
	       System.out.println("resp1onse : Build.CPU_ABI2         = " +      Build.CPU_ABI2     );
	       System.out.println("resp1onse : Build.DEVICE           = " +      Build.DEVICE       );
	       System.out.println("resp1onse : Build.DISPLAY          = " +      Build.DISPLAY      );
	       System.out.println("resp1onse : Build.FINGERPRINT      = " +      Build.FINGERPRINT  );
	       System.out.println("resp1onse : Build.HARDWARE         = " +      Build.HARDWARE     );
	       System.out.println("resp1onse : Build.HOST             = " +      Build.HOST         );
	       System.out.println("resp1onse : Build.ID               = " +      Build.ID           );
	       System.out.println("resp1onse : Build.MANUFACTURER     = " +      Build.MANUFACTURER );
	       System.out.println("resp1onse : Build.MODEL            = " +      Build.MODEL        );
	       System.out.println("resp1onse : Build.PRODUCT          = " +      Build.PRODUCT      );
	       System.out.println("resp1onse : Build.RADIO            = " +      Build.RADIO        );
	       System.out.println("resp1onse : Build.SERIAL           = " +      Build.SERIAL       );
	       System.out.println("resp1onse : Build.TAGS             = " +      Build.TAGS         );
	       System.out.println("resp1onse : Build.TIME             = " +      Build.TIME         );
	       System.out.println("resp1onse : Build.TYPE             = " +      Build.TYPE         );
	       System.out.println("resp1onse : Build.UNKNOWN          = " +      Build.UNKNOWN      );
	       System.out.println("resp1onse : Build.USER             = " +      Build.USER         );

	       System.out.println("resp1onse :CODENAME = " +Build.VERSION.CODENAME );
	       System.out.println("resp1onse :INCREMENTAL = " +Build.VERSION.INCREMENTAL );
	       System.out.println("resp1onse :RELEASE = " +Build.VERSION.RELEASE );
	       System.out.println("resp1onse :SDK = " +Build.VERSION.SDK );
	       System.out.println("resp1onse :SDK_INT = " +Build.VERSION.SDK_INT );
	       
	    try {
	        serial = android.os.Build.class.getField("SERIAL").get(null).toString();
	       //API>=9 使用serial号
//	        System.out.println("resp1onse :" + new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString());
	    } catch (Exception exception) {
	        //serial需要一个初始化
	        serial = "serial"; // 随便一个初始化
	    }
	    //使用硬件信息拼凑出来的15位号码
	    System.out.println("resp1onse :" + new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString());
	    return  " \nBOARD        = " +      Build.BOARD        
	    		+ " \nBOOTLOADER   = " +      Build.BOOTLOADER   
	    		+ " \nBRAND        = " +      Build.BRAND        
	    		+ " \nCPU_ABI      = " +      Build.CPU_ABI      
	    		+ " \nCPU_ABI2     = " +      Build.CPU_ABI2     
	    		+ " \nDEVICE       = " +      Build.DEVICE       
	    		+ " \nDISPLAY      = " +      Build.DISPLAY      
	    		+ " \nFINGERPRINT  = " +      Build.FINGERPRINT  
	    		+ " \nHARDWARE     = " +      Build.HARDWARE     
	    		+ " \nHOST         = " +      Build.HOST         
	    		+ " \nID           = " +      Build.ID           
	    		+ " \nMANUFACTURER = " +      Build.MANUFACTURER 
	    		+ " \nMODEL        = " +      Build.MODEL        
	    		+ " \nPRODUCT      = " +      Build.PRODUCT      
	    		+ " \nRADIO        = " +      Build.RADIO        
	    		+ " \nSERIAL       = " +      Build.SERIAL       
	    		+ " \nTAGS         = " +      Build.TAGS         
	    		+ " \nTIME         = " +      Build.TIME         
	    		+ " \nTYPE         = " +      Build.TYPE         
	    		+ " \nUNKNOWN      = " +      Build.UNKNOWN      
	    		+ " \nUSER         = " +      Build.USER ;
	}
}
