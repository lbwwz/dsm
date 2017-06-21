package com.dsm.controller;

import com.dsm.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lbwwz on 2016/8/1.
 */

/**
 * 首页一些交互操作的controller
 */
@Controller
public class HomeController extends BaseController {


    /**
     * 请求首页
     * @return 首页视图
     */
    @RequestMapping("/")
    public String index(){
        //返回跳转到首页
        return "/index";
    }




}
