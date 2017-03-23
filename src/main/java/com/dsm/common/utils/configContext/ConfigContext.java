package com.dsm.common.utils.configContext;

/**
 * Created by Lbw on 2016/8/25.
 *
 * 配置文件信息类的接口，
 */
public interface ConfigContext {


    /**
     * 通过 config 对象的键值获取对应的value值
     *
     * @param key 对应的键值
     * @return 通过 key 查询获得的 value
     */
    String getConfigVal(String key);

}
