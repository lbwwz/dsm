package com.dsm.model.address;

import java.io.Serializable;

/**
 * 用户的收发货地址
 *
 * @author lbwwz
 */
public class ShippingAddress implements Serializable{

    private static final long serialVersionUID = -6606045937473297873L;
    //收发货地址ID
    private int addressId;
    private int userId;

    //地址库中具体的地址id
    private int locationId;

    private Location location;

    //邮政编码
    private String zipCode;

    //收发货人姓名
    private String realName;
    //收发货人电话
    private String mobilePhone;
    //是否是默认地址：1是，0不是
    private int isDefault;

    public ShippingAddress() {
    }

    //
    public ShippingAddress(int addressId, String zipCode, String realName, String mobilePhone) {
        super();
        this.addressId = addressId;
        this.zipCode = zipCode;
        this.realName = realName;
        this.mobilePhone = mobilePhone;
    }

    //对应物流地址表的bean封装
    public ShippingAddress(int userId, int locationId, String zipCode, String realName, String mobilePhone) {
        super();
        this.userId = userId;
        this.locationId = locationId;
        this.zipCode = zipCode;
        this.realName = realName;
        this.mobilePhone = mobilePhone;
    }


    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "addressId=" + addressId +
                ", userId=" + userId +
                ", locationId=" + locationId +
                ", location=" + location +
                ", zipCode='" + zipCode + '\'' +
                ", realName='" + realName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}