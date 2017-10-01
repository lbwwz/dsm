package com.dsm.model.address;

/**
 * 用户收货地址的提交结果封装
 * 数据库dsm_user_shipping_address表的映射（弃用）
 *
 * @author lbwwz
 */
public class ShippingAddressUpdateItem {

    //收货地址ID
    private int addressId;
    private int userId;

    //地址库中具体的地址id
    private int locationId;

    private String provinceCode;
    private String cityCode;
    private String districtCode;
    private String address;


    //邮政编码
    private String zipcode;
    //收货人姓名
    private String realName;
    //收货人电话
    private String mobile;

    public ShippingAddressUpdateItem() {
    }

    public ShippingAddressUpdateItem(int addressId, int userId, int locationId, String provinceCode, String cityCode,
                                     String districtCode, String address, String zipcode, String realName, String mobile) {
        super();
        this.addressId = addressId;
        this.userId = userId;
        this.locationId = locationId;
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.districtCode = districtCode;
        this.address = address;
        this.zipcode = zipcode;
        this.realName = realName;
        this.mobile = mobile;
    }

    public ShippingAddressUpdateItem(int addressId, int locationId, String provinceCode, String cityCode,
                                     String districtCode, String address, String zipcode, String realName, String mobile) {
        super();
        this.addressId = addressId;
        this.locationId = locationId;
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.districtCode = districtCode;
        this.address = address;
        this.zipcode = zipcode;
        this.realName = realName;
        this.mobile = mobile;
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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "ShippingAddressUpdateItem [addressId=" + addressId + ", userId=" + userId + ", locationId=" + locationId
                + ", provinceCode=" + provinceCode + ", cityCode=" + cityCode + ", districtCode=" + districtCode
                + ", address=" + address + ", zipcode=" + zipcode + ", realName=" + realName + ", mobile=" + mobile
                + "]";
    }


}
