package com.dsm.model.product;

/**
 * 商品的类目信息封装
 * @author lbwwz
 *
 */
public class CategoryBean implements Comparable<CategoryBean> {

	private int catId;

	private String catName;	//名称

	private String catDesc; //类目描述

	private Integer parentId;	//父级类目ID

	private Integer catSort;	//类目排序

	private Integer status;		//可用状态：1，可用；0，不可用

	private Integer showInNav;	//主页菜单栏显示？（主页管理员设置）

	public CategoryBean() {}

	public CategoryBean(int catId, String catName, Integer status) {
		super();
		this.catName = catName;
		this.catId = catId;
		this.status = status;
	}

	public CategoryBean( String catName, Integer status ,Integer showInNav ,Integer parentId) {
		super();
		this.catName = catName;
		this.status = status;
		this.showInNav = showInNav;
		this.parentId = parentId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getCatSort() {
		return catSort;
	}

	public void setCatSort(Integer catSort) {
		this.catSort = catSort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getShowInNav() {
		return showInNav;
	}

	public void setShowInNav(Integer showInNav) {
		this.showInNav = showInNav;
	}

	@Override
	public int compareTo(CategoryBean o) {
		return this.catSort-o.catSort;
	}

	@Override
	public String toString() {
		return "CategoryBean{" +
				"catId=" + catId +
				", catName='" + catName + '\'' +
				", catDesc='" + catDesc + '\'' +
				", parentId=" + parentId +
				", catSort=" + catSort +
				", status=" + status +
				", showInNav=" + showInNav +
				'}';
	}
}
