<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>錯誤</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css">
</head>
<body class="error-page">
    <div class="error-container">
        <h1>發生錯誤</h1>
        <p>${errorMessage}</p>
        <button type="button" class="btn btn-outline-secondary" onclick="window.location.href='${pageContext.request.contextPath}/'">返回登入或註冊</button>
    </div>
</body>
</html>
