<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Produits</title>
</head>
<body>
<h2>Liste des Produits</h2>
<p>Bienvenue, ${user.login} | <a href="logout">Déconnexion</a></p>

<form action="listProduits" method="get">
    ID: <input type="text" name="idProduit" />
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
