package com.dsm.service.interfaces;

import com.dsm.model.formData.IdentifyInfoDTO;
import com.dsm.model.user.IdentifyInfo;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * @author : Lbwwz
 */
public interface IIdentifyInfoService {

    /**
     * 提交用户的实名验证信息
     * @param info 用户身份验证信息的表单数据信息
     */
    String addIdentifyInfo(IdentifyInfoDTO info);

    /**
     * 获取某一个用户的实名注册信息
     * @param userId
     * @return
     */
    IdentifyInfo getIdentifyInfo(long userId);

    /**
     * 更新用户的实名验证信息
     * @param info 用户身份验证信息的表单数据信息
     */
    String updateIdentifyInfo(IdentifyInfoDTO info);



}
