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
            <c:if test="${user == null || !user.connected}">
                <div>
                    <form class="form-inline" method="post" action="<c:url value = "/utilisateur/connexion"/>">
                        <div class="form-group mb-2">
                            <input type="email" name="email" class="form-control" placeholder="exemple@exemple.com" />
                        </div>
                        <div class="form-group mb-2">
                            <input type="password" name="password" class="form-control" placeholder="************" />
                        </div>
                        <button type="submit" class="btn btn-primary mb-2">Se connecter</button>
                    </form>
                    <c:if test="${user != null && user.badPassword}">
                        <div class="alert alert-danger" role="alert">
                            Email ou mot de passe incorrect.
                        </div>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${user != null && user.connected}">
                <div class="alignLeft">
                    <form method="post" action="<c:url value = "/utilisateur/profile"/>">
                        <button type="submit" class="btn btn-primary mx-sm-3 mb-2">Mon profile</button>
                    </form>
                </div>
                <div>
                    <form method="post" action="<c:url value = "/utilisateur/deconnexion"/>">
                        <button type="submit" class="btn btn-primary mx-sm-3 mb-2">Se deconnecter</button>
                    </form>
                </div>
            </c:if>
            <div class="alignRight">
                <form method="post" action="<c:url value = "/personne/liste"/>">
                    <button type="submit" class="btn btn-primary mx-sm-3 mb-2">Liste des personnes</button>
                </form>
            </div>

            <h1 class="title" align="center">Liste des groupes</h1>

            <form class="form-inline" method="post" action="<c:url value = "/groupe/liste"/>">
                <div class="form-group mb-2">
                    <input type="text" name="contain" class="form-control" placeholder="M1 ILD 2018/2019" />
                </div>
                <button type="submit" class="btn btn-primary mx-sm-3 mb-2">Rechercher</button>
            </form>
            <table class="table table-striped">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nom</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${groups}" var="group">
                    <tr>
                        <td><a href="<c:url value = "/groupe/${group.id}"/>"><c:out value="${group.id}" /></a></td>
                        <td><a href="<c:url value = "/groupe/${group.id}"/>"><c:out value="${group.name}" /></a></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
