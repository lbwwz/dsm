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
	<link href="${rsRoot}/css/timeline.css" rel="stylesheet">
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
				  <li class="active">申请开店</li>
				</ol>
				<form class="form-horizontal" <c:if test="${user.promotedType == 3 }">action="<%=request.getContextPath()%>/userCenterOperate?method=createShop" method="post"</c:if>>
					<!-- 使用session防止表单的重复提交 -->
			      	<c:set var="rand"><%= new java.util.Date().getTime()+"" %></c:set>
					<c:set var="token" scope="session" value="${rand }"/>
					<input type="hidden" name="token" value="${rand }"/>
					<div class="panel panel-default ">
						<div class="panel-body">
							<ul class="timeline" id="checkAuthInfo">
			                    <li class="timeline-inverted">
			                        <div class="timeline-badge"><strong>1</strong>
			                        </div>
			                        <div class="timeline-panel">
			                            <div class="timeline-heading">
			                                <h4 class="timeline-title">开店条件检测</h4>
			                            </div>
			                            <div class="timeline-body">
			                                <p class="tips-text"><span class="glyphicon glyphicon-ok-sign"></span> &nbsp;亲，恭喜您满足开店条件，请继续完成下面的开店认证后才能创建店铺！</p>
			                            </div>
			                        </div>
			                    </li>
			                    <li class="timeline-inverted">
			                        <div class="timeline-badge  info"><strong>2</strong>
			                        </div>
			                        <div class="timeline-panel">
			                            <div class="timeline-heading">
			                                <h4 class="timeline-title" id="checkedTitle">申请开店认证</h4>
			                            </div>
			                            <div class="timeline-body">
			                            	
		    								<fieldset>
				                                <div class="form-group">
											        <label class="col-xs-2 control-label" >选择所在地</label>
											        <div class="col-xs-4">
											        	<label class="radio-inline">
											          	<input type="radio" name="shopLocation" value="0" checked="checked">中国大陆</label>
											        </div>
										      	</div>
				                                <div class="form-group">
											        <label class="col-xs-2 control-label" ></label>
											        <div class="col-xs-8">
											        	<div class="table-responsive">
			                            					<table class="table table-striped table-hover authSchedule">
								                                <thead>
								                                    <tr>
								                                        <th>状态	</th>
								                                        <th>认证名称</th>
								                                        <th>操作</th>
								                                    </tr>
								                                </thead>
								                                <tbody >
								                                    <tr>
								                                        <td>
								                                        <c:choose>
									                                        <c:when test="${user.promotedType == 0 }"><small style="color:#aaa;"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;未开始</small></c:when>
									                                        <c:when test="${user.promotedType == 3 }"><small style="color:#3c763d;"><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;已完成</small></c:when>
									                                        <c:otherwise><small style="color:#8a6d3b;"><span class="glyphicon glyphicon-question-sign"></span>&nbsp;进行中</small></c:otherwise>
								                                        </c:choose>
								                                        </td>
								                                        <td><small>用户实名认证</small></td>
								                                        <td><small><a href="${webRoot}/userHome/idAuthentication">查看</a></small></td>
								                                    </tr>
								                                </tbody>
								                            </table>
							                        	</div>
											        </div>
										      	</div>
									      	</fieldset>
			                            </div>
			                        </div>
			                    </li>
								<c:if test="${user.promotedType == 3 }">
								<li class="timeline-inverted">
										<div class="col-xs-offset-1 col-xs-3">
											<button type="button" class="btn  btn-success" id="showCreateForm">创建我的店铺</button>
										</div>

								</li>
								</c:if>
			                </ul>
			                <c:if test="${user.promotedType == 3 }">
								<%--创建店铺需要填写的店铺信息--%>

								<div id="applyShopForm" style="display:none;">
									<div class="form-group">
										<label class="col-xs-2 control-label" >店铺名称</label>
										<div class="col-xs-4">
											<input type="text" name="shopName" class="form-control" id="shopName" placeholder="设置您店铺名称" required>
										</div>
										<div class="col-xs-4">
											<p class="help-block" id="checkedMsg" style="line-height: 20px;"></p>
										</div>
									</div>
								</div>
							</c:if>
		                </div>
			            <div class="panel-footer" id="footCreatePanel" style="display:none;">
		                    <div class="col-sm-offset-1 col-sm-3">
		                    <c:if test="${user.promotedType == 3 }">
								<button type="button" class="btn  btn-primary" id="createShop">创建店铺</button>
								<button type="button" class="btn  btn-default" id="cancelCreateShop">取消</button>
							</c:if>
						  	</div>
		                    <div class="clearfix"></div>
		                </div>
		            </div>
	            </form>
			</div>
		</div>
		<footer class="footer-style" >
	        <div class="row">
	            <div class="col-lg-12">
	                <p class="text-center">Copyright © D.S Mall 2015</p>
	            </div>
	        </div>
	    </footer>
	</div>

	<script src="${rsRoot}/front-lib/layer/layer.js"></script>
	<script type="text/javascript">
	$(function(){
		//禁用input控件的键盘回车事件
		$("input").keydown(function (event) {
			if (event.keyCode == 13) {
				event.keyCode = 0;
				return false;
			}
		});

		//创建店铺表单
		$("#showCreateForm").click(function(){
			$("#checkAuthInfo").animate({
				height: 'toggle',
			}, 300,"swing",function(){
				$("#applyShopForm").slideDown(300,"swing");
				$("#footCreatePanel").slideDown(300,"swing");
			});
		});

		//取消创建店铺
		$("#cancelCreateShop").click(function(){
			$("#applyShopForm").slideUp(300,"swing");
			$("#footCreatePanel").slideUp(300,"swing");
			$("#checkAuthInfo").delay(350).slideDown(300,"swing");
		});

		//使用ajax校验店铺名称是否可用
		$("#shopName").blur(function(){
			//移除checkedMsg中的内容
			$("#checkedMsg").html("");
	    	var shopName = $.trim($("#shopName").val());
	    	
	    	//验证关键字段是否为空（空格等也算作空）
	    	if(shopName == "" || shopName == null){
	    		layer.tips('店铺名不能为空', '#shopName');
	    		$("#shopName").val("");
	    		return false;
	    	}else{
	    		$("#checkedMsg").html("<img src='images/inputLoading.gif'/>")
				//发送请求
				$.ajax({
				   type: "POST",
				   url: "${pageContext.request.contextPath }/userCenterOperate?method=checkShopName",
				   data: "shopName="+shopName+"&time="+new Date(),
				   success: function(data){
					   $("#checkedMsg img").remove();
					   $("#checkedMsg").append(data);
				   }
				});
	    	}
	    	return false;
         });
	
		
		//创建店铺之前的检查操作
		$("#createShop").click(function(){
			var flag = true;
			//检查地址的设置是否合法（省市县不能部分为空）
			if($("#checkedMsg").find("span:eq(0)").attr("class") == "glyphicon glyphicon-remove-sign"){
				//相应的提示操作
				$("#shopName")[0].focus();
				flag = false;
			}else if($.trim($("#shopName").val()) == ""){
				layer.tips('店铺名不能为空', '#shopName');
				$("#shopName")[0].focus();
				flag = false;
			}
			if(flag){
				$("form").submit();
			}	
			return false;
		});
	});
	
	</script>
	
</body>
</html>