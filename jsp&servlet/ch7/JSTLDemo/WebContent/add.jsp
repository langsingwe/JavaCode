<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加法网页</title>
</head>
<body>
	<c:catch var="error">
		${param.a } + ${param.b } = ${param.a + param.b }
	</c:catch>
	<c:if test="${error != null }">
		<br><span style='color:red;'>${error.message }</span>
		<br>${error }
	</c:if>
</body>
</html>