<%-- 
    Document   : form_registrar_pagamento
    Created on : 22/01/2021, 14:20:19
    Author     : Grupo 2
--%>

<%@page import="model.AgendaDAO"%>
<%@page import="model.Agenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Agenda a = new Agenda();
    try{
        int id = Integer.parseInt(request.getParameter("id"));        
        AgendaDAO aDAO = new AgendaDAO();
        a = aDAO.carregarPorId(id);        
    }catch(Exception e) {
        out.println("ERRO: " + e);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registo de Pagamento</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Registo de Pagamento</h1>
        <form name="registrar_pagamento" method="POST" action="registrar_pagamento.do">
            <input type="hidden" name="id" value="<%=a.getId() %>" />
            ID Agenda: <%=a.getId() %> <br/>
            
            Data de Agendamento: <%=a.getDataAgendamento() %><br/>
            
            Data de Atendimento:  <%=a.getDataAtendimento() %><br/>
            
            Funcionário: <%=a.getFuncionario().getNomeCompleto() %><br/>
            
            Procedimento: <%=a.getProcedimento().getTitulo() %> <br/>
            
            Cliente: <%=a.getCliente().getNomeCompleto() %> <br/>
            
            Status: <%=a.getStatus() %> <br/>
            
            Desconto: <input type="number" name="desconto" /> <br/>
                        
            Valor Total: <input type="number" name="valor_total" value="<%=a.getProcedimento().getValor() %>" ><br/>
            
            <input type="submit" value="Salvar" /> | <a href="listar_agenda.jsp">Voltar</a>            
        </form>
    </body>
</html>
