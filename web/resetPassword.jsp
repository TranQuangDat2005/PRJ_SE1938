<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            padding: 20px;
        }

        .container {
            width: 100%;
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            font-size: 14px;
            color: #555;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }

        .form-group input:focus {
            border-color: #007bff;
            outline: none;
        }

        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        .form-group button:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }

        .success {
            color: green;
            font-size: 12px;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Reset Your Password</h2>
        <form action="updatePassword" method="POST">
            <div class="form-group">
                <label for="new-password">New Password</label>
                <input type="password" id="new-password" name="newPassword" required>
            </div>
           
            <input readonly name="email" value="${param.email}" />
            
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
            
            
            <div class="form-group">
                <button type="submit">Change Password</button>
            </div>
            <div id="message">
                <!-- Error or success message will appear here -->
                <p class="error">${requestScope.errorMessage}</p>
                <p class="success">${requestScope.successMessage}</p>
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
