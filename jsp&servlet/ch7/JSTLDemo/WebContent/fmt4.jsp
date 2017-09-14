<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, javax.servlet.jsp.jstl.fmt.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
  "http://www.w3.org/TR/html4/loose.dtd">
<%
    //为了简化范例，这边先用一下Scriptlet
    ResourceBundle zh_TW = ResourceBundle.getBundle("hello", new Locale("zh", "TW"));
    ResourceBundle zh_CN = ResourceBundle.getBundle("hello", new Locale("zh", "CN"));
    ResourceBundle ja_JP = ResourceBundle.getBundle("hello", new Locale("ja", "JP"));
    ResourceBundle en_US = ResourceBundle.getBundle("hello", new Locale("en", "US"));
    pageContext.setAttribute("zh_TW", new LocalizationContext(zh_TW));
    pageContext.setAttribute("zh_CN", new LocalizationContext(zh_CN));
    pageContext.setAttribute("ja_JP", new LocalizationContext(ja_JP));
    pageContext.setAttribute("en_US", new LocalizationContext(en_US));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        
        <fmt:message bundle="${zh_TW}" key="cc.openhome.hello"/><br>
        <fmt:message bundle="${zh_CN}" key="cc.openhome.hello"/><br>
        <fmt:message bundle="${ja_JP}" key="cc.openhome.hello"/><br>
        <fmt:message bundle="${en_US}"  key="cc.openhome.hello"/>
    </body>
</html>