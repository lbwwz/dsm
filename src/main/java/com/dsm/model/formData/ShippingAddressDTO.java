package com.dsm.model.formData;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * 物流地址保存的数据传输对象
 *
 * @author : Lbwwz
 */
public class ShippingAddressDTO implements Serializable{
    private static final long serialVersionUID = -4438039936880523693L;
    //收发货地址ID
    private Integer addressId;
    private Integer userId;

    private Integer locationId;

    private String consigneeProvince;

    private String consigneeCity;

    private String consigneeDistrict;

    //地址详情
    private String address;
    //邮政编码
    private String zipCode;
    //收发货人姓名
    private String consignee;
    //收发货人电话
    private String mobile;
    //是否默认地址
    private Integer isDefault;

    private int type;

    public ShippingAddressDTO() {
    }

    public ShippingAddressDTO(Integer addressId, Integer userId) {
        this.addressId = addressId;
        this.userId = userId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getConsigneeProvince() {
        return consigneeProvince;
    }

    public void setConsigneeProvince(String consigneeProvince) {
        this.consigneeProvince = consigneeProvince;
    }

    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity;
    }

    public String getConsigneeDistrict() {
        return consigneeDistrict;
    }

    public void setConsigneeDistrict(String consigneeDistrict) {
        this.consigneeDistrict = consigneeDistrict;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
