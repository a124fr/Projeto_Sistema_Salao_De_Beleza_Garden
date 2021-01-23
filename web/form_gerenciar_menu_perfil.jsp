<%-- 
    Document   : form_gerenciar_menu_perfil.jsp
    Created on : 19/12/2020, 23:38:46
    Author     : Francisco de Assis de Oliveira Silva
--%>

<%@page import="model.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.PerfilDAO"%>
<%@page import="model.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
    ArrayList<Menu> menusNaoVinculados = new ArrayList<Menu>();
    Perfil p = new Perfil();
    try {
        int id = Integer.parseInt(request.getParameter("id"));        
        PerfilDAO pDAO = new PerfilDAO();
        p = pDAO.carregarPorId(id);
        
        menusNaoVinculados = pDAO.carregarMenusNaoPerfil(id);        
    } catch(Exception e) {
        out.println("ERRO: " + e);
    }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciar Menu Perfil</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Gerenciar Menu Perfil</h1>
        <form name="menu_perfil" method="POST" action="gerenciar_menu_perfil.do">
            <strong>ID:</strong> <%=p.getId() %> 
            <input type="hidden" name="id" value="<%=p.getId() %>"/>
            <input type="hidden" name="op" value="add"/>
            <strong>Perfil:</strong> <%=p.getNome() %>             
            <br/>
            Menu: 
            <select name="id_menu" required>
                <option value=""> selecione </option>
                <%
                    for(Menu m:menusNaoVinculados) {
                %>
                    <option value="<%=m.getId() %>"> <%=m.getTitulo() %> </option>    
                <%
                    }
                %>                        
            </select>
            
            <input type="submit" value="+" />
        </form>
        
        <hr/>
        
        <h2>Menus Vinculados</h2>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>                    
                    <th>Título</th>                    
                    <th>Remover</th>
                </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Menu> menusVinculados = p.getMenus();
                for(Menu m:menusVinculados) {
            %>
                <tr>
                    <td><%=m.getId() %></td>
                    <td><%=m.getTitulo() %></td>
                    <td>
                        <a href="gerenciar_menu_perfil.do?id=<%=p.getId() %>&id_menu=<%=m.getId() %>&op=del"><img src="assets/images/delete.png" /></a>
                    </td>
                </tr>
            <%
                }
            %>          
            </tbody>
        </table>
        
        <a href="listar_perfil.jsp">Voltar</a>    
    </body>
</html>
