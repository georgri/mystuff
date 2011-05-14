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
		<span class = l><i><c:out value="${performance.title}" /></i></span><br />
		<h1><c:out value="${show.date}" /> </h1>
		<c:if test = "${log == 'admin'}"><span class = delete>- <a href="<c:url value="/deleteshow/" /><c:out value="${show.id}" />">delete</a></span></c:if>
		<br /><br />
		<span class = g>Theatre: </span><i>
			<a href="<c:url value="/theatre/" /><c:out value="${performance.theatre.id}" />"><c:out value="${performance.theatre.title}" /></a>
		</i><br />
		<span class = g>Director: </span><c:out value="${performance.director.firstName}" /> <c:out value="${performance.director.lastName}" /><br />
		<span class = g>Duration: </span><c:out value="${performance.duration}" /> min<br /><br />
		<c:if test = "${log == 'guest'}">
			<span class = error><a href = "<c:url value = "/login/" />">Sign up</a> or <a href = "<c:url value = "/registration/" />">register</a> to buy a ticket.</span>
		</c:if>
		<c:if test = "${log != 'guest'}">
			Buy ticket:
			<br /><br />
			
			<span class = g>Balcony:</span><br />
			<c:if test = "${cb.price == 0 || cb.price == -1}">
				<c:if test = "${log == 'admin'}">
					<a href="<c:url value="/pricetoshow/1/" /><c:out value="${show.id}" />" class = ad>&nbsp;set price&nbsp;</a>&nbsp;
				</c:if>
				<c:if test = "${log != 'admin'}">
					no price &nbsp; 
				</c:if>
			</c:if>
			<c:if test = "${cb.price != 0 && cb.price != -1}">
				$<c:out value = "${cb.price}" /> 
			</c:if>
			<c:forEach items="${b}" var="pl">
				<c:if test = "${cb.price != 0 && cb.price != -1}">
					 &nbsp; <a href = "<c:url value = "/buy/" /><c:out value = "${pl.id}" />/<c:out value = "${show.id}" />">[r. <c:out value="${pl.row}" />, n. <c:out value="${pl.number}" />]</a> &nbsp; 
				</c:if>
				<c:if test = "${cb.price == 0 || cb.price == -1}">
					[r. <c:out value="${pl.row}" />, n. <c:out value="${pl.number}" />] &nbsp; 
				</c:if>
			</c:forEach>
			<c:forEach items="${bb}" var="pl">
				<span class = error>[r. <c:out value="${pl.row}" />, n. <c:out value="${pl.number}" />]</span> &nbsp; 
			</c:forEach><br /><br />
			
			<span class = g>Stalls:</span><br />
			<c:if test = "${cs.price == 0 || cs.price == -1}">
				<c:if test = "${log == 'admin'}">
					<a href="<c:url value="/pricetoshow/0/" /><c:out value="${show.id}" />" class = ad>&nbsp;set price&nbsp;</a>&nbsp;
				</c:if>
				<c:if test = "${log != 'admin'}">
					no price &nbsp; 
				</c:if>
			</c:if>
			<c:if test = "${cs.price != 0 && cs.price != -1}">
				$<c:out value = "${cs.price}" /> 
			</c:if>
			<c:forEach items="${s}" var="pl">
				<c:if test = "${cs.price != 0 && cs.price != -1}">
					 &nbsp; <a href = "<c:url value = "/buy/" /><c:out value = "${pl.id}" />/<c:out value = "${show.id}" />">[r. <c:out value="${pl.row}" />, n. <c:out value="${pl.number}" />]</a> &nbsp; 
				</c:if>
				<c:if test = "${cs.price == 0 || cs.price == -1}">
					[r. <c:out value="${pl.row}" />, n. <c:out value="${pl.number}" />] &nbsp; 
				</c:if>
			</c:forEach>
			<c:forEach items="${bs}" var="pl">
				<span class = error>[r. <c:out value="${pl.row}" />, n. <c:out value="${pl.number}" />]</span> &nbsp; 
			</c:forEach><br /><br />
			
			<span class = g>Dress circle:</span><br />
			<c:if test = "${cd.price == 0 || cd.price == -1}">
				<c:if test = "${log == 'admin'}">
					<a href="<c:url value="/pricetoshow/2/" /><c:out value="${show.id}" />" class = ad>&nbsp;set price&nbsp;</a>&nbsp;
				</c:if>
				<c:if test = "${log != 'admin'}">
					no price &nbsp; 
				</c:if>
			</c:if>
			<c:if test = "${cd.price != 0 && cd.price != -1}">
				$<c:out value = "${cd.price}" /> 
			</c:if>
			<c:forEach items="${d}" var="pl">
				<c:if test = "${cd.price != 0 && cd.price != -1}">
					 &nbsp; <a href = "<c:url value = "/buy/" /><c:out value = "${pl.id}" />/<c:out value = "${show.id}" />">[r. <c:out value="${pl.row}" />, n. <c:out value="${pl.number}" />]</a> &nbsp; 
				</c:if>
				<c:if test = "${cd.price == 0 || cd.price == -1}">
					[r. <c:out value="${pl.row}" />, n. <c:out value="${pl.number}" />] &nbsp; 
				</c:if>
			</c:forEach>
			<c:forEach items="${bd}" var="pl">
				<span class = error>[r. <c:out value="${pl.row}" />, n. <c:out value="${pl.number}" />]</span> &nbsp; 
			</c:forEach>
		</c:if>
		</td></tr></table>
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
	</body>
</html>