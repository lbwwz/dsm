jQuery.extend({
	//将form转为AJAX提交，提交form中的所有信息
	ajaxSubmit: function (frm, fn1, fn2) {
		//是否含有文件域
		var formType = frm.find("input:file[name]").length;
		var dataPara = getFormJson(frm, formType);

		var actionUrl = frm.attr('action');
		if(actionUrl == "" || !actionUrl){
			console.error("form表单的action提交路径未定义！");
			return false;
		}
		var method = frm.attr('method')? frm.attr('method'):"get";
		if (formType) {
			$.ajax({
				url: actionUrl,
				type: method,
				cache: false,
				processData: false,
				contentType: false,
				data: dataPara,
				success: fn1,
				complete: fn2
			});
		} else {
			$.ajax({
				url: actionUrl,
				type: method,
				data: dataPara,
				success: fn1,
				complete: fn2
			});
		}
	}

});


//将form中的值转换为键值对。
function getFormJson(frm, formType) {
	var o = {};
	//获取form中简单数据
	var a = $(frm).serializeArray();
	$.each(a, function () {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	//含有文件域的表单提交使用 formData提交表单信息数据
	if(formType){
		var $input = $(frm).find("input:file");
		var data = new FormData();
		for(var i=0;i<$input.length;i++){
			data.append($($input[i]).prop('name'),$($input[i]).prop('files')[0])
		}
		for(var x in o){
			data.append(x,o[x]);
		}
		return data;
	}else{
		return o;
	}
}


