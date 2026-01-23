<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý sản phẩm</title>

    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #4facfe, #00f2fe);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background: #ffffff;
            padding: 40px 50px;
            border-radius: 12px;
            width: 420px;
            text-align: center;
            box-shadow: 0 15px 30px rgba(0,0,0,0.2);
        }

        h1 {
            margin-bottom: 10px;
            color: #333;
        }

        p {
            color: #666;
            margin-bottom: 30px;
            font-size: 15px;
        }

        a.button {
            display: inline-block;
            text-decoration: none;
            padding: 12px 24px;
            background-color: #007bff;
            color: #fff;
            border-radius: 8px;
            font-size: 16px;
            transition: background 0.3s, transform 0.2s;
        }

        a.button:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }

        footer {
            margin-top: 25px;
            font-size: 13px;
            color: #aaa;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>📦 Quản lý sản phẩm</h1>

    <p>
        Chào mừng bạn đến với hệ thống quản lý sản phẩm.<br/>
        Thực hiện các chức năng thêm, sửa, xóa và xem sản phẩm.
    </p>

    <a href="list" class="button">➡ Xem danh sách sản phẩm</a>

    <footer>
        JSP • Servlet • JDBC • MVC
    </footer>
</div>

</body>
</html>
