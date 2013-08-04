<%@ page language="java" contentType="text/html; charset=gb2312" pageEncoding="gb2312" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pager">
    <ul>
        <!-- ��ҳ��ҳ��ť -->
        <c:choose>
            <c:when test="${pageData.pageIndex <= 1}">
                <li class="disabled"><span>��ҳ</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="?pageIndex=1">��ҳ</a></li>
            </c:otherwise>
        </c:choose>
        <!-- ǰһҳ��ť -->
        <c:choose>
            <c:when test="${!pageData.hasPreviousPage()}">
                <li class="disabled"><span>ǰһҳ</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="?pageIndex=${pageData.pageIndex - 1}">ǰһҳ</a></li>
            </c:otherwise>
        </c:choose>
        <!-- ��һҳ��ť -->
        <c:choose>
            <c:when test="${!pageData.hasNextPage()}">
                <li class="disabled"><span>��һҳ</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="?pageIndex=${pageData.pageIndex + 1}">��һҳ</a></li>
            </c:otherwise>
        </c:choose>
        <!-- ��ҳβҳ��ť -->
        <c:choose>
            <c:when test="${pageData.pageIndex >= pageData.totalPage}">
                <li class="disabled"><span>βҳ</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="?pageIndex=${pageData.totalPage}">βҳ</a></li>
            </c:otherwise>
        </c:choose>
        <li class="disabled"><a> ��${pageData.totalCount }��</a></li>
    </ul>
</div>
<script>
    function skipPage() {
        var number = $("#current_page").val().trim();
        location = "?pageIndex=" + number;
    }
</script>
