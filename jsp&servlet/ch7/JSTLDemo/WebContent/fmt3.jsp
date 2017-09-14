<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setBundle basename="messages3"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><fmt:message key="cc.opemhome.title"/></title>
</head>
<body>
	<fmt:message key="cc.openhome.forUser">
		<fmt:param value="${param.username}"/>
		<fmt:param value="${now}"/>
		<fmt:param value="${now}"/>
	</fmt:message>
</body>
</html>