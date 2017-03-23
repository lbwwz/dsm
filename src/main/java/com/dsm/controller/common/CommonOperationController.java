package com.dsm.controller.common;

import com.dsm.model.product.CategoryBean;
import com.dsm.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/7
 *
 * @author : Lbwwz
 *
 * 一些模块共用的相关操作
 */
@Controller
@RequestMapping("operation")
public class CommonOperationController extends BaseController{

    @Autowired
    private ICategoryService categoryService;

    /**
     *
     * 根据根据根目录获取相应的子级目录的信息
     *
     * @param catId 类目ID
     * @param status 状态码，默认是可用类目，负数表示忽略状态
     * @return
     */
    @ResponseBody
    @RequestMapping("/getChildCat")
    public List<CategoryBean> getChildCat(Integer catId,
                                          @RequestParam(defaultValue = "1") Integer status) {
        return categoryService.getChildCategory(catId, status);
    }

}
