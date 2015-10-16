package com.dzl.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CheckEmService extends Service implements SensorEventListener {

	StringBuffer sb = new StringBuffer();
	private Sensor lightSensor;
	private SensorManager sensorManager;
	private int lightCount = 0;
	private long startTime;
	private boolean hasLightSensor = false;
	private boolean unRegSensor = false;
	private Handler handler = new Handler(Looper.getMainLooper()){
		public void handleMessage(android.os.Message msg) {
			
			System.out.println("response : end        end ==========================");
			if (sensorManager != null) {
				sensorManager.unregisterListener(CheckEmService.this, lightSensor);
			}
			writeToFile();
			System.out.println("response :sb== " + sb);
			stopSelf();
		};
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		System.out.println("response : " + 1111111111);
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startTime = System.currentTimeMillis();
		System.out.println("response : getCpuInfo2 start" + (System.currentTimeMillis() - startTime));
		getCpuInfo2();
		System.out.println("response : getCpuInfo2 end" + (System.currentTimeMillis() - startTime));
		getVersion();
		System.out.println("response : getVersion end"+ (System.currentTimeMillis() - startTime));
		getSim();
		System.out.println("response : getSim end"+ (System.currentTimeMillis() - startTime));
		getBluetooth();
		System.out.println("response : getBluetooth end"+ (System.currentTimeMillis() - startTime));
		//System.out.println("response : writeToFile end"+ (System.currentTimeMillis() - startTime));
		
		hasLightSensor = registerSensor();
		
		System.out.println("response :hasLightSensor= " + hasLightSensor);
		if (hasLightSensor) {
			System.out.println("response : registerSensor 有 sensor end "+ (System.currentTimeMillis() - startTime));
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					sensorManager.unregisterListener(CheckEmService.this, lightSensor);
					if (lightCount < 4) {
						handler.sendEmptyMessage(1);
					}
				}
			}, 5000);
		}else {
			System.out.println("response : registerSensor 没有 sensor end"+ (System.currentTimeMillis() - startTime));
			handler.sendEmptyMessage(1);
		}
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	void getCpuInfo2() {
		FileReader fr = null;
		BufferedReader br = null;
		boolean hasModel = false;
		String[] val = { "", "" };
		int cpuCount = 0;
		try {
			String path = "/proc/cpuinfo";
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line = "";
			String trimLine = "";

			while ((line = br.readLine()) != null) {

				trimLine = line.trim();
				//sb.append("\n" + trimLine);
				if (trimLine.startsWith("processor")) {
					cpuCount++;// cpu核数
					continue;
				}

				if ((!hasModel) && trimLine.contains("model") && trimLine.contains("name")) {
					val[0] = trimLine.substring(trimLine.indexOf(":"));
					hasModel = true;
					continue;
				}

				if (trimLine.trim().startsWith("Processor")) {
					val[1] = trimLine.substring(trimLine.indexOf(":"));
				}

			}

		} catch (IOException e) {

		} finally {
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

		sb.append("cpu_model_name=");
		if (hasModel && val[0].length() != 0) {
			sb.append(val[0]);
		} else {
			sb.append(val[1]);
		}
		sb.append("\n");
		sb.append("cpu_count=" + cpuCount);
	}
	
	void getVersion() {
		sb.append("\n");
		sb.append("android_version=" + android.os.Build.VERSION.SDK_INT);
	}

	void getSim() {
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(TELEPHONY_SERVICE);
		sb.append("\n");
		sb.append("sim_id=" + tm.getDeviceId());
		sb.append("\n");
		sb.append("sim_mobile=" + tm.getSimOperator());
		sb.append("\n");
		sb.append("sim_number=" + tm.getSimSerialNumber());

	}

	void getBluetooth() {

		BluetoothAdapter bAdapt = BluetoothAdapter.getDefaultAdapter();

		String mac;
		if (bAdapt != null) {
			mac = bAdapt.getAddress();
			if (mac == null) {
				mac = "";
			}
		} else {
			mac = "";
		}

		sb.append("\n");
		sb.append("blueTooth_mac=" + mac);

	}
	
	void writeToFile() {
		String fileName = "testCpu.txt";
		File file = new File(Environment.getExternalStorageDirectory(),
				fileName);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			writer.write(sb.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	boolean registerSensor() {
		// 获取传感器管理器
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sb.append("\n");
		sb.append("light_value=");
		if (sensorManager == null) {
			System.out.println("response : sensorManager = null" );
			return false;
		}

		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		if (lightSensor != null) {
			System.out.println("response : 注册中" );
			sensorManager.registerListener(this, lightSensor,SensorManager.SENSOR_DELAY_FASTEST);
			return true;
		}
		System.out.println("response : lightSensor = null" );
		return false;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		System.out.println("response : onSensorChanged  "+ (System.currentTimeMillis() - startTime));
		Toast.makeText(CheckEmService.this, "" + event.values[0], Toast.LENGTH_SHORT).show();
		lightCount ++;
		if (lightCount >= 4) {
			
			sensorManager.unregisterListener(this, lightSensor);
			if (!unRegSensor) {
				sb.append(event.values[0]);
			}
			unRegSensor = true;
			handler.sendEmptyMessage(1);
			System.out.println("response : onSensorChanged unregisterListener end "+ (System.currentTimeMillis() - startTime));
		}else {
			sb.append(event.values[0]+",");
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onDestroy() {
		if (sensorManager != null) {
			sensorManager.unregisterListener(CheckEmService.this, lightSensor);
		}
		super.onDestroy();
		System.out.println("response : end ======================== ");
	}

}
