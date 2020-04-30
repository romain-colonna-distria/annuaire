<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/include/include.jsp"%>

<html>
<head>
	<title>Annuaire</title>
	<%@ include file="/WEB-INF/jsp/include/head-bootstrap.jsp"%>
</head>
<body>
<div class="container">
	<h1>Liste des personnes</h1>
	<table class="table table-hover">
		<c:forEach items="${persons}" var="person">
			<tr>
				<td><c:out value="${person.firstName}" /></td>
				<td><c:out value="${person.lastName}" /></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
