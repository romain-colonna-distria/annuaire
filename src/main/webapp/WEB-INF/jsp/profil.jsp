<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/utils/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="css" value="/style.css" />

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Groupes</title>
    <link rel="stylesheet" href="${css}">
    <%@ include file="/WEB-INF/jsp/utils/head-bootstrap.jsp"%>
</head>
<body>
<div class="container">
    <form method="post" action="<c:url value = "/groupe/liste"/>">
        <button type="submit" class="btn btn-primary mx-sm-3 mb-2">Retour</button>
    </form>

    <h1 class="title" align="center">Page personnel</h1>
    <form method="post" action="<c:url value = "/utilisateur/edit"/>">
        <div class="form-group">
            <label for="firstName">Prenom</label>
            <input type="text" class="form-control" name="firstName" id="firstName" placeholder="${person.firstName}">
        </div>
        <div class="form-group">
            <label for="lastName">Nom</label>
            <input type="text" class="form-control" name="lastName" id="lastName" placeholder="${person.lastName}">
        </div>
        <div class="form-group">
            <label for="email">Adresse mail</label>
            <input type="email" class="form-control" name="email" id="email" placeholder="${person.email}">
        </div>
        <div class="form-group">
            <label for="website">Site web</label>
            <input type="url" class="form-control" name="website" id="website" placeholder="${person.website}">
        </div>
        <div class="form-group">
            <label for="birthday">Date de naissance</label>
            <input type="date" class="form-control" name="birthday" id="birthday" placeholder="${person.birthday}">
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
