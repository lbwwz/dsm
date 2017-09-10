<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>

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

	<jsp:include page="../common/commonPath.jsp"/>
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
                    <div class="col-xs-12">
                        <h1 class="page-header">添加类目</h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="authorityPage/admin/adminIndex.jsp">主页</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i> 设置商品类目属性
                            </li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                	<div class="col-xs-6">
	                	<div class="form-group">
	                        <label>商品根级类目</label>
	                        <select id="rootCat" class="form-control">
	                        	<option value="">--请选择--</option>
	                       	<c:forEach items="${requestScope.rootCat }" var="rootBean">
	                            <option value="${rootBean.catId }">${rootBean.catName }</option>
	                        </c:forEach>
	                        </select>
	                    </div>
                    </div>
                	<div class="col-xs-6">
	                	<div class="form-group">
	                        <label>商品子级类目</label>
	                        <select id="childCat" class="form-control">
	                        	<option value="">--请选择--</option>
	                        </select>
	                    </div>
                    </div>
                </div>
                <div class="row">
             		<div class="col-xs-12">
	                	<div class="panel panel-default">
	                        <div class="panel-heading">商品的销售属性结果如下：
	                        	<div class="pull-right">
	                                <div class="btn-group">
	                                    <button type="button" class="btn btn-primary btn-xs" id="addAttrItem">添加一项  <span class="glyphicon-plus"></span></button>
	                                </div>
	                            </div>
	                        </div>
                        	<!-- /.panel-heading -->
                        	<div class="panel-body">

	                        	<!-- Nav tabs -->
	                        	<div class="col-lg-8">
		                            <ul class="nav nav-tabs">
		                                <li class="active"><a href="#based" data-toggle="tab" aria-expanded="true">基本属性</a>
		                                </li>
		                                <li class=""><a href="#saled" data-toggle="tab" aria-expanded="false">销售属性</a>
		                                </li>
		                            </ul>

	                            	<!-- Tab panes -->
	                            	<div class="tab-content">
	                               		<div class="tab-pane fade active in" id="based">
		                                	<div class="table-responsive">
		                                		<h4><small>&nbsp;&nbsp;基本属性：</small></h4>
				                                <table id="baseAttrTable" class="table table-bordered table-hover">

					                                <thead>
					                                    <tr>
					                                        <th>属性名称</th>
					                                        <th>是否关键</th>
					                                        <th>是否必选</th>
					                                        <th>状态</th>
					                                        <th>操作</th>
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                    <tr>
					                                        <td colspan="5" align="center">请选择商品类目</td>
					                                    </tr>
					                                </tbody>
					                            </table>
												<!-- /.table-responsive -->
				                        	</div>
	                                	</div>
	                                	<div class="tab-pane fade" id="saled">
											<div class="table-responsive">
												<h4><small>&nbsp;&nbsp; 销售属性：</small></h4>
				                                <table id="saleAttrTable" class="table table-bordered table-hover">

					                                <thead>
					                                    <tr>
					                                        <th>属性名称</th>
					                                        <th>是否关键</th>
					                                        <th>是否必选</th>
					                                        <th>状态</th>
					                                        <th>操作</th>
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                    <tr>
					                                       <td colspan="5" align="center">请选择商品类目</td>
					                                    </tr>
					                                </tbody>
					                            </table>
											<!-- /.table-responsive -->
				                        	</div>
	                                	</div>
	                            	</div>
	                        	</div>

	                        	<div class="col-lg-4">
	                            	<div class="table-responsive">
	                            		<div class="header" id="attrValueName">
                                            <h4>属性值<small></small><button type="button" class="pull-right btn btn-primary btn-xs">添加一项  <span class="glyphicon-plus"></span></button></h4>
                                        </div>

		                                <table id=attrValueTable class="table table-bordered table-hover">
			                                <thead>
			                                    <tr>
			                                        <th>属性值</th>
			                                        <th>状态</th>
			                                        <th>操作</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                    <tr>
			                                        <td colspan="3" align="center">请选择右侧属性</td>
			                                    </tr>
			                                </tbody>
			                            </table>
			                            <!-- /.table-responsive -->
			                        </div>
		                        </div>
	                        <!-- /.panel-body -->
	                    	</div>
	                	</div>
	            	</div>
	            	<!-- /.container-fluid -->
	        	</div>
	        	<!-- /#page-wrapper -->
	    	</div>
	    	<!-- /#wrapper -->
   		</div>
   	</div>

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

		//根目录 select多选栏的change事件
		$("#rootCat").change(function(){
			$.selectRootCat(this);
		});

		//子目录 select多选栏的change事件
		$("#childCat").change(function(){
			$.showAttrFromCheckedCat(this);
		});

		//为选中分类中添加一条属性
		$('#addAttrItem').click(function(){
			$.addAttrItem(this);
		});

		//属性查询属性值按钮
		$('body').on("click",".attrNameBtn", function(){
			$.getAttrValues(this);
		});

		//状态切换按钮
		$('body').on("click",".changeAttrStatus,.changeValueStatus", function(){
			$.changeAttrAndValueStatus(this);
		});

		//编辑属性信息的按钮点击事件
		$('body').on("click",".updateAttrInfo", function(){
			$.editAttr(this);
		});

        $('body').on("click",".addAttrValues", function(){
			$.addAttrValues(this);
		});

		$('body').on("click",".editValueInfo", function(){
			$.editValueInfo(this);
		});

		$.extend({

			'selectRootCat':function(el){
				var catId = $(el).val();
				console.info(catId);
				if(!catId){
					return false;
				}
				//删除后两个 <select> 中除去第一个之外的其他option元素
				$("#childCat option:not(:first)").remove()
				$("#baseAttrTable tbody tr").remove();
				$("#saleAttrTable tbody tr").remove();
				$("#attrValueTable tbody tr").remove();


				if(catId != ""){
					var url = webRoot+"/operation/getChildCat";
					var args = {"catId":catId, "time":new Date()};

					//发送请求
					$.getJSON(url, args, function(data){
						for(var i = 0;i<data.length; i++){
							$("#childCat").append("<option value='"+data[i].catId+"'>"+data[i].catName+"</option>");
						}
					},'json');
				}
				return false;
			},
			/**
			 * 根据选中的类目展示相应的属性信息
			 */
			'showAttrFromCheckedCat':function(el){
				//删除之前 table 表中 tbody 中的元素
				$("#baseAttrTable tbody tr").remove();
				$("#saleAttrTable tbody tr").remove();
				$("#attrValueTable tbody tr").remove();
				var catId = $(el).val();

				if(catId != ""){
					var url = webRoot+"/admin/getAttrInfo";
					var args = {"catId":catId, "time":new Date()};

					//发送请求
					$.getJSON(url, args, function(data){
						if(data.length == 0){
							$("#baseAttrTable tbody").append("<tr><td colspan=5 align='center'>抱歉，没有找到该类目商品的属性</td></tr>");
							$("#saleAttrTable tbody").append("<tr><td colspan=5 align='center'>抱歉，没有找到该类目商品的属性</td></tr>");
						}
						else{
							for(var i = 0;i<data.length; i++){
								if(data[i].isSale == 0){
									$("#baseAttrTable tbody").append(setAttrDataToTable(data[i]));
								}else{
									$("#saleAttrTable tbody").append(setAttrDataToTable(data[i]));
								}
							}
						}
					});
				}
				return false;
			},

			'addAttrItem':function(el){
				//检查是否选择目录
				var catId = $("#childCat").val();
				if(!catId){
					layer.msg("请选择具体分类", {time: 2000});
					return false;
				}

				var addAttr = layer.open({
					type: 1,
					title: '添加属性',
					area: ['450px', '210px'],
					shadeClose: false, //点击遮罩关闭
					content: '\<div class="containter"><form id="formAttr"> '+
					'<div class="form-group">' +
					'	<label for="inputAttrName" class="col-sm-2 control-label">属性名</label>'+
					'	<div class="col-sm-9">' +
					'		<textarea  class="form-control" id="inputAttrName" placeholder="请填写属性名称名称，多个名称之间用“,”分隔" required="required"></textarea>' +
					'	</div>' +
					'</div>'+
					'<div class="form-group" >' +
					'	<div class="col-sm-offset-2 col-sm-9">' +
					'		<div class="checkbox">'+
					'			<label><input id="isSale" type="checkbox" >销售属性</label>&nbsp;'+
					'			<label><input id="isKey" type="checkbox" >关键属性</label>&nbsp;' +
					'			<label><input id="isMust" type="checkbox" >必填属性</label>' +
					'		</div>' +
					'	</div>'+
					'	<div class="col-sm-offset-4 col-sm-8" style="margin-top:10px;">'+
					'		<button id="addAttrInfoBtn" type="button" class="btn btn-primary">确定 </button>&nbsp;&nbsp;&nbsp;' +
					'		<button type="button" class="btn btn-default closeBtn">取消 </button>'+
					'	</div>' +
					'</div>' +
					'</form></div>'
				});

				$('#addAttrInfoBtn').click(function(){
					var attrsName = $.trim($("#inputAttrName").val()).split(/[，|,]/g).join(',');
					var isSale = $("#isSale").is(":checked") ? 1 : 0;
					var isKey = $("#isKey").is(":checked") ? 1 : 0;
					var isMust = $("#isMust").is(":checked") ? 1 : 0;

					var indexLoad = layer.load(2);
					//发送请求
					$.ajax({
						type: "get",
						url: webRoot+"/admin/addAttrInfo",
						data: "catId="+catId+"&attrsName=" + attrsName +"&isSale="+isSale+"&isKey=" + isKey + "&isMust=" + isMust + "&time=" + new Date(),
						dataType:'json',
						success: function (data) {
							var baseContent = '',saleContent = '';
							for(var i = 0; i<data.length; i++){
								if(data.isSale == 1)
									saleContent += setAttrDataToTable(data[i]);
								else{
									baseContent += setAttrDataToTable(data[i]);
								}
							}
							if($("#baseAttrTable td").length <=1){
								$("#baseAttrTable tbody  tr").remove();

							}
							if($("#saleAttrTable td").length <=1){
								$("#saleAttrTable tbody  tr").remove();

							}
							$('#baseAttrTable tbody').append(baseContent);
							$('#saleAttrTable tbody').append(saleContent);


						},
						error:function(){
							layer.msg("添加失败", {
								icon: 2,
								time: 1000
							});
						},
						complete:function(){
							layer.close(addAttr);
							layer.close(indexLoad);
						}
					});
				});
                //取消按钮关闭弹出框
                $(".closeBtn").click( function(){
                    layer.close(addAttr);
                });
			},
            'addAttrValues':function(el){

                var attrId = $(el).attr('data')?$(el).attr('data'):'0';
                if(attrId == "0"){
                    alert("数据异常，请刷新重试！")
                    return false;
                }

                var addAttrValues = layer.open({
                    type: 1,
                    title: '添加属性值',
                    area: ['450px', '210px'],
                    shadeClose: false, //点击遮罩关闭
                    content: '\<div class="containter"><form id="formAttr"> '+
                    '<div class="form-group">' +
                    '	<label for="inputCatName" class="col-sm-2 control-label">属性值</label>'+
                    '	<div class="col-sm-9">' +
                    '		<textarea  class="form-control" id="inputAttrValues" placeholder="请填写属性值，多个值之间用“,”分隔" required="required"></textarea>' +
                    '	</div>' +
                    '</div>'+
                    '<div class="form-group" >' +
                    '	<div class="col-sm-offset-2 col-sm-9">' +
                    '		<div class="checkbox">'+
                    '			<label><input id="status" type="checkbox" >可用状态？</label>' +
                    '       </div>' +
                    '   </div>'+
                    '	<div class="col-sm-offset-4 col-sm-8" style="margin-top:10px;">'+
                    '		<button id="addAttrValueInfoBtn" type="button" class="btn btn-primary">确定 </button>&nbsp;&nbsp;&nbsp;' +
                    '		<button type="button" class="btn btn-default closeBtn">取消 </button>'+
                    '	</div>' +
                    '</div>' +
                    '</form></div>'
                });

                $("#addAttrValueInfoBtn").on('click', function() {
                    var attrValues = $.trim($("#inputAttrValues").val()).split(/[，|,]/g).join(',');
                    var status = $("#status").is(":checked") ? 1 : 0;

                    var indexLoad = layer.load(2);
                    //发送请求
                    $.ajax({
                        type: "get",
                        url: webRoot + "/admin/addAttrValues",
                        data: "attrValues=" + attrValues + "&attrId=" + attrId + "&status=" + status  + "&time=" + new Date(),
                        dataType: 'json',
                        success: function (data) {


                            if(data.error == 0){
                                layer.msg(data.message, {
                                    icon: 1,
                                    time: 1000
                                });

                                data = JSON.parse(data.data);
                                var content = '';
                                for (var i = 0; i < data.length; i++) {
                                    content += setAttrValueDataToTable(data[i]);
                                }
                                if ($("#attrValueName").attr('data-attrId') == attrId) {
									if($("#attrValueTable td").length<=1){
										$('#attrValueTable tbody tr').remove()
									}
									$('#attrValueTable tbody').append(content);

                                }

                            }else {
                                layer.msg(data.message, {
                                    icon: 2,
                                    time: 1000
                                });
                            }
                        },
                        error: function () {
                            layer.msg("添加失败", {
                                icon: 2,
                                time: 1000
                            });
                        },
                        complete: function () {
                            layer.close(addAttrValues);
                            layer.close(indexLoad);
                        }
                    });
                });
                //取消按钮关闭弹出框
                $(".closeBtn").click( function(){
                    layer.close(addAttrValues);
                });
            },

			'getAttrValues':function(el){
				var attrId = $(el).attr("data");
				var attrName = $(el).text();
				var url = "/admin/getAttrValues";
				var args = {"attrId":attrId, "time":new Date()};

				//发送请求并执行响应函数
				$.getJSON(url, args, function(data){
					//移除#childCatTable tbody原有的元素
					$("#attrValueTable tbody tr").remove();
					$("#attrValueName h4 small").text("（"+attrName+"）");

					if(data == null || data == "")
						$("#attrValueTable tbody").append("<tr><td colspan=3 align='center'>该属性还未添加任何属性值</td></tr>");
					else{
						var content = '';
						for(var i = 0;i<data.length; i++){
							content+=setAttrValueDataToTable(data[i])
						}
                        $("#attrValueName").attr("data-attrId",attrId)
						$("#attrValueTable tbody").append(content);
					}
				},'json');
				return false;
			},

			'changeAttrAndValueStatus':function(el){

				var oSpan = $(el).parent('td').prev().children('span').get(0);
				var url = "";
				var data = "";
				if($(el).attr("class") == "changeAttrStatus"){
					url = webRoot+"/admin/changeAttrStatus";
					data ="attrId="+$(el).attr("data")+"&time="+new Date();
				}else{
					url = webRoot+"/admin/changeAttrValueStatus";
					data ="valueId="+$(el).attr("data")+"&time="+new Date();
				}

				$.ajax({
					type: "get",
					url: url,
					data: data,
					success: function(data){
						if (data.error == "0") {
							layer.msg(data.message, {
								icon: 1,
								time: 2000
							});
							$(oSpan).attr('class',"label label-default"==$(oSpan).attr("class")?"label label-success":"label label-default");
							$(oSpan).text("启用"==$(oSpan).text()?"禁用":"启用");
						}else{
							layer.msg(data.message, {
								icon: 2,
								time: 2000
							});
						}
					}
				});
				return false;
			},
			'editAttr':function(el){
				var chAttrId = $(el).attr("data");
				var chAttrName = $(el).parents('tr').find('a:eq(0)').text();
				var chIskey = $(el).parents('tr').find('td:eq(1)').text()=="√"?"checked='true'":"";
				var chIsMust = $(el).parents('tr').find('td:eq(2)').text()=="√"?"checked='true'":"";

				var updateAttr = layer.open({
					type: 1,
					title: '修改属性',
					area: ['450px', '210px'],
					shadeClose: false, //点击遮罩关闭
					content: '\<div class="containter"><form id="formAttr">'+
					'<div class="form-group">' +
					'	<label for="inputAttrName" class="col-sm-2 control-label">属性名</label>'+
					'	<div class="col-sm-9"> ' +
					'		<input type="input" value='+chAttrName+' class="form-control" id="inputAttrName" placeholder="请填写属性名称" required="required">' +
					'	</div>' +
					'</div>'+
					'<div class="form-group" >' +
					'	<div class="col-sm-offset-2 col-sm-9">' +
					'		<div class="checkbox">'+
					'			<label><input id="isKey" type="checkbox" '+chIskey+'>关键属性？</label>&nbsp;&nbsp;'+
					'			<label><input id="isMust" type="checkbox" '+chIsMust+'>必选属性？</label>' +
					'		</div>' +
					'	</div>'+
					'	<div class="col-sm-offset-4 col-sm-8" style="margin-top:20px;">'+
					'		<button id="updateAttrBtn" type="submit" class="btn btn-primary">修改 </button>&nbsp;&nbsp;&nbsp;' +
					'		<button type="button" class="btn btn-default closeBtn">取消 </button>'+
					'	</div>' +
					'</div>' +
					'</form></div>'
				});

				//修改按钮 执行AJAX表单提交
				$("#updateAttrBtn").click( function(){
					var attrName = $.trim($("#inputAttrName").val());
					var isKey = $("#isKey").is(":checked")?1:0;
					var isMust = $("#isMust").is(":checked")?1:0;

					//验证关键字段是否为空
					if(attrName == ""){
						layer.tips('请填写此字段', '#inputAttrName');
						return false;
					}else{
						var indexLoad = layer.load(2);
						//发送请求
						$.ajax({
							type: "POST",
							url: webRoot+"/admin/updateAttrItem",
							data: "attrId="+chAttrId+"&attrName="+attrName+"&isKey="+isKey+"&isMust="+isMust+"&time="+new Date(),
							success: function(data){
								var iconType = data.error == 0?1:2;
								layer.msg(data.message, {
									icon: iconType,
									time: 2000
								});

								//更新页面信息
								$(el).parents('tr').after(setAttrDataToTable(JSON.parse(data.data)));
								$(el).parents('tr').remove();

								if($('#attrValueName h4 small').text()){
									$('#attrValueName h4 small').text("("+JSON.parse(data.data).attrName+")")
								}
							},
							error: function () {
								layer.msg('请求操作失败', {
									icon: 2,
									time: 2000
								});
							},
							complete:function(){
								layer.close(updateAttr);
								layer.close(indexLoad);
							}
						});

					}
					return false;
				});

				//取消按钮关闭弹出框
				$(".closeBtn").click( function(){
					layer.close(updateAttr);
				});
			},
			'editValueInfo':function(el){
				var valueId = $(el).attr("data");
				var valueName = $(el).parents('tr').find('td:eq(0)').text();

				var updateValue = layer.open({
					type: 1,
					title: '修改属性值信息',
					area: ['450px', '180px'],
					shadeClose: false, //点击遮罩关闭
					content: '\<div class="containter">' +
					'<form id="formAttr"> ' +
					'	<div class="form-group">' +
					'		<label for="inputAttrValue" class="col-sm-2 control-label">属性值</label>' +
					'		<div class="col-sm-9">' +
					'			<input type="input" value=' + valueName + ' class="form-control" id="inputAttrValue" placeholder="请填写类目名称" required="required">' +
					'		</div>' +
					'	</div>' +
					'	<div class="form-group" >' +
					'	<div class="col-sm-offset-4 col-sm-8" style="margin-top:20px;">' +
					'		<button id="updateValueBtn" type="button" class="btn btn-primary">修改 </button>&nbsp;&nbsp;&nbsp;' +
					'		<button type="button" class="btn btn-default closeBtn">取消 </button>' +
					'	</div>' +
					'	</div>' +
					'</form>' +
					'</div>'
				});
				//修改按钮 执行AJAX表单提交
				$("#updateValueBtn").click(function(){
					var inputAttrValue = $.trim($("#inputAttrValue").val());

					//验证关键字段是否为空
					if(inputAttrValue == ""){
						layer.tips('请填写此字段', '#inputcatName');
						return false;
					}else{
						var indexLoad = layer.load(2);
						//发送请求
						$.ajax({
							type: "get",
							url: webRoot+"/admin/updateAttrValue",
							data: "valueId=" + valueId + "&attrValue=" + inputAttrValue +  "&time=" + new Date(),
							dataType:'json',
							success: function (data) {

								var iconType = data.error == 0?1:2;
								layer.msg(data.message, {
									icon: iconType,
									time: 1000
								});

								if(iconType == 1){
									//更新页面信息
									$(el).parents('tr').after(setAttrValueDataToTable(JSON.parse(data.data)));
									$(el).parents('tr').remove();

									$('#attrValueName small').text("("+JSON.parse(data.data).valueName+")")
								}


							},
							error: function () {
								layer.msg('请求操作失败', {
									icon: 2,
									time: 1000
								});
							},
							complete:function(){
								layer.close(updateValue);
								layer.close(indexLoad);
							}
						});

					}
					return false;
				});


				//取消按钮关闭弹出框
				$(".closeBtn").click(function(){
					layer.close(updateValue);
				});
				return false;
			}

		});
	});

	function setAttrDataToTable(data){
		return "/<tr>" +
                "   <td>"+
				"       <a class='attrNameBtn' data='"+data.attrId+"' href='javascript:;'>"+data.attrName+"</a></td>"+
				"   <td>"+(data.isKey==1?"√":"x")+"</td>"+
				"   <td>"+(data.isMust==1?"√":"x")+"</td>"+
				"   <td><span class='label "+(data.status==1?"label-success":"label-default")+"'>"+(data.status==1?"启用":"禁用")+"</span></td>"+
				"   <td><a data='"+data.attrId+"' class='changeAttrStatus' title='状态切换' href='javascript:;'><i class='fa fa-eye-slash'></i></a>&nbsp;&nbsp;"+
				"       <a data='"+data.attrId+"' class='updateAttrInfo' title='编辑' href='javascript:;'><i class='fa fa-pencil'></i></a>&nbsp;&nbsp;"+
				"       <a data='"+data.attrId+"' class='addAttrValues' title='添加属性' href='javascript:;'><i class='fa fa-plus'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;"+
				"       <button type='button' class='btn btn-default btn-xs'><i class='fa fa-arrow-down'></i></button>"+
				"       <button type='button' class='btn btn-default btn-xs'><i class='fa fa-arrow-up'></i></button>" +
                "   </td>" +
                "</tr>";
	}

	function setAttrValueDataToTable(data) {
		return "/<tr>" +
                "   <td>"+data.valueName+"</td>"+
                "   <td>" +
                "	    <span class='label "+(data.status==1?"label-success":"label-default")+"'>"+(data.status==1?"启用":"禁用")+"</span>" +
                "   </td>"+
                "   <td>" +
                "	    <a data='"+data.valueId+"' class='changeValueStatus' title='状态切换' href='javascript:;'><i class='fa fa-eye-slash'></i></a>&nbsp;&nbsp;"+
                "	    <a data='"+data.valueId+"' title='编辑' class='editValueInfo' href='javascript:;'><i class='fa fa-pencil'></i></a>&nbsp;&nbsp;"+
                "	    <button type='button' class='btn btn-default btn-xs'><i class='fa fa-arrow-down'></i></button>"+
                "	    <button type='button' class='btn btn-default btn-xs'><i class='fa fa-arrow-up'></i></button>" +
                "   </td>" +
                "</tr>"
	}
	</script>
</body>
</html>