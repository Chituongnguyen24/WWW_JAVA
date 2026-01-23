<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
        }

        .container {
            width: 900px;
            margin: 40px auto;
            background: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .add {
            display: inline-block;
            margin-bottom: 15px;
            text-decoration: none;
            padding: 8px 14px;
            background: #28a745;
            color: #fff;
            border-radius: 6px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }

        th {
            background: #007bff;
            color: white;
        }

        img {
            border-radius: 6px;
        }

        .action a {
            margin: 0 6px;
            text-decoration: none;
            padding: 6px 10px;
            border-radius: 4px;
            color: white;
        }

        .edit {
            background: #ffc107;
        }

        .delete {
            background: #dc3545;
        }
    </style>
</head>
<body>

<div class="container">

    <h2>📦 Danh sách sản phẩm</h2>

    <a href="new" class="add">+ Thêm mới</a>

    <table>
        <tr>
            <th>Tên</th>
            <th>Giá</th>
            <th>Hình ảnh</th>
            <th>Hành động</th>
        </tr>

        <c:forEach var="p" items="${listProduct}">
            <tr>
                <td>${p.name}</td>
                <td>${p.price}</td>
                <td>
                    <img src="uploads/${p.image}" width="90"/>
                </td>
                <td class="action">
                    <a class="edit" href="edit?id=${p.id}">Sửa</a>
                    <a class="delete" href="delete?id=${p.id}"
                       onclick="return confirm('Xóa sản phẩm này?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
