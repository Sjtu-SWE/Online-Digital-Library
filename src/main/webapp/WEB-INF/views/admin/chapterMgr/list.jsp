<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../comomResource.jsp"></jsp:include>
    <script type="text/javascript" src="/js/pages/admin/chapterList.js"></script>
</head>

<!--[if lt IE 7 ]>
<body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]>
<body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]>
<body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]>
<body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
<!--<![endif]-->
<jsp:include page="../navbar.jsp"></jsp:include>

<jsp:include page="../sidebar.jsp"></jsp:include>
<div class="content">
    <div class="header">
        <h1 class="page-title">${book.name} 章节列表</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="/admin/dashboard.do">Home</a> <span class="divider">/</span></li>
        <li><a href="/admin/book/list.do">书籍列表</a> <span class="divider">/</span></li>
        <li class="active">章节列表</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">
                    <a class="btn btn-primary" href="/admin/book/${book.id}/chapter/create.do">创建章节</a>
                </div>
                <div id="book-list">
                    <table class="table table-striped table-condensed table-bordered table-hover">
                        <thead>
                        <tr>
                            <%--<th>#</th>--%>
                                <th>序号</th>
                                <th>章节</th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach items="${pageData.getList()}" var="chapter">
                            <tr>
                                    <%--<td>${book.id}</td>--%>
                                        <td>${chapter.orderNumber}</td>
                                        <td>${chapter.title}</td>
                                <td><a class="btn btn-link" href="./${chapter.id}/edit.do">编辑</a>
                                    <a class="btn btn-link" id="btn-delete-chapter"
                                       href="javascript:if(confirm('确定删除？')){window.location='/admin/book/${book.id}/chapter/${chapter.id}/delete.do';}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <jsp:include page="../pagination.jsp"></jsp:include>
            </div>
        </div>
        <jsp:include page="../foot.jsp"></jsp:include>
    </div>
</div>

</body>
</html>