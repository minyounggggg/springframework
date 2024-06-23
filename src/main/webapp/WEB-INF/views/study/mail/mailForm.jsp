<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>mailForm.jsp</title>
	<jsp:include page="/WEB-INF/views/include/bs4.jsp" />
	<style>
		th{
			background-color : #eee;
			text-align : center;
		}
	</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
	<h2>메일보내기</h2>
	<p>(받는 사람의 메일 주소를 정확히 입력해주세요.)</p>
	<form name="myform" method="post">
		<table class="table table-bordered">
			<tr>
				<th>받는사람</th>
				<td>
					<div class="input-group">
						<input type="Text" name="toMail" placeholder="받는사람의 메일주소를 입력해주세요" autofocus required class="form-control"/>
						<div class="input-group-append">
							<input type="button" value="주소록" onclick="jucorokCheck()" class="btn btn-success" data-toggle="modal" data-target="#myModal"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th>메일 제목</th>
				<td><input type="Text" name="title" placeholder="메일제목을 입력해주세요" required class="form-control"/></td>
			</tr>
			<tr>
				<th>메일 내용</th>
				<td><textarea rows="7" name="content" placeholder="메일 내용을 입력해주세요" required class="form-control"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<input type="submit" value="메일보내기" class="btn btn-success mr-2" /> 
					<input type="reset" value="다시쓰기" class="btn btn-secondary mr-2" /> 
				</td>
			</tr>
		</table>
	</form>
</div>
<p><br/></p>

<!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">회원 주소록</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          Modal body..
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>

<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>