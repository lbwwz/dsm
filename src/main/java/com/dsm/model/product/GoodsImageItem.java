package com.dsm.model.product;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/3/1
 *
 * @author : Lbwwz
 *         <p>
 *         图片信息的封装类
 */
public class GoodsImageItem implements Serializable{
    private static final long serialVersionUID = -816821495265548426L;
    //图片id
    private Integer imgId;
    //商品id
    private Integer goodsId;
    //图片链接
    private String imgUrl;
    //图片描述
    private String imgDesc;
    //是否主图
    private Integer isMain;
    //图片类型
    private Integer type;
    //图片状态
    private Integer status;
    //排序
    private Integer imgSort;

    public GoodsImageItem(){}

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getImgSort() {
        return imgSort;
    }

    public void setImgSort(Integer imgSort) {
        this.imgSort = imgSort;
    }
}
