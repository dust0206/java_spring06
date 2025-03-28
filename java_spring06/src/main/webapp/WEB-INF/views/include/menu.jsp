<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

	<a href="/spring06/upload/input.do">업로드 테스트</a> 
	
	<a href="/spring06/shop/product/list.do">상품목록</a>

	<div style="text-align:right;">
		<c:choose>
			<c:when test="${sessionScope.userid == null }">
				<a href="/spring06/member/login.do">로그인</a>
				<a href="/spring06/admin/login.do">관리자 로그인</a>
			</c:when>
			<c:otherwise>
				${sessionScope.name }님이 로그인중입니다.
				<a href="/spring06/member/logout.do">로그아웃</a>
				<a href="/spring06/admin/logout.do">관리자 로그아웃</a>
			</c:otherwise>
		</c:choose>
	</div>
	
	