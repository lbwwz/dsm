package com.dsm.service.interfaces;

import com.dsm.model.BackMsg;
import com.dsm.model.formData.ReleaseProductFormDTO;
import com.dsm.model.product.ProductBean;
import com.dsm.model.product.ProductDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/27
 *
 * @author : Lbwwz
 *         <p>
 *         商品操作的相关服务接口
 */

public interface IProductService {

    /**
     * 发布商品的服务接口
     *
     * @param dto 表单信息封装
     * @return 操作详情信息返回对象
     */
    BackMsg releaseProduct(ReleaseProductFormDTO dto);

    ProductDetail getProductDetail(Integer productId);

    List<ProductBean> getProductListByCat(Integer catId,int pageIndex,int num,int SelectFlag);


}
