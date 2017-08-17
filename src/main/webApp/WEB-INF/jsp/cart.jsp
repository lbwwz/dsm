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
    <link href="${webRoot}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${webRoot}/css/non-responsive.css" rel="stylesheet">

    <link href="${webRoot}/css/style_base.css" rel="stylesheet">
    <script src="${webRoot}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${webRoot}/js/bootstrap.min.js" type="text/javascript"></script>

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
                    <label class="cart_label" attr_checked="0" style="margin-top: 3px;">
                        <span class="cart_checkbox cart_radioInput"></span>
                    </label>
                </li>
                <li class="dsm_item">
                    全选<span style="padding-left:60px">商品信息</span>
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
    <div class="row cart_list">
        <div class="col-xs-12">
            <div class="cart_item">
                <div class="cartItem_header">
                    <div style="width: 50px;float: left;">
                        <label class="cart_label" attr_checked="0" style="margin-top: 3px;">
                            <span class="cart_checkbox cart_radioInput"></span>
                        </label>
                    </div>
                    <span class="ct_shop_name"><font>店铺：</font><a href="#" attr_id="${cartPackage.shopId}">${cartPackage.shopName}</a></span>
                </div>


                <div class="cartItem_body">
                    <c:forEach items="${cartPackage.cartItems}" var="cartItem">
                    <div class="item_content">
                        <ul class="cart-inline">
                            <li class="dsm_chk">
                                <div class="div_inner">
                                    <label class="cart_label" attr_checked="0">
                                        <span class="cart_checkbox cart_radioInput"></span>
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
                                        <p>颜色分类：黄</p>
                                        <p>尺码：36</p>
                                    </div>
                                </div>
                            </li>
                            <li class="dsm_price">
                                <div class="div_inner">
                                    <div class="ct_price_content">
                                        <%--<div class="price_line"><em class="price_original">￥105.00</em></div>--%>
                                        <div class="price_line"><em class=" price_now" tabindex="0">￥${cartItem.skuPrice}</em></div>
                                    </div>
                                </div>
                            </li>
                            <li class="dsm_amount">
                                <div class="div_inner">
                                    <div class="ct_amount_wrapper ">
                                        <div class="item_amount ">
                                            <a href="javascript:;" class="ct_minus">-</a>
                                            <input type="text" value="${cartItem.cartItemNum}" class="text_amount" data-max="93"data-now="1" >
                                            <a href="javascript:;" class="ct_plus">+</a></div>
                                        <div class="amount-msg J_AmountMsg"></div>
                                    </div>
                                </div>
                            </li>
                            <li class="dsm_sum"><div class="div_inner"><em tabindex="0" class="Item_sum_number">￥${cartItem.skuPrice*cartItem.cartItemNum}</em></div></li>
                            <li class="dsm_op">
                                <div class="div_inner">
                                    <a href="#">收藏商品</a>
                                    <a href="#">删除</a>
                                </div>
                            </li>
                            <div class="clearfix"></div>
                        </ul>
                    </div>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>
    </c:forEach>

    <style>
        .cart_footer{height: 50px;overflow: hidden;  background: #e5e5e5; margin-top:20px;}
        .float_bar_wrapper{position: relative;line-height: 48px}
        .float_bar_right{float: right;position: relative; right: 0;top: 0;z-index: 4;padding-left: 20px;}

        .amount_sum,.check_cod,.ct_pipe,.ct_price_sum,.btn_area,.operations{
            padding-left:20px;}

        .operations a{padding:0 5px;}
        .float_bar_right .btn_area{position: relative;height: 50px;}
        .float_bar_right .btn_area .submit_btn{background: #5bc0de;color: #fff;display: inline-block;border-left: 1px solid #e7e7e7;width: 119px;height: 50px;
            text-align: center;font-family: 'Lantinghei SC','Microsoft Yahei';line-height: 50px;font-size: 20px;text-decoration: none;}
        .float_bar_right .btn_area .btn_no{background:#B0B0B0;cursor: not-allowed;}
        .float_bar_right .ct_price_sum .price{color: #f40;font-weight: 400;font-size: 18px;line-height: 48px;font-family: Arial;vertical-align: middle;}
        .float_bar_right .ct_price_sum .price em{font-weight: 700;font-size: 22px;padding: 0 3px;}
        .float_bar_right .ct_price_sum .price .total_symbol{font-weight: 400;font-size: 14px;font-family: verdana;}

    </style>
    <div class="cart_footer">
        <div class="float_bar_wrapper">
            <div id="J_SelectAll2 " class="select-all J_SelectAll pull-left">
                <label class="cart_label" attr_checked="0" style="margin-top: 3px;">
                    <span class="cart_checkbox cart_radioInput"></span>
                </label>
                &nbsp;全选
            </div>
            <div class="operations pull-left">
                <a href="#" hidefocus="true" class="ct_deleteSelected">删除</a>
                <a href="#" hidefocus="true"  class="ct_clearInvalid hidden">清除失效宝贝</a>
                <a href="#" hidefocus="true" class="ct_batchFav">移入收藏夹</a>
                <a href="#" hidefocus="true" class="ct_batchShare">分享</a>
            </div>

            <div class="float_bar_right">
                <div id="ct_showSelectedItems " class="amount_sum pull-left">
                    <span class="txt">已选商品</span>
                    <em id="J_SelectedItemsCount">0</em>
                    <span class="txt">件</span>
                    <div class="arrow-box"><span class="selected-items-arrow"></span><span class="arrow"></span></div>
                </div>
                <div id="ct_checkCOD pull-left" class="check_cod" style="display: none;">
                    <span class="s-checkbox J_CheckCOD"></span>货到付款
                </div>
                <div class="ct_pipe pull-left"></div>
                <div class="ct_price_sum pull-left"><span class="txt">合计（不含运费）：</span>
                    <strong class="price"><em ><span class="total_symbol">￥&nbsp;</span>${cartInfo.totalPrice}</em></strong>
                </div>
                <div class="btn_area pull-right"><a href="javascript:void(0)" class="submit_btn btn_no"><span>结&nbsp;算</span><b></b></a></div>
            </div>
        </div>

    </div>
    </c:if>
</div>
</body>
<script>
    $(function(){
        $(".dsm_chk").on("click",".cart_label",function(){
            if($(this).attr("attr_checked") == "1"){
                $(this).children(".cart_checkbox").removeClass("glyphicon glyphicon-ok");
                $(this).parents(".item_content").removeClass("item_content_selected");
                $(this).parents(".cart_item").children(".item_content");
                $(this).attr("attr_checked","0");
            }else{
                $(this).children(".cart_checkbox").addClass("glyphicon glyphicon-ok");
                $(this).parents(".item_content").addClass("item_content_selected");
                $(this).attr("attr_checked","1");
            }
        });

        $(".cartItem_header").on("click",".cart_label",function(){
            if($(this).attr("attr_checked") == "1"){
                $(this).children(".cart_checkbox").removeClass("glyphicon glyphicon-ok");
                var $item_content = $(this).parents(".cart_item").children(".item_content");
                $item_content.removeClass("item_content_selected");
                $item_content.find(".cart_checkbox").removeClass("glyphicon glyphicon-ok");
                $(this).add($item_content.find(".cart_checkbox")).attr("attr_checked","0");
            }else{
                $(this).children(".cart_checkbox").addClass("glyphicon glyphicon-ok");
                $(this).parents(".item_content").addClass("item_content_selected");
                $(this).attr("attr_checked","1");
            }
        });
    })
</script>
</html>