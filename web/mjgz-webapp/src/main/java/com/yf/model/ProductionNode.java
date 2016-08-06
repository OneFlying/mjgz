package com.yf.model;

import com.yf.annotation.Column;
import com.yf.annotation.Table;

@Table(name="productionnode")
public class ProductionNode {

	/**生产点id*/
	private String id;
	
	/**生产节点名称*/
	private String name;

	@Column(name="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
