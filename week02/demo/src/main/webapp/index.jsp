<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            margin: 0;
        }
        header {
            background: #4f46e5;
            color: white;
            padding: 16px;
            text-align: center;
        }
        .container {
            padding: 20px;
        }
        .card {
            background: white;
            padding: 16px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            margin-bottom: 16px;
        }
        ul {
            padding-left: 20px;
        }
        li {
            margin: 6px 0;
        }
    </style>
</head>

<body>

<header>
    <h1>Xin chào ${username}</h1>
    <p>Chào mừng đến với trang JSP</p>
</header>

<div class="container">

    <div class="card">
        <h2>📚 Danh sách khóa học</h2>

        <ul>
            <c:forEach items="${courses}" var="c">
                <li>${c}</li>
            </c:forEach>
        </ul>
    </div>

    <div class="card">
        <h2>ℹ️ Trạng thái</h2>

        <c:choose>
            <c:when test="${not empty username}">
                <p>Bạn đã đăng nhập ✔️</p>
            </c:when>
            <c:otherwise>
                <p>Bạn chưa đăng nhập ❌</p>
            </c:otherwise>
        </c:choose>
    </div>

</div>

</body>
</html>
