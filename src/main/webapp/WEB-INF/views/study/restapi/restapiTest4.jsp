<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/WEB-INF/views/include/bs4.jsp" />
	<style>
		@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap');
	</style>
	<script>
		'use strict';
		
		const API_KEY = "RStKOLr2bOzRLB6kB8fWtzIGpNrY3qtmAuDHC%2FY5Cv1n1Rp5PsSz3cN9vo%2B0m3fG4Cb1jL8Z03x67F2fBwsWiw%3D%3D";
		
		
		async function getCrimeDate(){
			let year = $("#year").val();
			let apiYear = '';

			if(year == 2015) apiYear = "/15084592/v1/uddi:fbbfd36d-d528-4c8e-aa9b-d5cdbdeec669";
			else if(year == 2016) apiYear = "/15084592/v1/uddi:21ec6fa1-a033-413b-b049-8433f5b446ff";
			else if(year == 2017) apiYear = "/15084592/v1/uddi:67117bd9-5ee1-4e07-ae4a-bfe0861ee116";
			else if(year == 2018) apiYear = "/15084592/v1/uddi:2d687e27-b5c3-4bdb-9b77-c644dcafcbc7";
			else if(year == 2019) apiYear = "/15084592/v1/uddi:b6cc7731-181b-48e1-9a6c-ae81388e46b0";
			else if(year == 2020) apiYear = "/15084592/v1/uddi:fdde1218-987c-49ba-9326-8e3ba276141e";
			else if(year == 2021) apiYear = "/15084592/v1/uddi:943e757d-462b-4b3a-ab9f-9a8553637ca2";
			else if(year == 2022) apiYear = "/15084592/v1/uddi:5e08264d-acb3-4842-b494-b08f318aa14c";
			
			let url = "https://api.odcloud.kr/api";
			url += apiYear;
			url += "?serviceKey=" + API_KEY;
			url += "&page=1&perPage=200";
			
			let response = await fetch(url)
			//console.log("response : ", response);
			
			let res = await response.json();		//await 이거 쓸라면 async이거 function앞에 붙여야함
			console.log("res : ", res);
			
			let str0 = "<table class='table table-hover text-center'>"
				+ "<tr class='table-secondary'><th>번호</th><th>발생년도</th><th>경찰서</th><th>강도</th><th>절도</th><th>살인</th><th>폭력</th></tr>";
			
			let str1 = res.data.map((item, i) => [
				"<tr>"
				+ "<td>" + (i+1) + "</td>"
				+ "<td>" + item.발생년도 + "년 </td>"
				+ "<td>" + item.경찰서 + "건 </td>"
				+ "<td>" + item.강도 + "건 </td>"
				+ "<td>" + item.절도 + "건 </td>"
				+ "<td>" + item.살인 + "건 </td>"
				+ "<td>" + item.폭력 + "건 </td>"
				+ "</tr>"
			]).join('');
			str1 += "<tr><td colspan='7' class='m-0 p-0'></td></tr></table>";
			
			let str = str0 + str1;
			$("#demo").html(str);
		}
		
		// 검색한 자료 DB에 저장하기
		async function saveCrimeDate(){
			let year = $("#year").val();
			let apiYear = '';

			if(year == 2015) apiYear = "/15084592/v1/uddi:fbbfd36d-d528-4c8e-aa9b-d5cdbdeec669";
			else if(year == 2016) apiYear = "/15084592/v1/uddi:21ec6fa1-a033-413b-b049-8433f5b446ff";
			else if(year == 2017) apiYear = "/15084592/v1/uddi:67117bd9-5ee1-4e07-ae4a-bfe0861ee116";
			else if(year == 2018) apiYear = "/15084592/v1/uddi:2d687e27-b5c3-4bdb-9b77-c644dcafcbc7";
			else if(year == 2019) apiYear = "/15084592/v1/uddi:b6cc7731-181b-48e1-9a6c-ae81388e46b0";
			else if(year == 2020) apiYear = "/15084592/v1/uddi:fdde1218-987c-49ba-9326-8e3ba276141e";
			else if(year == 2021) apiYear = "/15084592/v1/uddi:943e757d-462b-4b3a-ab9f-9a8553637ca2";
			else if(year == 2022) apiYear = "/15084592/v1/uddi:5e08264d-acb3-4842-b494-b08f318aa14c";
			
			let url = "https://api.odcloud.kr/api";
			url += apiYear;
			url += "?serviceKey=" + API_KEY;
			url += "&page=1&perPage=200";
			
			let response = await fetch(url);
			//console.log("response : ", response);
			
			let res = await response.json();		//await 이거 쓸라면 async이거 function앞에 붙여야함
			console.log("res : ", res);
			
			let str = res.data.map((item, i) => [
				(i+1) + "."
				+ "발생년도 : " + item.발생년도 + "년"
				+ ", 경찰서 : " + item.경찰서 + "건"
				+ ", 강도 : " + item.강도 + "건"
				+ ", 절도 : " + item.절도 + "건"
				+ ", 살인 : " + item.살인 + "건"
				+ ", 폭력 : " + item.폭력 + "건"
				+ "<br/>"
			]);
			
			$("#demo").html(str);
			
			// 화면에 출력된 자료들을 모두 DB에 저장 시켜준다.
			//alert("경찰서 : " + res.data[0].경찰서);
			let query = "";
			for(let i=0; i<res.data.length; i++){
				if(res.data[i].경찰서 != null){
					query = {
							year : year,
							police : res.data[i].경찰서,
							robbery : res.data[i].강도,
							theft : res.data[i].절도,
							murder : res.data[i].살인,
							violence : res.data[i].폭력
					}
				}
				$.ajax({
					url : "${ctp}/study/restapi/saveCrimeData",
					type : "post",
					data : query,
					error : function () {
						alert("전송오류");
					}
				});
			}
			alert(year + "년도 자료가 DB에 저장되었습니다.");
		}
		
		function yearPoliceCheck() {
			let year = $("#year").val();
			let police = $("#police").val();
		}
		
	</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
	<h2>경찰철 강력범죄 발생 현황 자료 리스트</h2>
	<hr/>
	<form name="myform" method="post">
		<div>
			<select name="year" id="year">
			<c:forEach var="i" begin="2015" end="2022">
				<option value="${i}" ${year == i ? 'selected' : ''}>${i}년도</option>
			</c:forEach>
			</select>
			<input type="button" value="강력범죄제공 현황조회" onclick="getCrimeDate()" class="btn btn-success mb-3"/>
			<input type="button" value="강력범죄 DB저장" onclick="saveCrimeDate()" class="btn btn-success mb-3"/>
			<input type="button" value="강력범죄 DB삭제" onclick="deleteCrimeDate()" class="btn btn-success mb-3"/>
			<input type="button" value="강력범죄 DB출력" onclick="listCrimeDate()" class="btn btn-success mb-3"/>
		</div>
		<div>
			경찰서 지역명 : 
			<select name="police" id="police" onchange="policeCheck()">
				<option>서울</option>
				<option>경기</option>
				<option>강원</option>
				<option>충북</option>
				<option>충남</option>
				<option>전북</option>
				<option>전남</option>
				<option>경북</option>
				<option>경남</option>
				<option>제주</option>
			</select>&nbsp;&nbsp;
			: 정렬순서 : 
			<input type="radio" name="yearOrder" value="a" />오름차순
			<input type="radio" name="yearOrder" value="d" />내림차순 : 
			<input type="button" value="년도/경찰서별출력" onclick="yearPoliceCheck()" class="btn btn-info"/>
		</div>
	</form>
	<hr/>
	<div id="demo"></div>
	<hr/>
	<h3>범죄 분석 통계</h3>
	<!-- 1. 년도/강도/살인/절도/폭력 -->
	<!-- 2. 경찰서 별 통계 : 년도/강도/살인/절도/폭력 -->
	<!-- 3. 범죄발생건수가 가장ㅇ 작은 지역은? -->
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>