<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
    <style>
    </style>
</head>

<body>
<jsp:include page="common/topNav.jsp" />
<div class="container">
    <div class="row">
        <div class="col-xs-5"><a href="index.jsp"><img src="${webRoot}/images/logo.png"/></a></div>
        <div id="search_box" class="col-xs-7">
            <form class="form-inline" style="margin:0;">
                <div class="input-group col-xs-12">
                    <input id="search" name="search" class="form-control input-md" type="search" placeholder="商品搜索" />
                    <span id="search_btn" class="input-group-btn">
                    <button class="btn btn-info btn-md" type="button">搜索</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container category cat_productList">
    <div class="row">
        <ul class="nav nav-tabs">
            <li class="pitch_on"><a href="#">全部分类</a></li>
            <li><a href="#">手机</a></li>
            <li><a href="#">单反相机</a></li>
            <li><a href="#">个人PC</a></li>
            <li><a href="#">智能3C</a></li>
            <li><a href="#">智能家电</a></li>
        </ul>
    </div>
</div>
<!--分类列表-->
<div class="container ">
    <%--${catNavList}--%>

    <div class="row catNavList">
        <ul class="list-inline">
            <li><a href="javascript:;">全部</a></li><i>&nbsp;></i>
            <c:forEach items="${catNavList}" varStatus="i">
                <li><a href="javascript:;" class="catNavBox">${catNavList[i.index].catName}</a><c:if test="${!i.last }"></li><i>&nbsp;></i></c:if>
            </c:forEach>
            <c:if test="${empty catNavList}">
                <li>当前类目不存在</li>
            </c:if>

        </ul>
    </div>
    <div class="row">
        <ul class="select col-xs-12">
            <%--<c:set var="saleIndex" value="0" />--%>
            <c:forEach items="${selectAttrList}" var="attrItem">
                <li class="select_list">
                    <dl id="select_${attrItem.attrId}">
                        <dt>${attrItem.attrName}：</dt>

                        <dd class="select_all selected"><a href="javascript:;">全部</a></dd>
                        <c:forEach items="${attrItem.attrValues}" var="valueItem">
                            <dd attr_id="${attrItem.attrId}" value_id="${valueItem.valueId}"><a href="javascript:;">${valueItem.valueName}</a></dd>
                        </c:forEach>
                    </dl>
                </li>
                <%--<c:set var="saleIndex" value="${saleIndex+1}" />--%>
            </c:forEach>
            <li class="select_result">
                <dl>
                    <dt>已选条件：</dt>
                    <c:if test="${ selectedAttrList==null || fn:length(selectedAttrList) == 0}">
                        <dd class="select_no">暂时没有选择过滤条件</dd>
                    </c:if>
                    <c:forEach items="${selectedAttrList}" var="attrItem">
                        <dd attr_id="${attrItem.attrId}" value_id="${attrItem.valueId}" class="selected"><a href="javascript:;">${attrItem.attrName}：${attrItem.attrValue}</a></dd>
                    </c:forEach>
                </dl>
            </li>
        </ul>
    </div>

    <div class="row option_nav">
            <ul class="nav navbar-nav  sort_nav">
                <li <c:if test="${sortType == 0}">class="active"</c:if>>
                    <a href="javascript:;" class="sortButton" type_data="0">综合 </a>
                </li>
                <li <c:if test="${sortType == 1}">class="active"</c:if>>
                    <a href="javascript:;" class="sortButton" type_data="1">人气${sortType == 1?"由高到低":""}</a>
                </li>
                <li <c:if test="${sortType == 2}">class="active"</c:if>>
                    <a href="javascript:;" class="sortButton" type_data="2">销量${sortType == 2?"由高到低":""}</a>
                </li>
                <li <c:if test="${sortType == 5}">class="active"</c:if>>
                    <a href="javascript:;" class="sortButton" type_data="5">信用${sortType == 5?"由高到低":""}</a>
                </li>
                <li  class="<c:if test="${sortType == 3 ||sortType == 4}">active</c:if> dropdown" id="sort_price">
                    <a href="javascript:;" >价格<c:if test="${sortType == 3 ||sortType == 4}">${sortType == 3?"由低到高":"由高到低"}</c:if><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:;" class="sortButton" type_data="3">价格由低到高</a></li>
                        <li><a href="javascript:;" class="sortButton" type_data="4">价格由高到低</a></li>
                    </ul>
                </li>

            </ul>
            <ul class="nav navbar-nav pull-right">
                <li style="float:right"><a href="javascript:;">信用</a></li>
            </ul>
            <%--<form class="navbar-form navbar-left" role="search">--%>
                <%--<div class="form-group">--%>
                    <%--<input type="text" class="form-control" placeholder="Search">--%>
                <%--</div>--%>
                <%--<button type="submit" class="btn btn-default">Submit</button>--%>
            <%--</form>--%>
    </div>
    <div class="row productList_panel">
        <c:forEach items="${productList}" var="productItem">
        <div class="item_box" >
            <a href="${webRoot}/item.html?id=${productItem.productId}" target="_blank">
            <img class="item_image"  src="${productItem.mainImage}"  alt="${productItem.productName}">
            </a>
            <div class="item_info">
                <p class="item_sale">￥<strong>${sortType==4?productItem.maxPrice:productItem.minPrice}</strong><font style="font-size:12px;float:right">123人付款</font></p>
                <div class="item_name"><a href="javascript:;">${productItem.productName}</a></div>

                <div class="item_shop">
                    <span class="dsrs">
                          <span class="dsr morethan"></span>
                          <span class="dsr morethan"></span>
                          <span class="dsr morethan"></span>
                    </span>
                    <a href="javascript:;">${productItem.shopName}</a>
                    <font style="font-size:12px;float:right">评价：<a href="javascript:;">23</a></font>
                </div>

            </div>
        </div>
        </c:forEach>

        <%--商品分页--%>
        <div class="col-xs-12 text-center pageIndex_ul">
            <ul class="pagination">
                <li id="prevPage">
                    <a href="#">< 上一页</a>
                </li>

                <%--<c:if test="${totalPage ge 5}">--%>
                    <%----%>
                <%--</c:if>--%>
                <%--<c:if test="${totalPage ge 5}">--%>
                <li class="active">
                    <a href="#">1</a>
                </li>
                <li>
                    <a href="#">2</a>
                </li>
                <li>
                    <a href="#">3</a>
                </li>
                <li>
                    <a href="#">4</a>
                </li>
                <c:if test="${totalPage gt 5}">
                    <li>
                        <i class="" style="border-width:0;padding: 7px 12px 6px;line-height: 1.42857143; float:left">···</i>
                    </li>
                </c:if>
                <li>
                    <a href="#">5</a>
                </li>
                <%--</c:if>--%>
                <li id="nextPage">
                    <a href="#">下一页 ></a>
                </li>
            </ul>
        </div>
        </div>



    <footer class="footer-style">
        <div class="row">
            <div class="col-lg-12">
                <p class="text-center">Copyright © D.S Mall 2015</p>
            </div>
        </div>
    </footer>

