<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../comomResource.jsp"></jsp:include>
    <script type="text/javascript" src="/js/pages/admin/bookList.js"></script>
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
        <h1 class="page-title">统计列表</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="/admin/dashboard.do">Home</a> <span class="divider">/</span></li>
        <li class="active">统计列表</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">
                </div>
                <div id="book-list">
                    <table class="table table-striped table-condensed table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>用户注册日期</th>
                            <th>用户注册量</th>
                        </tr>
                        </thead>
                        <c:forEach items="${userCount}" var="list">
                            <tr>
                                <td>${list.registerDate}</td>
                                <td>${list.userCount}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="../foot.jsp"></jsp:include>
    </div>
</div>

</body>
</html>