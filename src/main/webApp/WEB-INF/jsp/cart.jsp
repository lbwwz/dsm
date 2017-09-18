<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>D.S Mall主页</title>
    <!--  Bootstrap 核心 CSS 文件 -->
    <link href="${webRoot}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${webRoot}/css/non-responsive.css" rel="stylesheet">
    <link href="${webRoot}/css/style_base.css" rel="stylesheet">
    <script src="${webRoot}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${webRoot}/js/bootstrap.min.js" type="text/javascript"></script>
    <jsp:include page="common/commonPath.jsp"/>
</head>

<body>
<jsp:include page="common/topNav.jsp" />
<div class="container">
    <div class="row">
        <div class="col-xs-5"><a href="index.jsp"><img src="images/logo.png"/></a></div>
        <div  class="col-xs-7">
            <div id="search_box">
                <form class="form-inline" style="margin:0;">
                    <div class="input-group col-xs-12">
                        <input id="search" name="search" class="form-control input-md" type="search" placeholder="商品搜索" />
                    <span id="search_btn" class="input-group-btn">
                    <button class="btn btn-info btn-md" type="button">搜索</button>
                    </span> </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!--购物车页面 -->
<div class="container cart_panel">
</div>
</body>

<script src="${rsRoot}/front-lib/layer/layer.js"></script>
<script src="${rsRoot}/js/cart.js"></script>
</html>