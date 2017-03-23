package com.dsm.model.product;

import java.io.Serializable;

/**
 * 具体的某一件商品的一项基本属性
 * @author lbwwz
 *
 */
public class BaseAttrBean implements Serializable{

	private static final long serialVersionUID = -1177829900562353729L;
	private String attrName;
	
	private String attrValue;
	
	private int sort;
	
	public BaseAttrBean() {}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	

}
