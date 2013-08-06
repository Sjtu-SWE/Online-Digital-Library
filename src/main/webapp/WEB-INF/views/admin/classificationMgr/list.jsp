<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../comomResource.jsp"></jsp:include>
    <script type="text/javascript" src="/js/pages/admin/classificationList.js"></script>
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
        <h1 class="page-title">分类列表</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="/admin/dashboard.do">Home</a> <span class="divider">/</span></li>
        <li class="active">分类列表</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="btn-toolbar">
                <a class=" btn btn-primary" href="/admin/classification/create.do">添加类别</a>
            </div>
            <div id="classification-list">
                <table class="table table-striped table-condensed table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>类别名称</th>
                        <th>备注</th>
                        <th>创建日期</th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach items="${pageData.getList()}" var="classification">
                        <tr>
                            <td>${classification.classificationName}</td>
                            <td>${classification.note}</td>
                            <td>${classification.createDate}</td>
                            <td><a class="btn btn-link" href="./${classification.id}/edit.do">编辑</a>
                                <a class="btn btn-link" id="btn-delete-classification"
                                   href="/admin/classification/${classification.id}/delete.do">删除</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <jsp:include page="../pagination.jsp"></jsp:include>
            <jsp:include page="../foot.jsp"></jsp:include>
        </div>
    </div>

</div>
</body>
</html>