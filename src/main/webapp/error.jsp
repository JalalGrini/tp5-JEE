<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Erreur</title>
</head>
<body>
<h2>Une erreur est survenue</h2>
<p style="color:red;"><%= exception.getMessage() %></p>
<a href="listProduits">Retour à la liste</a>
</body>
</html>
