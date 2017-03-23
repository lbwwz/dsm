package com.dsm.model.address;

import java.io.Serializable;

/**
 * 用户地址
 * @author lbwwz
 *
 */
public class Location implements Serializable {
	private static final long serialVersionUID = 6835329374298227829L;
	//地址ID
	private int locationId;

	private ProvinceBean province;
	private  CityBean city;
	private  DistrictBean district;

	//详细地址（物流部分专用）
	private String address;

//	//地址的类型（0.收货地址；1.居住地；2.家乡；3.店铺地址）
//	private int type;

	public Location() {}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public ProvinceBean getProvince() {
		return province;
	}

	public void setProvince(ProvinceBean province) {
		this.province = province;
	}

	public CityBean getCity() {
		return city;
	}

	public void setCity(CityBean city) {
		this.city = city;
	}

	public DistrictBean getDistrict() {
		return district;
	}

	public void setDistrict(DistrictBean district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return "Location{" +
				"locationId=" + locationId +
				", province=" + province +
				", city=" + city +
				", district=" + district +
				", address='" + address + '\'' +
				'}';
	}
}
