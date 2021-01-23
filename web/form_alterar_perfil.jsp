<%-- 
    Document   : form_alterar_perfil.jsp
    Created on : 19/12/2020, 23:38:46
    Author     : Francisco de Assis de Oliveira Silva
--%>

<%@page import="model.PerfilDAO"%>
<%@page import="model.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Perfil p = new Perfil();
    try {
        int id = Integer.parseInt(request.getParameter("id"));
        PerfilDAO pDAO = new PerfilDAO();
        p = pDAO.carregarPorId(id);
        
    } catch(Exception e) {
        out.println("ERRO: " + e);
    }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Perfil</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Alterar Perfil</h1>
        <form name="alterar_perfil" method="POST" action="alterar_perfil.do">
            ID: <%=p.getId() %> <br/>
            <input type="hidden" name="id" value="<%=p.getId() %>"/>
            Nome:<input type="text" name="nome" value="<%=p.getNome() %>" required /><br/>
            
            Descrição:<textarea name="descricao" required><%=p.getDescricao() %></textarea><br/>
            
            <input type="submit" value="Salvar" /> | <a href="listar_perfil.jsp">Voltar</a>
        </form>
    </body>
</html>
