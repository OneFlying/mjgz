package netmjgz.com.netmjgz;

import com.google.gson.Gson;
import com.netmjgz.msg.HeartBeatReq;

public class Test {
	
	class BagOfPrimitives {
		  private int value1 = 1;
		  private String value2 = "abc";
		  private transient int value3 = 3;
		  BagOfPrimitives() {
		    // no-args constructor
		  }
		}
	//对象转为Json
	@org.junit.Test
	public void ObjToJson(){
		HeartBeatReq obj = new HeartBeatReq();
		Gson gson = new Gson();
		String json = gson.toJson(obj);  
		System.out.println(json);
	}
	
	@org.junit.Test
	public void JsonToObj(){
		String json = "{\"id\":1,\"data\":\"heartbeat\"}";
		Gson gson = new Gson();
		HeartBeatReq obj2 = gson.fromJson(json, HeartBeatReq.class);
		System.out.println(obj2.getData());
		
	}

}
