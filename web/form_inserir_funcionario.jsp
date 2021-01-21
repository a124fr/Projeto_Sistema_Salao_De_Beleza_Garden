<%-- 
    Document   : form_inserir_funcionario
    Created on : 17/12/2020, 18:59:11
    Author     : Grupo 2
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
        <title>Novo Funcionário</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Novo Funcionário</h1>
        <form name="novo_funcionario" method="POST" action="inserir_funcionario.do">
            Nome Completo:<input type="text" name="nome" required /><br/>
            
            Email:<input type="text" name="email" required /><br/> 
            
            CPF: <input type="text" name="cpf" required /><br/> 
            
            Telefone Contato: <input type="text" name="tel_contato" required /><br/> 
            
            Telefone Whatsapp: <input type="text" name="tel_whatsapp" required /><br/> 
                                    
            Senha:<input type="password" name="senha" required /><br/>
            
            endereco: <textarea name="endereco"></textarea><br/>
            
            cep: <input type="text" name="cep" /><br/> 
            
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
            
            <input type="submit" value="Salvar" /> | <a href="listar_funcionario.jsp">Voltar</a>
        </form>
    </body>
</html>
