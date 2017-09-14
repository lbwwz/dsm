package com.dsm.service.interfaces;

import com.dsm.model.BackMsg;
import com.dsm.model.formData.ReleaseProductFormDTO;
import com.dsm.model.product.ProductDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/27
 *
 * @author : Lbwwz
 *         <p/>
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

    /**
     * 条件查询类目下的商品列表
     *
     * @param catId     类目
     * @param pageIndex 页面index
     * @param num       每页商品数量
     * @param sortType  排序规则 0：默认；1：热度（点击量）；2：信用（好评分数）；3：价格（由低到高）；4：价格（由高到低）
     * @param ev        多选属性筛选条件
     * @return 商品列表信息
     */
    Map<String,Object> getProductListByCat(Integer catId, int pageIndex, int num, int sortType, String ev);
}
