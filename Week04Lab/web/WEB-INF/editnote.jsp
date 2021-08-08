<%-- 
    Document   : editnote
    Created on : Sep 28, 2020, 5:55:15 PM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Note Keeper</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h2>Edit Note</h2>
        <form method="post" action="note">
        <b>Title:</b> <input type="text" name="title" value="${note.title}"><br>
        <b>Contents:</b> <textarea cols="20" rows="5" name="contents">${note.contents}</textarea><br>
        
        <input type="submit" value="Save">
        </form>
    </body>
</html>
