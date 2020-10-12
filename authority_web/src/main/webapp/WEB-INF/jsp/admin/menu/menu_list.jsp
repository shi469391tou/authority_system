<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单列表</title>
<style type="text/css">
	td .iconfont {
	    font-size : 20px !important;
	}
</style>
<script type="text/javascript" src="${ctx}/static/js/hp_list.js"></script>
</head>
<body>
	<div>
			<button class="layui-btn bt_add" data="893px, 550px" data-url="${ctx}/menu/toAddPage">新增</button>
			<button class="layui-btn layui-btn-warm bt_update" data="893px, 550px" data-url="${ctx}/menu/toUpdatePage">修改</button>
			<button class="layui-btn layui-btn-danger bt_delete" data-url="${ctx}/menu/delete">删除</button>
	</div>

	<table class="layui-hide" id="menu" lay-data="{id: 'menu'}"></table>
	<script>
		layui.use('table', function() {
			var table = layui.table;

			table.render({
				elem : '#menu',
				url : '${ctx}/menu/list',
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
					width : 100,
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
					field : 'menuImg',
					title : '图标',
					width : 80,
					templet : function (e){
						return "<span class='iconfont "+e.menuImg+"'></span>";
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
				}, {
					field : 'seq',
					title : '顺序',
					width : 60
				}, {
					field : 'createTime',
					title : '创建时间'
				}, {
					field : 'updateTime',
					title : '更新时间'
				}] ]
			});
		});
	</script>
</body>
</html>
