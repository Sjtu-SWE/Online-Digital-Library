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
    <script type="text/javascript">

    </script>

</head>

<body>
<div class="container">
    <jsp:include page="../../../navigation.jsp"></jsp:include>
    <div class="row-fluid">
        <div class="page-header text-center">
            <h1>
                <a href="/book/${book.id}.do">${book.name}</a>
            </h1>

            <div class="text-center">作者：${book.author}</div>

        </div>
        <div class="row-fluid span11">
            <ul class="breadcrumb ">
                <li><a href="/">首页</a> <span class="divider">/</span></li>
                <li><a href="/">书库</a> <span class="divider">/</span></li>
                <li><a href="/book/${book.categoryId}/list.do">${book.category}</a> <span class="divider">/</span></li>
                <li class="active">${book.name}</li>
            </ul>
        </div>
        <div class="row-fluid span12">

            <ul class="nav nav-pills text-center">
                <c:forEach items="${chapters}" var="chapter"><li class="chapter"><a href="./chapter/${chapter.id}.do">${chapter.title}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
</html>
