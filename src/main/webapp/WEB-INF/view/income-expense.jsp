<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後台管理 - Dashboard</title>

    <!-- 引入樣式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>

<body>
    <%-- 左側導航欄 --%>
    <jsp:include page="fragments/sidebar.jspf" />

    <%-- 主內容區域 --%>
    <div class="main">
        <%-- 上方工具列 --%>
        <jsp:include page="fragments/header.jspf" />

        <main class="content">
            <div class="container-fluid p-0">
                <h1 class="h3 mb-3">收支管理</h1>
                <p>登入成功，這裡是收支管理頁面的內容。</p>
            </div>
        </main>
    </div>

    <!-- 引入 JS -->
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
