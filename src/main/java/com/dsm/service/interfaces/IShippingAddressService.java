package com.dsm.service.interfaces;

import com.dsm.model.address.ShippingAddress;
import com.dsm.model.formData.ShippingAddressDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * @author : Lbwwz
 */
public interface IShippingAddressService {

    /**
     * 根据userId查询用户的收货地址信息列表
     *
     * @return 地址列表
     */
    List<ShippingAddress> getConsigneeAddressList();

    /**
     * 根据userId查询用户的发货地址信息列表
     *
     * @return 地址列表
     */
    List<ShippingAddress> getConsignorAddressList(int userId);

    /**
     * 提交用户的地址信息
     */
    void submitConsigneeAddressInfo(ShippingAddressDTO shippingAddressDTO);

    /**
     * 添加收货地址
     *
     * @param shippingAddressDTO 地址信息dto
     * @return 地址的主键id
     */
    int addConsigneeAddress(ShippingAddressDTO shippingAddressDTO);

    /**
     * 根据ID删除相应的地址信息
     *
     * @param addressId 要删除的地址ID
     * @return 错误信息，若无异常，则返回null
     */
    String deleteShippingAddress(int addressId);


    /**
     * 更新收货地址
     *
     * @param shippingAddressDTO 地址信息dto
     */
    void updateConsigneeAddress(ShippingAddressDTO shippingAddressDTO);

    /**
     * 根据对应的地址ID获取相应的地址信息
     *
     * @param addressId 地址ID
     * @return 地址id对应的地址信息
     */
    ShippingAddress getShippingAddressByAddressId(int addressId);

    /**
     * 查询某个用户的默认收获地址
     *
     * @param userId 用户ID
     * @return 默认地址信息
     */
    ShippingAddress getDefaultAddressFromUser(int userId);

    /**
     * 更改默认地址
     * @return
     */
    boolean resetDefaultAddress(int addressId);


}
