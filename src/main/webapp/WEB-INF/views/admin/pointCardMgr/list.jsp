<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../comomResource.jsp"></jsp:include>
    <script type="text/javascript" src="/js/pages/admin/userList.js"></script>
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
        <h1 class="page-title">点卡维护</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="/admin/dashboard.do">home</a> <span class="divider">/</span></li>
        <li class="active">点卡维护</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="btn-toolbar">
                <a class="btn btn-primary" href="/admin/pointCard/generate.do">点卡生成</a>
            </div>
            <div class="alert alert-important fade in"><button type="button" class="close" data-dismiss="alert">x</button><span>每次生成50张面额为100点的点卡</span></div>

            <div class="help-inline"></div>
            <div id="user-list">

                <table class="table table-striped table-condensed table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>卡号</th>
                        <th>面值</th>
                        <th>是否已经使用</th>
                        <th>生成日期</th>
                    </tr>
                    </thead>
                    <c:forEach items="${pageData.getList()}" var="pointCard">
                        <tr>
                            <td>${pointCard.id}</td>
                            <td>${pointCard.credits}</td>
                            <td>${pointCard.used}</td>
                            <td><fmt:formatDate value="${pointCard.createdOn}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <jsp:include page="../pagination.jsp"></jsp:include>
        </div>
        <jsp:include page="../foot.jsp"></jsp:include>
    </div>
</div>

</body>
</html>