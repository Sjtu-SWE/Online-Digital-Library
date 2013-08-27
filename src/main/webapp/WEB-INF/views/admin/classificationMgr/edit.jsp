<%--
  User: Crystal
  Date: 13-8-4
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${classification.editType}</title>
    <jsp:include page="../comomResource.jsp"></jsp:include>
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
        <h1 class="page-title">${classification.editType}</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="/admin/book/list.do">类别列表</a><span class="divider">/</span></li>
        <li class="active">${classification.editType}</li>

    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="row-fluid">
                <form method="post" class="form-horizontal" action="/admin/classification/save.do">

                    <form:hidden path="classification.id"></form:hidden>
                    <div class="control-group">
                        <label class="control-label" for="classificationName">类别名称：</label>

                        <div class="controls">
                            <form:input path="classification.classificationName" cssClass="input-xlarge"
                                        placeholder="类别名称：科技"></form:input>
                            <form:errors cssClass="error text-error"
                                         path="classification.classificationName"></form:errors>
                        </div>
                    </div>
                    <c:if test="${classification.id!=null&&!''.equals(classification.id)}">
                        <div class="control-group">
                            <label class="control-label" for="createDate">创建日期：</label>

                            <div class="controls">
                                <form:input path="classification.createDate" cssClass="input-xlarge" placeholder="创建日期："
                                            readonly="true"></form:input>
                                <form:errors cssClass="error text-error" path="classification.createDate"></form:errors>
                            </div>
                        </div>
                    </c:if>
                    <div class="control-group">
                        <label class="control-label" for="note">备注：</label>

                        <div class="controls">
                            <form:input path="classification.note" cssClass="input-xlarge"
                                        placeholder="备注：无"></form:input>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <a class="btn" href="/admin/classification/list.do">取消</a>
                    </div>
                </form>
            </div>
            <jsp:include page="../foot.jsp"></jsp:include>

        </div>
    </div>

</div>
</body>
</html>