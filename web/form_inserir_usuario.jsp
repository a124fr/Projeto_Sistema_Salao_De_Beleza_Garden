<%-- 
    Document   : form_inserir_usuario
    Created on : 17/12/2020, 18:59:11
    Author     : francisco
--%>

<%@page import="model.PerfilDAO"%>
<%@page import="model.Perfil"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Perfil> listaPerfis = new ArrayList<Perfil>();
    try {
        PerfilDAO pDAO = new PerfilDAO();
        listaPerfis = pDAO.listar();                
    } catch(Exception e) {
        out.print("ERRO: " + e);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Usuário</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Novo Usuário</h1>
        <form name="novo_usuario" method="POST" action="inserir_usuario.do">
            Nome:<input type="text" name="nome" required /><br/>
            
            Login:<input type="text" name="login" required /><br/> 
            
            Senha:<input type="password" name="senha" required /><br/>
            
            Perfil: <select name="id_perfil" required>
                        <option value=""> selecione </option>
                        <%
                            for(Perfil p:listaPerfis) {
                        %>
                            <option value="<%=p.getId() %>"> <%=p.getNome() %> </option>    
                        <%
                            }
                        %>                        
                    </select><br/>
            
            <input type="submit" value="Salvar" /> | <a href="listar_usuario.jsp">Voltar</a>
        </form>
    </body>
</html>
