package com.json;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class 生成Json所有键值 {

	public static void main(String[] args) {
		String str = "{\"id\":5360,\"name\":\"\u5609\u548c\u57ce\u6e29\u838e\u5357\u90e1\",\"city_id\":\"2106\",\"city_name\":\"\u5357\u5b81\u5e02\",\"area_id\":\"2108\",\"area_name\":\"\u5174\u5b81\u533a\",\"coupon_num\":\"1000\",\"coupon_taken_num\":\"7\",\"location_desc\":\"\u5357\u5b81\u5e02\u5174\u5b81\u533a\"}";

		str = "{\"address_id\":\"27\",\"buyer_id\":\"1250\",\"true_name\":\"\u4eaeandroid\",\"area_id\":\"45074\",\"area_info\":\"\u5357\u5b81\u5e02\u897f\u4e61\u5858\u533a\u5149\u8f89\u5c0f\u533a\",\"address\":\"\u5149\u8f89\u5927\u697c668\",\"tel_phone\":null,\"mob_phone\":\"15994348631\",\"is_default\":\"0\"}";
		str = "{\"id\":627114,\"mt_poi_id\":42671445,\"name\":\"品螺香（罗赖店）\",\"support_coupon\":0,\"is_favorite\":0,\"sort_reason_type\":0,\"sort_reason_tag\":\"\",\"delivery_type\":0,\"mt_delivery_time\":\"46分钟\",\"status\":1,\"origin_status\":1,\"status_desc\":\"\",\"pic_url\":\"http://p0.meituan.net/xianfu/3bc644777306357bc979fd9358d66713383737.jpg.webp\",\"avg_delivery_time\":46,\"shipping_fee\":0.0,\"shipping_fee_discount\":0,\"min_price\":12.0,\"new_promotion\":0,\"discounts2\":[{\"type\":4,\"info\":\"新用户立减7元\",\"icon_url\":\"http://p1.meituan.net/xianfu/82a7fc1eb81256ae36e40d18462df7d62048.png\",\"use_icon_from_server\":1,\"display_code\":2,\"sequence\":3},{\"type\":20,\"info\":\"实际支付20元返3元商家代金券\",\"icon_url\":\"http://p1.meituan.net/xianfu/2cdedb516dd2c6cd5fe35ab92b9243de2048.png\",\"use_icon_from_server\":1,\"display_code\":2,\"sequence\":7}],\"shipping_time_info\":{\"in_shipping_time\":1,\"beg_shipping_time\":\"\",\"status_content\":\"\",\"desc_content\":\"\",\"reservation_status\":0},\"month_sale_num\":389,\"wm_poi_score\":4.6,\"shipping_time_x\":\"[[\\\"07:43-19:55\\\"],[\\\"07:43-19:55\\\"],[\\\"07:43-19:55\\\"],[\\\"07:43-19:55\\\"],[\\\"07:43-19:55\\\"],[\\\"07:43-19:55\\\"],[\\\"07:43-19:55\\\"]]\",\"pre_book\":0,\"brand_type\":0,\"sales\":389,\"priority\":0,\"wm_poi_opening_days\":181,\"buz_type\":0,\"across_book_offset_days\":0,\"across_book_max_days\":0,\"latitude\":22871336,\"longitude\":108275942,\"primitiveDistance\":1165.5952374496812,\"distance\":\"1.2km\",\"poi_type_icon\":\"\",\"ad_attr\":\"\",\"shipping_fee_tip\":\"配送费 ¥0\",\"min_price_tip\":\"起送价 ¥12\"}";
		printKeys(str);

	}

	@SuppressWarnings("unchecked")
	public static void printKeys(String jsonStr) {
		try {
			JSONObject object = new JSONObject(jsonStr);

			for (Iterator<String> keys = object.keys(); keys.hasNext();) {
				String key = keys.next();
				System.out.println("private String " + key + ";");
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
