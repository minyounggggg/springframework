<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ajaxForm.jsp</title>
	<jsp:include page="/WEB-INF/views/include/bs4.jsp" />
	<script>
		'use strict';
		
		function ajaxTest1(idx) {
			//location.href = "${ctp}/study/ajax/ajaxTest1?idx="+idx;
			$.ajax({
				url : "${ctp}/study/ajax/ajaxTest1",
				type : "post",
				data : {idx : idx},
				success : function (res) {
					$("#demo1").html(res);
				},
				error : function () {
					alert("전송오류!!");
				}
			});
		}
		
		function ajaxTest2(str) {
			//location.href = "${ctp}/study/ajax/ajaxTest1?idx="+idx;
			$.ajax({
				url : "${ctp}/study/ajax/ajaxTest2",
				type : "post",
				//contentType : "application/x-www-form-urlencoded; charset=utf-8",
				//headers : {Content-Type : "application/json"},
				data : {str : str},
				success : function (res) {
					$("#demo2").html(res);
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
	<h2>AJAX 연습</h2>
	<hr/>
	<div>기본(int -> String -> int -> String) : <br/><!-- 출발 int, 웹을 통해서는 String 받을땐 다시 int로 받아서 처리 후 String으로 돌아온다. -->
		<a href="javascript:ajaxTest1(10)" class="btn btn-success mr-2 mb-2">값 전달 1</a>
		: <span id="demo1"></span>
	</div>
	<div style="margin-top:30px">기본(String) : <br/>
		<a href="javascript:ajaxTest2('안녕')" class="btn btn-success mr-2 mb-2">값 전달 2</a>
		: <span id="demo2"></span>
	</div>
	<hr/>
	<div>응용(배열) - 시(도) / 구(시,군,동) 출력<br/>
		<a href="${ctp}/study/ajax/ajaxTest3_1" class="btn btn-primary mr-2">String 배열</a>
		<a href="${ctp}/study/ajax/ajaxTest3_2" class="btn btn-primary mr-2">ArrayList</a>
		<a href="${ctp}/study/ajax/ajaxTest3_3" class="btn btn-primary mr-2">Map 형식</a>
	</div>
	<hr/>
	<div>DB회원 정보 출력<br/>
		<a href="${ctp}/study/ajax/ajaxTest4" class="btn btn-warning mr-2">DB성명으로 자료검색</a>
		<a href="${ctp}/study/ajax/ajaxTest4_2" class="btn btn-warning mr-2">DB성명으로 자료검색22</a>
	</div>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>