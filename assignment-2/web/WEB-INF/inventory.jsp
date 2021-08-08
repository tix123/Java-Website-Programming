<%-- 
    Document   : inventory
    Created on : Nov 15, 2020, 6:46:12 PM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assignment2</title>
        <style>
            table, th, td {border: 1px solid black;}
        </style>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h3>Menu</h3>
        <ul>
            <li><a href="inventory">Inventory</a></li>
            <li><a href="admin">Admin</a></li>
            <li><a href="login?logout">Logout</a></li>
        </ul>
        <h2>Inventory for ${firstName} ${lastName}</h2>
        <table>
            <tr>
                <th>Category</th>
                <th>Name</th>
                <th>Price</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${items}" var="item">
                <tr>
                    <td>
                        <c:forEach items="${categories}" var="category">
                            <c:if test="${item.category eq category.categoryId}">${category.categoryName}</c:if>
                        </c:forEach>
                    </td>
                    <td>${item.itemName}</td>
                    <td>${item.price}</td>
                    <td>
                        <form action="inventory" method="post">
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">                        
                            <input type="hidden" name="itemId" value="${item.itemId}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h2>Add Item</h2>

        <form action="inventory" method="post">
            Category: <select name="category">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.categoryId}">${category.categoryName}</option>
                </c:forEach>
            </select><br>
            Name: <input type="text" name="itemName"><br>
            Price: <input type="text" name="price"><br>
            <input type="submit" value="Save">
            <input type="hidden" name="action" value="create"> 
        </form>

        <p>
            <c:if test="${message eq 'create'}">Item created</c:if>
            <c:if test="${message eq 'delete'}">Item deleted</c:if>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
        </p>
    </body>
</html>
