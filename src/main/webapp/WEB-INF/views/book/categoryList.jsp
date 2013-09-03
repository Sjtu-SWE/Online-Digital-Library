<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span4 block">
    <p class="block-heading">图书分类：</p>
    <div class="block-body sidebar-nav">
        <ul class="nav nav-list">
            <c:forEach items="${classifications}" var="classification">
                <li><a href="/book/${classification.id}/list.do">${classification.classificationName}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>