package com.dsm.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.dsm.controller.common.BaseController;
import com.dsm.model.BackMsg;
import com.dsm.model.product.AttrValueBean;
import com.dsm.model.product.CategoryBean;
import com.dsm.model.product.ProductAttrBean;
import com.dsm.service.interfaces.ICategoryService;
import com.dsm.service.interfaces.IProductAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class AdminManage
 */
@Controller
@RequestMapping("admin")
public class AdminManage extends BaseController {


    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductAttrService productAttrService;

    /**
     * 管理员页首页
     */
    @RequestMapping("")
    public String showIndex() {
        //返回跳转到首页
        return "/admin/adminIndex";
    }

    @RequestMapping("/manageCategory")
    public String manageCategory(Map<String, Object> m) {
        m.put("rootCat", categoryService.getRootCategoryIgnoreStatus());
        return "/admin/catManage";
    }


    @ResponseBody
    @RequestMapping("/addCategory")
    public List<CategoryBean> addCategory(String[] catNames, Integer status, Integer showInNav, Integer parentId) {
        List<CategoryBean> catList = new ArrayList<>();
        for (String catName : catNames) {
            catList.add(new CategoryBean(catName, status, showInNav, parentId));
        }
        if (categoryService.addCategoryList(catList)) {
            return catList;
        }
        return null;
    }


    /**
     * 更新类目设置
     *
     * @param bean 更新的类目信息封装
     */
    @ResponseBody
    @RequestMapping("/updateCatItem")
    public BackMsg updateCatItem(CategoryBean bean) {
        CategoryBean categoryBean = categoryService.updateCategory(bean);
        if (categoryBean != null) {
            return new BackMsg(0, JSONObject.toJSONString(categoryBean), "更新成功！");
        }
        return new BackMsg(1, "", "更新失败!");
    }

    /**
     * 变更类目的可用状态
     *
     * @param catId 要修改的类目ID
     */
    @ResponseBody
    @RequestMapping("/changeCatStatus")
    public BackMsg changeCatStatus(Integer catId) {

        if (categoryService.changeStatus(catId)) {
            return new BackMsg(0, "", "修改成功！");
        }
        return new BackMsg(1, "", "修改失败!");
    }


    /**
     * 加载属性管理页面
     */
    @RequestMapping("/manageProductAttr")
    public String manageProductAttr(Map<String, Object> m) {
        m.put("rootCat", categoryService.getRootCategory());
        return "/admin/attrManage";
    }

    /**
     * 管理员属性管理页根据类目ID加载属性信息
     */
    @ResponseBody
    @RequestMapping("/getAttrInfo")
    public List<ProductAttrBean> getAttrInfo(Integer catId) {
        return productAttrService.getAttrNameByCat(catId, -1);
    }

    /**
     * 添加属性的属性提交事件
     */
    @ResponseBody
    @RequestMapping("/addAttrInfo")
    public List<ProductAttrBean> addAttrInfo(Integer catId, Integer isSale, Integer isKey, Integer isMust, String[] attrsName) {

        return productAttrService.addAttrInfo(catId, isSale, isKey, isMust, attrsName);
    }


    /**
     * 修改属性的信息
     */
    @ResponseBody
    @RequestMapping("/updateAttrItem")
    public BackMsg updateAttrItem(ProductAttrBean attrBean) {

        ProductAttrBean bean = productAttrService.updateAttrInfo(attrBean);
        if (bean != null) {
            return new BackMsg(0, JSONObject.toJSONString(bean), "修改成功!");
        }
        return new BackMsg(1, "", "修改失败!");
    }


    /**
     * 变更商品属性的状态
     *
     * @param attrId 属性ID
     */
    @ResponseBody
    @RequestMapping("/changeAttrStatus")
    public BackMsg changeAttrStatus(Integer attrId) {

        if (productAttrService.changeAttrStatus(attrId)) {
            return new BackMsg(0, "", "修改成功！");
        }
        return new BackMsg(1, "", "修改失败!");
    }


    /**
     * 管理员属性管理页根据商品属性ID加载该属性的属性值
     */
    @ResponseBody
    @RequestMapping("/getAttrValues")
    public List<AttrValueBean> getAttrValues(Integer attrId) {

        return productAttrService.getAttrValues(attrId, -1);

    }

    /**
     * 为具体的某项属性添加属性值
     */
    @ResponseBody
    @RequestMapping("addAttrValues")
    private BackMsg addAttrValues(int attrId, String[] attrValues, Integer status) {

        if (attrId <= 0) {
            return new BackMsg(1, "", "数据异常，请刷新重试！");
        }
        return new BackMsg(0, JSONObject.toJSONString(productAttrService.addAttrValueInfo(attrId, status, attrValues)), "添加成功！");
    }


    /**
     * 为具体的某项属性添加属性值
     */
    @ResponseBody
    @RequestMapping("updateAttrValue")
    private BackMsg updateAttrValue(int valueId, String attrValue) {

        if (valueId <= 0) {
            return new BackMsg(1, "", "数据异常，请刷新重试！");
        }
        AttrValueBean bean = productAttrService.updateAttrValue(valueId, attrValue);
        if (bean == null) {
            return new BackMsg(1, "", "操作失败，请稍后重试");
        }
        return new BackMsg(0, JSONObject.toJSONString(bean), "修改成功！");
    }

    /**
     * 变更商品属性值的状态
     */
    @ResponseBody
    @RequestMapping("/changeAttrValueStatus")
    public BackMsg changeAttrValueStatus(Integer valueId) {

        if (productAttrService.changeAttrValueStatus(valueId)) {
            return new BackMsg(0, "", "修改成功！");
        }
        return new BackMsg(1, "", "修改失败!");
    }

    /**
     * 服务商设置页面
     */
    @SuppressWarnings("unused")
    private void showShippingServiceSetter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
