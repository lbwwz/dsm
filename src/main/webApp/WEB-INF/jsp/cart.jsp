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
    <div>头</div>
    <div  class="row cart_list">
        <div class="col-xs-12">
        <div class="cart_item">
            <div class="cartItem_header">

            </div>
            <div class="cartItem_body">
                <div class="item_content">
                    <ul class="cart-inline">
                        <li class="dsm_chk">
                            <div class="div_inner"><input type="checkbox" value=""></div>
                        </li>
                        <li class="dsm_item">
                            <div class="div_inner">
                                <div class="pull-left ct_pic">
                                    <img src="https://img.alicdn.com/bao/uploaded/i2/2115427409/TB2pgXLdFXXXXXnXXXXXXXXXXXX_!!2115427409.jpg_80x80.jpg"/>
                                </div>
                                <div class="ct_item_content">
                                    <div class="ct_name"><a>123123123123123123123123123123123123123123123123</a></div>
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
                                    <div class="price_line"><em class="price_original">￥105.00</em></div>
                                    <div class="price_line"><em class=" price_now" tabindex="0">￥45.00</em></div>
                                </div>
                            </div>
                        </li>
                        <li class="dsm_amount">
                            <div class="div_inner">
                                <div class="ct_amount_wrapper ">
                                    <div class="item_amount ">
                                        <a href="javascript:;" class="J_Minus no-minus">-</a>
                                        <input type="text" value="1" class="text text_amount J_ItemAmount" data-max="93"data-now="1" >
                                        <a href="javascript:;" class="J_Plus plus">+</a></div>
                                    <div class="amount-msg J_AmountMsg"></div>
                                </div>
                            </div>
                        </li>
                        <li class="dsm_sum"><div class="div_inner">List Item</div></li>
                        <li class="dsm_op"><div class="div_inner">List Item</div></li>
                        <div class="clearfix"></div>
                    </ul>

                </div>
            </div>
        </div>
        </div>
    </div>
    <div>结尾</div>
</div>
</body>
</html>