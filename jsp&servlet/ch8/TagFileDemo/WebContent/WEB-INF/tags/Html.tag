<%@ tag language="java" pageEncoding="UTF-8" description="HTML 懒人标签"%>
<%@ attribute name="title" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${title }</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	</head>
	<body>
		<jsp:doBody/>
	</body>
</html>