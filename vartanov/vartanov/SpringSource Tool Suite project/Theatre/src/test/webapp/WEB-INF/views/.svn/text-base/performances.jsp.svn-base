<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Performances. Enzet Theatres</title>
		<link rel = "stylesheet" type = "text/css" href = "<c:url value="/style/style.css" />">
	</head>
	<body> 
		<div class = top><a href="<c:url value="/" />">Enzet Theatres</a></div><br />
		<h1>
			All performances
		</h1>
		<ol>
		<c:forEach items="${performances}" var="p">
			<li class = g>
				<a href="<c:url value="/performance/" /><c:out value="${p.id}" />"><t class = "title"><i><c:out value="${p.title}" /></i></t></a><br />
				&nbsp; <t class = g>(<c:out value="${p.theatre.title}" />)</t><br /><br />
		</c:forEach>
		</ol> 
		<br />
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>