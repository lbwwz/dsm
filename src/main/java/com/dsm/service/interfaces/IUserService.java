package com.dsm.service.interfaces;

import com.dsm.model.BackMsg;
import com.dsm.model.formData.UserBaseInfoDTO;
import com.dsm.model.formData.UserRegisterDTO;
import com.dsm.model.user.User;

/**
 * Created by Lbwwz on 2016/8/1.
 *
 * 用户操作的service
 */
public interface IUserService {

    /**
     * 用户登录
     * @param loginName 用户登录名，可以是邮箱，电话，或者用户昵称
     * @param password 用户密码
     * @param isRemember 是否记住登录
     * @return {@link BackMsg} 返回对象信息封装
     */
    BackMsg userLogin(String loginName, String password, boolean isRemember);

    /**
     * 提交用户基本信息
     * @param userInfo {@link UserBaseInfoDTO} 用户基本信息封装对象
     * @return 提交操作遇到的错误信息，若为空，表示提交成功
     */
    String submitBaseInfo(UserBaseInfoDTO userInfo);

    /**
     * 查询用户信息
     * @param id 用户的ID
     * @return 用户对象
     */
    User queryUserById(int id);

    /**
     * 更新用户头像信息
     * @param imageMsg {@link UserBaseInfoDTO} 用户基本信息封装对象
     * @return 更新中的异常信息，若为空，表示更新成功
     */
    String uploadHeadImage(UserBaseInfoDTO imageMsg);

    /**
     * 用户注册
     * @param userRegisterDTO {@link UserRegisterDTO} 用户注册信息封装对象
     * @return 注册中的异常信息，若为空，表示注册成功
     */
    String register(UserRegisterDTO userRegisterDTO);
}
