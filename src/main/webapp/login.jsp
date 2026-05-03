<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>

<% String erreur = (String) request.getAttribute("erreur");
    if(erreur != null) { %>
    <p style="color:red;"><%= erreur %></p>
<% } %>

<form action="login" method="post">
    Login: <input type="text" name="login" required /><br/>
    Mot de passe: <input type="password" name="password" required /><br/>
    <input type="submit" value="Se connecter" />
</form>
</body>
</html>
