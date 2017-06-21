package com.dsm.service.impls;

import com.dsm.common.DsmConcepts;
import com.dsm.common.UploadConfigContext;
import com.dsm.common.utils.EncryptUtils;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.common.utils.StringHandleUtils;
import com.dsm.dao.IImageDao;
import com.dsm.model.BackMsg;
import com.dsm.model.ImageBean;
import com.dsm.service.base.BaseService;
import com.dsm.service.interfaces.IFileUploadService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Lbwwz on 2016/8/9.
 * <p/>
 * 文件上传操作的具体实现
 * 使用future 模式优化图片上传模块
 */
@Service("IFileUploadService")
public class FileUploadServiceImpl extends BaseService implements IFileUploadService {

    private static Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    /**
     * spring 线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Resource
    private IImageDao iImageDao;

    /**
     * 文件上传的实际根路径
     */
    private static final String ROOT_PATH;

    /**
     * 文件的WEB根路径
     */
    private static final String WEB_PATH;

    /**
     * 初始化为静态变量赋值
     */
    static {
        ROOT_PATH = packPath(UploadConfigContext.getFileDiskZone());
        WEB_PATH = "/" + packPath(UploadConfigContext.getFileWebZone());
    }

    @Override
    public BackMsg<String> uploadFile(MultipartFile file, String savePath) {
        return uploadFile(file, savePath, defaultFileNameHandler);
    }


    @Override
    public BackMsg<String> uploadFile(MultipartFile file, String savePath, Function<String, String> fileNameHandler) {
        return uploadFileWithSaveInfo(file, savePath, fileNameHandler, -1);
    }

    @Override
    public BackMsg<String> uploadFileWithSaveInfo(MultipartFile file, String savePath, int saveUploadDataInfoType) {
        return uploadFileWithSaveInfo(file, savePath, defaultFileNameHandler, saveUploadDataInfoType);
    }

    public BackMsg<String> uploadFileWithSaveInfo(MultipartFile file, String savePath, Function<String, String> fileNameHandler, int saveUploadDataInfoType) {
        //非空校验
        if (file.isEmpty()) {
            return new BackMsg<>(1, "", "文件上传中发生异常，请稍后重试");
        }

        savePath = packPath(savePath);
        //文件类型
        String fileType = file.getContentType();
        //文件名
        String fileName = file.getOriginalFilename();

        //检查文件的拓展名是否合法
        if (checkExts(fileType, fileName)) {
            return new BackMsg<>(DsmConcepts.EXT_WARRING, fileName,
                    fileName + " 类型异常，请上传" + UploadConfigContext.getAllowExts(fileType) + "类型的文件");
        } else {
            //校验上传该类型文件的大小
            if (file.getSize() > UploadConfigContext.getMaxSize(fileType)) {
                return new BackMsg<>(DsmConcepts.SIZE_WARRING, fileName,
                        fileName + " 大小超过：" + UploadConfigContext.getMaxSize(fileType) / (1024 * 1024) + "m");
            } else {
                try {
                    //上传文件，这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                    String fileNewName = fileNameHandler.apply(fileName);

                    FileUtils.copyInputStreamToFile(file.getInputStream(),
                            new File(ROOT_PATH + savePath, fileNewName));

                    //保存文件信息
                    return SaveInfoByFileType(savePath + fileNewName, fileType, fileName, file.getSize(), saveUploadDataInfoType);

                } catch (IOException e) {
                    logger.error("文件写入失败：{}\n{}", e, e.getMessage());
                    return new BackMsg<>(1, "", "文件上传中发生异常，请稍后重试");
                }
            }
        }

    }

    @Override
    public BackMsg<String> uploadFile(String base64File, String savePath, Function<String, String> pathHandler) {
        return uploadFileWithSaveInfo(base64File, savePath, pathHandler, -1);
    }

