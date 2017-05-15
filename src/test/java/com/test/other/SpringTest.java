package com.test.other;

import com.test.common.BaseJunitTest;
import org.junit.Test;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/11
 *
 * @author : Lbwwz
 */
public class SpringTest extends BaseJunitTest {

    @Test
    public void test(){
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

//        ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
//        ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(sc);
//        WebApplicationContext wac1 = (WebApplicationContext) sc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

        System.out.println(wac);
    }
}
