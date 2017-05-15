package com.dsm.service.impls;

import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.dao.IProductDao;
import com.dsm.model.BackMsg;
import com.dsm.model.formData.ReleaseProductFormDTO;
import com.dsm.model.product.ProductBean;
import com.dsm.model.product.ProductDetail;
import com.dsm.model.product.ProductDetailAttrInfo;
import com.dsm.service.interfaces.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/27
 *
 * @author : Lbwwz
 */
@Service("IProductService")
public class ProductServiceImpl implements IProductService {
    public static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Resource
    private IProductDao productDao;

    @Resource
    private IRedisService redisService;

    @Transactional
    @Override
    public BackMsg releaseProduct(ReleaseProductFormDTO releaseProductFormDTO) {

        /**
         * 基本信息校验
         */
        String errorMsg = checkReleaseProductFormInfo(releaseProductFormDTO);
        if (StringUtils.isNotEmpty(errorMsg)) {
            return new BackMsg(DsmConcepts.ERROR, "", errorMsg);
        }
        /**
         * 拆分相关的商品信息，将信息组合调用相关的接口进行保存
         */
        ProductBean productBean = new ProductBean(releaseProductFormDTO.getKeywords(),
                releaseProductFormDTO.getMainImgItem(), releaseProductFormDTO.getProductName(), releaseProductFormDTO.getBrand(),
                releaseProductFormDTO.getCatId(), Integer.parseInt(SessionToolUtils.getShop().getShopId()));
        try {

            try {
                //添加商品基本信息
                productDao.addProductInfo(productBean);
            } catch (Exception ex) {
                throw new Exception("商品基本信息添加失败！");
            }

            int productId = productBean.getProductId();

            List<ProductDetailAttrInfo> baseAttrList = new ArrayList<>(), customAttrList = new ArrayList<>();
            String[] keyValue;
            for (String info : releaseProductFormDTO.getBaseAttrInfo()) {
                keyValue = info.split("|");
                if (keyValue.length != 4 && StringUtils.isEmpty(keyValue[1]) && StringUtils.isEmpty(keyValue[3]))
                    continue;
                if ("#".equals(keyValue[2])) {
                    //设置了自定义的属性值
                    customAttrList.add(new ProductDetailAttrInfo(productId, keyValue[1], keyValue[3]));
                } else {
                    //设置基础属性值
                    baseAttrList.add(
                            new ProductDetailAttrInfo(productId, Integer.parseInt(keyValue[0]), keyValue[1], Integer.parseInt(keyValue[2]), keyValue[3]));
                }
            }
            try {
                productDao.addProductBaseAttrList(baseAttrList);
                //添加已设定的基础属性
            } catch (Exception ex) {
                throw new Exception("商品基础属性添加失败！");
            }

            String[]  customAttrNames,customAttrValues;
            if((customAttrNames=releaseProductFormDTO.getCustomBaseAttrName()).length
                    != (customAttrValues = releaseProductFormDTO.getCustomBaseAttrValue()).length){
                throw new Exception("自定义基础属性数据异常！");
            }

            for(int i = 0;i<customAttrNames.length;i++){
                if(StringUtils.isNoneBlank(customAttrNames[i]) && StringUtils.isNoneBlank(customAttrValues[i]))
                    customAttrList.add(new ProductDetailAttrInfo(productId, customAttrNames[i], customAttrValues[i]));
            }
            try {
                //添加自定义的基础属性
                productDao.addProductCustomAttrList(customAttrList);
            } catch (Exception ex) {
                throw new Exception("商品基础属性添加失败！");
            }
            return new BackMsg(DsmConcepts.CORRECT, "", "商品添加成功！");
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(ex.getMessage());
            return new BackMsg(DsmConcepts.ERROR, "", ex.getMessage());
        }
    }

    private String checkReleaseProductFormInfo(ReleaseProductFormDTO releaseProductFormDTO) {
        String msg = null;
        return msg;
    }

    @Override
    public ProductDetail getProductDetail(Integer productId){

        if(productId == null){
            return null;
        }
        /**
         * 先在redis中查询商品信息，若不存在再到数据库查询并将结果放到缓存中
         */
        String productDetailInfo =  redisService.get("productDetail_"+productId);

        if(StringUtils.isNoneBlank(productDetailInfo)){
            return JSONObject.parseObject(productDetailInfo,ProductDetail.class);
        }else{
            ProductDetail productDetail = productDao.getProductDetailInfo(productId);
            if(productDetail != null){
                redisService.set("productDetail_"+productId,JSONObject.toJSONString(productDetail),1200);
            }
            return productDetail;
        }
    }

}
