<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../common/commonPath.jsp" />
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


<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>


<body>
	<jsp:include page="../../common/topNav.jsp" />
	<jsp:include page="common/accountHeadNav.jsp" />


	<div class="container">
        <div class="panel panel-default">
			<div class="panel-body userSetter-panel">
				<div class="row">
					<jsp:include page="common/accountSideNav.jsp" />
					<div class="col-xs-10 info-panel">
						<dl>
							<dt>您的基础信息</dt>
							<dd>
								<ul class="account-info">
									<li>
										<span class="tit">会员名</span>
										<span class="default grid-msg ">${user.userName }</span>
									</li>
									<li>
										<span class="tit">登录邮箱：</span>
										<span class="grid-msg ">${user.email }</span>
										<span class="tit">&nbsp;</span>
										<span class="modify"><a title="修改" href="https://passport.taobao.com/ac/email_modify.htm?fromSite=0">修改邮箱&nbsp;&nbsp;</a></span>
									</li>
									<li>
										<span class="tit">绑定手机：</span>
										<span class="default grid-msg">${!empty user.mobile && user.mobile != ""? user.mobile : "未绑定" }</span>
										<span class="tit">&nbsp;</span>
										<span class="modify" style="padding-right:0;">
											<a title="修改手机" style="text-decoration:none;" rel="很抱歉，您只能在5天内修改一次手机号码。<br/>离上次修改时间（）还剩0天" href="https://passport.taobao.com/ac/mobile_modify.htm?fromSite=0">修改手机&nbsp;&nbsp;</a>
										</span>
									</li>
								</ul>
							</dd>

                            <dt class="topline">您的安全服务</dt>
                            <dd>
                                <div class="row safe-progress">

                                    <div class="col-xs-2">安全等级：<span class="safe-level"></span></div>
                                    <div class="col-xs-4">
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="100" style="">
                                                <span class="sr-only"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        完成 <span><a href="javascript:;">身份认证</a></span> /<span><a href="javascript:;">密保设置</a></span> ，提升账户安全。
                                    </div>

                                </div>
                                <ul class="safe-info">
                                    <li class="item identity">
                                        <div class="row">
                                            <div class="col-xs-2 state">
                                                <span class="${user.promotedType==3?'safe-over':'safe-alert' }">${user.promotedType==3?"已设置":"未认证" }</span>
                                            </div>
                                            <div class="col-xs-2 title">身&nbsp;份&nbsp;认&nbsp;证</div>
                                            <div class="col-xs-6 supply">用于提升账号的安全性和信任级别。认证后的有卖家记录的账号不能修改认证信息。</div>
                                            <div class="col-xs-2 operate">
                                                <a title="认证" target="_blank" href="userHome/idAuthentication">认证</a>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="item password ">
                                        <div class="row">
                                            <div class="col-xs-2 state">
                                                <span class="safe-over">已设置</span>
                                            </div>
                                            <div class="col-xs-2 title">登&nbsp;录&nbsp;密&nbsp;码</div>
                                            <div class="col-xs-6 supply">安全性高的密码可以使账号更安全。建议您定期更换密码，且设置一个包含数字和字母，并长度超过6位以上的密码。</div>
                                            <div class="col-xs-2 operate">
                                                <a title="修改" target="_blank" href="https://passport.taobao.com/ac/password_modify.htm?fromSite=0">修改</a>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="item question">
                                        <div class="row">
                                            <div class="col-xs-2 state">
                                                <span class="${user.hasQuestion==1?'safe-over':'safe-alert' }">${user.hasQuestion==1?"已设置":"未设置" }</span>
                                            </div>
                                            <div class="col-xs-2 title">密&nbsp;保&nbsp;问&nbsp;题</div>
                                            <div class="col-xs-6 supply">是您找回登录密码的方式之一。建议您设置一个容易记住，且最不容易被他人获取的问题及答案，更有效保障您的密码安全。</div>
                                            <div class="col-xs-2 operate">
                                                <a title="设置" target="_blank" href="https://passport.taobao.com/ac/protection_add.htm?fromSite=0">设置</a>
                                            </div>
                                        </div>
                                    </li>

                                    <li class="item phonebind ">
                                        <div class="row">
                                            <div class="col-xs-2 state">
                                                <span class="${!empty user.mobile && user.mobile != ""?'safe-over':'safe-alert' }">${!empty user.mobile  && user.mobile != ""?"已绑定":"未绑定" }</span>
                                            </div>
                                            <div class="col-xs-2 title">绑&nbsp;定&nbsp;手&nbsp;机</div>
                                            <div class="col-xs-6 supply">绑定手机后，您即可享受淘宝丰富的手机服务，如手机找回密码等。</div>
                                            <div class="col-xs-2 operate">
                                                <a title="修改" style="text-decoration:none;" rel="很抱歉，您只能在5天内修改一次手机号码。<br/>离上次修改时间（）还剩0天" href="https://passport.taobao.com/ac/mobile_modify.htm?fromSite=0">${!empty user.mobile  && user.mobile != ""?"修改":"绑定" }</a>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </dd>
                        </dl>
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
        //设置进度条区效果
        $(function () {
            var level = $(".safe-over").length;
            var levelValue = null;
            var levelNum = null;
            var levelStyle = null;

            switch (level) {
                case 1:
                    levelValue = "差";
                    levelNum = 25;
                    levelStyle = "progress-bar progress-bar-danger";
                    break;
                case 2:
                    levelValue = "中";
                    levelNum = 50;
                    levelStyle = "progress-bar progress-bar-warning";
                    break;
                case 3:
                    levelValue = "良";
                    levelNum = 75;
                    levelStyle = "progress-bar progress-bar-info";
                    break;
                default:
                    levelValue = "优";
                    levelNum = 100;
                    levelStyle = "progress-bar progress-bar-success";
                    break;
            }

            $(".safe-level").text(levelValue);
            $(".progress>div").attr("class", levelStyle);
            $(".progress>div").attr("aria-valuenow", levelNum);
            $(".progress>div").attr("style", "width:" + levelNum + "%");

            //设置侧栏选中样式
            setCheckedBtn(0);

        });
	</script>
</body>
</html>