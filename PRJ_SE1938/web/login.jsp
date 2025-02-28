<%-- 
    Document   : login
    Created on : 27 Feb 2025, 7:52:33 pm
    Author     : daizl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1>Login! You enter username and password!</h1>
        <form action="LoginServlet" method="POST">
            Username: <input type="text" name="username" value="" required/><br><br>
            Password: <input type="password" name="password" value="" required/><br><br>
            <div class="error">
                ${errorMessage}
            </div>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form>        
    </body>
</html>
