package com.yf.model;

import com.yf.annotation.Column;
import com.yf.annotation.Table;

/**
 * 用户表
 * @author abc
 *
 */
@Table(name="user")
public class User {

	/**用户id*/
	private String id;
	
	/**用户名称*/
	private String name;
	
	/**用户号码*/
	private String phone;
	
	/**用户职务*/
	private String duty;
	
	/**密码*/
	private String passwd;

	@Column(name="passwd")
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

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

	@Column(name="phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="duty")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	
}
