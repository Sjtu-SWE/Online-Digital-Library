<%--
  User: Crystal
  Date: 13-8-4
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${classification.editType}</title>
    <jsp:include page="../comomResource.jsp"></jsp:include>
</head>
<body>
<div class="span8">
    <fieldset>
        <legend>${classification.editType}</legend>
        <form method="post" class="form-horizontal" action="/admin/classification/save.do">

            <form:hidden path="classification.id"></form:hidden>
            <div class="control-group">
                <label class="control-label" for="classificationName">类别名称：</label>

                <div class="controls">
                    <form:input path="classification.classificationName" cssClass="input-xlarge" placeholder="类别名称：科技"></form:input>
                    <form:errors cssClass="error text-error" path="classification.classificationName"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="createDate">创建日期：</label>
                <div class="controls">
                    <form:input path="classification.createDate" cssClass="input-xlarge" placeholder="创建日期：" readonly="true"></form:input>
                    <form:errors cssClass="error text-error" path="classification.createDate"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="note">备注：</label>

                <div class="controls">
                    <form:input path="classification.note" cssClass="input-xlarge" placeholder="备注：无"></form:input>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">保存</button>
                <a class="btn" href="/admin/classification/list.do">取消</a>
            </div>
        </form>
    </fieldset>

</div>
</body>
</html>