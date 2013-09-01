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
      <h3>高级搜索</h3>
		
        <div class="row-fluid span11">
        <form class="navbar-form">
    		<span>书名</span><input type="text" class="span2" name="name" value="${name}">
    		<span>书号</span><input type="text" class="span2" name="bookNumber" value="${bookNumber}">
    		<span>作者</span><input type="text" class="span2" name="author" value="${author}"><p>
    		<span>出版社</span><input type="text" class="span2" name="publisher" value="${publisher}">
    		<span>关键词</span><input type="text" class="span2" name="keywords" value="${keywords}">
            <button type="submit" class="btn" formaction="/book/searchBooks.do" >搜索</button>
        </form>
<!--             <ul class="breadcrumb "> -->
<!--                 <li><a href="/">首页</a> <span class="divider">/</span></li> -->
<!--                 <li><a href="/">书库</a></li> -->
<!--             </ul> -->
        </div>
        <div class="row-fluid span12">
		  <c:if test="${!empty pageData.getList() }">
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
                    <td><a href="../${book.id}.do">${book.name}</a></td>
                    <td>${book.author}</td>
                    <td>${book.bookNumber}</td>
                    <td>${book.publisher}</td>
                    <td><img alt="${book.description}" src="../../img/cover.jpg"/></td>
                </tr>
            </c:forEach>
        </table>
        <jsp:include page="../admin/pagination.jsp"></jsp:include>
       </c:if>
        </div>
        
    </div>
    <jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
</html>
