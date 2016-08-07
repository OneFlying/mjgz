package com.yf.model;

import java.util.ArrayList;
import java.util.List;

import com.yf.annotation.Column;
import com.yf.annotation.Table;

/**
 * 订单表
 * @author abc
 *
 */
@Table(name="mjorder")
public class Order {

	/**订单id*/
	private String id;
	
	/**物料id*/
	private String materialId;
	
	/**二维码*/
	private String code;
	
	/**
	 * 订单状态
	 */
	private int status;
	
	/**上一次的节点名称*/
	private String node;
	
	private List<OrderNode> orderNodes = new ArrayList<OrderNode>();
	
	public List<OrderNode> getOrderNodes() {
		return orderNodes;
	}

	public void setOrderNodes(List<OrderNode> orderNodes) {
		this.orderNodes = orderNodes;
	}

	@Column(name="node")
	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@Column(name="status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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
	
}
