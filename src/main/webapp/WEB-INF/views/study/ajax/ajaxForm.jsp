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
		
		function fCheck1() {
			let mid = document.getElementById("mid").value;
			if(mid.trim() == ""){
				alert("아이디를 입력하세요");
				document.getElementById("mid").focus();
				return false;
			}
			$.ajax({
				url : "${ctp}/study/ajax/ajaxTest4-1",
				type : "post",
				data : {mid : mid},
				success : function (vo) {
					console.log(vo);
					let str = "<h5>vo로 전송된 자료 출력</h5>";
					if(vo != ''){
						str += '아이디 : ' + vo.mid + '<br/>';
						str += '성명 : ' + vo.name + '<br/>';
						str += '나이 : ' + vo.age + '<br/>';
						str += '주소 : ' + vo.address +'<br/>';
					}
					else {
						str += "찾고자하는 자료가 없습니다.";
					}
					$("#demo3").html(str);
				},
				error : function () {
					alert("전송오류!!");
				}
			});
		}
		
		function fCheck2() {
			let mid = document.getElementById("mid").value;
			if(mid.trim() == ""){
				alert("아이디를 입력하세요");
				document.getElementById("mid").focus();
				return false;
			}
			$.ajax({
				url : "${ctp}/study/ajax/ajaxTest4-2",
				type : "post",
				data : {mid : mid},
				success : function (vos) {
					console.log(vos);
					let str = "<h5>vos로 전송된 자료 출력</h5>";
					
					if(vos != ''){
						str += '<table class="table-bordered text-center">';
						str += '<tr class="table- secondary">';
						str += '<th>아이디</th><th>성명</th><th>나이</th><th>주소</th>';
						str += '</tr>';
						for(let i=0; i<vos.length; i++){
							str += '<tr>';
							str += '<td>' + vos[i].mid + '</td>';
							str += '<td>' + vos[i].name + '</td>';
							str += '<td>' + vos[i].age + '</td>';
							str += '<td>' + vos[i].address + '</td>';
							str += '</tr>';
						}
						str += '<tr><td colspan="4" class="m-0 p-0"></td></tr>';
						str += '</table>';
					}
					else {
						str += "찾고자하는 자료가 없습니다.";
					}
					 
					$("#demo3").html(str);
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
	<hr/>
	<div>아이디 : 
		<input type="text" name="mid" id="mid" class="form-control mb-3" autofocus/>
		<input type="button" value="아이디 완전일치(vo)" onclick="fCheck1()" class="btn btn-info mr-2" />
		<input type="button" value="아이디 부분일치(vos)" onclick="fCheck2()" class="btn btn-info mr-2" />
	</div>
	<div id="demo3"></div>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>