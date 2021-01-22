<%-- 
    Document   : listar_agenda
    Created on : 22/01/2021, 00:59:05
    Author     : Grupo 2
--%>

<%@page import="java.time.OffsetDateTime"%>
<%@page import="java.time.ZoneOffset"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="model.AgendaDAO"%>
<%@page import="model.Agenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
    ArrayList<Agenda> lista = new ArrayList<Agenda>();        
    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      
    try{
        AgendaDAO aDAO = new AgendaDAO();
        lista = aDAO.listar();    
    } catch(Exception e) {
        out.println("ERRO: "  + e);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Agenda</title>
        <script>
            function atender(id) {
                if( confirm('Deseja realizar o atendimento da Agenda - ID: ' + id + '?') ) {
                    window.open("atender_agenda.do?id="+id, "_self");
                }
            }
                        
            function cancelar(id) {
                if( confirm('Deseja realizar o cancelamento da Agenda - ID: ' + id + '?') ) {
                    window.open("cancelar_agenda.do?id="+id, "_self");
                }
            }
        </script>        
    </head>
    <body>        
        <%@include  file="banner.jsp" %>
        <h1>Lista de Agenda</h1>
        
        <a href="form_consultar_agenda.jsp?cpf=">Consultar
            <!-- img src="assets/images/delete.png" / -->
        </a> |
        
        
        | <button>Agendar</button> <br/>
                                        
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
            <% if(!lista.isEmpty()) { %>
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
                    <td align="center">
                       
                        <% if(a.getStatus().equals("Marcado")) { %> 
                            <a href="#" onclick="atender(<%=a.getId() %>);">Atender
                                <!-- img src="assets/images/delete.png" / -->
                            </a> |                       
                            <a href="#" onclick="cancelar(<%=a.getId() %>);">Cancelar
                                <!-- img src="assets/images/delete.png" / -->
                            </a> |
                            <a href="form_registrar_pagamento.jsp?id=<%=a.getId() %>">Pagar
                                <!-- img src="assets/images/delete.png" / -->
                            </a>
                       
                       <% }else if(a.getStatus().equals("EmAtendimento")) {%>
                            <a href="form_registrar_pagamento.jsp?id=<%=a.getId() %>">Pagar
                                <!-- img src="assets/images/delete.png" / -->
                            </a>                                
                       <% } else { %>                                               
                            <span> - </span>
                       <% } %>
                       </td>
                </tr>
                <% } %>
            </tbody>
            <% }else{%>
                <tr>
                    <td colspan="6">Nenhuma agenda cadastrada!</td>
                </tr>
            <% } %>
        </table>
    </body>
</html>
