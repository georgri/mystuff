<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Shows. Enzet Theatres</title>
		<link rel = "stylesheet" type = "text/css" href = "<c:url value="/style/style.css" />">
	</head>
	<body> 
		<div class = top><a href="<c:url value="/" />"><img src = "<c:url value="/resources/logo.jpg" />" /></a></div>
		<div class = menu><a href="<c:url value="/theatres/" />">THEATRES</a> &nbsp; &nbsp; <a href="<c:url value="/performances/" />">PERFORMANCES</a> &nbsp; &nbsp; <a href="<c:url value="/persons/" />">PERSONS</a></div>
		<div class = registration><c:if test = "${log != 'guest'}">you are logged as <c:out value="${log}" /> / <a href="<c:url value="/logout/" />">logout</a></c:if>	
			<c:if test = "${log == 'guest'}"><a href="<c:url value="/login/" />">login</a> / <a href="<c:url value="/registration/" />">registration</a></c:if></div>
		<table><tr><td>
		<h1>
			Shows
			<br />
		</h1>
		<ol>
		<c:forEach items="${shows}" var="p">
			<li>
				<a href="<c:url value="/show/" /><c:out value="${p.id}" />"><span class = "title"><i><c:out value="${p.performance.title}" /></i> <c:out value="${p.date}" /></span></a><br />
		</c:forEach>
		</ol> 
		</td></tr></table>
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>