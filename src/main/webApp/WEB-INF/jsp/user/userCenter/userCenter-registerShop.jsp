<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../../includePage/base.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/non-responsive.css" rel="stylesheet">
<link href="css/timeline.css" rel="stylesheet">
<link href="css/style_users.css" rel="stylesheet">
<!-- jQuery -->
<script src="js/jquery-1.11.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>


<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
<%@ include file="../../includePage/topNav.jsp" %>

	<div class="user-pageTab">
		<div class="container pageLogo">
		    <div class="row">
		        <div class="col-xs-3">
		        	<a href="authorityPage/user/idAuthentication.jsp"><img src="images/logo-user.png"/></a>
		        </div>
		        <div class="col-xs-9">
					<ul class="list-inline">
						<li><a href="#">首页</a><span class="block-angle"></span></li>
						<li><a href="#">账户设置</a></li>
						<li><a href="#">消息</a></li>
					</ul>
		        </div>
		    
		    </div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-xs-1 side-list">
                <h4>全部操作</h4>
                <ul class="list-unstyled ">
                    <li><a href="#">个人首页</a></li>
                    <li><a href="#">我的购物车</a></li>
                    <li><a href="#">已买到的宝贝</a></li>
                    <li><a href="#">我的收藏</a></li>
                    <li><a href="#">我的评价</a></li>
                    <li><a href="#">我的店铺</a></li>
                </ul>
                <!-- /.row -->
	        </div>
			<div class="col-xs-9">
				<ol class="breadcrumb">
				  <li><a href="javascript:;">我是卖家</a></li>
				  <li class="active">注册店铺</li>
				</ol>
				<div class="panel panel-default ">
					<div class="panel-body">
					<form class="form-horizontal" action="<%=request.getContextPath()%>/loginAndRegister?method=register" method="post">
					    <fieldset>
							<legend>填写店铺的基本信息</legend>
							<!-- 使用session防止表单的重复提交 -->
							<c:set var="rand"><%= new java.util.Date().getTime()+"" %></c:set>
							<c:set var="registerToken" scope="session" value="${rand }"/>
							<input type="hidden" name="registerToken" value="${rand }"/>
							<div class="form-group">
						        <label class="col-xs-2 control-label" for="email" >电子邮箱</label>
						        <div class="col-xs-4">
						          <input id="email" name="email" class="form-control" type="email" placeholder="请输入邮箱" />
						          <p class="help-block"></p>
						        </div>
					      	</div>
					  		
						  	<div class="form-group">
						   		<label for="password" class="col-xs-2 control-label">密码</label>
							    <div class="col-xs-4">
							      	<input type="password" name="password" id="password" class="form-control" placeholder="设置您的密码">
							    </div>
						  	</div>
							<div class="form-group">
						        <label class="col-xs-2 control-label" for="confirmPassword" >确认密码</label>
						        <div class="col-xs-4">
						          <input type="password" name="confirmPassword" class="form-control" id="confirmPassword" placeholder="重复上述密码" />
						        </div>
						        <div class="col-xs-6">
						          <p class="help-block">1231</p>
						        </div>
					      	</div>
					      	<div class="form-group">
					   			<label for="userName" class="col-xs-2 control-label">昵称</label>
					    		<div class="col-xs-4">
						      		<input type="text" name="userName" class="form-control" id="username" placeholder="设置您的昵称">
						    	</div>
						  	</div>
					      	<div class="form-group">
					   			<label for="telephone" class="col-xs-2 control-label">手机号</label>
					    		<div class="col-xs-4">
						      		<input type="text" name="telephone" class="form-control" id="telephone" placeholder="设置您的手机号码">
						    	</div>
						  	</div>
					      	
							<div class="form-group">
						        <label class="col-xs-2 control-label" >您的性别</label>
						        <div class="col-xs-4">
						        <label class="radio-inline">
						          <input type="radio" name="sex" value="0" checked="checked">保密</label>
							       <label class="radio-inline">
						          <input type="radio" name="sex" value="1">男</label>
						           <label class="radio-inline">
						          <input type="radio" name="sex" value="2">女</label>
						        </div>
					      	</div>
					 		
					      	</fieldset>
						</form>
	                </div>
		            <div class="panel-footer">
	                    <div class="col-xs-offset-2 col-xs-3">
					    	<button type="submit" class="btn  btn-success">提交注册</button>
					  	</div>
	                    <div class="clearfix"></div>
	                </div>
	            </div>
				
				
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