</div>

</body>
</html>
<script type="text/javascript">

    $(document).ready(function(){

        /* 类目下的条件筛选和排序操作 */
        $("[id^='select_'] dd").add(".selected").add(".sortButton").click(function () {
            var url = window.location.href;
            var param;
            if($(this).attr("class") == "sortButton"){  //条件排序
                param = "sort="+$(this).attr("type_data");
                if(url.split("sort=").length==1){
                    window.location.href = url+"&"+param;
                }else{
                    window.location.href = url.split("sort=")[0]+param+url.split("sort=")[1].substring(1);
                }
                //操作排序内容

            }else if($(this).attr("class") == "selected"){
                param = $(this).attr("attr_id")+"_"+$(this).attr("value_id");

                if(url.indexOf(param+"%40")!=-1){
                    window.location.href = url.split(param+"%40").join("");
                }else{
                    window.location.href = url.split(param).join("");
                }
            }else{      //属性筛选（增加）
                param = "ev="+$(this).attr("attr_id")+"_"+$(this).attr("value_id");
                if(url.split("ev=").length==1){
                    window.location.href = url+"&"+param;
                }else{
                    window.location.href = url.split("ev=").join("ev="+$(this).attr("attr_id")+"_"+$(this).attr("value_id")+"%40")
                }
            }
        });


        //价格排序的tab
        $('#sort_price ').hover(
            function () {
                $(this).addClass("open")
            },
            function () {
                $(this).removeClass("open")
            }
        );

    });

    /**
     * 操纵本地链接
     * @param type 0:删除参数；1：添加参数
     * @param param 参数：key=value形式
     */
    function doLocalHref(type,param){
        if(type == 0){

        }else{

        }

    }


    showSideNav();
</script>