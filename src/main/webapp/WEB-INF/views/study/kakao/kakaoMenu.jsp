<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<!-- kakaoMenu.jsp -->

<div>
	<p>
		<a href="${ctp}/study/kakao/kakaoEx1" class="btn btn-warning"><b>마커표시/저장</b></a>
		<a href="${ctp}/study/kakao/kakaoEx2" class="btn btn-warning"><b>MyDB에 저장된 지명검색</b></a>
		<a href="${ctp}/study/kakao/kakaoEx3" class="btn btn-warning"><b>kakaoDB에 저장된 키워드검색</b></a>
		<a href="${ctp}/study/kakao/kakaoEx4" class="btn btn-warning"><b>주변검색</b></a>
		<a href="${ctp}/study/kakao/kakaoEx5" class="btn btn-warning"><b>거리계산</b></a>
		<a href="${ctp}/study/kakao/kakaoEx6" class="btn btn-warning"><b>검색지점 사진저장</b></a>
	</p>
</div>