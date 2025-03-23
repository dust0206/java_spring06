<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	</head>
<body>
	<%@ include file="../include/menu.jsp" %>
	<h3>상품 정보</h3>
	
	<table>
		<tr>
				          <!-- 이미지 표시 -->
			<td><img src="/spring06/images/${dto.filename }" width="300px" height="300px"></td>
			<td align="center">
				<table>
					<tr>
						<td>상품명</td>
						<td>${dto.product_name }</td>
					</tr>
					<tr>
						<td>가격</td>
						<td>${dto.price }
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>${dto.description } </td>
					</tr>
					<tr>
						<td colspan="2">
							<form name="form1" method="post" action="/spring06/shop/cart/insert.do">
								<input type="hidden" name="product_code" value="${dto.product_code }">
								<select name="amount">
									<c:forEach begin="1" end="10" var="i">
										<option value="${i }">${i }</option>
									</c:forEach>
								</select>&nbsp;개
								<input type="submit" value="장바구니에 담기">
							</form>
							<a href="/spring06/shop/product/list.do">상품목록</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>