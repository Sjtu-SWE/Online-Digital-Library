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
                    <li><a href="/">首页</a> <span class="divider">/</span></li>
                    <li><a href="/">首页</a> <span class="divider">/</span></li>
                    <li><a href="/">首页</a></li>
                </ul>
                <div class="row-fluid">
                    <div class="span2">
                        <div class="span2"></div>
                        <div class="span10">
                            <img src="/attached/image/20130804/20130804225820_572.jpg"/>

                            <div class="divider" style="height: 15px;"></div>
                            <ul class="nav nav-list">
                                <li><a href="#" class="">点击阅读</a></li>
                                <li><a href="#" class="">加入书架</a></li>
                            </ul>
                        </div>

                    </div>
                    <div class="span10 popover-title">
                        <h4 class="accordion-heading">
                            <center>倚天屠龙记</center>
                        </h4>
                        <div class="offset7">作者：金庸</div>
                        <div class="tabbable">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#tab1" data-toggle="tab">内容介绍</a></li>
                                <li><a href="#tab2" data-toggle="tab">作品信息</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="tab1">
                                    <p>
                                        “春游浩荡，是年年寒食，梨花时节。白锦无纹香烂漫，玉树琼苞堆雪。静夜沉沉，浮光霭霭，冷浸溶溶月。人间天上，烂银霞照通彻。浑似姑射真人，天姿灵秀，意气殊高洁。万蕊参差谁信道，不与群芳同列。浩气清英，仙才卓荦，下土难分别。瑶台归去，洞天方看清绝。”</p>

                                </div>
                                <div class="tab-pane" id="tab2">
                                    <div class="span12">
                                        <div class="span4">
                                            <span>点击次数：</span>1000
                                        </div>
                                        <div class="span4">
                                            <span>作品售价：</span>1000
                                        </div>
                                        <div class="span4">
                                            <span>收藏数：</span>1000
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

                <div class="row-fluid"style="height: 15px;">
                </div>
                <div class="row-fluid">
                    <form action="" method="post" class="form-horizontal">
                        <div class="control-group">
                            <span class="control-label" >请输入内容：</span>

                            <div class="controls">
                                <textarea hidefocus="true" class="span12" style=" height: 98px;;" placeholder="评论内容"></textarea>
                                <button type="submit" class="btn btn-primary">发表评论</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="span4">
                <div class="row-fluid">
                    <div class="popover-title">评价</div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
</html>
