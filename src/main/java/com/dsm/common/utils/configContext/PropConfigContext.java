package com.dsm.common.utils.configContext;

import java.io.*;
import java.util.*;

/**
 * Created by Lbw on 2016/8/11.
 * <p>
 * properties的文件动态的信息类
 */
public class PropConfigContext extends AbstractConfigContext{

    /**
     * 构造方法
     *
     * @param configPath 传入properties文件的加载路径
     */
    PropConfigContext(String configPath) throws FileNotFoundException {
        super(configPath);

        if(new File(ConfigContextFactory.CLASS_DIR + configPath).exists()){
            //加载配置文件信息到config Map
            loadConfig();
        }else{
            throw new FileNotFoundException("该路径对应的配置文件："+configPath+"不存在！");
        }
    }


    /**
     * 加载配置文件信息到config对象中的具体操作件的修改时间戳
     */
    @Override
    public void loadConfigInfo() {
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream(ConfigContextFactory.CLASS_DIR + configPath));
        } catch (IOException e) {
            System.out.println("文件路径加载失败！");
            e.printStackTrace();
        }

        Enumeration<Object> keys = prop.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            config.put(key, prop.getProperty(key));
        }
    }
}
