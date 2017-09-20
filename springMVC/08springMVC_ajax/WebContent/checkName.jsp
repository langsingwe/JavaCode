<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>checkName</title>
<script src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#username').blur(function(){
			$.post('checkName.do',{username:$(this).val()},function(data){
				alert(data==1);
				if(!data){
					$('#username').css('border','1px solid green')
				}else{
					$('#username').css('border','1px solid red')
				}
			});
		})
	})
</script>
</head>
<body>
	用户名:<input type="text" id="username">
</body>
</html>