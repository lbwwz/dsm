package com.dsm.model.product;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 产品类的封装（用于卖家商品发布页面的信息封装）
 *
 * @author lbwwz
 *
 *
 */
public class ProductBaseBean implements Serializable{
    private static final long serialVersionUID = -936896536518602212L;
    //产品ID
    protected Integer productId;
    //店铺ID
    protected Integer shopId;
    //店铺名称
    protected String shopName;
    //分类ID
    protected Integer catId;
    //品牌ID
    protected Integer brandId;
    //商品名称
    protected String productName;
    //商品排序，该字段仅用于店铺内显示的商品排序
    protected Integer sort;
    //商品状态 0，草稿；1，上架；2，下架；3，禁售
    protected int status;
    //添加时间
    protected Timestamp createTime;
    //最后更新的时间
    protected Timestamp lastUpdateTime;

    public ProductBaseBean() {}

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }


}
