<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>收支紀錄</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">

</head>
<body>
    <%-- 主內容區域 --%>
    <div class="main">
        <%-- 上方工具欄 --%>
        <%@ include file="/WEB-INF/view/fragment/header.jspf"%>
        <%-- 左側選單 --%>
        <%@ include file="/WEB-INF/view/fragment/sidebar.jspf"%>

        <%-- 收支紀錄內容 --%>
        <main class="content">
            <div class="container-fluid p-0">
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-md-10 col-sm-12">
                        <h1 class="h3 mb-3">最近的收支紀錄</h1>
                        <%-- 放置收支表單和紀錄表格 --%>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const currentPath = window.location.pathname; // 獲取當前的路徑
            document.querySelectorAll(".nav-link").forEach(link => {
                if (link.getAttribute("href").includes(currentPath)) {
                    link.classList.add("menu-active");
                }
            });
        });
    </script>
</body>
</html>
