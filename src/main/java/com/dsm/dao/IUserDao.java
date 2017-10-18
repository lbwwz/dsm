package com.dsm.dao;

import com.dsm.model.formData.UserBaseInfoDTO;
import com.dsm.model.formData.UserRegisterDTO;
import com.dsm.model.user.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Lbwwz on 2016/7/27.
 */
@Repository
public interface IUserDao {

    /**
     * 注册用户，将用户信息提交到数据库中，主键会赋值给的user 的 id属性
     *
     * @param user 执行注册操作封装的User对象
     * @return 返回操作的影响的行数，当返回值大于0表示操作成功
     */
    Integer register(UserRegisterDTO user);

    /**
     * ajax 检测用户昵称属否存在
     *
     * @param userName 用户名
     * @return 返回改用户名对应的用户数量，大于0表示用户存在
     */
    Integer checkUserNameExist(String userName);

    /**
     * 判断电话号码是否已经被注册
     *
     * @param mobile 电话号码
     * @return
     */
    Integer checkMobileExist(String mobile);

    /**
     * 根据用户的登录名称查询相应的用户信息
     * userLogin中封装的是用户登录名中填写的信息，若为email，则通过email查询，若为userName，则通过userName查询
     *
     * @param userLogin
     * @return
     */
    User queryUserByLogin(User userLogin);

    /**
     * 通过user的ID获取相应的用户对象
     *
     * @param id
     * @return
     */
    User getUserById(long id);

    /**
     * 更新用户的最后登录时间
     */
    void setLastVisit(long id);

    /**
     * 更新用户的基本信息（baseInfo页面使用:用户性别，QQ，生日，家乡，居住地）
     *
     * @param userBaseInfo
     */
    void updateBaseInfo(UserBaseInfoDTO userBaseInfo);


    /**
     * 更新用户的头像
     *
     * @param userHeadImg
     */
    void updateHeadImage(UserBaseInfoDTO userHeadImg);

    /**
     * 设置用户的默认收获地址
     *
     * @param userDefaultAddr
     */
    void updateDefaultAddress(User userDefaultAddr);

    /**
     * 设置用户角色为买家角色
     *
     * @param userId
     */
    void becomeSeller(long userId);


    /**
     * 变更用户的账户可用状态
     *
     * @param userId
     */
    Integer changeStatus(long userId);

    /**
     * 更改用户的身份验证状态
     */
    Integer changePromotedType(User userInfo);

}
