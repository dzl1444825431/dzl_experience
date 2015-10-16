package com.dzl.test.location;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
  
/** 
 * @author Geek_Soledad (66704238@51uc.com) 
 */  
public class LocationUtil {  
  
    public static String getAddress(double lat, double lng) {  
        String url = String  
                .format("http://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f&language=CN&sensor=true",  
                        lat, lng);  
        System.out.println(url);  
        String result = "";
//        result = DownloadUtil.downloadByUrl(url,  
//                Charset.defaultCharset());  
        return jsonSax(result);  
    }  
  
    private static String jsonSax(String in) {  
        String address = null;  
        try {  
            JSONTokener tokener = new JSONTokener(in);  
            JSONObject results = (JSONObject) tokener.nextValue();  
            if (!results.getString("status").equals("OK")) {  
                return "无法获取具体地址。";  
            }  
            System.out.println(results.toString());  
            JSONObject result = (JSONObject) results.getJSONArray("results")  
                    .get(0);  
            address = result.getString("formatted_address");  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
        return address;  
    }  
}  