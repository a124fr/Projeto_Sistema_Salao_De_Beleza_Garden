<%-- 
    Document   : form_inserir_cliente
    Created on : 21/01/2021, 10:49:07
    Author     : Grupo 2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Cliente</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Novo Cliente </h1>
        <form name="novo_cliente" method="POST" action="inserir_cliente.do">
            Nome Completo:<input type="text" name="nome_completo" required /><br/>
            
            Email:<input type="text" name="email" required /><br/> 
            
            CPF: <input type="text" name="cpf" required /><br/> 
            
            Telefone Contato: <input type="text" name="tel_contato" required /><br/> 
            
            Telefone Whatsapp: <input type="text" name="tel_whatsapp" required /><br/> 
            
            endereco: <textarea name="endereco"></textarea><br/>
            
            cep: <input type="text" name="cep" /><br/> 
            
            <input type="submit" value="Salvar" /> | <a href="listar_cliente.jsp">Voltar</a>
        </form>
    </body>
</html>
