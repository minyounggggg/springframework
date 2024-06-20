<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ajaxTest4_2.jsp</title>
	<jsp:include page="/WEB-INF/views/include/bs4.jsp" />
	<script>
		'use strict';
		
		function fCheck() {
			let user = document.getElementById("user").value;
			let uData = document.getElementById("uData").value;
			if(user.trim() == "") {
				alert("회원을 선택해주세요");
				return false;
			}
			else if(uData.trim() == "") {
				alert("조회할 정보를 선택해주세요");
				return false;
			}
			
			$.ajax({
				url : "${ctp}/study/ajax/ajaxTest4_2",
				type : "post",
				data : {user : user, uData : uData},
				success : function (res) {
					//console.log(res);
					let str = '<option>도시선택</option>';
					for(let i=0; i<res.city.length; i++){
						str += '<option>'+res.city[i]+'</option>';
					}
					let str = "선택하신 " + user + "님의 " + uData + "(은)는" + str + "입니다.";
					$("#demo").html(str);
				},
				error : function () {
					alert("전송오류!!");
				}
			});
		}
		/*
		function fCheck() {
			let user = $("#user").val();
			let uData = $("#uData").val();
			
			if(user == "" || uData == ""){
				alert("항목을 선택 후 눌러주세요");
				return false;
			}
			let str = "선택하신 " + user + "님의 " + uData + "(은)는" + str + "입니다.";
			$("#demo").html(str);
		}
		*/
	</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
	<h2><b>ajaxTest4_2.jsp (DB 회원 정보 출력 숙제2)</b></h2>
	<hr/>
	<form>
		<h3>조회할 회원과 목록을 선택하세요</h3>
		<select name="user" id="user" onchange="userCheck()">
			<option value="">회원선택</option>
			<option>최민영</option>
			<option>김민영</option>
			<option>최영민</option>
			<option>영민최</option>
		</select>
		<select name="uData" id="uData" onchange="uDataCheck()">
			<option value="">조회할 정보선택</option>
			<option>아이디</option>
			<option>이름</option>
			<option>나이</option>
			<option>주소</option>
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