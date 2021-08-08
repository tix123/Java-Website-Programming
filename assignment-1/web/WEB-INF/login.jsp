<%-- 
    Document   : login
    Created on : Oct 14, 2020, 8:34:29 PM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Inventory</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h2>Login</h2>
        <form method="post" action="login">
            User name: <input type="text" name="username" value="${username}"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Submit"><br>
        </form>
        <div>${message}</div>

    </body>
</html>
