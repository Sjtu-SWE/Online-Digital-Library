<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/book.css" rel="stylesheet">
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
    <script type="text/javascript">

    </script>
</head>

<body>
<div class="container">
    <jsp:include page="../../../navigation.jsp"></jsp:include>
    <div class="row-fluid">
        <div class="row-fluid span11">
            <ul class="breadcrumb ">
                <li><a href="/">首页</a> <span class="divider">/</span></li>
                <li class="active">我的书架</li>
            </ul>
        </div>
        <div class="row-fluid span12">
            <div class="tabbable">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#tab1" data-toggle="tab">我的收藏</a></li>
                    <li class=""><a href="#tab2" data-toggle="tab">已购买书籍</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tab1">
                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <th class="span2"></th>
                                <th class="span3">书名</th>
                                <th class="span4">作者</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${favoriteBooks}" var="favoriteBook">
                                <tr>
                                    <td><a href="/book/${favoriteBook.book.id}.do"> [阅读]</a></td>
                                    <td><a href="/book/${favoriteBook.book.id}.do" class="tooltip-shelf" data-toggle="tooltip" data-placement="right" data-original-title="介绍：${favoriteBook.book.description}"> ${favoriteBook.book.name}</a></td>
                                    <td>${favoriteBook.book.author}</td>
                                    <td><a class="btn-remove" href="javascript:void(0);" data-id="${favoriteBook.id}">从书架中移除</a> </td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane" id="tab2">
                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <th class="span2"></th>
                                <th class="span3">书名</th>
                                <th class="span4">作者</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${purchasedBooks}" var="purchasedBook">
                                <tr>
                                    <td><a href="/book/${purchasedBook.book.id}.do"> [阅读]</a></td>
                                    <td><a href="/book/${purchasedBook.book.id}.do" class="tooltip-shelf" data-toggle="tooltip" data-placement="right" data-original-title="介绍：${purchasedBook.book.description}"> ${purchasedBook.book.name}</a></td>
                                    <td>${purchasedBook.book.author}</td>
                                    <td></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../common/foot.jsp"></jsp:include>
    <script type="text/javascript" src="/js/pages/book/myBookShelf.js"></script>
</div>
<!-- /container -->
</body>
</html>
