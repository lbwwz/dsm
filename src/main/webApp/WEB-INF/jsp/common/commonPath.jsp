<%--
  Created by IntelliJ IDEA.
  User: Lbwwz
  Date: 2016/8/2
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //定义web项目的根路径
    request.setAttribute("webRoot",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());

    //定义资源文件根路径
    request.setAttribute("rsRoot",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/resources");
%>
<script type="text/javascript">
    var webRoot="${webRoot}";
    var rsRoot="${rsRoot}";
</script>
<script src="${webRoot}/js/basic.js" type="text/javascript"></script>

