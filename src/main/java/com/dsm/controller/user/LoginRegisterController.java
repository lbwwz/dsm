package com.dsm.controller.user;

import com.dsm.common.DsmConcepts;
import com.dsm.common.utils.EncryptUtils;
import com.dsm.common.utils.ServletToolUtils;
import com.dsm.common.utils.ValidateUtils;
import com.dsm.common.utils.VerifyCodeUtils;
import com.dsm.controller.common.BaseController;
import com.dsm.model.BackMsg;
import com.dsm.model.formData.UserRegisterDTO;
import com.dsm.service.interfaces.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Lbw on 2016/8/15.
 * <p>
 * 登录注册操作的controller
 */
@Controller
public class LoginRegisterController extends BaseController {

    @Autowired
    private IUserService userService;


    /**
     * 请求用户的登录页面
     *
     * @return 登录页视图
     */
    @RequestMapping("showLogin")
    public ModelAndView showLogin() {
        ModelAndView view = new ModelAndView();

        //设置图片验证码
        view.addObject("VerifyCodeImage", getVerifyCodeImage());

        view.setViewName("/login");
        return view;
    }

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     */
    @ResponseBody
    @RequestMapping("/getVerifyCodeImage")
    public String getVerifyCodeImage() {
        //设置页面不缓存
        HttpServletResponse response = getResponse();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtils.generateTextCode(VerifyCodeUtils.TYPE_ALL_MIXED, 4, null);
        //将验证码放到HttpSession里面
        getSession().setAttribute("verifyCode", verifyCode);
        System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtils.generateImageCode(verifyCode, 90, 30, 5, true, Color.WHITE, Color.BLACK, null);
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        //写给浏览器
        try {
            ImageIO.write(bufferedImage, "jpg", b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = b.toByteArray();
        //拼接base64字符串信息
        return "data:image/*;base64," + EncryptUtils.encodeBase64(bytes).trim();
    }

    /**
     * 用户的登录操作
     *
     * @param loginName  用户登录名称
     * @param password   登录密码
     * @param isRemember 是否记住状态
     * @return {@link BackMsg}
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BackMsg login(String loginName, String password, @RequestParam(value = "isRemember", defaultValue = "0") String isRemember[]) {

        String lastAccessUrl = (String) getSession().getAttribute(DsmConcepts.LAST_ACCESS_URL);
        lastAccessUrl = lastAccessUrl == null ? getWebRoot() : lastAccessUrl;
        BackMsg backMsg;
        //校验是否已经登录
        if (getSession().getAttribute("user") == null) {
//        //获取HttpSession中的验证码
//        String verifyCode = (String)getSession().getAttribute("verifyCode");
//        //获取用户请求表单中输入的验证码
//        String submitCode = WebUtils.getCleanParam(request, "verifyCode");

            backMsg = userService.userLogin(loginName, password, !isRemember[0].equals("0"));
        } else {
            //用户已经登录过了
            backMsg = new BackMsg(2, "", "您已经登录过了");
        }

        if (backMsg.getError() == 0) {
            backMsg.setData(lastAccessUrl);
        }

        return backMsg;
    }

    /**
     * 请求用户的注册页面
     *
     * @return 注册页视图
     */
    @RequestMapping("showRegister")
    public ModelAndView showRegister() {
        ModelAndView view = new ModelAndView();

        view.setViewName("/register");
        return view;
    }

    /**
     * 用户注册
     *
     * @param userRegisterDTO 注册用户的封装
     * @return {@link BackMsg}
     */
    @ResponseBody
    @RequestMapping("register")
    public BackMsg register(UserRegisterDTO userRegisterDTO) {

        BackMsg backMsg;
        // 检查用户提交的信息是否合法
        backMsg = checkRegisterInfo(userRegisterDTO);
        if (backMsg.getError() == BackMsg.CORRECT) {// 验证通过
            // 校验重复提交
            if (ServletToolUtils.checkRepeatSubmit(getRequest(), "registerToken", "registerToken")) {
                // 密码加密
                userRegisterDTO.setPassword(EncryptUtils.encryptMD5(userRegisterDTO.getPassword()));
                // 提交注册信息
                String errorMsg = userService.register(userRegisterDTO);
                if (errorMsg == null)
                    backMsg = new BackMsg(BackMsg.CORRECT, "regSuccess", "注册用户成功");
                else
                    backMsg = new BackMsg(BackMsg.ERROR, null, errorMsg);
            } else {
                //表单重复提交的返回
                backMsg = new BackMsg(1, null, "");
            }
        }
        return backMsg;
    }

    /**
     * 请求跳转注册成功页
     *
     * @return /regSuccess.jsp
     */
    @RequestMapping("regSuccess")
    public String register() {
        return "/regSuccess";
    }

    /**
     * 检查用户注册的信息是否合法，并给出相应的提示信息
     *
     * @param userInfo 注册信息的封装实体
     * @return {@link BackMsg}
     */
    private BackMsg checkRegisterInfo(UserRegisterDTO userInfo) {

        String userName = userInfo.getUserName();
        String email = userInfo.getEmail();
        String password = userInfo.getPassword();
        String confirmPassword = userInfo.getConfirmPassword();
        String mobile = userInfo.getMobile();

        BackMsg backMsg = new BackMsg();
        // 检查必填元素是否为空
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(confirmPassword) || StringUtils.isEmpty(email)) {
            backMsg.set(BackMsg.ERROR, "error_mainInfo", "用户名，密码和邮箱不能为空！");
        }
        //检查设置的密码和确认密码是否相同
        else if (!confirmPassword.equals(password)) {
            backMsg.set(BackMsg.ERROR, "error_confirmPassword", "确认密码和密码不一致！");
        }
        // 检查选填信息是否合法
        else if (!ValidateUtils.isEmail(email)) {
            backMsg.set(BackMsg.ERROR, "error_email", "请输入正确的邮件格式！");
        }
//        else if (!ValidateUtils.isRightfulPassword(password)) {
//            backMsg.set(BackMsg.WARNING, "error_passwordFormat", "请设置密码为8-16位的数字字母组合！");
//        }
        else if (!StringUtils.isEmpty(mobile) && !ValidateUtils.isMobile(mobile)) {
            backMsg.set(BackMsg.WARNING, "error_mobileFormat", "请输入正确的手机号码！");
        } else {
            //注册成功。跳转登录页面
            backMsg.set(BackMsg.CORRECT, null, null);
        }
        return backMsg;
    }

    /**
     * 用户登出
     * @return 登出后跳转到首页
     */
    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
    }

}
