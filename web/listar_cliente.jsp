<%-- 
    Document   : listar_cliente
    Created on : 21/01/2021, 10:15:03
    Author     : Grupo 2
--%>

<%@page import="model.ClienteDAO"%>
<%@page import="model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Cliente> lista = new ArrayList<Cliente>();
    try{
        ClienteDAO cDAO = new ClienteDAO();
        lista = cDAO.listar();
    }catch(Exception e) {
        out.println("ERRO: " + e);
    }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Clientes</title>
        <script>
            function excluir(id,nome) {
                if( confirm('Deseja realmente excluir o cliente ' + nome + '?') ) {
                    window.open("excluir_cliente.do?id="+id, "_self");
                }
            }
        </script>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Lista de Clientes <a href="form_inserir_cliente.jsp"><img src="assets/images/add.png" /></a> </h1>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>CPF</th>
                    <th>Nome</th>                    
                    <th>Email</th>
                    <th>Telefone Contato</th>
                    <th>Opções</th>
                </tr>                
            </thead>
            <tbody>
            <% if(!lista.isEmpty()){%>
                <%for(Cliente c:lista){%>
                <tr>
                    <td><%=c.getId() %></td>
                    <td><%=c.getCpf() %></td>
                    <td><%=c.getNomeCompleto() %></td>
                    <td><%=c.getEmail() %></td>
                    <td><%=c.getTelefoneContato() %></td>
                    <td>
                        <a href="form_alterar_cliente.jsp?id=<%=c.getId() %>"><img src="assets/images/edit.png" /></a>
                        <a href="#" onclick="excluir(<%=c.getId() %>, '<%=c.getNome() %>');"><img src="assets/images/delete.png" /></a>
                    </td>
                </tr>    
                <%}%>
            <%}else{%>
                <tr>
                    <td colspan="6">Nenhum cliente cadastrado!</td>
                </tr>
            <%}%>
            </tbody>
        </table>
        
    </body>
</html>
