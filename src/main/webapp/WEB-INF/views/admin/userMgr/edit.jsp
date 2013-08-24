<%--
  User: Crystal
  Date: 13-8-5
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${user.editType}</title>
    <jsp:include page="../comomResource.jsp"></jsp:include>
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
        <h1 class="page-title">${user.editType}</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="/admin/user/list.do">书籍列表</a><span class="divider">/</span></li>
        <li class="active">${user.editType}</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="row-fluid">
        <form method="post" class="form-horizontal" action="/admin/user/save.do">

            <form:hidden path="user.id"></form:hidden>
            
            <div class="control-group">
                <label class="control-label" for="username">用户名：</label>
                <div class="controls">
                    <form:input path="user.username" cssClass="input-xlarge" placeholder="用户名：user"></form:input>
                    <form:errors cssClass="error text-error" path="user.username"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="realName">真实名：</label>
                <div class="controls">
                    <form:input path="user.realName" cssClass="input-xlarge" placeholder="真实名：张三"></form:input>
                    <form:errors cssClass="error text-error" path="user.realName"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="password">密码：</label>
                <div class="controls">
                    <form:input path="user.password" cssClass="input-xlarge" placeholder="密码：XXXXXX"></form:input>
                    <form:errors cssClass="error text-error" path="user.password"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="email">邮箱：</label>
                <div class="controls">
                    <form:input path="user.email" cssClass="input-xlarge" placeholder="邮箱：test@yahoo.com"></form:input>
                    <form:errors cssClass="error text-error" path="user.email"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="phone">手机号码：</label>
                <div class="controls">
                    <form:input path="user.phone" cssClass="input-xlarge" placeholder="手机号码：15800009999"></form:input>
                    <form:errors cssClass="error text-error" path="user.phone"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="credits">信用值：</label>
                <div class="controls">
                    <form:input path="user.credits" cssClass="input-xlarge" placeholder="信用值：0"></form:input>
                    <form:errors cssClass="error text-error" path="user.credits"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="roleName">角色：</label>
                <div class="controls">
                    <form:select path="user.roleName" items="${types}" itemValue="value" itemLabel="name"/>
                </div>
            </div>
            <c:if test="${user.id!=null}">
            <div class="control-group">
                <label class="control-label" for="createDate">创建日期：</label>
                <div class="controls">
                    <form:input path="user.createDate" cssClass="input-xlarge" placeholder="创建日期：" readonly="true"></form:input>
                    <form:errors cssClass="error text-error" path="user.createDate"></form:errors>
                </div>
            </div>
            </c:if>
            <div class="control-group">
                <label class="control-label" for="note">备注：</label>
                <div class="controls">
                    <form:input path="user.note" cssClass="input-xlarge" placeholder="备注：无"></form:input>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">保存</button>
                <a class="btn" href="/admin/user/list.do">取消</a>
            </div>
        </form>
        </div>
            <jsp:include page="../foot.jsp"></jsp:include>
     </div>
    </div>

</div>
</body>
</html>