package com.dsm.model.order;

import com.dsm.model.product.ProductSkuItem;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/17
 *
 * @author : Lbwwz
 */
public class OrderSkuItem extends ProductSkuItem{


    private static final long serialVersionUID = 1788969762956664341L;
    //结算数量
    public Integer itemNum;

    //库存是否充足
    public boolean isEnough = true;

    //优惠信息（待定）


    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public boolean getIsEnough() {
        return isEnough;
    }

    public void setIsEnough(boolean enough) {
        isEnough = enough;
    }

    @Override
    public String toString() {
        return "OrderSkuItem{" +
                "skuId=" + skuId +
                ", propertiesName='" + propertiesName + '\'' +
                ", quantity=" + quantity +
                ", skuPrice=" + skuPrice +
                ", shopSn='" + shopSn + '\'' +
                ", productId=" + productId +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", catId=" + catId +
                ", brandId=" + brandId +
                ", productName='" + productName + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", itemNum=" + itemNum +
                ", isEnough=" + isEnough +
                '}';
    }
}
