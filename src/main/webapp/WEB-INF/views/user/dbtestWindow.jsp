<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>dbtestWindow.jsp</title>
	<jsp:include page="/WEB-INF/views/include/bs4.jsp" />
	<script>
		'use strict';
		
		function wClose() {
			opener.window.myform.mid.value= "${mid}";
			opener.window.myform.name.focus();
			
			window.close();
		}
		
		function idCheck() {
			let mid= childForm.mid.value;
			if (mid == ""){
				alert("검색할 아이디를 입력하세요");
				childForm.mid.focus();
				return false;
			}
			childForm.submit();
		}
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>아이디 중복체크</h2>
	<hr/>
	<div class="text-center">
		<c:if test="${str == 'OK'}">${mid}는 사용가능한 아이디 입니다.</c:if>
		<c:if test="${str != 'OK'}">
		    <p>${mid}는 이미 사용중인 아이디입니다.</p>
			<form name="childForm" method="get" action="${ctp}/dbtest/dbtestWindow">
				<p>아이디 : 
					<input type="text" name="mid"/>	
					<input type="button" value="아이디검색" onclick="idCheck()"/>	
				</p>
			</form>
		</c:if>
	</div>
	<hr/>
	<div class="text-center"><input type="button" value="닫기" onclick="wClose()" class="btn btn-secondary"/></div>
</div>
<p><br/></p>
</body>
</html>