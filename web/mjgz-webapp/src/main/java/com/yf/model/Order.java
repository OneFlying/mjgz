package com.yf.model;

import com.yf.annotation.Column;
import com.yf.annotation.Table;

/**
 * 订单表
 * @author abc
 *
 */
@Table(name="order")
public class Order {

	/**订单id*/
	private String id;
	
	/**物料id*/
	private String materialId;
	
	/**二维码*/
	private String code;
	
	/**预警时间*/
	private String warningTime;
	
	/**生产节点*/
	private String node;

	@Column(name="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="materialId")
	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	@Column(name="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="warningTime")
	public String getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(String warningTime) {
		this.warningTime = warningTime;
	}

	@Column(name="node")
	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}
	
	
}
