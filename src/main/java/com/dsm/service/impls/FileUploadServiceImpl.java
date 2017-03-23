package com.dsm.service.impls;

import com.dsm.common.DsmConcepts;
import com.dsm.common.UploadConfigContext;
import com.dsm.common.utils.EncryptUtils;
import com.dsm.common.utils.StringHandleUtils;
import com.dsm.model.BackMsg;
import com.dsm.service.base.BaseService;
import com.dsm.service.interfaces.IFileUploadService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Lbwwz on 2016/8/9.
 *
 * 文件上传操作的具体实现
 */
@Service("IFileUploadService")
public class FileUploadServiceImpl extends BaseService implements IFileUploadService {



    /**
     * 文件上传的实际根路径
     */
    private static final String ROOT_PATH;

    /**
     * 文件上传的WEB根路径
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
    public BackMsg uploadFile(MultipartFile file, String savePath) {
        return uploadFile(file, savePath, defaultFileNameHandler);
    }


    @Override
    public BackMsg uploadFile(MultipartFile file, String savePath, Function<String, String> fileNameHandler) {
        //非空校验
        if (file.isEmpty()) {
            return new BackMsg(1, "", "文件上传中发生异常，请稍后重试");
        }

        savePath = packPath(savePath);
        //文件类型
        String fileType = file.getContentType();
        //文件名
        String fileName = file.getOriginalFilename();

        //检查文件的拓展名是否合法
        if (checkExts(fileType, fileName)) {
            return new BackMsg(DsmConcepts.EXT_WARRING, fileName,
                    fileName + " 类型异常，请上传" + UploadConfigContext.getAllowExts(fileType) + "类型的文件");
        } else {
            //校验上传该类型文件的大小
            if (file.getSize() > UploadConfigContext.getMaxSize(fileType)) {
                return new BackMsg(DsmConcepts.SIZE_WARRING, fileName,
                        fileName + " 大小超过：" + UploadConfigContext.getMaxSize(fileType) / (1024 * 1024) + "m");
            } else {

                try {
                    //上传文件，这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                    String fileNewName = fileNameHandler.apply(fileName);

                    FileUtils.copyInputStreamToFile(file.getInputStream(),
                            new File(ROOT_PATH + savePath, fileNewName));
                    return new BackMsg(0, WEB_PATH + savePath + fileNewName, fileName + "上传成功");
                } catch (IOException e) {
                    System.out.println("文件流获取失败！");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public BackMsg uploadFile(String base64File, String savePath, Function<String, String> pathHandler) {

        if(base64File.split(",").length<2){
            return new BackMsg(1,"","数据提交异常！");
        }
        String fileType = base64File.split(",")[0].split(":")[1];
        byte[] fileByte = EncryptUtils.decodeBase64(base64File.split(",")[1]);

        //获取文件的输入流
        InputStream is = new ByteArrayInputStream(fileByte);


        //校验上传该类型文件的大小
        try {
            if (is.available() > UploadConfigContext.getMaxSize(fileType)) {
                return new BackMsg(DsmConcepts.SIZE_WARRING, "",
                        "大小超过：" + UploadConfigContext.getMaxSize(fileType) / (1024 * 1024) + "m");
            } else {

                try {
                    //上传文件，这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                    String fileNewName = pathHandler.apply(getSessionUser().getId()+"");

                    FileUtils.copyInputStreamToFile(is,
                            new File(ROOT_PATH + savePath, fileNewName));
                    return new BackMsg(0, WEB_PATH + savePath + fileNewName, "上传成功!");
                } catch (IOException e) {
                    System.out.println("文件流获取失败！");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BackMsg> uploadFiles(MultipartFile[] files, String savePath) {
        return uploadFiles(files, savePath, defaultFilesNameHandler);
    }

    @Override
    public List<BackMsg> uploadFiles(MultipartFile[] files, String savePath, BiFunction<String,Integer,String> fileNameHandler) {
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

        List<BackMsg> fileReturnList = new ArrayList<>();
        for (int i = 0;i<files.length;i++) {
            //非空校验
            if (files[i].isEmpty()) {
                fileReturnList.add(new BackMsg(BackMsg.ERROR, "", "文件上传中发生异常，请稍后重试"));
                continue;
            }

            fileType = files[i].getContentType();
            fileName = files[i].getOriginalFilename();
            //检查文件的拓展名是否合法
            if (checkExts(fileType, fileName)) {
                fileReturnList.add(new BackMsg(DsmConcepts.EXT_WARRING, fileName,
                        fileName + " 类型异常，请上传" + UploadConfigContext.getAllowExts(fileType) + "类型的文件"));
            } else {
                //校验上传该类型文件的大小
                if (files[i].getSize() > UploadConfigContext.getMaxSize(fileType)) {
                    fileReturnList.add(new BackMsg(DsmConcepts.SIZE_WARRING, fileName,
                            fileName + " 大小超过：" + UploadConfigContext.getMaxSize(fileType) / (1024 * 1024) + "m"));
                } else {
                    try {
                        //上传文件，这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                        fileNewName = fileNameHandler.apply(fileName,i);
                        FileUtils.copyInputStreamToFile(files[i].getInputStream(),
                                new File(ROOT_PATH + savePath, fileNewName));
                        fileReturnList.add(new BackMsg(BackMsg.CORRECT, WEB_PATH + savePath + fileNewName, fileName + "上传成功"));
                    } catch (IOException e) {
                        System.out.println("文件流获取失败！");
                        e.printStackTrace();
                    }
                }
            }
        }
        return fileReturnList;
    }

    @Override
    public List<String> uploadFilesWithoutMsg(MultipartFile[] files, String savePath, BiFunction<String, Integer, String> pathHandler) {
        List<BackMsg> listMsg = uploadFiles(files,savePath,pathHandler);

        return listMsg.stream().filter(msg -> msg.getError() == BackMsg.CORRECT)
                .map(BackMsg::getData).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<String> uploadFilesWithoutMsg(MultipartFile[] files, String savePath){
        List<BackMsg> listMsg = uploadFiles(files,savePath,defaultFilesNameHandler);

        return listMsg.stream().filter(msg -> msg.getError() == BackMsg.CORRECT)
                .map(BackMsg::getData).collect(Collectors.toCollection(ArrayList::new));
    }


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
     * 单一文件文件名处理内部类
     * 默认的文件名处理操作的内部类 -实现函数接口{@link Function}
     */
    final Function<String, String> defaultFileNameHandler = fileName -> {
        String fileExt = StringHandleUtils.getFileExt(fileName);
        return EncryptUtils.encryptMD5(fileName + System.currentTimeMillis())
                + "_" + new Random().nextInt(10000) + "." + fileExt;
    };

    /**
     * 文件列表文件名处理内部类
     * 默认的文件名处理操作的内部类 -实现函数接口{@link BiFunction}
     */
    final BiFunction<String,Integer, String> defaultFilesNameHandler = (fileName,index) -> {
        String fileExt = StringHandleUtils.getFileExt(fileName);
        return EncryptUtils.encryptMD5(fileName + System.currentTimeMillis())
                + "_" + new Random().nextInt(10000) +index+ "." + fileExt;
    };
}
