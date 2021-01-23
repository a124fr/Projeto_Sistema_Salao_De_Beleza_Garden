<%-- 
    Document   : istar_procedimento
    Created on : 21/01/2021, 16:48:33
    Author     : Francisco de Assis de Oliveira Silva
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.ProcedimentoDAO"%>
<%@page import="model.Procedimento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Procedimento> lista = new ArrayList<Procedimento>();    
    try {
        ProcedimentoDAO pDAO = new ProcedimentoDAO();
        lista = pDAO.listar();        
    } catch(Exception e) {
        out.println("ERRO: " + e);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Procedimentos</title>
        <script>
            function excluir(id,titulo) {
                if( confirm('Deseja realmente excluir o procedimento ' + titulo + '?') ) {
                    window.open("excluir_procedimento.do?id="+id, "_self");
                }
            }
        </script>        
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Lista de Procedimentos <a href="form_inserir_procedimento.jsp"><img src="assets/images/add.png" /></a> </h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Descricão</th>
                    <th>Preço</th>
                    <th>Comissão</th>
                    <th>Opções</th>
                </tr>                
            </thead>            
            <tbody>
            <% if(!lista.isEmpty()){ %>
                <% for(Procedimento p:lista){ %>
                <tr>
                    <td><%=p.getId() %></td>
                    <td><%=p.getTitulo() %></td>
                    <td><%=p.getDescricao() %></td>
                    <td><%=p.getValor() %></td>
                    <td><%=p.getComissaoPorcentual() %>%</td>
                    <td>
                        <a href="form_alterar_procedimento.jsp?id=<%=p.getId() %>"><img src="assets/images/edit.png" /></a>
                        <a href="#" onclick="excluir(<%=p.getId() %>, '<%=p.getTitulo() %>');"><img src="assets/images/delete.png" /></a>
                    </td>
                </tr>
                <% } %>
            <% }else{ %>
                <tr>
                    <td colspan="6">Nenhum procedimento cadastrado!</td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </body>
</html>
