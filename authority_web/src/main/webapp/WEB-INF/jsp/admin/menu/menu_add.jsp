<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单添加</title>
<%@ include file="/static/base/common.jspf"%>
<style type="text/css">
	.iconfont {
	    font-size : 20px !important;
	}
</style>
<script type="text/javascript" src="${ctx}/static/js/hp_form.js"></script>
<script type="text/javascript" src="${ctx}/static/js/menu.js"></script>
</head>
<body>
	<div class="body_main">
		<form class="layui-form layui-form-pane" action="${ctx}/menu/save">
			<div class="layui-form-item">
				<label class="layui-form-label">父级菜单</label>
				<div class="layui-input-block">
					<select name="pid" lay-verify="required">
						<option value="0">顶级目录</option>
						<c:forEach items="${list}" var="menu">
							<option value="${menu.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.menuName }</option>
							<c:forEach items="${menu.nodes }" var="menu2">
								<option value="${menu2.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu2.menuName }</option>
							</c:forEach>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">菜单名称</label>
				<div class="layui-input-block">
					<input type="text" name="menuName" autocomplete="off"
						placeholder="请输入菜单名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">菜单类型</label>
				<div class="layui-input-block">
					<select name="menuType" lay-verify="required">
						<option value=""></option>
						<option value="1">目录</option>
						<option value="2">菜单</option>
						<option value="3">按钮</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">菜单图标</label>
				<div class="layui-input-inline">
					<input type="text" name="menuImg" autocomplete="off"
						placeholder="请输入菜单图标" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux"></div>
			</div>
			<div class="layui-form-item icon-div" style="display: none;">
				<div class="layui-input-block">
					<c:forEach var="icon" items="${iconFont}">
						<input type="radio" name="icon" lay-filter="icon" value="${icon}" title="<span class='iconfont ${icon}'></span>">
					</c:forEach>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">跳转地址</label>
				<div class="layui-input-block">
					<input type="text" name="url" autocomplete="off"
						placeholder="请输入跳转地址" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">回调方法</label>
				<div class="layui-input-block">
					<input type="text" name="function" autocomplete="off"
						placeholder="请输入回调方法" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">顺序</label>
				<div class="layui-input-block">
					<input type="text" name="seq" autocomplete="off"
						placeholder="请输入顺序" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">权限标识符</label>
				<div class="layui-input-block">
					<input type="text" name="permiss" autocomplete="off"
						placeholder="请输入权限标识符" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
