<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
<c:param name='title' value='Saus raden'/>
</c:import>
</head>
<body>
<vdab:menu />
<p>Te raden saus: <c:forEach var="letter" items="${letters}">${letter}</c:forEach></p>
<form action="sausraden.htm" method="post">



<c:choose>
<c:when test="${gewonnen}">Proficiat, u hebt de saus geraden met ${pogingen} verkeerde poging<c:if test="${pogingen !=1 }">en</c:if>!</c:when>
<c:otherwise>
<br>
<c:if  test='${pogingen < 10}'>
<label for="letter">Letter:<span>${fout}</span><input type="text" name="letter" autocomplete="off" size="1" /></label>
<button type="submit" name="knop" value ="raden">Raden</button><br></c:if>

<c:if  test='${pogingen > 0 && pogingen < 10}'>

<img alt="galgStadium${pogingen}" src="images/${pogingen}.png" />
</c:if>
<c:if test="${pogingen ==10}"><img alt="galgStadium${pogingen}" src="images/${pogingen}.png" /><p>U bent verloren. De saus was ${saus}.</p></c:if>
</c:otherwise>
</c:choose>
<br>
<button type="submit" name="knop" value ="nieuwspel">Nieuw spel</button>
</form>
</body>
</html>