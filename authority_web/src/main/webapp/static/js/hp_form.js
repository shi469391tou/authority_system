layui.use('form', function() {
	var form = layui.form;

	//通用弹出层表单提交方法
	form.on('submit(demo1)', function(data){
		// console.log(data.field);
		// 对手机号码进行校验
		// 判断是否是用户模块提交
		let action =$('form').attr("action");
		if(action.indexOf('user')!=-1 && !checkPhone()){
			return false;
		}

		$.post($('form').attr("action"),data.field, function (e){
			// var data = JSON.parse(e);
			// let e.result;
			if (e.result==true) {
				parent.closeLayer(e.msg);
			}else {
				layer.msg('操作失败：' + e.msg, {icon: 2, time: 2000});
			}
		})
		return false;
	})
});
