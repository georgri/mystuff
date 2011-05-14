<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Enzet Theatres</title>
		<link rel = "stylesheet" type = "text/css" href = "<c:url value="/style/style.css" />">
	</head>
	<body>
		<div class = top><a href="<c:url value="/" />">Enzet Theatres</a></div><br />
  <h1>
		<a href="<c:url value="/theatres" />">All theatres list</a><br />
		<a href="<c:url value="/performances" />">All performances list</a><br /><br />
  </h1>
  <table>
  <c:forEach items="${AllTheatres}" var="th">
    <tr>
      <td><c:out value="${th.id}" /></td>
      <td><c:out value="${th.address}" /></td>
      <td><c:out value="${th.title}" /></td>
    </tr>
  </c:forEach>
  </table>
		<div class = bottom>All rights reserved<br /><br /><br /><br />&nbsp;</div>
</body>
</html>
