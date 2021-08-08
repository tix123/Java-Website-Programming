<%-- 
    Document   : login.jsp
    Created on : Oct 12, 2020, 5:06:00 AM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="login">
        Username: <input type="text" name="username" value="${username}"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Log in"><br>
        </form>
        <div>${message}</div>
    </body>
</html>
