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
		<div class = top><a href="<c:url value="/" />">Enzet Theatres</a></div><br />
		<t class = l><i><c:out value="${performance.title}" /></i></t><br /><br />
		<t class = g>Theatre: </t><i>
			<a href="<c:url value="/theatre/" /><c:out value="${performance.theatre.id}" />"><c:out value="${performance.theatre.title}" /></a>
		</i><br />
		<t class = g>Director: </t><c:out value="${performance.director.firstName}" /> <c:out value="${performance.director.lastName}" /><br />
		<t class = g>Duration: </t><c:out value="${performance.duration}" /> min<br /><br />
		Actors:<br />
		<c:forEach items="${actors}" var="a">
			<a href="<c:url value="/person/" /><c:out value="${a.id}" />"><c:out value="${a.firstName}" /> <c:out value="${a.lastName}" /></a><br />
		</c:forEach><br />
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>