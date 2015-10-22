package com.dzl.test.cpu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.dzl.test.BaseActivity;

/**
 * 可添加开机时间判断
 * 
 * @author dzl 2015年9月9日
 */
public class CpuActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		System.out.println("resp1onse : getCPUSerial =  " + getCPUSerial());
		System.out.println("resp1onse : android.os.Build.SERIAL = "
				+ android.os.Build.SERIAL);

		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		System.out.println("resp1onse : getDeviceId = " + tm.getDeviceId()
				+ " " + tm.getDeviceSoftwareVersion());

		String android_id = Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID);
		System.out.println("resp1onse : Secure.ANDROID_ID = " + android_id);

		System.out.println("resp1onse : getLocalMacAddress mac = "
				+ getLocalMacAddress());

		System.out.println("resp1onse : ================================");
		System.out.println("resp1onse : displayDevice = " + displayDevice());

		System.out.println("resp1onse : ================================");
		System.out.println("resp1onse : displayMd5 = " + displayMd5());

		System.out.println("resp1onse : 手机型号MODEL = " + android.os.Build.MODEL);

		System.out.println("resp1onse : kernel = " + getKernelVersion());

		System.out.println("resp1onse : tm.getNetworkOperatorName() = "
				+ tm.getNetworkOperatorName());
		System.out.println("resp1onse : tm.getNetworkOperator() = "
				+ tm.getNetworkOperator());
		System.out.println("resp1onse : tm.getNetworkCountryIso() = "
				+ tm.getNetworkCountryIso());
		System.out.println("resp1onse : tm.getNetworkType() = "
				+ tm.getNetworkType());
		// System.out.println("resp1onse : tm.getNetworkTypeName() = " +
		// tm.getNetworkTypeName());
		
		getLocalIpAddress();
		System.out.println("resp1onse : 开机时间SystemClock.elapsedRealtime() 秒 = " + SystemClock.elapsedRealtime() / 1000); 
		System.out.println("resp1onse : 开机时间SystemClock.elapsedRealtime() 时 = " + SystemClock.elapsedRealtime() /(1000 * 60 * 60));
		
