<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btn').click(function(){
			$.post('list.do',function(data){
				var html = "";
				for(var i=0;i<data.length;i++){
					html+="<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].age+"</td></tr>";
				}
				$('#content').html(html);
			});
		});
	});
</script>
</head>
<body>
<input type='button' id='btn' value='获取数据'>
<table width='80%' align='center'>
	<tr>
		<td>编号</td>
		<td>姓名</td>
		<td>年龄</td>
	</tr>
	<tbody id='content'>
	
	</tbody>
</table>
</body>
</html>