<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gossip 微博</title>
</head>
<body>
	<html:Errors headline="新增会员失败" />
		<h1>会员注册</h1>
		 <form method='post' action='register.do'>
         <table bgcolor=#cccccc>
            <tr>
               <td>邮件位址：</td>
               <td><input type='text' name='email' value='${param.email}' size='25' maxlength='100'>
               </td>
            </tr>
            <tr>
               <td>名称（最大16字符）：</td>
               <td><input type='text' name='username' value='${param.username}' size='25' maxlength='16'></td>
            </tr>
            <tr>
               <td>密码（6到16字符）：</td>
               <td><input type='password' name='password' size='25' maxlength='16'>
               </td>
            </tr>
            <tr>
               <td>确认密码：</td>
               <td><input type='password' name='confirmedPasswd' size='25' maxlength='16'></td>
            </tr>
            <tr>
               <td colspan='2' align='center'><input type='submit' value='注册'></td>
            </tr>
         </table> 
       </form>
</body>
</html>