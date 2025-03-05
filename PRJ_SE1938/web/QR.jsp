<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html lang="en">
<head>
    <link rel="stylesheet" href="Css/QR.css" />
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <div class="courses_wrapper">
    <div class="courses_inner">
      <div class="course_item">
          <img src="ImageDemo/anh-dai-dien-90-780x450.jpg">
        <p>Digital Marketing</p>
        <p>10000</p>
        <a class="course_item_btn">Mua</a>
      </div>
      <div class="course_item">
          <img src="ImageDemo/anh-mau-xanh-duong.jpg">
        <p>Digital Marketing</p>
        <p>10000</p>
        <a class="course_item_btn">Mua</a>
      </div>
      <div class="course_item">
          <img src="ImageDemo/hinh-nen-anime-16.jpg">
        <p>Digital Marketing</p>
        <p>10000</p>
        <a class="course_item_btn">Mua</a>
      </div>
    </div>
  </div>
 
  <div class="toast_overlay hidden" id="toast_overlay"></div>

  <div class="course_qr hidden" id="course_qr">
    <div class="toast_body">
      <!-- QR Image -->
      <div class="qr_wrapper">
        <img class="course_qr_img" src="https://upload.wikimedia.org/wikipedia/commons/6/65/Simple_QR_code.svg" alt="QR Code">
      </div>
  
      <!-- Payment Information -->
      <div class="course_info">
        <p>Số tiền muốn nạp:</p>
        <div class="input_group">
          <input type="text" id="paid_price" class="form_input" readonly value="225.000 VND">
          <button class="copy_btn" onclick="copyToClipboard('paid_price')">Copy</button>
        </div>
  
        <p>Nội dung nạp:</p>
        <div class="input_group">
          <input type="text" id="paid_content" class="form_input" readonly value="OBSHD1">
          <button class="copy_btn" onclick="copyToClipboard('paid_content')">Copy</button>
        </div>
  
        <!-- Countdown -->
        <p id="countdown_timer" class="countdown">5:00</p>
      </div>
  
      <!-- Close Button -->
      <button class="close_btn" id="close_toast">Close Form</button>
    </div>
  </div>
  

<!-- Toast Success Notification -->
<div class="success_toast hidden11" id="success_toast">
  <div class="toast_icon">
    <i class="checkmark">✔</i>
  </div>
  <div class="toast_message">
    <p>Thanh toán thành công!</p>
  </div>
</div>



  </body>
  <script src="Javascript/QR.js"></script>
  </html>
  