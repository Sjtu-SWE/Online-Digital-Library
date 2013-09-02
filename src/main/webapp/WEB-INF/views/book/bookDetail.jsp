<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sjtu.onlinelibrary.util.SpringSecurityUtils" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/book.css" rel="stylesheet">
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet">

</head>

<body>
<div class="container">
    <jsp:include page="../../../navigation.jsp"></jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="span8">
                <ul class="breadcrumb">
                    <li><a href="/">首页</a> <span class="divider">/</span></li>
                    <li><a href="/book/bookLibrary.do">书库</a> <span class="divider">/</span></li>
                    <li><a href="/book/${book.book.categoryId}/list.do">${book.book.category}</a> <span class="divider">/</span>
                    </li>
                    <li class="active"> ${book.book.name}</li>
                </ul>
                <div class="row-fluid">
                    <div class="span2">
                        <div class="span2"><input type="hidden" id="bookId" value="${book.book.id}"/>
                        </div>
                        <div class="span10">
                            <a href="/book/${book.book.id}/read.do">
                                <c:choose>
                                    <c:when test="${book.book.bookCoverImgPath==''||book.book.bookCoverImgPath==null}">
                                        <img src="/img/fm_big.gif"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${book.book.bookCoverImgPath}"/>
                                    </c:otherwise>
                                </c:choose>
                            </a>

                            <div class="divider" style="height: 15px;"></div>
                            <ul class="nav nav-list">
                                <li><a href="/book/${book.book.id}/read.do" class="">点击阅读</a></li>
                                <li><a href="javascript:void(0);" class="${loginbtnClass}" id="btn-favorite">加入书架</a>
                                </li>
                                <c:if test="${book.book.price>0}">
                                    <li><a href="javascript:void(0);" class="${loginbtnClass}" id="btn-buy">购买图书</a>
                                    </li>
                                </c:if>
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
                                            <span>作品售价：</span><span id="lbl-credits">${book.book.price}</span> 信用值<c:if
                                                test="${book.book.price==0}">（免费）</c:if>
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
                        <span style="padding: 5px 5px;"> 评论区 (共${pageData.totalCount} 条评论) </span><i
                            class="icon-comment"></i>
                    </div>
                    <div class="row-fluid">
                        <ul class="comment-list">
                            <c:forEach items="${pageData.getList()}" var="comment">
                                <li class="comment-body comment-odd">
                                    <div class="comment-author">
                                            ${comment.userName}
                                    </div>
                                    <div class="comment-meta">
                                            ${comment.getFormatedCreateDate()}
                                    </div>
                                    <p>${comment.content}</p>
                                </li>
                            </c:forEach>
                        </ul>
                        <jsp:include page="../admin/pagination.jsp"></jsp:include>
                    </div>
                </div>

                <div class="row-fluid" style="height: 15px;">
                </div>
                <div class="row-fluid">
                    <div class="form-horizontal">
                        <div class="control-group">
                            <span class="control-label">请输入内容：</span>

                            <div class="controls">
                                <textarea hidefocus="true" id="comment-content" class="span12" style=" height: 98px;;"
                                          placeholder="评论内容"></textarea>
                                <a href="javascript:" id="btn-comment-submit" class="btn btn-primary ${loginbtnClass}">发表评论</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="span4  right-panle">
                <div class="row-fluid">
                    <div class="row-fluid">
                        <div class="popover-title well-small"><i class="icon-hand-right"></i> 评价</div>
                    </div>
                    <div class="row-fluid">
                        <div class="span10 btn-group span12 text-center">
                            <button class="btn ${loginbtnClass}" id="btn-like"
                                    data-like-amount="${book.book.userLikeAmount}"><i
                                    class="icon-thumbs-up"></i> 送鲜花
                            </button>
                            <button class="btn ${loginbtnClass}" id="btn-unlike"
                                    data-like-amount="${book.book.userUnlikeAmount}"><i
                                    class="icon-thumbs-down"></i> 砸鸡蛋
                            </button>
                        </div>
                    </div>
                    <div class="progress progress-success">

                        <c:choose>
                        <c:when test="${book.getTotalLikeUnlikeAmount()==0}">
                        <div class="bar" id="bar-like" style="width:100%;">
                            </c:when>
                            <c:otherwise>
                            <div class="bar" id="bar-like" style="width:${book.getLikeRate()}%;">
                                </c:otherwise>
                                </c:choose>
                                <span>鲜花率 ${book.getLikeRate()} %</span>
                            </div>
                            <div class="bar bar-warning" id="bar-unlike" style="width:${book.getUnlikeRate()}%;">
                                <span>鸡蛋率 ${book.getUnlikeRate()}%</span>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="controls-row">
                                鲜花数：<span id="likeAmount">${book.book.userLikeAmount}</span>
                                鸡蛋数：<span id="unlikeAmount">${book.book.userUnlikeAmount}</span>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="row-fluid">
                            <div class="popover-title well-small"><i class="icon-book"></i> 推荐图书<a class="pull-right" href="/book/listBooksByRecommend.do"> << 更多 </a></div>
                        </div>
                        <ul class="media-list">
                            <c:forEach items="${recommendBooks}" var="recommendBook">
                                <li class="media">
                                    <a class="pull-left" href="/book/${recommendBook.id}.do">
                                        <c:choose>
                                            <c:when test="${recommendBook.bookCoverImgPath==''||recommendBook.bookCoverImgPath==null}">
                                                <img style="width: 64px;"  src="/img/fm_big.gif"/>
                                            </c:when>
                                            <c:otherwise>
                                                <img style="width: 64px;"  src="${recommendBook.bookCoverImgPath}"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>

                                    <div class="media-body">
                                        <h4 class="media-heading">${recommendBook.name}</h4>
                                        <div class="media">
                                            <p><span class="label label-info">作者：</span> ${recommendBook.author}</p>
                                              <span class="label label-info">简介：</span> ${recommendBook.description}
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../common/foot.jsp"></jsp:include>
    </div>
    <!-- /container -->
        <%
        if (SpringSecurityUtils.isAuthenticated()) {
            out.print("<script type=\"text/javascript\" src=\"/js/pages/book/bookDetail.js\"></script>\"");
        }
    %>
</body>
</html>
