package com.dsm.dao;

import com.dsm.model.ImageBean;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/11
 *
 * @author : Lbwwz
 */
@Repository
public interface IImageDao {
    /**
     * 添加图片
     * @param image
     * @return
     */
    Integer addImage(ImageBean image);

    /**
     * 删除图片
     * @param ImgId
     * @return
     */
    Integer deleteImage(int ImgId);
}
