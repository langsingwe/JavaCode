<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    String name = "caterpillar";
    String password = "123456";

    boolean checkUser(String name, String password) {
        return this.name.equals(name) &&
                 this.password.equals(password);
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" 
               content="text/html; charset=UTF-8">                  
        <title>登入页面</title>
    </head>
    <body>
<%
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    if(checkUser(name, password)) {
%>
    <h1><%= name %> 登入成功</h1>
<%
    } else {
%>
    <h1>登入失败</h1>
<%
    }
%>
    </body>
</html>  


