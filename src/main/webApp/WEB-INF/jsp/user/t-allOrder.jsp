<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../../includePage/base.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已买的宝贝</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/non-responsive.css" rel="stylesheet">
<link href="css/style_users.css" rel="stylesheet">
<!-- jQuery -->
<script src="js/jquery-1.11.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>


<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
	<%@ include file="../../includePage/topNav.jsp"%>

	<div class="user-pageTab">
		<div class="container pageLogo">
			<div class="row">
				<div class="col-xs-3">
					<a href="authorityPage/user/idAuthentication.jsp"><img
						src="images/logo-user.png" /></a>
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
		<div class="row ">
			<!-- 侧栏导航 -->
			<div class="col-xs-1 side-list">
				<h4>个人交易</h4>
				<ul class="list-unstyled ">
					<li><a href="#">个人首页</a></li>
					<li><a href="#">我的购物车</a></li>
					<li><a href="#" id="be-Checked">已买到的宝贝</a></li>
					<li><a href="#">我的收藏</a></li>
					<li><a href="#">我的评价</a></li>
					<li><a href="#">我的店铺</a></li>
				</ul>
				<!-- /.row -->
			</div>
			<div class="col-xs-11">
				<div class="translation-list-panel">
					<ul class="list-inline goods-operate-list translation-list">
						<li><a href="#">所有订单</a></li>
						<li><a href="#">待付款</a></li>
						<li><a href="#">待发货</a></li>
						<li><a href="#">待收货</a></li>
						<li><a href="#">待评价</a></li>
					</ul>
				</div>
				<div class="panel panel-default">
					<table style="width: 100%; border-collapse: collapse; border-spacing: 0px;">
						<colgroup span="7">
							<col span="1" style="width: 38%;">
							<col span="2" style="width: 27%;">
							<col span="4" style="width: 35%;">
						</colgroup>
						<tbody>
							<tr style="background-color: #F5F5F5;">
								<td style="padding: 10px 20px; text-align: left;">
									<label><input type="checkbox" disabled="" style="margin-right: 8px;">
									<strong title="2015-09-15 09:35:01" style="margin-right: 8px; font-weight: bold;">2015-09-15</strong></label>
									<span>订单号：</span>
									<span>1283238881440130</span>
								</td>
								
								<td colspan="2" style="padding: 10px 20px; text-align: center;">
								<span><a href="#" style="display: inline-block; width: 70px; height: 16px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis; text-align: left; margin-left: 3px;"
										title="华谊数码正品特卖店" target="_blank">华谊数码正品特卖店</a></span>
										</td>
										
								<td colspan="4" style="padding: 10px 20px; text-align: right;">
								<span style="margin-right: 12px; visibility: hidden;">
									<span title="分享" >
									<i style="height: 17px; width: 1px; padding-left: 17px; overflow: hidden; vertical-align: middle; font-size: 0px; display: inline-block; background: url(//img.alicdn.com/tps/i1/TB1heyGFVXXXXXpXXXXR3Ey7pXX-550-260.png) no-repeat -206px -177px;"
														></i></span></span>
								<span style="margin-right: 12px; visibility: hidden;"><span><a>
											<span
												title="编辑标记信息，仅自己可见"><span><i
														style="height: 17px; width: 1px; padding-left: 17px; overflow: hidden; vertical-align: middle; font-size: 0px; display: inline-block; background: url(//img.alicdn.com/tps/i1/TB1heyGFVXXXXXpXXXXR3Ey7pXX-550-260.png) no-repeat -176px -176px;"
														></i></span></span></a></span></span><span
									style="margin-right: 12px; visibility: hidden;"><span title="删除订单"><span><i
															style="height: 17px; width: 1px; padding-left: 17px; overflow: hidden; vertical-align: middle; font-size: 0px; display: inline-block; visibility: visible; background: url(//img.alicdn.com/tps/i1/TB1heyGFVXXXXXpXXXXR3Ey7pXX-550-260.png) no-repeat -239px -176px;"
															></i>
															</span></span></span>
												</td>
							</tr>
						</tbody>
					</table>
					<table style="width: 100%; border-collapse: collapse; border-spacing: 0px;">
						<colgroup>
							<col style="width: 38%;">
							<col style="width: 10%;">
							<col style="width: 5%;" >
							<col style="width: 12%;">
							<col style="width: 12%;">
							<col style="width: 11%;">
							<col style="width: 12%;">
						</colgroup>
						<tbody>
							<tr>
								<td style="text-align: left; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 0; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 0; border-top-style: solid; border-top-color: #E8E8E8; padding-left: 20px;">
									<div style="overflow: hidden;">
										<a href="javascript:;" style="float: left; width: 27%; margin-right: 2%; text-align: center;">
										<img src="#" style="border: 1px solid #E8E8E8; max-width: 80px;"></a>
										<div style="float: left; width: 71%; word-wrap: break-word;">
											
												<a href="javascript:;"><span>手机自拍杆韩国
														蓝牙自拍杆 苹果三星拍照神棍架 线控自拍神器杆</span></a>
											
											<div style="margin-top: 8px; margin-bottom: 0; color: #9C9C9C;">
												<span style="margin-right: 6px;"><span>颜色分类</span><span>：</span><span>线控炫酷黑</span></span>
											</div>
											<div style="margin-top: 8px; margin-bottom: 0;">
												<span style="margin-right: 6px;">
													<a href="javascript:;" title="保障卡">
													</a>
												</span>
											</div>
										</div>
									</div>
								</td>
								<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 0; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 0; border-top-style: solid; border-top-color: #E8E8E8;">
									<div style="font-family: verdana; font-style: normal;">
										<p>138.00</p>
									</div>
								</td>
								
								<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 0; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 0; border-top-style: solid; border-top-color: #E8E8E8;">
									<div>1</div>
								</td>
								<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 1px; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 0; border-top-style: solid; border-top-color: #E8E8E8;">
									<div style="margin-bottom: 3px;">
										<span class="trade-operate-text">投诉卖家</span>
									</div>
									<div style="margin-bottom: 3px;" >
										<a href="javascript:;">
										<span >退运保险</span></a>
									</div>
								</td>
								<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 1px; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 0; border-top-style: solid; border-top-color: #E8E8E8;">
									<div style="font-family: verdana; font-style: normal;">
										<p>
											<strong>38.22</strong>
										</p>
									</div>
									<p>
										<span>(含运费：</span><span>0.00</span><span>)</span>
									</p>
								</td>
								<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 1px; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 0; border-top-style: solid; border-top-color: #E8E8E8;">
									<div style="margin-bottom: 3px;">
										<a href="javascript:;" >交易关闭</a>
									</div>

									<div style="margin-bottom: 3px;">
										<a  href="javascript:;" ><span>订单详情</span></a>
									</div>
								</td>
								<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 0; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 0; border-top-style: solid; border-top-color: #E8E8E8;">
								</td>
							</tr>
							<tr>
								<td style="text-align: left; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 0; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 1px; border-top-style: solid; border-top-color: #E8E8E8; padding-left: 20px;">
									<div style="overflow: hidden;">
										<div style="float: left; width: 71%; word-wrap: break-word;">
											<a href="javascript:;"><span>保险服务</span></a>
										</div>
									</div>
								</td>
							<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 0; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 1px; border-top-style: solid; border-top-color: #E8E8E8;">
								<div style="font-family: verdana; font-style: normal;">
									<p><del style="color: #9C9C9C;">0.00</del></p>
									<p>0.00</p>
								</div>
							</td>
							<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 0; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 1px; border-top-style: solid; border-top-color: #E8E8E8;">
								<div>1</div>
							</td>
								<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 1px; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 1px; border-top-style: solid; border-top-color: #E8E8E8;"></td>
								<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 1px; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 0; border-top-style: solid; border-top-color: #E8E8E8;"></td>
								<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 1px; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 0; border-top-style: solid; border-top-color: #E8E8E8;"></td>
								<td style="text-align: center; vertical-align: top; padding-top: 10px; padding-bottom: 10px; border-right-width: 0; border-right-style: solid; border-right-color: #E8E8E8; border-top-width: 0; border-top-style: solid; border-top-color: #E8E8E8;"></td>
							</tr>
						</tbody>
					</table>
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