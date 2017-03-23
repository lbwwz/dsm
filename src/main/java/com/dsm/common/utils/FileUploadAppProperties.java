package com.dsm.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂类，用于读取配置文件上传的properties文件的信息
 *
 * @author lbwwz
 */
public class FileUploadAppProperties {

    private Map<String, String> properties = new HashMap<>();

    private static FileUploadAppProperties factory = new FileUploadAppProperties();

    private FileUploadAppProperties() {

    }

    public static FileUploadAppProperties getInstance() {
        return factory;
    }


    /**
     * 为该类的properties属性赋初值（在servletContextListener监听器初始化的时候被自动调用）
     *
     * @param key
     * @param value
     */
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    /**
     * 获取配置文件的信息
     *
     * @param key 配置文件的键
     * @return 配置文件的值
     */
    public String getProperty(String key) {
        return properties.get(key);
    }


}
