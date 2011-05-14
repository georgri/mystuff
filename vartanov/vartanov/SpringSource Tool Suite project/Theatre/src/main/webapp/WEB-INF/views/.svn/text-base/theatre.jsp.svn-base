<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><c:out value="${t.title}" />. Enzet Theatres</title>
		<link rel = "stylesheet" type = "text/css" href = "<c:url value="/style/style.css" />">
	</head>
	<body>
		<div class = top><a href="<c:url value="/" />"><img src = "<c:url value="/resources/logo.jpg" />" /></a></div>
		<div class = menu><a href="<c:url value="/theatres/" />">THEATRES</a> &nbsp; &nbsp; <a href="<c:url value="/performances/" />">PERFORMANCES</a> &nbsp; &nbsp; <a href="<c:url value="/persons/" />">PERSONS</a></div>
		<div class = registration><c:if test = "${log != 'guest'}">you are logged as <c:out value="${log}" /> / <a href="<c:url value="/logout/" />">logout</a></c:if>	
			<c:if test = "${log == 'guest'}"><a href="<c:url value="/login/" />">login</a> / <a href="<c:url value="/registration/" />">registration</a></c:if></div>
		<table><tr><td>
		<span class = l><i><c:out value="${t.title}" /></i></span>
		<br />
		<c:if test = "${log == 'admin'}">
			<a class = ad href="<c:url value="/deletetheatre/" /><c:out value="${t.id}" />">&nbsp;- delete&nbsp;</a>
			<a class = ad href="<c:url value="/changetheatre/" /><c:out value="${t.id}" />">&nbsp;change&nbsp;</a>
		</c:if>
		<br /><br />
		<span class = g>Address:</span> <span class = "address"><c:out value="${t.address}" /></span><br /><br />
		PERFORMANCES
		<ol>
			<c:forEach items="${performances}" var="p">
			<li>
				<a href="<c:url value="/performance/" /><c:out value="${p.id}" />"><c:out value="${p.title}" /></a><br />
				&nbsp; <span class = g>(<c:out value="${p.duration}" /> min, dir. &mdash; <c:out value="${p.director.firstName}" /> <c:out value="${p.director.lastName}" />)</span><br /><br />
			</c:forEach>
		</ol>
		</td></tr></table>
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>