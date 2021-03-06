<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">

    </style>
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="/css/book.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <jsp:include page="navigation.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row-fluid">
        <div class="row-fluid span4">
            <div class="row-fluid block">
                <p class="block-heading">图书分类：</p>
                <div class="block-body sidebar-nav">
                    <ul class="nav nav-list">
                        <c:forEach items="${classifications}" var="classification">
                            <li><a href="/book/${classification.id}/list.do">${classification.classificationName}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="row-fluid block">
                <p class="block-heading" >各类榜单:</p>
                <div class="block-body sidebar-nav">
                    <ul class="nav nav-list">
                        <li><a  href="/book/listBooksByClick.do">总点击排行</a></li>
                        <li><a  href="/book/listBooksBySell.do">畅销书排行</a></li>
                        <li><a  href="/book/listBooksByUserLike.do">图书鲜花榜</a></li>
                        <li><a  href="/book/listBooksByUserFavorite.do">总收藏排行</a></li>
                        <li><a  href="/book/listBooksByRecommend.do">图书推荐榜</a></li>
                    </ul>
                </div>
            </div>
</div>
            <div class="span8">
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
                <div class='row-fluid'>
                    <div class="span4 block">
                        <p class="block-heading">点击量排行</p>
                        <ul class="block-body media-list">
				            <c:forEach items="${books}" var="book">
				            	<li class="media">
				                    <a class="pull-left" href="/book/${book.id}/read.do"></a>
				                    <div class="media-body">
				                        <h4 class="media-heading"><a href="../book/${book.id}.do">${book.name}</a></h4>
				                        <div class="media">
									                            点击量：${book.clickAmount}
				                        </div>
				                    </div>
				                </li>
				            </c:forEach>
				            </ul>
                        <p><a class="btn btn-link" href="/book/listBooksByClick.do">更多 &raquo;</a></p>
                    </div>
                    <div class="span4 block">
                        <p class="block-heading">畅销图书排行</p>
						<ul class="block-body media-list">
				            <c:forEach items="${books2}" var="book">
				            	<li class="media">
				                    <a class="pull-left" href="/book/${book.id}/read.do">
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
                        <p><a class="btn btn-link" href="/book/listBooksBySell.do">更多 &raquo;</a></p>
                    </div>
                    <div class="span4 block">
                        <p class="block-heading">图书鲜花榜</p>
						<ul class="block-body media-list">
				            <c:forEach items="${books3}" var="book">
				            	<li class="media">
				                    <a class="pull-left" href="/book/${book.id}/read.do">
<%-- 				                        <img class="media-object" style="width: 50px;;" src="../../img/cover.jpg" alt="${book.description}"> --%>
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
                        <p><a class="btn btn-link" href="/book/listBooksByUserLike.do">更多 &raquo;</a></p>
                    </div>
                </div>
				<div class='row-fluid'>
					<div class="span4 block">
					    <p class="block-heading">图书收藏榜</p>
						<ul class="block-body media-list">
				            <c:forEach items="${books4}" var="book">
				            	<li class="media">
				                    <a class="pull-left" href="/book/${book.id}/read.do">
<%-- 				                        <img class="media-object" style="width: 50px;;" src="../../img/cover.jpg" alt="${book.description}"> --%>
				                    </a>
				                    <div class="media-body">
				                        <h4 class="media-heading"><a href="../book/${book.id}.do">${book.name}</a></h4>
				                        <div class="media">
									                            收藏数：${book.userFavoriteAmount}
				                        </div>
				                    </div>
				                </li>
				            </c:forEach>
				            </ul>
                        <p><a class="btn btn-link" href="/book/listBooksByUserFavorite.do">更多 &raquo;</a></p>
                    </div>
                   <div class="span8 block">
                            <p class="block-heading">图书推荐</p>
                            <ul class="block-body media-list">
				            <c:forEach items="${books5}" var="book">
				            	<li class="media">
				                    <a class="pull-left" href="/book/${book.id}/read.do">
<%-- 				                        <img class="media-object" style="width: 50px;;" src="../../img/cover.jpg" alt="${book.description}"> --%>
				                    </a>
				                    <div class="media-body">
				                        <h4 class="media-heading"><a href="../book/${book.id}.do">${book.name}</a></h4>
				                        <div class="media">
				                                                                                                  点击量：${book.clickAmount}
				                                                                                                  销售量：${book.sellAmount}
									                            鲜花量：${book.userLikeAmount}
									                            收藏数：${book.userFavoriteAmount}
				                        </div>
				                    </div>
				                </li>
				            </c:forEach>
				            </ul>
                        <p><a class="btn btn-link" href="/book/listBooksByRecommend.do">更多 &raquo;</a></p>
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
