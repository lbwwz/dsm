package com.dsm.controller;


import com.dsm.dao.IUserDao;
import com.dsm.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by lbw on 2016/7/19.
 */
@Controller
@RequestMapping("hehe")
public class HelloController {

    @Autowired
    private IUserDao userDao;

    @RequestMapping("/getMyPage")
    public ModelAndView getMyPage(){


        User user = userDao.getUserById(25);
        System.out.println(user);
        return new ModelAndView(InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/");
    }
}
