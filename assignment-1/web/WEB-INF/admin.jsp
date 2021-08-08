<%-- 
    Document   : admin
    Created on : Oct 14, 2020, 8:34:07 PM
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
        <h2>Admin Summary</h2>
        <p>Total value for all users: $${total}. Most expensive item is ${item} at $${price} owned by ${owner}</p>
        <a href="login?logout">Log out</a>
    </body>
</html>
