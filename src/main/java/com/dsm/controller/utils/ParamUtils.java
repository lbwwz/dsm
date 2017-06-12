package com.dsm.controller.utils;

import com.dsm.model.product.BaseAttrBean;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/6/3
 *
 * @author : Lbwwz
 */
public class ParamUtils {

    /**
     * 将属性筛选的条件参数转化为 DO 对象
     * @param ev 属性筛选的条件参数
     * @return
     */
    public static List<BaseAttrBean> formatAttrSelectParamToBean(String ev){
        if(StringUtils.isBlank(ev)){
            return  null;
        }
        List<BaseAttrBean> beans = new ArrayList<>();
        String[] temp;
        try{
            for(String item : ev.split("@")){
                temp = item.split("_");
                if(temp.length != 2){
                    throw new IllegalArgumentException ();
                }else{
                    beans.add(new BaseAttrBean(Integer.parseInt(temp[0]),Integer.parseInt(temp[1])));
                }
            }
        }catch (Exception ex){
            throw new IllegalArgumentException ("非法的参数ev:"+ev);
        }

        return beans;
    }
}
