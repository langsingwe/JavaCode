<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<jsp:useBean id="now" class="java.util.Date" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fmt:setLocale value="zh_CN" />
	<fmt:formatDate value="${now }" type="both" /><br>
	<fmt:formatNumber value="123456.6789" type="currency" /><br>
	<fmt:setLocale value="en_US" />
	<fmt:formatDate value="${now }" type="both" /><br>
	<fmt:formatNumber value="123456.6789" type="currency" /><br>
	<fmt:setLocale value="ja_JP" />
	<fmt:formatDate value="${now }" type="both" /><br>
	<fmt:formatNumber value="123456.6789" type="currency" /><br>
</body>
</html>