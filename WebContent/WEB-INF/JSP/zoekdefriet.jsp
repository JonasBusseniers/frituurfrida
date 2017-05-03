<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
<c:param name='title' value='Zoek de friet'/>
</c:import>
</head>
<body>
<vdab:menu />
<form method="post" id="zoekdefrietform">
<c:forEach var='entry' items='${deuren}'>
<button type='submit' name='volgnummer' value='${entry.key}'>

<img src='<c:url value="/images/${entry.value}.png"/>' alt='${entry.value}'>
</button>
</c:forEach>
<br>
<button type="submit" name="nieuwspel" value="ja">Nieuw spel</button>
</form>

</body>
</html>