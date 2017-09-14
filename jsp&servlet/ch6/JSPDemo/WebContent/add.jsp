<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加法网页</title>
</head>
<body>
<%
	String a = request.getParameter("a");
	String b = request.getParameter("b");
	out.println("a + b =" + (Integer.parseInt(a) + Integer.parseInt(b)));
%>
</body>
</html>