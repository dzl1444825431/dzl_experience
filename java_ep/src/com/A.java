package com;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class A {
	
	public static void main(String[] args) {
		
		String json = "{\"success\":false,\"output\":\"\u6ca1\u6709\u5546\u54c1\u6570\u636e\uff01\",\"box_name\":\"goods_list\",\"data\":{\"code\":99}}";
		
		System.out.println("resp1onse : json = " + json);
		System.out.println("resp1onse : json = " + uniCodeToUtf8(json));
		
	}
	
	public static String uniCodeToUtf8(String str) {
        if (str == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = str.indexOf("\\u", pos)) != -1) {
            sb.append(str.substring(pos, i));
            if (i + 5 < str.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(str.substring(i + 2, i + 6), 16));
            }
        }

        if (sb.length() == 0) {
            return str;
        }

        return sb.toString();
    }

	
	public static void main1(String[] args) {
		
		String xmlString = "<xml><sign>CFEFF37245DFAF4C0E868FAF427EAB8C</sign><body>a_cup1</body><mch_id>1322767701</mch_id><spbill_create_ip>10.132.15.26</spbill_create_ip><total_fee>3</total_fee><notify_url>http://api.test.haoyangapp.com/company/get_about</notify_url><appid>wxab7549fe603216fd</appid><out_trade_no>040810500681398</out_trade_no><nonce_str>zfOQgGyFLorDBESzlIqIXFMOYjCzigGY</nonce_str><trade_type1><trade_type2>APP</trade_type2></trade_type1><trade_type>APP</trade_type></xml>";
		XmlPullParser saxParser = getSAXParser(xmlString);
		try {
			int eventType = saxParser.getEventType();
//			while (eventType != XmlPullParser.END_DOCUMENT) {
//				switch (eventType) {
//				case XmlPullParser.START_TAG:
//					String nodeName = saxParser.getName();
//					if (!"xml".equals(nodeName)) {
//						
//						String text = saxParser.nextText();
//						System.out.println("resp1onse :" + nodeName +" = " + text);
//					}else {
//						System.out.println("resp1onse :" + nodeName);
//						
//					}
//					
//				}
//				eventType = saxParser.next();
//			}
			
			String tag = null;
			String text = null;
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("a", "ccc");
				obj.put("b", new ArrayList<String>());
			} catch (JSONException e) {
			}
			System.out.println("resp1onse : obj = " + obj);
			int i = 0;
			String[] types = XmlPullParser.TYPES;
			System.out.println("resp1onse : types = " + Arrays.toString(types));
			
			while (eventType != XmlPullParser.END_DOCUMENT) {
				System.out.println("resp1onse : v = " + i++ + "  " +  eventType);
	            if (eventType == XmlPullParser.START_TAG) {
	            	tag = saxParser.getName();
//	                System.out.println("resp1onse : start tag = " + saxParser.getName());
	            } else if (eventType == XmlPullParser.END_TAG) {
//	                stringBuffer.append("\nEND_TAG: " + saxParser.getName());
//	                System.out.println("resp1onse : end tag = " + saxParser.getName());
	            } else if (eventType == XmlPullParser.TEXT) {
	                System.out.println("resp1onse : " + tag + " = " + saxParser.getText());
	            }
	            eventType = saxParser.next();
	        }
			
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	static XmlPullParser getSAXParser(String xmlString){
		System.out.println("resp1onse : xmlString = " + xmlString);
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = factory.newPullParser();
			xmlPullParser.setInput(new StringReader(xmlString));
			return xmlPullParser;
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
//		return Response.success(xmlPullParser, HttpHeaderParser.parseCacheHeaders(response));
	}

}
