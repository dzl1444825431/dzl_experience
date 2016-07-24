package com.android;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class 生成Json所有键值 {

	public static void main(String[] args) {
		// String str =
		// "{\"tags\":[{\"aa\":\"a\"}],\"company_id\":38912,\"cert_state\":1,\"job_type_icon\":\"http://static.iguamu.com/af1d34ab-0d62-4d0a-af13-803c974d50e3.png\",\"company_address\":\"北京市门头沟区蓝龙家园\",\"job_url\":\"http://guamu.yuedong001.com:80/guamu/app#/appjobdetail?id=104570\",\"job_type_name\":\"市场/宣传\",\"company_icon\":\"http://guamu.yuedong001.com:80/guamu/api/v1/imageByParam/39804ca0-f537-4c66-8a3c-fb89786f4289\",\"company_url\":\"http://guamu.yuedong001.com:80/guamu/app#/appentpdetail?id=38912\",\"distance\":\"0km\",\"pub_time\":\"2015-11-05\",\"job_lng\":0,\"area\":\"东城区\",\"job_lat\":0,\"search_zone\":0,\"settle_acount\":\"日结\",\"company_name\":\"北京峰林通达科技有限公司\",\"job_name\":\"手机兼职(简单，高薪)\",\"hot\":0,\"salary\":\"40元/小时
		// \",\"reliabilit\":5,\"publish_state\":1,\"work_time\":\"有手机就行，不限时间地点\",\"job_id\":104570}";
		// String str =
		// "{\"id\":1636266,\"store_id\":2614,\"goods_name\":\"\u5609\u5473\u6751\u5fc3\u91cc\u6a44\u6984\",\"spec\":\"1\u5305\uff08150\u514b\uff09\",\"pic\":\"http:\\/\\/7xkd2g.com2.z0.glb.qiniucdn.com\\/FkpVaSIL2Q_6ksQnxkUqdC-D-48I\",\"goods_id\":0,\"stock\":3,\"price\":\"3.50\",\"state\":2,\"old_price\":\"3.50\",\"is_discount\":0,\"limit_num\":0,\"category\":{\"id\":13,\"name\":\"\u65b9\u4fbf\u901f\u98df\",\"pid\":\"1\",\"pname\":\"\u98df\u54c1\u96f6\u98df\"}}";
		String str = "{\"id\":5360,\"name\":\"\u5609\u548c\u57ce\u6e29\u838e\u5357\u90e1\",\"city_id\":\"2106\",\"city_name\":\"\u5357\u5b81\u5e02\",\"area_id\":\"2108\",\"area_name\":\"\u5174\u5b81\u533a\",\"coupon_num\":\"1000\",\"coupon_taken_num\":\"7\",\"location_desc\":\"\u5357\u5b81\u5e02\u5174\u5b81\u533a\"}";

		str = "{\"address_id\":\"27\",\"buyer_id\":\"1250\",\"true_name\":\"\u4eaeandroid\",\"area_id\":\"45074\",\"area_info\":\"\u5357\u5b81\u5e02\u897f\u4e61\u5858\u533a\u5149\u8f89\u5c0f\u533a\",\"address\":\"\u5149\u8f89\u5927\u697c668\",\"tel_phone\":null,\"mob_phone\":\"15994348631\",\"is_default\":\"0\"}";
		str = "{\"itemId\":\"1447123962141444163\",\"itemName\":\"\uff08\u7279\u4ef7\uff09\u9648\u514b\u660e\u835e\u9ea6\u98ce\u5473\u6302\u97621kg\",\"itemPrice\":\"8.60\",\"itemCostPrice\":\"10.20\",\"itemSellCount\":\"1\",\"itemImage\":\"http://image.tenongwang.com/commodity/1458804470161670249.jpg\",\"itemImageList\":[{\"imageUrl\":\"http://image.tenongwang.com/commodity/1458804470161670249.jpg\"}],\"itemIsCollect\":\"0\",\"itemIsSource\":\"0\",\"itemSourceUrl\":\"\",\"itemShop\":{\"itemId\":\"1432258957735499242\",\"itemName\":\"\u7279\u519c\u7f51\",\"itemAddress\":\"\u6d77\u76d0\u53bf\u6b66\u539f\u9547\u767e\u5c3a\u5357\u8def28\u53f7\u4e0d\u591c\u57ce\u9152\u5e97\",\"itemTelephone\":\"057386286789\"},\"itemAttribute\":[],\"itemFirstId\":\"1404882229706984033\"}";
		str = "{\"itemImage\":\"http://image.tenongwang.com/commodity/1458804487407544736.jpg\",\"itemWidth\":\"750\",\"itemHeight\":\"4689\"}";
		str = "{\"shopId\":\"1432258957735499242\",\"shopName\":\"\u7279\u519c\u7f51\",\"itemList\":[{\"shopId\":\"1432258957735499242\",\"itemId\":\"1468909956451464197\",\"itemCommodityId\":\"1447123962141444163\",\"itemCommodityName\":\"\uff08\u7279\u4ef7\uff09\u9648\u514b\u660e\u835e\u9ea6\u98ce\u5473\u6302\u97621kg\",\"itemCommodityExtensionId\":\"1447123962141444164\",\"itemFirstId\":\"1404882229706984033\",\"itemFirstName\":\"\u7b52\",\"itemSecondId\":\"0\",\"itemSecondName\":\"\",\"itemPrice\":\"8.60\",\"itemStock\":\"1\",\"itemStockAmount\":\"11\",\"itemImage\":\"http://image.tenongwang.com/commodity/1458804470161670249.jpg\"}]}";
		str = "{\"id\":\"395\",\"type\":\"2\",\"typeName\":\"\u7b7e\u5230\u5956\u52b1\",\"score\":\"5\",\"content\":\"\",\"createTime\":\"2016-07-20 10:38:28\"}";
		str = "{\"userId\":\"1467767039888736489\",\"username\":\"15994348631\",\"score\":\"2020\",\"inviteCode\":\"J2O334\"}";
		str = "{\"itemId\":\"1467767181847809829\",\"itemName\":\"\u8c01\u8bf4\u7684\",\"itemPhone\":\"15994348631\",\"itemProvince\":\"\u6d59\u6c5f\u7701\",\"itemCity\":\"\u6d77\u76d0\u53bf\",\"itemArea\":\"\u6b66\u539f\u8857\u9053\",\"itemAddress\":\"\u591a\u5927\u7684\",\"itemZipcode\":\"315000\",\"itemIsDefault\":\"1\"}";
		str = "{\"orderId\":\"1469176018839688934\",\"orderSellerId\":\"1432258957735499242\",\"orderShopName\":\"\u7279\u519c\u7f51\",\"orderPrice\":\"59.40\",\"payType\":\"alipay\",\"waybillMoney\":\"0.00\",\"orderStatus\":\"0\",\"orderCreateTime\":\"1469176018\",\"isComment\":\"0\",\"itemList\":[{\"itemId\":\"1447123962141444163\",\"itemName\":\"\uff08\u7279\u4ef7\uff09\u9648\u514b\u660e\u835e\u9ea6\u98ce\u5473\u6302\u97621kg\",\"itemStandardFirst\":\"\u89c4\u683c\",\"itemFirstName\":\"\u7b52\",\"itemStandardSecond\":\"\",\"itemSecondName\":\"\",\"itemPrice\":\"8.60\",\"itemStock\":\"3\",\"itemImage\":\"\",\"isComment\":\"0\"},{\"itemId\":\"1466901670702137998\",\"itemName\":\"\u7279\u8272\u51c9\u9762\",\"itemStandardFirst\":\"\u89c4\u683c\",\"itemFirstName\":\"\u4efd\",\"itemStandardSecond\":\"\",\"itemSecondName\":\"\",\"itemPrice\":\"16.80\",\"itemStock\":\"2\",\"itemImage\":\"\",\"isComment\":\"0\"}]}";
		str = "{\"orderId\":\"1469195143437664396\",\"orderPayNo\":\"\",\"orderPrice\":\"29.20\",\"waybillMoney\":\"5.00\",\"orderArriveDate\":\"2016-07-23 09:00-10:00\",\"shopCode\":\"0\",\"orderRemark\":\"\",\"orderStatus\":\"-1\",\"orderCreateTime\":\"2016-07-22 21:45:43\",\"isComment\":\"0\",\"delivery\":{\"itemName\":\"\u6536\u8d272\",\"itemMobile\":\"15912345679\",\"itemTelephone\":\"\",\"itemProvince\":\"\u6d59\u6c5f\u7701\",\"itemCity\":\"\u6d77\u76d0\u53bf\",\"itemArea\":\"\u6b66\u539f\u8857\u9053\",\"itemAddress\":\"\u8857\u90532\",\"itemZipcode\":\"315000\"},\"shopList\":{\"shopId\":\"1432258957735499242\",\"shopName\":\"\u7279\u519c\u7f51\",\"shopAddress\":\"\u6d77\u76d0\u53bf\u6b66\u539f\u9547\u767e\u5c3a\u5357\u8def28\u53f7\u4e0d\u591c\u57ce\u9152\u5e97\",\"shopTelephone\":\"057386286789\",\"itemList\":[{\"itemId\":\"1447123962141444163\",\"itemName\":\"\uff08\u7279\u4ef7\uff09\u9648\u514b\u660e\u835e\u9ea6\u98ce\u5473\u6302\u97621kg\",\"itemStandardFirst\":\"\u89c4\u683c\",\"itemFirstName\":\"\u7b52\",\"itemStandardSecond\":\"\",\"itemSecondName\":\"\",\"itemPrice\":\"8.60\",\"itemStock\":\"2\",\"itemImage\":\"\",\"isComment\":\"0\"},{\"itemId\":\"1447124312834914360\",\"itemName\":\"\uff08\u7279\u4ef7\uff09\u9648\u514b\u660e\u9e21\u86cb\u7cbe\u81f4\u6302\u97621kg\",\"itemStandardFirst\":\"\u89c4\u683c\",\"itemFirstName\":\"\u7b52\",\"itemStandardSecond\":\"\",\"itemSecondName\":\"\",\"itemPrice\":\"7.00\",\"itemStock\":\"1\",\"itemImage\":\"\",\"isComment\":\"0\"}]}}";
		str = "{\"shopList\":[{\"shopId\":\"1432258957735499242\",\"shopName\":\"\u7279\u519c\u7f51\",\"address\":\"\u6d77\u76d0\u53bf\u6b66\u539f\u9547\u767e\u5c3a\u5357\u8def28\u53f7\u4e0d\u591c\u57ce\u9152\u5e97\",\"telephone\":\"057386286789\",\"businessTime\":\"8:00 - 20:00\",\"priceTotal\":\"37.8\",\"waybillMoney\":\"5\",\"itemList\":[{\"shopId\":\"1432258957735499242\",\"itemId\":\"1461116512779326344\",\"itemCommodityId\":\"1461116512779326343\",\"itemCommodityName\":\"\u8499\u725b\u4f18\u76caC340ml\uff08\u539f\u5473\uff09\",\"itemStandardNumber\":\"1\",\"itemStandardFirst\":\"\u89c4\u683c\",\"itemFirstName\":\"\u74f6\",\"itemStandardSecond\":\"\",\"itemSecondName\":\"\",\"itemCostPrice\":\"8.00\",\"itemPrice\":\"7.00\",\"itemStock\":\"1\",\"itemStockAmount\":\"9999\",\"itemImage\":\"\"},{\"shopId\":\"1432258957735499242\",\"itemId\":\"1447123962141444164\",\"itemCommodityId\":\"1447123962141444163\",\"itemCommodityName\":\"\uff08\u7279\u4ef7\uff09\u9648\u514b\u660e\u835e\u9ea6\u98ce\u5473\u6302\u97621kg\",\"itemStandardNumber\":\"1\",\"itemStandardFirst\":\"\u89c4\u683c\",\"itemFirstName\":\"\u7b52\",\"itemStandardSecond\":\"\",\"itemSecondName\":\"\",\"itemCostPrice\":\"10.20\",\"itemPrice\":\"8.60\",\"itemStock\":\"3\",\"itemStockAmount\":\"11\",\"itemImage\":\"\"}]}],\"delivery\":{\"itemId\":\"1469159747563733402\",\"itemName\":\"\u6536\u8d272\",\"itemPhone\":\"15912345679\",\"itemProvince\":\"\u6d59\u6c5f\u7701\",\"itemCity\":\"\u6d77\u76d0\u53bf\",\"itemArea\":\"\u6b66\u539f\u8857\u9053\",\"itemAddress\":\"\u8857\u90532\",\"itemZipcode\":\"315000\",\"itemIsDefault\":\"1\"}}";
		
		int level = sysoKeys(str, false);
		
		System.out.println();
		if (level > 0) {
			System.out.println("resp1onse : level = " + level + "\n=======================\n");
			sysoKeys(str, true);
		}

	}

	public static int sysoKeys(String str, boolean level) {
		try {
			JSONObject object = new JSONObject(str);
			return sysoKeys(object, level);
		} catch (JSONException e) {
		}
		return 0;
	}

	public static int sysoKeys(JSONObject object, boolean level) {

		int i = 0;

		try {

			for (Iterator<String> keys = object.keys(); keys.hasNext();) {
				String key = keys.next();
				Object value = object.get(key);
				if (value instanceof String) {
					System.out.println("private String " + key + ";");

				} else if (value instanceof JSONObject) {
					i++;
					System.out.println("private String " + key + ";");
					if (level) {
						System.out.println("\n Object start " + i);
						sysoKeys((JSONObject) value, level);
						System.out.println("\n Object end " + i);
						System.out.println();
					}

				} else if (value instanceof JSONArray) {
					i++;
					System.out.println("private String " + key + ";");
					if (level && ((JSONArray) value).length() > 0) {
						System.out.println("\n JSONArray start " + i);
						sysoKeys(((JSONArray) value).getJSONObject(0), level);
						System.out.println("\n JSONArray end " + i);
						System.out.println();
					}

				} else {
					System.out.println("private int " + key + ";");
				}
			}

			System.out.println("\n\n\n");
			for (Iterator<String> keys = object.keys(); keys.hasNext();) {
				String key = keys.next();
				System.out.println("//\t" + key + " = " + object.get(key));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return i;
	}

}
