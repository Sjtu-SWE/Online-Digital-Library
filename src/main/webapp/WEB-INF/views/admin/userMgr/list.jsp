<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../comomResource.jsp"></jsp:include>
    <script type="text/javascript" src="/js/pages/admin/userList.js"></script>
</head>
<h2>用户管理</h2>
<div>
    <div id="user-operation">
        <a class="btn btn-primary" href="/admin/user/create.do">添加用户</a>
    </div>
    <div class="help-inline"></div>
    <div id="user-list">
        <table class="table table-striped table-condensed table-bordered table-hover">
            <thead>
            <tr>
                <th>用户名</th>
                <th>真实名</th>
                <th>邮箱</th>
                <th>手机号码</th>
                <th>信用值</th>
                <th>最近登录时间</th>
                <th>角色</th>
                <th>创建日期</th>
                <th>修改日期</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${pageData.getList()}" var="user">
                <tr>
                    <td>${user.userName}</td>
                    <td>${user.realName}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.credits}</td>
                    <td><fmt:formatDate value="${user.lastLogonTime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                    <td>${user.roleName}</td>
                    <td><fmt:formatDate value="${user.createDate}" pattern="yyyy/MM/dd"/></td>
                    <td><fmt:formatDate value="${user.updateDate}" pattern="yyyy/MM/dd"/></td>
                    <td><a class="btn btn-link" href="./${user.id}/edit.do">编辑</a>
                        <a class="btn btn-link" id="btn-delete-user" href="/admin/user/${user.id}/delete.do">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <jsp:include page="../pagination.jsp"></jsp:include>
</div>

</body>
</html>