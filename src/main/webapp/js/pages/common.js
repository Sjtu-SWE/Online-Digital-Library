$(function () {
    var notLogInAlert = function () {
        modelAlert("请登陆","还未登陆，请登陆","确认");
    };
    $(".unlogined").click(notLogInAlert);
});
function confirmModel(heading, question, cancelButtonTxt, okButtonTxt, callback) {

    var confirmModal =
        $('<div class="modal hide fade">' +
            '<div class="modal-header">' +
            '<a class="close" data-dismiss="modal" >&times;</a>' +
            '<h3>' + heading + '</h3>' +
            '</div>' +

            '<div class="modal-body text-center">' +
            '<p>' + question + '</p>' +
            '</div>' +

            '<div class="modal-footer">' +
            '<a href="#" class="btn" data-dismiss="modal">' +
            cancelButtonTxt +
            '</a>' +
            '<a href="#" id="okButton" class="btn btn-primary">' +
            okButtonTxt +
            '</a>' +
            '</div>' +
            '</div>');

    confirmModal.find('#okButton').click(function (event) {
        callback();
        confirmModal.modal('hide');
    });
    confirmModal.modal('show');
}
function modelAlert(heading, question, okButtonTxt, callback) {

    var alertModel =
        $('<div class="modal hide fade">' +
            '<div class="modal-header">' +
            '<a class="close" data-dismiss="modal" >&times;</a>' +
            '<h3>' + heading + '</h3>' +
            '</div>' +

            '<div class="modal-body text-center">' +
            '<p>' + question + '</p>' +
            '</div>' +

            '<div class="modal-footer">' +
            '<a href="javascript:void(0)" id="okButton" class="btn btn-primary">' +
            okButtonTxt +
            '</a>' +
            '</div>' +
            '</div>');

    alertModel.find('#okButton').click(function (event) {
        if (typeof callback == "function") {
            callback();
        }
        alertModel.modal('hide');
        event.preventDefault();
    });
    alertModel.modal('show');
}