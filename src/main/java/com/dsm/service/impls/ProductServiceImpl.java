package com.dsm.service.impls;

import com.dsm.common.DsmConcepts;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.dao.IProductDao;
import com.dsm.model.BackMsg;
import com.dsm.model.formData.ReleaseProductFormDTO;
import com.dsm.model.product.ProductBean;
import com.dsm.service.interfaces.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/27
 *
 * @author : Lbwwz
 */
@Service("IProductService")
public class ProductServiceImpl implements IProductService {

    @Resource
    private IProductDao productDao;

    @Transactional
    @Override
    public BackMsg releaseProduct(ReleaseProductFormDTO releaseProductFormDTO) {

        /**
         * 基本信息校验
         */
        String errorMsg = checkReleaseProductFormInfo(releaseProductFormDTO);
        if(StringUtils.isNotEmpty(errorMsg)){
            return new BackMsg(DsmConcepts.ERROR,"",errorMsg);
        }

        /**
         * 拆分相关的商品信息，将信息组合调用相关的接口进行保存
         */
        ProductBean productBean = new ProductBean(releaseProductFormDTO.getKeywords(),"",
                releaseProductFormDTO.getMainImgItem(),releaseProductFormDTO.getProductName(),releaseProductFormDTO.getBrand(),
                releaseProductFormDTO.getCatId(), Integer.parseInt(SessionToolUtils.getShop().getShopId()));
//        public ProductBean(String keywords, String productDesc, String mainImage, String productName, Integer brandId, Integer catId, Integer shopId) {

        productDao.addProductInfo(productBean);

        return null;
    }

    private String checkReleaseProductFormInfo(ReleaseProductFormDTO releaseProductFormDTO) {
        String msg = null;


        return msg;
    }

}
