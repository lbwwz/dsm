package com.dsm.model.product;

import java.util.List;

/**
 * 商品实体类
 * @author lbwwz
 */
public class Goods extends GoodsEntry {

	//商品货号
	private String goodSn;
	//商品简述
	private String goodBrief;
	//商品详述
	private String goodDesc;
	//商品评分
	private double goodGrade;
	//该商品包含的一组Sku
	private List<Sku> skus;

	//商品的一组基础属性
	private List<GoodsAttrBean> baseAttrs;

	/*
	 * 通过商品的sku对象生成的SKU属性信息
	 * 每个list中存放着一组属性名与属性值List对应的MAP对象
	 *
	 * 格式见:销售属性的中文名:{attrId:valueId,中文属性值}的字符串数组组成的集合
	 * 如：	颜色   -> 1234:2332,黄色	2314:3215,绿色···
	 * 		尺码   -> 6534:2478,S	8169:5253,M···
	 */
	private SkuViewInfo skuViewInfo;
	
	public Goods() {
		super();
	}
	
	
	public String getGoodSn() {
		return goodSn;
	}
	public void setGoodSn(String goodSn) {
		this.goodSn = goodSn;
	}
	public String getGoodBrief() {
		return goodBrief;
	}
	public void setGoodBrief(String goodBrief) {
		this.goodBrief = goodBrief;
	}
	public String getGoodDesc() {
		return goodDesc;
	}
	public void setGoodDesc(String goodDesc) {
		this.goodDesc = goodDesc;
	}
	public double getGoodGrade() {
		return goodGrade;
	}
	public void setGoodGrade(double goodGrade) {
		this.goodGrade = goodGrade;
	}
	public List<Sku> getSkus() {
		return skus;
	}
	
	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}
//	/*
//	 * 设置SKU元组的时候加载skuPropInfo
//	 */
//	public void setSkus(List<Sku> skus) {
//		this.skus = skus;
//		
//		Map<String, List<String>> propInfo = createPropInfo(skus.get(0));
//		
//		String PropertiesName = null;
//		String[] props = null;
//		String[] propItem = null;
//		String itemValue = null;
//		for (int i = 0; i < skus.size(); i++) {
//			PropertiesName = skus.get(i).getPropertiesName();
//			props = PropertiesName.split(";");
//			
//			for (int j = 0; j < props.length; j++) {
//				propItem = props[j].split(":");
//				
//				itemValue = propItem[3]+","+propItem[0]+":"+propItem[1];
//				if(!propInfo.get(propItem[2]).contains(itemValue)){
//					propInfo.get(propItem[2]).add(itemValue);
//				}
//			}
//		}
//		
//		this.skuPropInfo = propInfo;
//		
//	}
//	//根据SKU propertiesName生成skuPropInfo数据结构
//	private Map<String, List<String>> createPropInfo(Sku sku){
//		Map<String, List<String>> propInfo = new HashMap<>(); 
//		
//		String[] temp = sku.getPropertiesName().split(";");
//		String[] itemTemp;
//		for (int i = 0; i < temp.length; i++) {
//			itemTemp = temp[i].split(":");
//			propInfo.put(itemTemp[2], new ArrayList<String>());
//		}
//		
//		return propInfo;
//		
//	}
	

	public List<GoodsAttrBean> getBaseAttrs() {
		return baseAttrs;
	}
	
	public void setBaseAttrs(List<GoodsAttrBean> baseAttrs) {
		this.baseAttrs = baseAttrs;
	}

	public SkuViewInfo getSkuViewInfo() {
		return skuViewInfo;
	}
	
	public void setSkuViewInfo(SkuViewInfo skuViewInfo) {
		this.skuViewInfo = skuViewInfo;
	}
	

}
