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
			<ul class="media-list">
            <c:forEach items="${pageData.getList()}" var="book">
            	<li class="media">
                    <a class="pull-left" href="/book/${book.id}/read.do">
                        <c:choose>
                     	<c:when test="${book.bookCoverImgPath==''||book.bookCoverImgPath==null}">
                        	<img class="media-object" style="width: 100px;;" src="/img/fm_big.gif" alt="${book.description}"/>
					    </c:when>
					    <c:otherwise>
					        <img class="media-object" style="width: 100px;;" src="${book.bookCoverImgPath}" alt="${book.description}"/>
					    </c:otherwise>
                    </c:choose>
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
       </c:if>
        </div>
        
    </div>
    <jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
</html>
