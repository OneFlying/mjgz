package com.yf.model;

import com.yf.annotation.Column;
import com.yf.annotation.Table;
/**
 * 物料表
 * @author abc
 *
 */
@Table(name="material")
public class Material {

	/**物料id*/
	private String id;
	
	/**物料编号*/
	private String num;
	
	/**物料名称*/
	private String name;
	
	/**物料规格*/
	private String stantard;
	
	/**物料材质*/
	private String material;
	
	/**使用寿命*/
	private String life;
	
	/**图纸*/
	private String drawing;

	@Column(name="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="num")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="stantard")
	public String getStantard() {
		return stantard;
	}

	public void setStantard(String stantard) {
		this.stantard = stantard;
	}

	@Column(name="material")
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name="life")
	public String getLife() {
		return life;
	}

	public void setLife(String life) {
		this.life = life;
	}

	@Column(name="drawing")
	public String getDrawing() {
		return drawing;
	}

	public void setDrawing(String drawing) {
		this.drawing = drawing;
	}
		
}