package com.dzl.fastJson;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class GenerateJson {

	public static void main(String[] args) {
		// 店名 特征 距离 营业时间 配送时间 已卖订单数量
		List<SellerShop> list = new ArrayList<SellerShop>();
		String values = "星百香炸鸡店、新店开业,主营炸鸡,源料三黄鸡、821m、营业24小时、26分钟、月售300单、http://img5.imgtn.bdimg.com/it/u=486155690,1770085545&fm=21&gp=0.jpg";
		list.add(getShop(values));

		values = "开心果园水果超市、多样海外进口水果、2.2km、营业8~21:30、35分钟、月售600单、http://e.hiphotos.baidu.com/bainuo/crop=0,0,690,461;w=690;q=90;c=nuomi,95,95/sign=6fea29f9b619ebc4d4372cd9bf16e3cc/472309f79052982264d52f5fd2ca7bcb0b46d443.jpg";
		list.add(getShop(values));

		values = "力赞龙虾馆、特色龙虾，宵夜、3.6km、营业14~24:00、50分钟、月售800单、http://e.hiphotos.baidu.com/bainuo/crop%3D0%2C0%2C690%2C460%3Bw%3D690%3Bq%3D89%3Bc%3Dnuomi%2C95%2C95/sign=5341b69bf7deb48fef26fb9ecd2f1619/b812c8fcc3cec3fd52b88b8fd288d43f86942741.jpg";
		list.add(getShop(values));

		values = "光影千层糕甜品、少儿成人都满足，全城免费配送、8km、营业8~22:00、34分钟、月售138单、http://img0.imgtn.bdimg.com/it/u=3866240613,2112310837&fm=21&gp=0.jpg";
		list.add(getShop(values));
		
		values = "甜巢甜品、折扣商品0.8折起、1.7km、营业8~22:00、37分钟、月售194单、http://p1.meituan.net/xianfu/b80bb824056a673d0c60be2965e0f0e2215991.jpg.webp";
		list.add(getShop(values));
		
		values = "台湾卤肉饭（长堽店）、由专送提供高品质送餐服务、1.9km、营业16~24:00、30分钟、月售471单、http://p0.meituan.net/xianfu/f5013c9252f85fc25f69979fbe63a23c378551.jpg.webp";
		list.add(getShop(values));

		String jsonString = JSON.toJSONString(list);
		System.out.println("resp1onse : jsonString = " + jsonString);
		
		int size = 4;
		System.out.println("resp1onse : size = " + size % 6);

	}

	private static SellerShop getShop(String values) {
		String[] v = values.split("、");
		SellerShop sellerShop = new SellerShop(v[0], v[1], v[2], v[3], v[4], v[5], v[6]);
		return sellerShop;
	}

}
