<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">

    </style>
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <jsp:include page="navigation.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span3">
                <div class="well sidebar-nav">
                    <ul class="nav nav-list">
                        <c:forEach items="${classifications}" var="classification">
                            <li><a href="/book/${classification.id}/list.do">${classification.classificationName}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <!--/.well -->
            </div>
            <!--/span-->

            <div class="span9">
                <!-- Main hero unit for a primary marketing message or call to action -->
<!--                 <div class="hero-unit"> -->
<!--                     <h1>欢迎来到在线数字图书馆</h1> -->

<!--                     <p>This is a template for a simple marketing or informational website.It includes a large callout -->
<!--                         called -->
<!--                         the hero unit and three supporting pieces of content.Use it as a starting point to create -->
<!--                         something -->
<!--                         more unique.</p> -->

<!--                     <p><a class="btn btn-primary btn-large">了解更多 &raquo;</a></p> -->
<!--                 </div> -->

                <!-- Example row of columns -->
                <div class='row'>
                    <div class="span4">
                        <h2>点击量排行</h2>

<!--                         <p>各类图书排行</p> -->
                        <ul class="media-list">
				            <c:forEach items="${books}" var="book">
				            	<li class="media">
				                    <a class="pull-left" href="#">
										<c:choose>
                        					<c:when test="${book.bookCoverImgPath==''||book.bookCoverImgPath==null}">
                            					<img class="media-object" style="width: 50px;;" src="/img/fm_big.gif" alt="${book.description}"/>
					                        </c:when>
					                        <c:otherwise>
					                            <img class="media-object" style="width: 50px;;" src="${book.bookCoverImgPath}" alt="${book.description}"/>
					                        </c:otherwise>
                    					</c:choose>
				                    </a>
				                    <div class="media-body">
				                        <h4 class="media-heading"><a href="../book/${book.id}.do">${book.name}</a></h4>
				                        <div class="media">
									                            点击量：${book.clickAmount}
				                        </div>
				                    </div>
				                </li>
				            </c:forEach>
				            </ul>
                        <p><a href="/book/listBooksByClick.do">更多 &raquo;</a></p>
                    </div>
                    <div class="span5 main">
                        <h2>畅销图书排行</h2>
<!--                         <p>Donec id elit non mi porta gravida at eget metus.</p> -->
						<ul class="media-list">
				            <c:forEach items="${books2}" var="book">
				            	<li class="media">
				                    <a class="pull-left" href="#">
				                        <c:choose>
                        					<c:when test="${book.bookCoverImgPath==''||book.bookCoverImgPath==null}">
                            					<img class="media-object" style="width: 50px;;" src="/img/fm_big.gif" alt="${book.description}"/>
					                        </c:when>
					                        <c:otherwise>
					                            <img class="media-object" style="width: 50px;;" src="${book.bookCoverImgPath}" alt="${book.description}"/>
					                        </c:otherwise>
                    					</c:choose>
				                    </a>
				                    <div class="media-body">
				                        <h4 class="media-heading"><a href="../book/${book.id}.do">${book.name}</a></h4>
				                        <div class="media">
									                            销售量：${book.sellAmount}
				                        </div>
				                    </div>
				                </li>
				            </c:forEach>
				            </ul>
                        <p><a href="/book/listBooksBySell.do">更多 &raquo;</a></p>
                    </div>
                    <div class="span5 main">
                        <h2>图书鲜花榜</h2>
<!--                         <p>Donec id elit non mi porta gravida at eget metus.</p> -->
						<ul class="media-list">
				            <c:forEach items="${books3}" var="book">
				            	<li class="media">
				                    <a class="pull-left" href="#">
<%-- 				                        <img class="media-object" style="width: 50px;;" src="../../img/cover.jpg" alt="${book.description}"> --%>
										<c:choose>
                        					<c:when test="${book.bookCoverImgPath==''||book.bookCoverImgPath==null}">
                            					<img class="media-object" style="width: 50px;;" src="/img/fm_big.gif" alt="${book.description}"/>
					                        </c:when>
					                        <c:otherwise>
					                            <img class="media-object" style="width: 50px;;" src="${book.bookCoverImgPath}" alt="${book.description}"/>
					                        </c:otherwise>
                    					</c:choose>
				                    </a>
				                    <div class="media-body">
				                        <h4 class="media-heading"><a href="../book/${book.id}.do">${book.name}</a></h4>
				                        <div class="media">
									                            鲜花量：${book.userLikeAmount}
				                        </div>
				                    </div>
				                </li>
				            </c:forEach>
				            </ul>
                        <p><a href="/book/listBooksByUserLike.do">更多 &raquo;</a></p>
                    </div>
                </div>

                <hr>
                <jsp:include page="WEB-INF/views/common/foot.jsp"></jsp:include>
            </div>
        </div>
    </div>
    <!-- /container -->

</div>
</body>
</html>
