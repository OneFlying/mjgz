package com.netmjgz.msg;

/**
 * 扫描ACK
 * @author Administrator
 *
 */
public class SaomiaoXinxiAck {
	private boolean status = false;//是否成功
	private String data = "";//消息内容
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = status+":"+data;
		return s;
	}
}
