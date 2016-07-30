package com.netmjgz.msg;
/**
 * 心跳消息
 * @author Administrator
 *
 */
public class HeartBeatReq{
	
	private String data = "heartbeat";
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return data;
	}
	
	
	
	
	
}
