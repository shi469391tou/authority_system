<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单列表</title>
<%@ include file="/static/base/common.jspf" %>
<style type="text/css">
.layui-table-cell .layui-form-checkbox[lay-skin=primary] {
   top: 5px !important;
}
</style>
</head>
<body>
	<div style="padding: 15px;">
		<%-- 隐藏角色id--%>
		<input type="hidden" value="${role_id}" id="id">
		<table class="layui-hide" id="menu" lay-data="{id: 'menu'}"></table>
		<div>
			<button class="layui-btn bt_setMenu">立即提交</button>
			<button class="layui-btn layui-btn-primary bt_close">关闭</button>
		</div>
	</div>
	<script>
		$(function () {
			layui.use('table', function() {
				let table = layui.table;
				let $ = layui.jquery;
				let id = $('#id').val();

				table.render({
					elem : '#menu',
					url : '${ctx}/menu/MenuList?id='+id,
					cellMinWidth : 80,
					cols : [ [ {
						type : 'checkbox'
					}, {
						field : 'menuName',
						title : '目录',
						width : 120,
						templet : function (e){
							if (e.menuType==1) {
								return e.menuName;
							}else if(e.menuType==2){
								return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|____";
							}else {
								return "";
							}
						}
					}, {
						field : 'menuName',
						title : '菜单',
						width : 120,
						templet : function (e){
							if (e.menuType==2) {
								return e.menuName;
							}else if (e.menuType==3){
								return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|____";
							}else {
								return "";
							}
						}
					}, {
						field : 'menuName',
						title : '按钮',
						width : 120,
						templet : function (e){
							if (e.menuType==3) {
								return e.menuName;
							}else {
								return "";
							}
						}
					}, {
						field : 'menuType',
						title : '菜单类型',
						templet : function (e){
							if (e.menuType==1) {
								return "目录";
							}else if(e.menuType==2){
								return "菜单";
							}else if(e.menuType==3){
								return "按钮";
							}
						}
					}, {
						field : 'permiss',
						title : '权限标识'
					}, {
						field : 'url',
						title : '菜单地址'
					}, {
						field : 'function',
						title : '回调方法'
					}] ]
				});
				//关闭模态框
				$('.bt_close').on('click', function() {
					parent.layer.closeAll();
				});

				//提交
				$('.bt_setMenu').on('click', function() {
					let checkStatus = layui.table.checkStatus($('table').attr("id"));
					$.ajax({
						type: "POST",
						url: '${ctx}/role/setMenu?id='+$('#id').val(),
						contentType : "application/json",
						dataType: 'json',
						data: JSON.stringify(checkStatus.data),
						success: function(data){
							if (data.result==true) {
								parent.layer.msg(data.msg, {icon: 1, time: 1500});
								let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
								parent.layer.close(index); //再执行关闭
							}else {
								layer.msg(data.msg, {icon: 2, time: 1500});
							}
						},
						error: function(res){
							layer.msg("未知异常", {icon: 2});
						}
					});
				});

			});
		})
	</script>
</body>
</html>
