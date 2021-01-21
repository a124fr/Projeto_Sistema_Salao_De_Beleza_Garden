<%-- 
    Document   : form_alterar_usuario
    Created on : 17/12/2020, 18:59:11
    Author     : Grupo 2
--%>

<%@page import="model.FuncionarioDAO"%>
<%@page import="model.Funcionario"%>
<%@page import="model.PerfilDAO"%>
<%@page import="model.Perfil"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Perfil> listaPerfis = new ArrayList<Perfil>();
    Funcionario f = new Funcionario();
    
    try {
        PerfilDAO pDAO = new PerfilDAO();
        listaPerfis = pDAO.listar();                
        
        int id = Integer.parseInt(request.getParameter("id"));
        FuncionarioDAO fDAO = new FuncionarioDAO();
        f = fDAO.carregarPorId(id);
    } catch(Exception e) {
        out.print("ERRO: " + e);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Usuário</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Alterar Usuário</h1>
        <form name="alterar_usuario" method="POST" action="alterar_funcionario.do">
            <input type="hidden" name="id" value="<%=f.getId() %>" />
            ID: <%=f.getId() %> <br/>
            Nome:<input type="text" name="nome" value="<%=f.getNome() %>" required /><br/>
            
            Login:<input type="text" name="login" value="<%=f.getLogin() %>" required /><br/> 
            
            Senha:<input type="password" name="senha" value="<%=f.getSenha() %>" required /><br/>
            
            Perfil: <select name="id_perfil" required>
                        <option value=""> selecione </option>
                        <%
                            for(Perfil p:listaPerfis) {
                                String selecao = "";
                                if(f.getPerfil().getId() == p.getId()) {
                                    selecao = "selected";
                                }
                        %>
                            <option value="<%=p.getId() %>" <%=selecao %>> <%=p.getNome() %> </option>    
                        <%
                            }
                        %>                        
                    </select><br/>
            
            <input type="submit" value="Salvar" /> | <a href="listar_funcionario.jsp">Voltar</a>
        </form>
    </body>
</html>
