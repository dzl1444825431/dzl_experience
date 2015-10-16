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

public class TestEmulatorActivity extends Activity implements SensorEventListener {
	private final int valCount = 10;
	EditText et;
	StringBuilder sb;
	long startTime;
	private Sensor lightSensor;
	private SensorManager sensorManager;
	private int lightCount = 0;
	private boolean hasLightSensor = false;//true 有传感
	private boolean isFour = false;//true 获取足够值
	   
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			System.out.println("response : end        end ==========================");
			Toast.makeText(TestEmulatorActivity.this, "检测结束", Toast.LENGTH_SHORT).show();
			if (sensorManager != null) {
				sensorManager.unregisterListener(TestEmulatorActivity.this, lightSensor);
			}
			  
			if (hasLightSensor && (!isFour)) {
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
		
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		System.out.println("resp1onse : " + "sim_device_id = " + ( tm.getDeviceId() == null ? "" : tm.getDeviceId()));
		System.out.println("resp1onse : " + "sim_mobile =" + ( tm.getSimOperator() == null ? "" : tm.getSimOperator()));
		System.out.println("resp1onse : " + "sim_serial_number = "+ ( tm.getSimSerialNumber() == null ? "" : tm.getSimSerialNumber()));
		et = (EditText) findViewById(R.id.et);
		
		sb = new StringBuilder();
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
		
		if (hasLightSensor) {
			System.out.println("response : registerSensor 有 sensor end "+ (System.currentTimeMillis() - startTime));
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					sensorManager.unregisterListener(TestEmulatorActivity.this, lightSensor);
					if (lightCount < valCount) {
						handler.sendEmptyMessage(1);
					}
				}
			}, 15000);
		}else {
			System.out.println("response : registerSensor 没有 sensor end"+ (System.currentTimeMillis() - startTime));
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
				System.out.println("response :trimLine= " + trimLine);
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
		sb.append("sim_id=" + (tm.getDeviceId() == null ? "" : tm.getDeviceId()));
		sb.append("\n");
		sb.append("sim_mobile=" + (tm.getSimOperator() == null ? "" : tm.getSimOperator()));
		sb.append("\n");
		sb.append("sim_number=" + (tm.getSimSerialNumber() == null ? "" : tm.getSimSerialNumber()));
		
		/*TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		mCheckEnvironmentMap.put("sim_device_id", tm.getDeviceId() == null ? "" : tm.getDeviceId());
		mCheckEnvironmentMap.put("sim_mobile", tm.getSimOperator() == null ? "" : tm.getSimOperator());
		mCheckEnvironmentMap.put("sim_serial_number", tm.getSimSerialNumber() == null ? "" : tm.getSimSerialNumber());*/

		
		
	}

	void getBluetooth() {

		BluetoothAdapter bAdapt = BluetoothAdapter.getDefaultAdapter();

		String mac = null;
		if (bAdapt != null) {
			mac = bAdapt.getAddress();
		}

		sb.append("\n");
		sb.append("blueTooth_mac=" + (mac == null ? "" : mac));

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
			sensorManager.registerListener(this, lightSensor,SensorManager.SENSOR_DELAY_GAME);
			return true;
		}
		System.out.println("response : lightSensor = null" );
		return false;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		System.out.println("response : onSensorChanged  "+ (System.currentTimeMillis() - startTime));
		Toast.makeText(this, "" + event.values[0], Toast.LENGTH_SHORT).show();
		lightCount ++;
		if (lightCount >= valCount) {
			
			sensorManager.unregisterListener(this, lightSensor);
			if (!isFour) {
				sb.append(event.values[0]);
			}
			isFour = true;
			handler.sendEmptyMessage(1);
			System.out.println("response : onSensorChanged unregisterListener end "+ (System.currentTimeMillis() - startTime));
		}else {
			sb.append(event.values[0]+",");
		}
	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		System.out.println("response : accuracy= " + accuracy);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (sensorManager != null) {
			sensorManager.unregisterListener(TestEmulatorActivity.this, lightSensor);
		}
	}

}
