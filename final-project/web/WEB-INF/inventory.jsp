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
        <title>HOME nVentory</title>
        <style>
            table, th, td {border: 1px solid black;}
        </style>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <p>Login user : ${firstName} ${lastName} </p>
        <h3>Menu </h3> 
        <ul>
            <li><a href="inventory">Inventory</a></li>
            <li><a href="admin">Admin</a></li>
            <li><a href="company_admin">Company Admin</a></li>
            <li><a href="category">Manage Categories</a></li>
            <li><a href="settings">Settings</a></li>
            <li><a href="login?logout">Logout</a></li>
        </ul>
        <h2>Inventory List</h2>
        <table>
            <tr>
                <th>Category</th>
                <th>Name</th>
                <th>Price</th>
                <th>Delete</th>
                <th>Edit</th>

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
                    <td>
                        <form action="inventory" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="edit">                        
                            <input type="hidden" name="itemId" value="${item.itemId}">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <c:if test="${selectedItem eq null}">
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
        </c:if>

        <c:if test="${selectedItem ne null}">
            <h2>Edit Item</h2>

            <form action="inventory" method="post">
                Category: <select name="category">
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.categoryId}"
                                <c:if test="${selectedItem.category eq category.categoryId}">
                                    selected
                                </c:if>
                                >${category.categoryName}</option>
                    </c:forEach>
                </select><br>
                Name: <input type="text" name="itemName" value="${selectedItem.itemName}"><br>
                Price: <input type="text" name="price" value="${selectedItem.price}"><br>
                <input type="submit" value="Save">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="itemId" value="${selectedItem.itemId}">
            </form>
        </c:if>

        <p>
            <c:if test="${message eq 'create'}">Item created</c:if>
            <c:if test="${message eq 'update'}">Item updated</c:if>
            <c:if test="${message eq 'delete'}">Item deleted</c:if>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
        </p>


    </body>
</html>
