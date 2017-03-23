<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../common/commonPath.jsp"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>D.S Mall 身份验证</title>

	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<link href="../css/non-responsive.css" rel="stylesheet">
	<!-- style-idAuth 样式 -->
	<link href="../css/style-idAuth.css" rel="stylesheet">
	<!-- Custom Fonts -->
	<link href="${rsRoot}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<script src="../js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="../js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>

<body>
	<jsp:include page="../common/topNav.jsp" />
	<div class="container pageLogo">
	    <div class="row">
	        <div class="col-xs-5"><a href="authorityPage/user/idAuthentication.jsp"><img src="../images/logo-idAuthentication.png"/></a></div>
	    </div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
	                <div class="panel-heading">
	                    <div class="media">
							<div class="media-left media-middle ">
								<img class="media-object"  src="../images/id-img.png" style="width: 120px; height: 120px;">
							</div>
							<div class="media-body">
								<h4>亲爱的：${user.userName }</h4>
								<h1 class="media-heading">
									<c:choose>
										<c:when test="${user.promotedType == 0 }">
											您尚未进行认证
										</c:when>
										<c:when test="${user.promotedType == 3 }">
											您已经完成验证
										</c:when>
										<c:otherwise>
											您的信息正在验证中
										</c:otherwise>
									</c:choose>
								</h1>
							  	<p>D.S Mall 身份认证是一项关于互联网个人身份有效性、真实性的认证服务。<a href="javascript:;">了解更多>></a></p>
							</div>
						</div>
	                </div>
                    <div class="panel-body">
                    	<c:if test="${user.promotedType == 0 }">
	                		<p class="text-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您可以：&nbsp;&nbsp;<a class="btn btn-primary btn-sm" href="${webRoot}/userHome/idEditForm" role="button">立即认证</a></p>
						</c:if>
                    	<c:if test="${user.promotedType == 1 }">
	                		<p class="text-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正在等待审核...&nbsp;&nbsp;<a class="btn btn-primary btn-sm" href="${webRoot}/userHome/idEditForm" role="button">信息修改</a></p>
						</c:if>
                    	<c:if test="${user.promotedType == 2 }">
	                		<p class="text-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核未能通过，请点击右侧按钮修改信息&nbsp;&nbsp;<a class="btn btn-primary btn-sm" href="${webRoot}/userHome/idEditForm" role="button">信息修改</a></p>
						</c:if>
                    	<c:if test="${user.promotedType == 3 }">
	                		<p class="text-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核完成 &nbsp;&nbsp;<a class="btn btn-primary btn-sm" href="#" role="button">店铺创建</a></p>
						</c:if>
	                </div>
                    <div class="panel-footer">
                        <div class="row">
				            <div class="col-lg-12">
				                <h2 class="page-header">Our Customers</h2>
				            </div>
				            <div class="col-md-2 col-sm-4 col-xs-6">
				                <img class="img-responsive customer-img" src="http://placehold.it/500x300" alt="">
				            </div>
				            <div class="col-md-2 col-sm-4 col-xs-6">
				                <img class="img-responsive customer-img" src="http://placehold.it/500x300" alt="">
				            </div>
				            <div class="col-md-2 col-sm-4 col-xs-6">
				                <img class="img-responsive customer-img" src="http://placehold.it/500x300" alt="">
				            </div>
				            <div class="col-md-2 col-sm-4 col-xs-6">
				                <img class="img-responsive customer-img" src="http://placehold.it/500x300" alt="">
				            </div>
				            <div class="col-md-2 col-sm-4 col-xs-6">
				                <img class="img-responsive customer-img" src="http://placehold.it/500x300" alt="">
				            </div>
				            <div class="col-md-2 col-sm-4 col-xs-6">
				                <img class="img-responsive customer-img" src="http://placehold.it/500x300" alt="">
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
		</div>
	</div>
	

</body>
</html>