<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp" />
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
  <script>
    var IMP = window.IMP;
    IMP.init("imp05688533");

    //function requestPay() {
      IMP.request_pay(
        {
          pg: "html5_inicis.INIpayTest",		//연동관리-연동정보에 PG Provider, MID
          pay_method: "card",
          merchant_uid: "javaclassS15_" + new Date().getTime(),
          name : "${vo.name}",		//상품명
          amount : "${vo.amount}",
          buyer_email: "${vo.buyer_email}",
          buyer_name: "${vo.buyer_name}",
          buyer_tel: "${vo.buyer_tel}",
          buyer_addr: "${vo.buyer_addr}",
          buyer_postcode: "${vo.buyer_postcode}",
        },		// 여까지는 이니시스에서 처리
        // 결제창에서 엑스 버튼 누르면  밑에 else로 가서 결제가 취소되었습니다뜨게ㅔ 됨
        function (rsp) {
          if(rsp.success) {
        	  alert("결재가 완료 되었습니다.");
        	  location.href = '${ctp}/study/payment/paymentOk';
          } else {
        	  alert("결재가 취소 되었습니다.");
        	  location.href = '${ctp}/study/payment/payment';
          }
        }
      );
    //}
  </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
  <p>
    <img src="${ctp}/images/payment.gif" />
  </p>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>