<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<jsp:include page="../common/commonPath.jsp"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>D.S Mall - 管理员管理</title>
	<!-- Bootstrap Core CSS -->
	<link href="${rsRoot}/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

	<!-- MetisMenu CSS -->
	<link href="${rsRoot}/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

	<!-- Custom CSS -->
	<link href="${rsRoot}/css/sb-admin-2.css" rel="stylesheet">

	<!-- Custom Fonts -->
	<link href="${rsRoot}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

	
</head>

<body>

    <div id="wrapper">
		<!-- Navigation -->
		<jsp:include page="common-nav.jsp"/>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">添加类目</h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="authorityPage/admin/adminIndex.jsp">主页</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i> 更新商品类目
                            </li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                	<div class="col-lg-12">
	                	<div class="panel panel-default">
	                        <div class="panel-heading">商品类目结构
	                        	<div class="pull-right">
	                                <div class="btn-group">
	                                    <button type="button" class="btn btn-primary btn-xs" id="addCategory">添加根目录  <span class="glyphicon-plus"></span></button>
	                                </div>
	                            </div>
	                        </div>
	                        <!-- /.panel-heading -->
	                        <div class="panel-body">
	                			<div class="col-lg-6">
		                            <div class="table-responsive" >
		                            	<div class="header">
                                            <h4>商品根目录<small></small>
	                                            <button type="button" class="pull-right btn btn-primary btn-xs">保存排序  
	                                            	<i class="fa fa-save"></i>
	                                            </button>
                                            </h4>
                                        </div>
		                                <table class="table table-bordered" id="rootCatTable" >
			                                <thead>
			                                    <tr>
			                                        <th>类名</th>
			                                        <th>分类中显示</th>
			                                        <th>状态</th>
			                                        <th>操作</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                	<c:forEach items="${rootCat}" var="rootBean">
			                                    <tr>
			                                        <td><a data="${rootBean.catId }" href="javascript:;">${rootBean.catName }</a></td>
			                                        <td>${rootBean.showInNav == 1?"√":"x" }</td>
			                                        <td><span class="label ${rootBean.status == 1?"label-success":"label-default" }">${rootBean.status == 1?"启用":"禁用" }</span></td>
			                                        <td>
				                                        <a class="chanceCatStatus" data="${rootBean.catId }" title='状态切换' href='javascript:;'><i class='fa fa-eye-slash'></i></a>&nbsp;&nbsp;
														<a class="updateCatInfo" data="${rootBean.catId }" title='编辑' href='javascript:;'><i class='fa fa-pencil'></i></a>&nbsp;&nbsp;
														<a class="addChildCat" data="${rootBean.catId }" title='添加下级' href='javascript:;'><i class='fa fa-plus'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
				                                        <button type="button" class="btn btn-default btn-xs"><i class="fa fa-arrow-up"></i></button>
		                            					<button type="button" class="btn btn-default btn-xs"><i class="fa fa-arrow-down"></i> </button>
													</td>
			                                    </tr>
			                                	</c:forEach>
			                                </tbody>
			                            </table>
		                            </div>
		                            <!-- /.table-responsive -->
		                        </div>
		                        <div class="col-lg-6">
		                       		<div class="table-responsive">
		                       			<div class="header" id="catNameTitle">
                                            <h4>子目录<small></small>
                                            <button type="button" class="pull-right btn btn-primary btn-xs">保存排序 
                                            	<i class="fa fa-save"></i>
                                            	</button>
                                           	</h4>
                                        </div>
		                                <table class="table table-bordered" id="childCatTable">
			                                <thead>
			                                    <tr>
			                                        <th>类名</th>
			                                        <th>分类中显示</th>
			                                        <th>状态</th>
			                                        <th>操作</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                	<tr>
			                                    	<td colspan="4" align="center">请选择右侧类目</td>
			                                    </tr>
			                                </tbody>
			                            </table>
		                            </div>
		                            <!-- /.table-responsive -->
	                            </div>
		                        <!-- /.panel-body -->
		                    </div>
	                        
	                       
	                    </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
</body>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="${rsRoot}/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${rsRoot}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="${rsRoot}/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${rsRoot}/js/sb-admin-2.js"></script>
<script src="${rsRoot}/front-lib/layer/layer.js"></script>

