package com.dsm.model.product;

import java.io.Serializable;

/**
 * 属性的属性值
 * @author lbwwz
 *
 */
public class AttrValueBean implements Comparable<AttrValueBean>,Serializable {

	private static final long serialVersionUID = 499388446522218050L;
	private int valueId;
	
	private String valueName;
	
	private int attrId;
	
	private Integer catId;
	
	private Integer valueSort;

	//属性状态，0：禁用； 1：正常
	private Integer status;
	
	public AttrValueBean() {}

	public AttrValueBean(String valueName, int attrId, Integer status) {
		this.valueName = valueName;
		this.attrId = attrId;
		this.status = status;
	}

	public AttrValueBean(int valueId, String valueName) {
		this.valueId = valueId;
		this.valueName = valueName;
	}

	public int getValueId() {
		return valueId;
	}

	public void setValueId(int valueId) {
		this.valueId = valueId;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public int getAttrId() {
		return attrId;
	}

	public void setAttrId(int attrId) {
		this.attrId = attrId;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getValueSort() {
		return valueSort;
	}

	public void setValueSort(Integer valueSort) {
		this.valueSort = valueSort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AttrValueBean [valueId=" + valueId + ", valueName=" + valueName + ", attrId=" + attrId + ", catId="
				+ catId + ", valueSort=" + valueSort + "]";
	}

	@Override
	public int compareTo(AttrValueBean o) {
		return this.valueSort-o.valueSort;
	}
	
	
	

}
