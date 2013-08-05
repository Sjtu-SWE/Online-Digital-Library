<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pagination pagination-centered">
    <ul>
        <!-- 分页首页按钮 -->
        <c:choose>
            <c:when test="${pageData.pageIndex <= 1}">
                <li class="disabled"><span>首页</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="?pageIndex=1">首页</a></li>
            </c:otherwise>
        </c:choose>
        <!-- 前一页按钮 -->
        <c:choose>
            <c:when test="${!pageData.hasPreviousPage()}">
                <li class="disabled"><span>前一页</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="?pageIndex=${pageData.pageIndex - 1}">前一页</a></li>
            </c:otherwise>
        </c:choose>
        <!-- 下一页按钮 -->
        <c:choose>
            <c:when test="${!pageData.hasNextPage()}">
                <li class="disabled"><span>下一页</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="?pageIndex=${pageData.pageIndex + 1}">下一页</a></li>
            </c:otherwise>
        </c:choose>
        <!-- 分页尾页按钮 -->
        <c:choose>
            <c:when test="${pageData.pageIndex >= pageData.totalPage}">
                <li class="disabled"><span>尾页</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="?pageIndex=${pageData.totalPage}">尾页</a></li>
            </c:otherwise>
        </c:choose>
        <li class="disabled"><a> 共${pageData.totalCount }条</a></li>
    </ul>
</div>
<script>
    function skipPage() {
        var number = $("#current_page").val().trim();
        location = "?pageIndex=" + number;
    }
</script>
