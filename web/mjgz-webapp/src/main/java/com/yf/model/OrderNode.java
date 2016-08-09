package com.yf.model;

import com.yf.annotation.Column;
import com.yf.annotation.Table;

@Table(name="node")
public class OrderNode {

	/**节点名称*/
	private String nodeName;
	
	/**订单id*/
	private String orderId;
	
	/**流程节点开始时间*/
	private String starttime;
	
	/**流程节点超时时间*/
	private String overtime;
	
	/**当前节点状态*/
	private int status;
	
	/**流程节点结束*/
	private String endtime;
	
	@Column(name="endtime")
	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Column(name="status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="nodeName")
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Column(name="orderId")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name="starttime")
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	@Column(name="overtime")
	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
	
	
}
