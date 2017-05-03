<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
<c:param name='title' value='Roos-Blauw'/>
</c:import>
<c:if test="${not empty kleur}">

<style>
body{
background-color:${kleur};
}
</style>
</c:if>
</head>
<body>
<vdab:menu />
<form method='post'>
<input type='submit' name='roosblauw' value='Roos'>
<input type='submit' name='roosblauw' value='Blauw'>
</form>

</body>
</html>