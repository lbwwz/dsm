<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
	<%--<link href="${rsRoot}/css/bootstrap.min.css" rel="stylesheet">--%>
	<%--<link href="${rsRoot}/css/non-responsive.css" rel="stylesheet">--%>
	<link href="${rsRoot}/css/style_log.css" rel="stylesheet" type="text/css" media="all" />
	<script src="${rsRoot}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<jsp:include page="common/commonPath.jsp"/>

</head>
<body class="login">
<%--<jsp:include page="common/topNav.jsp" />--%>
	<div class="login_m">
		<div class="login_logo"><a href="index.jsp"><img src="./images/logo.png" width="240" height="55"></a></div>
		
		<div class="login_body">
			<div class="login_padding" id="login_model">

				<form id="loginForm" action="${webRoot}/login" method="post">
					<input type="hidden" name="backUrl" value="${pageContext.request.contextPath}"/>

					<h3>帐号</h3>
					<label>
						<input type="text" id="username" class="txt_input" name="loginName" placeholder="邮箱/电话/用户名"
							   required/>
					</label>
					<h3>密码</h3>
					<label>
						<input type="password" id="userpwd" class="txt_input" name="password" placeholder="密码"
							   required/>
					</label>
					<div class="other">
						<span class="forgot leftFloat"><a href="javascript:void(0);">忘记密码？</a></span>
						<span class="forgot"><a href="register.jsp;">用户注册</a></span>
					</div>
					<div style="clear:both;"></div>
					<div class="rem_sub">
						<div class="rem_sub_l">
							<input type="checkbox" name="isRemember" id="save_me" value="1">
							<label for="save_me">记住我</label>
						</div>
						<label class="sub_label">
							<input type="button" class="sub_button" name="button" id="login_button" value="登	录"
								   style="opacity: 0.6;">
						</label>
					</div>
				</form>
				<div id="imgButton" ><img src="${VerifyCodeImage}"/></div>

			</div>
	
		<!--login_padding  Sign up end-->
		</div><!--login_boder end-->
	</div><!--login_m end-->


	<script src="js/ajaxSubmit.js"></script>
	<script src="${rsRoot}/front-lib/layer/layer.js"></script>
	<script type="text/javascript">
	$(function(){
		//验证码切换
		$("#imgButton").click(function(){

			$.ajax({
				url: "${webRoot}/getVerifyCodeImage",
				data:{"timeStamp":new Date()},
				cache: false,
				success: function(data){
					$("#imgButton").html("");
					$("#imgButton").append('<img src="'+data+'"/>');
				}
			});

		})
//

		//登录提交事件
		$("#login_button").click(function(){
			//表单提交前校验表单提交的信息的合法性
			$.ajaxSubmit($("#loginForm"),function(data){
				//登陆成功
				if(0 == data.error){
					location.href=data.data;
				}else{
					layer.msg(data.message, {
						icon: 1,
						time: 1000
					});
				}
			})
		})
	});
	</script>
</body>
</html>