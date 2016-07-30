package com.netmjgz.msg;

import java.util.List;

/**
 * 流程信息Ack
 * @author Administrator;
 *
 */
public class LiuchengXinxiAck {
	
	private List<String> list ;//流程节点列表

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s= null;
		if(list!=null){
			for(String name : list){
				System.out.println(name);
				s+=name+",";
			}
		}
		return s;
	}
	
	
	

}
