<%@ page language="java" contentType="text/html; charset=UTF-8" import="org.springframework.security.core.context.SecurityContextHolder" pageEncoding="UTF-8"%>
<!-- 导航Navigation -->
<!-- <h1>在线数字图书�?/h1> -->
<!-- <div class="navbar navbar-inverse">   -->
<!--    <div class="navbar-inner nav-collapse"  style="height: auto;">   -->
<!--        <ul class="nav">   -->
<!--           <li class="active"><a href="index.jsp">首页</a></li> -->
<!--           <li class="divider-vertical"></li>  -->
<!--           <li><a>我的书架</a></li> -->
<!--           <li class="divider-vertical"></li>  -->
<!--           <li><a>我的账户</a></li> -->
<!--           <li class="divider-vertical"></li> -->
<!--            <li><a href="login.jsp">登录</a></li> -->
<!--            <li><a href="/admin/dashboard.do">后台管理</a></li> -->
<!--           <li class="divider-vertical"></li> -->
<!--           <li><a href="register.jsp">注册</a></li> -->
<!--           <form class="navbar-form pull-right"> -->
<!--           	<input type="text" class="span2"> -->
<!--           	<button type="submit" class="btn">搜索</button> -->
<!--       	  </form> -->
<!--       	  <li class="divider-vertical"></li> -->
<!--           <li><a href="/j_spring_security_logout">�?��</a></li> -->
<!--         </ul> -->
<!--     </div>   -->
<!-- </div> -->

<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">在线数字图书�?/a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              	登录用户�?
             <!-- <a href="#" class="navbar-link">Username</a> -->
              <%
					String username = SecurityContextHolder.getContext().getAuthentication().getName();
					out.print(username);
			  %>
            </p>
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>