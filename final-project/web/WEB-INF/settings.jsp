<%-- 
    Document   : settings
    Created on : Dec 7, 2020, 8:43:50 PM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <p>Login user : ${user.firstName} ${user.lastName} </p>
        <h3>Menu </h3> 
        <ul>
            <li><a href="inventory">Inventory</a></li>
            <li><a href="admin">Admin</a></li>
            <li><a href="company_admin">Company Admin</a></li>
            <li><a href="category">Manage Categories</a></li>
            <li><a href="settings">Settings</a></li>
            <li><a href="login?logout">Logout</a></li>
        </ul>
        <h2>Account Settings</h2>
        <form action="settings" method="post">
            Email: ${email}<br>
            First Name: <input type="text" name="first_name" value="${user.firstName}"><br>
            Last Name: <input type="text" name="last_name" value="${user.lastName}"><br>
            Password: <input type="password" name="password" value="${user.password}"><br>
            Active: <select name="active">
                <option value="true" 
                        <c:if test="${user.active eq true}">
                            selected
                        </c:if>
                        >Active</option>
                <option value="false" 
                        <c:if test="${user.active eq false}">
                            selected
                        </c:if>
                        >Inactive</option>
            </select> (Note: Once you deactivate your account, you may not login again.)<br>
            Role:<c:forEach items="${roles}" var="role">
                <c:if test="${user.role eq role.roleId}">${role.roleName}</c:if>
            </c:forEach><br>
            Company: <select name="company">
                <c:forEach items="${companys}" var="company">
                    <option value="${company.companyId}"
                            <c:if test="${user.company eq company.companyId}">
                                selected
                            </c:if>
                            >${company.companyName}</option>
                </c:forEach>
            </select><br>
            <input type="submit" value="Save">
        </form>
        <p>
            <c:if test="${message eq 'update'}">Settings updated</c:if>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
        </p>
    </body>
</html>
