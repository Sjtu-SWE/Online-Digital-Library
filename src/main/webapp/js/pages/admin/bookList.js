$(function () {
    $("#btn-delete-book").click(function () {
        return  confirm("删除后不能恢复，确认要删除吗？");
    });
});