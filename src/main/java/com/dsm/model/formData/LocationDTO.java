package com.dsm.model.formData;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * @author : Lbwwz
 */
public class LocationDTO implements Serializable{
    private static final long serialVersionUID = -5619019210162869670L;
    //地址ID
    private int locationId;

    //地址编号(对应省、市、县编号)
    private String provinceCode;
    private String cityCode;
    private String districtCode;

    //地址详情信息
    private String address;

    public LocationDTO() {}

    public LocationDTO(int locationId, String provinceCode, String cityCode, String districtCode) {
        this.locationId = locationId;
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.districtCode = districtCode;
    }

    public LocationDTO( String provinceCode, String cityCode, String districtCode, String address) {
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.districtCode = districtCode;
        this.address = address;
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
}
