package com.dsm.model.product;

import java.io.Serializable;

/**
 * 具体的某一件商品的一项基本属性
 * @author lbwwz
 *
 */
public class BaseAttrBean implements Serializable,Comparable<BaseAttrBean>{


	private static final long serialVersionUID = -7989853405230150197L;
	private Integer attrId;
	private String attrName;

	private Integer valueId;
	private String attrValue;
	private int sort;

	public BaseAttrBean() {
	}

	public BaseAttrBean(Integer attrId,Integer valueId) {
		this.valueId = valueId;
		this.attrId = attrId;
	}

	public BaseAttrBean(int attrId, String attrName, int valueId, String valueName, int sort) {
		this.attrId = attrId;
		this.attrName = attrName;
		this.valueId = valueId;
		this.attrValue = valueName;
		this.sort = sort;
	}

	public Integer getAttrId() {
		return attrId;
	}

	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Integer getValueId() {
		return valueId;
	}

	public void setValueId(Integer valueId) {
		this.valueId = valueId;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaseAttrBean)) return false;

		BaseAttrBean that = (BaseAttrBean) o;

		if (sort != that.sort) return false;
		if (attrId != null ? !attrId.equals(that.attrId) : that.attrId != null) return false;
		if (attrName != null ? !attrName.equals(that.attrName) : that.attrName != null) return false;
		if (valueId != null ? !valueId.equals(that.valueId) : that.valueId != null) return false;
		return attrValue != null ? attrValue.equals(that.attrValue) : that.attrValue == null;

	}

	@Override
	public int hashCode() {
		int result = attrId != null ? attrId.hashCode() : 0;
		result = 31 * result + (attrName != null ? attrName.hashCode() : 0);
		result = 31 * result + (valueId != null ? valueId.hashCode() : 0);
		result = 31 * result + (attrValue != null ? attrValue.hashCode() : 0);
		result = 31 * result + sort;
		return result;
	}



	@Override
	public String toString() {
		return "BaseAttrBean{" +
				"attrId=" + attrId +
				", attrName='" + attrName + '\'' +
				", valueId=" + valueId +
				", attrValue='" + attrValue + '\'' +
				", sort=" + sort +
				'}';
	}

	@Override
	public int compareTo(BaseAttrBean o) {
		return o.getSort()-this.sort;
	}
}
