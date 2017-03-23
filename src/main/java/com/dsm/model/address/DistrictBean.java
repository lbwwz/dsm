package com.dsm.model.address;

import java.io.Serializable;

public class DistrictBean implements Serializable{
	private static final long serialVersionUID = -5410822708823282798L;
	// 区域邮编
	private String zipCode;

	private String districtName;

	// 区域对应的上级城市编码
	private String cityCode;
	
	
	public DistrictBean() {
		
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}



	@Override
	public String toString() {
		return "DistrictBean [zipCode=" + zipCode + ", districtName=" + districtName + ", cityCode=" + cityCode
				+ "]";
	}
	

}
