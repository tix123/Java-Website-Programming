<%-- 
    Document   : Registration
    Created on : Dec 7, 2020, 1:39:25 PM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <h2>Register</h2>
        <form action="register" method="post">
            First Name: <input type="text" name="first_name"><br>
            Last Name: <input type="text" name="last_name"><br>
            Email: <input type="text" name="email"><br>
            Password: <input type="password" name="password"><br>
            Company: <select name="company">
                <c:forEach items="${companys}" var="company">
                    <option value="${company.companyId}">${company.companyName}</option>
                </c:forEach>
            </select><br>
            <input type="submit" value="Register">
        </form>
        <p>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
            <c:if test="${message eq 'invalid'}">Sorry, this Email is registered.</c:if>
            <c:if test="${message eq 'create'}">A new account is created.</c:if> 
        </p>
        <a href="login">Back to login page</a>
    </body>
</html>
