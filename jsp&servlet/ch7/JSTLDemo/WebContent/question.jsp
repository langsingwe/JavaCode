<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set target="${pageContext.request}" property="characterEncoding" value="UTF-8"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Questionnaire</title>
</head>
<body>
	<form action="question.jsp" method="post">
		<c:choose>
			<c:when test="${param.page == 'page1' }">
				问题一：<input type='text' name='p1q1'><br>
				问题二：<input type='text' name='p1q2'><br>
				<input type='submit' name='page' value='page2'>
			</c:when>
			<c:when test="${param.page =='page2' }">
				<c:set var="p1q1" value="${param.p1q1 }" scope="session" />
				<c:set var="p1q2" value="${param.p1q2 }" scope="session"/>
				问题三：<input type='submit' name='p2q1'><br>
				<input type='submit' name='page' value='finish'>
			</c:when>
			<c:when test="${param.page == 'finish' }">
				${sessionScope.p1q1 }<br>
				${sessionScope.p1q2 }<br>
				${param.p2q1 }<br>
			</c:when>
		</c:choose>
	</form>
</body>
</html>