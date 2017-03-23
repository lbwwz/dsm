package com.dsm.model.product;

import java.util.ArrayList;
import java.util.List;


/**
 * 商品的一组销售属性的封装（多组销售属性的选项用来组成一件SKU）
 *
 * 格式如：
 * 		颜色	-> "红色,1231:2312","黄色,1231:2321",···
 * 用于商品详细页的SKU信息显示
 * @author lbwwz
 *
 */
public class SkuSaleAttr{
	// 销售属性的中文名
	private String zhName;

	// "中文属性值,attrId:valueId" 字符串组成的集合
	private List<String> valueList;

	public SkuSaleAttr() {}

	public SkuSaleAttr(String zhName) {
		this.zhName = zhName;
	}

	public SkuSaleAttr(String zhName,String valueListItem) {
		this.zhName = zhName;
		this.valueList = new ArrayList<>();
		valueList.add(valueListItem);
	}

	public String getZhName() {
		return zhName;
	}

	public List<String> getValueList() {
		return valueList;
	}

	public void addValueItem(String valueListItem) {
		valueList.add(valueListItem);
	}

	@Override
	public String toString() {
		return "SkuSaleAttr [zhName=" + zhName + ", valueList=" + valueList + "]";
	}


}
