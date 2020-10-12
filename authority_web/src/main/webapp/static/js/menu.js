$(function (){
	$('input[name="menuImg"]').on('click', function(){
		$('.icon-div').toggle();
	})
	
	layui.use('form', function() {
		var form = layui.form;
		
		form.on('radio(icon)', function(data){
	  		console.log(data.value);
	  		$('.icon-div').hide();
	  		$('input[name="menuImg"]').val(data.value);
	  		$('.layui-form-mid').empty().append("<span class='iconfont "+data.value+"'></span>");
		}); 
	});
})