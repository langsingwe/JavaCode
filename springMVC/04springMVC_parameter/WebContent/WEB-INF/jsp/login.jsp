<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<form action="login3.do" method="post">
		username:<input type="text" name="name"><br>
		password:<input type="password" name="pwd"><br>
		role:<input type="radio" name="role.name" value="管理员">管理员
			<input type="radio" name="role.name" value="普通会员">普通会员<br>
		<input type="submit" value="登录">
	</form>
</body>
</html>