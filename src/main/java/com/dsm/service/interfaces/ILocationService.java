package com.dsm.service.interfaces;



import com.dsm.model.address.CityBean;
import com.dsm.model.address.DistrictBean;
import com.dsm.model.address.Location;
import com.dsm.model.address.ProvinceBean;

import java.util.List;

/**
 * 有关地址管理的service
 *
 * Created by Lbwwz on 2016/8/8.
 */
public interface ILocationService {

    /*========地址库的相关操作========*/
    /**
     * 添加地址信息
     * @param location
     */
    void addLocationInfo(Location location);

    /**
     * 更新地址信息
     * @param location
     */
    void updateLocationInfo(Location location);

    /**
     * 根据locationId查询相应的地址信息
     * @param locationId
     * @return
     */
    Location getLocationInfo(int locationId);

    /*========省市县表信息获取操作========*/
    /**
     * 查询省份列表信息
     * @return 所有的省份信息封装
     */
    List<ProvinceBean> getProvinces();

    /**
     * 根据省份编号查询省份下面的所有城市列表信息
     * @param provinceCode 省份编号
     * @return 省份对应下的所有的城市信息列表
     */
    List<CityBean> getCities(String provinceCode);

    /**
     * 根据城市编号查询城市下面的所有区域列表信息
     * @param cityCode 城市编号
     * @return 城市对应下的所有区域信息列表
     */
    List<DistrictBean> getDistricts(String cityCode);


    /**
     * 根据省份编码查询省份信息
     * @param zipCode
     * @return 省份信息对象
     */
    ProvinceBean getProvince(String zipCode);

    /**
     * 根据城市编码查询城市信息
     * @param zipCode
     * @return 城市信息对象
     */
    ProvinceBean getCity(String zipCode);

    /**
     * 根据区域编码查询区域信息
     * @param zipCode
     * @return 区域信息对象
     */
    ProvinceBean getDistrict(String zipCode);




}
