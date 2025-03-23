<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	</head>
<body>
	<%@ include file="../include/menu.jsp" %>
	<h3>상품목록</h3>
	
	<a href="write.do">상품등록</a>
	<table border="1">
		<tr>
			<th>상품ID</th>
			<th>&nbsp;</th>
			<th> 상품명</th>
			<th>가격</th>
		</tr>
		<c:forEach var="row" items="${list }">
		<tr>
			<td>${row.product_code }</td>
			<td><img src="/spring06/images/${row.filename}" width="100px" height=""></td>
			<td> <a href="/spring06/shop/product/detail/${row.product_code }">${row.product_name }</a>
			<c:if test="${sessionScope.admin_userid != null }">
				<br>
				<a href="/spring06/shop/product/edit/${row.product_code }">[편집]</a>
			</c:if></td>
			<td> <fmt:formatNumber value="${row.price }" pattern="#,###" /> </td>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>