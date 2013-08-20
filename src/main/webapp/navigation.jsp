<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!-- 导航Navigation -->
<h1>在线数字图书馆</h1>
<div class="navbar navbar-inverse">  
   <div class="navbar-inner nav-collapse"  style="height: auto;">  
       <ul class="nav">  
          <li class="active"><a href="index.jsp">首页</a></li>
          <li class="divider-vertical"></li> 
          <li><a>我的书架</a></li>
          <li class="divider-vertical"></li> 
          <li><a>我的账户</a></li>
          <li class="divider-vertical"></li>
           <li><a href="login.jsp">登录</a></li>
           <li><a href="/admin/dashboard.do">后台管理</a></li>
          <li class="divider-vertical"></li>
          <li><a href="register.jsp">注册</a></li>
          <form class="navbar-form pull-right">
          	<input type="text" class="span2">
          	<button type="submit" class="btn">搜索</button>
      	  </form>
      	  <li class="divider-vertical"></li>
          <li><a href="/j_spring_security_logout">退出</a></li>
        </ul>
    </div>  
</div>