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
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends Activity implements SensorEventListener {

	EditText et;
	EditText num;
	TextView light;
	Button button;
	Button button2;
	StringBuilder sb2;
	int m = 0;
	private Sensor lightSensor;
	private SensorManager sensorManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
		num = (EditText) findViewById(R.id.number);
		light = (TextView) findViewById(R.id.light);
		button = (Button) findViewById(R.id.button);
		button2 = (Button) findViewById(R.id.button2);
		sb2 = new StringBuilder();
		getCpuInfo2();
		getVersion();

		getSim();
		getBluetooth();

		// getLevel();
		getSensorList();
		
		et.setText(sb2);
		System.out.println("response : " + sb2);
		//writeToFile();
		getScreenMode();
		getScreenBrightness();
		System.out.println("response : " + "开始调节");
		//setScreenMode(0);
		light.setText("dd" + getScreenBrightness() + "   |   " + getScreenBrightness2() +"ee");
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setScreenBrightness(Integer.parseInt(num.getText().toString()));
				getScreenMode();
				light.setText("" + getScreenBrightness() + "|" + getScreenBrightness2());
				
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveScreenBrightness(m);
				if (m == 0) {
					m = 1;
				}else {
					m = 0;
				}
				getScreenMode();
				getScreenBrightness();
			}
		});
		
	}

	void writeToFile() {
		String fileName ="testCpu.txt";
		File file = new File(Environment.getExternalStorageDirectory(),fileName );
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			writer.write(sb2.toString());
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
		/*sb.append(" 手机制造商" + android.os.Build.PRODUCT+"\n");
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
		sb.append(" USER" + android.os.Build.USER+"\n");*/
		
		
		
	}

	void getCpuInfo2() {
		FileReader fr = null;
		BufferedReader br = null;
		boolean hasModel = false;
		String[] val = {"",""};
		int cpuCount = 0;
		try {
			String path = "/proc/cpuinfo";
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line = "";
			String trimLine = "";
			
			while ((line = br.readLine()) != null) {
				
				trimLine = line.trim();
				
				if (trimLine.startsWith("processor")) {
					cpuCount++;//cpu核数
				}
				
				if ((!hasModel) && trimLine.contains("model") ) {
					String[]  values = trimLine.split(":");
					if (values.length >= 2) {
						val[0] = values[1].trim();
					}else {
						val[0] = values[0].trim();
					}
					hasModel = true;
				}
				
				if (trimLine.trim().startsWith("Processor")) {
					String[]  values = trimLine.split(":");
					if (values.length >= 2) {
						val[1] = values[1].trim();
					}else {
						val[1] = values[0].trim();
					}
				}
				
			}
			
			
		} catch (IOException e) {
			
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			br = null;
			fr = null;
		}
		
		sb2.append("cpu_model_name=");
		if (hasModel && val[0].length() != 0) {
			sb2.append(val[0]);
		}else {
			sb2.append(val[1]);
		}
		sb2.append("\n");
		sb2.append("cpu_count=" + cpuCount);
	}

	void getVersion() {
		sb2.append("\n");
		sb2.append("android_version=" + android.os.Build.VERSION.SDK_INT);
		sb2.append("\n");
		sb2.append("linux_version=");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			String path = "/proc/version";
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				sb2.append(line.trim());
				break;
			}

		} catch (IOException e) {
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			br = null;
			fr = null;
		}
	}

	void getSim() {
		TelephonyManager tm = (TelephonyManager) this .getSystemService(TELEPHONY_SERVICE);
		sb2.append("\n");
		sb2.append("sim_id=" + tm.getDeviceId());
		sb2.append("\n");
		sb2.append("sim_mobile=" + tm.getSimOperator());
		sb2.append("\n");
		sb2.append("sim_number=" + tm.getSimSerialNumber());

	}

	void getBluetooth() {

		BluetoothAdapter bAdapt = BluetoothAdapter.getDefaultAdapter();

		String mac;
		if (bAdapt != null) {
			mac = bAdapt.getAddress();
		} else {
			mac = "";
		}

		sb2.append("\n");
		sb2.append("blueTooth_mac= " + mac);

	}

	@SuppressLint("NewApi")
	void getSensorList() {
		// 获取传感器管理器
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		if (sensorManager == null) {
			System.out.println("response : " + "传感管理器错误");
			return;
		}
		// 获取全部传感器列表
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		sb2.append("\n传感器数量: " + sensors.size()+"\n");
		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		if (lightSensor != null) {
			System.out.println("response : " + "光线传感器");
			sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
		}
	}

	

	@Override
	public void onSensorChanged(SensorEvent event) {
		Toast.makeText(this, "光感值" + event.values[0], Toast.LENGTH_SHORT).show();
		System.out.println("response onSensorChanged : " + event.values[0] + " "  + event.accuracy );
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		System.out.println("response AccuracyChanged : accuracy=" + accuracy);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this, lightSensor); 
	}
	
	/**
	 * 获得当前屏幕亮度的模式 SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
	 * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
	 */
	int getScreenMode() {
		int screenMode = 0;
		try {
			screenMode = Settings.System.getInt(getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS_MODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("response :1=自动 0手动 ScreenMode= " + screenMode);
		return screenMode;
	}

	/**
	 * 获得当前屏幕亮度值 0--255
	 */
	int getScreenBrightness() {
		int screenBrightness = 255;
		try {
			screenBrightness = Settings.System.getInt(getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("response : 获得当前屏幕亮度值 0--255 ScreenBrightness=" + screenBrightness);
		return screenBrightness;
	}
	
	float getScreenBrightness2() {
		float screenBrightness = getWindow().getAttributes().screenBrightness;
		System.out.println("response : screenBrightness = " + screenBrightness);
		return  screenBrightness;
	}

	/**
	 * 设置当前屏幕亮度的模式 SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
	 * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
	 */
	void setScreenMode(int paramInt) {
		try {
			Settings.System.putInt(getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS_MODE, paramInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置当前屏幕亮度值 0--255
	 */
	void saveScreenBrightness(int paramInt) {
		try {
			Settings.System.putInt(getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS, paramInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存当前的屏幕亮度值，并使之生效
	 */
	void setScreenBrightness(int paramInt) {
		Window localWindow = getWindow();
		WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
		float f = paramInt / 255.0F;
		localLayoutParams.screenBrightness = f;
		localWindow.setAttributes(localLayoutParams);
	}
}
