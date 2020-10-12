<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎页</title>
<style type="text/css">
	img{
		width: 100%;
		height: 100%;
	}
</style>
</head>
<body>
	<div class="layui-carousel" id="test10">
		<div carousel-item="">
			<div>
				<img src="https://res.layui.com/images/layui/demo/1.png">
			</div>
			<div>
				<img src="https://res.layui.com/images/layui/demo/2.png">
			</div>
			<div>
				<img src="https://res.layui.com/images/layui/demo/3.png">
			</div>
			<div>
				<img src="https://res.layui.com/images/layui/demo/4.png">
			</div>
			<div>
				<img src="https://res.layui.com/images/layui/demo/5.png">
			</div>
			<div>
				<img src="https://res.layui.com/images/layui/demo/6.png">
			</div>
			<div>
				<img src="https://res.layui.com/images/layui/demo/7.png">
			</div>
		</div>
	</div>
	<script>
		layui.use([ 'carousel', 'form' ],
			function() {
				var carousel = layui.carousel, form = layui.form;
				//图片轮播
				carousel.render({
					elem : '#test10',
					width : '100%',
					height : '100%',
					interval : 5000
				});

				//监听开关
				form.on('switch(autoplay)', function() {
					ins3.reload({
						autoplay : this.checked
					});
				});

			});
	</script>
</body>
</html>