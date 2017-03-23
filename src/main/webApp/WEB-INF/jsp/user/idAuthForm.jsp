<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../common/commonPath.jsp"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>卖家注册</title>

	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<link href="../css/non-responsive.css" rel="stylesheet">
	<!-- Custom Fonts -->
	<link href="${rsRoot}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- style-idAuth 样式 -->
	<link href="../css/style-idAuth.css" rel="stylesheet">

	<script src="../js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="../js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>

<body>
	<!-- 顶栏菜单 -->
	<jsp:include page="../common/topNav.jsp"/>
	<div class="container pageLogo">
	    <div class="row">
	        <div class="col-xs-5"><a href="authorityPage/user/idAuthentication.jsp"><img src="../images/logo-idAuthentication.png"/></a></div>
	    </div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<a href="/userHome/idAuthentication" type="button" class="btn btn-link"><i class="fa fa-arrow-circle-left"></i>&nbsp;返回</a>
				<h3 class="page-header-title">D.S Mall 身份认证资料（中国大陆地区）<a class="pull-right">帮助 >></a></h3>
				<p >请通过以下方式提交认证：</p>
				<p class="under-line">开店认证支持使用电脑、手机淘宝客户端和阿里钱盾客户端提交认证资料，<strong>系统会根据您的网络安全环境做出推荐。</strong></p>
				<c:if test="${!empty alertMsg || alertMsg == ''}">
				<div class="alert alert-warning alert-dismissible fade in" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
					<strong>提示!</strong> ${alertMsg}
				</div>
				</c:if>
			    <form class="form-horizontal" action="${webRoot}/userHome/submitIdentifyInfo" method="post" enctype="multipart/form-data">
				    <fieldset>
						<!-- 使用session防止表单的重复提交 -->
						<c:set var="rand"><%= new java.util.Date().getTime()+"" %></c:set>
						<c:set var="idCheckToken" scope="session" value="${rand }"/>
						<input type="hidden" name="idCheckToken" value="${rand }"/>
						<input type="hidden" name="identifyId" value="${identifyInfo.identifyId}"/>

						<div class="form-group">
					        <label class="col-xs-2 control-label" for="realName">真实姓名</label>
					        <div class="col-xs-4">
					        	<input type="text" id="realName" name="realName" value="${identifyInfo.realName}" class="form-control" placeholder="请输入您的真实姓名" required>
					        </div>
				      	</div>
				  		
					  	<div class="form-group">
					   		<label for="idCardNum" class="col-xs-2 control-label">身份证号</label>
						    <div class="col-xs-4">
						      	<input type="text" id="idCardNum" name="idCardNum" value="${identifyInfo.idCardNum}" class="form-control " placeholder="填写你的身份证号码" required>
						    </div>
					  	</div>
				      	<div class="form-group" >
				   			<label for="idImageFace" class="col-xs-2 control-label">认证照片</label>
				    		<div class="col-xs-4 " >
								<div class="imageFileContainer">
									<c:if test="${!empty identifyInfo.idImageFace}">
										<img src="${identifyInfo.idImageFace}" alt="" style="z-index: -1;height: 100%;width:100%;position: absolute"/>
									</c:if>
									<a class=" pull-left"><%--<span class="glyphicon glyphicon-folder-open"></span>&nbsp;--%>+
										<input type="file" name="idImageFace" id="idImageFace" class="uploadFileBtn"  accept="image/gif, image/jpeg,  image/png, image/jpg"/>
									</a>
								</div>
					    	</div>
							<p class="help-block">（身份证正面信息）</p>
					  	</div>
						<div class="form-group " >
							<label for="idImageBack" class="col-xs-2 control-label"></label>

							<div class="col-xs-4">
								<div class="imageFileContainer">
									<c:if test="${!empty identifyInfo.idImageBack}">
										<img src="${identifyInfo.idImageBack}" alt="" style="z-index: -1;height: 100%;width:100%;position: absolute"/>
									</c:if>
									<a class=" pull-left" ><%--<span class="glyphicon glyphicon-folder-open"></span>&nbsp;--%>+
										<input type="file" name="idImageBack" id="idImageBack" class="uploadFileBtn"  accept="image/gif, image/jpeg,  image/png, image/jpg"/>
									</a>
								</div>
							</div>
							<p class="help-block">（身份证背面信息）</p>
						</div>
				      	<div class="form-group">
				   			<label for="checkPermission" class="col-xs-2 control-label">同意协议</label>
				    		<div class="col-xs-4 checkbox-control">
					      		<input type="checkbox" id="checkPermission" name="checkPermission" checked value="1">
					    	</div>
					  	</div>
			      	</fieldset>
					<div class="form-group ">
					  	<div class="col-xs-offset-2 col-xs-4">
					    	<button type="button" class="btn btn-success" id="submitInfo">提交信息</button>
					  	</div>
					</div>
				</form>
				<footer class="footer-style">
			        <div class="row">
			            <div class="col-xs-12">
			                <p class="text-center">Copyright © D.S Mall 2015</p>
			            </div>
			        </div>
			    </footer>
			</div>
		</div>
	</div>
	<script src="${rsRoot}/front-lib/layer/layer.js"></script>
	<script src="../js/ajaxSubmit.js"></script>
	<script type="text/javascript">
	
	$(function(){
		
		if(!$("#checkPermission").is(":checked")){
			$("#submitInfo").attr("class","btn btn-success disabled")
		}
		
		$("#checkPermission").on('click', function(){
			
			var isChecked = $("#checkPermission").is(":checked")?1:0;
			if(isChecked){
				$("#submitInfo").attr("class","btn btn-success")
			}else{
				$("#submitInfo").attr("class","btn btn-success disabled")
			}
		});

		//初始化表单域中的文件上传功能
		initFileUpdate();

		// 提交事件
		$("#submitInfo").click(function(){
			$.ajaxSubmit($("form"),function(data){
				//提交成功
				if(0 == data.error){
					layer.msg(data.message, {
						icon: 1,
						time: 2000
					});
					setTimeout(function(){
						location.href=webRoot + data.data;
					},1500);
				}else{
					layer.msg(data.message, {
						icon: 2,
						time: 2000
					});
				}
			})
		})


	});

	//表单域中的文件上传功能
	function initFileUpdate() {
		//检验浏览器的兼容性
			$(".uploadFileBtn").on('change', function () {
				var $_oContainer = $(this).parents(".imageFileContainer");
				if (typeof FileReader === 'undefined') {
					console.log($_oContainer.find("font").length)
					if($_oContainer.find("font").length == 0){
						$_oContainer.prepend("<font style='color:#fa8072'>*抱歉，您的浏览器暂不支持图片预览</font>");
					}
				} else {
					var file;
					for(var i = 0; i < this.files.length; i++){
						file = this.files[i];
						console.log(file.size);
						if (!/image\/\w+/.test(file.type)) {
							alert("文件必须为图片！");
							updateInput.val("");
							return false;
						}
						if (window.FileReader) {
							var reader = new FileReader();
							reader.readAsDataURL(file);
							reader.onloadend = function (e) {

								if($_oContainer.find("img").length){
									$_oContainer.find("img").attr("src",this.result)
								}else{
									$_oContainer.prepend('<img src="' + this.result + '" alt="" style="z-index: -1;height: 100%;width:100%;position: absolute"/>');
								}
							}
						}
					}
				}

			});

	}

	</script>
	

</body>
</html>