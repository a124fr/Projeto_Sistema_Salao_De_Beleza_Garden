<%-- 
    Document   : sair
    Created on : 22/12/2020, 00:25:26
    Author     : Grupo 2
--%>

<%
    session.removeAttribute("funcionario_logado");
    response.sendRedirect("login.jsp");
%>