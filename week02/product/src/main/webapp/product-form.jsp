<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>
    <c:choose>
      <c:when test="${product == null}">Thêm sản phẩm</c:when>
      <c:otherwise>Sửa sản phẩm</c:otherwise>
    </c:choose>
  </title>

  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f4f6f8;
    }
    .container {
      width: 450px;
      margin: 60px auto;
      background: #fff;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 10px 25px rgba(0,0,0,0.15);
    }
    h2 {
      text-align: center;
      margin-bottom: 25px;
    }
    label {
      display: block;
      margin-bottom: 15px;
      font-weight: bold;
    }
    input {
      width: 100%;
      padding: 8px;
      margin-top: 6px;
    }
    button {
      width: 100%;
      padding: 10px;
      background: #007bff;
      color: #fff;
      border: none;
      border-radius: 6px;
      font-size: 16px;
      cursor: pointer;
    }
    button:hover {
      background: #0056b3;
    }
    .back {
      text-align: center;
      margin-top: 20px;
    }
    img {
      margin-top: 10px;
      border-radius: 6px;
    }
  </style>
</head>

<body>

<div class="container">

  <h2>
    <c:choose>
      <c:when test="${product == null}">➕ Thêm sản phẩm</c:when>
      <c:otherwise>✏️ Sửa sản phẩm</c:otherwise>
    </c:choose>
  </h2>

  <form action="${product == null ? 'insert' : 'update'}"
        method="post"
        enctype="multipart/form-data">

    <input type="hidden" name="id" value="${product.id}" />

    <label>
      Tên sản phẩm
      <input type="text" name="name" value="${product.name}" required />
    </label>

    <label>
      Giá
      <input type="number" name="price" value="${product.price}" required />
    </label>

    <label>
      Ảnh sản phẩm
      <input type="file" name="image" accept="image/*" />
    </label>

    <c:if test="${product != null && product.image != null}">
      <p>Ảnh hiện tại:</p>
      <img src="uploads/${product.image}" width="120" />
    </c:if>

    <button type="submit">💾 Lưu</button>
  </form>

  <div class="back">
    <a href="list">⬅ Quay lại danh sách</a>
  </div>

</div>

</body>
</html>
