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
        <a class="navbar-brand" aria-disabled="true" href="<c:url value = "/personne/liste"/>">Liste personnes</a>
        <c:if test="${user == null || !user.connected}">
            <form class="form-inline" method="post" action="<c:url value = "/utilisateur/connexion"/>">
                <input type="email" name="email" class="orm-control mr-sm-2" placeholder="exemple@exemple.com" aria-label="Search"/>
                <input type="password" name="password" class="orm-control mr-sm-2" placeholder="************" aria-label="Search"/>
                <button type="submit" class="btn btn-outline-success my-2 my-sm-0">Se connecter</button>
            </form>
        </c:if>
        <c:if test="${user != null && user.connected}">
            <a class="navbar-brand" href="<c:url value = "/utilisateur/deconnexion"/>">Se deconnecter</a>
        </c:if>
    </nav>

    <h1 class="title" align="center">Page personnel</h1>
    <form method="post" action="<c:url value = "/utilisateur/edit"/>">
        <div class="form-group">
            <label for="firstName">Prenom</label>
            <input type="text" class="form-control" name="firstName" id="firstName" placeholder="${user.person.firstName}">
        </div>
        <div class="form-group">
            <label for="lastName">Nom</label>
            <input type="text" class="form-control" name="lastName" id="lastName" placeholder="${user.person.lastName}">
        </div>
        <div class="form-group">
            <label for="email">Adresse mail</label>
            <input type="email" class="form-control" name="email" id="email" placeholder="${user.person.email}">
        </div>
        <div class="form-group">
            <label for="website">Site web (indiquez le protocole, exemple : https://www.monsite.fr)</label>
            <input type="text" class="form-control" name="website" id="website" placeholder="${user.person.website}">
        </div>
        <div class="form-group">
            <label for="birthday">Date de naissance</label>
            <input type="date" class="form-control" name="birthday" id="birthday"  value="${user.person.birthdayString}">
        </div>
        <div class="form-group">
            <label for="password">Mot de passe</label>
            <input type="password" class="form-control" name="password" id="password">
        </div>
        <button type="submit" class="btn btn-primary">Modifier</button>
    </form>
</div>
</body>
</html>
