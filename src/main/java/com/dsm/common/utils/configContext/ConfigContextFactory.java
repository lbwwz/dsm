package com.dsm.common.utils.configContext;

import com.dsm.common.utils.StringHandleUtils;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lbw on 2016/8/11.
 *
 * <p> properties 配置文件 context对象创建和管理的 bean 工厂
 */
public class ConfigContextFactory {

    /**
     * 配置文件在 src 的映射目录
     */
    public static String JAVA_RS_DIR = "src/main/resources/";

    public void setJAVA_RS_DIR(String javaRsDir) {
        JAVA_RS_DIR = javaRsDir;
    }

    /**
     * 编译后的class文件的根路径
     */
    public static final String CLASS_DIR;

    static {
        URL c = PropConfigContext.class.getClassLoader().getResource("");
        if (c != null)
            CLASS_DIR = c.getPath();
        else
            CLASS_DIR = "";
    }


    /**
     * 管理 configContext 对象的 Map
     */
    private final static Map<String, ConfigContext> configContextMap;

    /**
     * 初始化configContextMap
     */
    static {
        configContextMap = new HashMap<>();
    }


    /**
     * 根据传入的 key 从获取工厂对应的 configContext 对象
     *
     * @param configPath 配置文件路径
     * @return configPath 对应的 configContext 对象，不存在则返回空值
     */
    static ConfigContext getConfigContext(String configPath) {
        return configContextMap.get(configPath);

    }

    /**
     * 通过配置文件路径创建{@link PropConfigContext}实例对象
     *
     * @param configPath <i>String</i> 配置文件的路径
     * @return 配置文件参数对应{@link PropConfigContext}实例对象
     * @throws FileNotFoundException
     */
    public static ConfigContext CreateContext(String configPath) throws FileNotFoundException {
        ConfigContext configContext = getConfigContext(configPath);
        if (configContext == null) {
            configContext = newInstance(configPath);
        }
        return configContext;
    }

    /**
     * 根据传入不同配置文件的配置路径生成不同的 ConfigContext 对象
     * 简单工厂模式，根据根据不同的入参生成不同的 ConfigContext对象
     *
     * @param configPath <i>String</i> 配置文件的路径
     * @return {@link ConfigContext} 实例对象
     * @throws FileNotFoundException
     */
    private static ConfigContext newInstance(String configPath) throws FileNotFoundException {

        ConfigContext configContext = null;

        String ext = StringHandleUtils.getFileExt(configPath);
        switch (ext) {
            case "properties":
                configContext = new PropConfigContext(configPath);
                break;
            case "xml":
                break;
            default:
                break;
        }

        configContextMap.put(configPath, configContext);
        return configContext;
    }

}
