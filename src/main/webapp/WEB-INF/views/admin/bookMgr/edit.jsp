<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-8-3
  Time: 下午4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${book.editType}</title>
    <jsp:include page="../comomReource.jsp"></jsp:include>
</head>
<body>
<div class="span8">
    <form class="form-horizontal">
        <fieldset>
            <legend>${book.editType}</legend>
            <form:hidden path="book.id"></form:hidden>
            <div class="control-group">
                <label class="control-label" for="name">书名：</label>

                <div class="controls">
                    <form:input path="book.name" cssClass="input-xlarge"></form:input>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="author">作者：</label>

                <div class="controls">
                    <form:input path="book.author" cssClass="input-xlarge"></form:input>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="bookNumber">书号：</label>

                <div class="controls">
                    <form:input path="book.bookNumber" cssClass="input-xlarge"></form:input>

                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="price">售价：</label>

                <div class="controls">
                    <form:input path="book.price" cssClass="input-xlarge"></form:input>

                </div>
            </div>
            <div class="control-group">

                <label class="control-label" for="publisher">出版社：</label>

                <div class="controls">
                    <form:input path="book.publisher" cssClass="input-xlarge"></form:input>

                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="publishDate">出版日期：</label>

                <div class="controls">
                    <form:input path="book.publishDate" cssClass="input-xlarge"></form:input>

                </div>
            </div>
            <div class="control-group ">
                <label class="control-label" for="category">书籍分类：</label>

                <div class="controls">
                    <form:input path="book.category" cssClass="input-xlarge"></form:input>

                </div>
            </div>
            <div class="control-group ">
                <label class="control-label" for="keywords">关键字：</label>

                <div class="controls">
                    <form:input path="book.keywords" cssClass="input-xlarge"></form:input>

                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="selectError">Select with success</label>

                <div class="controls">
                    <select id="selectError">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                    <span class="help-inline">Woohoo!</span>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">保存</button>
                <a class="btn" href="./list.do">取消</a>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>