<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>登录</title>
<%@ include file="/static/base/common.jspf" %>
<style>
.window {
	width: 350px;
	position: absolute;
	margin-left: -200px;
	margin-top: -80px;
	top: 35%;
	left: 50%;
	display: block;
	z-index: 2000;
	background: #fff;
	padding: 20px 0;
}
</style>
</head>
<body style="background: #f1f1f1;">
	<div class="window">
		<form class="layui-form" action="${pageContext.request.contextPath}/user/login" method="post">
			<div class="layui-form-item" style="text-align: center">
				<h1>欢迎登录</h1>
			</div>
			<div style="color: red;padding-left: 110px;">${msg}</div>
			<div class="layui-form-item" style="margin-right: 50px; margin-top: 30px;">
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-block">
					<input type="text" name="userName" placeholder="请输入用户名" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top: 25px;">
				<label class="layui-form-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
				<div class="layui-input-inline">
					<input type="password" name="password" placeholder="请输入密码" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div style="text-align: center;">
					<input type="submit" class="layui-btn"
						value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>
