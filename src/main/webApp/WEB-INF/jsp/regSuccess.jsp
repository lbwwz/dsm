<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>注册成功</title>
	<link href="${rsRoot}/css/style-reg.css" rel="stylesheet">
	<script type="text/javascript" src="${rsRoot}/js/jquery-1.11.3.min.js"></script>
	<jsp:include page="common/commonPath.jsp"/>
	<script type="text/javascript">
		//五秒倒计时跳转
		function timer(intDiff,surl){
			window.setInterval(function(){
				var second=0;//时间默认值
				if(intDiff > 0){
					second = Math.floor(intDiff);
				}
				$('#second_show').html('<s></s>'+second+'秒');

				if(--intDiff<0){
					location.href=surl;
				}
			}, 1000);
		}

		$(function(){
			timer(4,"${webRoot}/showLogin");
		});

	</script>

</head>
<body>
	<div class="suss_msg"  align="center" style="width:416px; margin:50px auto">
		<h1>恭喜您注册成功</h1>
		<div class="time-item">
			<h2><strong id="second_show">5秒</strong>后将跳转到登录页</h2>
		</div>
		<p>若未能成功跳转：<a href="${webRoot}/showLogin">请点击这里</a></p>
	</div>
</body>
</html>