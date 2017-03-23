package com.dsm.common;

import com.dsm.common.utils.configContext.ConfigContext;
import com.dsm.common.utils.configContext.ConfigContextFactory;
import com.dsm.common.exception.NullUploadParamException;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lbw on 2016/8/9.
 *
 * 关于文件上传的配置文件信息 操作类
 */
public class UploadConfigContext {

    //文件上传资源文件的config 对象
    private static ConfigContext uploadSettings;

    static {
        try {
            uploadSettings = ConfigContextFactory.CreateContext("properties/fileUpload.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取键值为 key 的 value 值
     *
     * @param key properties文件中的键值
     * @return 简直对应的value
     */
    public static String get(String key) {
        return uploadSettings.getConfigVal(key);
    }


    /**
     * 获取该类型文件的限制大小
     *
     * @param contentType 文件的类型，类似image/jpg；
     * @return 上传的限制大小
     */
    public static int getMaxSize(String contentType) {
        String type = contentType.split("/")[0];

        String limitSize = get("upload.maxSize." + type);
        int maxSize;
        /**
         * 设置各类型文件的默认上传大小
         */
        if(limitSize == null){
            switch (type) {
                case "image":
                    maxSize = 5 * 1024 * 1024;
                    break;
                case "text":
                    maxSize = 5 * 1024 * 1024;
                    break;
                case "application":
                    maxSize = 25 * 1024 * 1024;
                    break;
                case "video":
                    maxSize = 100 * 1024 * 1024;
                    break;
                case "audio":
                    maxSize = 20 * 1024 * 1024;
                    break;
                default:
                    //其他未知文件的最大上传大小
                    maxSize = 10 * 1024 * 1024;
                    break;
            }
        } else {
            maxSize = Integer.parseInt(limitSize);
        }

        return maxSize;
    }


    /**
     * 获取指定类型的文件限制后缀名列表
     *
     * @param contentType 文件的类型，类似image/jpg
     * @return 该文件类型(image)对应可以使用的后缀名，如jepg，png
     */
    public static List<String> getAllowExts(String contentType) {
        String type = contentType.split("/")[0];
        String allowExits = get("upload.file." + type);
        if (allowExits == null) {
//            throw new NullUploadParamException("类型：" + contentType + "文件限制类型未配置");
            //使用默认的可用拓展名
            allowExits = "jpg,jepg,png,gif,txt,doc,docx";
        }else{
            get("upload."+type+".exits");
        }
        //默认设置为小写拓展名
        return Arrays.asList(allowExits.toLowerCase().split(","));
    }

    /**
     * 获取图片域地址的根路径
     *
     * @return 图片在disk保存地址的根路径
     */
    public static String getFileDiskZone() {
        String fileDiskZone = get("upload.file.diskZone");
        if (fileDiskZone == null) {
            throw new NullUploadParamException("文件库对应本地跟地址未配置");
        }
        return fileDiskZone;
    }

    /**
     * 获取图片对应web站点的根路径
     * @return DiskZone文件地址在web站点下的路径
     */
    public static String getFileWebZone() {
        String fileWebZone = get("upload.file.webZone");
        if (fileWebZone == null) {
            throw new NullUploadParamException("文件库对应的web站点的根地址未配置");
        }
        return fileWebZone;
    }
}