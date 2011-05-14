<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><c:out value="${theat.title}" />. Enzet Theatres</title>
		<link rel = "stylesheet" type = "text/css" href = "<c:url value="/style/style.css" />">
	</head>
	<body>
		<div class = top><a href="<c:url value="/" />">Enzet Theatres</a></div><br />
		<t class = l><i><c:out value="${theat.title}" /></i></t><br /><br />
		<t class = g>Address:</t> <t class = "address"><c:out value="${theat.address}" /></t><br /><br />
		Performances in this theatre:
		<ol>
			<c:forEach items="${performances}" var="p">
			<li>
				<a href="<c:url value="/performance/" /><c:out value="${p.id}" />"><c:out value="${p.title}" /></a><br />
				&nbsp; <t class = g>(<c:out value="${p.duration}" /> min, dir. &mdash; <c:out value="${p.director.firstName}" /> <c:out value="${p.director.lastName}" />)</t><br /><br />
			</c:forEach>
		</ol>
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>