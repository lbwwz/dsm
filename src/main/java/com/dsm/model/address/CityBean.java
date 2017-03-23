package com.dsm.model.address;


import java.io.Serializable;

/**
 * 城市信息对应的bean对象
 * @author lbwwz
 */
public class CityBean implements Serializable{
	private static final long serialVersionUID = 3714435944105886977L;
	//城市邮编
	private String zipCode;

	private String cityName;

	//省属邮编
	private String provinceCode;

	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public CityBean() {
		
	}

	@Override
	public String toString() {
		return "CityBean [zipCode=" + zipCode + ", cityName=" + cityName + ", provinceCode="
				+ provinceCode + "]";
	}
	
	
	
}
