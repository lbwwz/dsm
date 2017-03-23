package com.dsm.model.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 某个商品的的SKU属性
 */
public class Sku {

    //该条SKU的id
    private int skuId;

    //该条sku所属的商品的id
    private int goodId;

    // sku的销售属性的ID组合字符串（颜色，大小，等等，可通过类目API获取某类目下的销售属性）,格式是p1:v1;p2:v2(对应数据库中的数据ID)
    // 用于反映一项sku确定的商品的具体信息
    private String properties;

    //表示SKU销售属性ID和对应中文名
    private String propertiesName;

    //这个sku商品的具体数量
    private int quantity;

    //该sku商品的具体价格
    private double skuPrice;

    //商家设置的产品编码
    private String shopSn;

    public Sku() {
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getPropertiesName() {
        return propertiesName;
    }


    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(double skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getShopSn() {
        return shopSn;
    }

    public void setShopSn(String shopSn) {
        this.shopSn = shopSn;
    }


    /*
	 * 获取该SKU对相应的组合ID的List
	 *	如：
	 *	[1234:1231, 2312:1242, 6389:1234]
	 */
    public List<String> getPropItems() {
        List<String> PropItems = new ArrayList<>();

        if (PropItems.addAll(Arrays.asList(properties.split(";")))) {
            return PropItems;
        } else {
            throw new RuntimeException("sku对象的 properties 未传入···");
        }

    }


}
