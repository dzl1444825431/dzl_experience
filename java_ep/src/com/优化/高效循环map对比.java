package com.优化;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 采取第二种getFromMapByEntrySet
 *  第二种 比 第一种快至少30%
 *  原因 ：与存储方式有关
 */
public class 高效循环map对比 {

	public static void getFromMap(Map map) {
		for (Iterator ite = map.keySet().iterator(); ite.hasNext();) {
			Object key = ite.next();
			Object value = map.get(key);
		}
	}

	public static void getFromMapByEntrySet(Map map) {
		for (Iterator ite = map.entrySet().iterator(); ite.hasNext();) {
			Map.Entry entry = (Map.Entry) ite.next();
			entry.getKey();
			entry.getValue();
		}
	}
	
	public static void getFromMapByEntrySetForeach(Map map) {
		Set<Map.Entry> entrySet = map.entrySet();
		for (Map.Entry entry : entrySet) {
			entry.getKey();
			entry.getValue();
		}
	}
	
	

	public static void main(String[] args) {
		Map map = new HashMap();
		for (int i = 0; i < 2000000; i++) {
			map.put("key" + i, "value" + i);
		}
		long currentTime = System.currentTimeMillis();
		getFromMap(map);
		long currentTime2 = System.currentTimeMillis();
		getFromMapByEntrySet(map);
		long currentTime3 = System.currentTimeMillis();
		getFromMapByEntrySetForeach(map);
		long currentTime4 = System.currentTimeMillis();
		System.out.println(currentTime2 - currentTime);
		System.out.println(currentTime3 - currentTime2);
		System.out.println(currentTime4 - currentTime3);
	}

}
