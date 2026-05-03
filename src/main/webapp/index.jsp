<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Page d'accueil : redirige automatiquement vers le FrontController
    response.sendRedirect(request.getContextPath() + "/Controller?action=login");
%>
