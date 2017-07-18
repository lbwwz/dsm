package com.dsm.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/7/17
 *
 * @author : Lbwwz
 * <p>
 *     实例工厂，
 *     可以通过查询数据库动态的为不同的角色定义不同的权限
 * </p>
 */
public class FilterChainDefinitionsFactory {

    Map<String ,String > filterChainDefinitionMap = new LinkedHashMap<>();

    /**
     * 实例工厂getFilterChainDefinitionsMap用来为 shiro 的 ShiroFilterFactoryBean 的 filterChainDefinitions属性赋值
     */
    public  Map<String, String> getFilterChainDefinitionsMap(){
        //查询数据库获取权限信息并进行设置
        filterChainDefinitionMap.put("/user**","authc");
        filterChainDefinitionMap.put("/user**","roles[user]");
        return filterChainDefinitionMap;
    }
}
