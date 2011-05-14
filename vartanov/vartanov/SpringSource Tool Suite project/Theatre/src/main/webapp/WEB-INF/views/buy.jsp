<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<div class = round>
			Buy ticket: <br /><br />
			<span class = g>Date:</span> <c:out value = "${show.date}" /><br /><br />
			Place:<br />
			<span class = g>Row:</span> <c:out value = "${place.row}" /><br />
			<span class = g>Number:</span> <c:out value = "${place.number}" /><br />
			<span class = g>Type:</span> <c:out value = "${place.type}" /><br /><br />
			<h1> $<c:out value = "${price}" /> <a href = "<c:url value="/ticket/" /><c:out value = "${place.id}" />/<c:out value = "${show.id}" />">BUY</a></h1>
		</div>
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>
