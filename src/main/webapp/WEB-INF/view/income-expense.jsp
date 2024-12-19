<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>收支紀錄</title>

    <!-- 引入 jQuery  -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/income-expense.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">

    <!-- JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>
    <script src="${pageContext.request.contextPath}/js/incomeExpense.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

</head>
<body>
    <%-- 上方工具欄 --%>
    <%@ include file="/WEB-INF/view/fragment/header.jspf" %>

    <div class="d-flex">
        <%-- 左側選單 --%>
        <%@ include file="/WEB-INF/view/fragment/sidebar.jspf" %>

        <%-- 收支紀錄內容 --%>
        <main id="main-content" class="main-content flex-grow-1 p-4">
            <div class="container-fluid">
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-md-10 col-sm-12">
                        <h1 class="h3 mb-3">收支紀錄</h1>

                        <!-- 添加收支記錄表單 -->
                        <form id="recordForm" class="row align-items-center g-2">
                            <!-- 收入/支出類型 -->
                            <div class="col-auto">
                                <label class="form-label">收支建立 <i class="bi bi-pencil"></i></label>
                                <div>
                                    <input type="radio" class="btn-check" name="type" id="income" value="INCOME">
                                    <label class="btn btn-outline-primary" for="income">收入</label>

                                    <input type="radio" class="btn-check" name="type" id="expense" value="EXPENSE">
                                    <label class="btn btn-outline-danger" for="expense">支出</label>
                                </div>
                            </div>

                            <!-- 金額 -->
                            <div class="col-auto">
                                <label for="amount" class="form-label"></label>
                                <input type="number" id="amount" name="amount" class="form-control" placeholder="輸入金額" required>
                                <div id="amountError" class="invalid-feedback">金額不可為負數</div>
                            </div>

                            <!-- 備註 -->
                            <div class="col-auto">
                                <label for="note" class="form-label"></label>
                                <input type="text" id="note" name="note" class="form-control" placeholder="備註">
                            </div>

                            <!-- 提交按鈕 -->
                            <div class="col-auto align-self-end">
                                <button type="submit" class="btn btn-success">送出</button>
                            </div>
                        </form>

                        <!-- 顯示收支記錄表格 -->
                        <table id="recordsTable" class="table table-striped mt-4">
                            <thead>
                                <tr>
                                    <th>日期時間</th>
                                    <th>類別</th>
                                    <th>金額</th>
                                    <th>備註</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <!-- 編輯記錄模態框 -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">編輯收支記錄</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="editForm">
                    <div class="modal-body">
                        <input type="hidden" id="editId" name="id">

                        <!-- 類別選擇 -->
                        <div class="mb-3">
                            <div>
                                <input type="radio" class="btn-check" name="editType" id="editINCOME" value="INCOME">
                                <label class="btn btn-outline-primary" for="editINCOME">收入</label>

                                <input type="radio" class="btn-check" name="editType" id="editEXPENSE" value="EXPENSE">
                                <label class="btn btn-outline-danger" for="editEXPENSE">支出</label>
                            </div>
                        </div>

                        <!-- 金額 -->
                        <div class="mb-3">
                            <label for="editAmount" class="form-label">金額</label>
                            <input type="number" step="1" class="form-control" id="editAmount" name="amount" required>
                            <div id="editAmountError" class="invalid-feedback">金額不可為負數</div>
                        </div>

                        <!-- 備註 -->
                        <div class="mb-3">
                            <label for="editNote" class="form-label">備註</label>
                            <textarea class="form-control" id="editNote" name="note" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">保存更改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>