<%@ page language="java" contentType="text/html; charset=UTF-8" import="org.springframework.security.core.context.SecurityContextHolder" pageEncoding="UTF-8"%>
<%@ page import="com.sjtu.onlinelibrary.util.SpringSecurityUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<title>棣栭〉</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<style type='text/css'>

</style>
</head>

<body>
<div class="container">
<jsp:include page="navigation.jsp"></jsp:include>

<div >
<!-- Main hero unit for a primary marketing message or call to action-->
<div class="hero-unit"><h1>
<%
	String username = SpringSecurityUtils.getCurrentUserName();
	out.print(username);
%>
娆㈣繋鏉ュ埌鍦ㄧ嚎鏁板瓧鍥句功棣?</h1>
<p>This is a template for a simple marketing or informational website.It includes a large callout called
 the hero unit and three supporting pieces of content.Use it as a starting point to create something
  more unique.</p>
  <p><a class="btn btn-primary btn-large">浜嗚В鏇村 &raquo;</a></p>
  </div>

  <!-- Example row of columns -->
  <div class='row'>
  <div class="span4">
  <h2>鍥句功鎺掕姒?/h2>
  <p>鍚勭被鍥句功鎺掕</p>
  <p><a href="#">View details &raquo;</a></p>
  </div>
  <div class="span5 main">
  <h2>鏂颁功涓婃</h2>
  <p>Donec id elit non mi porta gravida at eget metus.</p>
  <p><a href="#">View details &raquo;</a></p>
  </div>
  <div>
  <h2>鍥句功妫?储</h2>
  <p>Donec id elit non mi porta gravida at eget metus.</p>
  <p><a href="#">View details &raquo;</a></p>
  </div>
  </div>

  <hr>
  <footer>

  <p>@SJTU 2013</p>
  </footer>
  </div>
  </div><!-- /container -->
</body>
</html>
