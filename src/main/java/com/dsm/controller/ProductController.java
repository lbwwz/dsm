package com.dsm.controller;

import com.alibaba.fastjson.JSONObject;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.model.BackMsg;
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
public class ProductController {

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

    @RequestMapping("addToCart")
    public BackMsg<String> addToCart(){

        //检查用户登录
        if(SessionToolUtils.checkLogin()==1){
            //调用api将商品信息添加到购物车中
        }else{

        }

        return null;
    }


}
