package com.dsm.model.product;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/3/1
 *
 * @author : Lbwwz
 *         <p>
 *         图片信息的封装类
 */
public class ProductImageItem implements Serializable{
    private static final long serialVersionUID = -1908995726273257700L;
    private Long imgId;
    private Long productId;
    private String imgUrl;
    private int isMain;
    private Integer sort;
    private Integer status;
    private Timestamp createTime;

    public ProductImageItem() {
    }

    public ProductImageItem(Long productId,String imgUrl,int isMain) {
        this.productId = productId;
        this.imgUrl = imgUrl;
        this.isMain = isMain;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(long imgId) {
        this.imgId = imgId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getIsMain() {
        return isMain;
    }

    public void setIsMain(int isMain) {
        this.isMain = isMain;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProductImageItem{" +
                "imgId=" + imgId +
                ", productId=" + productId +
                ", imgUrl='" + imgUrl + '\'' +
                ", isMain=" + isMain +
                ", sort=" + sort +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
