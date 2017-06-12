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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AttrValueBean)) return false;

		AttrValueBean that = (AttrValueBean) o;

		if (valueId != that.valueId) return false;
		if (attrId != that.attrId) return false;
		if (valueName != null ? !valueName.equals(that.valueName) : that.valueName != null) return false;
		if (catId != null ? !catId.equals(that.catId) : that.catId != null) return false;
		if (valueSort != null ? !valueSort.equals(that.valueSort) : that.valueSort != null) return false;
		return status != null ? status.equals(that.status) : that.status == null;

	}

	@Override
	public int hashCode() {
		int result = valueId;
		result = 31 * result + (valueName != null ? valueName.hashCode() : 0);
		result = 31 * result + attrId;
		result = 31 * result + (catId != null ? catId.hashCode() : 0);
		result = 31 * result + (valueSort != null ? valueSort.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		return result;
	}

	@Override
	public int compareTo(AttrValueBean o) {
		return this.valueSort-o.valueSort;
	}
	
	
	

}
