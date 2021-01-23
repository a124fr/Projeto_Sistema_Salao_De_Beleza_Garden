<%-- 
    Document   : banner
    Created on : 21/12/2020, 23:58:10
    Author     : Francisco de Assis de Oliveira Silva
--%>

<%@page import="model.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Funcionario"%>
<%
    Funcionario fLogado = new Funcionario();
    
    try {
        // Página Requisitada
        int ultimo_indice = request.getRequestURL().lastIndexOf("/");
        String pagina = request.getRequestURL().substring(ultimo_indice+1);
        
        ArrayList<String> minhas_paginas = new ArrayList<String>();
        minhas_paginas.add("index.jsp");// Página index acessado por todos os perfis
        
        fLogado = (Funcionario) session.getAttribute("funcionario_logado");        
        ArrayList<Menu> menusDoFuncionario = fLogado.getPerfil().getMenus();
        for(Menu mu:menusDoFuncionario) {
                minhas_paginas.add(mu.getLink());
                
                if(mu.getLink().contains("listar_")){
%>            
                    <a href="<%=mu.getLink() %>"><%=mu.getTitulo() %></a>
                    |            
<%    
                }
        }
        out.print(" ("+fLogado.getNome() + ") <a href='sair.jsp'>Sair</a> ");
        
        
        if(!minhas_paginas.contains(pagina)){
            throw new Exception("Acesso a página não autorizada!");
        }        
        
    } catch(Exception e) {
        response.sendRedirect("login.jsp");
    }
    
%>