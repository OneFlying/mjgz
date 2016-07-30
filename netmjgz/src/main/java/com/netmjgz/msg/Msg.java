package com.netmjgz.msg;
/**
 * 消息发送
 * @author Administrator
 *
 */
public class Msg {
	private int id = 0;//消息类型
	private Object object = null;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
