package com.dsm.controller;

import com.alibaba.fastjson.JSONObject;
import com.dsm.controller.common.BaseController;
import com.dsm.model.product.ProductDetail;
import com.dsm.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/24
 *
 * @author : Lbwwz
 */
@Controller
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    /**
     * 商品详情
     * @param m
     * @param id 商品ID
     * @return
     */
    @RequestMapping("item.html")
    public String getProductDetailPage(Map<String, Object> m,Integer id){

        ProductDetail detail = productService.getProductDetail(id);

        m.put("productDetail",detail);
        m.put("skuList", JSONObject.toJSONString(detail.getSkuList()));

        return "/productDetail";
    }




}
