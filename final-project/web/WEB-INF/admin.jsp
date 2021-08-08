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
        <title>HOME nVentory</title>
        <style>
            table, th, td {border: 1px solid black;}
        </style>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <p>Login user : ${firstName} ${lastName} </p>
        <h3>Menu</h3> 
        <ul>
            <li><a href="inventory">Inventory</a></li>
            <li><a href="admin">Admin</a></li>
            <li><a href="company_admin">Company Admin</a></li>
            <li><a href="category">Manage Categories</a></li>
            <li><a href="settings">Settings</a></li>
            <li><a href="login?logout">Logout</a></li>
        </ul>
        <h2>Manage Users</h2>
        <table>
            <tr>
                <th>Email</th>
                <th>Active</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Role</th>
                <th>Company</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>
                        <c:if test="${user.active eq true}">Active</c:if>
                        <c:if test="${user.active eq false}">Inactive</c:if>
                        </td>
                        <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <c:forEach items="${roles}" var="role">
                            <c:if test="${user.role eq role.roleId}">
                                ${role.roleName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${companys}" var="company">
                            <c:if test="${user.company eq company.companyId}">
                                ${company.companyName}
                            </c:if>
                        </c:forEach>
                    </td>
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
                Company: <select name="company">
                    <c:forEach items="${companys}" var="company">
                        <option value="${company.companyId}">${company.companyName}</option>
                    </c:forEach>
                </select><br>
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
                Active: <select name="active">
                    <option value="true" 
                            <c:if test="${selectedUser.active eq true}">
                                selected
                            </c:if>
                            >Active</option>
                    <option value="false" 
                            <c:if test="${selectedUser.active eq false}">
                                selected
                            </c:if>
                            >Inactive</option>
                </select><br>
                Role: <select name="role">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.roleId}"
                                <c:if test="${selectedUser.role eq role.roleId}">
                                    selected
                                </c:if>
                                >${role.roleName}</option>
                    </c:forEach>
                </select><br>
                Company: <select name="company">
                    <c:forEach items="${companys}" var="company">
                        <option value="${company.companyId}"
                                <c:if test="${selectedUser.company eq company.companyId}">
                                    selected
                                </c:if>
                                >${company.companyName}</option>
                    </c:forEach>
                </select><br>
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

            <h2>Search Items</h2>
            <form action="admin" method="get">
                Item Keyword: <input type="text" name="keyword">
                <input type="hidden" name="action" value="search">
                <input type="submit" value="Search"><br>
            </form>
        <c:if test="${items ne null}">
            <table>
                <tr>
                    <th>Item Name</th>
                    <th>Owner</th>
                </tr>

                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>${item.itemName}</td>
                        <td>${item.owner}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
