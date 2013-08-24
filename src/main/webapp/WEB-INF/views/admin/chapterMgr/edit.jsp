<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${book.editType}</title>
    <jsp:include page="../comomResource.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="/js/kindeditor-4.1.7/themes/default/default.css"/>
    <script type="text/javascript" src="/js/kindeditor-4.1.7/kindeditor-min.js"></script>
    <script type="text/javascript" src="/js/kindeditor-4.1.7/lang/zh_CN.js"></script>
    <script type="text/javascript" src="/js/pages/admin/chapterEdit.js"></script>
</head>
<!--[if lt IE 7 ]>
<body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]>
<body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]>
<body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]>
<body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
<!--<![endif]-->
<jsp:include page="../navbar.jsp"></jsp:include>

<jsp:include page="../sidebar.jsp"></jsp:include>
<div class="content">
    <div class="header">
        <h1 class="page-title">${book.name}:${chapter.editType}</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="/admin/book/chapter/${book.id}/list.do">章节列表</a><span class="divider">/</span></li>
        <li class="active">${chapter.editType}</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="row-fluid">
                <form method="post" class="form-horizontal" action="/admin/book/${book.id}/chapter/save.do">
                    <form:hidden path="chapter.id"></form:hidden>
                    <c:if test="${chapter.createdOn !=null}">
                        <form:hidden path="chapter.createdOn"></form:hidden></c:if>
                    <div class="control-group">
                        <label class="control-label" for="title">章节名：</label>

                        <div class="controls">
                            <form:input path="chapter.title" cssClass="input-xlarge"
                                        placeholder="第一章：xxxxxx"></form:input>
                            <form:errors cssClass="error text-error" path="chapter.title"></form:errors>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="content">章节名：</label>

                        <div class="controls">
                            <form:textarea path="chapter.content" cssClass="input-xlarge"
                                           issimplerichedit="true"></form:textarea>
                            <form:errors cssClass="error text-error" path="chapter.content"></form:errors>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <a class="btn" href="/admin/book/${book.id}/chapter/list.do">取消</a>
                    </div>
                </form>
            </div>
            <jsp:include page="../foot.jsp"></jsp:include>
        </div>
    </div>
</div>
</body>
</html>