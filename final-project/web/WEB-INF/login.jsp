<%-- 
    Document   : login
    Created on : Nov 15, 2020, 3:10:35 PM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <h2>Login</h2>
        <form action="login" method="post">
            Email: <input type="text" name="email"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Log in">
        </form>

        <p>${message}</p>

        <p><a href="register">Register a new account</a></p>
    </body>
</html>
