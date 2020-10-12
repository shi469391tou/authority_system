<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>403</title>
<style type="text/css">
	.error{
		margin: auto;  
		position: absolute;  
		top: 0; 
		left: 0; 
		bottom: 0; 
		right: 0;
	    height: 660px;
    	width: 708px;
		background: url("${ctx}/static/img/403.jpg");
	}
</style>
</head>
<body>
	<div class="error">
	</div>
</body>
</html>