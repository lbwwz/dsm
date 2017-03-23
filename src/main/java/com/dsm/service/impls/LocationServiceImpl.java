package com.dsm.service.impls;

import com.dsm.dao.ILocationDao;
import com.dsm.dao.IShippingAddressDao;
import com.dsm.model.address.*;
import com.dsm.model.formData.ShippingAddressDTO;
import com.dsm.service.interfaces.ILocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * 与地址相关的service操作类
 *
 * @author : Lbwwz
 */

@Service("ILocationService")
public class LocationServiceImpl implements ILocationService {

    @Resource
    private ILocationDao locationDao;

    @Resource
    private IShippingAddressDao shippingAddressDao;


    @Override
    public void addLocationInfo(Location location) {

    }

    @Override
    public void updateLocationInfo(Location location) {

    }

    @Override
    public Location getLocationInfo(int locationId) {
        return locationDao.getLocation(locationId);
    }

    @Override
    public List<ProvinceBean> getProvinces() {
        return locationDao.getProvinces();
    }

    @Override
    public List<CityBean> getCities(String provinceCode) {
        return locationDao.getCities(provinceCode);
    }

    @Override
    public List<DistrictBean> getDistricts(String cityCode) {
        return locationDao.getDistricts(cityCode);
    }

    @Override
    public ProvinceBean getProvince(String zipCode) {
        return null;
    }

    @Override
    public ProvinceBean getCity(String zipCode) {
        return null;
    }

    @Override
    public ProvinceBean getDistrict(String zipCode) {
        return null;
    }


}
