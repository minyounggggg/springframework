<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>thumbnailform.jsp</title>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>썸네일 연습</h2>
	<form name="myform" method="post" enctype="multipart/form-data">
		<p>파일명 : 
			<input type="file" name="file" id="file" class="form-control-file border" accept=".jpg,.gif,.png"/>
		</p>
		<p> 
			<input type="button" value="썸네일만들기" onclick="fCheck()" class="btn btn-success"/>
		</p>
	</form>
</div>
<p><br/></p>
</body>
</html>