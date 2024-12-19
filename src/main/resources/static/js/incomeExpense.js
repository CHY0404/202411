   $(document).ready(function() {
            const pageContext = "http://localhost:8080";

            // 處理類型選擇
            $('input[name="type"]').change(function() {
                var selectedType = $('input[name="type"]:checked').val();
                $('#recordType').val(selectedType);
            });

            // 初始化 DataTables
            const recordsTable = $('#recordsTable').DataTable({
                ajax: {
                    url: `${pageContext}/wealth/records/json?page=0&size=10`,
                    dataSrc: '' // 直接使用後端返回的 JSON 陣列
                },
                columns: [
                    { 
                        data: 'timestamp', 
                        render: function(data) {
                            return new Date(data).toISOString().split('T')[0]; // 格式化日期
                        }
                    },
                    { 
                        data: 'type', 
                        render: function(data) {
                            return `<span class="${data === 'INCOME' ? 'income' : 'expense'}">${data === 'INCOME' ? '收入' : '支出'}</span>`;
                        }
                    },
                    { data: 'amount' },
                    { data: 'note', defaultContent: '' },
                    { 
                        data: 'id',
                        render: function(data) {
                            return `
                                <button class="btn btn-outline-primary edit-record" data-id="${data}">編輯</button>
                                <button class="btn btn-outline-danger delete-record" data-id="${data}">刪除</button>
                            `;
                        }
                    }
                ],
                language: {
                    url: "https://cdn.datatables.net/plug-ins/1.13.4/i18n/zh-HANT.json"
                },
                responsive: true,
                pageLength: 10,
                lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "全部"]]
            });

            // 提交表單
            $('#recordForm').on('submit', function(e) {
                e.preventDefault();
                var formData = {
                    amount: $('#amount').val(),
                    type: $('input[name="type"]:checked').val(),
                    timestamp: new Date().toISOString().slice(0, 16), // 使用当前时间
                    note: $('#note').val()
                };

                $.ajax({
                    url: `${pageContext}/wealth/records`,
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(formData),
                    success: function(response) {
                        recordsTable.ajax.reload(); // 重新加載表格數據
                    },
                    error: function(xhr, status, error) {
                        console.error('錯誤:', error);
                    }
                });
            });

            // 刪除記錄
            $(document).on('click','.delete-record', function() {
                var recordId = $(this).data('id');
                if (confirm('確定要刪除這條記錄嗎？')) {
                    $.ajax({
                        url: `${pageContext}/wealth/records/${recordId}`,
                        type: 'DELETE',
                        success: function(response) {
                            recordsTable.ajax.reload(); // 重新加載表格數據
                        },
                        error: function(xhr, status, error) {
                            console.error('錯誤:', error);
                        }
                    });
                }
            });

// 用於儲存觸發編輯操作的按鈕
let editTriggerButton = null;

// 編輯記錄
$(document).on('click', '.edit-record', function() {
 // 記錄觸發編輯操作的按鈕
 editTriggerButton = $(this);

 var recordId = $(this).data('id');
 // 開啟模態框
 $('#editModal').modal('show');

 // 加載記錄詳情
 $.ajax({
 url: `${pageContext}/wealth/records/${recordId}`,
 type: 'GET',
 success: function(record) {
 $('#editId').val(record.id);
 $(`#edit${record.type}`).prop('checked', true); // 設定類別選項
 $('#editAmount').val(record.amount);
 $('#editNote').val(record.note);
 },
 error: function(xhr, status, error) {
 console.error('錯誤:', error);
 }
 });
});

// 提交編輯表單
$('#editForm').on('submit', function(e) {
 e.preventDefault();
 var formData = {
 id: $('#editId').val(),
 type: $('input[name="editType"]:checked').val(), // 更新類別
 amount: $('#editAmount').val(),
 note: $('#editNote').val()
 };

 $.ajax({
 url: `${pageContext}/wealth/records/${formData.id}`,
 type: 'PUT',
 contentType: 'application/json',
 data: JSON.stringify(formData),
 success: function(response) {
 console.log(formData);
 recordsTable.ajax.reload(); // 重新載入表格數據

 // 移除模態框內的所有元素的焦點
 $('#editModal').find(':focus').blur();

 // 關閉模態框
 $('#editModal').modal('hide');

 // 恢復焦點到觸發編輯操作的按鈕
 if (editTriggerButton) {
 editTriggerButton.focus();
 // 清空引用以備下次使用
 editTriggerButton = null;
 }
 },
 error: function(xhr, status, error) {
 console.error('錯誤:', error);
 }
 });
});
            // 表單驗證
            $('#recordForm').on('submit', function(event) {
                var amount = parseFloat($('#amount').val());
                if (amount < 0) {
                    $('#amountError').text('金額不可為負數').show();
                    $('#amount').addClass('is-invalid');
                    event.preventDefault(); // 阻止表单提交
                } else {
                    $('#amountError').hide();
                    $('#amount').removeClass('is-invalid');
                }
            });

            $('#editForm').on('submit', function(event) {
                var editAmount = parseFloat($('#editAmount').val());
                if (editAmount < 0) {
                    $('#editAmountError').text('金額不可為負數').show();
                    $('#editAmount').addClass('is-invalid');
                    event.preventDefault(); // 阻止表单提交
                } else {
                    $('#editAmountError').hide();
                    $('#editAmount').removeClass('is-invalid');
                }
            });
        });