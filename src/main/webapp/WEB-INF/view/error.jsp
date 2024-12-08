<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>錯誤 - Forward to Wealth</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <div class="error-container">
        <h1>發生錯誤</h1>
        <p>${errorMessage}</p>
        <a href="${pageContext.request.contextPath}/" class="btn btn-primary">返回登入或註冊</a>
    </div>
</body>
</html>
