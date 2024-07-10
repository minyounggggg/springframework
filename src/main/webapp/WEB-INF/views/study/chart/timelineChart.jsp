<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>timelineChart</title>
	<jsp:include page="/WEB-INF/views/include/bs4.jsp" />
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
	  google.charts.load("current", {packages:["timeline"]});
	  google.charts.setOnLoadCallback(drawChart);
	  function drawChart() {
	    var container = document.getElementById('example2.1');
	    var chart = new google.visualization.Timeline(container);
	    var dataTable = new google.visualization.DataTable();
	
	    dataTable.addColumn({ type: 'string', id: 'Term' });
	    dataTable.addColumn({ type: 'string', id: 'Name' });
	    dataTable.addColumn({ type: 'date', id: 'Start' });
	    dataTable.addColumn({ type: 'date', id: 'End' });
	
	    dataTable.addRows([
	      [ '1', 'George Washington', new Date(1789, 3, 30), new Date(1797, 2, 4) ],
	      [ '2', 'John Adams',        new Date(1797, 2, 4),  new Date(1801, 2, 4) ],
	      [ '3', 'Thomas Jefferson',  new Date(1801, 2, 4),  new Date(1809, 2, 4) ]]);
	
	    chart.draw(dataTable);
	  }
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	<div id="example2.1" style="height: 200px;"></div>
</div>
<p><br/></p>
</body>
</html>