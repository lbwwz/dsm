package com.dsm.controller;

import com.dsm.model.product.*;
import com.dsm.service.interfaces.ICategoryService;
import com.dsm.service.interfaces.IProductAttrService;
import com.dsm.service.interfaces.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/24
 *
 * @author : Lbwwz
 */
@Controller
@RequestMapping
public class CategoryController {

    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductAttrService productAttrService;



    @Autowired
    private ICategoryService categoryService;


    /**
     * 分类列表
     * @param catId
     * @param m
     * @return
     */
    @RequestMapping(value="/list.html")
    public String showProductListPage(@RequestParam(value = "cat") Integer catId,String ev,
                                      @RequestParam(value = "sort",defaultValue = "0")Integer sortType,Map<String, Object> m){
        System.out.println(catId);

        //查询类目层级
        List<CategoryBean> catBeans = categoryService.getCatalogNavList(catId);
        m.put("catNavList",catBeans);

        try {
            //获取类目下的关键属性，如果不是子类目显示其叶子类目
            List<ProductAttrBean> attrBeans = productAttrService.getKeyAttrList(catId);
            //选中的属性列信息
            m.put("selectedAttrList",getSelectedAttrList(attrBeans,ev));
            //剩余可选的属性列信息
            m.put("selectAttrList",attrBeans);
            //查询按照综合排序查询默认的商品列表
            m.put("productList",productService.getProductListByCat(catId,0,15,sortType));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        m.put("sortType",sortType);


        return "/productList";
    }

    /**
     *
     * @param attrBeans 当前类目下查询到的所有关键属性
     * @param ev 属性项，以 attrId1_ValueId1%ttrId2_ValueId2 的形式组成
     * @return 选中的基本属性项
     * @throws Exception
     */
    private List<BaseAttrBean> getSelectedAttrList(List<ProductAttrBean> attrBeans, String ev) throws Exception {
        List<BaseAttrBean> list = null;
        if(StringUtils.isNoneBlank(ev)){
            //ev 属性项
            String[] items= ev.split("@");
            String[] temp;
            Iterator<ProductAttrBean> it;
            ProductAttrBean tempBean;
            list = new ArrayList<>(5);
            //校验ev数据的flag
            boolean attrFlag= true,valueFlag = true;
            for (String item : items){
                 it = attrBeans.iterator();
                while(it.hasNext()){
                    temp =item.split("_");
                    if(temp.length!= 2){
                        throw new Exception("传入的参数ev格式不正确！");
                    }
                    tempBean = it.next();
                    if(tempBean.getAttrId() == Integer.parseInt(temp[0])){
                        attrFlag = false;
                        for (AttrValueBean valueBean:tempBean.getAttrValues()){
                            if(valueBean.getValueId() == Integer.parseInt(temp[1])){
                                //添加信息到被选中信息列表中
                                list.add(new BaseAttrBean(tempBean.getAttrId(),tempBean.getAttrName(),valueBean.getValueId(),valueBean.getValueName(),tempBean.getAttrSort()));
                                //删除原有列表信息中该项
                                it.remove();
                                valueFlag = false;
                                break;
                            }
                        }
                        if(valueFlag){
                            //没有找到valueId
                            throw new Exception("传入的参数数据异常，找不到相应的\"属性值Id\"信息。异常项信息为："+item);
                        }else{
                            valueFlag = true;
                        }
                    }
                }
                if(attrFlag){
                    //没有找到attrId
                    throw new Exception("传入的参数数据异常，找不到相应的\"属性Id\"信息。异常项信息为："+item);
                }else{
                    attrFlag = true;
                }
            }
        }
        return list;
    }


    @ResponseBody
    @RequestMapping(value="/searchByAttrInfo",method = RequestMethod.POST)
    public List<ProductBean> searchByAttrInfo(Integer catId,String[] attrConditions){


        System.out.println(catId+"__"+ Arrays.toString(attrConditions));



        return null;
    }





    @ResponseBody
    @RequestMapping("getProductList")
    public List<ProductBean> getProductList(Integer catId, Integer pageIndex, @RequestParam(defaultValue = "0")int sortType){
        return productService.getProductListByCat(catId,pageIndex,20,sortType);
    }
}
