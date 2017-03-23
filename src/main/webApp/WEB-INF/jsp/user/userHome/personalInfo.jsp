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
	<jsp:include page="../../common/topNav.jsp"/>
	<jsp:include page="common/accountHeadNav.jsp"/>

	<div class="container">

		<div class="panel panel-default">

			<div class="panel-body userSetter-panel">
				<div class="row">
					<!-- 侧栏导航 -->
					<jsp:include page="common/accountSideNav.jsp"/>
					<div class="col-xs-10 info-panel">
						<ul class="nav nav-tabs">
							<li role="presentation" class="active"><a href="javascript:;">个人资料</a></li>
						</ul>
						<ul class="list-inline personal_setter">
							<li><a href="${webRoot}/userHome/personalInfo" id="actived">基本资料</a></li>
							<li><a href="${webRoot}/userHome/headImage">头像设置</a></li>
						</ul>
						<div class="user_baseInfo">
							<div class="alert alert-success" id="alertMsg" style="display:none;">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
								<span class="glyphicon glyphicon-ok-sign"></span> <span id="backMsg">${backMsg }</span>
							</div>
							<h5>亲爱的<strong>l***z</strong>，请填写基础的资料，有助于好友找到你哦。</h5>
							<form id="BaseInfo" action="${webRoot }/userHome/submitBaseInfo" class="form-horizontal" method="post">

								<!-- 使用session防止表单的重复提交 -->
								<c:set var="rand"><%= new java.util.Date().getTime()+"" %></c:set>
								<c:set var="token" scope="session" value="${rand }"/>
								<input type="hidden" name="token" value="${rand }"/>
								<div class="form-group">
							        <label class="col-sm-2 control-label" >头像</label>
							        <div class="col-xs-4 head_image">
								        <span class="bas-avatar-box">
								          	<a ><img src="${webRoot}/${user.headImage }"></a>
								          	<a href="javascript:;" class="edit-avatar" style="display: none;">编辑头像</a>
								        </span>
							        </div>
						      	</div>
						      	<div class="form-group">
							        <label class="col-xs-2 control-label" >*性别</label>
							        <div class="col-xs-3">
							        	<label class="radio-inline">
					          			<input type="radio" name="sex" value="0" <c:if test="${user.sex == 0 }">checked="checked"</c:if>>保密</label>
						       			<label class="radio-inline">
							          	<input type="radio" name="sex" value="1" <c:if test="${user.sex == 1 }">checked="checked"</c:if>>男</label>
					           			<label class="radio-inline">
							          	<input type="radio" name="sex" value="2" <c:if test="${user.sex == 2 }">checked="checked"</c:if>>女</label>
							        </div>
							        <div class="col-xs-6">
							        	<p class="help-block">*（性别可能会影响某些活动的参与）</p>
							        </div>
						      	</div>
							  	<div class="form-group">
							   		<label for="qq" class="col-xs-2 control-label">QQ号码</label>
								    <div class="col-xs-4">
								      	<input type="text" name="qq" id="qq" class="form-control input-sm" value="${user.qq }" placeholder="您的QQ号码">
								    </div>
							  	</div>

								<div class="form-group">
							        <label class="col-xs-2 control-label"  >生日</label>
							        <div class="col-xs-4 ">
							        	<input type="date" name="birthday" id="birthday" class="form-control input-sm" value="${user.birthday }" placeholder="您的生日">
							        </div>
							        <div class="col-xs-5">
							        	<p class="help-block">（出生年份为保密）</p>
							        </div>
						      	</div>

								<%--家乡标签--%>
								<div class="form-group">
				        			<c:set value="${hometown }" var="hometown" />
							        <label class="col-xs-2 control-label"  >家乡</label>
							        <div class="col-xs-8 ">
							        	<div class="row">
							        		<c:if test="${!empty user.hometown }">
								        		<div class="col-xs-4 select-param">
														<%--suppress XmlDuplicatedId --%>
								        			<select class="form-control input-sm " name="hometownProvince" id="hometownProvince">
								        				<option>- 请选择 -</option>
								        				<c:forEach items="${provinces }" var="hometownProvince">
									        				<option value="${hometownProvince.zipCode }" <c:if test="${hometownProvince.zipCode == user.hometown.province.zipCode }">selected = "selected"</c:if>>${hometownProvince.provinceName }</option>
								        				</c:forEach>
								        			</select>
								        		</div>
								        		<div class="col-xs-4 select-param">
														<%--suppress XmlDuplicatedId --%>
								        			<select class="form-control input-sm " name="hometownCity" id="hometownCity">
								        				<option value="">- 请选择 -</option>
								        				<c:forEach items="${hometownCityRs }" var="hometownCity">
									        				<option value="${hometownCity.zipCode }" <c:if test="${hometownCity.zipCode == user.hometown.city.zipCode }">selected = "selected"</c:if>>${hometownCity.cityName }</option>
								        				</c:forEach>
								        			</select>
								        		</div>
								        		<div class="col-xs-4 select-param">
														<%--suppress XmlDuplicatedId --%>
													<select class="form-control input-sm " name="hometownDistrict" id="hometownDistrict">
								        				<option value="">- 请选择 -</option>
								        				<c:forEach items="${hometownDistrictRs }" var="hometownDistrict" >
									        				<option value="${hometownDistrict.zipCode }" <c:if test="${hometownDistrict.zipCode == user.hometown.district.zipCode }">selected = "selected"</c:if>>${hometownDistrict.districtName }</option>
								        				</c:forEach>
								        			</select>
								        		</div>
							        		</c:if>
							        		<c:if test="${empty user.hometown}">
							        			<div class="col-xs-4 select-param">
														<%--suppress XmlDuplicatedId --%>
								        			<select id="hometownProvince" class="form-control input-sm" name="hometownProvince">
							                        	<option >--请选择--</option>
							                       	<c:forEach items="${provinces }" var="hometownProvince">
							                            <option value="${hometownProvince.zipCode }">${hometownProvince.provinceName }</option>
							                        </c:forEach>
							                        </select>
								        		</div>
							        			<div class="col-xs-4 select-param ">
														<%--suppress XmlDuplicatedId --%>
								        			<select id="hometownCity" class="form-control input-sm " name="hometownCity" >
								        				<option value="">- 请选择 -</option>
								        			</select>
								        		</div>
								        		<div class="col-xs-4 select-param">
														<%--suppress XmlDuplicatedId --%>
								        			<select class="form-control input-sm " name="hometownDistrict" id="hometownDistrict">
								        				<option value="">- 请选择 -</option>
								        			</select>
								        		</div>
							        		</c:if>
							        	</div>
							        </div>
							        <div class="col-xs-2">
							        	<p class="help-block" id="hometownMsg" style="color:red;"></p>
							        </div>

						      	</div>

								<%--居住地标签--%>
								<div class="form-group">
							        <label class="col-xs-2 control-label"  >居住地</label>
							        <div class="col-xs-8 ">
							        	<div class="row">
							        		<c:if test="${!empty user.domicile }">
								        		<div class="col-xs-4 select-param" name="hometownDistrict">
								        			<c:set value="${user.domicile }" var="domicile" />
								        			<select class="form-control input-sm " name="domicileProvince" id="domicileProvince">
								        				<option value="">- 请选择 -</option>
								        				<c:forEach items="${provinces }" var="domicileProvince">
									        				<option value="${domicileProvince.zipCode }" <c:if test="${domicileProvince.zipCode == user.domicile.province.zipCode }">selected = "selected"</c:if>>${domicileProvince.provinceName }</option>
								        				</c:forEach>
								        			</select>
								        		</div>
								        		<div class="col-xs-4 select-param">
													<%--suppress XmlDuplicatedId --%>
								        			<select class="form-control input-sm " name="domicileCity" id="domicileCity">
								        				<option value="">- 请选择 -</option>
								        				<c:forEach items="${domicileCityRs }" var="domicileCity">
									        				<option value="${domicileCity.zipCode }" <c:if test="${domicileCity.zipCode == user.domicile.city.zipCode }">selected = "selected"</c:if>>${domicileCity.cityName }</option>
								        				</c:forEach>
								        			</select>
								        		</div>
								        		<div class="col-xs-4 select-param">
													<%--suppress XmlDuplicatedId --%>
								        			<select class="form-control input-sm " name="domicileDistrict" id="domicileDistrict">
								        				<option value="">- 请选择 -</option>
								        				<c:forEach items="${domicileDistrictRs }" var="domicileDistrict">
									        				<option value="${domicileDistrict.zipCode }" <c:if test="${domicileDistrict.zipCode == user.domicile.district.zipCode }">selected = "selected"</c:if>>${domicileDistrict.districtName }</option>
								        				</c:forEach>
								        			</select>
								        		</div>
							        		</c:if>
							        		<c:if test="${empty user.domicile}">
							        			<div class="col-xs-4 select-param" name="domicileProvince">
														<%--suppress XmlDuplicatedId --%>
								        			<select class="form-control input-sm " name="domicileProvince" id="domicileProvince">
								        				<option value="">- 请选择 -</option>
								        				<c:forEach items="${provinces }" var="domicileProvince">
									        				<option value="${domicileProvince.zipCode }" >${domicileProvince.provinceName }</option>
								        				</c:forEach>
								        			</select>
								        		</div>
								        		<div class="col-xs-4 select-param">
														<%--suppress XmlDuplicatedId --%>
								        			<select class="form-control input-sm " name="domicileCity" id="domicileCity">
								        				<option value="">- 请选择 -</option>
								        			</select>
								        		</div>
								        		<div class="col-xs-4 select-param">
														<%--suppress XmlDuplicatedId --%>
								        			<select class="form-control input-sm " name="domicileDistrict" id="domicileDistrict">
								        				<option value="">- 请选择 -</option>
								        			</select>
								        		</div>
							        		</c:if>
							        	</div>
							        </div>
							        <div class="col-xs-2">
							        	<p class="help-block" id="domicileMsg" style="color:red;"></p>
							        </div>

						      	</div>


								<div class="form-group ">
								  	<div class="col-xs-offset-2 col-xs-4">
								    	<button id="submitInfo" type="button" class="btn  btn-success btn-sm">保存</button>
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
	<script src="${rsRoot}/js/ajaxSubmit.js"></script>
	<script src="${rsRoot}/front-lib/layer/layer.js"></script>
	<script type="text/javascript">
	//头像标签 :hover效果
	$(function(){
		$(".bas-avatar-box").mouseover(function(){
			$(".edit-avatar").attr("style","display:inline");
		});
		$(".bas-avatar-box").mouseleave(function(){
			$(".edit-avatar").attr("style","display:none");
		});
	});
	
	//使用 ajax提交用户的基本信息
	$(function(){
		$("#submitInfo").click(function(){
			var flag = true;
			//检查地址的设置是否合法（省市县不能部分为空）
			//检查家乡地址
			if($("#hometownProvince option:selected").val() != "" && $("#hometownDistrict option:selected").val() == ""){
				//相应的提示操作
				$("#hometownMsg").text("信息不完整！")
				flag = false;
			}
			//检查居住地地址
			if($("#domicileProvince option:selected").val() != "" && $("#domicileDistrict option:selected").val() == ""){
				//相应的提示操作
				$("#domicileMsg").text("信息不完整！")
				flag = false;
			}
			
			if(flag){
				$.ajaxSubmit($("form"),function(data){
					//保存成功
					if(0 == data.error){
						location.href=data.data;
					}else{
						layer.msg(data.message, {
							icon: 1,
							time: 2000
						});
					}
				})
			}	
			return false;
		});
		
	});
	
	/*
	 *select标签的三级联动
	 */
	$(function(){
		/*
		 * 家乡的三级联动
		 */
		 //第一个select多选栏的change事件
		$("#hometownProvince").change(function(){
			//删除后两个 <select> 中除去第一个之外的其他option元素
			$("#hometownCity option:not(:first)").remove()
			$("#hometownDistrict option:not(:first)").remove()
			var provinceCode = $(this).val();
			
			if(provinceCode != ""){
				var url = "${webRoot}/region/listCity";
				var args = {"provinceCode":provinceCode, "time":new Date()};
				
				//发送请求
				$.getJSON(url, args, function(data){
					for(var i = 0;i<data.length; i++){
 						$("#hometownCity").append("<option value='"+data[i].zipCode+"'>"+data[i].cityName+"</option>");
					}
					
				});
			}
			return false;
		});
		
		//第二个select多选栏的change事件
		$("#hometownCity").change(function(){
			//删除最后一个 <select> 多选栏中除去第一个之外的其他option元素
			$("#hometownDistrict option:not(:first)").remove()
			var cityCode = $(this).val();
			
			if(cityCode != ""){
				var url = "${webRoot}/region/listDistrict";
				var args = {"cityCode":cityCode, "time":new Date()};
				
				//发送请求
				$.getJSON(url, args, function(data){
					for(var i = 0;i<data.length; i++){
 						$("#hometownDistrict").append("<option value='"+data[i].zipCode+"'>"+data[i].districtName+"</option>");
					}
					
				});
			}
			return false;
		});
		
		/*
		 * 居住地的三级联动
		 */
		//第一个select多选栏的change事件
		$("#domicileProvince").change(function(){
			//删除后两个 <select> 中除去第一个之外的其他option元素
			$("#domicileCity option:not(:first)").remove()
			$("#domicileDistrict option:not(:first)").remove()
			var provinceCode = $(this).val();
			
			if(provinceCode != ""){
				var url = "${webRoot}/region/listCity";
				var args = {"provinceCode":provinceCode, "time":new Date()};
				
				//发送请求
				$.getJSON(url, args, function(data){
					for(var i = 0;i<data.length; i++){
 						$("#domicileCity").append("<option value='"+data[i].zipCode+"'>"+data[i].cityName+"</option>");
					}
					
				});
			}
			return false;
		});
		
		//第二个select多选栏的change事件
		$("#domicileCity").change(function(){
			//删除最后一个 <select> 多选栏中除去第一个之外的其他option元素
			$("#domicileDistrict option:not(:first)").remove()
			var cityCode = $(this).val();
			
			if(cityCode != ""){
				var url = "${webRoot }/region/listDistrict";
				var args = {"cityCode":cityCode, "time":new Date()};
				
				//发送请求
				$.getJSON(url, args, function(data){
					for(var i = 0;i<data.length; i++){
 						$("#domicileDistrict").append("<option value='"+data[i].zipCode+"'>"+data[i].districtName+"</option>");
					}
					
				});
			}
			return false;
		});
		
	});

	//修改信息之后的提示框
	$(function(){
		var msg = $("#backMsg").text();
		if(msg != ""){
			$("#alertMsg").attr("style","display:block;");
		}
	});
	</script>
</body>
</html>