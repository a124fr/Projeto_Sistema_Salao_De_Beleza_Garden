<%-- 
    Document   : form_alterar_cliente
    Created on : 21/01/2021, 15:48:50
    Author     : Francisco de Assis de Oliveira Silva
--%>

<%@page import="model.ClienteDAO"%>
<%@page import="model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Cliente c = new Cliente();
    
    try{
        int id = Integer.parseInt(request.getParameter("id"));
        ClienteDAO cDAO = new ClienteDAO();
        c = cDAO.carregarPorId(id);
    } catch(Exception e) {
        out.println("ERRO: " + e);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Cliente</title>
    </head>
    <body>
        <h1>Alterar Cliente</h1>
        <form name="alterar_cliente" method="POST" action="alterar_cliente.do">
            <input type="hidden" name="id" value="<%=c.getId() %>" />
            ID: <%=c.getId() %> <br/>
            
            Nome Completo:<input type="text" name="nome_completo" value="<%=c.getNomeCompleto() %>" required /><br/>
            
            Email:<input type="text" name="email" value="<%=c.getEmail() %>" required /><br/> 
            
            CPF: <input type="text" name="cpf" value="<%=c.getCpf() %>" required /><br/> 
            
            Telefone Contato: <input type="text" name="tel_contato" value="<%=c.getTelefoneContato() %>" required /><br/> 
            
            Telefone Whatsapp: <input type="text" name="tel_whatsapp" value="<%=c.getTelefoneWhatsapp() %>" required /><br/> 
            
            endereco: <textarea name="endereco"><%=c.getEndereco() %></textarea><br/>
            
            cep: <input type="text" name="cep" value="<%=c.getCep() %>" /><br/> 
            
            <input type="submit" value="Salvar" /> | <a href="listar_cliente.jsp">Voltar</a>
        </form>
    </body>
</html>
