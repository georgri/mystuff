<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><c:out value="${performance.title}" />. Enzet Theatres</title>
		<link rel = "stylesheet" type = "text/css" href = "<c:url value="/style/style.css" />">
	</head>
	<body>
		<div class = top><a href="<c:url value="/" />"><img src = "<c:url value="/resources/logo.jpg" />" /></a></div>
		<div class = menu><a href="<c:url value="/theatres/" />">THEATRES</a> &nbsp; &nbsp; <a href="<c:url value="/performances/" />">PERFORMANCES</a> &nbsp; &nbsp; <a href="<c:url value="/persons/" />">PERSONS</a></div>
		<div class = registration><c:if test = "${log != 'guest'}">you are logged as <c:out value="${log}" /> / <a href="<c:url value="/logout/" />">logout</a></c:if>	
			<c:if test = "${log == 'guest'}"><a href="<c:url value="/login/" />">login</a> / <a href="<c:url value="/registration/" />">registration</a></c:if></div>
		<table><tr><td>
		<span class = l><i><c:out value="${performance.title}" /></i></span>
		<br />
		<c:if test = "${log == 'admin'}">
			<a class = ad href="<c:url value="/deleteperformance/" /><c:out value="${performance.id}" />">&nbsp;- delete&nbsp;</a>
			<a class = ad href="<c:url value="/changeperformance/" /><c:out value="${performance.id}" />">&nbsp;change&nbsp;</a>
		</c:if>
		<br /><br />

		<span class = g>Theatre: </span><i>
		<c:if test = "${log == 'admin' && performance.theatre == NULL}">
			<a href = "<c:url value="/theatretoperformance/" /><c:out value="${performance.id}" />" class = ad>
				&nbsp;+ set theatre&nbsp;</a>
		</c:if>
		<c:if test = "${performance.theatre != NULL}">
			<a href="<c:url value="/theatre/" /><c:out value="${performance.theatre.id}" />"><c:out value="${performance.theatre.title}" /></a>
		</c:if>
		</i><br />
		
		<span class = g>Director: </span>
		<c:if test = "${log == 'admin' && performance.director == NULL}">
			<a href = "<c:url value="/directortoperformance/" /><c:out value="${performance.id}" />" class = ad>
				&nbsp;+ set director&nbsp;</a>
		</c:if>
		<c:if test = "${performance.director != NULL}">
			<a href="<c:url value="/person/" /><c:out value="${performance.director.id}" />"><c:out value="${performance.director.firstName}" /> <c:out value="${performance.director.lastName}" /></a>
		</c:if>
		<br />
		<span class = g>Duration: </span><c:out value="${performance.duration}" /> min<br /><br />
		Actors:
		<ol>
		<c:forEach items="${actors}" var="a">
			<li>
				<a href="<c:url value="/person/" /><c:out value="${a.id}" />"><c:out value="${a.firstName}" /> <c:out value="${a.lastName}" /></a><br />
			</li>
		</c:forEach>
		<c:if test = "${log == 'admin'}">
			<br />
			<a href = "<c:url value="/actortoperformance/" /><c:out value="${performance.id}" />" class = ad>
			&nbsp;+ add actor&nbsp;</a>
		</c:if>
		</ol>
		<br />
		Dates of shows:
		<ol>
		<c:forEach items="${shows}" var="s">
			<li>
				<a href="<c:url value="/show/" /><c:out value="${s.id}" />"><c:out value="${s.date}" /></a><br />
			</li>
		</c:forEach>
		<c:if test = "${log == 'admin'}">
			<br />
			<c:if test = "${performance.theatre != NULL}">
				<a href = "<c:url value="/showtoperformance/" /><c:out value="${performance.id}" />" class = ad>
				&nbsp;+ add show date&nbsp;</a>
			</c:if>
			<c:if test = "${performance.theatre == NULL}">
				<span class = error>Can not add a show. The theater is not specified.</span>
			</c:if>
		</c:if>
		</ol>
		</td></tr></table>
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>