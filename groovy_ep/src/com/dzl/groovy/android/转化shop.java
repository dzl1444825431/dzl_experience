package com.dzl.groovy.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

public class 转化shop {

	public static void main(String[] args) {

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
			shop = new Shop(split[i++], split[i++], split[i++], split[i++], split[i++], split[i++], split[i++], split[i++]);
		} else if (split.length == 7) {
			shop = new Shop(split[i++], split[i++], split[i++], split[i++], split[i++], split[i++]);
			String score = split[i++];
			if (score.length() > 3) {
				shop.setShop_score(score);
			}else {
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
