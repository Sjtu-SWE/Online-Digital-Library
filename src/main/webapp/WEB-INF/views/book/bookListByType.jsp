<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sjtu.onlinelibrary.util.SpringSecurityUtils" %>

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
    <script type="text/javascript" src="/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript">

    </script>
</head>

<body>
<div class="container">
    <jsp:include page="../../../navigation.jsp"></jsp:include>
    <div class="row-fluid">
<!--         <div class="page-header text-center"> -->
<%--         	<h3>${category}</a></h3> --%>
<!--             <div class="text-center"></div> -->
<!--         </div> -->
        <div class="row-fluid span11">
            <ul class="breadcrumb ">
                <li><a href="/">首页</a> <span class="divider">/</span></li>
                <li><a href="/">书库</a> <span class="divider">/</span></li>
                <li class="active">${category}</li>
            </ul>
        </div>
        <div class="row-fluid span12">

			<table class="table table-condensed">
            <thead>
            <tr>
                <th>书名</th>
                <th>作者</th>
                <th>书号</th>
                <th>出版社</th>
                <th>封面</th>
            </tr>
            </thead>
            <c:forEach items="${pageData.getList()}" var="book">
                <tr>
                    <td><a href="../${book.id}/read.do">${book.name}</a></td>
                    <td>${book.author}</td>
                    <td>${book.bookNumber}</td>
                    <td>${book.publisher}</td>
                    <td><img alt="${book.description}" src="../../img/cover.jpg"/></td>
                </tr>
            </c:forEach>
        </table>
        </div>
        <jsp:include page="../admin/pagination.jsp"></jsp:include>
    </div>
    <jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
</html>
