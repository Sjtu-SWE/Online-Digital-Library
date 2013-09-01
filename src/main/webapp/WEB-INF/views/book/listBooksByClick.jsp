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
        <div class="row-fluid span11">
        <h3>点击量排行榜</h3>
        </div>
        <div class="row-fluid span12">
        
			<ul class="media-list">
            <c:forEach items="${pageData.getList()}" var="book">
            	<li class="media">
                    <a class="pull-left" href="#">
                        <img class="media-object" style="width: 100px;;" src="../../img/cover.jpg" alt="${book.description}">
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading"><a href="../book/${book.id}.do">${book.name}</a></h4>
                        <div class="media">
					                            作者：${book.author}<p>
					                            书号：${book.bookNumber}<p>
					                           出版社：${book.publisher}<p>
					                           简介：${book.description}
                        </div>
                    </div>
                </li>
            </c:forEach>
            </ul>
        <jsp:include page="../admin/pagination.jsp"></jsp:include>
        </div>
        
    </div>
    <jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
</html>
