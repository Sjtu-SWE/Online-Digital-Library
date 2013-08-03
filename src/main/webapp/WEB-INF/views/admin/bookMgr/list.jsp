<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../comomReource.jsp"></jsp:include>
    <script type="text/javascript" src="/js/pages/admin/bookList.js"></script>
</head>
<body>
<h2>书籍数量：${books.getTotalCount()}</h2>

<div>
    <div id="book-operation">
        <a class="btn btn-primary" href="/admin/book/create.do">创建书籍</a>
    </div>
    <div class="help-inline"></div>
    <div id="book-list">
        <table class="table table-striped table-condensed table-bordered table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>书名</th>
                <th>作者</th>
                <th>售价</th>
                <th>书号</th>
                <th>出版社</th>
                <th>出版日期</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${books.getList()}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.price}</td>
                    <td>${book.bookNumber}</td>
                    <td>${book.publisher}</td>
                    <td>${book.publishDate}</td>
                    <td><a class="btn-link" herf="/admin/book/${book.id}/edit.do" data-id="${book.id}">编辑</a>
                        <a class="btn-link" herf="void(0)" data-id="${book.id}">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div id="editBook" class="modal hide fade">

    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4>编辑书籍</h4>
    </div>
    <!--Modal header-->
    <div class="modal-body">
        <div class="row">
            <div class="span1"></div>
            <div class="span4 ">
                <h3>求评价(@^_^@) : </h3>
            </div>
            <div class="span4" id="ratingstar"></div>
            <div class="span1" id="target"></div>
        </div>
    </div>
    <!--Modal body-->
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">Close</a>
        <a href="#" class="btn btn-primary">Save changes</a>
    </div>
    <!--Modal footer-->
</div>
<!--Modal-->
</body>
</html>