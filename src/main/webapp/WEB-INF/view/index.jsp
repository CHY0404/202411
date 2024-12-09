<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>收支管家</title>

    <!-- 引入 Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 自定義樣式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>

<body class="bg-light d-flex justify-content-center align-items-center vh-100">
    <div class="container">
        <!-- 標題與標語 -->
        <div class="text-center mb-4">
            <h1 class="fw-bold">Forward to Wealth</h1>
            <p class="text-muted">管理財務，輕鬆致富！</p>
        </div>

        <!-- 表單卡片 -->
        <div class="card shadow-sm mx-auto" style="max-width: 400px;">
            <div class="card-body">
                <!-- 登入表單 -->
                <div id="login-form" class="${errorSource == 'login' || empty errorSource ? '' : 'd-none'}">
                    <h2 class="mb-3 text-center">登入</h2>
                    <form id="login" action="${pageContext.request.contextPath}/login" method="POST">
                        <div class="mb-3">
                            <label for="login-username" class="form-label">帳號:</label>
                            <input type="text" id="login-username" name="username" class="form-control" value="${username}" required>
                            <c:if test="${errorSource == 'login' && error == 'username'}">
                                <small class="text-danger">${errorMessage}</small>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label for="login-password" class="form-label">密碼:</label>
                            <input type="password" id="login-password" name="password" class="form-control" required>
                            <c:if test="${errorSource == 'login' && error == 'password'}">
                                <small class="text-danger">${errorMessage}</small>
                            </c:if>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">登入</button>
                    </form>
                    <p class="text-center mt-3">
                        還沒有帳號？<a href="javascript:void(0);" onclick="toggleForms()">點擊註冊</a>
                    </p>
                </div>

                <!-- 註冊表單 -->
                <div id="register-form" class="${errorSource == 'register' ? '' : 'd-none'}">
                    <h2 class="mb-3 text-center">註冊</h2>
                    <form id="register" action="${pageContext.request.contextPath}/register" method="POST">
                        <div class="mb-3">
                            <label for="register-username" class="form-label">帳號:</label>
                            <input type="text" id="register-username" name="username" class="form-control" value="${username}" required>
                            <c:if test="${errorSource == 'register' && error == 'username'}">
                                <small class="text-danger">${errorMessage}</small>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label for="register-email" class="form-label">Email:</label>
                            <input type="email" id="register-email" name="email" class="form-control" value="${email}" required>
                            <c:if test="${errorSource == 'register' && error == 'email'}">
                                <small class="text-danger">${errorMessage}</small>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label for="register-password" class="form-label">密碼:</label>
                            <input type="password" id="register-password" name="password" class="form-control" required>
                            <c:if test="${errorSource == 'register' && error == 'password'}">
                                <small class="text-danger">${errorMessage}</small>
                            </c:if>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">註冊</button>
                    </form>
                    <p class="text-center mt-3">
                        已經有帳號？<a href="javascript:void(0);" onclick="toggleForms()">點擊登入</a>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <!-- 引入 Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- JS for 表單切換 -->
    <script>
        function toggleForms() {
            document.getElementById('login-form').classList.toggle('d-none');
            document.getElementById('register-form').classList.toggle('d-none');
        }
    </script>
</body>

</html>
