<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="content-language" content="zh-CN" />
	<title>D.S Mall-我是卖家</title>

	<link href="${rsRoot}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${rsRoot}/css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="${rsRoot}/css/non-responsive.css" rel="stylesheet">
	<!-- 侧边栏菜单 -->
	<link href="${rsRoot}/css/menuNav.css" rel="stylesheet">

	<script src="${rsRoot}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${rsRoot}/js/bootstrap.min.js" type="text/javascript"></script>
	<!-- style-seller 样式 -->
	<link href="${rsRoot}/css/style-seller.css" rel="stylesheet">
	<link href="${rsRoot}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<!-- kindeditor-4.1.10 -->
	<%--<link rel="stylesheet" href="${rsRoot}/front-lib/kindeditor-master/plugins/code/prettify.css" />--%>
	<script src="${rsRoot}/front-lib/kindeditor-master/kindeditor-all.js" type="text/javascript"></script>
	<script src="${rsRoot}/front-lib/kindeditor-master/lang/zh-CN.js" type="text/javascript"></script>
	<script src="${rsRoot}/front-lib/kindeditor-master/plugins/code/prettify.js" type="text/javascript"></script>
	<jsp:include page="../../common/commonPath.jsp"/>

			
<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>

<body>
	<jsp:include page="../../common/topNav.jsp" />
	<div class="container pageLogo">
	    <div class="row">
	        <div class="col-xs-5"><a href="index.jsp"><img src="${pageContext.request.contextPath }/images/logo-seller.png" alt=""/></a></div>
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
								<a href="javascript:;">
									<i class="glyphicon glyphicon-th-large"></i>&nbsp;卖家首页
								</a>
							</li>
							<li>
								<a href="#transactionManage" class="nav-header collapsed" data-toggle="collapse">
									<i class="glyphicon glyphicon-cog"></i>&nbsp;交易管理
									<span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
								</a>
								<ul id="transactionManage" class="nav nav-list secondmenu collapse" style="height: 0px;">
									<li><a href="javascript:;"><i class="glyphicon glyphicon-th-list"></i>&nbsp;评价管理</a></li>
									<li><a href="javascript:;"><i class="glyphicon glyphicon-user"></i>&nbsp;已卖出的宝贝</a></li>
								</ul>
							</li>
							<li>
								<a href="#shippingManage" class="nav-header collapsed" data-toggle="collapse">
									<i class="glyphicon glyphicon-cog"></i>&nbsp;物流管理
									<span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
								</a>
								<ul id="shippingManage" class="nav nav-list secondmenu collapse" >
									<li><a href="javascript:;"><i class="glyphicon glyphicon-user"></i>&nbsp;发货</a></li>
									<li><a href="javascript:;"><i class="glyphicon glyphicon-th-list"></i>&nbsp;物流工具</a></li>
								</ul>
							</li>
							<li class="active">
								<a href="#goodsManage" class="nav-header " data-toggle="collapse">
									<i class="glyphicon glyphicon-cog"></i>&nbsp;宝贝管理
									<span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
								</a>
								<ul id="goodsManage" class="nav nav-list secondmenu collapse in">
									<li class="active"><a href="javascript:;"><i class="glyphicon glyphicon-user"></i>&nbsp;发布宝贝</a></li>
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
                <div class="panel panel-default">
	                <div class="panel-heading">
	                	<div class="row">
							<div class="col-xs-8">
								<span><strong>产品信息</strong> &nbsp;&nbsp;<small>当前类目:&nbsp;
								<c:forEach items="${requestScope.productCatMenu }" var="productCatItem">
								<font color="blue">>></font>${productCatItem.catName}
								</c:forEach>
								</small></span>
							</div>
							<div class="col-xs-4">
								<button type="button" class="btn btn-default btn-xs pull-right" id="changeCatalog">修改分类</button>
							</div>
						  
						</div><!-- /.row -->
	                </div>
               		<form id="addProductInfo" method="post"  class="form-horizontal" action="${webRoot}/user/seller/releaseProductInfo">
					    <fieldset>
							
						<!-- 使用session防止表单的重复提交 -->
						<c:set var="rand"><%= new java.util.Date().getTime()+"" %></c:set>
						<c:set var="token" scope="session" value="${rand }"/>
						
						<input type="hidden" name="token" value="${rand }"/>
						<input type="hidden" name="catId" value="${catId }"/>
						<div class="panel-body">
							<!-- 宝贝常规属性设置 -->
							<div class="routineAttrSetter">
			                	<h4>1.宝贝常规信息
	                                <small>（所有宝贝的共有属性信息）</small>
	                            </h4>
	                            <div class="form-group ">
							        <label class="col-xs-2 control-label">宝贝标题</label>
							        <div class="col-xs-4 ">
							        	<input type="text" name="productName" class="form-control input-sm" placeholder="填写宝贝的名称，字数30字以内" required>
							        </div>
							        <div class="col-xs-6">
								        <p class="help-block"  ></p>
							        </div>
							        
						      	</div>
	                            <div class="form-group ">
							        <label class="col-xs-2 control-label">品牌</label>
							        <div class="col-xs-4 ">
										<select class="form-control input-sm" name="brand">
				                        	<option value="0">--请选择--</option>
				                       		<c:forEach items="${requestScope.brandBean }" var="brandBean">
				                            <option value="${brandBean.brandId }">${brandBean.btandName }</option>
				                        	</c:forEach>
				                        </select>							        </div>
							        <div class="col-xs-4">
