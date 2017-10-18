package com.dsm.service.impls;

import com.dsm.common.utils.SessionToolUtils;
import com.dsm.dao.ILocationDao;
import com.dsm.dao.IShippingAddressDao;
import com.dsm.model.address.ShippingAddress;
import com.dsm.model.formData.LocationDTO;
import com.dsm.model.formData.ShippingAddressDTO;
import com.dsm.model.user.User;
import com.dsm.service.base.BaseService;
import com.dsm.service.interfaces.IShippingAddressService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * @author : Lbwwz
 */
@Service("IShippingAddressService")
public class ShippingAddressServiceImpl extends BaseService implements IShippingAddressService {

    @Resource
    private ILocationDao locationDao;

    @Resource
    private IShippingAddressDao shippingAddressDao;

    @Override
    public List<ShippingAddress> getConsigneeAddressList() {

        User user = SessionToolUtils.getUser();
        List<ShippingAddress> consigneeAddressList = shippingAddressDao.getConsigneeAddressList(user.getId());

        //查询为空，则直接返回
        if (consigneeAddressList == null) {
            return null;
        }

        ShippingAddress defaultAddress = user.getDefaultAddress();
        for (ShippingAddress itemIddress : consigneeAddressList) {
            if (itemIddress.getAddressId() == defaultAddress.getAddressId()) {
                itemIddress.setIsDefault(1);
                break;
            }
        }

        return consigneeAddressList;

    }

    /**
     * 校验查询的ShippingAddress中是否含有默认地址
     *
     * @param shippingAddressList 地址信息列表
     * @return 返回是否有默认地址的断言
     */
    private Boolean hasDefaultAddress(List<ShippingAddress> shippingAddressList) {
        if (shippingAddressList == null) {
            return false;
        }

        boolean flag = false;
        for (ShippingAddress shippingAddress : shippingAddressList) {
            if (shippingAddress.getIsDefault() == 1) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public List<ShippingAddress> getConsignorAddressList(long userId) {
        return shippingAddressDao.getConsignorAddressList(userId);
    }

    /**
     * 提交收获地址信息
     *
     * @param shippingAddressDTO 物流地址封装对象
     */
    @Override
    public void submitConsigneeAddressInfo(ShippingAddressDTO shippingAddressDTO) {

        //判断提交操作
        if (shippingAddressDTO.getAddressId() == null) {
            // 提交信息中不包含地址ID，则新增地址
            addConsigneeAddress(shippingAddressDTO);
        } else {
            //包含地址ID，则表示更新地址
            updateConsigneeAddress(shippingAddressDTO);
        }
        //判断是否需要更新session中User的默认地址信息
        User sessionUser = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if (shippingAddressDTO.getIsDefault() > 0 &&
                sessionUser.getDefaultAddress().getAddressId() != shippingAddressDTO.getAddressId()) {
            sessionUser.setDefaultAddress(shippingAddressDao.getDefaultAddress(sessionUser.getId()));
        }

    }


    @Transactional
    @Override
    public long addConsigneeAddress(ShippingAddressDTO shippingAddressDTO) {
        //先将地址信息保存到地址库信息表中
        LocationDTO newLocation = new LocationDTO(shippingAddressDTO.getConsigneeProvince(), shippingAddressDTO.getConsigneeCity(),
                shippingAddressDTO.getConsigneeDistrict(), shippingAddressDTO.getAddress());
        locationDao.addLocation(newLocation);
        shippingAddressDTO.setLocationId(newLocation.getLocationId());
        shippingAddressDao.addShippingAddress(shippingAddressDTO);
        //设置默认地址
        if (shippingAddressDTO.getIsDefault() > 0) {
            shippingAddressDao.updateDefaultRelevant(shippingAddressDTO);
        }
        return shippingAddressDTO.getAddressId();
    }


    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public String deleteShippingAddress(long addressId) {
        String errorMsg;
        User sessionUser = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if (sessionUser.getDefaultAddress().getAddressId() == addressId) {
            //删除的地址是默认地址
            errorMsg = deleteShippingAddress(addressId, true);
        } else {
            //删除非默认地址
            errorMsg =deleteShippingAddress(addressId, false);
        }
        return errorMsg;
    }


    /**
     * 删除的地址为用户的默认地址
     * <p>删除地址钱判定用户是否还有其他地址
     * 1)用户还有其他地址，则将用户设置的最新的地址默认为用户的默认地址
     * 2)用户没有其他地址，则设置用户默认地址为空</p>
     *
     * @param addressId 要删除的地址ID
     * @param isDefault 删除的地址是否是默认地址
     * @return 删除地址过程的错误提示，成功则为空
     */
    private String deleteShippingAddress(long addressId, boolean isDefault) {

        try{
            //执行删除操作
            shippingAddressDao.deleteShippingAddress(addressId);
            if (isDefault) {
                //是默认地址，检查用户是否有最近添加的收货地址，若存在，则设置为默认地址
                User sessionUser = getSessionUser();
                ShippingAddress newestAddress = shippingAddressDao.getNewestConsigneeAddress(sessionUser.getId());

                //用户还有其他收货地址，则设置最新的收获地址为默认地址
                if (newestAddress != null) {
                    long defaultAddressId = newestAddress.getAddressId();
                    //设置最新的添加的收货地址为默认地址
                    shippingAddressDao.updateDefaultRelevant(new ShippingAddressDTO(defaultAddressId, sessionUser.getId()));
                    //更新session中的user的默认地址信息
                    sessionUser.setDefaultAddress(shippingAddressDao.getShippingAddressById(defaultAddressId));
                }
            }
            return "";
        }catch (Exception ex){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "地址信息删除失败";
        }

    }

    @Transactional
    @Override
    public void updateConsigneeAddress(ShippingAddressDTO shippingAddressDTO) {
        //设置为默认地址
        if (shippingAddressDTO.getIsDefault() == 1) {
            shippingAddressDao.updateDefaultRelevant(shippingAddressDTO);
        }
        shippingAddressDao.updateShippingAddress(shippingAddressDTO);

        //若重置默认地址，则更新session中User默认地址设置
        User sessionUser = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if (shippingAddressDTO.getIsDefault() == 1 &&
                sessionUser.getDefaultAddress().getAddressId() != shippingAddressDTO.getAddressId()) {
            sessionUser.setDefaultAddress(getDefaultAddressFromUser(sessionUser.getId()));
            shippingAddressDao.updateDefaultRelevant(shippingAddressDTO);
        }
    }

    @Override
    public ShippingAddress getShippingAddressByAddressId(long addressId) {
        return shippingAddressDao.getShippingAddressById(addressId);
    }

    @Override
    public ShippingAddress getDefaultAddressFromUser(long userId) {
        return shippingAddressDao.getDefaultAddress(userId);
    }

    @Override
    public boolean resetDefaultAddress(long addressId) {
        try{
            shippingAddressDao.updateDefaultRelevant(new ShippingAddressDTO(addressId, getSessionUser().getId()));
            User sessionUser = getSessionUser();
            sessionUser.setDefaultAddress(getDefaultAddressFromUser(sessionUser.getId()));
            return true;
        }catch (Exception ex){
            return false;
        }
    }


}
