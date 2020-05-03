<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/utils/include.jsp"%>

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
                <button type="submit" class="btn btn-primary mx-sm-3 mb-2">Retour liste des groupes</button>
            </form>

            <h1 align="center" class="title">Details du groupe "${group.name}"</h1>
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