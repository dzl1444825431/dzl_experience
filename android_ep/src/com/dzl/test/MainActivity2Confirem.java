package com.dzl.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2Confirem extends BaseActivity implements SensorEventListener {
	private final int valCount = 2;
	EditText et;
	StringBuffer sb;
	private Sensor lightSensor;
	private SensorManager sensorManager;
	private int lightCount = 0;
	private boolean hasLightSensor = false;//true 有传感
	private boolean isFourCount = false;//true 获取足够值
	   
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			System.out.println("response : end        end ==========================");
			Toast.makeText(MainActivity2Confirem.this, "检测结束", Toast.LENGTH_SHORT).show();
			if (sensorManager != null) {
				sensorManager.unregisterListener(MainActivity2Confirem.this, lightSensor);
			}
			  
			if (hasLightSensor && (!isFourCount)) {
				for (int i = 0; i < valCount - 1 - lightCount; i++) {
					sb.append("0,");
				}
				sb.append("0");
			}
			et.setText(sb);
			writeToFile();
			System.out.println("response :sb== " + sb);
			
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
		sb = new StringBuffer();
		
		getCpuMode();
		getSDK();
		getSim();
		getBluetooth();
		getLightSensor();
		
		if (hasLightSensor) {
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					sensorManager.unregisterListener(MainActivity2Confirem.this, lightSensor);
					if (lightCount < valCount) {
						handler.sendEmptyMessage(1);
					}
				}
			}, 5000);
		}else {
			handler.sendEmptyMessage(1);
		}
		//startService(new Intent(this,CheckEmService.class));

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

	void getCpuMode() {
		FileReader fr = null;
		BufferedReader br = null;
		boolean hasModel = false;
		String[] cpuModeArr = { "", "" };// modeName
		int cpuCount = 0;// cpu核数
		try {
			String path = "/proc/cpuinfo";
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line = "";
			String trimLine = "";

			while ((line = br.readLine()) != null) {

				trimLine = line.trim();
				if (trimLine.startsWith("processor")) {
					cpuCount++;
					continue;
				}

				if ((!hasModel) && trimLine.contains("model") && trimLine.contains("name")) {
					cpuModeArr[0] = trimLine.substring(trimLine.indexOf(":"));
					hasModel = true;
					continue;
				}

				if (trimLine.trim().startsWith("Processor")) {
					cpuModeArr[1] = trimLine.substring(trimLine.indexOf(":"));
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
		if (hasModel && cpuModeArr[0].length() != 0) {
			sb.append(cpuModeArr[0]);
		} else {
			sb.append(cpuModeArr[1]);
		}
		sb.append("\n");
		sb.append("cpu_count=" + cpuCount);
	}
	
	void getSDK() {
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

	void getLightSensor() {
		// 获取传感器管理器
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sb.append("\n");
		sb.append("light_value=");
		if (sensorManager == null) {
			return;
		}

		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		if (lightSensor != null) {
			sensorManager.registerListener(this, lightSensor,SensorManager.SENSOR_DELAY_GAME);
			hasLightSensor = true;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		
		lightCount ++;
		if (lightCount >= valCount) {
			
			sensorManager.unregisterListener(this, lightSensor);
			if (!isFourCount) {
				sb.append(event.values[0]);
			}
			isFourCount = true;
			handler.sendEmptyMessage(1);
		}else {
			sb.append(event.values[0]+",");
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (sensorManager != null) {
			sensorManager.unregisterListener(MainActivity2Confirem.this, lightSensor);
		}
	}

}
