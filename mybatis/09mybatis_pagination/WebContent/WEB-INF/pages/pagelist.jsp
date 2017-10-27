<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>MyBatis分页实例</title>
	</head>
	<style>
tr {
	display: table-row;
	vertical-align: inherit;
	border-color: inherit;
}

td {
	border-left: 1px solid #dddddd;
}
</style>
	<body>

		<table style="width: 60%; border: 1px solid #dddddd;" border="1"
			align="center">
			<tr>
				<th>
					编号
				</th>
				<th>
					订单号
				</th>
				<th>
					金额
				</th>
			</tr>

			<c:forEach items="${orders}" var="order">
				<tr>
					<td align="center">
						${order.orderId}
					</td>
					<td align="center">
						${order.orderNo}
					</td>
					<td align="center">
						${order.money}
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3">
					${pagelist}
				</td>
			</tr>
		</table>
	</body>
</html>
