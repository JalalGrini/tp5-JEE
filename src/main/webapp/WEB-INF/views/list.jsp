<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Produits</title>
</head>
<body>
<h2>Liste des Produits</h2>
<p>
    Bienvenue, ${sessionScope.user.login} |
    <a href="${pageContext.request.contextPath}/Controller?action=logout">Déconnexion</a>
</p>

<form action="${pageContext.request.contextPath}/Controller" method="get">
    <input type="hidden" name="action" value="listProduits" />
    ID : <input type="text" name="idProduit" />
    <input type="submit" value="Rechercher" />
</form>

<hr/>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Description</th>
        <th>Prix</th>
    </tr>
    <c:forEach var="p" items="${listeProduits}">
        <tr>
            <td>${p.idProduit}</td>
            <td>${p.nom}</td>
            <td>${p.description}</td>
            <td>${p.prix}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
