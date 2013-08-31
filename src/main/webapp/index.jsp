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
                <div class="hero-unit">
                    <h1>欢迎来到在线数字图书馆</h1>

                    <p>This is a template for a simple marketing or informational website.It includes a large callout
                        called
                        the hero unit and three supporting pieces of content.Use it as a starting point to create
                        something
                        more unique.</p>

                    <p><a class="btn btn-primary btn-large">了解更多 &raquo;</a></p>
                </div>

                <!-- Example row of columns -->
                <div class='row'>
                    <div class="span4">
                        <h2>图书排行</h2>

                        <p>各类图书排行</p>

                        <p><a href="#">View details &raquo;</a></p>
                    </div>
                    <div class="span5 main">
                        <h2>新书上榜</h2>

                        <p>Donec id elit non mi porta gravida at eget metus.</p>

                        <p><a href="#">View details &raquo;</a></p>
                    </div>
                    <div>
                        <h2>图书</h2>

                        <p>Donec id elit non mi porta gravida at eget metus.</p>

                        <p><a href="#">View details &raquo;</a></p>
                    </div>
                </div>

                <hr>
                <footer>
                    <p>@SJTU 2013</p>
                </footer>
            </div>
        </div>
    </div>
    <!-- /container -->
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
</div>
</body>
</html>
