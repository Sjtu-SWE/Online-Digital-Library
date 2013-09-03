$(function () {
    var increasAmount = function (amountType, callback) {
        var request = "bookId=" + $("#bookId").val() + "&amountType=" + amountType;
        $.post("/book/increase.do", request, function (data) {
            if ((typeof callback) == "function") {
                callback(data);
            }
        });
    };
    increasAmount("clickAmount");
    $("#btn-like").click(function () {
        increasAmount("userLikeAmount", function (data) {
            if (data.resultStatus == "OK") {
                var like = new Number($("#likeAmount").text()), unlikeAmount = new Number($("#unlikeAmount").text());
                like++;
                resetBar(like, unlikeAmount);
            } else {
                modelAlert("鲜花&鸡蛋", data.message, "确认");
            }
        });
    });
    $("#btn-unlike").click(function () {
        increasAmount("userUnlikeAmount", function (data) {
            if (data.resultStatus == "OK") {
                var like = new Number($("#likeAmount").text()), unlikeAmount = new Number($("#unlikeAmount").text());
                unlikeAmount++;
                resetBar(like, unlikeAmount);
            } else {
                modelAlert("鲜花&鸡蛋", data.message, "确认");
            }

        });
    });

    var resetBar = function (like, unlikeAmount) {
        $("#likeAmount").text(like)
        $("#unlikeAmount").text(unlikeAmount)
        var likerate = (like * 100 / (like + unlikeAmount)).toFixed(2) + "%";
        $("#bar-like").removeAttr("style").attr("style", "width:" + likerate).html($("<span></span>").text("鲜花率 " + likerate));
        var unlikerate = (unlikeAmount * 100 / (like + unlikeAmount)).toFixed(2) + "%";
        $("#bar-unlike").removeAttr("style").attr("style", "width:" + unlikerate).html($("<span></span>").text("鸡蛋率 " + unlikerate));
    };
    var commentAlert = function (submit, message, success) {
        var alert = $('<div class="alert alert-error fade in"><button type="button" class="close" data-dismiss="alert">×</button></div>');

        if ((typeof success) != "undefined") {
            alert.removeClass("alert-error").addClass("alert-success");
        }

        setTimeout(function () {
            alert.find(".close").click();
        }, 5000);
        submit.parent().append(alert.append($("<span>").text(message)));
    };
    $("#btn-comment-submit").click(function (e) {
        var $content = $("#comment-content"), $this = $(this);
        if ($content.val().length == 0) {
            commentAlert($this, "评论不能为空")
            e.pre
            return;
        }
        else if ($content.val().length < 10) {
            commentAlert($this, "评论字数不能少于10字");
            return;
        }
        $this.addClass("disabled").attr("disabled","disabled");
        var request = "content=" + $content.val();
        $.post("/book/" + $("#bookId").val() + "/comment/add.do", request, function (data) {
            if (data != "ok") {
                commentAlert($this, "添加评论失败，请重试");
                $this.removeClass("disabled").removeAttr("disabled","");

            } else {
                commentAlert($this, "添加评论成功", "success");
                setTimeout(function () {
                    $this.removeClass("disabled").removeAttr("disabled","");
                    location = location;
                }, 3000);
            }
        });
    });

    $("#btn-favorite").click(function () {
        $.post("/book/" + $("#bookId").val() + "/addToBookshelf.do", function (data) {
            modelAlert("加入书架", data.message, "确认");
        });
    });
    $("#btn-buy").click(function () {
        confirmModel("图书购买", "确认要购买该书吗，您将花费<span>" + $("#lbl-credits").text() + "</span>信用值", "取消", "确认", function () {
            $.post("/book/" + $("#bookId").val() + "/buy.do", function (data) {
                setTimeout(function () {
                    modelAlert("图书购买", data.message, "确认");
                }, 500);
            });
        });
    });
})
;
