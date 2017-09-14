package com.dsm.service.impls;

import com.alibaba.fastjson.JSONObject;
import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.common.utils.SessionToolUtils;
import com.dsm.controller.utils.ParamUtils;
import com.dsm.dao.IProductDao;
import com.dsm.dao.IProductSkuDao;
import com.dsm.model.BackMsg;
import com.dsm.model.formData.ReleaseProductFormDTO;
import com.dsm.model.product.*;
import com.dsm.service.interfaces.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

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
    private IProductSkuDao productSkuDao;

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
        ProductBean productBean = new ProductBean(releaseProductFormDTO.getKeywords(), releaseProductFormDTO.getProduceBrief(),
                releaseProductFormDTO.getMainImgItem(), releaseProductFormDTO.getProductName(), releaseProductFormDTO.getBrand(),
                releaseProductFormDTO.getCatId(), Integer.parseInt(SessionToolUtils.getUser().getShop().getShopId()));
        try {
            /**
             * 添加商品基本信息
             */
            try {
                productDao.addProductInfo(productBean);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("商品基本信息添加失败！");
            }
            int productId = productBean.getProductId();

            /**
             * 添加商品的图片列表
             */
            if (releaseProductFormDTO.getImgItem() != null) {
                int isMain;
                List<ProductImageItem> imageList = new ArrayList<>();

                for (String item : releaseProductFormDTO.getImgItem()) {
                    if (releaseProductFormDTO.getMainImgItem().equals(item)) {
                        isMain = 1;
                    } else {
                        isMain = 0;
                    }
                    imageList.add(new ProductImageItem(productId, item, isMain));
                }
                try {
                    productDao.addProductImageList(imageList);
                } catch (Exception ex) {
                    throw new Exception("商品图片列表添加失败！", ex);
                }
            }

            /**
             * 添加商品详情信息
             */
            try {
                productDao.addGraphicDetail(new GraphicDetail(productId, releaseProductFormDTO.getDetailContent()));
            } catch (Exception ex) {
                throw new Exception("商品详情内容添加失败！", ex);
            }

            /**
             * 添加基础属性和自定义属性
             */
            List<ProductDetailAttrInfo> baseAttrList = new ArrayList<>(), customAttrList = new ArrayList<>();
            String[] keyValue;
            for (String info : releaseProductFormDTO.getBaseAttrInfo()) {
                if (StringUtils.isBlank(info)) continue;
                keyValue = info.split("\\|");
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
            String[] customAttrNames = releaseProductFormDTO.getCustomBaseAttrName(), customAttrValues = releaseProductFormDTO.getCustomBaseAttrValue();

            if (customAttrNames != null && customAttrValues != null) {
                if (customAttrNames.length != customAttrValues.length) {
                    throw new Exception("自定义基础属性数据异常！");
                }
                for (int i = 0; i < customAttrNames.length; i++) {
                    if (StringUtils.isNoneBlank(customAttrNames[i]) && StringUtils.isNoneBlank(customAttrValues[i]))
                        customAttrList.add(new ProductDetailAttrInfo(productId, customAttrNames[i], customAttrValues[i]));
                }
            }

            try {
                //添加自定义的基础属性
                if (customAttrList.size() > 0)
                    productDao.addProductCustomAttrList(customAttrList);
            } catch (Exception ex) {
                throw new Exception("商品基础属性添加失败！", ex);
            }

            try {
                //添加已设定的基础属性
                if (baseAttrList.size() > 0)
                    productDao.addProductBaseAttrList(baseAttrList);
            } catch (Exception ex) {
                throw new Exception("商品基础属性添加失败！", ex);
            }

            /**
             * 添加商品sku信息
             */
            List<Sku> skuList = new ArrayList<>();
            String[] propertiesNames = releaseProductFormDTO.getProperty();
            for (int i = 0; i < propertiesNames.length; i++) {
                //排除未设置价格，propertiesName莫名为空的不合格sku
                if (StringUtils.isBlank(propertiesNames[i]) || releaseProductFormDTO.getProductPrice()[i] == null) {
                    continue;
                }
                skuList.add(new Sku(productId, propertiesNames[i], releaseProductFormDTO.getProductCount()[i],
                        releaseProductFormDTO.getProductPrice()[i], releaseProductFormDTO.getCustomProductNo()[i]));
            }
            //检查并过滤无效sku信息
            doCheckSkuList(skuList);
            try {
                if (skuList.size() > 0)
                    productSkuDao.addSkuItemList(skuList);
            } catch (Exception ex) {
                throw new Exception("商品sku信息添加失败！", ex);
            }

            return new BackMsg(DsmConcepts.CORRECT, "", "商品添加成功！");
        } catch (Exception ex) {
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(ex.getMessage());
            return new BackMsg(DsmConcepts.ERROR, "", ex.getMessage());
        }
    }


    /**
     * 根据商品的ID查询商品详情页信息
     *
     * @param productId 商品ID
     */
    @Override
    public ProductDetail getProductDetail(Integer productId) {

        if (productId == null) {
            return null;
        }
        ProductDetail productDetail;
        /**
         * 先在redis中查询商品信息，若不存在再到数据库查询并将结果放到缓存中
         */
        String productDetailInfo = redisService.get("productDetail_" + productId);

        if (StringUtils.isNoneBlank(productDetailInfo)) {
            productDetail = JSONObject.parseObject(productDetailInfo, ProductDetail.class);
        } else {
            productDetail = productDao.getProductDetailInfo(productId);
            if (productDetail != null) {
                //设置商品的缓存时间为10分钟过期
                redisService.set("productDetail_" + productId, JSONObject.toJSONString(productDetail), DsmConcepts.MINUTE*10);
            }
        }
        /**
         * 操作将sku信息转换成相应的销售属性列表集
         */
        if (productDetail != null) {
            productDetail.setSaleAttrInfo(formatSkuListToSaleAttrList(productDetail.getSkuList()));

        }


        return productDetail;
    }

    /**
     * 将sku对象结果集转换为销售属性信息列表
     *
     * @param skuList
     * @return
     */
    private List<ProductDetailAttrInfo> formatSkuListToSaleAttrList(List<Sku> skuList) {

        List<ProductDetailAttrInfo> saleAttrInfoList = new ArrayList<>(skuList.get(0).getPropertiesName().split(";").length);
        String[] itemInfoList, tempList;
        for (int i = 0; i < skuList.size(); i++) {
            itemInfoList = skuList.get(i).getPropertiesName().split(";");

            if (i == 0) {
                for (String itemInfo : itemInfoList) {
                    tempList = itemInfo.split("\\|");
                    if (tempList.length != 4) {
                        logger.error("录入的sku信息异常，skuId为{}", skuList.get(i).getSkuId());
                        return null;
                    }
                    Set<AttrValueBean> attrValueSet = new HashSet<>();
                    attrValueSet.add(new AttrValueBean(Integer.parseInt(tempList[1]), tempList[3]));
                    saleAttrInfoList.add(new ProductDetailAttrInfo(Integer.parseInt(tempList[0]), tempList[2], attrValueSet));
                }
            } else {
                for (int j = 0; j < itemInfoList.length; j++) {
                    tempList = itemInfoList[j].split("\\|");
                    if (tempList.length != 4) {
                        logger.error("录入的sku信息异常，skuId为{}", skuList.get(i).getSkuId());
                        return null;
                    }
                    saleAttrInfoList.get(j).getSaleAttrValues().add(new AttrValueBean(Integer.parseInt(tempList[1]), tempList[3]));
                }
            }
        }
        return saleAttrInfoList;
    }



    @Transactional(timeout = 10000)
    @Override
    public Map<String,Object> getProductListByCat(Integer catId, int pageIndex, int num, int sortType, String ev) {
        if (catId == null) {
            return null;
        }
        num = num != 0 ? num : DsmConcepts.LIST_PAGE_DEFAULT_NUM;
        int totalNum = num * DsmConcepts.LIST_PAGE_SIZE;

        try {
            List<BaseAttrBean> attrBeans = ParamUtils.formatAttrSelectParamToBean(ev);//抛出非法参数异常

            List<ProductBean> list;
            String cacheKey = "productList_" + catId + "_" + totalNum + "_" + sortType + "_" + ev;
            //这里可以考虑使用缓存
            String productList = redisService.get(cacheKey);

            if (StringUtils.isNoneBlank(productList)) {
                list =  JSONObject.parseArray(productList, ProductBean.class);
            }else {
                //缓存中不存在,在数据库中查询
                if (sortType < 3) { //默认排序和点击排序
                    String[] queryWeight = getQueryTypeInfo(sortType);
                    //根据权重排序，分页查询相关的商品信息
                    list = productDao.getPageByCategoryWithWeightValueNew(catId, totalNum, attrBeans, queryWeight);
                } else {
                    if (sortType == DsmConcepts.SEARCH_SORT_PRICE_TO_LARGE) {
                        list = productDao.getPageByCategoryWithPriceNew(catId, totalNum, 0, attrBeans,attrBeans == null?0:attrBeans.size());
                    } else {
                        list = productDao.getPageByCategoryWithPriceNew(catId, totalNum, 1, attrBeans,attrBeans == null?0:attrBeans.size());
                    }
                }
                //设置商品列表的缓存时间为10分钟
                redisService.set(cacheKey, JSONObject.toJSONString(list), DsmConcepts.MINUTE*10);
            }

            if (list != null && list.size() > 0) {
                Map<String,Object> m = new HashMap<>();
                m.put("totalPage",list.size() % num != 0?list.size()/num+1:list.size()/num);
                //查询分页数据
                int length = list.size(), start = pageIndex * num, end = Math.min((start + num), length);
                if (start <= end && end <= length) {
                    m.put("productList", list.subList(pageIndex * num, end));
                }
                return m;
            }
        } catch (IllegalArgumentException iex) {
            logger.error(iex.getMessage());
            throw new IllegalArgumentException(iex.getMessage());
        } catch (Exception ex) {
            logger.error("类目查询商品信息失败：{}", ex.getMessage());
        }
        return null;
    }


    /**
     * 使用哪种权值排序
     *
     * @param sortType 0：默认；1：热度（点击量）；2：信用（好评分数）；3：价格（由低到高）；4：价格（由高到低）
     * @return
     */
    private String[] getQueryTypeInfo(int sortType) {
        switch (sortType) {
            case DsmConcepts.SEARCH_SORT_DEFAULT:
                return new String[]{"0.05", "1", "1", "1", "0"};
            case DsmConcepts.SEARCH_SORT_HOT:
                return new String[]{"1", "0", "0", "0", "0"};
            case DsmConcepts.SEARCH_SORT_PRICE_TO_LARGE:
                break;
            case DsmConcepts.SEARCH_SORT_PRICE_TO_SMALL:
                break;
        }
        return null;
    }


    /**
     * 校验基本信息是否符合商品添加的相关要求
     *
     * @param releaseProductFormDTO 商品信息的bean封装
     */
    private String checkReleaseProductFormInfo(ReleaseProductFormDTO releaseProductFormDTO) {
        String msg = null;
        return msg;
    }

    /**
     * 处理 sku 列表
     */
    private void doCheckSkuList(List<Sku> skuList) {
        if (skuList == null || skuList.size() == 0) {
            return;
        }
        Iterator<Sku> it = skuList.iterator();
        Sku sku;
        String temp;
        while (it.hasNext()) {
            sku = it.next();

            if (StringUtils.isBlank((temp = getSkuProperties(sku.getPropertiesName())))) {
                it.remove();
                continue;
            }
            sku.setProperties(temp);
        }
    }

    /**
     * 根据提交的propertiesInfo信息获取当中的properties
     *
     * @param propertiesInfo 提交的properties信息
     */
    private String getSkuProperties(String propertiesInfo) {
        if (StringUtils.isBlank(propertiesInfo)) {
            return null;
        }
        String[] skuAttrs = propertiesInfo.split(";"), temp;
        String properties = "";
        for (String attr : skuAttrs) {
            if (StringUtils.isBlank(attr)) {
                return null;
            }
            temp = attr.split("\\|");
            if (temp.length != 4) {
                return null;
            } else {
                properties += temp[0] + ":" + temp[1] + ",";
            }
        }
        properties = StringUtils.removeEnd(properties, ",");
        return properties;
    }


}
