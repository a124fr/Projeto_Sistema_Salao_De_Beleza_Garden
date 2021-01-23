<%-- 
    Document   : form_consultar_agenda
    Created on : 22/01/2021, 16:15:58
    Author     : Francisco de Assis de Oliveira Silva
--%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="model.AgendaDAO"%>
<%@page import="model.Agenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Agenda> lista = new ArrayList<Agenda>();    
    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    String cpf = request.getParameter("cpf");    
    
    if(!cpf.isEmpty()) {
        AgendaDAO aDAO = new AgendaDAO();
        lista = aDAO.consultarAgendaPorCpfCliente(cpf);
    }
         
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de Agenda</title>
    </head>
    <body>
        <%@include  file="banner.jsp" %>
        <h1>Consulta de Agenda</h1>
        
        <form method="GET" action="form_consultar_agenda.jsp">
            CPF: <input type="text" name="cpf" requerid /><br/>
            <input type="submit" value="Enviar" />                    
        </form>
        
        <% if(!lista.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Data Agendamento</th>
                    <th>Data Atendimento</th>
                    <!-- th>Data Pagamento</th -->
                    <!-- th>Data Cancelamento</th -->                    
                    <th>Funcionário</th>
                    <th>Cliente</th>
                    <th>Procedimento</th>
                    <th>Status</th>                    
                    <th>Opções</th>
                </tr>
            </thead>            
            <tbody>
                <% for(Agenda a:lista) {%>
                <tr>
                    <td><%=a.getId() %></td>
                    <td><%
                        Date date = inputFormat.parse( a.getDataAgendamento() );
                        out.print( formatador.format(date) );                        
                    %></td>
                    <td><%
                        date = inputFormat.parse( a.getDataAtendimento() );
                        out.print( formatador.format(date) );                        
                    %></td>
                    
                    <td><%=a.getFuncionario().getNomeCompleto() %></td>
                    <td><%=a.getCliente().getNomeCompleto() %></td>
                    <td><%=a.getProcedimento().getTitulo() %></td>
                    <td><%=a.getStatus() %></td>                    
                </tr>
                <% } %>
            </tbody>
        <% } %>
        
    </body>
</html>
