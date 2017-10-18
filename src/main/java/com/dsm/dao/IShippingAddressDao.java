package com.dsm.dao;

import com.dsm.model.address.ShippingAddress;
import com.dsm.model.formData.ShippingAddressDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * @author : Lbwwz
 */
@Repository
public interface IShippingAddressDao {

    /**
     * 根据收获地址ID获取物流地址对象信息
     * @param addressId 物流地址ID
     * @return id为addressId的物流地址对象
     */
    ShippingAddress getShippingAddressById(long addressId);

    /**
     * 添加一条新的物流地址
     * @param shippingAddressDTO {@link ShippingAddressDTO}地址操作数据对象
     * @return 1表示成功，0表示失败
     */
    int addShippingAddress(ShippingAddressDTO shippingAddressDTO);

    /**
     * 删除物流地址信息
     * @param addressId 物流地址ID
     */
    void deleteShippingAddress(long addressId);

    /**
     * 更新用户的物流地址
     * @param shippingAddressDTO {@link ShippingAddressDTO}地址操作数据对象
     */
    void updateShippingAddress(ShippingAddressDTO shippingAddressDTO);

    /**
     * 新增默认地址关联关系
     * @param userId 要注册关联关系的用户Id(默认注册时不关联地址)
     */
    void addDefaultRelevant(int userId);


    /**
     * 更新默认地址关联关系
     * @param shippingAddressDTO {@link ShippingAddressDTO}地址操作数据对象
     */
    @SuppressWarnings("unused")
    void updateDefaultRelevant(ShippingAddressDTO shippingAddressDTO);


    /**
     * 查询某一用户的所有收货地址
     * @param userId 用户的主键id
     * @return 查询获取的该用户的所有收货信息
     */
    List<ShippingAddress> getConsigneeAddressList(long userId);


    /**
     * 查询用户最新添加的收货地址
     * @param userId 用户ID
     * @return 最新的一条用户收货地址信息
     */
    ShippingAddress getNewestConsigneeAddress(long userId);


    /**
     * 查询某一用户的所有发货地址
     * @param userId 用户主键id
     * @return 某个用户的所有发货地址
     */
    List<ShippingAddress> getConsignorAddressList(long userId);

    /**
     * 获取用户的默认收获地址
     * @param userId 用户ID
     * @return 用户的默认地址
     */
    ShippingAddress getDefaultAddress(long userId);


}
