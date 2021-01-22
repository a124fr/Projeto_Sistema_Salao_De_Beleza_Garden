<%-- 
    Document   : form_inserir_procedimento
    Created on : 21/01/2021, 16:49:43
    Author     : Grupo 2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Procedimento</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Novo Procedimento</h1>
        <form name="inserir_procedimento" method="POST" action="inserir_procedimento.do">
            Título:<input type="text" name="titulo" required /><br/>
            
            Descrição:<textarea name="descricao" required></textarea><br/>
            
            Preço: <input type="text" name="valor" required /><br/>
            
            Valor Comissão: <input type="text" name="comissao" required /> % <br/>
                        
            <input type="submit" value="Salvar" /> | <a href="listar_procedimento.jsp">Voltar</a>
        </form>
    </body>
</html>
