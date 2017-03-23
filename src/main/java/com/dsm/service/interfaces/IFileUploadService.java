package com.dsm.service.interfaces;


import com.dsm.model.BackMsg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Lbwwz on 2016/8/9.
 * <p>
 * 提供文件上传服务的接口
 */
public interface IFileUploadService {

    /**
     * * 上传单个文件，返回文件的url
     *
     * @param file     {@link MultipartFile}
     * @param savePath 上传文件的保存目录
     * @return 文件上传结果封装，成功返回其对应的文件url
     */
//    @SuppressWarnings("unused")
    BackMsg uploadFile(MultipartFile file, String savePath);

    /**
     * 上传单个文件，返回文件的url
     *
     * @param file        {@link MultipartFile}
     * @param savePath    上传文件的保存目录
     * @param PathHandler 文件名操作接口，用于设置所保存的新的文件名称
     *                    <p>关于两个泛型参数，前一个参数表示传入类型（原文件名），后一个表示返回参数类型（新的文件名）<p/>
     * @return 文件上传结果封装，成功返回其对应的文件url
     */
    BackMsg uploadFile(MultipartFile file, String savePath, Function<String, String> PathHandler);


    /**
     * 上传base64编码的文件
     *
     * @param base64File  base64 文件编码
     * @param savePath    保存的文件路径
     * @param PathHandler 文件名设置方式
     * @return 文件上传结果封装，成功返回其对应的文件url
     */
    BackMsg uploadFile(String base64File, String savePath, Function<String, String> PathHandler);

    /**
     * 多文件上传，返回文件的url信息列表
     *
     * @param files    {@link MultipartFile}集合对象
     * @param savePath 上传文件的保存目录
     * @return 文件上传结果封装集合，成功返回其对应的文件url
     */
    List<BackMsg> uploadFiles(MultipartFile[] files, String savePath);

    /**
     * 上传多个文件，返回文件的url信息列表
     *
     * @param files       {@link MultipartFile}集合对象
     * @param savePath    上传文件的保存目录
     * @param pathHandler 文件名操作接口，用于设置所保存的新的文件名称
     *                    <p>
     *                    关于三个泛型参数：
     *                    前一个参数表示传入类型（原文件名），中间一个是传入的文件列表index（从0开始），
     *                    最后一个是输出类型（新的文件名）
     *                    <p>
     * @return 文件上传结果封装集合，成功返回其对应的文件url
     */
    List<BackMsg> uploadFiles(MultipartFile[] files, String savePath, BiFunction<String, Integer, String> pathHandler);


    /**
     * 上传多个文件，返回文件的url信息列表
     *
     * @param files    文件列表
     * @param savePath 保存路径
     * @return 上传成功的文件路径集合
     */
    List<String> uploadFilesWithoutMsg(MultipartFile[] files, String savePath);

    /**
     * 上传多个文件，返回文件的url信息列表
     *
     * @param files       文件列表
     * @param savePath    保存路径
     * @param pathHandler 文件名操作接口，用于设置所保存的新的文件名称
     *                    <p>
     *                    关于三个泛型参数：
     *                    前一个参数表示传入类型（原文件名），中间一个是传入的文件列表index（从0开始），
     *                    最后一个是输出类型（新的文件名）
     *                    <p>
     * @return 上传成功的文件路径集合
     */
    List<String> uploadFilesWithoutMsg(MultipartFile[] files, String savePath, BiFunction<String, Integer, String> pathHandler);
}
