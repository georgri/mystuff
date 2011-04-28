<%@include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
    <title><fmt:message key="title"/></title>
  </head>
  <body>
    <h1><fmt:message key="channel.heading"/> <c:out value="${wdate.date}"/> </h1>
    
    <form:form commandName="wdate" >
      <fmt:message key="main.label"/>&nbsp;
      <form:select path="date" items="${dates}"/>
      <fmt:message key="main.button" var="buttonLabel"/>
      <input type="submit" value="${buttonLabel}"/>
    </form:form>
    
    <p><a href="program.htm?sdate=${wdate.date}"><fmt:message key="channel.home"/></a></p>

    <form:form commandName="programs">
    <table cellpadding="2" border="1"> 
      <tr>
	    <th colspan="3"> 
	      <table cellpadding="1" width="100%">
	        <tr valign="middle">
	          <td align="left"><img src="${channel.logoFile}"></td>
	          <th><c:out value="${channel.title}"/></th>
	        </tr> 
	      </table>
	        
        </th>
      </tr> 
      
      <c:forEach items="${programs.indexer}" var="index">
      <tr>
        <td><form:input path="${programs.startShortTime[index]}"></form:input> <c:out value="${programs.items[index].startShortTime}"/>&nbsp;</td>
        <td><form:input path="${programs.endShortTime[index]}"></form:input><c:out value="${programs.items[index].endShortTime}"/>&nbsp;</td>
        <td><form:input path="${programs.items[index].title}"></form:input><c:out value="${programs.items[index].title}"/>&nbsp;</td>
      </tr> 
      </c:forEach>

    </table>
    </form:form>
    
  </body>
</html>