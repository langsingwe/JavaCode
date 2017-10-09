<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>array</title>
</head>
<body>
	<form action="arr2.do" method="post">
		商品名称:<input type="text" name="list[0].name">
		商品价格:<input type="text" name="list[0].price">
		商品数量:<input type="text" name="list[0].num"><br>
		商品名称:<input type="text" name="list[1].name">
		商品价格:<input type="text" name="list[1].price">
		商品数量:<input type="text" name="list[1].num"><br>
			<input type="submit" value="提交">
	</form>
</body>
</html>