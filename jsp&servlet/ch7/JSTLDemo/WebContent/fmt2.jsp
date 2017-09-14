<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setBundle basename="messages1"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><fmt:message key="cc.openhome.title"/></title>
</head>
<body>
	<h1><fmt:message key="cc.openhome.forGuest"/></h1>
	<fmt:bundle basename="messages2">
		<h1><fmt:message key="cc.openhome.forGuest"/></h1>
	</fmt:bundle>
</body>
</html>
