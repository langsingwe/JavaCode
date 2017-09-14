<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>友站的留言</title>
    </head>
    <body>
        <c:import var="xml" url="xmlMessage" charEncoding="UTF-8" />
        <c:set var='xmlns'>
            xmlns="http://java.sun.com/xml/ns/jdbc"
        </c:set>
        
        <!-- JSTL 1.0 不支援 XML Namespace，所以用空字符串取代掉 -->
        <x:parse var="webRowSet" doc="${fn:replace(xml, xmlns, '')}"/>
        <h2>友站的留言</h2>
        <table border="1">
            <tr bgcolor="#00ff00">
                <th align="left">名称</th>
                <th align="left">邮件</th>
                <th align="left">留言</th>
            </tr>
            <x:forEach var="row" select="$webRowSet//currentRow">
            <tr>
                <td><x:out select="$row/columnValue[2]"/></td>
                <td><x:out select="$row/columnValue[3]"/></td>
                <td><x:out select="$row/columnValue[4]"/></td>
            </tr>
            </x:forEach>
        </table>
    </body>
</html>