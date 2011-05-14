<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Theatres. Enzet Theatres</title>
		<link rel = "stylesheet" type = "text/css" href = "<c:url value="/style/style.css" />">
	</head>
	<body> 
		<div class = top><a href="<c:url value="/" />">Enzet Theatres</a></div><br />
		<h1>
			All theatres
		</h1>
		<c:forEach items="${AllTheatres}" var="th">
			<div class = column>
				<a href="<c:url value="/theatre/" /><c:out value="${th.id}" />"><t class = "title"><i><c:out value="${th.title}" /></i></t></a>
				<t class = g>(<c:out value="${th.address}" />)</t>
			</div>
		</c:forEach>
		<br />
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>