<%-- 
    Document   : form_alterar_menu.jsp
    Created on : 20/12/2020, 21:58:17
    Author     : Francisco de Assis de Oliveira Silva
--%>

<%@page import="model.MenuDAO"%>
<%@page import="model.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Menu m = new Menu();
    try {
        int id = Integer.parseInt(request.getParameter("id"));
        MenuDAO mDAO = new MenuDAO();
        m = mDAO.carregarPorId(id);
        
    } catch(Exception e) {
        out.println("ERRO: " + e);
    }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Menu</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Alterar Menu</h1>
        <form name="novo_menu" method="POST" action="alterar_menu.do">
            <input type="hidden" name="id" value="<%=m.getId() %>"/>
            
            ID:  <%=m.getId() %><br/>
            
            Título:<input type="text" name="titulo"  value="<%=m.getTitulo() %>" required /><br/>
            
            Link:<input type="text" name="link"  value="<%=m.getLink() %>" required /><br/>
            
            Icone:<input type="text" name="icone"  value="<%=m.getIcone() %>" /><br/>
            
            <input type="submit" value="Salvar" /> | <a href="listar_menu.jsp">Voltar</a>
        </form>
    </body>
</html>
