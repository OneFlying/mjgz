package com.netmjgz.util;

import com.google.gson.Gson;
import com.netmjgz.msg.HeartBeatReq;

public class JsonUtils {
	
	public static String ObjToJson(Object obj){
		Gson gson = new Gson();
		String json = gson.toJson(obj);  
		return json;
	}
	
	public static Object JsonToObj(String json,Class clazz){
		Gson gson = new Gson();
		Object obj2 = gson.fromJson(json, clazz);
		return obj2;
		
	}
	

}
