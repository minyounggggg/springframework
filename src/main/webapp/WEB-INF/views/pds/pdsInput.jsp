<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>pdsInput.jsp</title>
    <jsp:include page="/WEB-INF/views/include/bs4.jsp" />
    <script>
    	'use strict';
    	let cnt = 1;
    	
    	/*
    	function fCheck() {
			let fName1 = document.getElementById("fName1").value;
			//let ext1 = fName1.substring(fName1.lastIndexOf(".")+1).toLowerCase();
			let maxSize = 1024 * 1024 * 30;  // 기본단위 : Byte, 1024*1024*10 = 10MByte 허용
			let title = $("#title").val();
			
			if(fName1.trim() == ""){
				alert("업로드할 파일을 선택하세요");
				return false;
			}
			else if(title.trim() == ""){
				alert("업로드할 파일을 선택하세요");
				return false;
			}
			
			//파일사이즈와 확장자 체크하기
			let fileSize = 0;
			for(let i=1; i<=cnt; i++){
				let imsiName = "fName" + i;
				if(isNaN(document.getElementById(imsiName))){		
					let fName = document.getElementById(imsiName).value;
					if(fName != ""){
						fileSize += document.getElementById(imsiName).files[0].size;
						let ext1 = fName.substring(fName.lastIndexOf(".")+1).toLowerCase();
						if(ext1 != 'jpg' && ext1 != 'gif' && ext1 != 'png' && ext1 != 'zip' && ext1 != 'hwp' && ext1 != 'ppt' && ext1 != 'pptx' && ext1 != 'doc' && ext1 != 'pdf' && ext1 != 'xlsx' && ext1 != 'txt') {
			    		    alert("업로드 가능한 파일은 'jpg/gif/png/zip/hwp/ppt/pptx/doc/pdf/xlsx/txt'만 가능합니다.");
							return false;
						}
					}
				}
			}
				
			if(fileSize > maxSize) {
				alert("업로드할 파일의 최대용량은 30MByte입니다.");
				return false;
			}
			else{
				myform.fSize.value = fileSize;
				//alert("파일 총 사이즈 : " + fileSize);
				myform.submit();
			}
		}
    	*/
    	function fCheck() {
        	let fName = document.getElementById("fName").value;
        	//let title = $("#title").val();
        	let ext = "";
        	let fileSize = 0;
        	let maxSize = 1024 * 1024 * 20;	// 기본 단위 : Byte,   1024 * 1024 * 20 = 20MByte 허용
        	
        	let fileLength = document.getElementById("fName").files.length;	// 선택한 파일의 갯수
        	if(fileLength < 1) {
        		alert("업로드할 파일을 선택하세요");
        		return false;
        	}
        	
        	for(let i=0; i<fileLength; i++) {
        		fName = document.getElementById("fName").files[i].name;		// 선택된 1개의 파일이름가져오기
        		ext = fName.substring(fName.lastIndexOf(".")+1).toLowerCase();
    	    	fileSize = document.getElementById("fName").files[i].size;
    	    	if(ext != 'jpg' && ext != 'gif' && ext != 'png' && ext != 'zip') {
    	    		alert("업로드 가능한 파일은 'jpg/gif/png'만 가능합니다.");
    	    	}
        	}
        	
        	if(fileSize > maxSize) {
        		alert("업로드 파일의 최대용량은 20MByte입니다.");
        	}
        	else {
    	    	myform.fSize.value = fileSize;
        		myform.submit();
        	}
        }
    	
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
	<form name="myform" method="post" class="was-validated" enctype="multipart/form-data">
		<h2 class="text-center">자료올리기</h2>
		<br/>
		<div>
			<input type="file" name="file" id="fName" multiple class="form-control-file border mb-2"/> 	<!-- vo에있는 fName과 여기 name의 이름이 동일하면안된다 -->
		</div>
		<div id="fileBox"></div>
		<div class="mb-2">
			올린이 : ${sNickName}
		</div>
		<div class="mb-2">
			제목 : <input type="text" name="title" id="title" placeholder="자료의 제목을 입력하세요" class="form-control" required/>
		</div>
		<div class="mb-2">
			내용 : <textarea rows="4" name="content" id="content" placeholder="자료의 상세내역을 입력하세요" class="form-control"></textarea>
		</div>
		<div class="mb-2">
			분류 : 
			<select name="part" id="part" class="form-control">
				<option ${part == "학습" ? "selected" : ""}>학습</option>
				<option ${part == "여행" ? "selected" : ""}>여행</option>
				<option ${part == "음식" ? "selected" : ""}>음식</option>
				<option ${part == "기타" ? "selected" : ""}>기타</option>
			</select> 
		</div>
		<div class="mb-2">
			공개여부 : 
			<input type="radio" name="openSw" value="공개" onclick="pwdCheck1()" checked class="mr-3"/>공개
			<input type="radio" name="openSw" value="비공개" onclick="pwdCheck2()" class="mr-3"/>비공개
			<div id="pwdDemo" style="display:none"><input type="password" name="pwd" id="pwd" value="1234"/></div>
		</div>
		<div class="mb-2">
			<input type="button" value="자료올리기" onclick="fCheck()" class="btn btn-success"/>
			<input type="reset" value="다시쓰기" class="btn btn-warning"/>
			<input type="button" value="돌아가기" onclick="location.href='PdsList.pds?part=${part}';" class="btn btn-info"/>
		</div>
		<input type="hidden" name="hostIp" value="${pageContext.request.remoteAddr}"/>
		<input type="hidden" name="mid" value="${sMid}"/>
		<input type="hidden" name="nickName" value="${sNickName}"/>
		<input type="hidden" name="fSize" value="0"/>
	</form>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>