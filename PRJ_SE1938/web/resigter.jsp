<%-- 
    Document   : resigter
    Created on : Mar 2, 2025, 2:45:24 PM
    Author     : Phan Sơn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registration Form</title>
    </head>
    <body>
        <h2>Registration Form</h2>
        <form action="RegisterServlet" method="POST">
            <label>Username:</label>
            <input type="text" name="username" required><br>

            <label>Password:</label>
            <input type="password" name="password" required><br>

            <label>Email:</label>
            <input type="email" name="email" required><br>

            <label>Phone Number:</label>
            <input type="text" name="phone_number" required><br>

            <label>Date of Birth:</label>
            <input type="date" name="dob" required><br>
            <input type="submit" value="Register" name="btAction" />
            <%
            String error = (String) request.getAttribute("error");
            if (error != null){
            %>
            <h2 style="color: red"><%=error%></h2>
            <%
                }
            %>
        </form>
    </body>
</html>
