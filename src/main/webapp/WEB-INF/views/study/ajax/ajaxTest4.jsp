<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ajaxTest3_3.jsp</title>
	<jsp:include page="/WEB-INF/views/include/bs4.jsp" />
	<script>
		'use strict';
		
		function fCheck() {
			let user = document.getElementById("user").value;
			if(user.trim() == "") {
				alert("회원을 선택해주세요");
				return false;
			}
			
			$.ajax({
				url : "${ctp}/study/ajax/ajaxTest4",
				type : "post",
				data : {user : user},
				success : function (res) {
					let str = "선택하신 ["+user+"]님의 아이디는 ["+res.mid+"], 나이는["+res.age+"], 주소는["+res.address+"] 입니다.";
					$("#demo").html(str);
				},
				error : function () {
					alert("전송오류!!");
				}
			});
		}
		
	</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
	<h2><b>ajaxTest4.jsp (DB 회원 정보 출력 숙제)</b></h2>
	<hr/>
	<form>
		<h3>조회할 회원을 선택하세요</h3>
		<select name="user" id="user">
			<option value="">회원선택</option>
			<option>최민영</option>
			<option>김민영</option>
			<option>최영민</option>
			<option>영민최</option>
		</select>
		<input type="button" value="선택" onclick="fCheck()" class="btn btn-info mr-3 mb-3"/>
		<input type="button" value="돌아가기" onclick="location.href='ajaxForm';" class="btn btn-secondary mr-3 mb-3"/>
	</form>
	<div id="demo"></div>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>