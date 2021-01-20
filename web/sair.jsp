<%-- 
    Document   : sair
    Created on : 22/12/2020, 00:25:26
    Author     : francisco
--%>

<%
    session.removeAttribute("usuario");
    response.sendRedirect("login.jsp");
%>