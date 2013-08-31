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
});
