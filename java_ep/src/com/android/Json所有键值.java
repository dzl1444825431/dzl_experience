package com.android;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;



public class Json所有键值 {
	
	public static void main(String[] args) {
		String str = "{\"tags\":[],\"company_id\":38912,\"cert_state\":1,\"job_type_icon\":\"http://static.iguamu.com/af1d34ab-0d62-4d0a-af13-803c974d50e3.png\",\"company_address\":\"北京市门头沟区蓝龙家园\",\"job_url\":\"http://guamu.yuedong001.com:80/guamu/app#/appjobdetail?id=104570\",\"job_type_name\":\"市场/宣传\",\"company_icon\":\"http://guamu.yuedong001.com:80/guamu/api/v1/imageByParam/39804ca0-f537-4c66-8a3c-fb89786f4289\",\"company_url\":\"http://guamu.yuedong001.com:80/guamu/app#/appentpdetail?id=38912\",\"distance\":\"0km\",\"pub_time\":\"2015-11-05\",\"job_lng\":0,\"area\":\"东城区\",\"job_lat\":0,\"search_zone\":0,\"settle_acount\":\"日结\",\"company_name\":\"北京峰林通达科技有限公司\",\"job_name\":\"手机兼职(简单，高薪)\",\"hot\":0,\"salary\":\"40元/小时 \",\"reliabilit\":5,\"publish_state\":1,\"work_time\":\"有手机就行，不限时间地点\",\"job_id\":104570}";
		
		try {
			JSONObject object = new JSONObject(str);
//			Iterator<String> keys = object.keys();
			
			for (Iterator<String> keys = object.keys(); keys.hasNext();) {
				String next = keys.next();
				if (object.get(next) instanceof String) {
					System.out.println("resp1one : String " + next + ";");
				}else {
					System.out.println("resp1one : int " + next + ";");
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

}
