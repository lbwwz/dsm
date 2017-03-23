package com.dsm.dao;

import com.dsm.model.seller.Shop;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/1/23
 *
 * @author : Lbwwz
 */
@Repository
public interface IShopDao {

    /**
     * 查询店铺名称数量
     * @param shopName 店铺名称
     * @return 同名数量
     */
    Integer getNameCount(String shopName);

    /**
     * 创建店铺
     * @param shop 需要创建的店铺基本信息
     */
    Integer createShop(Shop shop);

    /**
     * 根据店主查询店铺信息
     * @param userId 卖家ID
     */
    Shop getShopByUserId(int userId);

    /**
     * 根据店铺ID查询店铺信息
     * @param shopId 店铺ID
     */
    Shop getShopByShopId(int shopId);
}
