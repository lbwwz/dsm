package com.dsm.common.filter;

import com.dsm.controller.common.RequestResponseContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lbwwz on 2016/8/8.
 * <p/>
 *
 */
public class HttpContextFilter extends OncePerRequestFilter {

    private String XSS_exclude;  //不需要过滤的路径集合
    private Pattern pattern;  //匹配不需要过滤路径的正则表达式

    public String getXSS_exclude() {
        return XSS_exclude;
    }

    public void setXSS_exclude(String exclude) {
        this.XSS_exclude = exclude;
        pattern = Pattern.compile(getRegStr(exclude));
    }

    /**
     * 使用 RequestResponseContext
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        RequestResponseContext.setResponse(response);
        String requestURI = request.getRequestURI();
        if (StringUtils.isNotBlank(requestURI))
            requestURI = requestURI.replace(request.getContextPath(), "");


        HttpServletRequest httpServletRequest =
                pattern.matcher(requestURI).matches()?request:new EscapeScriptWrapper(request);

        RequestResponseContext.setRequest(httpServletRequest);
        RequestResponseContext.setResponse(response);
        filterChain.doFilter(httpServletRequest, response);
        RequestResponseContext.removeRequest();
        RequestResponseContext.removeResponse();
    }

    /**
     * 将传递进来的不需要过滤得路径集合的字符串格式化成一系列的正则规则
     *
     * @param str 不需要过滤的路径集合
     * @return 正则表达式规则
     */
    private String getRegStr(String str) {
        if (StringUtils.isNotBlank(str)) {
            String[] excludes = str.split(";");  //以分号进行分割
            int length = excludes.length;
            for (int i = 0; i < length; i++) {
                String tmpExclude = excludes[i];
                //对点、反斜杠和星号进行转义
                tmpExclude = tmpExclude.replace("\\", "\\\\").replace(".", "\\.").replace("*", ".*");

                tmpExclude = "^" + tmpExclude + "$";
                excludes[i] = tmpExclude;
            }
            return StringUtils.join(excludes, "|");
        }
        return str;
    }



    /**
     * 继承HttpServletRequestWrapper，创建装饰类，以达到修改HttpServletRequest参数的目的
     */
    private class EscapeScriptWrapper extends HttpServletRequestWrapper {
        private Map<String, String[]> parameterMap;  //所有参数的Map集合

        public EscapeScriptWrapper(HttpServletRequest request) {
            super(request);
            parameterMap = request.getParameterMap();
        }

        //重写几个HttpServletRequestWrapper中的方法

        /**
         * 获取所有参数名
         *
         * @return 返回所有参数名
         */
        @Override
        public Enumeration<String> getParameterNames() {
            Vector<String> vector = new Vector<>(parameterMap.keySet());
            return vector.elements();
        }

        /**
         * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值
         * 接收一般变量 ，如text类型
         *
         * @param name 指定参数名
         * @return 指定参数名的值
         */
        @Override
        public String getParameter(String name) {
            String[] results = parameterMap.get(name);
            if (results == null || results.length <= 0)
                return null;
            else {
                return escapeXSS(results[0]);
            }
        }

        /**
         * 获取指定参数名的所有值的数组，如：checkbox的所有数据
         * 接收数组变量 ，如checkobx类型
         */
        @Override
        public String[] getParameterValues(String name) {
            String[] results = parameterMap.get(name);
            if (results == null || results.length <= 0)
                return null;
            else {
                int length = results.length;
                for (int i = 0; i < length; i++) {
                    results[i] = escapeXSS(results[i]);
                }
                return results;
            }
        }

        /**
         * 过滤字符串中的js脚本
         * 解码 htmlEncode(escapedStr)
         */
        private String escapeXSS(String str) {
            str = htmlEncode(str);

            Pattern tmpPattern = Pattern.compile("[sS][cC][rR][iI][pP][tT]");
            Matcher tmpMatcher = tmpPattern.matcher(str);
            if (tmpMatcher.find()) {
                str = tmpMatcher.replaceAll(tmpMatcher.group(0) + "\\\\");
            }
            return str;
        }

        private String htmlEncode(char c) {
            switch (c) {
                case '&':
                    return "&amp;";
                case '<':
                    return "&lt;";
                case '>':
                    return "&gt;";
                case '"':
                    return "&quot;";
                case ' ':
                    return "&nbsp;";
                default:
                    return c + "";
            }
        }

        /**
         * 对传入的字符串str进行Html encode转换
         */
        public String htmlEncode(String str) {
            if (str == null || str.trim().equals("")) return str;
            StringBuilder encodeStrBuilder = new StringBuilder();
            for (int i = 0, len = str.length(); i < len; i++) {
                encodeStrBuilder.append(htmlEncode(str.charAt(i)));
            }
            return encodeStrBuilder.toString();
        }
    }


}
