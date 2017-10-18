package com.dsm.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/11
 *
 * @author : Lbwwz
 */
public class ImageBean implements Serializable {
    private static final long serialVersionUID = -4785679352152746014L;
    private Integer imgId;
    private Long userId;
    private String url;
    private String desc;
    private Long size;
    //图片类型，0：一般图片，1：商品图片，2:详情图片，3：广告图片
    private Integer type;
    private Integer sort;
    private String status;
    private Timestamp createTime;

    public ImageBean() {

    }

    /**
     * 添加图片使用的构造
     *
     * @param url  图片链接
     * @param desc 图片描述
     * @param size 图片大小
     * @param type 图片类型
     */
    public ImageBean(Long userId, String url, String desc, Long size, Integer type) {
        this.userId = userId;
        this.url = url;
        this.desc = desc;
        this.size = size;
        this.type = type;
    }


    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
