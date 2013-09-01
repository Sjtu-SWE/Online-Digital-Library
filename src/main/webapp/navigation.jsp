<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="org.springframework.security.core.context.SecurityContextHolder" pageEncoding="UTF-8" %>
<%@ page import="com.sjtu.onlinelibrary.util.SpringSecurityUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container-fluid">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="/">在线数字图书馆</a>

            <div class="nav-collapse collapse">
                <p class="navbar-text pull-right"></p>
                <ul class="nav">
                    <li class="active"><a href="/">首页</a></li>
                    <li class=""><a href="/">书库</a></li>
                    <li class=""><a href="/">我的书架</a></li>
                    <li><a href="/admin/dashboard.do">后台管理</a></li>

                    <form class="navbar-form pull-right">
                        <input type="text" name="name" class="span2" placeholder="书名" value="${name}">
                        <button type="submit" class="btn" formaction="/book/searchBook.do">搜索</button>
                        <a href="/toSearch.do">高级搜索</a>
                    </form>
                </ul>
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-white icon-user"></i> <%
                            String username = SpringSecurityUtils.getCurrentUserName();
                            if (!SpringSecurityUtils.isAuthenticated()) {
                                out.print("游客");
                            } else {
                                out.print(username);
                            }
                        %>
                            <i class="icon-white caret"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <%
                                if (SpringSecurityUtils.isAuthenticated()) {
                                    out.print("<li><a href='/modifyPassword.jsp'>修改密码</a></li>");
                                    out.print("<li><a href='/j_spring_security_logout'>退出</a></li>");
                                }else {
                                    out.print("<li><a href=\"/login.jsp\">登录</a></li>");
                                    out.print(" <li><a href=\"/register.do\">注册</a></li>");
                                }
                            %>
                        </ul>
                    </li>
                </ul>
            </div>
            <!--/.nav-collapse -->

        </div>
    </div>
</div>