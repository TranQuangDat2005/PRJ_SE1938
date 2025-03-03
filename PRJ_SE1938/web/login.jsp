<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CARDLORD - LOGIN</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <%@ include file="chatBot.jsp" %>

        <h1>Login! You enter username and password!</h1>
        <form action="LoginServlet" method="POST">
            Username: <input type="text" name="username" value="" required/><br><br>
            Password: <input type="password" name="password" value="" required/><br><br>
            <div class="error">
                ${errorMessage}
            </div>

            <div style="border-bottom:none;display: flex" class="inputbox">
                <ion-icon name="lock-closed-outline"></ion-icon> 
                <img id="captcha" style="width: 256px" class="captcha" alt="captcha" src="/PRJ_SE1938/captcha">
                <button id="reloadCaptcha" class="reload-btn" style="height: 44px; width: 44px; margin-top: 8px; margin-left: 8px;">
                    <i class="fas fa-redo-alt"></i>
                </button>
            </div>

            <div class="inputbox">
                <ion-icon name="lock-closed-outline"></ion-icon> 
                <input type="text" name="captcha" required />
                <label>Input captcha code</label>
            </div>

            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form> 

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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
