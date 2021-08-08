<%-- 
    Document   : category
    Created on : Dec 8, 2020, 9:19:09 AM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2>Manage Categories</h2>
        <table>
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.categoryId}</td>
                    <td>${category.categoryName}</td>
                    <td>
                        <form action="category" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="edit">                        
                            <input type="hidden" name="categoryId" value="${category.categoryId}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${selectedCategory eq null}">
            <h2>Add Category</h2>

            <form action="category" method="post">
                Category Name: <input type="text" name="categoryName"><br>
                <input type="submit" value="Save">
                <input type="hidden" name="action" value="create"> 
            </form>
        </c:if>

        <c:if test="${selectedCategory ne null}">
            <h2>Edit Item</h2>

            <form action="category" method="post">
                Category ID: ${selectedCategory.categoryId}<br>
                Category Name: <input type="text" name="categoryName" value="${selectedCategory.categoryName}"><br>
                <input type="submit" value="Save">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="categoryId" value="${selectedCategory.categoryId}">
            </form>
        </c:if>

        <p>
            <c:if test="${message eq 'create'}">Category created</c:if>
            <c:if test="${message eq 'update'}">Category updated</c:if>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
        </p>

    </body>
</html>
