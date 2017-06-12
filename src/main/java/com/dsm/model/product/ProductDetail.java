package com.dsm.model.product;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/9
 *
 * @author : Lbwwz
 * <p/>
 * 商品详情的信息封装
 */
public class ProductDetail extends ProductBean implements Serializable{

    private static final long serialVersionUID = -4661744011492672537L;
    //商品图文详情
    private String detailText;
    //商品图片
    private List<ProductImageItem> productImages;
    //基本属性
    private List<ProductDetailAttrInfo> baseAttrsInfo;
    //自定义属性
    private List<ProductDetailAttrInfo> customerAttrsInfo;
    //商品sku
    private List<Sku> skuList;
    //商品销售属性
    private List<ProductDetailAttrInfo> saleAttrInfo;

    //商品的销售属性集合

    public ProductDetail() {
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public List<ProductImageItem> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImageItem> productImages) {
        this.productImages = productImages;
    }


    public List<ProductDetailAttrInfo> getBaseAttrsInfo() {
        return baseAttrsInfo;
    }

    public void setBaseAttrsInfo(List<ProductDetailAttrInfo> baseAttrsInfo) {
        this.baseAttrsInfo = baseAttrsInfo;
    }

    public List<ProductDetailAttrInfo> getCustomerAttrsInfo() {
        return customerAttrsInfo;
    }

    public void setCustomerAttrsInfo(List<ProductDetailAttrInfo> customerAttrsInfo) {
        this.customerAttrsInfo = customerAttrsInfo;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }

    public List<ProductDetailAttrInfo> getSaleAttrInfo() {
        return saleAttrInfo;
    }

    public void setSaleAttrInfo(List<ProductDetailAttrInfo> saleAttrInfo) {
        this.saleAttrInfo = saleAttrInfo;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "productId=" + productId +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", catId=" + catId +
                ", brandId=" + brandId +
                ", productName='" + productName + '\'' +
                ", productSn='" + productSn + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", detailText='" + detailText + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", keywords='" + keywords + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", isBest=" + isBest +
                ", isNew=" + isNew +
                ", isHot=" + isHot +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", productImages=" + productImages +
                ", baseAttrsInfo=" + baseAttrsInfo +
                ", customerAttrsInfo=" + customerAttrsInfo +
                ", skuList=" + skuList +
                '}';
    }
}
