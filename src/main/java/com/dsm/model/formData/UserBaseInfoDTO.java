package com.dsm.model.formData;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * @author : Lbwwz
 *
 * 用户基本信息修改对应的pojo对象
 */
public class UserBaseInfoDTO implements Serializable{

    private static final long serialVersionUID = 4628299305561757242L;
    private int userId;

    private String sex;

    private String qq;

    private String birthday;

    private String headImage;

    //用户家乡信息
    private LocationDTO hometown;
    //用户居住地信息
    private LocationDTO domicile;

    public UserBaseInfoDTO() {}

    //头像信息构造
    public UserBaseInfoDTO(int userId, String headImage) {
        this.userId = userId;
        this.headImage = headImage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public LocationDTO getHometown() {
        return hometown;
    }

    public void setHometown(LocationDTO hometown) {
        this.hometown = hometown;
    }

    public LocationDTO getDomicile() {
        return domicile;
    }

    public void setDomicile(LocationDTO domicile) {
        this.domicile = domicile;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
}