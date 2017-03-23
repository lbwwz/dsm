package com.dsm.model.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * sku商品的销售属性的封装的信息
 *
 * @author lbwwz
 */
public class SkuViewInfo {

    public List<SkuSaleAttr> skuInfo;

    public SkuViewInfo(List<GoodsAttrBean> goodsAttrBean) {
        this.skuInfo = new ArrayList<>();
        //对查询的属性和属性值按照字段描述的序列号进行排序
        Collections.sort(goodsAttrBean);

        String valueSetItem;
        for (GoodsAttrBean bean : goodsAttrBean) {
            //"中文属性值,attrId:valueId"字符串
            StringBuilder builder = new StringBuilder();
            valueSetItem = builder.append(bean.getValueName()).append(",").append(bean.getAttrId())
                    .append(":").append(bean.getValueId()).toString();

            //为相应的SkuPropItem对象中添加valueSet属性
            addToValueSet(bean.getAttrName(), valueSetItem);
        }

    }

    /**
     * 向skuInfo集合中属性名为name的SkuPropItem对象里添加valueSet属性内容
     *
     * @param name
     * @return
     */
    private void addToValueSet(String name, String valueSetItem) {
        if (skuInfo != null) {
            for (SkuSaleAttr item : skuInfo) {
                if (name.equals(item.getZhName())) {
                    item.addValueItem(valueSetItem);
                    return;
                }
            }
        }

        //循环结束未找到，则创建一个SkuPropItem并将其添加到skuInfo中
        skuInfo.add(new SkuSaleAttr(name, valueSetItem));

    }

    public List<SkuSaleAttr> getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(List<SkuSaleAttr> skuInfo) {
        this.skuInfo = skuInfo;
    }
}
