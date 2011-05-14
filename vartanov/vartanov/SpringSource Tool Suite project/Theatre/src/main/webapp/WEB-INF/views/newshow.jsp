<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Enzet Theatres</title>
		<link rel = "stylesheet" type = "text/css" href = "<c:url value="/style/style.css" />">
	</head>
	<body>
		<div class = top><a href="<c:url value="/" />"><img src = "<c:url value="/resources/logo.jpg" />" /></a></div>
		<div class = menu><a href="<c:url value="/theatres/" />">THEATRES</a> &nbsp; &nbsp; <a href="<c:url value="/performances/" />">PERFORMANCES</a> &nbsp; &nbsp; <a href="<c:url value="/persons/" />">PERSONS</a></div>
		<div class = registration><c:if test = "${log != 'guest'}">you are logged as <c:out value="${log}" /> / <a href="<c:url value="/logout/" />">logout</a></c:if>	
			<c:if test = "${log == 'guest'}"><a href="<c:url value="/login/" />">login</a> / <a href="<c:url value="/registration/" />">registration</a></c:if></div>
		<form method="GET" action="/Theatre/addshow">
		<div class = round>
		<table class = form>
			<tr>
				<td>Performance: &nbsp; </td> 
				<td>
					<c:out value="${show1.performance.title}" />
					<input value="${show1.performance.id}" type="hidden" name="performanceId" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td>Date: &nbsp; </td> 
				<td><input type="text" name="date" /></td>
				<td><span class = g>in format YYYY.MM.DD.HH.MM.SS</span></td> 
			</tr>
			<tr>
				<td colspan = 2><br /><input type="submit" value="Add" /></td>
				<td></td>
			</tr>
		</table>
		</div>
		</form>
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>