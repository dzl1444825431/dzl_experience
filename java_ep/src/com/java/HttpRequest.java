package com.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HttpRequest {
	
	
	public static void main(String[] args) {
		String url = "http://api.zaijiadd.com/location/province";
		String sendGet = sendGet(url, null);
		
		System.out.println("province=" + sendGet);
		
		List<String> cityList = new ArrayList<String>();
		List<String> areaList = new ArrayList<String>();
		
		
		try {
			//城市
			JSONObject jsonObject = new JSONObject(sendGet);
			List<Province> provinces = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<ArrayList<Province>>(){}.getType());
			
			if (provinces == null || provinces.size() == 0) {
				
			}else {
				
				for (Province province : provinces) {
					
					String url1 = "http://api.zaijiadd.com/location/province/" + province.getId() + "/city";
					String sendGet_city = sendGet(url1, null);
					
					System.out.println("city" + province.getId() + "=" + sendGet_city);
					
					
					JSONObject json_city = new JSONObject(sendGet_city);
					List<Province> citys = new Gson().fromJson(json_city.getJSONArray("data").toString(), new TypeToken<ArrayList<Province>>(){}.getType());
					
					if (citys == null || citys.size() == 0) {
						break;
					}
					for (Province p_city : citys) {
						cityList.add(p_city.getId() + "");
					}
					
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(60 * 1000 * 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n\nresp1onse : ======================Thread.sleep(60 * 1000 * 10) = \n\n");
		
		
		if (!cityList.isEmpty()) {
			
			for (String city_id : cityList) {
				getArea(city_id, areaList);//片区
				
			}
		}
		
//		getArea(2107 + "");
//		getCommunity(2108 + "");
		
		
		try {
			Thread.sleep(60 * 1000 * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n\nresp1onse : ======================Thread.sleep(60 * 1000 * 10) = \n\n");
		if (!areaList.isEmpty()) {
			
			for (String area_id : areaList) {
				getCommunity(area_id);//片区
			}
		}
		
		
	}

	//片区
	public static void getArea(String city_id, List<String> areaList){
		
		String url_city = "http://api.zaijiadd.com/city/" + city_id + "/areas";
		String sendGet_area = sendGet(url_city, null);
		
		System.out.println(/*url_city + */"area" + city_id + "=" + sendGet_area);
		
		try {
			JSONObject jsonObject = new JSONObject(sendGet_area);
			List<Province> provinces = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<ArrayList<Province>>(){}.getType());
			
			if (provinces == null || provinces.size() == 0) {
				return;
			}
			for (Province province : provinces) {
				areaList.add(province.getId() + "");
//				System.out.println("resp1onse : province.getId() = " + city_id + " " + province.getId());
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	public static void getCommunity(String area_id){
		//小区
		String url_city = "http://api.zaijiadd.com/city/area/" + area_id + "/communities?offset=0&size=500";
		String sendGet_community = sendGet(url_city, null);
		
		System.out.println(/*url_city + */"community" + area_id + "=" + sendGet_community);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection
					.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; Android 4.4.4; HUAWEI Y635-CL00 Build/HuaweiY635-CL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36 ##23c42fa3-d23d-4e0e-bb38-2317ca4ab17d");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main2(String[] args) {
        //发送 GET 请求
//        String s=HttpRequest.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
//        System.out.println(s);
        
		Map<String, String> params = new HashMap<String, String>();
//		param1(params);
		
		params.put("version_pro","2.1");
		params.put("manufacturer","HUAWEI");
		params.put("model","HUAWEI Y635-CL00");
		params.put("os","Android");
		params.put("version_os","19");
		params.put("channel","web");
		params.put("uniquecode","A00000554506EF");
		params.put("web_version","1");
		params.put("gecCode","CN");
		params.put("dingzaiID","1326906");
		params.put("sessionID","f187134442e3492e96e1e1d4cd2f4bc8");
		
		params.put("requestType","1100");//对方请求分类逻辑
		
		for (int i = 40; i < 60; i++) {
			params.put("albumID", i +"");
//			System.out.println("resp1onse : i = " + i);
			getDatas(params);
		}
		
    }

	private static void getDatas(Map<String, String> params) {
		//        发送 POST 请求
		        String encodeParameters = encodeParameters(params);
//		        System.out.println("resp1onse : encodeParameters = " + encodeParameters);
				String sr=HttpRequest.sendPost("http://browser.ilovegame.net/app", encodeParameters);
		        
		        sr = sr.replace("\"code\":200", "\"code\":201")
		        		.replace("categoryID", "category_id")
		        		.replace("\"logo\"", "\"logo_url\"")
//		        		.replace("\"gameAlbum\"", "\"data\"")
		        		.replace("\"originalLink\"", "\"jump_url\"")
		        		.replace("\"description\"", "\"desc\"")
		        		;
		        System.out.println(sr);
	}

	private static void param1(Map<String, String> params) {
		params.put("version_pro","2.1");
		params.put("manufacturer","HUAWEI");
		params.put("model","HUAWEI Y635-CL00");
		params.put("os","Android");
		params.put("version_os","19");
		params.put("channel","web");
		params.put("uniquecode","A00000554506EF");
		params.put("web_version","1");
		params.put("gecCode","CN");
		params.put("dingzaiID","1326906");
		params.put("sessionID","f187134442e3492e96e1e1d4cd2f4bc8");
		params.put("tipTime","1445838790596");
		params.put("requestType","1100");
	}
	
	private static String encodeParameters(Map<String, String> params) {
		return encodeParameters(params, "UTF-8");
	}

	static String encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString()/*.getBytes(paramsEncoding)*/;
        } catch (UnsupportedEncodingException uee) {
        	
        }
        return "";
    }
	
	
	
}