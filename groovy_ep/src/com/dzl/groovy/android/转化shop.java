package com.dzl.groovy.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class 转化shop {

	static List<Object> list = new ArrayList<Object>();

	public static void main(String[] args) {

		String array = "盲拍！高档创意名片新做法！,名赫名片,￥199.00,LOGO设计 企业标识设计 淘宝连续10年No.1服务商,猫叔传媒,￥20.00,网上洗照片 5寸6寸7寸柯达/富士手机数码冲印洗相片晒照片,纸匮旗舰店,￥0.50,商务笔记本定制 可定制LOGO,深泰旗舰店,￥17.70,广告纸杯定做印刷 250ml装 5元拍样品包邮,印帮网,￥125.00,英力佳创意记事本办公用品文具笔记本日记本韩国商务本子厚A5定制,英力佳旗舰店,￥28.00";

		array = array.replaceAll("￥", "");

		String[] imgs = { "http://res.biyinjishi.com/img/dyn/b/P/SP/10000242/Face/s3hjdVyCoJHdN.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000552/Face/s3huEdaHiJkgh.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000224/Face/s3o2nMfzFf48n.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000772/Face/s3gsFmW0TGGb2.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000716/Face/s3gqJCvhS3MJD.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000828/Face/s3hEC16fGpIMC.jpg"

		};

		// array = array.replaceAll("¥ ", "");

		String[] split = array.split(",");
		double ic = 1.23;
		int length = split.length;
		System.out.println("resp1onse : split = " + length);
		try {
			for (int i = 0; i < length;) {
				C a = new C((int) ((i + 5) * 10 * ic), split[i++], split[i++], Double.parseDouble(split[i++]));
				list.add(a);
			}
		} catch (Exception e) {
		}

		length = list.size();
		for (int i = 0; i < length; i++) {
			((C) list.get(i)).shop_url = imgs[i];
		}

		HashMap map = new HashMap();
		map.put("list1", list);
		System.out.println("resp1onse : list = " + list.size());
		String json = JSON.toJSONString(list);
		System.out.println("resp1onse : json = " + json);

	}

	public static void main6(String[] args) {

		String str = "我的订单,我的评价,我的足迹,意见反馈,分享APP给好友,评价我们,设置";

		String[] split = str.split(",");
		for (int i = 0; i < split.length; i++) {
			D d = new D();
			d.shop_id = i + 1;
			d.shop_name = split[i];
			list.add(d);

		}

		HashMap hashMap = new HashMap();
		hashMap.put("list", list);

		String json = JSON.toJSONString(list);
		System.out.println("resp1onse : json = " + json);

	}

	public static void main5(String[] args) {
		List<Object> list1 = new ArrayList<Object>();
		List<Object> list2 = new ArrayList<Object>();
		List<Object> list3 = new ArrayList<Object>();
		String array = "综合排序,销量排序,评分排序,价格 由低到高,价格 由高到低";
		pus(array, list3, 3);
		array = "不限,荐 推荐产品,热 热闹产品,立 进店立等可取,送 同城免费送货,普 报价含普通发票,增 星级企业产品,星 优先商家产品,优 报价含增值税发票";
		pus(array, list2, 2);
		array = "不限,日常办公,宣传展示,活动营销,形象装饰,生产经营,资料打印,个性定制,设计服务,更多服务";
		pus(array, list1, 1);

		int[] color = { 0xffffcc00, 0xffff6666, 0xffff9966, 0xff66cc33, 0xffff3366, 0xffff3366, 0xffff9900,
				0xffff33ff };

		for (int i = 1; i < list2.size(); i++) {
			((C) list2.get(i)).shop_color = color[i - 1];
		}

		HashMap map = new HashMap();
		map.put("list1", list1);
		map.put("list2", list2);
		map.put("list3", list3);
		System.out.println("resp1onse : list = " + list.size());
		String json = JSON.toJSONString(map);
		System.out.println("resp1onse : json = " + json);

	}

	static void pus(String array, List<Object> list2, int type) {
		String[] split = array.split(",");
		double ic = 1.23;
		int length = split.length;
		try {
			for (int i = 0; i < length;) {
				C a = new C((int) ((i + 5) * 10 * ic), split[i++]);
				a.shop_type = type;
				list2.add(a);
			}
		} catch (Exception e) {
		}
	}

	public static void main4(String[] args) {

		String array = "励志墙贴 企业文化墙贴,7.50,/张,月销量7569笔,唐生八戒图片处理服务 P图/修图/抠图/去水印/照片精修,5.00,/次,月销量16823笔,英力佳创意记事本办公用品文具笔记本日记本韩国商务本子厚A5定制,8.20,/本,月销量12108笔,5-12号邮政纸箱现货出售/特殊尺寸可定做,0.18,/个,月销量31253笔,"
				+ "商务笔记本定制 可定制LOGO,6.80,/本,月销量11362笔,图片处理服务 P图 照片精修,5.00,/次,月销量22873笔,彩色包装箱定制,0.15,/个,月销量41120笔,铁质注水门型展架 简单大方 寿命长,30.00,/个,月销量8944笔,"
				+ "7-15寸 水晶照片摆台 拉米娜版画定制,6.10,/张,月销量8745笔,圆领T恤衫（彩色） 团队文化衫定制 公司T恤衫 衫客团体定制,49.00,/件,月销量5250笔,明信片DIY印刷 创意贺卡定制 印刷包邮,0.29,/张,月销量8315笔,A4A5宣传单印制彩页印刷 DM单印刷,68.00,/张,月销量6926笔,"
				+ "明信片DIY印刷 创意贺卡定制 印刷包邮,0.29,/张,月销量8315笔,A4A5宣传单印制彩页印刷 DM单印刷,68.00,/张,月销量6926笔,牛皮纸袋定制 手提袋印刷 礼品定做,0.50,/个,月销量6069笔,门型户外注水展架 户外广告牌,35.00,/个,月销量5946笔,"
				+ "【测试】比印集市的集市商家产品测试样品02,0.01,/盒,月销量219笔,二维码不干胶定制印刷 100%扫描,10.00,/张,月销量5671笔,户外遮阳伞广告伞定制 30把起印,39.00,/个,月销量5271笔,照片书印刷 宝宝相册打印 聚会毕业纪念册,29.80,/本,月销量4731笔";

		String[] imgs = { "http://res.biyinjishi.com/img/dyn/b/P/SP/10000896/Face/s3hf8huIgWqqa.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000543/Face/s3omD0yKcDguk.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000201/Face/s3gzMCbdFmiJD.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000828/Face/s3hEC16fGpIMC.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000772/Face/s3gsFmW0TGGb2.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000491/Face/s3gQBwsVU7DCc.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000898/Face/s3hQ8uosjzyti.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000746/Face/s3ol4AF7OnFVm.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000370/Face/s3gUA6BSY8WC4.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000393/Face/s3g5rgJgOME1u.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000367/Face/s3g3AzYWSDQK2.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000559/Face/s3o9slF9QwGcT.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000335/Face/s3h888ByphBNt.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000772/Face/s3gsFmW0TGGb2.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000370/Face/s3gUA6BSY8WC4.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000543/Face/s3omD0yKcDguk.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000896/Face/s3hf8huIgWqqa.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000201/Face/s3gzMCbdFmiJD.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000898/Face/s3hQ8uosjzyti.jpg",
				"http://res.biyinjishi.com/img/dyn/b/P/SP/10000754/Face/s3hyrVknuGH8j.jpg" };

		// array = array.replaceAll("¥ ", "");

		String[] split = array.split(",");
		double ic = 1.23;
		int length = split.length;
		System.out.println("resp1onse : split = " + length);
		try {
			for (int i = 0; i < length;) {
				C a = new C((int) ((i + 5) * 10 * ic), split[i++], Double.parseDouble(split[i++]), split[i++],
						split[i++]);
				list.add(a);
			}
		} catch (Exception e) {
		}

		length = list.size();
		for (int i = 0; i < length; i++) {
			((C) list.get(i)).shop_url = imgs[i];
		}

		HashMap map = new HashMap();
		map.put("list1", list);
		System.out.println("resp1onse : list = " + list.size());
		String json = JSON.toJSONString(list);
		System.out.println("resp1onse : json = " + json);

	}

	public static void main3(String[] args) {

		// String array = "励志墙贴 企业文化墙贴 ¥ 7.5 网上洗照片 5寸6寸7寸柯达/富士手机数码冲印洗相片晒照片 ¥ 0.1
		// LOGO设计 企业标识设计淘宝连续10年No.1服务商 ¥ 20.0 广告立牌 门店商场导向牌指示牌 ¥ 130.0 A4黑白打印
		// 最低0.04元/面限70g复印纸双面打印 ¥ 0.04 盲拍！ 高档创意名片新做法！ ¥ 199.0";
		String array = "励志墙贴 企业文化墙贴 ¥ 7.5 http://res.biyinjishi.com/img/dyn/b/p/sp/RecomPics/hnTRnhUJWNr/hnTRnhUJWNr.jpg 网上洗照片 5寸6寸7寸柯达/富士手机数码冲印洗相片晒照片 ¥ 0.1 http://res.biyinjishi.com/img/dyn/b/p/sp/RecomPics/gqEZUaRGIRi/gqEZUaRGIRi.jpg LOGO设计 企业标识设计淘宝连续10年No.1服务商 ¥ 20.0 http://res.biyinjishi.com/img/dyn/b/p/sp/RecomPics/hpgKifDYqVl/hpgKifDYqVl.jpg 广告立牌 门店商场导向牌指示牌 ¥ 130.0 http://res.biyinjishi.com/img/dyn/b/p/sp/RecomPics/hcLBKL77Baj/hcLBKL77Baj.jpg A4黑白打印 最低0.04元/面限70g复印纸双面打印 ¥ 0.04 http://res.biyinjishi.com/img/dyn/b/p/sp/RecomPics/hciRRJ3dO3U/hciRRJ3dO3U.jpg 盲拍！ 高档创意名片新做法！ ¥ 199.0 http://res.biyinjishi.com/img/dyn/b/p/sp/RecomPics/hrz4vuwwMeo/hrz4vuwwMeo.jpg";
		String array2 = "日常办公 共 300 件产品 http://res.biyinjishi.com/img/s_v1/mfp/products_10.jpg 宣传展示 共 201 件产品 http://res.biyinjishi.com/img/s_v1/mfp/products_20.jpg 活动营销 共 108 件产品 http://res.biyinjishi.com/img/s_v1/mfp/products_30.jpg 形象装饰 共 36 件产品 http://res.biyinjishi.com/img/s_v1/mfp/products_35.jpg 生产经营 共 166 件产品 http://res.biyinjishi.com/img/s_v1/mfp/products_50.jpg 资料打印 共 23 件产品 http://res.biyinjishi.com/img/s_v1/mfp/products_60.jpg 个性定制 共 154 件产品 http://res.biyinjishi.com/img/s_v1/mfp/products_70.jpg 设计服务 共 25 件产品 http://res.biyinjishi.com/img/s_v1/mfp/products_65.jpg 更多服务 共 5 件产品 http://res.biyinjishi.com/img/s_v1/mfp/products_99.jpg";

		array = array.replaceAll("¥ ", "");
		array2 = array2.replaceAll("共 ", "").replaceAll(" 件产品", "");

		String[] split = array.split(" ");
		String[] split2 = array2.split(" ");
		System.out.println("resp1onse : list = " + split2.length);
		List<Object> list2 = new ArrayList<Object>();
		double ic = 1.23;
		try {
			for (int i = 0; i < split.length;) {
				A a = new A((int) ((i + 5) * 10 * ic), split[i++] + " " + split[i++], Double.parseDouble(split[i++]),
						split[i++]);
				list.add(a);
			}
		} catch (Exception e) {
		}
		try {
			for (int i = 0; i < split2.length;) {
				B a = new B((int) ((i + 5) * 10 * ic), split2[i++], Integer.parseInt(split2[i++]), split2[i++]);
				list2.add(a);
			}
		} catch (Exception e) {
		}

		HashMap map = new HashMap();
		map.put("list1", list);
		map.put("list2", list2);
		System.out.println("resp1onse : list = " + list.size() + " " + list2.size());
		String json = JSON.toJSONString(map);
		System.out.println("resp1onse : json = " + json);

	}

	public static class D {
		public int shop_id;
		public String shop_name;
	}

	public static class C {

		public int shop_id;
		public String shop_name;
		public String shop_desc;
		public String shop_url;
		public String shop_unit;
		public String shop_sale_count;
		public double shop_price;
		public int shop_count;
		public int shop_type;
		public int shop_color;
		// 励志墙贴 企业文化墙贴,7.50,/张,月销量7569笔

		public C(int shop_id, String shop_name, double shop_price, String shop_unit, String shop_sale_count) {
			super();
			this.shop_id = shop_id;
			this.shop_name = shop_name;
			this.shop_price = shop_price;
			this.shop_unit = shop_unit;
			this.shop_sale_count = shop_sale_count;
		}

		public C(int shop_id, String shop_name) {
			super();
			this.shop_id = shop_id;
			this.shop_name = shop_name;
		}

		public C(int shop_id, String shop_name, String shop_desc, double shop_price) {
			super();
			this.shop_id = shop_id;
			this.shop_name = shop_name;
			this.shop_desc = shop_desc;
			this.shop_price = shop_price;
		}

	}

	public static class A {
		public int shop_id;
		public String shop_name;
		public String shop_url;
		public double shop_price;

		public A(int shop_id, String shop_name, double shop_price, String shop_url) {
			super();
			this.shop_id = shop_id;
			this.shop_name = shop_name;
			this.shop_price = shop_price;
			this.shop_url = shop_url;
		}

	}

	public static class B {
		public int shop_id, shop_count;
		public String shop_name, shop_url;

		public B(int shop_id, String shop_name, int shop_count, String shop_url) {
			super();
			this.shop_id = shop_id;
			this.shop_count = shop_count;
			this.shop_name = shop_name;
			this.shop_url = shop_url;
		}

	}

	public static void main2(String[] args) {

		String[] array = {
				"1 印比三家 http://res.biyinjishi.com/img/dyn/s/510178/L/ocSNF9slKoL.jpg 大辰印务 民主路6-8号都市华庭A座一楼18号商铺 >7.0公里",
				"1 推荐商家 http://res.biyinjishi.com/img/dyn/s/500321/L/hdoIWyeQoCm.jpg 印特丽数字印刷 白沙水路79号长湴工业园10幢3楼 >519.14公里 99%好评/5.0分 EB",
				"2 推荐商家 http://res.biyinjishi.com/img/dyn/s/500770/L/oDXtV7OXrLU.png 丰海通快印（白家庄店） 白家庄向军北里6号楼106室（团结湖西门对面） >2046.65公里 EAH",
				"3 推荐商家 http://res.biyinjishi.com/img/dyn/s/500655/L/hLkU3zjaFrz.jpg 大洋图文（黄花岗店） 先-烈中路100号中科院地理所首层（黄花岗剧院北侧） >513.67公里 99%好评/4.95分",
				"1 热门商家 http://res.biyinjishi.com/img/dyn/s/510178/L/ocSNF9slKoL.jpg 大辰印务 民主路6-8号都市华庭A座一楼18号商铺 >7.0公里"

		};

		ArrayList<Shop> list = new ArrayList<Shop>();

		int i = 2001;
		for (String string : array) {
			Shop shop = shop(string);
			shop.setShop_vip("V");
			shop.setShop_id(i *= 1.15);
			list.add(shop);
		}
		System.out.println("resp1onse : list = " + list.size());
		HashMap hashMap = new HashMap();
		hashMap.put("list", list);

		String json = JSON.toJSONString(list);
		System.out.println("resp1onse : json = " + json);

	}

	static Shop shop(String aString) {
		String[] split = aString.split(" ");

		Shop shop = null;
		int i = 0;
		if (split.length == 6) {
			shop = new Shop(split[i++], split[i++], split[i++], split[i++], split[i++], split[i++]);
		} else if (split.length == 8) {
			shop = new Shop(split[i++], split[i++], split[i++], split[i++], split[i++], split[i++], split[i++],
					split[i++]);
		} else if (split.length == 7) {
			shop = new Shop(split[i++], split[i++], split[i++], split[i++], split[i++], split[i++]);
			String score = split[i++];
			if (score.length() > 3) {
				shop.setShop_score(score);
			} else {
				shop.setShop_feature(score);
			}

		} else {
			System.out.println("resp1onse : size = " + split.length + split[1]);
		}
		return shop;
	}

	public static class Shop {

		private int shop_id;
		private String shop_position;
		private String shop_category;
		private String img_url;
		private String shop_name;
		private String shop_address;
		private String shop_distance;
		private String shop_score;
		private String shop_feature;
		private String shop_vip;

		public Shop(String shop_position, String shop_category, String img_url, String shop_name, String shop_address,
				String shop_distance) {
			super();
			this.shop_position = shop_position;
			this.shop_category = shop_category;
			this.img_url = img_url;
			this.shop_name = shop_name;
			this.shop_address = shop_address;
			this.shop_distance = shop_distance;
		}

		public Shop(String shop_position, String shop_category, String img_url, String shop_name, String shop_address,
				String shop_distance, String shop_score, String shop_feature) {
			super();
			this.shop_position = shop_position;
			this.shop_category = shop_category;
			this.img_url = img_url;
			this.shop_name = shop_name;
			this.shop_address = shop_address;
			this.shop_distance = shop_distance;
			this.shop_score = shop_score;
			this.shop_feature = shop_feature;
		}

		public Shop(String shop_position, String shop_category, String img_url, String shop_name, String shop_address,
				String shop_distance, String shop_score) {
			super();
			this.shop_position = shop_position;
			this.shop_category = shop_category;
			this.img_url = img_url;
			this.shop_name = shop_name;
			this.shop_address = shop_address;
			this.shop_distance = shop_distance;
			this.shop_score = shop_score;
		}

		public int getShop_id() {
			return shop_id;
		}

		public void setShop_id(int shop_id) {
			this.shop_id = shop_id;
		}

		public String getImg_url() {
			return img_url;
		}

		public void setImg_url(String img_url) {
			this.img_url = img_url;
		}

		public String getShop_name() {
			return shop_name;
		}

		public void setShop_name(String shop_name) {
			this.shop_name = shop_name;
		}

		public String getShop_address() {
			return shop_address;
		}

		public void setShop_address(String shop_address) {
			this.shop_address = shop_address;
		}

		public String getShop_distance() {
			return shop_distance;
		}

		public void setShop_distance(String shop_distance) {
			this.shop_distance = shop_distance;
		}

		public String getShop_score() {
			return shop_score;
		}

		public void setShop_score(String shop_score) {
			this.shop_score = shop_score;
		}

		public String getShop_feature() {
			return shop_feature;
		}

		public void setShop_feature(String shop_feature) {
			this.shop_feature = shop_feature;
		}

		public String getShop_vip() {
			return shop_vip;
		}

		public void setShop_vip(String shop_vip) {
			this.shop_vip = shop_vip;
		}

		public String getShop_position() {
			return shop_position;
		}

		public void setShop_position(String shop_position) {
			this.shop_position = shop_position;
		}

		public String getShop_category() {
			return shop_category;
		}

		public void setShop_category(String shop_category) {
			this.shop_category = shop_category;
		}

	}

}