    @Override
    public BackMsg<String> uploadFileWithSaveInfo(String base64File, String savePath, Function<String, String> pathHandler, int saveUploadDataInfoType) {
        if (base64File.split(",").length < 2) {
            return new BackMsg<>(1, null, "数据提交异常！");
        }
        savePath = packPath(savePath);
        String fileType = base64File.split(",")[0].split(":")[1];
        byte[] fileByte = EncryptUtils.decodeBase64(base64File.split(",")[1]);

        //获取文件的输入流
        InputStream is = new ByteArrayInputStream(fileByte);

        //校验上传该类型文件的大小
        try {
            if (is.available() > UploadConfigContext.getMaxSize(fileType)) {
                return new BackMsg<>(DsmConcepts.SIZE_WARRING, "",
                        "大小超过：" + UploadConfigContext.getMaxSize(fileType) / (1024 * 1024) + "m");
            } else {

                try {
                    //上传文件，这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                    String fileNewName = pathHandler.apply(getSessionUser().getId() + "");
                    FileUtils.copyInputStreamToFile(is,
                            new File(ROOT_PATH + savePath, fileNewName));

                    //保存文件信息
                    return SaveInfoByFileType(savePath + fileNewName, fileType, "", is.available(), saveUploadDataInfoType);

                } catch (IOException e) {
                    logger.error("文件写入失败：{}\n{}", e, e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new BackMsg<>(1, "", "文件上传中发生异常，请稍后重试");
    }


    @Override
    public List<BackMsg<String>> uploadFiles(MultipartFile[] files, String savePath) {
        return uploadFiles(files, savePath, defaultFilesNameHandler);
    }

    @Override
    public List<BackMsg<String>> uploadFiles(MultipartFile[] files, String savePath, BiFunction<String, Integer, String> fileNameHandler) {
        return uploadFilesWithSaveInfo(files, savePath, fileNameHandler, -1);
    }

    @Override
    public List<BackMsg<String>> uploadFilesWithSaveInfo(MultipartFile[] files, String savePath, int saveUploadDataInfoType) {
        return uploadFilesWithSaveInfo(files, savePath, defaultFilesNameHandler, saveUploadDataInfoType);
    }


    @Override
    public List<BackMsg<String>> uploadFilesWithSaveInfo(MultipartFile[] files, String savePath,
                                                         BiFunction<String, Integer, String> fileNameHandler, int saveUploadDataInfoType) {
        if (files == null) {
            return null;
        }
        savePath = packPath(savePath);

        //文件类型
        String fileType;
        //文件名
        String fileName;
        //上传保存的新文件名
        String fileNewName;

        List<BackMsg<String>> fileReturnList = new ArrayList<>();
        BackMsg<String> msg;
        for (int i = 0; i < files.length; i++) {
            //非空校验
            if (files[i].isEmpty()) {
                fileReturnList.add(new BackMsg<>(BackMsg.ERROR, "", "文件上传中发生异常，请稍后重试"));
                continue;
            }

            fileType = files[i].getContentType();
            fileName = files[i].getOriginalFilename();
            //检查文件的拓展名是否合法
            if (checkExts(fileType, fileName)) {
                fileReturnList.add(new BackMsg<>(DsmConcepts.EXT_WARRING, fileName,
                        fileName + " 类型异常，请上传" + UploadConfigContext.getAllowExts(fileType) + "类型的文件"));
            } else {
                //校验上传该类型文件的大小
                if (files[i].getSize() > UploadConfigContext.getMaxSize(fileType)) {
                    fileReturnList.add(new BackMsg<>(DsmConcepts.SIZE_WARRING, fileName,
                            fileName + " 大小超过：" + UploadConfigContext.getMaxSize(fileType) / (1024 * 1024) + "m"));
                } else {
                    try {
                        //上传文件，这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                        fileNewName = fileNameHandler.apply(fileName, i);
                        FileUtils.copyInputStreamToFile(files[i].getInputStream(),
                                new File(ROOT_PATH + savePath, fileNewName));

                        msg = SaveInfoByFileType(savePath + fileNewName, fileType, fileName, files[i].getSize(), saveUploadDataInfoType);

                        if (msg != null) {
                            fileReturnList.add(msg);
                        }
                    } catch (IOException e) {
                        logger.error("文件写入失败：{}\n{}", e, e.getMessage());
                    }
                }
            }
        }

        return fileReturnList;
    }

    /**
     * 单一文件文件名处理内部类
     * 默认的文件名处理操作的内部类 -实现函数接口{@link Function}
     */
    final Function<String, String> defaultFileNameHandler = fileName -> {
        String fileExt = StringHandleUtils.getFileExt(fileName);

        return EncryptUtils.encryptMD5(fileName + System.currentTimeMillis()) +
                "_" + new Random().nextInt(10000) + "." + fileExt;

    };

    /**
     * 文件列表文件名处理内部类
     * 默认的文件名处理操作的内部类 -实现函数接口{@link BiFunction}
     */
    final BiFunction<String, Integer, String> defaultFilesNameHandler = (fileName, index) -> {
        String fileExt = StringHandleUtils.getFileExt(fileName);
        return EncryptUtils.encryptMD5(fileName + System.currentTimeMillis()) + "_" +
                new Random().nextInt(10000) + "_" + "index" + "." + fileExt;
    };

    /**
     * 校验某一类型的文件拓展名是否是合法的上传文件
     *
     * @param fileType 文件类型
     * @param fileName 文件名称
     * @return 文件是否合法
     */
    private boolean checkExts(String fileType, String fileName) {
        List<String> extList = UploadConfigContext.getAllowExts(fileType);
        String fileExt = StringHandleUtils.getFileExt(fileName);

        return !extList.contains(fileExt);
    }


    /**
     * 包装 “文件目录” 的路径
     *
     * @param fileDir 文件目录字符串 如：fileDir/innerDir；/fileDir/innerDir/；/fileDir/innerDir
     * @return 修饰后的字符串，格式为: fileDir/innerDir/
     */
    private static String packPath(String fileDir) {
        char firstChar = fileDir.charAt(0);
        if (firstChar == '\\' || firstChar == '/') {
            fileDir = fileDir.substring(1);
        }
        char lastChar = StringHandleUtils.getLastChar(fileDir);
        if (lastChar == '\\' || lastChar == '/') {
            return fileDir;
        }
        return fileDir + "/";

    }

    /**
     * 根据保存的图片类型，将上传文件的信息保存到用户相应的数据库中
     *
     * @param fileUrl                保存路径
     * @param fileType               文件类型
     * @param fileName               文件名
     * @param fileSize               图片大小
     * @param saveUploadDataInfoType 图片类型，0：一般图片，1：商品图片，2：商品详情图，3：广告图；小于0表示不保存图片
     * @return msg信息封装
     */
    private BackMsg<String> SaveInfoByFileType(String fileUrl, String fileType, String fileName, long fileSize, int saveUploadDataInfoType) {
        if (saveUploadDataInfoType < 0) {//不保存图片到用户图片库
            return new BackMsg<>(BackMsg.CORRECT, WEB_PATH + fileUrl, fileName + "上传成功");
        }
        if (SessionToolUtils.checkLogin() == DsmConcepts.IS_USER_LOGIN)  //普通用户
            try {
                //如果是图片，保存当前用户上传图片的信息
                if (fileType.contains("image")) {
                    iImageDao.addImage(new ImageBean(SessionToolUtils.getUser().getId(), WEB_PATH + fileUrl, null, fileSize, saveUploadDataInfoType));
                }
                return new BackMsg<>(BackMsg.CORRECT, WEB_PATH + fileUrl, fileName + "上传成功");
                //其他。。。
            } catch (Exception ex) {
                //发生异常，回滚该上传的文件
                if (!new File(ROOT_PATH + fileUrl).delete()) {
                    logger.warn("\n{}信息录入失败! {}回滚删除异常：\n{}", fileType, ROOT_PATH + fileUrl, ex);
                } else {
                    logger.warn("\n{}信息录入失败! {}回滚删除成功：\n{}", fileType, ROOT_PATH + fileUrl, ex);
                }
                return new BackMsg<>(BackMsg.ERROR, null, fileName + "上传失败");
            }
        else if (SessionToolUtils.checkLogin() == DsmConcepts.IS_ADMIN_LOGIN) {   //  管理员用户

        }
        return null;
    }
}
