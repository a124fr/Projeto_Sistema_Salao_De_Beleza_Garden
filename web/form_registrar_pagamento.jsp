<%-- 
    Document   : form_registrar_pagamento
    Created on : 22/01/2021, 14:20:19
    Author     : Grupo 2
--%>

<%@page import="model.AgendaDAO"%>
<%@page import="model.Agenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Agenda a = new Agenda();
    try{
        int id = Integer.parseInt(request.getParameter("id"));        
        AgendaDAO aDAO = new AgendaDAO();
        a = aDAO.carregarPorId(id);        
    }catch(Exception e) {
        out.println("ERRO: " + e);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registo de Pagamento</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Registo de Pagamento</h1>
        <form name="registrar_pagamento" method="POST" action="registrar_pagamento.do">
            <input type="hidden" name="id" value="<%=a.getId() %>" />
            ID: <%=a.getId() %> <br/>
            
            
            
        </form>
    </body>
</html>
