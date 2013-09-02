$(function () {
    $(".tooltip-shelf").tooltip();
    $(".btn-remove").click(function () {
        var id = $(this).attr("data-id"), $this = $(this);
        confirmModel("我的书架", "确实要将该书从您的书架里移除吗？", "取消", "确认", function () {
            $.post("/book/myBookShelf/" + id + "/delete.do", function (data) {
                setTimeout(function () {
                    $this.parent().parent().remove();
                    modelAlert("我的书架", data.message, "确认");
                }, 500);
            });
        });
    });
});