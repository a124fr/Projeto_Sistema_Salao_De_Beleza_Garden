<%-- 
    Document   : listar_menu
    Created on : 17/12/2020, 22:17:18
    Author     : francisco
--%>
<%@page import="model.MenuDAO"%>
<%@page import="model.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Menu> lista = new ArrayList<Menu>();
    try {
        MenuDAO mDAO = new MenuDAO();
        lista = mDAO.listar();
    }catch(Exception e) {
        out.println("ERRO: " + e);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Menu</title>
        <script>
            function excluir(id,titulo) {
                if( confirm('Deseja realmente excluir o Menu ' + titulo + '?') ) {
                    window.open("excluir_menu.do?id="+id, "_self");
                }
            }
        </script>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Lista de Menu <a href="form_inserir_menu.jsp"><img src="assets/images/add.png" /></a> </h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>                    
                    <th>Título</th>
                    <th>Link</th>
                    <th>Icone</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
            <%
                for(Menu m:lista) {
            %>
                <tr>
                    <td><%=m.getId() %></td>
                    <td><%=m.getTitulo() %></td>
                    <td><%=m.getLink() %></td>
                    <td><%=m.getIcone() %></td>
                    <td>
                        <a href="form_alterar_menu.jsp?id=<%=m.getId() %>"><img src="assets/images/edit.png" /></a>
                        <a href="#" onclick="excluir(<%=m.getId() %>, '<%=m.getTitulo() %>');"><img src="assets/images/delete.png" /></a>
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
