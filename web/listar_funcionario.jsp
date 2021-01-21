<%-- 
    Document   : listar_usuario
    Created on : 17/12/2020, 22:17:18
    Author     : Grupo 2
--%>
<%@page import="model.FuncionarioDAO"%>
<%@page import="model.Funcionario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
    try {
        FuncionarioDAO fDAO = new FuncionarioDAO();
        lista = fDAO.listar();
    }catch(Exception e) {
        out.println("ERRO: " + e);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Funcionários</title>
        <script>
            function excluir(id,nome) {
                if( confirm('Deseja realmente excluir o Funcionario ' + nome + '?') ) {
                    window.open("excluir_funcionario.do?id="+id, "_self");
                }
            }
        </script>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Lista de Funcionários <a href="form_inserir_funcionario.jsp"><img src="assets/images/add.png" /></a> </h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Login</th>                    
                    <th>Situação</th>
                    <th>Perfil</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
            <%
                for(Funcionario f:lista) {
            %>
                <tr>
                    <td><%=f.getId() %></td>
                    <td><%=f.getNome() %></td>
                    <td><%=f.getLogin() %></td>
                    <td>
                        <%
                           if( f.getSituacao().equals("Ok") ) {
                        %>
                        <a href="gerenciar_situacao_funcionario.do?id=<%=f.getId() %>&op=0"><img src="assets/images/ok.png" /></a>
                        <%
                           } else {
                        %>
                            <a href="gerenciar_situacao_funcionario.do?id=<%=f.getId() %>&op=1"><img src="assets/images/nok.png" /></a>
                        <%       
                           }
                        
                        %>                    
                    </td>
                    <td><%=f.getPerfil().getNome() %></td>
                    <td>
                        <a href="form_alterar_funcionario.jsp?id=<%=f.getId() %>"><img src="assets/images/edit.png" /></a>
                        <a href="#" onclick="excluir(<%=f.getId() %>, '<%=f.getNome() %>');"><img src="assets/images/delete.png" /></a>
                    </td>
                </tr>
            <%
                }
            %>          
            </tbody>
        </table>
        
        <a href="index.jsp">Voltar</a>
    </body>
</html>
