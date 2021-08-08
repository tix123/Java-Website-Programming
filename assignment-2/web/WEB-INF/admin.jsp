<%-- 
    Document   : admin
    Created on : Nov 15, 2020, 6:41:30 PM
    Author     : 808111
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h2>Manage Users</h2>
        <table>
            <tr>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <form action="admin" method="post">
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">                        
                            <input type="hidden" name="user_email" value="${user.email}">
                        </form>
                    </td>
                    <td>
                        <form action="admin" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="edit">                        
                            <input type="hidden" name="user_email" value="${user.email}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${selectedUser eq null}">
            <h2>Add User</h2>
            <form action="admin" method="post">
                Email: <input type="email" name="user_email"><br>
                First Name: <input type="text" name="first_name"><br>
                Last Name: <input type="text" name="last_name"><br>
                Password: <input type="password" name="password"><br>
                <input type="hidden" name="action" value="create">
                <input type="submit" value="Save">
            </form>
        </c:if>

        <c:if test="${selectedUser ne null}">
            <h2>Edit User</h2>
            <form action="admin" method="post">
                Email: ${selectedUser.email}<br>
                First Name: <input type="text" name="first_name" value="${selectedUser.firstName}"><br>
                Last Name: <input type="text" name="last_name" value="${selectedUser.lastName}"><br>
                Password: <input type="password" name="password" value="${selectedUser.password}"><br>
                <input type="hidden" name="user_email" value="${selectedUser.email}"><br>
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Save">
            </form>
        </c:if>

        <p>
            <c:if test="${message eq 'create'}">User created</c:if>
            <c:if test="${message eq 'update'}">User updated</c:if>
            <c:if test="${message eq 'delete'}">User deleted</c:if>
            <c:if test="${message eq 'invalid'}">You cannot delete yourself.</c:if>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
        </p>

    </body>
</html>
