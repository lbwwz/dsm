package com.dsm.model.product;

import com.dsm.common.utils.StringHandleUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 某个商品的的SKU属性
 */
public class Sku {

    //该条SKU的id
    private int skuId;

    //该条sku所属的商品的id
    private int productId;

    // sku的销售属性的ID组合字符串（颜色，大小，等等，可通过类目API获取某类目下的销售属性）,格式是p1:v1;p2:v2(对应数据库中的数据ID)
    // 用于反映一项sku确定的商品的具体信息
    private String properties;

    private Map<String,String> jsonProperties;

    //表示SKU销售属性ID和对应中文名
    private String propertiesName;

    //这个sku商品的具体数量
    private int quantity;

    //该sku商品的具体价格
    private BigDecimal skuPrice;

    //商家设置的产品编码
    private String shopSn;

    public Sku() {
    }

    public Sku(int productId, String propertiesName, int quantity, BigDecimal skuPrice, String shopSn) {
        this.productId = productId;
        this.propertiesName = propertiesName;
        this.quantity = quantity;
        this.skuPrice = skuPrice;
        this.shopSn = shopSn;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getShopSn() {
        return shopSn;
    }

    public void setShopSn(String shopSn) {
        this.shopSn = shopSn;
    }

    public Map<String, String> getJsonProperties() {
        return StringHandleUtils.formatPropertiesToMap(properties);
    }

    public void setJsonProperties(Map<String, String> jsonProperties) {
        this.jsonProperties = jsonProperties;
    }



    @Override
    public String toString() {
        return "Sku{" +
                "skuId=" + skuId +
                ", productId=" + productId +
                ", properties='" + properties + '\'' +
                ", propertiesName='" + propertiesName + '\'' +
                ", quantity=" + quantity +
                ", skuPrice=" + skuPrice +
                ", shopSn='" + shopSn + '\'' +
                '}';
    }


}
