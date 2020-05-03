<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/utils/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <meta charset="UTF-8"/>
    <title>Groupes</title>
    <%@ include file="/WEB-INF/jsp/utils/head-bootstrap.jsp"%>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-light bg-light justify-content-between">
        <a class="navbar-brand" href="<c:url value = "/groupe/liste"/>">Liste groupes</a>
        <a class="navbar-brand" aria-disabled="true" href="#">Liste personnes</a>
        <c:if test="${user == null || !user.connected}">
            <form class="form-inline" method="post" action="<c:url value = "/utilisateur/connexion"/>">
                <input type="email" name="email" class="orm-control mr-sm-2" placeholder="exemple@exemple.com" aria-label="Search"/>
                <input type="password" name="password" class="orm-control mr-sm-2" placeholder="************" aria-label="Search"/>
                <button type="submit" class="btn btn-outline-success my-2 my-sm-0">Se connecter</button>
            </form>
        </c:if>
        <c:if test="${user != null && user.connected}">
            <a class="navbar-brand" href="<c:url value = "/utilisateur/profile"/>">Mon profile</a>
            <a class="navbar-brand" href="<c:url value = "/utilisateur/deconnexion"/>">Se deconnecter</a>
        </c:if>
    </nav>
    <c:if test="${user != null && user.badPassword}">
        <div class="alert alert-danger" role="alert">
            Email ou mot de passe incorrect.
        </div>
    </c:if>

    <h1 class="title" align="center">Liste des personnes</h1>
    <form class="form-inline" method="get" action="<c:url value = "/personne/liste"/>">
        <div class="form-group mb-2">
            <input type="text" name="prenom" class="form-control" placeholder="Prenom" />
            <input type="text" name="nom" class="form-control" placeholder="Nom" />
        </div>
        <button type="submit" class="btn btn-primary mx-sm-3 mb-2">Rechercher</button>
    </form>
    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Nom</th>
            <th scope="col">Prenom</th>
            <th scope="col">Site web</th>
            <c:if test="${user != null && user.connected}">
                <th scope="col">Email</th>
                <th scope="col">Date de naissance</th>
            </c:if>
            <th scope="col">Groupe</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${persons}" var="person">
            <tr>
                <td><c:out value="${person.id}" /></td>
                <td><c:out value="${person.firstName}" /></td>
                <td><c:out value="${person.lastName}" /></td>
                <td><c:out value="${person.website}" /></td>
                <c:if test="${user != null && user.connected}">
                    <td><c:out value="${person.email}" /></td>
                    <td><c:out value="${person.birthday}" /></td>
                </c:if>
                <td><a href="<c:url value = "/groupe/${person.classGroup.id}"/>"><c:out value="${person.classGroup.name}" /></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
