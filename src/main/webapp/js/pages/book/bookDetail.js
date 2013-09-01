$(function () {
    var increasAmount = function (amountType, callback) {
        var request = "bookId=" + $("#bookId").val() + "&amountType=" + amountType;
        $.post("/book/increase.do", request, function (data) {
            if ((typeof callback) == "function") {
                callback();
            }
        });
    };
    increasAmount("clickAmount");
    $("#btn-like").click(function () {
        var like = new Number($("#likeAmount").text()), unlikeAmount = new Number($("#unlikeAmount").text());
        like++;
        increasAmount("userLikeAmount", function () {
            resetBar(like, unlikeAmount);
        });
    });
    $("#btn-unlike").click(function () {
        increasAmount("userUnlikeAmount", function () {
            var like = new Number($("#likeAmount").text()), unlikeAmount = new Number($("#unlikeAmount").text());
            unlikeAmount++;
            resetBar(like, unlikeAmount);

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
    $("#btn-comment-submit").click(function () {
        var $content = $("#comment-content"), $this = $(this);
        if ($content.val().length == 0) {
            commentAlert($this, "评论不能为空")
            return;
        }
        else if ($content.val().length < 10) {
            commentAlert($this, "评论字数不能少于10字");
            return;
        }
        var request = "content=" + $content.val();
        $.post("/book/" + $("#bookId").val() + "/comment/add.do", request, function (data) {
            if (data != "ok") {
                commentAlert($this, "添加评论失败，请重试");
            } else {
                commentAlert($this, "添加评论成功", "success");
                location = location;
            }
        })
    });

});
