package com.dsm.model.product;

import java.io.Serializable;
import java.util.List;

/**
 * 某一的类别商品属性信息的封装
 * 比如手机类，该类封装了手机类所有的属性的填写规范信息
 * 用于卖家录入商商品时页面的显示和录入规则定义
 * @author Think
 *
 */
public class ProductAttrBean implements Comparable<ProductAttrBean>,Serializable {


	private static final long serialVersionUID = -3470095136542536895L;
	private int attrId;

	private int catId;

	private String attrName;

	// 定义的排序顺序
	private Integer attrSort;

	// 属性的类型(是否是销售属性 ，1：销售属性； 0：基本属性)
	private Integer isSale;

	//是否关键；0：否； 1：是；（用于分类查询中某一个具体子类的特征查询栏：例如手机类中选择了手机屏幕尺寸4.3；4.7的商品等等）
	//关键属性只能通过select下拉框选择属性值，若为否，则可以自定义属性值
	private Integer isKey;

	// 是否必填：0：否； 1：是
	private Integer isMust;

	//属性状态，0：禁用； 1：正常
	private Integer status;
	
	private List<AttrValueBean> attrValues;
	
	public ProductAttrBean() {}


	public ProductAttrBean(int catId, String attrName, int isSale, int isKey, int isMust) {
		super();
		this.catId = catId;
		this.attrName = attrName;
		this.isSale = isSale;
		this.isKey = isKey;
		this.isMust = isMust;
	}



	public int getAttrId() {
		return attrId;
	}

	public void setAttrId(int attrId) {
		this.attrId = attrId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Integer getAttrSort() {
		return attrSort;
	}

	public void setAttrSort(Integer attrSort) {
		this.attrSort = attrSort;
	}

	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	public Integer getIsKey() {
		return isKey;
	}

	public void setIsKey(Integer isKey) {
		this.isKey = isKey;
	}

	public Integer getIsMust() {
		return isMust;
	}

	public void setIsMust(Integer isMust) {
		this.isMust = isMust;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<AttrValueBean> getAttrValues() {
		return attrValues;
	}

	public void setAttrValues(List<AttrValueBean> attrValues) {
		this.attrValues = attrValues;
	}

	@Override
	public String toString() {
		return "ProductAttrBean{" +
				"attrId=" + attrId +
				", catId=" + catId +
				", attrName='" + attrName + '\'' +
				", attrSort=" + attrSort +
				", isSale=" + isSale +
				", isKey=" + isKey +
				", isMust=" + isMust +
				", status=" + status +
				", attrValues=" + attrValues +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProductAttrBean)) return false;

		ProductAttrBean that = (ProductAttrBean) o;

		if (attrId != that.attrId) return false;
		if (catId != that.catId) return false;
		if (attrName != null ? !attrName.equals(that.attrName) : that.attrName != null) return false;
		if (attrSort != null ? !attrSort.equals(that.attrSort) : that.attrSort != null) return false;
		if (isSale != null ? !isSale.equals(that.isSale) : that.isSale != null) return false;
		if (isKey != null ? !isKey.equals(that.isKey) : that.isKey != null) return false;
		if (isMust != null ? !isMust.equals(that.isMust) : that.isMust != null) return false;
		if (status != null ? !status.equals(that.status) : that.status != null) return false;
		return attrValues != null ? attrValues.equals(that.attrValues) : that.attrValues == null;

	}

	@Override
	public int hashCode() {
		int result = attrId;
		result = 31 * result + catId;
		result = 31 * result + (attrName != null ? attrName.hashCode() : 0);
		result = 31 * result + (attrSort != null ? attrSort.hashCode() : 0);
		result = 31 * result + (isSale != null ? isSale.hashCode() : 0);
		result = 31 * result + (isKey != null ? isKey.hashCode() : 0);
		result = 31 * result + (isMust != null ? isMust.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (attrValues != null ? attrValues.hashCode() : 0);
		return result;
	}

	/**
	 * 重写 Comparable 接口的 compareTo 方法
	 * @param o 比较的对象
	 * @return
     */
	@Override
	public int compareTo(ProductAttrBean o) {
		return this.attrSort-o.attrSort;
	}


}
