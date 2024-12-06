<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>收支管理系統</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
    <!-- 上方工具列 -->
    <header class="navbar">
        <button id="toggle-sidebar">☰</button>
        <div class="user-menu">
            <span>會員</span>
            <div class="dropdown">
                <a href="${pageContext.request.contextPath}/profile.jsp">會員資料</a>
                <a href="${pageContext.request.contextPath}/logout">登出</a>
            </div>
        </div>
    </header>

    <!-- 左側選單 -->
    <aside class="sidebar">
        <ul>
            <li><a href="${pageContext.request.contextPath}/transaction.jsp">收支管理</a></li>
            <li><a href="${pageContext.request.contextPath}/chart.jsp">圖表分析</a></li>
            <li><a href="${pageContext.request.contextPath}/profile.jsp">會員資料</a></li>
        </ul>
    </aside>

    <!-- 中間內容區 -->
    <main class="content">
        <!-- 這裡會由各個 JSP 的內容覆蓋 -->
        <h1>歡迎使用收支管理系統</h1>
    </main>

    <script>
        // 左側選單收縮功能
        document.getElementById('toggle-sidebar').addEventListener('click', function () {
            document.querySelector('.sidebar').classList.toggle('collapsed');
        });
    </script>
</body>
</html>
