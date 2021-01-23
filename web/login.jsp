<%-- 
    Document   : login
    Created on : 21/12/2020, 23:28:54
    Author     : Francisco de Assis de Oliveira Silva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Entrar no Sistema</h1>
        <form name="login" method="post" action="valida_login.do">
            Login: <input type="text" name="login" required /> <br/>
            
            Senha: <input type="password" name="senha" required /><br/>
            
            <input type="submit" value="Entrar" />
        </form>
    </body>
</html>
