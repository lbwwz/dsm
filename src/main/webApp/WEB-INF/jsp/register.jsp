<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="common/commonPath.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新用户注册</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="${rsRoot}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${rsRoot}/css/non-responsive.css" rel="stylesheet">

    <link href="${rsRoot}/css/style-reg.css" rel="stylesheet">

    <script src="${rsRoot}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${rsRoot}/js/bootstrap.min.js" type="text/javascript"></script>

    <%--校验工具--%>
    <script type="text/javascript" src="${rsRoot}/js/ValidateTools.js"></script>
    <script type="text/javascript" src="${rsRoot}/js/ajaxSubmit.js"></script>

</head>
<body>
<%--<jsp:include page="common/topNav.jsp" />--%>
<div class="container">
    <h2 class="page-header">新用户注册</h2>
    <form id="registerForm" class="form-horizontal" action="/register" method="post">
        <fieldset>
            <legend>用户注册</legend>
            <!-- 使用session防止表单的重复提交 -->
            <c:set var="rand"><%= new java.util.Date().getTime() + "" %></c:set>
            <c:set var="registerToken" scope="session" value="${rand }"/>
            <input type="hidden" name="registerToken" value="${rand }"/>
            <div class="form-group">
                <label for="userName" class="col-xs-2 control-label">昵称</label>
                <div class="col-xs-4">
                    <input type="text" name="userName" class="form-control " id="userName" placeholder="设置您的昵称">
                </div>
                <div class="col-xs-6">
                    <p class="help-block" id="userName_check"></p>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-xs-2 control-label">密码</label>
                <div class="col-xs-4">
                    <input type="password" name="password" id="password" class="form-control " placeholder="设置您的密码">
                </div>
                <div class="col-xs-6">
                    <p class="help-block" id="password_check"></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 control-label" for="confirmPassword">确认密码</label>
                <div class="col-xs-4">
                    <input type="password" name="confirmPassword" class="form-control " id="confirmPassword"
                           placeholder="重复上述密码"/>
                </div>
                <div class="col-xs-6">
                    <p class="help-block" id="confirmPassword_check"></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 control-label" for="email">电子邮箱</label>
                <div class="col-xs-4">
                    <input id="email" name="email" class="form-control " type="email" placeholder="请输入邮箱"/>
                    <div class="col-xs-6">
                        <p class="help-block" id="email_check"></p>
                    </div>
                </div>
                <div class="col-xs-6">
                    <p class="help-block"></p>
                </div>
            </div>

            <div class="form-group">
                <label for="mobile" class="col-xs-2 control-label">手机号</label>
                <div class="col-xs-4">
                    <input type="text" name="mobile" class="form-control " id="mobile" placeholder="设置您的手机号码">
                </div>
                <div class="col-xs-6">
                    <p class="help-block" id="mobile_check"></p>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">您的性别</label>
                <div class="col-xs-4">
                    <label class="radio-inline">
                        <input type="radio" name="sex" value="0" checked="checked">保密</label>
                    <label class="radio-inline">
                        <input type="radio" name="sex" value="1">男</label>
                    <label class="radio-inline">
                        <input type="radio" name="sex" value="2">女</label>
                </div>
            </div>

        </fieldset>
        <div class="form-group ">
            <div class="col-xs-offset-2 col-xs-4">
                <button type="button" id="register_button" class="btn btn-success">确认注册</button>
            </div>
        </div>
    </form>
</div> <!-- /container -->

<script src="${rsRoot}/front-lib/layer/layer.js"></script>
<script type="text/javascript">
    $(function(){
        //注册按钮表单提交事件
        $("#register_button").click(function(){

            var flag = true;

            //需要校验的信息
            var userName = $.trim($("#userName").val());
            var password = $.trim($("#password").val());
            var confirmPassword = $.trim($("#confirmPassword").val());
            var email = $.trim($("#email").val());
            var mobile = $.trim($("#mobile").val());

            if(userName == ""){
                $("#userName_check").html('<span class="glyphicon glyphicon-remove-sign"></span>&nbsp;昵称不能为空');
                flag = false;
            }
            if(password  == ""){
                $("#password_check").html('<span class="glyphicon glyphicon-remove-sign"></span>&nbsp;密码不能为空')
                flag = false;
            }

            if(password != "" && password != confirmPassword){
                $("#confirmPassword_check").html('<span class="glyphicon glyphicon-remove-sign"></span>&nbsp;两次输入的密码不一致')
                flag = false;
            }

            if(email == ""){
                $("#email_check").html('<span class="glyphicon glyphicon-remove-sign"></span>&nbsp;邮箱不能为空')
                flag = false;
            }else if(!checkEmail(email)){
                $("#email_check").html('<span class="glyphicon glyphicon-remove-sign"></span>&nbsp;请输入正确的邮箱')
                flag = false;
            }
            if(mobile != "" && !checkMobile(mobile)){
                $("#mobile_check").html('<span class="glyphicon glyphicon-remove-sign"></span>&nbsp;请输入正确的电话号码')
                flag = false;
            }

            if(flag){
                //表单提交前校验表单提交的信息的合法性
                $.ajaxSubmit($("#registerForm"),function(data){
                    //注册成功
                    if(0 == data.error){
                        //跳转相应页面
                        location.href=data.data;
                    }else{
                        layer.msg(data.message, {
                            icon: 2,
                            time: 1000
                        });
                    }
                })
            }
            return false;

        })
    })
</script>
</body>
</html>