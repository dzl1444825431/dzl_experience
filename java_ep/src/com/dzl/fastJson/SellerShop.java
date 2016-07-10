package com.dzl.fastJson;

import java.io.Serializable;

public class SellerShop implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	private String title; // 店名 特征 距离 营业时间
	private String feature;
	private String distance;
	private String shop_hours;
	private String deliver_time; // 配送时间如30分钟
	private String sell_order; // 已卖订单数量
	private String goods_img_url_thumb; // normal img

	public SellerShop(String title, String feature, String distance, String shop_hours, String deliver_time,
			String sell_order,String goods_img_url_thumb) {
		super();
		this.title = title;
		this.feature = feature;
		this.distance = distance;
		this.shop_hours = shop_hours;
		this.deliver_time = deliver_time;
		this.sell_order = sell_order;
		this.goods_img_url_thumb = goods_img_url_thumb;
	}

	public SellerShop() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getShop_hours() {
		return shop_hours;
	}

	public void setShop_hours(String shop_hours) {
		this.shop_hours = shop_hours;
	}

	public String getDeliver_time() {
		return deliver_time;
	}

	public void setDeliver_time(String deliver_time) {
		this.deliver_time = deliver_time;
	}

	public String getSell_order() {
		return sell_order;
	}

	public void setSell_order(String sell_order) {
		this.sell_order = sell_order;
	}

	public String getGoods_img_url_thumb() {
		return goods_img_url_thumb;
	}

	public void setGoods_img_url_thumb(String goods_img_url_thumb) {
		this.goods_img_url_thumb = goods_img_url_thumb;
	}


}
