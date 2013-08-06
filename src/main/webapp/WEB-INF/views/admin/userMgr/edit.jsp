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
<body>
<div class="span8">
    <fieldset>
        <legend>${user.editType}</legend>
        <form method="post" class="form-horizontal" action="/admin/user/save.do">

            <form:hidden path="user.id"></form:hidden>
            
            <div class="control-group">
                <label class="control-label" for="userName">用户名：</label>
                <div class="controls">
                    <form:input path="user.userName" cssClass="input-xlarge" placeholder="用户名：user"></form:input>
                    <form:errors cssClass="error text-error" path="user.userName"></form:errors>
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
                    <form:input path="user.roleName" cssClass="input-xlarge" placeholder="角色：普通用户"></form:input>
                    <form:errors cssClass="error text-error" path="user.roleName"></form:errors>
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
    </fieldset>

</div>
</body>
</html>