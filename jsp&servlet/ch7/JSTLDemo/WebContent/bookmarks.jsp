<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线书签</title>
</head>
<body>
	<c:import var="xml" url="bookmarks.xml" charEncoding="utf-8" />
	<x:parse var="bookmarks" doc="${xml }" />
	<h2>在线书签</h2>
	<table border="1">
		<tr bgColor="#00ff00">
			<th align="left">名称</th>
			<th align="left">网址</th>
			<th align="left">分类</th>
		</tr>
		<x:forEach var="bookmark" select="$bookmarks//bookmark" >
			<tr>
				<td><x:out select="$bookmark/title"/></td>
				<td><x:out select="$bookmark/url"/></td>
				<td><x:out select="$bookmark/category"/></td>
			</tr>
		</x:forEach>
	</table>
</body>
</html>