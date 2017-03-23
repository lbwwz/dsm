package com.dsm.common.utils.configContext;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lbw on 2016/8/25.
 *
 * 抽象类
 */
public abstract class AbstractConfigContext implements ConfigContext {

    /**
     * properties文件的加载路径
     */
    protected String configPath;

    /**
     * 按照键值形式存放配置信息的map
     */
    protected Map<String, String> config = new HashMap<>();

    protected AbstractConfigContext(String configPath) throws FileNotFoundException {
        // 校验文件是否存在
        if(!new File(ConfigContextFactory.CLASS_DIR + configPath).exists()){
            throw new FileNotFoundException(configPath+" 对应文件不存在！");
        }

        this.configPath = configPath;
    }

    /**
     * 文件覆盖
     * -将 src 目录中的配置文件覆写到 target（bin）目录下的同名文件中
     */
    protected void copyFile() {

        InputStream is = null;
        OutputStream os = null;
        try {
            is = new BufferedInputStream(new FileInputStream(ConfigContextFactory.JAVA_RS_DIR + configPath));
            os = new BufferedOutputStream(new FileOutputStream(ConfigContextFactory.CLASS_DIR + configPath));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            System.out.println("文件复制异常");
            e.printStackTrace();
        } finally {
            if (os != null)try {
                os.close();
            } catch (IOException e) {e.printStackTrace();}
            if (is != null)try {
                is.close();
            } catch (IOException e2) {e2.printStackTrace();}

        }
    }
    /**
     * config对象是否重载的判定
     *
     * @param dateModifiedInJava  src 目录路径下 properties文件修改时间
     * @param dateModifiedInClass target（bin）目录路径下 properties文件的修改时间
     */
    protected void reloadInMap(long dateModifiedInJava, long dateModifiedInClass) {

        long dateModified = Long.valueOf(config.get("dateModified"));
        long newDateModified = Math.max(dateModifiedInJava, dateModifiedInClass);
        if (newDateModified != dateModified) {
            //最后改动的是src下的文件
            if (dateModifiedInJava == newDateModified) {
                /*
                 * 如果更新的是 src目录中的文件 将 src 目录中的配置文件拷贝覆盖到 target（bin）目录中
				 */
                copyFile();
            }

			/*
             * 配置信息重新加载的具体操作
			 */
            loadConfig();
        }
    }

    /**
     * 加载配置文件信息到config对象中的具体操作
     *
     //     * @param dateModified 配置文件的修改时间戳
     */
    protected void loadConfig() {
        config.clear();
        config.put("dateModified", new File(ConfigContextFactory.CLASS_DIR + configPath).lastModified() + "");


        loadConfigInfo();
    }


    /**
     * 这里的loadConfigInfo方法要提供给具体的子类进行实现
     * 例如properties文件的读取和加载就要使用读取properties文件的方法，而xml文件的加载就相应使用另外的方法
     */
    protected abstract void loadConfigInfo();

    /**
     * 动态获取配置对象的操作
     *
     * @return 内部键值存放区-config
     */
    private Map<String, String> getConfig() {
        long newDateModifiedInJava = new File(ConfigContextFactory.JAVA_RS_DIR + configPath).lastModified();
        long newDateModifiedInClass = new File(ConfigContextFactory.CLASS_DIR + configPath).lastModified();

		/*
         * 检测文件配置文件是否被重新修改，若修改则重新加载配置文件信息到 config的 map 对象中
		 */
        reloadInMap(newDateModifiedInJava, newDateModifiedInClass);
        return config;
    }

    /**
     * 通过 config 对象的键值获取对应的value值
     *
     * @param key 对应的键值
     * @return 通过 key 查询获得的 value
     */
    public String getConfigVal(String key) {
        return getConfig().get(key);
    }
}
