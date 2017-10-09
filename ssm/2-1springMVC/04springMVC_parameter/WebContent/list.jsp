<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>array</title>
</head>
<body>
	<form action="arr1.do" method="post">
		爱好：<input type="checkbox" name="hobbies[0]" value="足球">足球
			<input type="checkbox" name="hobbies[1]" value="篮球">篮球
			<input type="checkbox" name="hobbies[2]" value="羽毛球">羽毛球
			<input type="checkbox" name="hobbies[3]" value="乒乓球">乒乓球
			<input type="submit" value="提交">
	</form>
</body>
</html>