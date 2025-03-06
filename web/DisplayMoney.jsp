<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .details {
            margin-top: 20px;
            font-size: 18px;
            line-height: 1.6;
        }
        .details p {
            color: #555;
        }
        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 20px;
            background-color: #007BFF;
            color: #fff;
            text-decoration: none;
            font-weight: bold;
            border-radius: 4px;
            text-align: center;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Payment Information</h2>
        <div class="details">
            <p><strong>Amount:</strong> ${param.vnp_Amount} VND</p>
            <p><strong>Payment Date:</strong> ${param.vnp_PayDate}</p>
        </div>
        
        <!-- Optional: Displaying formatted amount and date from servlet -->
        <div class="details">
            <p><strong>Formatted Amount:</strong> ${amount} VND</p>
            <p><strong>Formatted Payment Date:</strong> ${formattedDateTime}</p>
        </div>
        <div> BẠN VỪA NẠP THÀNH CÔNG  ${param.vnp_Amount} VND, CARDLORD CHÚC BẠN HỌC TỐT</div>
        <a href="someOtherPage.jsp" class="btn">Back to Home</a>
    </div>

</body>
</html>
