<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>

	// 카카오 로그아웃
	window.Kakao.init("4bed81139daac8fd06f75e2c669b1570");
	function kakaoLogout() {
		const accessToken = Kakao.Auth.getAccessToken();
		if(accessToken){	// 내 토큰이 살아잇다면 끊어조
			Kakao.Auth.logout(function () {
				window.location.href = "https://kauth.kakao.com/oauth/logout?client_id=4bed81139daac8fd06f75e2c669b1570&logout_redirect_uri=http://localhost:9090/javaclassS/member/memberLogout";
				
			});
		}
	}
</script>

<!-- Navbar -->
<div class="w3-top">
  <div class="w3-bar w3-black w3-card">
    <a class="w3-bar-item w3-button w3-padding-large w3-hide-medium w3-hide-large w3-right" href="javascript:void(0)" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    <a href="${ctp}/" class="w3-bar-item w3-button w3-padding-large">HOME</a> <!-- 프로젝트 제출시 주석풀고 밑에꺼 주석 -->
    <!-- <a href="http://192.168.50.53:9090/javaclassS/" class="w3-bar-item w3-button w3-padding-large">HOME</a> -->
    <a href="${ctp}/guest/guestList" class="w3-bar-item w3-button w3-padding-large w3-hide-small">GUEST</a>
    <c:if test="${!empty sLevel}">
	    <a href="${ctp}/board/boardList" class="w3-bar-item w3-button w3-padding-large w3-hide-small">BOARD</a>
	    <a href="${ctp}/pds/pdsList" class="w3-bar-item w3-button w3-padding-large w3-hide-small">PDS</a>
	    <div class="w3-dropdown-hover w3-hide-small">
	      <button class="w3-padding-large w3-button" title="More">STUDY_1 <i class="fa fa-caret-down"></i></button>     
	      <div class="w3-dropdown-content w3-bar-block w3-card-4">
	        <a href="${ctp}/user/userList" class="w3-bar-item w3-button">UserList</a>
	        <a href="${ctp}/dbtest/dbtestList" class="w3-bar-item w3-button">DB Test</a>
	        <a href="${ctp}/study/ajax/ajaxForm" class="w3-bar-item w3-button">Ajax Test</a>
	        <a href="${ctp}/study/restapi/restapi" class="w3-bar-item w3-button">REST API</a>
	        <a href="${ctp}/password/password" class="w3-bar-item w3-button">암호화</a>
	        <a href="${ctp}/study/mail/mailForm" class="w3-bar-item w3-button">메일연습</a>
	        <a href="${ctp}/study/fileUpload/fileUpload" class="w3-bar-item w3-button">파일업로드연습</a>
	      </div>
	    </div>
	    <div class="w3-dropdown-hover w3-hide-small">
	      <button class="w3-padding-large w3-button" title="More">MY PAGE <i class="fa fa-caret-down"></i></button>     
	      <div class="w3-dropdown-content w3-bar-block w3-card-4">
	        <a href="${ctp}/member/memberMain" class="w3-bar-item w3-button">회원메인방</a>
	        <a href="${ctp}/" class="w3-bar-item w3-button">일정관리</a>
	        <a href="${ctp}/" class="w3-bar-item w3-button">Photo Gallery</a>
	        <a href="${ctp}/member/memberList" class="w3-bar-item w3-button">회원리스트</a>
	        <a href="${ctp}/member/memberPwdCheck/p" class="w3-bar-item w3-button">비밀번호변경</a>
	        <a href="${ctp}/member/memberPwdCheck/i" class="w3-bar-item w3-button">회원정보수정</a>
	        <a href="${ctp}/member/memberPwdDeleteCheck" class="w3-bar-item w3-button">회원탈퇴</a>
	        <c:if test="${sLevel == 0}"><a href="${ctp}/admin/adminMain" class="w3-bar-item w3-button">관리자메뉴</a></c:if>
	        
	      </div>
	    </div>
    </c:if>
    <c:if test="${empty sLevel}">
	    <a href="${ctp}/member/memberLogin" class="w3-bar-item w3-button w3-padding-large w3-hide-small">LOGIN</a>
	    <a href="${ctp}/member/memberJoin" class="w3-bar-item w3-button w3-padding-large w3-hide-small">JOIN</a>
    </c:if>
    <c:if test="${!empty sLevel}">
	    <div class="w3-dropdown-hover w3-hide-small">
		      <button class="w3-padding-large w3-button" title="More">Logout <i class="fa fa-caret-down"></i></button>     
		      <div class="w3-dropdown-content w3-bar-block w3-card-4">
			    <a href="${ctp}/member/memberLogout" class="w3-bar-item w3-button w3-padding-large w3-hide-small">일반 LOGOUT</a>
			    <a href="javascript:kakaoLogout()" class="w3-bar-item w3-button w3-padding-large w3-hide-small">카카오 LOGOUT</a>
			  </div>
		</div>
    </c:if>
    <a href="javascript:void(0)" class="w3-padding-large w3-hover-red w3-hide-small w3-right"><i class="fa fa-search"></i></a>
  </div>
</div>

<!-- Navbar on small screens (remove the onclick attribute if you want the navbar to always show on top of the content when clicking on the links) -->
<div id="navDemo" class="w3-bar-block w3-black w3-hide w3-hide-large w3-hide-medium w3-top" style="margin-top:46px">
  <a href="${ctp}/guest/guestList" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">GUEST</a>
  <a href="${ctp}/board/boardList" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">BOARD</a>
  <a href="${ctp}/pds/pdsList" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">PDS</a>
  <a href="#" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">STUDY_1</a>
</div>