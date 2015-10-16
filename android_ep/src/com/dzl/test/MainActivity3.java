package com.dzl.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.widget.EditText;

public class MainActivity3 extends Activity implements SensorEventListener {

	EditText et;
	StringBuilder sb;
	StringBuilder sb2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
		sb = new StringBuilder();
		sb2 = new StringBuilder();
		getCpuInfo2();
		getVersion();

		readTelephonyManager();
		getBluetooth();

		// getLevel();
		getSensorList();
		sb.append(" 系统总开机时间SystemClock.elapsedRealtime()="
				+ SystemClock.elapsedRealtime());
		et.setText(sb);
		System.out.println("response : " + sb);
		//writeToFile();
	}

	void writeToFile() {
		String fileName ="testCpu.txt";
		File file = new File(Environment.getExternalStorageDirectory(),fileName );
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			writer.write(sb.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void getBuild() {
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

		sb.append(" 手机制造商" + android.os.Build.PRODUCT+"\n");
		sb.append(" 主板" + android.os.Build.BOARD+"\n");
		sb.append(" " + android.os.Build.BOOTLOADER+"\n");
		sb.append(" android系统定制商" + android.os.Build.BRAND+"\n");
		sb.append(" cpu指令集" + android.os.Build.CPU_ABI+"\n");
		sb.append(" cpu指令集2" + android.os.Build.CPU_ABI2+"\n");
		sb.append(" DEVICE" + android.os.Build.DEVICE+"\n");
		sb.append(" 显示屏参数" + android.os.Build.DISPLAY+"\n");
		sb.append(" 硬件名称" + android.os.Build.FINGERPRINT+"\n");
		sb.append(" HARDWARE" + android.os.Build.HARDWARE+"\n");
		sb.append(" HOST" + android.os.Build.HOST+"\n");
		sb.append(" 修订版本列表ID" + android.os.Build.ID+"\n");
		sb.append(" 硬件制造商" + android.os.Build.MANUFACTURER+"\n");
		sb.append(" 版本" + android.os.Build.MODEL+"\n");
		sb.append(" 手机制造商" + android.os.Build.PRODUCT+"\n");
		sb.append(" RADIO" + android.os.Build.RADIO+"\n");
		// sb.append(" " + android.os.Build.SERIAL+"\n");
		sb.append(" 描述build的标签" + android.os.Build.TAGS+"\n");
		sb.append(" TIME" + android.os.Build.TIME+"\n");
		sb.append(" TYPE" + android.os.Build.TYPE+"\n");
		sb.append(" USER" + android.os.Build.USER+"\n");
	}

	void getCpuInfo2() {
		String str1 = "/proc/cpuinfo";
		try {
			FileReader fr = new FileReader(str1);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String trimLine = "";
			String model = "";
			String[] model2 = {"",""};
			sb.append("cpu信息 start"+"\n");
			int cpuCount = 0;
			boolean hasModel = false;
			while ((line = br.readLine()) != null) {
				if (line.contains("rocessor") || line.contains("model")
						|| line.contains("ardware")) {
					sb.append(" " + line+"\n");
				}
				
				trimLine = line.trim();
				
				if (trimLine.startsWith("processor")) {
					cpuCount++;//cpu核数
				}
				
				if ((!hasModel) && trimLine.contains("model") ) {
					String[]  name = trimLine.split(":");
					if (name.length >= 2) {
						model2[0] = name[1].trim();
					}else {
						model2[0] = name[0].trim();
					}
					hasModel = true;
				}
				
				if (trimLine.trim().startsWith("Processor")) {
					String[]  name = trimLine.split(":");
					if (name.length >= 2) {
						model2[1] = name[1].trim();
					}else {
						model2[1] = name[0].trim();
					}
				}
				
				
			}
			sb.append("cpu信息 end\n\n\n");
			sb2.append("cpu_model_name:");

			br.close();
		} catch (IOException e) {
		}
	}

	void getVersion() {
		String str1 = "/proc/version";
		try {
			FileReader fr = new FileReader(str1);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			sb.append("Version信息 start"+"\n");
			while ((line = br.readLine()) != null) {
				sb.append(line+"\n");
			}
			sb.append("Version信息 end"+"\n");

			br.close();
		} catch (IOException e) {
		}
	}

	void readTelephonyManager() {
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(TELEPHONY_SERVICE);

		sb.append(" 唯一的设备ID" + tm.getDeviceId()+"\n");// String
		sb.append(" 电话方位" + tm.getCellLocation()+"\n");// CellLocation
		sb.append(" 手机号" + tm.getLine1Number()+"\n");// String
		sb.append(" 当前使用的网络类型" + tm.getNetworkType()+"\n");// int
		sb.append(" 手机类型" + tm.getPhoneType()+"\n");// int
		sb.append(" 获取SIM卡提供的移动国家码和移动网络码"
				+ tm.getSimOperator()+"\n");// String
		sb.append(" 服务商名称" + tm.getSimOperatorName()+"\n");// String
		sb.append(" SIM卡的序列号" + tm.getSimSerialNumber()+"\n");// String
		sb.append(" SIM的状态信息" + tm.getSimState()+"\n");// int
		sb.append(" 唯一的用户ID" + tm.getSubscriberId()+"\n");// String

	}

	void getBluetooth() {

		BluetoothAdapter bAdapt = BluetoothAdapter.getDefaultAdapter();

		String btMac;
		if (bAdapt != null) {
			btMac = bAdapt.getAddress();
		} else {
			btMac = "No Bluetooth Device!";
		}

		sb.append(" 蓝牙地址= " + btMac+"\n");

	}

	@SuppressLint("NewApi")
	void getSensorList() {
		// 获取传感器管理器
		SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		// 获取全部传感器列表
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		sb.append("传感器数量: " + sensors.size()+"\n");
		// 打印每个传感器信息
		
		for (Sensor item : sensors) {
			// sensorManager.registerListener(this, item,
			// SensorManager.SENSOR_DELAY_FASTEST);
			sb.append("传感器 item: " + item+"\n");
			switch (item.getType()) { 
				case Sensor.TYPE_LIGHT:
					System.out.println("response : " + "光线传感器");
					sensorManager.registerListener(this, item, SensorManager.SENSOR_DELAY_FASTEST);
					break;
			
			}
			/*strLog.append(iIndex + ".");
			strLog.append(" Sensor Type - " + item.getType() + "\r\n");
			strLog.append(" Sensor Name - " + item.getName() + "\r\n");
			strLog.append(" Sensor Version - " + item.getVersion() + "\r\n");
			strLog.append(" Sensor Vendor - " + item.getVendor() + "\r\n");
			strLog.append(" Sensor Maximum Range - " + item.getMaximumRange()
					+ "\r\n");
			strLog.append(" Sensor Minimum Delay - " + item.getMinDelay()
					+ "\r\n");
			strLog.append(" Sensor Power - " + item.getPower() + "\r\n");
			strLog.append(" Sensor Resolution - " + item.getResolution()
					+ "\r\n");
			strLog.append("\r\n");*/
		}
		//sb.append(" " + strLog.toString());
	}

	

	@Override
	public void onSensorChanged(SensorEvent event) {
		System.out.println("response : " + event.values + " " + event.timestamp + " " + event.accuracy);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		System.out.println("response : " + sensor.toString() + " accuracy=" + accuracy);
	}
}
