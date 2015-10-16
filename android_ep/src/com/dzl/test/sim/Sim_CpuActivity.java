package com.dzl.test.sim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import com.dzl.test.BatteryReceiver;
import com.dzl.test.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.telephony.TelephonyManager;

public class Sim_CpuActivity extends Activity implements SensorEventListener {
	
	BatteryReceiver batteryReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		getCpuInfo();
//		isUnusualPhone();
//		hasCompatibleCPU();
		
//		getBuild();
		getCpuInfo2();
		getVersion();
		
		getTotalMemory();
		getSDCardMemory();
		getRomMemroy();
		
		readTelephonyManager();
		displayBriefMemory();
		System.out.println("response : 系统总开机时间SystemClock.elapsedRealtime()=" + SystemClock.elapsedRealtime());
		getBluetooth();
		
		/*String t=android.provider.Settings.System.getString(getContentResolver(), "android_id"); 
	     System.out.println("response : " + "android_id="+ t);
	     
	     TelephonyManager telephonyManager=
	    		 (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	     
	     String imei=telephonyManager.getDeviceId();
	     System.out.println("response : " + "imei="+ imei);*/
	     
	     getLevel();
	     getSensorList();
	     TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);  
	     System.out.println("response : SIM卡的序列号11" + tm.getSimSerialNumber());//String
	}

	void getLevel() {
		//注册广播接受者java代码
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		intentFilter.addAction(Intent.ACTION_TIME_TICK);
		//创建广播接受者对象
		batteryReceiver = new BatteryReceiver();
		//注册receiver
		registerReceiver(batteryReceiver, intentFilter);
	}

	private void getBuild() {
		/*
	      * SystemProperties
	      *
1. Build.BOARD // 主板    
2. Build.BRAND // android系统定制商    
3. Build.CPU_ABI // cpu指令集    
4. Build.DEVICE // 设备参数    
5. Build.DISPLAY // 显示屏参数    
6. Build.FINGERPRINT // 硬件名称    
7. Build.HOST    
8. Build.ID // 修订版本列表    
9. Build.MANUFACTURER // 硬件制造商    
10. Build.MODEL // 版本    
11. Build.PRODUCT // 手机制造商    
12. Build.TAGS // 描述build的标签    
13. Build.TIME    
14. Build.TYPE // builder类型    
15. Build.USER
	      */
	     
			 System.out.println("response : " + android.os.Build.PRODUCT);
			 System.out.println("response : " + android.os.Build.BOARD);
			 System.out.println("response : " + android.os.Build.BOOTLOADER);
			 System.out.println("response : " + android.os.Build.BRAND);
			 System.out.println("response : " + android.os.Build.CPU_ABI);
			 System.out.println("response : " + android.os.Build.CPU_ABI2);
			 System.out.println("response : " + android.os.Build.DEVICE);
			 System.out.println("response : " + android.os.Build.DISPLAY);
			 System.out.println("response : " + android.os.Build.FINGERPRINT);
			 System.out.println("response : " + android.os.Build.HARDWARE);
			 System.out.println("response : " + android.os.Build.HOST);
			 System.out.println("response : " + android.os.Build.ID);
			 System.out.println("response : " + android.os.Build.MANUFACTURER);
			 System.out.println("response : " + android.os.Build.MODEL);
			 System.out.println("response : " + android.os.Build.PRODUCT);
			 System.out.println("response : " + android.os.Build.RADIO);
			// System.out.println("response : " + android.os.Build.SERIAL);
			 System.out.println("response : " + android.os.Build.TAGS);
			 System.out.println("response : " + android.os.Build.TIME);
			 System.out.println("response : " + android.os.Build.TYPE);
			 System.out.println("response : " + android.os.Build.USER);
	}

	public void getCpuInfo2() {
		String str1 = "/proc/cpuinfo";
		try {
			FileReader fr = new FileReader(str1);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			System.out.println("response : " + "cpu信息 start");
			while ((line = br.readLine()) != null) {
				System.out.println("response : " + line);
			}
			System.out.println("response : " + "cpu信息 end\n\n\n");
			
			br.close();
		} catch (IOException e) {
		}
	}
	
	public void getTotalMemory() {
        String str1 = "/proc/meminfo";
        try {
        	FileReader fr = new FileReader(str1);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			System.out.println("response : " + "Memory信息 start");
			while ((line = br.readLine()) != null) {
				System.out.println("response : " + line);
			}
			System.out.println("response : " + "Memory信息 end");
			System.out.println("response : ");
			System.out.println("response : ");
			System.out.println("response : ");
			br.close();
        } catch (IOException e) {  
        }  
    }
	
	 @SuppressLint("NewApi")
	private void displayBriefMemory() {    

	        final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);    

	        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();   

	        activityManager.getMemoryInfo(info); 
	        System.out.println("response : " + info.availMem + " " + info.threshold + " " + info.totalMem + " " + info.lowMemory);

	        System.out.println("response : " +"系统剩余内存:"+(info.availMem >> 10)+"k");   

	        System.out.println("response : " +"系统是否处于低内存运行："+info.lowMemory);

	        System.out.println("response : " +"当系统剩余内存低于"+info.threshold+"时就看成低内存运行");

	    } 
	
	public void getVersion() {
		String str1 = "/proc/version";  
		try {
			FileReader fr = new FileReader(str1);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			System.out.println("response : " + "Version信息 start");
			while ((line = br.readLine()) != null) {
				System.out.println("response : " + line);
			}
			System.out.println("response : " + "Version信息 end");
			
			br.close();
		} catch (IOException e) {  
		}  
	} 
	
	@SuppressLint("NewApi")
	public long[] getSDCardMemory() {  
        long[] sdCardInfo=new long[2];  
        String state = Environment.getExternalStorageState();  
        if (Environment.MEDIA_MOUNTED.equals(state)) {  
            File sdcardDir = Environment.getExternalStorageDirectory();  
            StatFs sf = new StatFs(sdcardDir.getPath());  
            long bSize = sf.getBlockSizeLong();  
            long bCount = sf.getBlockCountLong();  
            long availBlocks = sf.getAvailableBlocksLong();  
   
            sdCardInfo[0] = bSize * bCount;//总大小  
            sdCardInfo[1] = bSize * availBlocks;//可用大小  
            System.out.println("response : " + "SDCardMemory信息");
            System.out.println("response : bSize = " + bSize + " bCount = " + bCount + " availBlocks=" + availBlocks);
            System.out.println("response : " + sdCardInfo[0]);
            System.out.println("response : " + sdCardInfo[1]);
        }  
        return sdCardInfo;  
    }
	
	public long[] getRomMemroy() {  
        long[] romInfo = new long[2];  
        //Total rom memory  
        //romInfo[0] = getTotalInternalMemorySize();  
   
        //Available rom memory  
        File path = Environment.getDataDirectory();  
        StatFs stat = new StatFs(path.getPath());
        long bSize = stat.getBlockSize();
        long bCount = stat.getBlockCount();
        long availBlocks = stat.getAvailableBlocks();
        romInfo[0] = bSize * bCount;
        romInfo[1] = bSize * availBlocks;
        System.out.println("response : " + "RomMemroy信息");
        System.out.println("response : bSize = " + bSize + " bCount = " + bCount + " availBlocks=" + availBlocks);
        System.out.println("response : " + romInfo[0]);
        System.out.println("response : " + romInfo[1]);
         
        return romInfo;  
    }  
   
	
	public String[] getCpuInfo() {
		String str1 = "/proc/cpuinfo";
		String str2 = "";
		String[] cpuInfo = { "", "" };
		String[] arrayOfString;
		try {
			FileReader fr = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			for (int i = 2; i < arrayOfString.length; i++) {
				cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
			}
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			cpuInfo[1] += arrayOfString[2];
			localBufferedReader.close();
		} catch (IOException e) {
		}
		return cpuInfo;
	}

	boolean isUnusualPhone() {
		// android.os.SystemProperties is;
		try {

			Class<?> cl = Class.forName("android.os.SystemProperties");

			Object invoker = cl.newInstance();

			Method m = cl.getMethod("get", new Class[] { String.class,
					String.class });

			Object result = m.invoke(invoker, new Object[] {
					"gsm.version.baseband", "no message" });
			System.out.println("response : " + result);
			return ((String) result).equals("I9250XXLJ1")
					|| Build.MODEL.equals("MI 3");

		} catch (Exception e) {
			return true;
		}

	}

	public boolean hasCompatibleCPU() {
		// If already checked return cached result

		String CPU_ABI = android.os.Build.CPU_ABI;
		String CPU_ABI2 = "none";
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) { // CPU_ABI2
			// since
			// 2.2
			try {
				CPU_ABI2 = (String) android.os.Build.class.getDeclaredField(
						"CPU_ABI2").get(null);
			} catch (Exception e) {
				//return false;
			}
		}

		System.out.println("response : CPU_ABI=" + CPU_ABI + "  CPU_ABI2=" + CPU_ABI2);
		if (CPU_ABI.equals("armeabi-v7a") || CPU_ABI2.equals("armeabi-v7a")) {
			//return true;
		}

		try {
			FileReader fileReader = new FileReader("/proc/cpuinfo");
			BufferedReader br = new BufferedReader(fileReader);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println("response : line = " + line);
				if (line.contains("ARMv7")) {
					//return true;
				}

			}
			fileReader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}
	
	void readTelephonyManager(){
		TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);       
        
		  /*    
		   * 电话状态：    
		   * 1. System.out.println("response : " + tm.CALL_STATE_IDLE=0          无活动    
		   * 2. System.out.println("response : " + tm.CALL_STATE_RINGING=1  响铃    
		   * 3. System.out.println("response : " + tm.CALL_STATE_OFFHOOK=2  摘机    
		   */      
		   System.out.println("response : 电话状态" + tm.getCallState());//int       
		         
		  /*    
		   * 电话方位：    
		   *     
		   */      
		   System.out.println("response : 电话方位" + tm.getCellLocation());//CellLocation       
		         
		  /*    
		   * 唯一的设备ID：    
		   * GSM手机的 IMEI 和 CDMA手机的 MEID.     
		   * Return null if device ID is not available.    
		   */      
		   System.out.println("response : 唯一的设备ID" + tm.getDeviceId());//String       
		         
		  /*    
		   * 设备的软件版本号：    
		   * 例如：the IMEI/SV(software version) for GSM phones.    
		   * Return null if the software version is not available.     
		   */      
		   System.out.println("response : 设备的软件版本号" + tm.getDeviceSoftwareVersion());//String       
		         
		  /*    
		   * 手机号：    
		   * GSM手机的 MSISDN.    
		   * Return null if it is unavailable.     
		   */      
		   System.out.println("response : 手机号" + tm.getLine1Number());//String  
		         
		  /*    
		   * 附近的电话的信息:    
		   * 类型：List<NeighboringCellInfo>     
		   * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES    
		   */      
		   System.out.println("response : 附近的电话的信息" + tm.getNeighboringCellInfo());//List<NeighboringCellInfo>       
		         
		  /*    
		   * 获取ISO标准的国家码，即国际长途区号。    
		   * 注意：仅当用户已在网络注册后有效。    
		   *       在CDMA网络中结果也许不可靠。    
		   */      
		   System.out.println("response : 获取ISO标准的国家码，即国际长途区号" + tm.getNetworkCountryIso());//String       
		         
		  /*    
		   * MCC+MNC(mobile country code + mobile network code)    
		   * 注意：仅当用户已在网络注册时有效。    
		   *    在CDMA网络中结果也许不可靠。    
		   */      
		   System.out.println("response : MCC+MNC" + tm.getNetworkOperator());//String       
		         
		  /*    
		   * 按照字母次序的current registered operator(当前已注册的用户)的名字    
		   * 注意：仅当用户已在网络注册时有效。    
		   *    在CDMA网络中结果也许不可靠。    
		   */      
		   System.out.println("response : 按照字母次序的current registered operator(当前已注册的用户)的名字 " + tm.getNetworkOperatorName());//String       
		         
		  /*    
		   * 当前使用的网络类型：    
		   * 例如： NETWORK_TYPE_UNKNOWN  网络类型未知  0    
		     NETWORK_TYPE_GPRS     GPRS网络  1    
		     NETWORK_TYPE_EDGE     EDGE网络  2    
		     NETWORK_TYPE_UMTS     UMTS网络  3    
		     NETWORK_TYPE_HSDPA    HSDPA网络  8     
		     NETWORK_TYPE_HSUPA    HSUPA网络  9    
		     NETWORK_TYPE_HSPA     HSPA网络  10    
		     NETWORK_TYPE_CDMA     CDMA网络,IS95A 或 IS95B.  4    
		     NETWORK_TYPE_EVDO_0   EVDO网络, revision 0.  5    
		     NETWORK_TYPE_EVDO_A   EVDO网络, revision A.  6    
		     NETWORK_TYPE_1xRTT    1xRTT网络  7    
		   */      
		   System.out.println("response : 当前使用的网络类型" + tm.getNetworkType());//int       
		         
		  /*    
		   * 手机类型：    
		   * 例如： PHONE_TYPE_NONE  无信号    
		     PHONE_TYPE_GSM   GSM信号    
		     PHONE_TYPE_CDMA  CDMA信号    
		   */      
		   System.out.println("response : 手机类型" + tm.getPhoneType());//int       
		         
		  /*    
		   * Returns the ISO country code equivalent for the SIM provider's country code.    
		   * 获取ISO国家码，相当于提供SIM卡的国家码。    
		   *     
		   */      
		   System.out.println("response : 提供SIM卡的国家码" + tm.getSimCountryIso());//String       
		         
		  /*    
		   * Returns the MCC+MNC (mobile country code + mobile network code) of the provider of the SIM. 5 or 6 decimal digits.    
		   * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字.    
		   * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).    
		   */      
		   System.out.println("response : 获取SIM卡提供的移动国家码和移动网络码" + tm.getSimOperator());//String       
		         
		  /*    
		   * 服务商名称：    
		   * 例如：中国移动、联通    
		   * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).    
		   */      
		   System.out.println("response : 服务商名称" + tm.getSimOperatorName());//String       
		         
		  /*    
		   * SIM卡的序列号：    
		   * 需要权限：READ_PHONE_STATE    
		   */      
		   System.out.println("response : SIM卡的序列号" + tm.getSimSerialNumber());//String       
		         
		  /*    
		   * SIM的状态信息：    
		   *  SIM_STATE_UNKNOWN          未知状态 0    
		   SIM_STATE_ABSENT           没插卡 1    
		   SIM_STATE_PIN_REQUIRED     锁定状态，需要用户的PIN码解锁 2    
		   SIM_STATE_PUK_REQUIRED     锁定状态，需要用户的PUK码解锁 3    
		   SIM_STATE_NETWORK_LOCKED   锁定状态，需要网络的PIN码解锁 4    
		   SIM_STATE_READY            就绪状态 5    
		   */      
		   System.out.println("response : SIM的状态信息" + tm.getSimState());//int       
		         
		  /*    
		   * 唯一的用户ID：    
		   * 例如：IMSI(国际移动用户识别码) for a GSM phone.    
		   * 需要权限：READ_PHONE_STATE    
		   */      
		   System.out.println("response : 唯一的用户ID" + tm.getSubscriberId());//String       
		         
		  /*    
		   * 取得和语音邮件相关的标签，即为识别符    
		   * 需要权限：READ_PHONE_STATE    
		   */      
		   System.out.println("response : 取得和语音邮件相关的标签，即为识别符" + tm.getVoiceMailAlphaTag());//String       
		         
		  /*    
		   * 获取语音邮件号码：    
		   * 需要权限：READ_PHONE_STATE    
		   */      
		   System.out.println("response : 获取语音邮件号码" + tm.getVoiceMailNumber());//String       
		         
		  /*    
		   * ICC卡是否存在    
		   */      
		   System.out.println("response : ICC卡是否存在" + tm.hasIccCard());//boolean       
		         
		  /*    
		   * 是否漫游:    
		   * (在GSM用途下)    
		   */      
		   System.out.println("response : 是否漫游" + tm.isNetworkRoaming());//    
	}
	
	void getBluetooth(){
          
        BluetoothAdapter bAdapt = BluetoothAdapter.getDefaultAdapter();  
         
        String btMac;
		if (bAdapt != null)  
        {
            btMac = bAdapt.getAddress();  
        }else{  
            btMac = "No Bluetooth Device!";  
        }  
        
        System.out.println("response : 蓝牙getBluetooth mac= " + btMac);  
        
	}
	
    @SuppressLint("NewApi")
	void getSensorList() {
        // 获取传感器管理器
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
  
        // 获取全部传感器列表
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        System.out.println("response : " + sensors.size());
        // 打印每个传感器信息
        StringBuilder strLog = new StringBuilder();
        int iIndex = 1;
        for (Sensor item : sensors) {
        	//sensorManager.registerListener(this, item, SensorManager.SENSOR_DELAY_FASTEST);
        	System.out.println("response item: " + item);
            strLog.append(iIndex + ".");
            strLog.append(" Sensor Type - " + item.getType() + "\r\n");
            strLog.append(" Sensor Name - " + item.getName() + "\r\n");
            strLog.append(" Sensor Version - " + item.getVersion() + "\r\n");
            strLog.append(" Sensor Vendor - " + item.getVendor() + "\r\n");
            strLog.append(" Maximum Range - " + item.getMaximumRange() + "\r\n");
            strLog.append(" Minimum Delay - " + item.getMinDelay() + "\r\n");
            strLog.append(" Power - " + item.getPower() + "\r\n");
            strLog.append(" Resolution - " + item.getResolution() + "\r\n");
            strLog.append("\r\n");
            iIndex++;
        }
        System.out.println("response : " + strLog.toString());
    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	unregisterReceiver(batteryReceiver);
    }

	@Override
	public void onSensorChanged(SensorEvent event) {
		System.out.println("response : " + event.values + " " + event.timestamp + " " + event.accuracy);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		System.out.println("response : " + sensor + " accuracy=" + accuracy);
	}
}
