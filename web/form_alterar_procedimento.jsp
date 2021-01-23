<%-- 
    Document   : form_alterar_procedimento
    Created on : 21/01/2021, 20:23:01
    Author     : Francisco de Assis de Oliveira Silva
--%>

<%@page import="model.ProcedimentoDAO"%>
<%@page import="model.Procedimento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Procedimento p = new Procedimento();
    try{
        int id = Integer.parseInt(request.getParameter("id"));
        ProcedimentoDAO pDAO = new ProcedimentoDAO();
        p = pDAO.carregarPorId(id);
    } catch(Exception e) {
        out.println("ERRO: " + e);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Procedimento</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Alterar Procedimento</h1>
        <form name="alterar_procedimento" method="POST" action="alterar_procedimento.do">
            ID: <%=p.getId() %> <br/>
            <input type="hidden" name="id" value="<%=p.getId() %>"/>
            
            Título:<input type="text" name="titulo" value="<%=p.getTitulo() %>" required /><br/>
            
            Descrição:<textarea name="descricao" required><%=p.getDescricao() %></textarea><br/>
            
            Preço: <input type="text" name="valor" value="<%=p.getValor() %>" required /><br/>
            
            Valor Comissão: <input type="text" name="comissao" value="<%=p.getComissaoPorcentual() %>" required /> % <br/>
                        
            <input type="submit" value="Salvar" /> | <a href="listar_procedimento.jsp">Voltar</a>
        </form>        
    </body>
</html>
