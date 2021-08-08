<%-- 
    Document   : inventory
    Created on : Oct 14, 2020, 8:35:44 PM
    Author     : 808111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Inventory</title>
    </head>
    <body>
        <h1>Home Inventory for ${username}</h1>
        <h2>Add Item</h2>
        <form method="post" action="inventory">
            Category<select name="category">
                <option value="bedroom">bedroom</option>
                <option value="garage">garage</option>
                <option value="kitchen">kitchen</option>
                <option value="living room">living room</option>
            </select><br>
            Item Name: <input type="text" name="item"><br>
            Price: <input type="text" name="price"><br>
            <input type="submit" value="Add"><br> 
        </form>
        <p>${message}</p>

        <p>Total value in inventory: $${total}</p>

        <a href="login?logout">Log out</a>
    </body>
</html>
