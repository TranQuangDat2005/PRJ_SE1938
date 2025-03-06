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
             <form action="LoginServlet" method="POST" style="margin-top: -30px">
                
                <label>Username:</label><br>
                <input type="text" name="username" required><br>

                <label>Password:</label><br>
                <input type="password" name="password" required><br>

                <div style="border-bottom:none;display: flex; justify-self: center" class="inputbox">
                    <ion-icon name="lock-closed-outline"></ion-icon> 
                    <img id="captcha" style="width: 256px; border: 2px black solid;" class="captcha" alt="captcha" src="/PRJ_SE1938/captcha">
                    <button id="reloadCaptcha" class="reload-btn" 
                            style="height: 44px; width: 44px; margin-top: 8px; margin-left: 8px; border-radius: 50px;
                            background-image: url('icon_image/retry-captcha.png');
                            background-size: cover; background-repeat: no-repeat;">
                    </button>
                </div>

                <div class="inputbox" style="justify-self: center; width: 75%">
                    <label>Input captcha code above!</label>
                    <input type="text" name="captcha" required /><br><!-- comment -->
                </div>
                <div style="justify-self: center">
                    <input style="background-color: #007BFF;
                           color: white;
                           border: none;
                           padding: 5px;
                           margin: 10px;
                           font-size: 16px;
                           cursor: pointer;
                           border-radius: 10px;
                           text-align: center;" type="submit" value="Log in" name="btAction" />
                </div>
                <div style="justify-self: center; justify-items: center">
                    <a href="ForgotPassword.jsp" style="color: #007BFF; text-decoration: none;">Forgot your password? Recover it here!</a><br><!-- comment -->
                    <p>or</p>
                    <a href="register.jsp" style="color: #007BFF; text-decoration: none;">New to us? Sign up here!</a>
                </div>
            </form>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


        <script>
                $(document).ready(function () {
                    // G?n s? ki?n click cho nút reload captcha
                    $('#reloadCaptcha').click(function (e) {
                        e.preventDefault(); // Ng?n ch?n hành ??ng m?c ??nh c?a nút
                        reloadCaptcha();
                    });

                    // Hàm ?? t?i l?i captcha b?ng AJAX
                    function reloadCaptcha() {
                        // G?i yêu c?u AJAX ?? t?i l?i captcha
                        $.ajax({
                            type: 'GET',
                            url: '/PRJ_SE1938/captcha', // ??m b?o ???ng d?n ?úng t?i endpoint c?a captcha
                            success: function () {
                                // C?p nh?t hình ?nh captcha v?i ???ng d?n m?i (?? tránh cache)
                                var newImgSrc = '/PRJ_SE1938/captcha?' + new Date().getTime(); // Thêm timestamp ?? tránh cache
                                $('.captcha').attr('src', newImgSrc); // C?p nh?t src c?a ?nh captcha
                            },
                            error: function (xhr, status, error) {
                                // X? lý l?i n?u có
                                console.error('L?i t?i l?i captcha:', error);
                            }
                        });
                    }
                });
        </script>
    </body>
</html>
