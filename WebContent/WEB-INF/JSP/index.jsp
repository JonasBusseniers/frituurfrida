<%@page import="java.time.LocalDateTime"%>
<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<fmt:setBundle basename='resourceBundels.teksten'/> 
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
<c:param name='title' value='Frituur Frida'/>
</c:import>
</head>
<body>
<vdab:menu />
<c:choose>
<c:when test="${isOpen}">
<h1><fmt:message key='openZin' /></h1>
<img alt="<fmt:message key='openAfbeelding' />" src="images/<fmt:message key='openAfbeelding' />">
</c:when>
<c:otherwise>
<h1><fmt:message key='geslotenZin' /></h1>
<img alt="<fmt:message key='geslotenAfbeelding' />" src="images/<fmt:message key='geslotenAfbeelding' />">
</c:otherwise>
</c:choose>
<h2><fmt:message key='adres'/></h2>
<p>${adres.straat} ${adres.huisNr}<br>
${adres.gemeente.postcode} ${adres.gemeente.naam}</p>
<fmt:message key='tel'/>: ${initParam.telefoonHelpdesk}
<br>
<c:forEach var="url" items="${aantalRequests}">
<br>
${url.key} : ${url.value}
</c:forEach>
</body>
</html>