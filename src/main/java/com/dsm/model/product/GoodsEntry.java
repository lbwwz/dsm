package com.dsm.model.product;

/**
 * 使用查询得出的商品项目
 * 包含了一些商品的必要信息
 * （用于查询结果页中单个的结果商品项）
 * @author lbwwz
 *
 */

public class GoodsEntry {

	//商品Id
	private Integer goodId;
	//商品标题
	private String goodTitle;
	//商品价格（用于按照价格排序）
	private double goodPrice;
	//商品点击量（用于按照人气项排序）
	private Integer clickCount;

	//商品所属的店铺ID
	private Integer shopId;
	//商品所属的店铺名
	private Integer shopName;

	//商品所属的店铺发货地址id（用于按照发货地址筛选）
	private Integer shopProvince;
	//商品所属的店铺发货地址字符串
	private Integer shopProvinceName;
	//店铺信誉（用于信誉排序）
	private double shopScore;


}
