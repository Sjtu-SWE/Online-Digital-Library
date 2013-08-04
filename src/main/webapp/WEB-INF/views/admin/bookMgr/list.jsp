<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../comomResource.jsp"></jsp:include>
    <script type="text/javascript" src="/js/pages/admin/bookList.js"></script>
</head>
<body>
<h2>书籍数量：${pageData.getTotalCount()}</h2>

<div>
    <div id="book-operation">
        <a class="btn btn-primary" href="/admin/book/create.do">创建书籍</a>
    </div>
    <div class="help-inline"></div>
    <div id="book-list">
        <table class="table table-striped table-condensed table-bordered table-hover">
            <thead>
            <tr>
                <%--<th>#</th>--%>
                <th>书名</th>
                <th>作者</th>
                <th>售价</th>
                <th>书号</th>
                <th>出版社</th>
                <th>出版日期</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${pageData.getList()}" var="book">
                <tr>
                        <%--<td>${book.id}</td>--%>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.price}</td>
                    <td>${book.bookNumber}</td>
                    <td>${book.publisher}</td>
                    <td>${book.publishDate}</td>
                    <td><a class="btn btn-link" href="./${book.id}/edit.do">编辑</a>
                        <a class="btn btn-link" id="btn-delete-book" href="/admin/book/${book.id}/delete.do">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <jsp:include page="../pagination.jsp"></jsp:include>
</div>

</body>
</html>