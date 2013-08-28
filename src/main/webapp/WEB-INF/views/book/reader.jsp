<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sjtu.onlinelibrary.util.SpringSecurityUtils" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/book.css" rel="stylesheet">
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/pages/book/bookReader.js">

    </script>

</head>

<body>
<div class="container">
    <jsp:include page="../../../navigation.jsp"></jsp:include>
    <div class="row-fluid">
        <div class="page-header text-center">
            <h1>
                ${chapter.title}
            </h1>
        </div>
        <div class="row-fluid span11">
            <ul class="breadcrumb ">
                <li><a href="/">首页</a> <span class="divider">/</span></li>
                <li><a href="/">书库</a> <span class="divider">/</span></li>
                <li><a href="/">${book.category}</a> <span class="divider">/</span></li>
                <li><a href="/book/${book.id}.do">${book.name}</a><span class="divider">/</span></li>
                <li class="active">${chapter.title}</li>
            </ul>
        </div>
        <div id="chapter-content"  style="width:960px;margin: auto auto;">${chapter.content}</div>
        <div class="text-center">
            <canvas id="canvas" width="960"></canvas>
        </div>
    </div>
    <jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
</html>