<script type="text/javascript">
	//使用Ajax查询目录下的子目录
	$(function(){
		// 添加类目信息
		$("body").on("click","#addCategory,.addChildCat",function(){
			$.addCategory(this);
		});

		//查询父类目的所有子类目
		$("#rootCatTable tbody tr").find("a:eq(0)").click(function(){
			$.selectChildCat(this);
		});

		//改变类目状态按钮
		$("body").on("click",".chanceCatStatus",function(){
			$.changeStatus(this);
		});
		//修改类目信息
		$('body').on("click",".updateCatInfo", function(){
			$.updateCat(this);
		});

		//触发事件
		$.extend({
			'addCategory':function(el){
				var parentId = $(el).attr('data')?$(el).attr('data'):'0';

				var addCat = layer.open({
					type: 1,
					title: '添加根类目',
					area: ['450px', '210px'],
					shadeClose: false, //点击遮罩关闭
					content: '\<div class="containter"><form id="formAttr"> '+
					'<div class="form-group">' +
					'	<label for="inputCatName" class="col-sm-2 control-label">类目名</label>'+
					'	<div class="col-sm-9">' +
					'		<textarea  class="form-control" id="inputCatName" placeholder="请填写类目名称，多个名称之间用“,”分隔" required="required"></textarea>' +
					'	</div>' +
					'</div>'+
					'<div class="form-group" >' +
					'	<div class="col-sm-offset-2 col-sm-9">' +
					'		<div class="checkbox">'+
					'			<label><input id="showInNav" type="checkbox">目录菜单展示？</label>&nbsp;&nbsp;'+
					'			<label><input id="status" type="checkbox" >可用状态？</label></div></div>'+
					'		<div class="col-sm-offset-4 col-sm-8" style="margin-top:10px;">'+
					'		<button id="addCategoryBtn" type="button" class="btn btn-primary">确定 </button>&nbsp;&nbsp;&nbsp;' +
					'		<button type="button" class="btn btn-default closeBtn">取消 </button>'+
					'	</div>' +
					'</div>' +
					'</form></div>'
				});
				$("#addCategoryBtn").on('click', function() {
					var catNames = $.trim($("#inputCatName").val()).split(/[，|,]/g).join(',');
					var showInNav = $("#showInNav").is(":checked") ? 1 : 0;
					var status = $("#status").is(":checked") ? 1 : 0;

					var indexLoad = layer.load(2);
					//发送请求
					$.ajax({
						type: "get",
						url: webRoot+"/admin/addCategory",
						data: "catNames=" + catNames + "&parentId="+parentId+"&status=" + status + "&showInNav=" + showInNav + "&time=" + new Date(),
						dataType:'json',
						success: function (data) {
							var content = '';
							for(var i = 0; i<data.length; i++){
								content += setCatDataToTable(data[i]);
							}
							if($(el).attr("id")){
								$('#rootCatTable tbody').append(content);
							}else{
								if($("#catNameTitle").attr('data-parent') == parentId){
									$('#childCatTable tbody').append(content);
								}
							}
						},
						error:function(){
							layer.msg("添加失败", {
								icon: 2,
								time: 1000
							});
						},
						complete:function(){
							layer.close(addCat);
							layer.close(indexLoad);
						}
					});


				});
				//取消按钮关闭弹出框
				$(".closeBtn").click( function(){
					layer.close(updateAttr);
				});
			},

			'changeStatus':function(obj){
				var catId = $(obj).attr("data");
				var oSpan = $(obj).parent('td').prev().children('span').get(0);

				$.ajax({
					type: "get",
					url: webRoot + "/admin/changeCatStatus",
					data: "catId=" + catId + "&time=" + new Date(),
					dataType: 'json',
					success: function (data) {
						if (data.error == "0") {
							layer.msg(data.message, {
								icon: 1,
								time: 1000
							});
							$(oSpan).attr('class', "label label-default" == $(oSpan).attr("class") ? "label label-success" : "label label-default");
							$(oSpan).text("启用" == $(oSpan).text() ? "禁用" : "启用");
						}else{
							layer.msg(data.message, {
								icon: 2,
								time: 1000
							});
						}
					}
				});
				return false;
			},
			//修改类目信息的事件函数
			'updateCat':function(obj){
				var catId = $(obj).attr("data");
				var catName = $(obj).parents('tr').find('a:eq(0)').text();
				var chIsShow = $(obj).parents('tr').find('td:eq(1)').text()=="√"?"checked='true'":"";

				var updateCat = layer.open({
					type: 1,
					title: '修改类目信息',
					area: ['450px', '210px'],
					shadeClose: false, //点击遮罩关闭
					content: '\<div class="containter">' +
							'<form id="formAttr"> ' +
							'	<div class="form-group">' +
							'		<label for="inputcatName" class="col-sm-2 control-label">类目名</label>' +
							'		<div class="col-sm-9">' +
							'			<input type="input" value=' + catName + ' class="form-control" id="inputcatName" placeholder="请填写类目名称" required="required">' +
							'		</div>' +
							'	</div>' +
							'	<div class="form-group" >' +
							'	<div class="col-sm-offset-2 col-sm-9">' +
							'		<div class="checkbox">' +
							'			<label><input id="isShow" type="checkbox" ' + chIsShow + '>目录菜单展示?</label>&nbsp;&nbsp;' +
							'		</div>' +
							'	</div>' +
							'	<div class="col-sm-offset-4 col-sm-8" style="margin-top:20px;">' +
							'		<button id="updateCatBtn" type="button" class="btn btn-primary">修改 </button>&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default closeBtn">取消 </button>' +
							'	</div>' +
							'	</div>' +
							'</form>' +
							'</div>'
				});

				//修改按钮 执行AJAX表单提交
				$("#updateCatBtn").click(function(){
					var catName = $.trim($("#inputcatName").val());
					var showInNav = $("#isShow").is(":checked")?1:0;
// 			    	alert("catName="+catName+"isShow="+isShow);

					//验证关键字段是否为空
					if(catName == ""){
						layer.tips('请填写此字段', '#inputcatName');
						return false;
					}else{
						var indexLoad = layer.load(2);
						//发送请求
						$.ajax({
							type: "get",
							url: webRoot+"/admin/updateCatItem",
							data: "catId=" + catId + "&catName=" + catName + "&showInNav=" + showInNav + "&time=" + new Date(),
							dataType:'json',
							success: function (data) {

								var iconType = data.error == 0?1:2;
								layer.msg(data.message, {
									icon: iconType,
									time: 1000
								});

								if(iconType == 1){
									//更新页面信息
									$(obj).parents('tr').after(setCatDataToTable(JSON.parse(data.data)));
									$(obj).parents('tr').remove();

									if($('#catNameTitle small').text()){
										$('#catNameTitle small').text("("+JSON.parse(data.data).catName+")")
									}
								}

							},
							error: function () {
								layer.msg('请求操作失败', {
									icon: 2,
									time: 1000
								});
							},
							complete:function(){
								layer.close(updateCat);
								layer.close(indexLoad);
							}
						});

					}
					return false;
				});
				//取消按钮关闭弹出框
				$(".closeBtn").click(function(){
					layer.close(updateCat);
				});
				return false;
			},
			'selectChildCat':function(el){
				var id = $(el).attr("data");
				var catName = $(el).text();
				var url = webRoot+"/operation/getChildCat";

				$.getJSON(url, {catId:id, status:-1, time:new Date()}, function(data){
					$("#childCatTable tbody tr").remove();
					$("#catNameTitle h4 small").text("（"+catName+"）");
					$("#catNameTitle").attr('data-parent',id);
					if(data.length == 0)
						$("#childCatTable tbody").append("<tr><td colspan=4 align='center'>抱歉，没有找到任何类目！</td></tr>");
					else{
						var content = '';
						for(var i = 0;i<data.length; i++){
							content += setCatDataToTable(data[i])
						}
						$("#childCatTable tbody").append(content);
					}
				});
				return false;
			}
		});
	});

	function setCatDataToTable(data){

		var content='<tr>' +
				'<td><a data="'+data.catId+'" href="javascript:;">'+data.catName+'</a></td>' +
				'<td>'+(data.showInNav == 1?"√":"x")+'</td>' +
				'<td><span class="label '+(data.status == 1?"label-success":"label-default")+'">'+(data.status == 1?"启用":"禁用")+'</span></td>' +
				'<td>' +
				'	<a class="chanceCatStatus" data="'+data.catId+'" title="状态切换" href="javascript:;"><i class="fa fa-eye-slash"></i></a>&nbsp;&nbsp;' +
				'	<a class="updateCatInfo" data="'+data.catId+'" title="编辑" href="javascript:;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' +
				'	<a class="addChildCat" data="'+data.catId+'" title="添加下级" href="javascript:;"><i class="fa fa-plus"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;' +
				'	<button type="button" class="btn btn-default btn-xs"><i class="fa fa-arrow-up"></i></button>' +
				'	<button type="button" class="btn btn-default btn-xs"><i class="fa fa-arrow-down"></i> </button>' +
				'</td>' +
				'</tr>';
		return content;
	}
</script>
</html>

