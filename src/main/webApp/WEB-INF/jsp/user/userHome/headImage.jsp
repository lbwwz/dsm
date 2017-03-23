<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../../common/commonPath.jsp"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>D.S Mall 个人设置</title>
	<link href="${rsRoot}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${rsRoot}/css/non-responsive.css" rel="stylesheet">

	<link href="${rsRoot}/css/style_users.css" rel="stylesheet">
	<link href="${rsRoot}/css/style-userSetter.css" rel="stylesheet">

	<!-- jQuery -->
	<script src="${rsRoot}/js/jquery-1.11.3.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${rsRoot}/js/bootstrap.min.js"></script>


	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>


<body>
	<jsp:include page="../../common/topNav.jsp" />
	<jsp:include page="common/accountHeadNav.jsp" />

	<div class="container">
			
			<div class="panel panel-default">
			
			<div class="panel-body userSetter-panel">
				<div class="row">
					<!-- 侧栏导航 -->
					<jsp:include page="common/accountSideNav.jsp" />
					<div class="col-xs-10 info-panel">
						<ul class="nav nav-tabs">
							<li role="presentation" class="active"><a href="javascript:;">个人资料</a></li>
						</ul>
						<ul class="list-inline personal_setter">
							<li><a href="${webRoot}/userHome/personalInfo" >基本资料</a></li>
							<li><a href="${webRoot}/userHome/headImage" id="actived">头像设置</a></li>
						</ul>
						<div class="user_baseInfo">
							<div class="alert alert-success" id="alertMsg" style="display:none;">
								<span class="glyphicon glyphicon-ok-sign"></span> <span id="backMsg">${requestScope.backMsg }</span>
							</div>
							<form class="form-horizontal" action="${webRoot}/userHome/setHeadImage" method="post" enctype="multipart/form-data">
						    	<fieldset>
							    	<!-- 使用session防止表单的重复提交 -->
									<c:set var="rand"><%= new java.util.Date().getTime()+"" %></c:set>
									<c:set var="token" scope="session" value="${rand }"/>
									<input type="hidden" name="token" value="${rand }"/>
									<div class="form-group">
										<label for="headImgInput" class="col-xs-2 control-label">请选择</label>
										<div class="col-xs-4">
											<input type="file" id="headImgInput" name="headImgInput"
												class="form-control " placeholder="上传您的头像" required>
										</div>
									</div>
									<div class="form-group">
									<label class="col-xs-2 control-label"></label>
										<div class="col-sm-4">
											<div id="result">
												<img src="${webRoot}/${user.headImage}"/>
											</div>
										</div>
									</div>
								</fieldset>
								<div class="form-group ">
								  	<div class="col-xs-offset-2 col-xs-4">
								    	<button type="submit" class="btn btn-success btn-sm" id="submitInfo">保存头像</button>
								  	</div>
								</div>
							</form>
							
						</div>
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
	<script type="text/javascript">
	$(function(){
		var result = $("#result"); 
		var input = $("#headImgInput"); 
		//检验浏览器的兼容性
		if(typeof FileReader==='undefined'){ 
		    result.text("抱歉，您的浏览器暂不支持图片预览"); 
		}else{ 
		    input.on('change',function(){ 
			    var file = this.files[0]; 
			    if(!/image\/\w+/.test(file.type)){ 
			        alert("文件必须为图片！"); 
			        input.val("");
			        return false; 
			    } 
			    var reader = new FileReader(); 
			    reader.readAsDataURL(file); 
			    reader.onload = function(e){ 
			        result.html('<img src="'+this.result+'" alt=""/>'); 
			    } 
			}); 
		}
		
	});

	//回执信息
	$(function(){
		var msg = $("#backMsg").text();
		if(msg != ""){
			$("#alertMsg").attr("style","display:block;");
		}
	});
	</script>
</body>
</html>