<!-- 								         -->
										<div class="alert alert-warning sideAlert" role="alert">&nbsp;
									    	<strong>没有该品牌?</strong>&nbsp; 您可以&nbsp;
									    	<u><a href="javascript:;" class="">添加品牌</a></u>&nbsp;
									    </div>
							        </div>
							        
						      	</div>
	                            <div class="form-group ">
							        <label class="col-xs-2 control-label">市场价</label>
							        <div class="col-xs-4 ">
							        	<input type="text" name="productMarketPrice" class="form-control input-sm" placeholder="填写宝贝的名称，字数30字以内" required>
							        </div>
							        <div class="col-xs-6">
								        <p class="help-block" >（市场价是商品的标签价格）</p>
								        
							        </div>
							        
						      	</div>
	                            <div class="form-group ">
							        <label class="col-xs-2 control-label">商品关键字</label>
							        <div class="col-xs-4 ">
							        	<input type="text" name="keywords" class="form-control input-sm" placeholder="填写宝贝的名称，字数30字以内" required>
							        </div>
							        <div class="col-xs-6">
								        <p class="help-block" id="">（用于搜索，词数不超过6个，用英文的“;”分隔。）</p>
							        </div>
							        
						      	</div>
								<div class="form-group ">
									<label class="col-xs-2 control-label">商品简述</label>
									<div class="col-xs-4 ">
										<textarea type="text" rows="4" name="produceBrief" class="form-control input-sm" placeholder="填写宝贝简述，字数100字以内"></textarea>
									</div>
									<div class="col-xs-6">
										<p class="help-block" id="">（可以填写促销语，活动语等。）</p>
									</div>

								</div>
					      	</div>
					      	<!-- 宝贝基本属性 -->
							<div class="baseAttrSetter">
						      	<h4>2.宝贝基本属性
	                                <small>（具体类目下宝贝的基础属性信息，描述产品的型号类别等等）</small>
	                            </h4>

	                            <c:forEach items="${productAttrBeans}" var="productAttrBean" >
                            	<c:if test="${productAttrBean.isSale == 0 }">
	                            <div class="form-group ">
							        <label class="col-xs-2 control-label" title="${productAttrBean.attrName }" data_attrId="${productAttrBean.attrId}">${productAttrBean.attrName }
							        <c:if test="${productAttrBean.isMust == 1 }"><font color="#f40">*</font></c:if>
							        </label>
							        <div class="col-xs-4 selectBox">
							        	<div class="select-param">
						        			<select class="form-control input-sm" class="baseAttrValue" <c:if test="${productAttrBean.isMust == 1 }">required</c:if>>
													<%--<c:if test="${productAttrBean.isMust == 1 }">required</c:if>--%>>
					                        	<option value="">--请选择--</option>
					                       		<c:forEach items="${productAttrBean.attrValues }" var="attrValue">
					                            <option value="${attrValue.valueId }">${attrValue.valueName }</option>
					                        	</c:forEach>

					                        	<c:if test="${productAttrBean.isKey == 0 }">
					                        	<option value="#">自定义</option>
					                        	</c:if>
					                        </select>
						        		</div>
							        </div>
							        <p class="help-block" style="color:red;"></p>
									<input type="hidden" name="baseAttrInfo"  value=""/>
						      	</div>

                           		</c:if>
						      	</c:forEach>
						      	
						      	<div class="form-group">
							    	<label class="col-xs-2 control-label">自定义属性
							        </label>
							        <div class="col-xs-6">
							        	<div class="alert alert-warning formAlert" role="alert" >&nbsp;
									    	<strong>自定义?</strong>&nbsp; 请选择右侧&nbsp;<u>添加自定义</u>&nbsp;按钮添加属性
									    </div>
								    </div>
							        <div class="col-xs-2 ">
							        	<button type="button" class="btn btn-default btn-sm" id="addCustomBaseAttr">添加自定义</button>
							        </div>
						        </div>
					      	</div>
					      	
					      	<!-- 宝贝销售属性 -->
					      	<div class="saleAttrSetter">
						      	<h4>2.宝贝销售属性
	                                <small>（购买的用户可选择的信息，例如颜色，尺寸等等，不同的销售属性组合成一件SKU商品）</small>
	                            </h4>
	                            <c:set var="saleIndex" value="0" />  
	                            <c:forEach items="${productAttrBeans}" var="productAttrBean">
	                            <c:if test="${productAttrBean.isSale == 1 }">
	                            
	                            <div class="saleAttrSetterItem">
		                            <div class="form-group ">
								        <label class="col-xs-2 control-label">${productAttrBean.attrName }
								        <input type="hidden" value=""/>
