<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sjtu.onlinelibrary.util.SpringSecurityUtils" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <meta charset="utf-8">
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
        <div class="row-fluid span11">
        	<ul class="breadcrumb ">
                <li><a href="/">首页</a> <span class="divider">/</span></li>
                <li class="active">个人主页</li>
            </ul>
        </div>
        <div class="row-fluid span12">
			<form method="post" class="form-horizontal" action="/savePersonal.do">

            <form:hidden path="user.id"></form:hidden>
            <div class="control-group">
                <label class="control-label" for="username">用户名：</label>
                <div class="controls">
                    <form:input path="user.username" cssClass="input-xlarge" readonly="true"></form:input>
                    <form:errors cssClass="error text-error" path="user.username"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="realName">真实名：</label>
                <div class="controls">
                    <form:input path="user.realName" cssClass="input-xlarge" placeholder=""></form:input>
                    <form:errors cssClass="error text-error" path="user.realName"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="password">密码：</label>
                <div class="controls">
                    <input name="password" type="password" value="${user.password }" readonly="true"/>
                    <form:errors cssClass="error text-error" path="user.password"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="email">邮箱：</label>
                <div class="controls">
                    <form:input path="user.email" cssClass="input-xlarge" placeholder=""></form:input>
                    <form:errors cssClass="error text-error" path="user.email"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="phone">手机号码：</label>
                <div class="controls">
                    <form:input path="user.phone" cssClass="input-xlarge" placeholder=""></form:input>
                    <form:errors cssClass="error text-error" path="user.phone"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="credits">信用值：</label>
                <div class="controls">
                    <form:input path="user.credits" cssClass="input-xlarge" readonly="true"></form:input>
                    <form:errors cssClass="error text-error" path="user.credits"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="roleName">角色：</label>
                <div class="controls">
                    <form:select path="user.roleName" items="${types}" itemValue="value" itemLabel="name" disabled="true"/>
                </div>
            </div>
            <c:if test="${user.id!=null&&!''.equals(user.id)}">
            <div class="control-group">
                <label class="control-label" for="createDate">注册日期：</label>
                <div class="controls">
                    <form:input path="user.createDate" cssClass="input-xlarge" placeholder="" readonly="true"></form:input>
                    <form:errors cssClass="error text-error" path="user.createDate"></form:errors>
                </div>
            </div>
            </c:if>
            <div class="control-group">
                <label class="control-label" for="note">备注：</label>
                <div class="controls">
                    <form:input path="user.note" cssClass="input-xlarge" ></form:input>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">更新个人信息</button>&nbsp;
                <a class="btn" href="">充值</a>
            </div>
        </form>
        </div>
    </div>
    <jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
</html>
