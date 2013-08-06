<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../comomResource.jsp"></jsp:include>
    <script type="text/javascript" src="/js/pages/admin/classificationList.js"></script>
</head>
<h2>书籍类别管理</h2>
<div>
    <div id="classification-operation">
        <a class="btn btn-primary" href="/admin/classification/create.do">添加类别</a>
    </div>
    <div class="help-inline"></div>
    <div id="classification-list">
        <table class="table table-striped table-condensed table-bordered table-hover">
            <thead>
            <tr>
                <th>类别名称</th>
                <th>备注</th>
                <th>创建日期</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${pageData.getList()}" var="classification">
                <tr>
                    <td>${classification.classificationName}</td>
                    <td>${classification.note}</td>
                    <td>${classification.createDate}</td>
                    <td><a class="btn btn-link" href="./${classification.id}/edit.do">编辑</a>
                        <a class="btn btn-link" id="btn-delete-classification" href="/admin/classification/${classification.id}/delete.do">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <jsp:include page="../pagination.jsp"></jsp:include>
</div>

</body>
</html>