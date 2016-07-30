package com.netmjgz.msg;
/**
 * 扫描信息上报
 * @author Administrator
 *
 */
public class SaomiaoXinxiReq {
	private String name ;//工人名字
	private String node ;//流程节点名字
	private String id ;//订单ID
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = id+":"+name+":"+node;
		return s;
	}
	
	
	
}
