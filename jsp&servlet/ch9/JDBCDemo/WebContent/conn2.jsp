<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="db" class="cc.openhome.DatabaseBean"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
            "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type"
                content="text/html; charset=UTF-8">
        <title>测试数据库连线</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${db.connectedOK}">连线成功！</c:when>
            <c:otherwise>连线失败！</c:otherwise>
        </c:choose>
    </body>
</html>
