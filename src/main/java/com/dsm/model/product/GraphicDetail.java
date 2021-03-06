package com.dsm.model.product;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/13
 *
 * @author : Lbwwz
 *         <p/>
 *         商品图文详情
 */
public class GraphicDetail implements Serializable{

    private static final long serialVersionUID = 2879399477645150428L;
    private Long detailId;
    private Long productId;
    private String detailText;
    private Timestamp updateTime;
    private Timestamp createTime;

    public GraphicDetail(){

    }

    public GraphicDetail(Long productId,String detailText){
        this.productId = productId;
        this.detailText = detailText;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "GraphicDetail{" +
                "detailId=" + detailId +
                ", productId=" + productId +
                ", detailText='" + detailText + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
