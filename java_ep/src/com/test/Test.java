package com.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;
import java.util.Map.Entry;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("resp1onse new Date().getTime() = " + new Date().getTime() / 1000);
		System.out.println("resp1onse new Date().getTime() = " + System.currentTimeMillis() / 1000);
	}

	public static void main1(String[] args) {
		
//		new Test().getRandomStr(32);
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("appid","wx72034c683cc1a087"); 						//公众账号ID		
		params.put("mch_id","1310191001");								//商户号	
		params.put("nonce_str","19qwW9IrksMtIGG9QIq34jNEsBNHccRx"); 					//随机字符串		
		params.put("body","a_cup");							//商品描述	
		params.put("out_trade_no","201602050112"); 						//商户订单号	
		params.put("total_fee","10");									//总金额			
		params.put("spbill_create_ip","10.161.78.23"); 					//终端IP		
		params.put("notify_url","http://apiuser.1qiao.com:88/company/get_about"); //通知地址		
		params.put("trade_type","APP");									//交易类型	
		
		StringBuffer a = sortAndAppendKV(params);
		
		String apiKey = "13c10402a12adf429720ecb0093c661b";
		a.append("key=").append(apiKey);
		System.out.println("resp1onse a.toString() = " + a.toString());
		
		String encryption = getEncryption(a.toString());
		
		System.out.println("resp1onse encryption = " + encryption);
		
		params.put("sign",encryption);						//签名	
		
		//4B89416620F499D94588E4FF18318E1B
		//4B89416620F499D94588E4FF18318E1B
		//4B89416620F499D94588E40F18318E1B
		System.out.println("resp1onse strToXml = " + strToXml(params));
	}
	
	public static String strToXml(Map<String, String> params){
		
		StringBuffer sb = new StringBuffer();
		if (params != null) {
			sb.append("<xml>");
			
			for (Iterator<Map.Entry<String, String>> it = params.entrySet().iterator(); it.hasNext();) {
				Entry<String, String> entry = it.next();
				sb.append("<").append(entry.getKey()).append(">")
					.append(entry.getValue())
					.append("</").append(entry.getKey()).append(">");
			}
			
			sb.append("</xml>");
			
		}
		return sb.toString();
		
	}

	public static StringBuffer sortAndAppendKV(Map<String, String> params) {
		
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.addAll(params.keySet());
		
		StringBuffer sb = new StringBuffer();
		
		Iterator<String> it = treeSet.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			sb.append(key).append("=").append(params.get(key)).append("&");
		}
		
		return sb;
	}
	
	 public static String getEncryption(String originString) {
        String result = "";
        if (originString != null) {
            try {
                // 指定加密的方式为MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 进行加密运算
                byte bytes[] = md.digest(originString.getBytes());
                for (int i = 0; i < bytes.length; i++) {
                    // 将整数转换成十六进制形式的字符串 这里与0xff进行与运算的原因是保证转换结果为32位
                    String str = Integer.toHexString(bytes[i] & 0xFF);
                    if (str.length() == 1) {
                        str += "F";
                    }
                    result += str;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return result.toUpperCase();
    }
	
	private String[] array = {
			 "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
			,"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
			,"0","1","2","3","4","5","6","7","8","9"
			,"0"};

	/**
	 * 随机生成字符串
	 * @param len
	 * @return
	 */
	public String getRandomStr(int len) {
		
		Random random = new Random();
		int index = 0;
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < len; i++) {
			
			index = random.nextInt(62);
			sb.append(array[index]);
		}
		
		return sb.toString();
	}

}
