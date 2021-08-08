<%-- 
    Document   : shoppingList
    Created on : Oct 18, 2020, 7:29:50 PM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello ${username}</p>
        <p><a href="ShoppingList?action=logout">Logout</a></p>

        <form action="" method="post">
            <h2>Add Item</h2>
            <input type="text" name="item">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add Item">
        </form>

        <form action="" method="post">
            <ul>
                <c:forEach items="${itemList}" var="item">
                    <li><input type="radio" name="item" value="${item}">${item}</li>
                    </c:forEach>
            </ul>

            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete">
        </form>


    </body>
</html>
