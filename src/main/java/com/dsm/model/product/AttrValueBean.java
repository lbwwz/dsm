package com.dsm.model.product;

import java.io.Serializable;

/**
 * 属性的属性值
 * @author lbwwz
 *
 */
public class AttrValueBean implements Comparable<AttrValueBean>,Serializable {

	private static final long serialVersionUID = 499388446522218050L;
	private Long valueId;
	
	private String valueName;
	
	private Long attrId;
	
	private Integer catId;
	
	private int valueSort;

	//属性状态，0：禁用； 1：正常
	private int status;
	
	public AttrValueBean() {}

	public AttrValueBean(String valueName, long attrId, Integer status) {
		this.valueName = valueName;
		this.attrId = attrId;
		this.status = status;
	}

	public AttrValueBean(Long valueId, String valueName) {
		this.valueId = valueId;
		this.valueName = valueName;
	}

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(long attrId) {
		this.attrId = attrId;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getValueSort() {
		return valueSort;
	}

	public void setValueSort(int valueSort) {
		this.valueSort = valueSort;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AttrValueBean)) return false;

		AttrValueBean that = (AttrValueBean) o;

		if (valueSort != that.valueSort) return false;
		if (status != that.status) return false;
		if (valueId != null ? !valueId.equals(that.valueId) : that.valueId != null) return false;
		if (valueName != null ? !valueName.equals(that.valueName) : that.valueName != null) return false;
		if (attrId != null ? !attrId.equals(that.attrId) : that.attrId != null) return false;
		return catId != null ? catId.equals(that.catId) : that.catId == null;

	}

	@Override
	public int hashCode() {
		int result = valueId != null ? valueId.hashCode() : 0;
		result = 31 * result + (valueName != null ? valueName.hashCode() : 0);
		result = 31 * result + (attrId != null ? attrId.hashCode() : 0);
		result = 31 * result + (catId != null ? catId.hashCode() : 0);
		result = 31 * result + valueSort;
		result = 31 * result + status;
		return result;
	}

	@Override
	public int compareTo(AttrValueBean o) {
		return this.valueSort-o.valueSort;
	}
	
	
	

}
