<%-- 
    Document   : forgot
    Created on : Nov 27, 2020, 9:23:38 PM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        <p>Please enter your email address to retrieve your password.</p>
        <form actioin="forgot" method="post">
            Email:<input type="email" name="email"><br>
            <input type="submit" value="Submit">
        </form>
        <p>
            <c:if test="${message eq 'sent'}">If the address you entered is valid, you will receive an email very soon. <br>
                Please check your email for your password.</c:if>
        </p>
    </body>
</html>
