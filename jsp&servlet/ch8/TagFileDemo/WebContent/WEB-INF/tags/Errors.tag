<%@ tag language="java" pageEncoding="UTF-8" description="显示错误信息的标签"%>
<%@ attribute name="headline" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${requestScope.errors != null }">
	<h1>${headline }</h1>
	<ul style='color:rgb(255,0,0);'>
		<c:forEach var="error" items="${requestScope.errors }">
			<li>${error }</li>
		</c:forEach>
	</ul>
</c:if>
