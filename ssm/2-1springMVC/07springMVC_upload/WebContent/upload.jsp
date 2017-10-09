<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
	<form action="upload.do" method="post" enctype="multipart/form-data">
		用户名：<input type="text" name="username">
		文件：<input type="file" name="file">
			<input type="submit" value="上传">
	</form>
</body>
</html>