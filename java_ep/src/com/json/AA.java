package com.json;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class AA implements Serializable {

	public static void main(String[] args) {

		String p1 = "[{\"city_name\":\"广西\",\"id\":\"450000\",\"sub_city_info\":[{\"city_name\":\"南宁市\",\"id\":\"450102\",\"sub_city_info\":[{\"city_name\":\"西乡塘区\",\"id\":\"450103\"}]}]}]";

		List<AA> pp1 = JSON.parseArray(p1, AA.class);
		System.out.println("resp1onse : pp1 = " + pp1);
	}

	private String id;
	private String city_name;

	private List<AA> sub_city_info;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public List<AA> getSub_city_info() {
		return sub_city_info;
	}

	public void setSub_city_info(List<AA> sub_city_info) {
		this.sub_city_info = sub_city_info;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", city_name=" + city_name + ", sub_city_info=" + sub_city_info + "]";
	}

}
