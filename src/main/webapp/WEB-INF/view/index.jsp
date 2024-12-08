<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>你的財務收支管家</title>

    <!-- 引入樣式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>

<body class="loginmain">
    <div class="form-container">
        <!-- 標題與標語 -->
        <div class="form-title">
            <h1>Forward to Wealth</h1>
            <p>管理財務，輕鬆致富！</p>
        </div>

        <!-- 登入表單 -->
        <div id="login-form" class="${errorSource == 'login' || empty errorSource ? '' : 'hidden'}">
            <h2>登入</h2>
            <form id="login" action="${pageContext.request.contextPath}/login" method="POST">
                <label for="login-username">帳號:</label>
                <input type="text" id="login-username" name="username" value="${username}" required>
                <c:if test="${errorSource == 'login' && error == 'username'}">
                    <span class="error">${errorMessage}</span>
                </c:if>

                <label for="login-password">密碼:</label>
                <input type="password" id="login-password" name="password" required>
                <c:if test="${errorSource == 'login' && error == 'password'}">
                    <span class="error">${errorMessage}</span>
                </c:if>

                <button type="submit">登入</button>
            </form>
            <p class="form-toggle" onclick="toggleForms()">還沒有帳號？點擊註冊</p>
        </div>

        <!-- 註冊表單 -->
        <div id="register-form" class="${errorSource == 'register' ? '' : 'hidden'}">
            <h2>註冊</h2>
            <form id="register" action="${pageContext.request.contextPath}/register" method="POST">
                <label for="register-username">帳號:</label>
                <input type="text" id="register-username" name="username" value="${username}" required>
                <c:if test="${errorSource == 'register' && error == 'username'}">
                    <span class="error">${errorMessage}</span>
                </c:if>

                <label for="register-email">Email:</label>
                <input type="email" id="register-email" name="email" value="${email}" required>
                <c:if test="${errorSource == 'register' && error == 'email'}">
                    <span class="error">${errorMessage}</span>
                </c:if>

                <label for="register-password">密碼:</label>
                <input type="password" id="register-password" name="password" required>
                <c:if test="${errorSource == 'register' && error == 'password'}">
                    <span class="error">${errorMessage}</span>
                </c:if>

                <button type="submit">註冊</button>
            </form>
            <p class="form-toggle" onclick="toggleForms()">已經有帳號？點擊登入</p>
        </div>
    </div>

    <!-- JS for 表單切換 -->
    <script>
        function toggleForms() {
            document.getElementById('login-form').classList.toggle('hidden');
            document.getElementById('register-form').classList.toggle('hidden');
        }
    </script>
</body>

</html>
