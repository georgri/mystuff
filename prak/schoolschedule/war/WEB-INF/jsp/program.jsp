<%@include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
    <title>
      <fmt:message key="title"/>
    </title>
  </head>
  <body>
    <h1><fmt:message key="main.heading"/> <c:out value="${wdate.date}"/> </h1>
    
    <form:form commandName="wdate">
      <fmt:message key="main.label"/>&nbsp;
      <form:select path="date" items="${dates}"/>
      <fmt:message key="main.button" var="buttonLabel"/>
      <input type="submit" value="${buttonLabel}"/>
    </form:form>
    
    <table cellpadding="2" border="1"> 
      <tr>
        <c:forEach items="${channels}" var="channel">
	      <th colspan="2"> 
	      
	        <table cellpadding="1" width="100%">
	          <tr valign="middle">
	            <td align="left"><img src="${channel.logoFile}"></td>
                <th><c:out value="${channel.title}"/></th>
	            <td align="right"><a href="channel.htm?channelId=${channel.id}&amp;sdate=${wdate.date}"><fmt:message key="main.edit"/></a></td>
	          </tr> 
	        </table>
	        
	      </th>
        </c:forEach>
      </tr> 
      
      <c:forEach items="${indexer}" var="rownum">
      <tr>
        <c:forEach items="${channels}" var="channel">
	      <td><c:out value="${programs[channel][rownum].interval}"/>&nbsp;</td>
	      <td><c:out value="${programs[channel][rownum].title}"/>&nbsp;</td>
        </c:forEach>
      </tr> 
      </c:forEach>

    </table>
  </body>
</html>