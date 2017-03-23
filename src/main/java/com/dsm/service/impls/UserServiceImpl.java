package com.dsm.service.impls;

import com.dsm.common.utils.EncryptUtils;
import com.dsm.dao.ILocationDao;
import com.dsm.dao.IShippingAddressDao;
import com.dsm.dao.IUserDao;
import com.dsm.model.BackMsg;
import com.dsm.model.formData.UserBaseInfoDTO;
import com.dsm.model.formData.UserRegisterDTO;
import com.dsm.model.user.User;
import com.dsm.service.interfaces.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Lbwwz on 2016/8/19.
 * <p>
 * IUserService 的实现类
 */
@Service("IUserService")
public class UserServiceImpl implements IUserService {


    @Resource
    private IUserDao userDao;

    @Resource
    private ILocationDao locationDao;

    @Resource
    private IShippingAddressDao shippingAddressDao;


    @Transactional
    @Override
    public String submitBaseInfo(UserBaseInfoDTO userInfo) {
        try{
            //向地址库中更新居住地和家乡地址信息
            if (userInfo.getHometown().getLocationId() == 0) {
                locationDao.addLocation(userInfo.getHometown());
            } else {
                locationDao.updateLocation(userInfo.getHometown());
            }
            if (userInfo.getDomicile().getLocationId() == 0) {
                locationDao.addLocation(userInfo.getDomicile());
            } else {
                locationDao.updateLocation(userInfo.getDomicile());
            }
            //更新user主表的user信息
            userDao.updateBaseInfo(userInfo);

            return null;
        }catch (Exception e){
            return "信息提交发生异常，请稍后再试！";
        }
    }

    @Override
    public User queryUserById(int id) {
        return queryDetailUserInfo(userDao.getUserById(id));
    }

    @Override
    public BackMsg userLogin(String loginName, String password, boolean isRemember) {

        BackMsg backMsg = new BackMsg();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, EncryptUtils.encryptMD5(password));

        //设置是否记住账户
        token.setRememberMe(isRemember);

        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
            backMsg.set(BackMsg.CORRECT, "", "验证通过");

        } catch (UnknownAccountException uae) {
            backMsg.set(BackMsg.ERROR, "", "账户不存在");
            return backMsg;
        } catch (IncorrectCredentialsException ice) {
            backMsg.set(BackMsg.ERROR, "", "密码输入不正确！");
            return backMsg;
        } catch (LockedAccountException lae) {
            backMsg.set(BackMsg.ERROR, "", "抱歉，您的账户暂时被冻结！");
            return backMsg;
        } catch (ExcessiveAttemptsException eae) {
            backMsg.set(BackMsg.ERROR, "", "错误次数过多，请1小时后重试");
            return backMsg;
        } catch (Exception ae) {
            backMsg.set(BackMsg.ERROR,"",ae.getMessage());
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
//            User loginUser = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
            //查询设置用户的详细信息
//            queryDetailUserInfo(loginUser);
        } else {
            token.clear();
        }
        return backMsg;
    }

    /**
     * 查询用户的详细信息
     * <p>为User对象中的引用属性赋值</p>
     *
     * @param user 经过初步查询的用户信息 <i>user主表查询获取的用户信息</i>
     */
//    @Transactional
    private User queryDetailUserInfo(User user) {
//        //查询家乡信息
//        user.setHometown(locationDao.getLocation(user.getHometownId()));
//        //查询居住地信息
//        user.setDomicile(locationDao.getLocation(user.getDomicileId()));
        return user;

    }

    @Override
    public String uploadHeadImage(UserBaseInfoDTO imageMsg) {
        String errorMsg = null;
        userDao.updateHeadImage(imageMsg);
        return errorMsg;
    }

    @Transactional
    @Override
    public String register(UserRegisterDTO userRegisterDTO) {
        try{
            userDao.register(userRegisterDTO);
            shippingAddressDao.addDefaultRelevant(userRegisterDTO.getId());
        }catch (Exception e){
            return "注册过程发生异常，抱歉请稍后重试！";
        }
        return null;
    }
}
