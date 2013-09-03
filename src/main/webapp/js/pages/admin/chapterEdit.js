$(function () {
    var editor = KindEditor.create("textarea[issimplerichedit='true']", {
        resizeType: 0,
        allowPreviewEmoticons: true,
        allowImageUpload: true,
        allowFileManager: false,
        uploadJson: '/kindeditor/fileUpload.do',
        fileManagerJson: '/kindeditor/fileManager.do',
        items: ['source', 'preview','fullscreen', 'plainpaste', 'image', 'multiimage',]
    });
})