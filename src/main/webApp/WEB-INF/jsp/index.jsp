<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/commonPath.jsp"/>
<!DOCTYPE html>
<html lang="zh">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>D.S Mall主页</title>
    <!--  Bootstrap 核心 CSS 文件 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/non-responsive.css" rel="stylesheet">

    <link href="css/style_base.css" rel="stylesheet">
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>

</head>

<body>
<jsp:include page="common/topNav.jsp" />
<div class="container">
    <div class="row">
        <div class="col-xs-5"><a href="index.jsp"><img src="images/logo.png"/></a></div>
        <div id="search_box" class="col-xs-7">
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
<div class="container category">
    <div class="row">
        <ul class="nav nav-tabs">
            <li class="pitch_on"><a href="#">首页</a></li>
            <li><a href="${webRoot}/list.html?cat=14">手机</a></li>
            <li><a href="#">单反相机</a></li>
            <li><a href="#">个人PC</a></li>
            <li><a href="#">智能3C</a></li>
            <li><a href="#">智能家电</a></li>
        </ul>
    </div>
</div>
<div class="detail_category">
    <div class="container ">
        <div class="row">
            <div class="col-xs-2 detail_list">
                <dl>
                    <dd>手机</dd>
                    <dt> <a href="#">三星</a><a href="#">华为</a><a href="#">苹果</a><a href="#">手机</a><a href="#">手机</a><a href="#">手机</a> </dt>
                </dl>
            </div>
            <div class="col-xs-2 detail_list">
                <dl>
                    <dd>平板电脑</dd>
                    <dt> <a href="#">ipad</a><a href="#">手机</a><a href="#">蓝魔</a><a href="#">手机</a><a href="#">手机</a><a href="#">手机</a> </dt>

                </dl>
            </div>
            <div class="col-xs-2 detail_list">
                <dl>
                    <dd>相机/单反</dd>
                    <dt> <a href="#">尼康</a><a href="#">宾得</a><a href="#">佳能</a><a href="#">手机</a><a href="#">手机</a><a href="#">手机</a> </dt>
                </dl>
            </div>
            <div class="col-xs-2 detail_list">
                <dl>
                    <dd>笔记本</dd>
                    <dt> <a href="#">苹果</a><a href="#">thinkpad</a><a href="#">手机</a><a href="#">戴尔</a><a href="#">手机</a><a href="#">手机</a> </dt>
                </dl>
            </div>
            <div class="col-xs-2 detail_list">
                <dl>
                    <dd>台式机</dd>
                    <dt> <a href="#">手机</a><a href="#">联想</a><a href="#">手机</a><a href="#">手机</a><a href="#">手机</a><a href="#">手机</a> </dt>
                </dl>
            </div>
            <div class="col-xs-2 detail_list dl_last">
                <dl >
                    <dd>数码配件</dd>
                    <dt> <a href="#">耳机</a><a href="#">手机</a><a href="#">手机</a><a href="#">手机</a><a href="#">手机</a><a href="#">手机</a> </dt>
                </dl>
            </div>
        </div>
    </div>
</div>

<!--广告 -->
<div class="container ads_region">
    <div class="row">
        <div class="col-xs-6 no_padding " id="content_1">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active"> <a href="#"> <img src="images/1.jpg" alt="..."> </a> </div>
                    <div class="item"> <a href="#"> <img src="images/2.jpg" alt="..."> </a> </div>
                    <div class="item"> <a href="#"> <img src="images/3.jpg" alt="..."> </a> </div>
                    <div class="item"> <a href="#"> <img src="images/4.jpg" alt="..."> </a> </div>
                    <div class="item"> <a href="#"> <img src="images/5.jpg" alt="..."> </a> </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span> </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <div class="col-xs-6 no_padding " id="content_2" >
            <div class="col-xs-4 no_padding">
                <a href="#"> <img src="images/7.jpg" alt="..."> </a>
            </div>
            <div class="col-xs-4 no_padding "><a href="#"> <img src="images/8.jpg" alt="..."> </a></div>
            <div class="col-xs-4 no_padding"><a href="#"> <img src="images/6.jpg" alt="..."> </a></div>
        </div>
    </div>
</div>
</body>
</html>