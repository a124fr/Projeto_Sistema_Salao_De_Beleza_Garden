<%-- 
    Document   : form_inserir_menu.jsp
    Created on : 20/12/2020, 21:58:17
    Author     : Francisco de Assis de Oliveira Silva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Menu</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Novo Menu</h1>
        <form name="novo_menu" method="POST" action="inserir_menu.do">
            Título:<input type="text" name="titulo" required /><br/>
            
            Link:<input type="text" name="link" required /><br/>
            
            Icone:<input type="text" name="icone" /><br/>
            
            <input type="submit" value="Salvar" /> | <a href="listar_menu.jsp">Voltar</a>
        </form>
    </body>
</html>
