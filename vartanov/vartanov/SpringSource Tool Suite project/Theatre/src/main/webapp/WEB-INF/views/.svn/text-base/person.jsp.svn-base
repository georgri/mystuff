<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><c:out value="${person.firstName}" /> <c:out value="${person.lastName}" />. Enzet Theatres</title>
		<link rel = "stylesheet" type = "text/css" href = "<c:url value="/style/style.css" />">
	</head>
	<body>
		<div class = top><a href="<c:url value="/" />"><img src = "<c:url value="/resources/logo.jpg" />" /></a></div>
		<div class = menu><a href="<c:url value="/theatres/" />">THEATRES</a> &nbsp; &nbsp; <a href="<c:url value="/performances/" />">PERFORMANCES</a> &nbsp; &nbsp; <a href="<c:url value="/persons/" />">PERSONS</a></div>
		<div class = registration><c:if test = "${log != 'guest'}">you are logged as <c:out value="${log}" /> / <a href="<c:url value="/logout/" />">logout</a></c:if>	
			<c:if test = "${log == 'guest'}"><a href="<c:url value="/login/" />">login</a> / <a href="<c:url value="/registration/" />">registration</a></c:if></div>
		<table><tr><td colspan = 2>
			<span class = l><c:out value="${person.firstName}" /> <c:out value="${person.lastName}" /></span><br />
			<c:if test = "${log == 'admin'}">
				<span class = delete>- <a href="<c:url value="/deleteperson/" /><c:out value="${person.id}" />">delete</a></span>
				<span class = delete>/ <a href="<c:url value="/changeperson/" /><c:out value="${person.id}" />">change</a></span>
			</c:if>
		</td></tr><tr><td>
		Director of performances:
		<ol>
		<c:forEach items="${dperformances}" var="p">
			<li>
				<a class = caption href="<c:url value="/performance/" /><c:out value="${p.id}" />"><c:out value="${p.title}" /></a><br />
				&nbsp; <span class = g>(<c:out value="${p.duration}" /> min)</span><br /><br />
			</li>
		</c:forEach>
		</ol>
		</td><td>
		Actor in performances:<br />
		<ol>
			<c:forEach items="${aperformances}" var="p">
			<li>
				<a class = caption href="<c:url value="/performance/" /><c:out value="${p.id}" />"><c:out value="${p.title}" /></a><br />
				&nbsp; <span class = g>(<c:out value="${p.duration}" /> min, dir. &mdash; <c:out value="${p.director.firstName}" /> <c:out value="${p.director.lastName}" />)</span><br /><br />
			</c:forEach>
		</ol>
		</td></tr></table>
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>