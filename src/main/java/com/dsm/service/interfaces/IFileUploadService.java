package com.dsm.service.interfaces;


import com.dsm.model.BackMsg;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Lbwwz on 2016/8/9.
 * <p/>
 * 提供文件上传服务的接口
 */
public interface IFileUploadService {

    /**
     * 获取return 的msg中的url list
     * @param backMsgList {@link BackMsg}信息对象列表
     * @return file url list
     */
    static List<String> getUpLoadUrlList(List<BackMsg<String>> backMsgList) {
        return backMsgList.stream().filter(msg -> msg.getError() == BackMsg.CORRECT)
                .map(BackMsg::getData).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * * 上传单个文件，返回文件的url
     *
     * @param file     {@link MultipartFile}
     * @param savePath 上传文件的保存目录
     * @return 文件上传结果封装，成功返回其对应的文件url
     */
    BackMsg<String> uploadFile(MultipartFile file, String savePath);

    /**
     * 上传单个文件，返回文件的url
     *
     * @param file        {@link MultipartFile}
     * @param savePath    上传文件的保存目录
     * @param PathHandler 文件名操作接口，用于设置所保存的新的文件名称
     *                    <p>关于两个泛型参数，前一个参数表示传入类型（原文件名），后一个表示返回参数类型（新的文件名）<p/>
     * @return 文件上传结果封装，成功返回其对应的文件url结果信息
     */
    BackMsg<String> uploadFile(MultipartFile file, String savePath, Function<String, String> PathHandler);

    /**
     * 上传单个文件，并将该条上传记录保存
     *
     * @param file     {@link MultipartFile}
     * @param savePath 上传文件的保存目录
     * @return 文件上传结果封装，成功返回其对应的文件url结果信息
     */
    BackMsg<String> uploadFileWithSaveInfo(MultipartFile file, String savePath, int saveUploadDataInfoType);

    /**
     * 上传base64编码的文件
     *
     * @param base64File  base64 文件编码
     * @param savePath    保存的文件路径
     * @param pathHandler 文件名设置方式
     * @return 文件上传结果封装，成功返回其对应的文件url结果信息
     */
    BackMsg<String> uploadFile(String base64File, String savePath, Function<String, String> pathHandler);

    /**
     * 上传base64编码的文件，并将该条上传记录保存
     *
     * @param base64File  base64 文件编码
     * @param savePath    保存的文件路径
     * @param pathHandler 文件名设置方式
     * @param saveUploadDataInfoType type：0，一般图片；1：商品图片；2：商品详情图片，3：广告图片
     * @return 文件上传结果封装，成功返回其对应的文件url结果信息
     */
    BackMsg<String> uploadFileWithSaveInfo(String base64File, String savePath, Function<String, String> pathHandler, int saveUploadDataInfoType);

    /**
     * 多文件上传，返回文件的url信息列表
     *
     * @param files    {@link MultipartFile}集合对象
     * @param savePath 上传文件的保存目录
     * @return 文件上传结果封装，成功返回该组文件url列表结果信息
     */
    List<BackMsg<String>> uploadFiles(MultipartFile[] files, String savePath);


    /**
     * 多文件上传，并将上传成功的这些上传记录保存
     *
     * @param files    {@link MultipartFile}集合对象
     * @param savePath 上传文件的保存目录
     * @param saveUploadDataInfoType type：0，一般图片；1：商品图片；2：商品详情图片，3：广告图片
     * @return 文件上传结果封装，成功返回其对应的文件url结果信息
     */
    List<BackMsg<String>> uploadFilesWithSaveInfo(MultipartFile[] files, String savePath, int saveUploadDataInfoType);

    /**
     * 上传多个文件，返回文件的url信息列表
     *
     * @param files       {@link MultipartFile}集合对象
     * @param savePath    上传文件的保存目录
     * @param pathHandler 文件名操作接口，用于设置所保存的新的文件名称
     *                    <p/>
     *                    关于三个泛型参数：
     *                      前一个参数表示传入类型（原文件名），
     *                      中间一个是传入的文件列表index（从0开始），
     *                      最后一个是输出类型（新的文件名）
     *                    <p/>
     * @return 文件上传结果封装集合，成功返回其对应的文件url
     */
    List<BackMsg<String>> uploadFiles(MultipartFile[] files, String savePath, BiFunction<String, Integer, String> pathHandler);


    /**
     * 上传多个文件，并将上传成功的这些上传记录保存
     *
     * @param saveUploadDataInfoType type：0，一般图片；1：商品图片；2：商品详情图片，3：广告图片
     * @return 文件上传结果封装，成功返回其对应的文件url结果信息
     */
    List<BackMsg<String>> uploadFilesWithSaveInfo(MultipartFile[] files, String savePath, BiFunction<String, Integer, String> fileNameHandler, int saveUploadDataInfoType);
}
