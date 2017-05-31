package com.dsm.controller;

import com.dsm.model.address.CityBean;
import com.dsm.model.address.DistrictBean;
import com.dsm.model.address.ProvinceBean;
import com.dsm.service.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * @author : Lbwwz
 *
 * <p>地址库中的省份，城市，区域请求信息。用来实现表单地址选择的三级联动效果</p>
 */
@Controller
@RequestMapping("region")
public class RegionController {

    @Autowired
    private ILocationService addressService;


    /**
     * 查询所有的省份，并将其封装为provinceBean对象
     */
    @ResponseBody
    @RequestMapping("listProvince")
    public List<ProvinceBean> listProvince(){

        List<ProvinceBean> provinces = addressService.getProvinces();

        return provinces;
    }


    /**
     * 根据选择的省份编号，查询相应的城市信息（cityBean）
     */
    @ResponseBody
    @RequestMapping("listCity")
    public List<CityBean> listCity(String provinceCode) {

        List<CityBean> cities = addressService.getCities(provinceCode);

        return cities;
    }

    /**
     * 根据选择的城市编号，查询相应的地区信息（areaBean）
     */
    @ResponseBody
    @RequestMapping("listDistrict")
    public List<DistrictBean> listDistrict(String cityCode) {

        List<DistrictBean> areas = addressService.getDistricts(cityCode);

        return areas;

    }

}