<%-- 								        <c:if test="${productAttrBean.isMust == 1 }"><font color="#f40">*</font></c:if> --%>
								        </label>
								        <div class="col-xs-8 ">
								        	<c:forEach items="${productAttrBean.attrValues }" var="attrValue">
									        <label class="checkbox-inline">
											  	<input type="checkbox" value="${attrValue.valueId }" saleIndex="${saleIndex }" isKey="${productAttrBean.isKey }"  data="${attrValue.valueName }">
											  	${attrValue.valueName }
											</label>
				                        	</c:forEach>
								        </div>
								        <p class="help-block bg-danger" id="123"></p>
								        
							      	</div>
<!-- 							      	<div class="form-group"> -->
<!-- 								      	<div class="col-xs-offset-2 col-xs-8 customSaleAttrBox"> -->
<!-- 								      		<label class="checkbox-inline"> -->
<%-- 											  	<input type="checkbox" value="" data="${productAttrBean.attrName }" disabled="disabled"> --%>
<%-- 											  	<input type="text" name="" class="form-control input-sm" placeholder="其他${productAttrBean.attrName }" style="width: 100px;" /> --%>
<!-- 											</label> -->
<%-- 											<button type="button" attrNameData="${productAttrBean.attrName }" class="btn btn-default btn-sm addCustomSaleAttr" style="margin-top: 6.7px;"><small><span class="glyphicon glyphicon-trash"></span></small>&nbsp;添加一项</button> --%>
<!-- 								      	</div> -->
<!-- 							      	</div> -->
						      	</div>
							    <c:set var="saleIndex" value="${saleIndex+1}" />  
	                            </c:if>
						      	</c:forEach>
							      	<div class="skuProductSetter">
										<!--sku生成表 -->
										<table class="table table-bordered skuSetterTable">
											<thead>
												<tr style="background-color: #f9f9f9;">
												<c:forEach items="${productAttrBeans}" var="productAttrBean">
												<c:if test="${productAttrBean.isSale == 1 }">
													<th data="${productAttrBean.attrId }">${productAttrBean.attrName }</th>
												</c:if>
												</c:forEach>
													<th>价格</th>
													<th>数量</th>
													<th>商家编码</th>
												</tr>
											</thead>
											<tbody>

											</tbody>
										</table>
								</div>
	                		</div>
	                		<div class="imageSetter" >
						      	<h4>3.宝贝展示图片
	                                <small>（在商品详细页面显示的侧栏图片）</small>
	                            </h4>
								<div class="panel panel-default">
									<div class="panel-heading">
										<label class="control-label">请选择需要上传的图片</label>
										<a id="dv" class="fileInputContainer btn btn-default btn-sm pull-right"><span class="glyphicon glyphicon-folder-open"></span>&nbsp;<strong>上传图片</strong>
											<input type="file" name="productImgUpload" id="productImgUpload" multiple accept="image/gif, image/jpeg,  image/png, image/jpg"/>
										</a>
									</div>
									<div class="panel-body">
										<div class="imgContainter">
											<div class="clearfix imgInsertPoint"></div>
											<input id="mainImage" name="mainImgItem" type="hidden"/>
										</div>
									</div>
								</div>
								<h4>4.详细文描设置
	                                <small>（设置商品的文描。文描内容将在商品的详细说明中展示）</small>
		                        </h4>
	                			<div>
 								<%
								request.setCharacterEncoding("UTF-8");
 								String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
 								%>
 								<%=htmlData%>
									<textarea name="detailContent" cols="100" rows="8" style="width:100%;height:500px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
									<br />
									
 								<%!
 								private String htmlspecialchars(String str) {
 									str = str.replaceAll("&", "&amp;");
									str = str.replaceAll("<", "&lt;");
 									str = str.replaceAll(">", "&gt;");
 									str = str.replaceAll("\"", "&quot;");
 									return str;
 								}
 								%>
	                			</div>
		                		<h4>5.运费模块设置
	                                <small>（设置商品的运输的运费，运费价格将在商品详情页面显示）</small>
		                        </h4>
	                		</div>
	                		<div class="form-group ">
						        <label class="col-xs-2 control-label">运费模版</label>
						        <div class="col-xs-4 ">
									<select class="form-control input-sm" name="brande">
			                        	<option value="">--请选择--</option>
			                        </select>
								</div>
						        <div class="col-xs-5">
									<div class="alert alert-warning sideAlert" role="alert">&nbsp;
								    	<strong>没有合适的模块</strong>&nbsp; 您可以&nbsp;
								    	<u><a href="javascript:;" class="">添加新的运费模块</a></u>&nbsp;
								    </div>
						        </div>
					      	</div>
						</div>
						</fieldset>
						<div class="panel-footer">
							<div class="form-group" style="height: 15px;">
								<div class="col-xs-offset-5 col-xs-2">
									<button type="button" class="btn btn-success btn-sm btn-block" id="submitProductInfo">现在发布宝贝</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${rsRoot}/front-lib/layer/layer.js"></script>
	<script type="text/javascript" src="${rsRoot}/js/ajaxSubmit.js"></script>
	<script type="text/javascript" src="${rsRoot}/js/ajaxfileupload.js"></script>
	<%--suppress JSDuplicatedDeclaration--%>
	<script type="text/javascript">
	//文本编辑器初始化
	KindEditor.ready(function(K) {
		var editor = K.create('textarea[name="detailContent"]', {
			cssPath : '${rsRoot}/front-lib/kindeditor-master/themes/default/default.css',
			uploadJson : '${webRoot}/user/seller/uploadDetailImg',
			<%--fileManagerJson : '${rsRoot}/authorityPage/user/file_manager_json.jsp',--%>
			allowFileManager : false,
			resizeType : 1 ,//编辑器只能调整高度
			afterCreate : function() { //置入从数据库读取的值
				this.sync();
			},
			afterBlur:function(){	//编辑后的信息置入value中
				this.sync();
			}
		});
		prettyPrint();
	});

	$(function(){
    	//上传图片
    	$("#productImgUpload").change(function() {
			ajaxFileUpload();
    	});
    	$(".imgItem").hover(
			function(){
				$(this).children(".imgMask").slideDown(100);
			},
			function(){
				$(this).children(".imgMask").slideUp(100);
			}
		);
    	$.extend({
			'imgItemOperate':function(obj){
				$(obj).hover(
					function(){
						$(this).children(".imgMask").slideDown(100);
					},
					function(){
						$(this).children(".imgMask").slideUp(100);
					}
				);
				$(obj).find(".setMainImg").click(function(){
					$(".imgItem").removeAttr("id");
					$(this).parents(".imgItem").attr("id","mainImgMark");
					$("#mainImage").val($(this).parents(".imgItem").children("input").val());
				});
				$(obj).find(".removeImg").click(function(){
					if($(this).parents(".imgItem").attr("id") == "mainImgMark"){//若删除的是主图
						$("#mainImage").val($(".imgItem:first").attr("id","mainImgMark").children("input").val());
					}
					$(this).parents(".imgItem").remove();
				});
			}
		});
	});


	//上传的具体方法
    function ajaxFileUpload() {
        // 开始上传文件时显示一个图片
        $("#wait_loading").ajaxStart(function() {
            $(this).show();
        // 文件上传完成将图片隐藏起来
        }).ajaxComplete(function() {
            $(this).hide();
        });
		//var elementIds=["flag"]; //flag为id、name属性名
        $.ajaxFileUpload({
            url: webRoot+'/user/seller/uploadProductImg',
            type: 'post',
            securer: false, //一般设置为false
            fileElementId: 'productImgUpload', // 上传文件的控件id、name属性名
            dataType: 'json', //返回值类型，一般设置为json、application/json
            success: function(data){
				datas = data;
				console.log(data);
				var fileNames = []
                for(var i = 0; i<data.length; i++){
	                if(data[i].error != 0){
                		//提示错误信息
						fileNames.push(data[i].data);
	                }else{
						var imageItem = $(
								'<div class="imgItem">' +
								'	<span class="imgMask">' +
								'		<a class="setMainImg" href="javascript:;">设为主图</a>'+
								'		<a class="pull-right removeImg" href="javascript:;"><i class="glyphicon glyphicon-remove"></i></a>' +
								'	</span>'+
								'	<img src="'+data[i].data+'" />'+
								'	<input type="hidden" name="imgItem" value="'+data[i].data+'"/>' +
								'</div>').insertBefore($(".imgInsertPoint"));
						//新插入的 imageItem 元素对象绑定事件
						$.imgItemOperate(imageItem);
	                }
                }
				if(fileNames.length != 0){
					alert(fileNames.join(",")+" 图片上传失败！")
				}
                //插入操作完成 执行主图设置
				if($("#mainImgMark").length == 0){//若主图未设置
					$("#mainImage").val($(".imgItem:first").attr("id","mainImgMark").children("input").val());
				}
            },
            error: function(data, status, e){
//                 alert(e);
            }
        });
        var content = '\
            <input id="productImgUpload" name="productImgUpload" type="file" onchange="ajaxFileUpload();" multiple accept="image/gif, image/jpeg,  image/png, image/jpg"/>\
        ';
        $("#productImgUpload").remove();
        $("#dv").append(content);
        //return false;
    }

	function goPageSite(siteY){
		siteY = siteY >0?siteY:0;
		var timer=setInterval(function(){
			var currentPosition=window.pageYOffset|| document.documentElement.scrollTop || document.body.scrollTop;
			currentPosition-=60;
			if(currentPosition>siteY)  {
				window.scrollTo(0,currentPosition);
			}else {
				window.scrollTo(0,siteY);
				clearInterval(timer);
			}
		},1);
	}

	$(function(){
		// 提交产品信息按钮之前的检查操作
		$("#submitProductInfo").click(function(){
			var flag = true;
			//必填字段校验
			var $_requiredInput = $("form [required]");
			for(var i= 0;i<$_requiredInput.length; i++){
				if($($_requiredInput[i]).val() == ""){
					goPageSite($($_requiredInput[i]).offset().top-$(window).height()/2);
					layer.tips("请填写该信息",$_requiredInput[i]);
					$($_requiredInput[i]).focus();
					return false;
				}
			}

			$.ajaxSubmit($("#addProductInfo"),function(data){
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
		});
		
		//根目录 select多选栏的change事件
		$("select").change(function(){
			var attrValueId = $(this).val();
			
			if(attrValueId == "#"){
				var baseAttrName = $(this).parents(".selectBox").prev("label").text();
				$(this).parents(".selectBox").after('<div class="col-xs-4 aliasAttr">'
						+'<input type="text" class="aliasAttrValue" class="form-control input-sm"'
						+'placeholder="填写自定义的  '+baseAttrName+'" required></div>');
			}else{
				$(this).parents(".selectBox").next(".aliasAttr").remove();


			}
			var attrId = $(this).parents('.form-group ').find('label').attr("data_attrId");
			var attrName = $(this).parents('.form-group ').find('label').attr("title");
			var attrvalueName = $(this).find(":selected").text();
			$(this).parents('.form-group ').find('input:hidden').val(
					attrValueId == "" ? "" : attrId + "|" + attrName + "|" + attrValueId + "|" + (attrValueId == "#" ? "" : attrvalueName));
			return false;
		});

		$(".baseAttrSetter").on('input','.aliasAttrValue',function(){
			var $_submitInput = $(this).parents('.form-group ').find('input[name="baseAttrInfo"]');
			$_submitInput.val($_submitInput.val().substring(0,$_submitInput.val().lastIndexOf("|")+1)+$.trim($(this).val()));
		})
		
		/*
		*自定义基本属性的页面操作
		*/
		//添加自定义属性
		$("#addCustomBaseAttr").click(function(){
			$(this).parents(".form-group").after(
					'<div class="form-group">' +
					'	<div class="col-xs-offset-2 col-xs-2 ">'+
					'		<input type="text"  class="form-control input-sm customBaseAttrName" name="customBaseAttrName" placeholder="自定义属性名" required>' +
					'	</div>'+
					'	<div class="col-xs-4 ">'+
					'		<input type="text"  class="form-control input-sm customBaseAttrValue" name="customBaseAttrValue" placeholder="填写自定义的属性值，字数30字以内" required>'+
					'	</div>' +
					'	<div class="col-xs-2 ">' +
					'		<a href="javascript:;" class="btn btn-link deleteCustomBaseAttr" >'+
					'		<small><span class="glyphicon glyphicon-trash"></span></small>&nbsp;删除属性</a>'+
					'	</div>' +
					'	<p class="help-block bg-danger" id=""></p>' +
					'</div>');
			//删除自定义属性
			$(".deleteCustomBaseAttr").click(function(){
				$(this).parents(".form-group").remove();
//				$.resortCustomBaseAttr();
				return false;
			});
//			$.resortCustomBaseAttr();
			return false;
		});

		var saleAttrCount = $(".saleAttrSetterItem").length;	//销售属性个数
		$.extend({

			/*
			 * 定义销售属性的页面部分操作
			 */
			'getCheckSign':function(){
				var checkSign = [];
				for(var i=0; i<saleAttrCount; i++){
					checkSign[i] = $($(".saleAttrSetterItem").get(i)).find("input:checkbox:checked").length;
				}
				return checkSign;
			},

			/**
			 * 设置标志数组集合
			 *
			 * index 开始位置，一般为0，也就是第一位开始
			 * temp	用于临时记录的标志数组
			 * list	每项标志数组存放的集合
			 * checkSign 每个saleIndex位置选中销售属性对应的个数的记录数组
			 */
			'setDataList':function(index,temp,list,checkSign){
				if(index != 0){
					for(var i = 0;i<checkSign[index]||i==0;i++){
						if(index<checkSign.length-1){
							$.setDataList(index+1, temp, list, checkSign);
						}else{
							list.push(temp.concat());
							temp[temp.length-1]++;
						}
					}
					temp[index]=0;
					temp[index-1]++;
				}else{
					for(var i = 0;i<checkSign[index]||i==0;i++){
						if(index<checkSign.length-1){
							$.setDataList(index+1, temp, list, checkSign);
						}else{
							list.push(temp.concat());
							temp[temp.length-1]++;
						}
					}
				}
			},

			/**
			 * 获取所有的标志数组集合
			 *
			 * checkSign 每个saleIndex位置选中销售属性对应的个数的记录数组
			 */
			'getAllList':function(checkSign){
				var temp = new Array(checkSign.length);
				for(var i=0; i<temp.length; i++){
					temp[i] = 0;
				}
				var list = new Array();
				
				$.setDataList(0, temp, list, checkSign);
				return list;
			},

			/**
			 * 获取选中checkbox对应位置的标志数组集合(同时也是删除操作的signData属性的集合)
			 *
			 * Alllist 该checkded阶段所有的标志数组集合
			 * saleIndex 选中的属性对应的索引位置
			 * sort 选中属性的属性值对应的排序位置
			 */
			'getTheList':function(allList,saleIndex,sort){
				var list = new Array();
				for(var i = 0; i<allList.length; i++){
					if(allList[i][saleIndex] == sort){
						list.push(allList[i]);
					}
				}
				if(list.length == 0){
					for(var i = 0; i<allList.length; i++){
						if(allList[i][saleIndex] == (sort-1)){
							var temp = allList[i].concat();
	 						temp[saleIndex]++;
							list.push(temp);
						}
					}
				}
				return list
			},

			/**
			 * 获取插入点的tr的标志数组集合
			 *
			 * AllList 该checkded阶段所有的标志数组集合
			 * saleIndex 选中的属性对应的索引位置
			 * sort 选中属性的属性值对应的排序位置
			 */
			'getOperateList':function(allList,saleIndex,sort){
				var list = new Array();
				for(var i = 0; i<allList.length; i++){
					var count = Number(0);
					for(j=Number(saleIndex)+1;j<saleAttrCount; j++){
						count += allList[i][j];
					}
					if(allList[i][saleIndex] == sort && count == 0){
// 						var temp = allList[i].concat();
// 						temp[saleIndex]--;
						list.push(allList[i].concat());
					}
				}
				
				return list
			},

			'getInputChangeList':function(allList,saleIndex,sort){
				var list = new Array();
				for(var i = 0; i<allList.length; i++){
					if(allList[i][saleIndex] == sort){
						list.push(allList[i].concat());
					}
				}
				return list;
			},

			/**
			 * 标志数组集合转化SignData数组
			 */
			'changeListToSignDataArr':function(list){
				var arr = new Array();
				for(var i = 0; i<list.length; i++){
					arr[i] = list[i].join(":");
				}
				return arr;
			},

			'getCheckSignCount':function(checkSign,index){
				var temp = Number(1);
				for(var i = index+1; i<checkSign.length; i++){
// 					alert(i);
					if(checkSign[i] != 0)
						temp *= checkSign[i];
				}
				return temp;
			}
		});
		
		var aTd;	//销售属性对应skuSetterTable表中的生成对象
		for(var i = 0; i<saleAttrCount; i++){
			aTd = aTd+"<td></td>"
		}
		
		//"自定义sale属性控件"的事件操作函数
		$(".saleAttrSetterItem").find("input[type='checkbox']").click(function(){
			//设置sortData排序属性
			var removeSortData = $(this).attr("sortData");	//取消选中属性的属性值操作 checkbox对应的排序位置
			
			//重置checkbox sortData属性
			$(this).parents(".saleAttrSetterItem").find("input:checkbox").removeAttr("sortData");
			$(this).parents(".saleAttrSetterItem").find("input:checkbox:checked").each(function(i){
				$(this).attr("sortData",i);
			});
			/*
			* 获取其他基本参数
			*/
			var valueNameData = $(this).attr("data");
			var key = $(this).attr("iskey");
			var valueId = $(this).val();
			var saleIndex = $(this).attr("saleIndex");	//选中的属性对应的索引位置
			
			var checkSign = $.getCheckSign();
			
			var allList = $.getAllList(checkSign);
			var allSignDataArr = $.changeListToSignDataArr(allList);
			
			
			
			//
// 			alert("checkSign:"+checkSign);
// 			alert("signDataArr:"+$.changeListToSignDataArr($.getTheList(allList,saleIndex,removeSortData)));
			
			if($(this).is(":checked")){	//选取属性
				var insertSortData = $(this).attr("sortData");	//选中属性的属性值对应的排序位置
				//操作位置对应的signData	
				var insertSignDataArr =  $.changeListToSignDataArr($.getTheList(allList,saleIndex,insertSortData));
				if(key != 1){
					var oInputText = $('<input class="pull-right" type="text" saleIndex="'+saleIndex+'" sortData = "'+insertSortData
							+'" placeholder="自定义" style="width:60px; height:20px; font-size:12px;"/>')
							.appendTo($(this).parents(".checkbox-inline"));
					
					//定义自定义sku属性输入框的输入事件
					$(oInputText).on("input",function(){
						var inputSaleIndex = $(this).attr("saleIndex");
						var inputSortData = $(this).attr("sortData");
						var inputSignDataArr = $.changeListToSignDataArr($.getTheList($.getAllList($.getCheckSign())
								,inputSaleIndex,inputSortData));
						var value = $.trim($(this).val());
						if(value != ""){
//							alert(inputSignDataArr);
		 					for(var i = 0; i<inputSignDataArr.length; i++){
		 						var oTr = $(".skuSetterTable tbody tr[signData='"+inputSignDataArr[i]+"']");
		 						$(oTr).find("td:eq("+inputSaleIndex+")").text(value);
		 						var oldAttr = $(oTr).find("input[type='hidden']").val().split(";");
		 						var attrItem = oldAttr[inputSaleIndex].split("|");
		 						attrItem[3] = value;
		 						var newVal = "";
		 						for(var j = 0; j<oldAttr.length; j++){
		 							if(j == inputSaleIndex)
		 								newVal += attrItem.join("|")+";";
	 								else
	 									newVal += oldAttr[j]+";";
		 						}
		 						newVal = newVal.substring(0,(newVal.length-1));
		 						$(oTr).find("input[type='hidden']").val(newVal);
		 					}
		 				}else{
		 					for(var i = 0; i<inputSignDataArr.length; i++){
		 						var oTr = $(".skuSetterTable tbody tr[signData='"+inputSignDataArr[i]+"']");
	 							$(oTr).find("td:eq("+inputSaleIndex+")").text($(this).prev("input").attr("data"));
	 							
		 						var newVal = "";
	 							var attrId = $(".skuSetterTable thead tr").find("th:eq("+inputSortData+")").attr("data");
	 							var attrName = $(".skuSetterTable thead tr").find("th:eq("+inputSaleIndex+")").text();
	 							var valueId = $(this).prev("input").val();
	 							var valueName = $(this).prev("input").attr("data");
		 						var oldAttr = $(oTr).find("input[type='hidden']").val().split(";");
		 						for(var j = 0; j<saleAttrCount; j++){
		 							if(j == inputSaleIndex)
		 								newVal += attrId+"|"+valueId+"|"+attrName+"|"+valueName+";"
	 								else
	 									newVal += oldAttr[j]+";";
		 						}
	 							
		 						newVal = newVal.substring(0,(newVal.length-1));
		 						$(oTr).children("input").val(newVal);
		 					}
		 				}
		 			});
				}

				if($(".skuSetterTable tbody").find("tr").length==0){	//选中事件是第一项添加
					var oTr = $('<tr signData="'+insertSignDataArr[0]+'"><input type="hidden"/>'+aTd+'<td><input type="text" placeholder="商品价格"/></td><td><input type="text" placeholder="商品数量"/></td>'+
							'<td><input type="text" placeholder="卖家自定义编号"/></td></tr>').appendTo($(".skuSetterTable tbody"));
					oTr.find("td:eq("+saleIndex+")").append(valueNameData).attr("data",valueId);
				}else{
					
					if(checkSign[saleIndex] == 1){	//属性只有一项该属性值一条值
						for(var i=0; i<insertSignDataArr.length; i++){
							$(".skuSetterTable tbody tr[signData='"+insertSignDataArr[i]+"']").find("td:eq("+saleIndex+")")
								.append(valueNameData).attr("data",valueId);
						}
					}else{ //属性有不止一条属性值
						
						var operateSignDataArr = $.changeListToSignDataArr($.getOperateList(allList,saleIndex,insertSortData));
						
						if(checkSign[saleIndex] == Number(insertSortData)+1){	 //属性值为当前属性最后一项()
							for(var i=0; i<operateSignDataArr.length; i++){
								var operateSignData = insertSignDataArr[i].split(":")
								operateSignData[saleIndex] = Number(operateSignData[saleIndex])-1;
								for(var j = Number(saleIndex)+1; j<saleAttrCount; j++){
									operateSignData[j] = checkSign[j] == 0?0:(checkSign[j]-1);
								}
								var temp = operateSignData.slice(0,Number(saleIndex)+1);
								$(".skuSetterTable tbody tr[signData^='"+temp.join(":")+"']")
									.clone().insertAfter($(".skuSetterTable tbody tr[signData='"+operateSignData.join(":")+"']"))
									.find("td:eq("+saleIndex+")").attr("data",valueId).html(valueNameData);
							}
						}else{	//属性为中间项
							for(var i=0; i<operateSignDataArr.length; i++){
								var temp = operateSignDataArr[i].split(":").slice(0,Number(saleIndex)+1);
								
								$(".skuSetterTable tbody tr[signData^='"+temp.join(":")+"']").clone()
									.insertBefore($(".skuSetterTable tbody tr[signData='"+operateSignDataArr[i]+"']"))
									.find("td:eq("+saleIndex+")").attr("data",valueId).html(valueNameData);
							}
						}
						
					}
				}
				
			}
			else{	//如果取消选中
				$(this).next("input[type='text']").remove();
				//需要删去的表格元素项的  signData 属性
				var removeSignDataArr = $.changeListToSignDataArr($.getTheList(allList,saleIndex,removeSortData));
				if(checkSign[saleIndex] == 0 && $(".saleAttrSetter").find("input:checkbox:checked").length != 0){
					
					for(var i=0; i<removeSignDataArr.length; i++){
						$(".skuSetterTable tbody tr[signData='"+removeSignDataArr[i]+"']").find("td:eq("+saleIndex+")").html("");
					}
				}else{
					for(var i=0; i<removeSignDataArr.length; i++){
						$(".skuSetterTable tbody tr[signData='"+removeSignDataArr[i]+"']").remove();
					}
				}
			}
			
//			提交数据赋值
//			$(this).parents(".saleAttrSetterItem").find("input:checkbox:checked").each(function(i){
//				$(this).next("input").attr("name","customSaleAttrValue"+(Number($(this).attr("saleIndex"))+1)+":"
//						+(Number($(this).attr("sortData"))+1)).attr("sortdata",$(this).attr("sortData"));
//			});
			
			/*
			 * 重构表格信息和设置css样式信息
			 */
			$(".skuSetterTable tbody tr td").removeAttr("class");
			
			//顺序为signData赋值，并设置隐藏文件域的value属性和name值
			$(".skuSetterTable tbody tr").each(function(i){
				$(this).attr("signData",allSignDataArr[i]);
				$(this).find("input:eq(1)").attr("name","productPrice");
				$(this).find("input:eq(2)").attr("name","productCount");
				$(this).find("input:eq(3)").attr("name","customProductNo");
				var value = "";
				for(var j = 0; j<saleAttrCount; j++){
					var valueName = $(this).find("td:eq("+j+")").text();
					if(valueName == "")
						continue;
					var attrId = $(".skuSetterTable thead tr").find("th:eq("+j+")").attr("data");
					var attrName = $(".skuSetterTable thead tr").find("th:eq("+j+")").text();
					var valueId = $(this).find("td:eq("+j+")").attr("data");
					value += attrId+"|"+valueId+"|"+attrName+"|"+valueName+";"
				}
				value = value.substring(0,(value.length-1));
				
				$(this).children("input").attr("name","property").val(value);
			});
			
			//设置需要合并的td隐藏
			$(".skuSetterTable tbody tr").each(function(){
				var next = $(this).next("tr");
				if(next.length != 0){
					var mark;
					for(var i = checkSign.length-1;i>=0;i--){
						if(checkSign[i] != 0){
							mark = i;
							break;
						}
						mark = i;
					}
					for(var j = 0;j<mark;j++){
						if($(this).find("td:eq("+j+")").text() == $(next).find("td:eq("+j+")").text()){
							$(next).find("td:eq("+j+")").attr("class","td_hidden");
						}
					}
				}
//					$(this).find("td").slice(0,(saleAttrCount-1)).attr("class","tr_hidden").removeAttr("rowspan")
			});
			
			for(var j = 0;j<saleAttrCount;j++){
				$(".skuSetterTable tbody tr").find("td:eq("+j+")[class!='td_hidden']").attr("rowspan",$.getCheckSignCount(checkSign,j))
			}
		});
	});
	</script>
</body>
</html>