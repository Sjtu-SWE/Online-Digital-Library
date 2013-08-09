KindEditor.ready(function(K) {
    var uploadbutton = K.uploadbutton({
        button : K('#coverUpload')[0],
        fieldName : 'imgFile',
        url : '/kindeditor/fileUpload.do?dir=image',
        afterUpload : function(data) {
            if (data.error === 0) {
                var url = K.formatUrl(data.url, 'absolute');
                K('#bookCoverImgPath').val(url);
            } else {
                alert(data.message);
            }
        },
        afterError : function(str) {
            alert('上传失败: ' + str);
        }
    });
    uploadbutton.fileBox.change(function(e) {
        uploadbutton.submit();
    });
});