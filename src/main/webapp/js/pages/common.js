$(function(){
    var notLogInAlert = function () {
        alert("还未登陆，请登陆！");
    };
    $(".unlogined").click(notLogInAlert);
    $(".form-date").datepicker({format:"yyyy-mm-dd"})
})