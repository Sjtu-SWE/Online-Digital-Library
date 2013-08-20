<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>在线数字图书馆用户注册</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
	<style type="text/css">
	</style>
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</head>  
<body>
<div class="container">
<jsp:include page="navigation.jsp"></jsp:include> 
<form method="post" action="/register.do">
<div class="hero-unit">
<table class="">
    <tr><td>用户名</td><td><input id="username" name="username" type="text" /></td></tr>
    <tr><td>真实名</td><td><input id="username" name="username" type="text" /></td></tr>
    <tr><td>密码</td><td><input id="password" name="password"  type="password" /></td></tr>
    <tr><td>邮箱</td><td><input id="username" name="username" type="text" /></td></tr>
    <tr><td colSpan="2" align="center">
    	<input type="submit" value="提交" class="btn"/><input type="button" onclick="" value="返回" class="btn"/> </td></tr>
</table>
</div>
</div>
</form>  
</body>  
</html> 