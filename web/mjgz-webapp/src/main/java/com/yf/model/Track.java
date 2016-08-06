package com.yf.model;

import com.yf.annotation.Column;
import com.yf.annotation.Table;

/**
 * 追踪表
 * 
 * @author abc
 *
 */
@Table(name = "track")
public class Track {

	/** id */
	private String id;

	/** 订单id */
	private String orderId;

	/** 员工名称 */
	private String workName;

	/** 生产叶节点 */
	private String node;

	/** 二维码 */
	private String code;

	private String time;

	@Column(name="time")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "orderId")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "workName")
	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	@Column(name = "node")
	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
