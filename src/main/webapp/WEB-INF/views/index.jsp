<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Gestion des Produits (Admin)</title>
</head>
<body>
<h2>Gestion des Produits</h2>
<p>
    Bienvenue, ${sessionScope.user.login} |
    <a href="${pageContext.request.contextPath}/Controller?action=logout">Déconnexion</a>
</p>

<form action="${pageContext.request.contextPath}/Controller?action=${not empty produitEdit ? 'updateProduit' : 'addProduit'}"
      method="post">
    <input type="hidden" name="idProduit" value="${produitEdit.idProduit}" />
    Nom :         <input type="text" name="nom"         value="${produitEdit.nom}"         required /><br/>
    Description : <input type="text" name="description" value="${produitEdit.description}" required /><br/>
    Prix :        <input type="text" name="prix"        value="${produitEdit.prix}"        required /><br/>
    <input type="submit" value="${not empty produitEdit ? 'Modifier' : 'Ajouter'}" />
    <c:if test="${not empty produitEdit}">
        <a href="${pageContext.request.contextPath}/Controller?action=listProduits">Annuler</a>
    </c:if>
</form>

<hr/>

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
        <th>Actions</th>
    </tr>
    <c:forEach var="p" items="${listeProduits}">
        <tr>
            <td>${p.idProduit}</td>
            <td>${p.nom}</td>
            <td>${p.description}</td>
            <td>${p.prix}</td>
            <td>
                <a href="${pageContext.request.contextPath}/Controller?action=editProduit&id=${p.idProduit}">Modifier</a> |
                <a href="${pageContext.request.contextPath}/Controller?action=deleteProduit&id=${p.idProduit}"
                   onclick="return confirm('Voulez-vous vraiment supprimer ce produit ?');">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
