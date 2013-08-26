<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="org.springframework.security.core.context.SecurityContextHolder" pageEncoding="UTF-8" %>
<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container-fluid">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">在线数字图书馆</a>

            <div class="nav-collapse collapse">
                <p class="navbar-text pull-right">
                    当前用户
                    <!-- <a href="#" class="navbar-link">Username</a> -->
                    <%
                        String username = SecurityContextHolder.getContext().getAuthentication().getName();
                        if ("anonymousUser".equals(username)) {
                            out.print("游客");
                        } else {
                            out.print(username);
                        }
                    %>
                </p>
                <ul class="nav">
                    <li class="active"><a href="/">首页</a></li>
                    <li><a href="/login.jsp">登录</a></li>
                    <li><a href="/admin/dashboard.do">后台管理</a></li>
                    <li><a href="/register.jsp">注册</a></li>
                    <form class="navbar-form pull-right">
                        <input type="text" class="span2">
                        <button type="submit" class="btn">搜索</button>
                    </form>
                    <li><a href="/j_spring_security_logout">退出</a></li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>