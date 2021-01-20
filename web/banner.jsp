<%-- 
    Document   : banner
    Created on : 21/12/2020, 23:58:10
    Author     : francisco
--%>

<%@page import="model.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Usuario"%>
<%
    Usuario uLogado = new Usuario();
    
    try {
        // P�gina Requisitada
        int ultimo_indice = request.getRequestURL().lastIndexOf("/");
        String pagina = request.getRequestURL().substring(ultimo_indice+1);
        
        ArrayList<String> minhas_paginas = new ArrayList<String>();
        minhas_paginas.add("index.jsp");// P�gina index acessado por todos os perfis
        
        uLogado = (Usuario) session.getAttribute("usuario");        
        ArrayList<Menu> menusDoUsuario = uLogado.getPerfil().getMenus();
        for(Menu mu:menusDoUsuario) {
                minhas_paginas.add(mu.getLink());
                
                if(mu.getLink().contains("listar_")){
%>            
                    <a href="<%=mu.getLink() %>"><%=mu.getTitulo() %></a>
                    |            
<%    
                }
        }
        out.print(" ("+uLogado.getNome() + ") <a href='sair.jsp'>Sair</a> ");
        
        
        if(!minhas_paginas.contains(pagina)){
            throw new Exception("Acesso a p�gina n�o autorizada!");
        }        
        
    } catch(Exception e) {
        response.sendRedirect("login.jsp");
    }
    
%>