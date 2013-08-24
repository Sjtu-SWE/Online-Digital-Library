$(function () {
    var editor = KindEditor.create("textarea[issimplerichedit='true']", {
        resizeType: 0,
        allowPreviewEmoticons: false,
        allowImageUpload: false,
        allowFileManager: false,
        items: [ 'preview','fullscreen']
    });
})