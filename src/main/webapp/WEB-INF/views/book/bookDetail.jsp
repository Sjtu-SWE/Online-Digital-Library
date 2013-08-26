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
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript">

    </script>

</head>

<body>
<div class="container">
<jsp:include page="../../../navigation.jsp"></jsp:include>
<div class="row-fluid">
<div class="span12">
<div class="span8">
    <ul class="breadcrumb">
        <li><a href="/">首页</a> <span class="divider">/</span></li>
        <li><a href="/">首页</a> <span class="divider">/</span></li>
        <li><a href="/">${book.book.category}</a> <span class="divider">/</span></li>
        <li><a href="/book/${book.book.id}.do">${book.book.name}</a></li>
    </ul>
    <div class="row-fluid">
        <div class="span2">
            <div class="span2"></div>
            <div class="span10">
                <a>
                    <c:choose>

                        <c:when test="${book.book.bookCoverImgPath==''||book.book.bookCoverImgPath==null}">
                            <img src="/img/fm_big.gif"/>
                        </c:when>
                        <c:otherwise>
                            <img src="${book.book.bookCoverImgPath}">
                        </c:otherwise>

                    </c:choose>
                </a>

                <div class="divider" style="height: 15px;"></div>
                <ul class="nav nav-list">
                    <li><a href="#" class="">点击阅读</a></li>
                    <li><a href="#" class="">加入书架</a></li>
                    <li><a href="#" class="">购买图书</a></li>
                </ul>
            </div>

        </div>
        <div class="span10 popover-title">
            <h4 class="accordion-heading text-center">
                ${book.book.name}
            </h4>

            <div class="offset7">作者：${book.book.author}</div>
            <div class="tabbable">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#tab1" data-toggle="tab">内容介绍</a></li>
                    <li><a href="#tab2" data-toggle="tab">作品信息</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tab1">
                        <p>
                            ${book.book.description}
                        </p>
                    </div>
                    <div class="tab-pane" id="tab2">
                        <div class="span12">
                            <div class="span4">
                                <span>点击次数：</span>${book.book.clickAmount}
                            </div>
                            <div class="span4">
                                <span>作品售价：</span>${book.book.price}
                            </div>
                            <div class="span4">
                                <span>收藏数：</span>${book.book.userFavoriteAmount}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="popover-title">
            <span style="padding: 5px 5px;"> 评论区</span><i class="icon-comment"></i>
        </div>
        <div class="row-fluid">
            <ul class="comment-list">
                <li class="comment-body comment-odd">
                    <div class="comment-author">
                        张三
                    </div>
                    <div class="comment-meta">2010-1-1</div>
                    <p>内容内容</p>
                </li>
                <li class="comment-body comment-even">
                    <div class="comment-author">
                        张三三
                    </div>
                    <div class="comment-meta">2010-1-1</div>
                    <p>内容内容是是</p>
                </li>
            </ul>
            <div class="pagination pagination-centered">
                <ul>
                    <li><a href="#"><</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#"> > </a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="row-fluid" style="height: 15px;">
    </div>
    <div class="row-fluid">
        <form action="" method="post" class="form-horizontal">
            <div class="control-group">
                <span class="control-label">请输入内容：</span>

                <div class="controls">
                    <textarea hidefocus="true" class="span12" style=" height: 98px;;"
                              placeholder="评论内容"></textarea>
                    <button type="submit" class="btn btn-primary">发表评论</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="span4  right-panle">
    <div class="row-fluid">
        <div class="row-fluid">
            <div class="popover-title well-small"><i class="icon-hand-right"></i> 评价</div>
        </div>
        <div class="row-fluid">
            <div class="span10 btn-group span12 text-center">
                <button class="btn"><i class="icon-arrow-up"></i> 送鲜花</button>
                <button class="btn"><i class="icon-arrow-down"></i> 砸鸡蛋</button>
            </div>
        </div>

        <div class="progress progress-success">
            <div class="bar" style="width:${book.getLikeRate()}%;">
                <span>鲜花率 ${book.getLikeRate()} %</span>
            </div>
                <div class="bar bar-warning" style="width:${book.getUnlikeRate()}%;">
                    <span>鸡蛋 ${book.getUnlikeRate()}%</span>
                </div>
        </div>
        <div class="row-fluid">
            <div class="controls-row">
                <span>鲜花数：${book.book.userLikeAmount}</span>
                <span>鸡蛋数：${book.book.userUnlikeAmount}</span>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="row-fluid">
            <div class="popover-title well-small"><i class="icon-book"></i> 推荐图书</div>
        </div>
        <ul class="media-list">
            <li class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" style="width: 64px;;" src="/img/fm_big.gif">
                </a>

                <div class="media-body">
                    <h4 class="media-heading">Media heading</h4>

                    <!-- Nested media object -->
                    <div class="media">
                        adfadsfasdasdfasdf
                    </div>
                </div>
            </li>
            <li class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" style="width: 64px;;" src="/img/fm_big.gif">
                </a>

                <div class="media-body">
                    <h4 class="media-heading">Media heading</h4>

                    <!-- Nested media object -->
                    <div class="media">
                        adfadsfasdasdfasdf
                    </div>
                </div>
            </li>
            <li class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" style="width: 64px;;" src="/img/fm_big.gif">
                </a>

                <div class="media-body">
                    <h4 class="media-heading">Media heading</h4>

                    <!-- Nested media object -->
                    <div class="media">
                        adfadsfasdasdfasdf
                    </div>
                </div>
            </li>
            <li class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" style="width: 64px;;" src="/img/fm_big.gif">
                </a>

                <div class="media-body">
                    <h4 class="media-heading">Media heading</h4>

                    <!-- Nested media object -->
                    <div class="media">
                        adfadsfasdasdfasdf
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
</div>
</div>
<jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
</html>
