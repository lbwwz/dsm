package com.dsm.dao;

import com.dsm.model.address.CityBean;
import com.dsm.model.address.DistrictBean;
import com.dsm.model.address.Location;
import com.dsm.model.address.ProvinceBean;
import com.dsm.model.formData.LocationDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lbwwz on 2016/8/31.
 */
@Repository
public interface ILocationDao {
    /**
     * 添加地址，返回对应的location_id
     *
     * @param location 新的location信息对象
     * @return
     */
    int addLocation(LocationDTO location);

    /**
     * 通过locationId获取对应的地址信息
     *
     * @param locationId 要查询的locationId
     */
    Location getLocation(int locationId);

    /**
     * 更新地址信息
     *
     * @param location 更新之后的location信息封装
     */
    void updateLocation(LocationDTO location);

    /**
     * 查询省份列表信息
     * @return 所有的省份信息封装
     */
    List<ProvinceBean> getProvinces();

    /**
     * 根据省份编号查询省份下面的所有城市信息
     * @param provinceCode 省份编号
     * @return 省份对应下的所有的城市信息列表
     */
    List<CityBean> getCities(String provinceCode);

    /**
     * 根据城市编号查询城市下面的所有区域信息
     * @param cityCode 城市编号
     * @return 城市对应下的所有区域信息列表
     */
    List<DistrictBean> getDistricts(String cityCode);

    /**
     * 删除某条地址（收获地址操作专用）
     *
     * @param locationId 要删除的location对应的locationId
     */
    void deleteLocation(int locationId);

}

