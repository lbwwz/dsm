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
    <jsp:include page="../common/commonPath.jsp"/>
</head>

<body>
<jsp:include page="../common/topNav.jsp" />
<div class="container">
    <div class="row">
        <div class="col-xs-5 dsm_ico"><a href="index.jsp"><img src="${rsRoot}/images/logo.png"/></a><font>结算页</font></div>
        <div  class="col-xs-7">

        </div>
    </div>
    <!--结算页面主体 -->
    <div class=" orderCheck_panel">
    </div>
    <footer class="footer-style">
        <div class="row">
            <div class="col-lg-12">
                <p class="text-center">Copyright © D.S Mall 2015</p>
            </div>
        </div>
    </footer>
</div>

<form action="order/confirm_order.htm" method ="post" id="confirm_order">
    <input type="hidden" value="${items}" id="items" name="items"/>
    <input type="hidden" value="cart" name="buyerFrom"/>
    <input type="hidden" value="" name="source_time" id="source_time"/>
</form>


</body>

<script src="${rsRoot}/front-lib/layer/layer.js"></script>
<script>
    $(function(){
        $(".orderCheck_panel").on("click","[name=address]",function(){
            if($(this).parents(".selected_address").length == 0){
                var addressId = $(this).attr("id").split("-")[1];

                $.ajax({
                    url: "/order/getOrderCheckInfo",
                    type: "post",
                    cache: false,
                    data: {items:$("#items").val(),addressId:addressId},
                    success:function(data){
                        $(".orderCheck_panel").html(data);
                    }
                })

            }
        });

        $.ajax({
            url: "/order/getOrderCheckInfo",
            type: "post",
            cache: false,
            data: {items:$("#items").val()},
            success:function(data){
                $(".orderCheck_panel").html(data);
            }
        })
    })
</script>
</html>