<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	</head>
<body>
	<%@ include file="../include/menu.jsp" %>
	<c:if test="${message == 'success' }" >
		<h2>
			${sessionScope.admin_name } ( ${sessionScope.admin_userid } )님 환영합니다.
		</h2>
	</c:if>
</body>
</html>