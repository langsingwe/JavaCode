<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="cc.openhome.User"></jsp:useBean>
<jsp:setProperty name="user" property="*">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
	<c:choose>
		<c:when test="${user.valid }">
			<h1>
				<jsp:getProperty name="user" property="name"> 登陆成功
			</h1>
		</c:when>
		<c:otherwise>
			<h1>登录失败</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>