//		System.out.println("resp1onse : GetNetIp " + new AllInfo(this).GetNetIp(""));测试出现网络错误 dzl
		
		System.out.println("resp1onse : getBluetooth = " + getBluetooth());
		
	}

	/**
	 * 获取CPU序列号
	 * 
	 * @return CPU序列号(16位) 读取失败为"0000000000000000"
	 */
	public static String getCPUSerial() {

		String str = "", strCPU = "", cpuAddress = "0000000000000000";

		try {
			// 读取CPU信息

			Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");

			InputStreamReader ir = new InputStreamReader(pp.getInputStream());

			LineNumberReader input = new LineNumberReader(ir);

			// 查找CPU序列号

			for (int i = 1; i < 1000; i++) {

				str = input.readLine();
				System.out.println("resp1onse : str = " + str);

				if (str != null) {

					// 查找到序列号所在行

					if (str.indexOf("Serial") > -1) {

						// 提取序列号

						strCPU = str.substring(str.indexOf(":") + 1,
								str.length());

						// 去空格

						cpuAddress = strCPU.trim();

						break;

					}
				} else {

					// 文件结尾
					break;

				}
			}

		} catch (IOException ex) {

			// 赋予默认值

			ex.printStackTrace();
		}
		return cpuAddress;
	}

	private String getLocalMacAddress() {
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	public String getIMEI() {
		return ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE))
				.getDeviceId();
	}

	public String getAndroidId() {
		return android.provider.Settings.Secure.getString(getContentResolver(),
				android.provider.Settings.Secure.ANDROID_ID);
	}

	public String getSimSerialNumber() {
		return ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE))
				.getSimSerialNumber();
	}

	public static String getSerialNumber1() {
		return android.os.Build.SERIAL;
	}

	/**
	 * getSerialNumber
	 * 
	 * @return result is same to getSerialNumber1()
	 */
	public static String getSerialNumber() {
		String serial = null;
		try {
			Class<?> c = Class.forName("android.os.SystemProperties");
			Method get = c.getMethod("get", String.class);
			serial = (String) get.invoke(c, "ro.serialno");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serial;
	}

	public String displayMd5_2() {
		TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String m_szImei = TelephonyMgr.getDeviceId();
		String m_szDevIDShort = "35"
				+ // we make this look like a valid IMEI

				Build.BOARD.length() % 10 + Build.BRAND.length() % 10
				+ Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
				+ Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
				+ Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
				+ Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
				+ Build.TAGS.length() % 10 + Build.TYPE.length() % 10
				+ Build.USER.length() % 10; // 13 digits
		String m_szAndroidID = Secure.getString(getContentResolver(),
				Secure.ANDROID_ID);

		WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();

		BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
		m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		String m_szBTMAC = m_BluetoothAdapter.getAddress();

		String m_szLongID = m_szImei + m_szDevIDShort + m_szAndroidID
				+ m_szWLANMAC + m_szBTMAC;
		// compute md5
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
		// get md5 bytes
		byte p_md5Data[] = m.digest();
		// create a hex string
		String m_szUniqueID = new String();
		for (int i = 0; i < p_md5Data.length; i++) {
			int b = (0xFF & p_md5Data[i]);
			// if it is a single digit, make sure it have 0 in front (proper
			// padding)
			if (b <= 0xF)
				m_szUniqueID += "0";
			// add number to string
			m_szUniqueID += Integer.toHexString(b);
		} // hex string to uppercase
		m_szUniqueID = m_szUniqueID.toUpperCase();

		return m_szUniqueID;
	}

	public String displayMd5() {

		String m_szDevIDShort = "35"
				+ // we make this look like a valid IMEI

				Build.BOARD.length() % 10 + Build.BRAND.length() % 10
				+ Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
				+ Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
				+ Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
				+ Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
				+ Build.TAGS.length() % 10 + Build.TYPE.length() % 10
				+ Build.USER.length() % 10; // 13 digits
		return m_szDevIDShort;

	}

	public String displayDevice() {
		String dest_imei = getIMEI();
		String androidId = getAndroidId();
		return " isTestDevice: " + " ,IMEI:" + dest_imei + " ,ANDROID ID:"
				+ androidId + " ,SerialNumber:" + getSerialNumber()
				+ " ,SimSerialNumber:" + getSimSerialNumber();
	}

	/**
	 * 内核版本
	 * 
	 * @return
	 */
	public static String getKernelVersion() {
		String kernelVersion = "";
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("/proc/version");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return kernelVersion;
		}
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream), 8 * 1024);
		String info = "";
		String line = "";
		try {
			while ((line = bufferedReader.readLine()) != null) {
				info += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("resp1onse : 内核版本 =  " + info);

		try {
			if (!info.equals("")) {
				final String keyword = "version ";
				int index = info.indexOf(keyword);
				line = info.substring(index + keyword.length());
				index = line.indexOf(" ");
				kernelVersion = line.substring(0, index);
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return kernelVersion;
	}

	/**
	 * 获取手机IP地址
	 * 
	 * @return
	 */
	public String getLocalIpAddress() {
		int i = 0;
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) 
			{
				System.out.println("resp1onse : NetworkInterface " + i++);
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					System.out.println("resp1onse : InetAddress " + i++);
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
//						System.out.println("resp1onse : inetAddress.getHostName() = " + inetAddress.getHostName());
						System.out.println("resp1onse : inetAddress.getHostAddress() =  " + inetAddress.getHostAddress().toString());
//						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取终端蓝牙地址 dzl 15-06-26
	 */
	String getBluetooth() {

		BluetoothAdapter bAdapt = BluetoothAdapter.getDefaultAdapter();

		String mac = null;
		if (bAdapt != null) {
			mac = bAdapt.getAddress();
		}
		return (mac == null ? "" : mac);

	}

}
