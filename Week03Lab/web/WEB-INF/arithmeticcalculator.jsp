<%-- 
    Document   : arithmeticcalculator.jsp
    Created on : Sep 25, 2020, 9:30:38 PM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Arithmetic Calculator</title>
    </head>
    <body>
        <h1>Arithmetic Calculator</h1>
        <form method="post" action="arithmetic">
        First:<input type="text" name="first" value="${first}"><br>
        Second:<input type="text" name="second" value="${second}"><br>
        <input type="submit" value="+" name="submit">
        <input type="submit" value="-" name="submit">
        <input type="submit" value="*" name="submit">
        <input type="submit" value="%" name="submit">
        </form>
        <div>Result: ${message}</div>
        <a href="age">Age Calculator</a>
    </body>
</html>
