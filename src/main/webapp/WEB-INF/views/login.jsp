<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login - tpnewmvc2</title>
</head>
<body>
<h2>Connexion</h2>

<c:if test="${not empty erreur}">
    <p style="color:red;">${erreur}</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/Controller?action=login">
    Login: <input type="text" name="login" required /><br/>
    Mot de passe: <input type="password" name="password" required /><br/>
    <input type="submit" value="Se connecter" />
</form>

</body>
</html>
