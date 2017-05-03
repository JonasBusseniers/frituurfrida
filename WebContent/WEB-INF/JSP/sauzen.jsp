<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
<c:param name='title' value='Sauzen'/>
</c:import>
</head>
<body>
<vdab:menu />
<form method="post" id="verwijderForm" action="<c:url value='/sausverwijderen.htm' />">
<ul class="zebra">
<c:forEach var="saus" items="${sauzen}">
<li><input type="checkbox" name="nummer" value="${saus.nummer}"/>${saus.naam} - Ingrediënten: 
<c:forEach var="ingredient" items="${saus.ingredienten}" varStatus="status">${ingredient}<c:if test="${!status.last}">,</c:if>

</c:forEach>
</li>
</c:forEach>
</ul>
<input type="submit" value="Verwijderen" id="verwijderKnop" /><span>${foutVerwijder}</span>
</form>
<form>
<label for="ingredient">Ingrediënt *<span>${fout}</span></label><input name="ingredient" type="text" >
<input type="submit" value="Zoeken">
</form>
</body>
</html>