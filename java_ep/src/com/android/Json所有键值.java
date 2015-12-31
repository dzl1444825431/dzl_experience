package com.android;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;



public class Json所有键值 {
	
	public static void main(String[] args) {
//		String str = "{\"tags\":[{\"aa\":\"a\"}],\"company_id\":38912,\"cert_state\":1,\"job_type_icon\":\"http://static.iguamu.com/af1d34ab-0d62-4d0a-af13-803c974d50e3.png\",\"company_address\":\"北京市门头沟区蓝龙家园\",\"job_url\":\"http://guamu.yuedong001.com:80/guamu/app#/appjobdetail?id=104570\",\"job_type_name\":\"市场/宣传\",\"company_icon\":\"http://guamu.yuedong001.com:80/guamu/api/v1/imageByParam/39804ca0-f537-4c66-8a3c-fb89786f4289\",\"company_url\":\"http://guamu.yuedong001.com:80/guamu/app#/appentpdetail?id=38912\",\"distance\":\"0km\",\"pub_time\":\"2015-11-05\",\"job_lng\":0,\"area\":\"东城区\",\"job_lat\":0,\"search_zone\":0,\"settle_acount\":\"日结\",\"company_name\":\"北京峰林通达科技有限公司\",\"job_name\":\"手机兼职(简单，高薪)\",\"hot\":0,\"salary\":\"40元/小时 \",\"reliabilit\":5,\"publish_state\":1,\"work_time\":\"有手机就行，不限时间地点\",\"job_id\":104570}";
//		String str = "{\"id\":1636266,\"store_id\":2614,\"goods_name\":\"\u5609\u5473\u6751\u5fc3\u91cc\u6a44\u6984\",\"spec\":\"1\u5305\uff08150\u514b\uff09\",\"pic\":\"http:\\/\\/7xkd2g.com2.z0.glb.qiniucdn.com\\/FkpVaSIL2Q_6ksQnxkUqdC-D-48I\",\"goods_id\":0,\"stock\":3,\"price\":\"3.50\",\"state\":2,\"old_price\":\"3.50\",\"is_discount\":0,\"limit_num\":0,\"category\":{\"id\":13,\"name\":\"\u65b9\u4fbf\u901f\u98df\",\"pid\":\"1\",\"pname\":\"\u98df\u54c1\u96f6\u98df\"}}";
		String str = "{\"id\":5360,\"name\":\"\u5609\u548c\u57ce\u6e29\u838e\u5357\u90e1\",\"city_id\":\"2106\",\"city_name\":\"\u5357\u5b81\u5e02\",\"area_id\":\"2108\",\"area_name\":\"\u5174\u5b81\u533a\",\"coupon_num\":\"1000\",\"coupon_taken_num\":\"7\",\"location_desc\":\"\u5357\u5b81\u5e02\u5174\u5b81\u533a\"}";
		
		sysoKeys(str);
		
	}

	public static void sysoKeys(String str) {
		try {
			JSONObject object = new JSONObject(str);
//			Iterator<String> keys = object.keys();
			
			for (Iterator<String> keys = object.keys(); keys.hasNext();) {
				String key = keys.next();
				if (object.get(key) instanceof String) {
					System.out.println("private String " + key + ";");
				}else {
					System.out.println("private int " + key + ";");
				}
			}
			
			System.out.println("\n\n\n");
			for (Iterator<String> keys = object.keys(); keys.hasNext();) {
				String key = keys.next();
				System.out.println(key + " = " + object.get(key));
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
