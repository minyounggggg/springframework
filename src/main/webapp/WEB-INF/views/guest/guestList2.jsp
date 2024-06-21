<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>guestList2.jsp</title>
    <jsp:include page="/WEB-INF/views/include/bs4.jsp" />
    <script>
    'use strict';
    
    	function delCheck(idx) {
			let ans = confirm("현재 방명록을 삭제하시겠습니까?");
			if(ans) {
				location.href = '${ctp}/GuestDelete?idx=' + idx;
				return false
			}
		}
    	
    	function pageSizeCheck() {
			let pageSize = document.getElementById("pageSize").value;
			location.href = "${ctp}/GuestList?pag=${pag}&pageSize="+pageSize;
		}
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
	<h2 class="text-center">방명록 리스트 (기본 페이징 처리)</h2>
	<table class="table table-borderless m-0 p-0">
	<tr>
		<!-- <td><a href="#" class="btn btn-primary">관리자</a></td> -->
		<td><a href="${ctp}/guest/guestInput.jsp" class="btn btn-success">글쓰기</a></td>
		<td class="text-right">
			<c:if test="${pag > 1}">
				<a href="${ctp}/GuestList?pag=1" title="첫페이지">◁◁</a>
				<a href="${ctp}/GuestList?pag=${pag-1}" title="이전페이지">◀</a>
			</c:if>
			${pag}/${totPage}
			<c:if test="${pag < totPage}">
				<a href="${ctp}/GuestList?pag=${pag+1}" title="다음페이지">▶</a>
				<a href="${ctp}/GuestList?pag=${totPage}" title="마지막페이지">▷▷</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="text-right">
			<select name="pageSize" id="pageSize" onchange="pageSizeCheck()">
				<option <c:if test="${pageSize == 2}">selected</c:if>>2</option>
				<option <c:if test="${pageSize == 3}">selected</c:if>>3</option>
				<option <c:if test="${pageSize == 5}">selected</c:if>>5</option>
				<option <c:if test="${pageSize == 10}">selected</c:if>>10</option>
			</select>
		</td>
	</tr>
	</table>
	<c:set var="curScrStarNO" value="${curScrStarNO}"></c:set>  <!-- 인덱스 번호 중간에 삭제해도 빵꾸 안나게 번호 처리 -->
	<c:forEach var="vo" items="${vos}" varStatus="st">
		<table class="table table-borderless m-0 p-0">
			<tr>
				<td>
					번호 : ${curScrStarNO}
					<c:if test="${sAdmin == 'OK' || sName == vo.name}"><a href="javascript:delCheck(${vo.idx})" class="btn btn-danger btn-sm">삭제</a></c:if>
					<!-- cmy1234로 로그인할떄 삭제버튼보이기. / 이름이 로그인한 이름과 같은거만 삭제버튼 보이기 -->
				</td>
				<td class="text-right">방문IP : ${vo.hostIp}</td>   <!-- 이거 안쓰면 메머장에 아이피버전포 그거 복사해서 캡쳐본에 넣기  -->
			</tr>
		</table>
		<table class="table table-bordered">
			<tr>
				<th>성명</th>
				<td>${vo.name}</td>
				<th>방문일자</th>
				<td>${fn:substring(vo.visitDate,0,19)}</td> <!-- .이 19번쨰 니까 거기부터 자르겟다 -->
			</tr>
			<tr>
				<th>메일주소</th>
				<td colspan="3">
<%-- 				<c:if test="${vo.email == '' || vo.email == null}">- 없음 -</c:if> <!-- 이렇게하면 에러가안나서 문제가 없긴하지만 --> --%>
<%-- 				<c:if test="${vo.email != '' && vo.email != null}">${vo.email}</c:if>       위랑 이거는 한짝--%> 
					<c:if test="${empty vo.email || fn:length(vo.email)<6 || fn:indexOf(vo.email,'@')==-1 || fn:indexOf(vo.email,'.')==-1}">- 없음 -</c:if>   <!-- 이메일 없는 사람들은 -없음- 이라고 뜨게함, empty / 이메일형식 체크 -->
					<c:if test="${!empty vo.email && fn:length(vo.email)>=6 && fn:indexOf(vo.email,'@')!=-1 && fn:indexOf(vo.email,'.')!=-1}">${vo.email}</c:if>
				</td>
			</tr>
			<tr>
				<th>홈페이지</th>
				<td colspan="3">
					<c:if test="${empty vo.homePage || fn:length(vo.homePage)<=10 || fn:indexOf(vo.homePage,'.')==-1}">- 없음 -</c:if>   <!-- 작성앟ㄴ 홈페이지의 주소의 글자수가 10보다 작거나 같고, .이 들어가있지 않다면 링크걸지않음 -->
					<c:if test="${!empty vo.homePage && fn:length(vo.homePage)>10 && fn:indexOf(vo.homePage,'.')!=-1}"><a href='${vo.homePage}' target='_blank'>${vo.homePage}</a></c:if>
				</td>
			</tr>
			<tr>
				<th>방문소감</th>
				<td colspan="3">${fn:replace(vo.content, newLine, "<br/>")}</td>  <!-- 엔터처리 >>>  vo.content 에 newLine 이 있다면 <br/> 처리해라-->
			</tr>
		</table>
		<br/>
		<c:set var="curScrStarNO" value="${curScrStarNO - 1}"></c:set>
	</c:forEach>
	<br/>
	<!-- 블록페이지 시작 -->
	<div class="text-center">
	
		<c:if test="${pag > 1 }">[<a href="${ctp}/GuestList?pag=1&pageSize=${pageSize}">첫 페이지</a>]</c:if>
		<c:if test="${curBlock > 0}">[<a href="${ctp}/GuestList?pag=${(curBlock-1) * blockSize + 1}&pageSize=${pageSize}">이전블록</a>]</c:if>
		<c:forEach var="i" begin="${(curBlock * blockSize)+1}" end="${(curBlock * blockSize) + blockSize}" varStatus="st">
			<!-- 현재있는 페이지 수만 굵게 표시한다. -->
			<c:if test="${i <= totPage && i == pag}"><a href="${ctp}/GuestList?pag=${i}&pageSize=${pageSize}">[<font color="red"><b>${i}</b></font>]</a></c:if> 
			<!-- 현재있는 페이지가 아닌 숫자는 일반 표시, 두껍게 하지 않는다. -->
			<c:if test="${i <= totPage && i != pag}"><a href="${ctp}/GuestList?pag=${i}&pageSize=${pageSize}">[${i}]</a></c:if>
		</c:forEach>
		<c:if test="${curBlock < lastBlock}">[<a href="${ctp}/GuestList?pag=${(curBlock+1) * (blockSize+1)}&pageSize=${pageSize}">다음블록</a>]</c:if>
		<c:if test="${pag < totPage}">[<a href="${ctp}/GuestList?pag=${totPage}&pageSize=${pageSize}">마지막 페이지</a>]</c:if>
		
	</div>
	<!-- 블록페이지 끝 -->
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>