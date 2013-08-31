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
    <script type="text/javascript" src="/js/jquery.scrollUp.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/pages/book/bookReader.js">

    </script>

</head>

<body>
<div class="container">
    <jsp:include page="../../../navigation.jsp"></jsp:include>
    <div class="row-fluid">
        <div class="row-fluid span12">
            <ul class="breadcrumb ">
                <li><a href="/">首页</a> <span class="divider">/</span></li>
                <li><a href="/">书库</a> <span class="divider">/</span></li>
                <li><a href="/book/${book.categoryId}/list.do">${book.category}</a> <span class="divider">/</span></li>
                <li><a href="/book/${book.id}.do">${book.name}</a><span class="divider">/</span></li>
                <li class="active">${chapter.current.title}</li>
            </ul>
            <div class="row-fluid page-header text-center">
                <h1>
                    ${chapter.current.title}
                </h1>
            </div>
        </div>

        <div id="chapter-content" style="width:960px;margin: auto auto;">${chapter.current.content}</div>
        <div class="text-center">
            <canvas id="canvas" width="960"></canvas>

        </div>
        <div class="offset1 span10">
            <c:if test="${chapter.hasPrevious}">
                <a class="pull-left" href="/book/${book.id}/chapter/${chapter.previous.id}.do">上一章</a>
            </c:if>
            <c:if test="${chapter.hasNext}">
                <a class="pull-right" href="/book/${book.id}/chapter/${chapter.next.id}.do">下一章</a>
            </c:if>
        </div>
    </div>
    <a id="scrollUp" title="回到顶部"></a>
    <jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
</html>
