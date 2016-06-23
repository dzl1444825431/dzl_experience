package com.json;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class AA2 implements Serializable {

	public static void main(String[] args) {

		String str = "[{\"sub_category_list\":[],\"quantity\":65,\"name\":\"全部品类\",\"code\":0},{\"sub_category_list\":[{\"quantity\":19,\"name\":\"全部\",\"code\":0},{\"quantity\":10,\"name\":\"中式简餐\",\"code\":100051},{\"quantity\":3,\"name\":\"地方小吃\",\"code\":100052},{\"quantity\":3,\"name\":\"米粉米线\",\"code\":100100},{\"quantity\":3,\"name\":\"黄焖鸡米饭\",\"code\":100102},{\"quantity\":1,\"name\":\"鸭脖卤味\",\"code\":100054},{\"quantity\":1,\"name\":\"麻辣烫\",\"code\":100097},{\"quantity\":1,\"name\":\"面馆\",\"code\":100099},{\"quantity\":1,\"name\":\"炸鸡炸串\",\"code\":100101},{\"quantity\":1,\"name\":\"饺子馄饨\",\"code\":100103}],\"quantity\":19,\"name\":\"快餐小吃\",\"code\":1021},{\"sub_category_list\":[{\"quantity\":0,\"name\":\"全部\",\"code\":0}],\"quantity\":0,\"name\":\"各地风味\",\"code\":100059},{\"sub_category_list\":[{\"quantity\":3,\"name\":\"全部\",\"code\":0},{\"quantity\":3,\"name\":\"汉堡快餐\",\"code\":100063},{\"quantity\":1,\"name\":\"意面披萨\",\"code\":100064}],\"quantity\":3,\"name\":\"汉堡披萨\",\"code\":100062},{\"sub_category_list\":[{\"quantity\":1,\"name\":\"全部\",\"code\":0},{\"quantity\":1,\"name\":\"韩国料理\",\"code\":100068}],\"quantity\":1,\"name\":\"异国美食\",\"code\":100065},{\"sub_category_list\":[{\"quantity\":14,\"name\":\"全部\",\"code\":0},{\"quantity\":8,\"name\":\"烤串\",\"code\":100086},{\"quantity\":8,\"name\":\"小龙虾\",\"code\":100087},{\"quantity\":5,\"name\":\"海鲜\",\"code\":100088},{\"quantity\":1,\"name\":\"火锅\",\"code\":100089}],\"quantity\":14,\"name\":\"火锅烧烤\",\"code\":100072},{\"sub_category_list\":[{\"quantity\":14,\"name\":\"全部\",\"code\":0},{\"quantity\":6,\"name\":\"面包蛋糕\",\"code\":1043},{\"quantity\":6,\"name\":\"奶茶果汁\",\"code\":1044},{\"quantity\":5,\"name\":\"甜品\",\"code\":1042}],\"quantity\":14,\"name\":\"甜点饮品\",\"code\":19},{\"sub_category_list\":[{\"quantity\":5,\"name\":\"全部\",\"code\":0},{\"quantity\":3,\"name\":\"便利店\",\"code\":100090},{\"quantity\":1,\"name\":\"水果\",\"code\":100093},{\"quantity\":1,\"name\":\"生鲜\",\"code\":100094}],\"quantity\":5,\"name\":\"果蔬超市\",\"code\":100077},{\"sub_category_list\":[{\"quantity\":15,\"name\":\"全部\",\"code\":0},{\"quantity\":9,\"name\":\"鲜花\",\"code\":1063},{\"quantity\":6,\"name\":\"蛋糕\",\"code\":1064}],\"quantity\":15,\"name\":\"鲜花蛋糕\",\"code\":23},{\"sub_category_list\":[{\"quantity\":0,\"name\":\"全部\",\"code\":0}],\"quantity\":0,\"name\":\"送药上门\",\"code\":22},{\"sub_category_list\":[{\"quantity\":17,\"name\":\"全部\",\"code\":0},{\"quantity\":6,\"name\":\"面包蛋糕\",\"code\":1067},{\"quantity\":6,\"name\":\"果汁咖啡\",\"code\":1069},{\"quantity\":5,\"name\":\"精选甜品\",\"code\":1066},{\"quantity\":2,\"name\":\"馋嘴小吃\",\"code\":100166},{\"quantity\":1,\"name\":\"鲜切水果\",\"code\":1065}],\"quantity\":17,\"name\":\"下午茶\",\"code\":940},{\"sub_category_list\":[{\"quantity\":39,\"name\":\"全部\",\"code\":0},{\"quantity\":11,\"name\":\"烤串海鲜\",\"code\":100035},{\"quantity\":8,\"name\":\"小龙虾\",\"code\":100175},{\"quantity\":5,\"name\":\"奶茶果汁\",\"code\":100040},{\"quantity\":3,\"name\":\"便利店\",\"code\":100037},{\"quantity\":3,\"name\":\"汉堡披萨\",\"code\":100038},{\"quantity\":2,\"name\":\"地方小吃\",\"code\":100041},{\"quantity\":1,\"name\":\"麻辣烫\",\"code\":100042},{\"quantity\":1,\"name\":\"米粉面馆\",\"code\":100044},{\"quantity\":1,\"name\":\"炸鸡炸串\",\"code\":100045},{\"quantity\":1,\"name\":\"鸭脖卤味\",\"code\":100176}],\"quantity\":39,\"name\":\"夜宵\",\"code\":970},{\"sub_category_list\":[{\"quantity\":10,\"name\":\"全部\",\"code\":0},{\"quantity\":6,\"name\":\"海鲜/烧烤\",\"code\":100026},{\"quantity\":2,\"name\":\"甜点饮品\",\"code\":100033},{\"quantity\":1,\"name\":\"火锅\",\"code\":100025},{\"quantity\":1,\"name\":\"西餐\",\"code\":100031}],\"quantity\":10,\"name\":\"土豪馆\",\"code\":950}]";

		List<AA2> list = JSON.parseArray(str, AA2.class);
		System.out.println("resp1onse : object = " + list);
		
		for (AA2 aa2 : list) {
			System.out.println(aa2);
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String quantity;
	private String name;
	private String code;

	private List<AA2> sub_category_list;

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<AA2> getSub_category_list() {
		return sub_category_list;
	}

	public void setSub_category_list(List<AA2> sub_category_list) {
		this.sub_category_list = sub_category_list;
	}

	@Override
	public String toString() {
		String category_two = "";
		if (sub_category_list != null && !sub_category_list.isEmpty()) {
			int i = 0;
			for (AA2 aa2 : sub_category_list) {
				if (i++ == 3) {
					category_two += aa2.getName();
					break;
				}
				category_two += aa2.getName() + ",";
			}
			category_two = category_two.replace("全部,", "");
		}
		
		return ",\"category_one\":\"" + name +"\",\"category_two\":\"" + category_two + "\"";
	}
	
	

}
