package com.dzl.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//判断它是否是为电量变化的Broadcast Action
		if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
			//获取当前电量
			int level = intent.getIntExtra("level", 0);
			//电量的总刻度
			int scale = intent.getIntExtra("scale", 100);
			//把它转成百分比
			System.out.println("response : " + "电池电量为"+((level*100)/scale)+"%");
			System.out.println("response : " + "电池电量 level="+level + " scale=" + scale);
			
			int BatteryN = intent.getIntExtra("level", 0);    //目前电量  
            int BatteryV = intent.getIntExtra("voltage", 0);  //电池电压  
            int BatteryT = intent.getIntExtra("temperature", 0);  //电池温度  
              
            String BatteryStatus = "";
			switch (intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN))   
            {  
            case BatteryManager.BATTERY_STATUS_CHARGING:  
                BatteryStatus = "充电状态";  
                break;  
            case BatteryManager.BATTERY_STATUS_DISCHARGING:  
                BatteryStatus = "放电状态";  
                break;  
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:  
                BatteryStatus = "未充电";  
                break;  
            case BatteryManager.BATTERY_STATUS_FULL:  
                BatteryStatus = "充满电";  
                break;  
            case BatteryManager.BATTERY_STATUS_UNKNOWN:  
                BatteryStatus = "未知道状态";  
                break;  
            }  
              
            String BatteryTemp = "";
			switch (intent.getIntExtra("health", BatteryManager.BATTERY_HEALTH_UNKNOWN))   
            {  
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:  
                BatteryTemp = "未知错误";  
                break;  
            case BatteryManager.BATTERY_HEALTH_GOOD:  
                BatteryTemp = "状态良好";  
                break;  
            case BatteryManager.BATTERY_HEALTH_DEAD:  
                BatteryTemp = "电池没有电";  
                break;  
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:  
                BatteryTemp = "电池电压过高";  
                break;  
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:  
                BatteryTemp =  "电池过热";  
                break;  
            } 
			System.out.println("response : 目前电量为" + BatteryN + "% --- " + BatteryStatus + "\n" + "电压为" + BatteryV + "mV --- " + BatteryTemp + "\n" + "温度为" + (BatteryT*0.1) + "℃");  
		}else if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
			System.out.println("response : " + "时间变化 ");
		}
	}
	
}