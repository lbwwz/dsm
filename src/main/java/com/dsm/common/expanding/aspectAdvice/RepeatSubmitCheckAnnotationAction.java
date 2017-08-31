package com.dsm.common.expanding.aspectAdvice;

import com.dsm.common.DsmConcepts;
import com.dsm.common.cache.cacheService.IRedisService;
import com.dsm.common.expanding.aspectAdvice.utils.JoinPointUtils;
import com.dsm.controller.common.RequestResponseContext;
import com.dsm.model.BackMsg;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/8/21
 *
 * @author : Lbwwz
 *         <p>
 *         校验重复提交的注解的具体执行操作类
 *         </p>
 */
@Aspect
@Component
public class RepeatSubmitCheckAnnotationAction {


    @Resource
    private IRedisService redisService;

    //重复校验异常跳转的页面url
    private String errorPageUrl;

    public void setErrorPageUrl(String errorPageUrl) {
        this.errorPageUrl = errorPageUrl;
    }

    public String getErrorPageUrl() {
        return errorPageUrl;
    }

    /**
     * 对重复提交校验注解的操作
     *
     * @param pjp 连接点
     */
    @Around("@annotation(com.dsm.common.annotation.RepeatSubmitCheck)")
    public Object around(ProceedingJoinPoint pjp) {
        Object retVal = null;
        try {
            boolean setCheckFlag = true;
            HttpServletRequest request = RequestResponseContext.getRequest();
            //只对@Controller注解标注的控制器内的方法进行处理
            if (pjp.getSignature().getDeclaringType().getAnnotation(Controller.class) != null) {
                //重复提交校验
                try {
                    retVal = doRepeatSubmitCheck(pjp, request);
                    if (!Optional.empty().equals(retVal)) { //校验通过，执行后续操作
                        return retVal;
                    }
                } catch (Exception ex) {
                    //校验操作异常，这里不做标记更新处理
                    setCheckFlag = false;
                }
            }
            retVal = pjp.proceed();
            if (setCheckFlag) {
                //操作成功：这里做重复提交标记。标记前n秒是否有提交操作
                redisService.set(request.getSession().getId() + "_repeatCheck", "1", 3);
            }
        } catch (Throwable throwable) {
            //controller方法中出现异常处理
            throwable.printStackTrace();
        }
        return retVal;
    }

    /**
     * 重复性校验操作的返回信息
     *
     * @return 返回的信息：如果为Optional.empty()，表示校验通过
     */
    private Object doRepeatSubmitCheck(ProceedingJoinPoint pjp, HttpServletRequest request) {
        //校验重复提交，若重复
        if (StringUtils.isNoneBlank(redisService.get(request.getSession().getId() + "_repeatCheck"))) {
            Method method = JoinPointUtils.getMethod(pjp);
            assert method != null;
            Class<?> clazz = method.getReturnType();
            if (request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {//如果请求是ajax请求
                //这里只处理三种情况
                if (clazz.equals(BackMsg.class)) {
                    return new BackMsg<>(DsmConcepts.WARRING, null, "请勿重复提交！");
                } else if (clazz.equals(String.class)) {
                    return "warning:请勿重复提交！";
                } else {
                    //pojo对象（将用于son化为字符串）
                    try {
                        return clazz.newInstance();
                    } catch (Exception ex) {
                        return null;
                    }
                }
            } else { // 非ajax请求
                //这里处理两种请情况
                errorPageUrl = StringUtils.isBlank(errorPageUrl) ? "/error" : errorPageUrl;
                if (clazz.equals(ModelAndView.class)) { //返回值为view模型
                    ModelAndView mv = new ModelAndView();
                    mv.setViewName(errorPageUrl);
                    return mv;
                } else if (clazz.equals(String.class)) { //返回值为String路径
                    errorPageUrl = StringUtils.isBlank(errorPageUrl) ? "/error" : errorPageUrl;
                    return errorPageUrl;
                }
            }
        }
        return Optional.empty();
    }


//    @Pointcut("execution(* com.dsm.controller..*(..))")
//    public void requestValidate() {
//    }

}

