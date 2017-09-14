<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>个人中心</title>
	<link href="${rsRoot}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${rsRoot}/css/non-responsive.css" rel="stylesheet">
	<link href="${rsRoot}/css/style_users.css" rel="stylesheet">
	<!-- jQuery -->
	<script src="${rsRoot}/js/jquery-1.11.3.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${rsRoot}/js/bootstrap.min.js"></script>

	<jsp:include page="../../common/commonPath.jsp"/>


	<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
<jsp:include page="../../common/topNav.jsp" />

<div class="user-pageTab">
	<div class="container pageLogo">
		<div class="row">
			<div class="col-xs-3">
				<a href="authorityPage/user/idAuthentication.jsp"><img src="${rsRoot}/images/logo-user.png"/></a>
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
			<h4>全部功能</h4>
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
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class= "col-xs-4">
							<div class="media">
								<div class="media-left media-middle ">
									<img class="media-object"  src="${webRoot}/${user.headImage}" style="width: 55px; height: 55px;">
									<span ></span>
								</div>
								<div class="media-body user_name">
									<h4 class="media-heading">
										lbwwz蚊子
									</h4>
								</div>
							</div>
						</div>
						<div class= "col-xs-8">
							<ul class="list-inline bas-list">
								<li><a href="#">我的收货地址</a></li>
								<li><a href="#">我的账户余额</a></li>
								<li><a href="#">我的收藏信息</a></li>
							</ul>
						</div>
					</div>

				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<ul class="list-inline goods-operate-list">
								<li><a href="#">待付款</a></li>
								<li><a href="#">待发货</a></li>
								<li><a href="#">待收货</a></li>
								<li><a href="#">待评价</a></li>
								<li><a href="#">退款</a></li>
							</ul>
						</div>
					</div>
				</div>

			</div>
			<div class="jumbotron">
				<h1>
					Welcome!
				</h1>
				<p>
					To get the most out of Akira start with our 3 minute tour.
				</p>
				<p>
					<a class="btn btn-primary btn-lg" href="help.htm" role="button">Learn more</a>
					<a class="btn btn-large">No Thanks</a>
				</p>

			</div>
			<div class="well summary">
				<ul>
					<li>
						<a href="#"><span class="count">3</span> Projects</a>
					</li>
					<li>
						<a href="#"><span class="count">27</span> Tasks</a>
					</li>
					<li>
						<a href="#"><span class="count">7</span> Messages</a>
					</li>
					<li class="last">
						<a href="#"><span class="count">5</span> Files</a>
					</li>
				</ul>
			</div>
			<h2>
				Recent Activity
			</h2>
			<table class="table table-bordered table-striped">
				<thead>
				<tr>
					<th>
						Project
					</th>
					<th>
						Client
					</th>
					<th>
						Type
					</th>
					<th>
						Date
					</th>
					<th>
						View
					</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td>
						Nike.com Redesign
					</td>
					<td>
						Monsters Inc
					</td>
					<td>
						New Message
					</td>
					<td>
						5 days ago
					</td>
					<td>
						<a href="#" class="view-link">View</a>
					</td>
				</tr>


				</tbody>
			</table>

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