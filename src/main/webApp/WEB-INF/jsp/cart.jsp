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
    <c:if test="${!empty cartInfo}">
    <%--信息头--%>
    <div class="row cart_header">
        <div class="col-xs-12">
            <ul class="cart-inline">
                <li class="dsm_chk">
                    <label id="cart_selectAll_1" class="cart_label cart_checkBox" attr_checked="<c:if test="${cartInfo.selectedAll}">1</c:if><c:if test="${!cartInfo.selectedAll}">0</c:if>" style="margin-top: -3px;">
                        <span class="cart_checkbox_span cart_radioInput <c:if test="${cartInfo.selectedAll}">glyphicon glyphicon-ok</c:if>"></span>
                    </label>
                </li>
                <li class="dsm_item">
                    全选<span style="padding-left:65px">商品信息</span>
                </li>
                <li class="dsm_info">&nbsp;</li>
                <li class="dsm_price">
                    <span style="padding-left:40px">单价</span>
                </li>
                <li class="dsm_amount">
                    <span style="padding-left:40px">数量</span>
                </li>
                <li class="dsm_sum">
                    <span style="padding-left:20px">金额</span>
                </li>
                <li class="dsm_op">
                    <span style="padding-left:20px">操作</span>
                </li>
                <div class="clearfix"></div>
            </ul>
        </div>
    </div>
    <%--信息主体--%>
    <c:forEach items="${cartInfo.cartPackages}" var="cartPackage">
    <div class="row cart_list" data_shopId="${cartPackage.shopId}">
        <div class="col-xs-12">
            <div class="cartItem_header">
                <div style="width: 50px;float: left;">
                    <label class="cart_label cart_checkBox" attr_checked="<c:if test="${cartPackage.isSelected }">1</c:if><c:if test="${!cartPackage.isSelected}">0</c:if>" style="margin-top: -3px;">
                        <span class="cart_checkbox_span cart_radioInput <c:if test="${cartPackage.isSelected}">glyphicon glyphicon-ok</c:if>"></span>
                    </label>
                </div>
                <span class="ct_shop_name"><font>店铺：</font><a href="#" attr_id="${cartPackage.shopId}">${cartPackage.shopName}</a></span>
            </div>


            <div class="cartItem_body" >
                <c:forEach items="${cartPackage.cartItems}" var="cartItem">
                <div class="item_content <c:if test="${cartItem.isSelected == 1}">item_content_selected</c:if>" data_skuId="${cartItem.skuId}">
                    <ul class="cart-inline">
                        <li class="dsm_chk">
                            <div class="div_inner">
                                <label class="cart_label cart_checkBox" attr_checked="<c:if test="${cartItem.isSelected == 1}">1</c:if><c:if test="${cartItem.isSelected != 1}">0</c:if>">
                                    <span class="cart_checkbox_span cart_radioInput <c:if test="${cartItem.isSelected == 1}">glyphicon glyphicon-ok</c:if>"></span>
                                </label>
                            </div>
                        </li>
                        <li class="dsm_item">
                            <div class="div_inner">
                                <div class="pull-left ct_pic">
                                    <img src="${cartItem.mainImage}" width="80" height="80"/>
                                </div>
                                <div class="ct_item_content">
                                    <div class="ct_name"><a>${cartItem.productName}</a></div>
                                    <div class="ct_tips">
                                        <span class="glyphicon glyphicon-align-center"></span>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </li>
                        <li class="dsm_info">
                            <div class="div_inner">
                                <div class="ct_sku_content">
                                    <c:forEach items="${fn:split(cartItem.propertiesName, ';')}" var="skuItem">
                                        <c:set var="temp" value="${fn:split(skuItem, '|')}"/>
                                        <p data_attr="${temp[0]}:${temp[1]}">${temp[2]}：${temp[3]}</p>
                                    </c:forEach>
                                </div>
                            </div>
                        </li>
                        <li class="dsm_price">
                            <div class="div_inner">
                                <div class="ct_price_content">
                                    <div class="price_line"><em class="price_original">￥105.00</em></div>
                                    <div class="price_line"><em class=" price_now" tabindex="0">￥${cartItem.skuPrice}</em></div>
                                </div>
                            </div>
                        </li>
                        <li class="dsm_amount">
                            <div class="div_inner">
                                <div class="ct_amount_wrapper ">
                                    <div class="item_amount ">
                                        <a href="javascript:;" class="ct_minus" >-</a>
                                        <input type="text" value="${cartItem.cartItemNum}" class="text_amount" data_max="${cartItem.quantity}"  data_num="${cartItem.cartItemNum}" >
                                        <a href="javascript:;" class="ct_plus">+</a></div>
                                    <div class="amount_msg">剩余<font>${cartItem.quantity}</font>件</div>
                                </div>
                            </div>
                        </li>
                        <li class="dsm_sum"><div class="div_inner"><em tabindex="0" class="item_sum_number">￥${cartItem.skuPrice*cartItem.cartItemNum}</em></div></li>
                        <li class="dsm_op">
                            <div class="div_inner">
                                <a href="#">收藏商品</a>
                                <a href="javascript:;" class="deleteCartItem">删除</a>
                            </div>
                        </li>
                        <div class="clearfix"></div>
                    </ul>
                </div>
                </c:forEach>
            </div>

        </div>
    </div>
    </c:forEach>

    <style>

    </style>
    <div class="cart_footer">
        <div class="float_bar_wrapper">
            <div  class="select-all J_SelectAll pull-left">
                <label id="cart_selectAll_2 " class="cart_label cart_checkBox"
                       attr_checked="<c:if test="${cartInfo.selectedAll}">1</c:if><c:if test="${!cartInfo.selectedAll}">0</c:if>" style="margin-top: -3px;">
                    <span class="cart_checkbox_span cart_radioInput <c:if test="${cartInfo.selectedAll}">glyphicon glyphicon-ok</c:if>"></span>
                </label>
                &nbsp;全选
            </div>
            <div class="operations pull-left">
                <a href="javascript:;" id="cleanCartAll" class="ct_deleteSelected">删除</a>
                <a href="#" hidefocus="true"  class="ct_clearInvalid hidden">清除失效宝贝</a>
                <a href="#" hidefocus="true" class="ct_batchFav">移入收藏夹</a>
                <a href="#" hidefocus="true" class="ct_batchShare">分享</a>
            </div>

            <div class="float_bar_right">
                <div id="ct_showSelectedItems " class="amount_sum pull-left">
                    <span class="txt">已选商品</span>
                    <em id="J_SelectedItemsCount">${cartInfo.selectTotalNum}</em>
                    <span class="txt">件</span>
                    <div class="arrow-box"><span class="selected-items-arrow"></span><span class="arrow"></span></div>
                </div>
                <div id="ct_checkCOD pull-left" class="check_cod" style="display: none;">
                    <span class="s-checkbox J_CheckCOD"></span>货到付款
                </div>
                <div class="ct_pipe pull-left"></div>
                <div class="ct_price_sum pull-left"><span class="txt">合计（不含运费）：</span>
                    <strong class="price"><em ><span class="total_symbol">￥&nbsp;</span><font id="totalPrice">${cartInfo.totalPrice}</font></em></strong>
                </div>
                <div class="btn_area pull-right"><a href="javascript:void(0)" id="cartToOrderCheck" class="submit_btn <c:if test="${cartInfo.selectTotalNum<1}">btn_no</c:if>"><span>结&nbsp;算</span><b></b></a></div>
            </div>
        </div>
    </div>
    </c:if>
</div>
</body>

<script src="${rsRoot}/front-lib/layer/layer.js"></script>
<script src="${rsRoot}/js/cart.js"></script>
</html>