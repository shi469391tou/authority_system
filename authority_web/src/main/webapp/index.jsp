<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/static/base/common.jspf" %>
</head>
<body>
    <%-- 向登录页面跳转 --%>
    <jsp:forward page="login.jsp"></jsp:forward>
</body>
</html>
