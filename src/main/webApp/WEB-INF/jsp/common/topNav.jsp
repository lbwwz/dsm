<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--首页顶栏--%>
<link href="${webRoot}/css/topNav.css" rel="stylesheet">
<nav class="navbar navbar-default">
    <div class="container">
        <div class="row">
        <div class="col-xs-12">
            <div class="row">

            <div class="navbar-header"><a class="navbar-brand" href="${webRoot}">D.S Mall</a></div>
            <div id="topNavbar" class="collapse navbar-collapse">
                <!--左侧菜单 -->
                <ul class="nav navbar-nav">
                <c:if test="${empty sessionScope.user }">
                    <li><a href="${webRoot}/showLogin">登录</a></li>
                    <li><a href="${webRoot}/showRegister">新注册</a></li>
                </c:if>
                <c:if test="${!empty sessionScope.user }">
                    <li class="dsm_user"><a href="#">${user.userName }  <span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
                        <ul class="list-group personalMsg">
                          <li class="list-group-item"><a href="${webRoot}/userHome"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 账号管理</a></li>
                          <li class="list-group-item"><a href="${webRoot}/logout"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 退出</a></li>
                        </ul>
                    </li>
                    <li><a href="${webRoot}/register.jsp">消息  <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a></li>
                </c:if>
                </ul>
                <!-------->

                <!--右侧菜单 -->
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">我的Mall <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></a></li>
                    <li><a href="#">购物车  <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a></li>
                    <li><a href="#">收藏夹  <span class="glyphicon glyphicon-star" aria-hidden="true"></span></a></li>
                    <li><a href="#">买家中心  <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></a></li>
                    <li><a href="#">其他</a></li>
                </ul>
                <!-------->

            </div>
            <!--/.nav-collapse -->
            </div>
        </div>
        </div>
    </div>
</nav>
<script type="text/javascript">

	//用户顶栏菜单
	$(function(){
	    $('.dsm_user').hover(
	        function () {          
	            $('.dsm_user').css('background','#eee');
	            $('.personalMsg').slideDown(100);    
	        },
	        
	        function () {
	            $('.dsm_user').css('background','#f8f8f8');
	            $('.personalMsg').slideUp(100);
	        }
	    );
	});

	
</script>