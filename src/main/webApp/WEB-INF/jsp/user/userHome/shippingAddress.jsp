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
					<div class="user_baseInfo">
						<form id="editShippingAddressForm" class="form-horizontal" method="post" action="${webRoot }/userHome/submitConsigneeAddress">
							<fieldset>
								<legend><strong>收货地址</strong></legend>
								<!-- 使用session防止表单的重复提交 -->
								<c:set var="rand"><%= new java.util.Date().getTime()+"" %></c:set>
								<c:set var="token" scope="session" value="${rand }"/>
								<input type="hidden" name="token" value="${rand }"/>
								<input type="hidden" name="addressId" value="${consigneeAddressItem.addressId }"/>
								<input type="hidden" name="locationId" value="${consigneeAddressItem.location.locationId }"/>

								<h5><font style="color:#f40;">
									<c:choose><c:when test="${empty consigneeAddressItem}">新增收货地址</c:when><c:otherwise>更新收货地址</c:otherwise></c:choose>
								</font>&nbsp;&nbsp; 打<i>&nbsp;*&nbsp;</i>号内容为必填内容</h5>
								<div class="form-group">
									<label class="col-xs-2 control-label">所在地区<i>*</i></label>
									<div class="col-xs-8 ">
										<div class="row">
											<div class="col-xs-4 select-param">
												<select class="form-control input-sm " name="consigneeProvince" id="consigneeProvince" required>
													<option>- 请选择 -</option>
													<c:forEach items="${provinces }" var="consigneeProvince">
														<option value="${consigneeProvince.zipCode }" <c:if test="${consigneeProvince.zipCode == consigneeAddressItem.location.province.zipCode }">selected = "selected"</c:if>>${consigneeProvince.provinceName }</option>
													</c:forEach>
												</select>
											</div>
											<div class="col-xs-4 select-param ">
												<select class="form-control input-sm " name="consigneeCity" id="consigneeCity">
													<option value="">- 请选择 -</option>
													<c:forEach items="${cities }" var="consigneeCity">
														<option value="${consigneeCity.zipCode }" <c:if test="${consigneeCity.zipCode == consigneeAddressItem.location.city.zipCode }">selected = "selected"</c:if>>${consigneeCity.cityName }</option>
													</c:forEach>
												</select>
											</div>
											<div class="col-xs-4 select-param">
												<select class="form-control input-sm " name="consigneeDistrict" id="consigneeDistrict">
													<option value="">- 请选择 -</option>
													<c:forEach items="${districts }" var="consigneeDistrict">
														<option value="${consigneeDistrict.zipCode }" <c:if test="${consigneeDistrict.zipCode == consigneeAddressItem.location.district.zipCode }">selected = "selected"</c:if>>${consigneeDistrict.districtName }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-xs-2">
										<p class="help-block" id="consigneeAddMsg" style="color:#f40;"></p>
									</div>

								</div>
								<div class="form-group">
									<label for="address" class="col-xs-2 control-label">详细地址<i>*</i></label>
									<div class="col-xs-8">
										<textarea id="address" name="address" class="form-control input-sm" rows="3" placeholder="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息" required>${consigneeAddressItem.location.address }</textarea>
									</div>
								</div>
								<div class="form-group">
									<label for="zipCode" class="col-xs-2 control-label">邮政编码</label>
									<div class="col-xs-4">
										<input type="text" name="zipCode" id="zipCode" class="form-control input-sm" value="${consigneeAddressItem.zipCode }" placeholder="您的邮政编码">
									</div>
								</div>
								<div class="form-group">
									<label for="consignee" class="col-xs-2 control-label">收件人姓名<i>*</i></label>
									<div class="col-xs-4">
										<input type="text" name="consignee" id="consignee" class="form-control input-sm" value="${consigneeAddressItem.realName }" placeholder="填写收件人的名称" required>
									</div>
								</div>
								<div class="form-group">
									<label for="mobile" class="col-xs-2 control-label">联系电话<i>*</i></label>
									<div class="col-xs-4">
										<input type="text" name="mobile" id="mobile" class="form-control input-sm" value="${consigneeAddressItem.mobilePhone }" placeholder="收件的联系电话" required>
									</div>
								</div>
								<div class="form-group">
									<label for="isDefault" class="col-xs-2 control-label"></label>
									<div class="col-sm-4 checkbox-control">
										<input type="checkbox" id="isDefault" name="isDefault" value="1" <c:if test="${!empty user.defaultAddress && user.defaultAddress.addressId == consigneeAddressItem.addressId }">checked=checked</c:if>>
										<span>设置为默认</span>
									</div>
								</div>
								<div class="form-group ">
									<div class="col-sm-offset-2 col-sm-1">
										<button type="button" class="btn btn-success btn-sm" id="submitAdd">提交</button>
									</div>
									<c:if test="${!empty consigneeAddressItem }">
									<div class="col-sm-1">
										<a href="${webRoot}/userHome/shippingAddress" class="btn btn-primary btn-sm" id="quitRevise">取消</a>
									</div>
									</c:if>
									<div class="col-xs-4">
									<c:if test="${requestScope.backMsg != null }">

										  <span class="alert-success" id="addrBackMsg">${requestScope.backMsg }</span>
									</c:if>
									</div>
								</div>
							</fieldset>
						</form>


						<div class="table-responsive address-dataTable">
							<table class="table  table-hover">
								<thead>
									<tr>
										<th>收货人</th>
										<th>所在地区</th>
										<th>详细地址</th>
										<th>邮编</th>
										<th>联系电话</th>
										<th>操作</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="consigneeItems">
									<c:forEach items="${addresses }" var="address">
									<tr>
										<td>${address.realName }</td>
										<td>${address.location.province.provinceName } &nbsp; ${address.location.city.cityName } &nbsp; ${address.location.district.districtName }</td>
										<td>${address.location.address }</td>
										<td>${address.zipCode }</td>
										<td>${address.mobilePhone }</td>
										<td>
											<a href="${pageContext.request.contextPath }/userHome/reviseConsigneeAddress?addressId=${address.addressId }" class="updateAddress">修改</a>|
											<a href="javascript:;" class="deleteAddress" addressData="${address.addressId }">删除</a>
										</td>
										<td>
											<c:if test="${!empty user.defaultAddress && user.defaultAddress.addressId == address.addressId }">
											<span class="label label-warning">默认地址</span>
											</c:if>
											<c:if test="${empty user.defaultAddress || user.defaultAddress.addressId != address.addressId }">
											<a href="javascript:;" addressData="${address.addressId }" class="btn btn-danger btn-xs changeDefaultAddr">设为默认</a>
											</c:if>
										</td>
									</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
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
	$(function(){
		//初始化页面的显示效果
		initPage();
		
		//保存按钮点击事件
		$("#submitAdd").click(function(){
			var flag = true;
			//检查地址的设置是否合法（省市县不能部分为空）
			if($("#consigneeDistrict option:selected").val() == ""){
				//相应的提示操作
				$("#consigneeAddMsg").text("请选择地区");
				flag = false;
			}

			if(flag){
				$.ajaxSubmit($("form"),function(data){
					//登陆成功
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

		//删除地址点击事件
		$(".deleteAddress").click(function(){
			var addressId = $(this).attr("addressData");
			var locationId = $(this).attr("locationData");
			layer.confirm('您确认删除该地址?', {icon: 3, title:'提示'}, function(index){
				var indexLoad = layer.load(2);
				//发送请求
				$.ajax({
					type: "GET",
					url: "${webRoot }/userHome/deleteConsigneeAddress",
					data: "addressId="+addressId+"&time="+new Date(),
					success: function(data){
						if(0 == data.error){
							location.href.reload(true);
						}else{
							layer.msg(data.message, {
								icon: 1,
								time: 1500
							});
						}
					},
					complete:function (XMLHttpRequest, textStatus) {
						layer.close(indexLoad);
					}
				});
				layer.close(index);
			});
			 
		});

		//重置默认地址
		$(".changeDefaultAddr").click(function(){

			var indexLoad = layer.load(2);
			//发送请求
			$.ajax({
				type: "GET",
				url: "${webRoot }/userHome/changeDefaultAddress",
				data: "addressId="+$(this).attr("addressData")+"&time="+new Date(),
				success: function(data){
					if(0 == data.error){
						location.href=data.data;
					}else{
						layer.msg(data.message, {
							icon: 1,
							time: 1500
						});
					}
				},
				complete:function (XMLHttpRequest, textStatus) {
					layer.close(indexLoad);
				}
			});
		})
		
		//设置鼠标hover事件的JS动态效果
		$("#consigneeItems").find("tr").hover(
	        function () {          
	        	$(this).find(".changeDefaultAddr").attr("style","display:block;");   
	        },
	        
	        function () {
	        	$(this).find(".changeDefaultAddr").attr("style","display:none;");
	        }
	    );
	
	
		/*
		 * 收货地址三级联动
		 */
		 //第一个select多选栏的change事件
		$("#consigneeProvince").change(function(){
			//删除后两个 <select> 中除去第一个之外的其他option元素
			$("#consigneeCity option:not(:first)").remove()
			$("#consigneeDistrict option:not(:first)").remove()
			var provinceCode = $(this).val();
			
			if(provinceCode != ""){
				var url = "${webRoot}/region/listCity";
				var args = {"provinceCode":provinceCode, "time":new Date()};
				
				//发送请求
				$.getJSON(url, args, function(data){
					for(var i = 0;i<data.length; i++){
 						$("#consigneeCity").append("<option value='"+data[i].zipCode+"'>"+data[i].cityName+"</option>");
					}
					
				});
			}
			if($("#consigneeDistrict").val()==""){
				$("#address").attr("disabled","disabled");
			}
			return false;
		});
		
		//第二个select多选栏的change事件
		$("#consigneeCity").change(function(){
			//删除最后一个 <select> 多选栏中除去第一个之外的其他option元素
			$("#consigneeDistrict option:not(:first)").remove()
			var cityCode = $(this).val();
			
			if(cityCode != ""){
				var url = "${webRoot}/region/listDistrict";
				var args = {"cityCode":cityCode, "time":new Date()};
				
				//发送请求
				$.getJSON(url, args, function(data){
					for(var i = 0;i<data.length; i++){
 						$("#consigneeDistrict").append("<option value='"+data[i].zipCode+"'>"+data[i].districtName+"</option>");
					}
					
				});
			}
			if($("#consigneeDistrict").val()==""){
				$("#address").attr("disabled","disabled");
			}
			return false;
		});
		// 第三个select多选栏的change事件
		$("#consigneeDistrict").change(function(){
			if($(this).val() != ""){
				$("#address").removeAttr("disabled");
				$("#consigneeAddMsg").text("");
			}
			
			else
				$("#address").attr("disabled","disabled");
				
		});
	});

	function initPage(){
		//设置详细地址填写框效果
		if($("#consigneeDistrict").val()==""){
			$("#address").attr("disabled","disabled");
		}

		//设置保存成功后的信息框显示效果
	}
	

  </script>  
</body>
</html>