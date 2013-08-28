$(function () {
    var DOMURL = self.URL || self.webkitURL || self;
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var con = $("#chapter-content").html();
    var height = $("#chapter-content").height();

    var ignoreLeftClick = function () {
        $("#chapter-content").bind("contextmenu", function () {
            return false;
        });
        $("#chapter-content").bind("selectstart", function () {
            return false;
        });
    };
    if (!$.support.noCloneChecked) {
        $("#canvas").remove();
        ignoreLeftClick();
        return;
    }
    canvas.height = height + (height / 620) * 85;
    var data = "<svg xmlns='http://www.w3.org/2000/svg' width='" + canvas.width + "' height='" + canvas.height + "'>" +
        "<foreignObject width='100%' height='100%'>" +
        "<div xmlns='http://www.w3.org/1999/xhtml' style='font-size:14px;color:#000;line-height:20px;'>" +
        $("#chapter-content").html() +
        "</div>" +
        "</foreignObject>" +
        "</svg>";
    var img = new Image();
    var svg = new Blob([data], {type: "image/svg+xml;charset=utf-8"});
    var url = DOMURL.createObjectURL(svg);
    img.onload = function () {
        ctx.drawImage(img, 0, 0);
        DOMURL.revokeObjectURL(url);
        $("#chapter-content").remove();
    };
    img.src = url;
});