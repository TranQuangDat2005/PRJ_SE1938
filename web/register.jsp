<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CARDLORD - SIGN UP</title>
        <link href="https://fonts.googleapis.com/css2?family=Sigmar&display=swap" rel="stylesheet">
        <style>
            * {
                font-family: 'Sigmar', cursive;
                letter-spacing: 1px;
                box-sizing: border-box;
            }

            body {
                background-image: url(icon_image/register-bg.png);
                background-size: cover;
                background-position: center;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: flex-end;
                align-items: center;
                height: 100vh;
                padding-right: 10vw;
            }

            .re-form {
                background-color: #ececec;
                width: 40vw;
                max-width: 500px;
                padding: 3vh;
                border: 1vh #b8b8b8 dashed;
                border-radius: 1vh;
                text-align: center;
                position: relative;
            }

            .close-btn {
                position: absolute;
                top: 2vh;
                right: 2vw;
                cursor: pointer;
                font-size: 2rem;
                color: #545454;
            }

            .re-form h1 {
                font-size: 4vw;
                color: white;
                text-shadow: -2px -2px 0 black, 2px -2px 0 black, -2px 2px 0 black, 2px 2px 0 black;
                margin-bottom: 2vh;
            }

            .error {
                color: #ff0000;
                font-size: 1.2rem;
                font-weight: bold;
                margin-bottom: 1vh;
            }

            label {
                font-size: 1.2rem;
                margin-top: 1vh;
            }

            input {
                width: 100%;
                height: 4vh;
                border-radius: 1vh;
                padding-left: 1vw;
                background-color: #F5F5F5;
                color: #545454;
                border: none;
                font-size: 1rem;
                margin-bottom: 1vh;
            }

            input[type="submit"] {
                background-color: #007BFF;
                color: white;
                border: none;
                height: 5vh;
                padding: 0.5vh;
                font-size: 1.2rem;
                cursor: pointer;
                border-radius: 1vh;
                width: 100%;
                margin-top: 2vh;
            }

            a {
                display: block;
                color: #007BFF;
                text-decoration: none;
                margin-top: 2vh;
                font-size: 1rem;
            }
            
            
        </style>
    </head>
    <body>
        <div class="re-form">
            <div class="close-btn" onclick="window.location.href = 'index.html';">X</div>
            <h1>CARDLORD</h1>
            <div class="error">${errorMessage}</div>
            <form action="RegisterServlet" method="POST">
                <label>Username</label>
                <input type="text" name="username" required>

                <label>Password</label>
                <input type="password" name="password" required>

                <label>Email</label>
                <input type="email" name="email" required>

                <label>Phone Number</label>
                <input type="text" name="phone_number" required>

                <label>Date of Birth</label>
                <input type="date" name="dob" required>

                <input type="submit" value="This is me" name="btAction">
                <a href="login.jsp">Already a Duke? Login here!</a>
            </form>
        </div>
    </body>
</html>
