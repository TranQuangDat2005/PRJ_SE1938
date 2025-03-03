<%-- 
    Document   : register
    Created on : Feb 28, 2025, 2:18:24 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CARDLORD - SIGN UP</title>
        <link href="https://fonts.googleapis.com/css2?family=Sigmar&display=swap" rel="stylesheet">
        <style>
            * {
                font-family: 'Sigmar';
                letter-spacing: 1px;
            }

            .re-body{
                display: flex;
            }

            .nothing{
                width: 50%;
                display: hidden;
            }

            .re-form{
                background-color: #ececec;
                justify-self: end;
                justify-items: center;
                margin-right: 4%;
                margin-top: 4%;
                height: 80vh;
                width: 35%;
                border: 10px #b8b8b8 dashed;
                border-radius: 10px;
                padding: 20px;
                align-content: center;

            }

            .re-form form{
                font-size: 20px;
                justify-content: center;
            }

            .re-form form input{
                height: 40px;
                width: 45vh;
                border-radius: 10px;
                padding-left: 20px;
                background-color: #F5F5F5;
                color: #545454;
                margin-top: 1%;
                margin-bottom: 2%;
                margin-left: 1%;
            }

            body{
                background-image: url(icon_image/register-bg.png);
            }

            .error {
                color: #ff0000; /* Red color for visibility */
                font-size: 15px;
                margin: 0px;
                font-weight: bold;
                text-align: center;
                display: inline-block;
                width: 100%;
            }

        </style>
    </head>
    <body>
        <div class="re-form">
            <div onclick="window.location.href = 'index.html';" style="margin-left: 90%; margin-top: -10%; cursor: pointer; font-size: 30px; color: #545454">X</div>
            <form action="RegisterServlet" method="POST" style="margin-top: -30px">
                <div style="justify-self: center">
                    <h1 class="logo"
                        style="font-size: 70px;
                        font-weight: bold;
                        color: white;
                        margin: 0px;
                        text-shadow:
                        -2px -2px 0 black,  2px -2px 0 black,
                        -2px  2px 0 black,  2px  2px 0 black,
                        -3px -3px 0 black,  3px -3px 0 black,
                        -3px  3px 0 black,  3px  3px 0 black;">
                        CARDLORD
                    </h1>
                    <div class="error">
                        ${errorMessage}
                    </div>
                </div>
                <label>Username:</label><br>
                <input type="text" name="username" required><br>

                <label>Password:</label><br>
                <input type="password" name="password" required><br>

                <label>Email:</label><br>
                <input type="email" name="email" required><br>

                <label>Phone Number:</label><br>
                <input type="text" name="phone_number" required><br>

<!--
                <label>Date of Birth:</label><br>
                <input type="date" name="dob" required><br>-->


   <div style="border-bottom:none;display: flex" class="inputbox"> <ion-icon name="lock-closed-outline"></ion-icon> 
                            <img id="captcha" style="width: 256px" class="captcha" alt="captcha" src="/PRJ_SE1938/captcha">
                            <button id="reloadCaptcha" class="reload-btn" style="height: 44px; width: 44px; margin-top: 8px ;margin-left: 8px"><i class="fas fa-redo-alt"></i></button>
                        </div>


                        <div class="inputbox"> <ion-icon name="lock-closed-outline"></ion-icon> 
                            <input  type="text" name="captcha" > 
                            <label>Input captcha code</label>
                        </div>
                
                <div style="justify-self: center">
                    <input style="background-color: #007BFF;
                           color: white;
                           border: none;
                           padding: 10px;
                           margin: 10px;
                           font-size: 16px;
                           cursor: pointer;
                           border-radius: 10px;
                           text-align: center;" type="submit" value="This is me" name="btAction" />
                </div>
                <div style="justify-self: center;">
                    <a href="login.jsp" style="color: #007BFF; text-decoration: none;">Already a Duke? Login here!</a>
                </div>
                
                
              
            </form>
        </div>
                       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>     
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <script src="https://code.jquery.com/jquery-3.6.3.js"
        integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

                <script>
            $(document).ready(function () {
                // Gắn sự kiện click cho nút reload captcha
                $('#reloadCaptcha').click(function (e) {
                    e.preventDefault(); // Ngăn chặn hành động mặc định của nút
                    reloadCaptcha();
                });

                // Hàm để tải lại captcha bằng AJAX
                function reloadCaptcha() {
                    // Gửi yêu cầu AJAX để tải lại captcha
                    $.ajax({
                        type: 'GET',
                        url: '/PRJ_SE1938/captcha', // Đảm bảo đường dẫn đúng tới endpoint của captcha
                        success: function () {
                            // Cập nhật hình ảnh captcha với đường dẫn mới (để tránh cache)
                            var newImgSrc = '/PRJ_SE1938/captcha?' + new Date().getTime(); // Thêm timestamp để tránh cache
                            $('.captcha').attr('src', newImgSrc); // Cập nhật src của ảnh captcha
                        },
                        error: function (xhr, status, error) {
                            // Xử lý lỗi nếu có
                            console.error('Lỗi tải lại captcha:', error);
                        }
                    });
                }
            });
        </script>
    </body>
</html>

