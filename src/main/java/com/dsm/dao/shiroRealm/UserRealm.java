package com.dsm.dao.shiroRealm;

import com.dsm.common.utils.ValidateUtils;
import com.dsm.dao.IShopDao;
import com.dsm.dao.IUserDao;
import com.dsm.model.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 * Created by Lbwwz on 2016/8/15.
 *
 * 自定义的指定Shiro验证用户登录的类
 */
public class UserRealm extends AuthorizingRealm {


    @Resource
    private IUserDao userDao;

    @Resource
    private IShopDao shopDao;

    /**
     * 为当前登录的Subject授予角色和权限
     * @ 经测试:本例中该方法的调用时机为需授权资源被访问时
     * @ 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * @ 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     * @ 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        String currentUsername = (String)super.getAvailablePrincipal(principals);
//      List<String> roleList = new ArrayList<String>();
//      List<String> permissionList = new ArrayList<String>();
//      //从数据库中获取当前登录用户的详细信息
//      User user = userService.getByUsername(currentUsername);
//      if(null != user){
//          //实体类User中包含有用户角色的实体类信息
//          if(null!=user.getRoles() && user.getRoles().size()>0){
//              //获取当前登录用户的角色
//              for(Role role : user.getRoles()){
//                  roleList.add(role.getName());
//                  //实体类Role中包含有角色权限的实体类信息
//                  if(null!=role.getPermissions() && role.getPermissions().size()>0){
//                      //获取权限
//                      for(Permission pmss : role.getPermissions()){
//                          if(!StringUtils.isEmpty(pmss.getPermission())){
//                              permissionList.add(pmss.getPermission());
//                          }
//                      }
//                  }
//              }
//          }
//      }else{
//          throw new AuthorizationException();
//      }
//      //为当前用户设置角色和权限
//      SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
//      simpleAuthorInfo.addRoles(roleList);
//      simpleAuthorInfo.addStringPermissions(permissionList);
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        //实际中可能会像上面注释的那样从数据库取得
        if(null!=currentUsername && "jadyer".equals(currentUsername)){
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
            simpleAuthorInfo.addRole("admin");
            //添加权限
            simpleAuthorInfo.addStringPermission("admin:manage");
            System.out.println("已为用户[jadyer]赋予了[admin]角色和[admin:manage]权限");
            return simpleAuthorInfo;
        }else if(null!=currentUsername && "玄玉".equals(currentUsername)){
            System.out.println("当前用户[玄玉]无授权");
            return simpleAuthorInfo;
        }
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置
        return null;
    }


    /**
     * 验证当前登录的Subject
     * @ 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        /**
         * 获取基于用户名和密码的令牌
         * 实际上这个 token 是从 UserServiceImpl 里面currentUser.login(token)传过来的
         */
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        User user = getUserByLoginName(token.getUsername());

        if (null != user) {
            //校验用户是否被禁封
            if(user.getStatus() > 0){
                throw new AuthenticationException("用户账户被禁封，请联系管理员！");
            }
            //未被禁封，校验用户密码
            String password = String.valueOf(token.getPassword());
            if (password.equals(user.getPassword())) {
                AuthenticationInfo authcInfo =
                        new SimpleAuthenticationInfo(user.getUserName(), user.getPassword().toCharArray(), getName());
                this.setSession("user", user);
                if(user.getPromotedType() == 3){
                    try{
                        //设置用户的店铺信息
                        user.setShop(shopDao.getShopByUserId(user.getId()));
                    }catch (Exception ex){
                        throw new RuntimeException("查询店铺信息出错！");
                    }

                }
                return authcInfo;
            }else{
                throw new IncorrectCredentialsException("密码错误！");
            }
        }
        return null;
    }

    /**
     * 根据登录名查询用户的信息
     * @param loginName 用户输入的登录名
     * @return 根据登录名查询的用户信息
     */
    private User getUserByLoginName(String loginName){
        User user = new User();
        if(ValidateUtils.isEmail(loginName)){
            user.setEmail(loginName);
        }else if(ValidateUtils.isMobile(loginName)){
            user.setMobile(loginName);
        }else{
            user.setUserName(loginName);
        }
        return userDao.queryUserByLogin(user);
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * @ 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            session.setAttribute(key, value);
        }
    }
}