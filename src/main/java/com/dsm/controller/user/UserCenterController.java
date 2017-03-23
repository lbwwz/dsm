package com.dsm.controller.user;

import com.dsm.controller.common.BaseController;
import com.dsm.model.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/1/23
 *
 * @author : Lbwwz
 *         <p>
 *         用户中心的先关操作
 */
@Controller
@RequestMapping("user")
public class UserCenterController extends BaseController {

    /**
     * 进入我的店铺 (userCenter模块页面中“我的店铺”链接使用)
     */
    @RequestMapping("myShop")
    public String goToMyShop(Map<String,Object> m) {
        User sessionUser = (User)getSession().getAttribute("user");
        //判断用户是否已经开店
        if(sessionUser.getRole() == 0){
            //未开店铺
            return "/userHome/userCenter-applyShop";
        }else{
//已开设店铺
//          Shop shop = shopDao.getShopByUserId(sessionUser.getId());
//          request.getSession().setAttribute("sessionShop", shop);
            return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/user/seller/";
        }
    }
}
