<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../../../includePage/base.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>D.S Mall-我是卖家</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/non-responsive.css" rel="stylesheet">
<!-- 侧边栏菜单 -->
<link href="css/menuNav.css" rel="stylesheet">
<!-- style-seller 样式 -->
<link href="css/style-seller.css" rel="stylesheet">
<link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>

<body>
<%@ include file="../../../includePage/topNav.jsp" %>
	<div class="container pageLogo">
	    <div class="row">
	        <div class="col-xs-5"><a href="index.jsp"><img src="images/logo-seller.png"/></a></div>
	    </div>
	</div>
	
	<div class="container">
		<nav class="navbar navbar-default horizontal-navbar">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		    </div>
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="javascript:;">首页 <span class="sr-only">(current)</span></a></li>
					<li><a href="javascript:;">基础设置</a></li>
					<li><a href="javascript:;">规则中心</a></li>
					<li class="dropdown">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">安全中心 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="javascript:;">Action</a></li>
							<li><a href="javascript:;">Another action</a></li>
							<li><a href="javascript:;">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="javascript:;">Separated link</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="javascript:;">One more separated link</a></li>
						</ul>
					</li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
		      
		    </div><!-- /.navbar-collapse -->
		</nav>
	</div>
	<div class="container">
        <div class="row">
			<!-- 侧栏菜单 -->
            <div class="col-xs-2">
            	<div class="panel panel-default">
                        
                        <div class="panel-body">
                            <ul id="main-nav" class="main-nav nav nav-tabs nav-stacked" style="">
			                    <li>
			                        <a href="authorityPage/user/seller/shopIndex.jsp">
			                            <i class="glyphicon glyphicon-th-large"></i>&nbsp;卖家首页
			                        </a>
			                    </li>
			                    <li>
			                        <a href="#transactionManage" class="nav-header collapsed" data-toggle="collapse">
			                            <i class="glyphicon glyphicon-cog"></i>&nbsp;交易管理
			                            <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
			                        </a>
			                        <ul id="transactionManage" class="nav nav-list secondmenu collapse" style="height: 0px;">
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-user"></i>&nbsp;已卖出的宝贝</a></li>
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-th-list"></i>&nbsp;评价管理</a></li>
			                        </ul>
			                    </li>
			                    <li class="active">
			                        <a href="#shippingManage" class="nav-header " data-toggle="collapse">
			                            <i class="glyphicon glyphicon-cog"></i>&nbsp;物流管理
			                            <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
			                        </a>
			                        <ul id="shippingManage" class="nav nav-list secondmenu collapse in" >
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-user"></i>&nbsp;发货</a></li>
			                            <li class="active"><a href="javascript:;"><i class="glyphicon glyphicon-th-list"></i>&nbsp;物流工具</a></li>
			                        </ul>
			                    </li>
			                    <li>
			                        <a href="#goodsManage" class="nav-header collapsed " data-toggle="collapse">
			                            <i class="glyphicon glyphicon-cog"></i>&nbsp;宝贝管理
			                            <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
			                        </a>
			                        <ul id="goodsManage" class="nav nav-list secondmenu collapse">
			                            <li><a href="${pageContext.request.contextPath }/shopOperate?method=releaseProductPage"><i class="glyphicon glyphicon-user"></i>&nbsp;发布宝贝</a></li>
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-th-list"></i>&nbsp;出售中的宝贝</a></li>
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-asterisk"></i>&nbsp;橱窗推荐</a></li>
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-edit"></i>&nbsp;仓库中的宝贝</a></li>
			                        </ul>
			                    </li>
			                    <li>
			                        <a href="#shopManage" class="nav-header collapsed" data-toggle="collapse" >
			                            <i class="glyphicon glyphicon-credit-card"></i>&nbsp;店铺管理
			                            <span class="pull-right glyphicon  glyphicon-chevron-toggle"></span>
			                        </a>
			                        <ul id="shopManage" class="nav nav-list secondmenu collapse" >
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-globe"></i>&nbsp;查看店铺</a></li>
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-star-empty"></i>&nbsp;图片空间</a></li>
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-star"></i>&nbsp;宝贝分类管理</a></li>
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-text-width"></i>&nbsp;店铺基本设置</a></li>
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-ok-circle"></i>&nbsp;掌柜推荐</a></li>
			                        </ul>
			                    </li>
			
			                    <li>
			                        <a href="#disSetting" class="nav-header collapsed" data-toggle="collapse">
			                            <i class="glyphicon glyphicon-globe"></i>&nbsp;分发配置
										 <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
			                        </a>
			                        <ul id="disSetting" class="nav nav-list secondmenu collapse">
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-th-list"></i>&nbsp;分发包配置</a></li>
			                        </ul>
			                    </li>
			
			                    <li>
			                        <a href="#dicSetting" class="nav-header collapsed" data-toggle="collapse">
			                            <i class="glyphicon glyphicon-bold"></i>&nbsp;字典配置
			                            <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
			                        </a>
			                        <ul id="dicSetting" class="nav nav-list secondmenu collapse">
			                            <li><a href="javascript:;"><i class="glyphicon glyphicon-text-width"></i>&nbsp;关键字配置</a></li>
			                        </ul>
			                    </li>
			                    <li>
			                        <a href="javascript:;">
			                            <i class="glyphicon glyphicon-fire"></i>&nbsp;关于系统
			                        </a>
			                    </li>
			
			                </ul>
                        </div>
                        
                    </div>
                
            </div>
            
            <!-- 内容区 -->   
			<div class="col-xs-10">
				<ol class="breadcrumb well well-sm">
                    <li>
                        <i class="fa fa-dashboard"></i>  <a href="authorityPage/admin/adminIndex.jsp">主页</a>
                    </li>
                    <li class="active">
                        <i class="fa fa-file"></i> 设置商品类目属性
                    </li>
                </ol>
                <ul class="nav nav-tabs">
					<li role="presentation" class="active"><a href="#">服务商设置</a></li>
					<li role="presentation"><a href="#">运费模版设置</a></li>
					<li role="presentation"><a href="#">物流跟踪</a></li>
					<li role="presentation"><a href="#">发货地址库</a></li>
				</ul>
				<div class="mainPanel">
					
                </div>
			</div>
		</div>
	</div>
</body>
</html>