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
                 <jsp:include page="./listSidebar.jsp"></jsp:include>
          <div class="row-fluid span8 block">
        <p class="block-heading" >图书畅销排行榜</p>
            <ul class="block-body media-list">
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
                                                                                                  销售量：${book.sellAmount}<p>
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
