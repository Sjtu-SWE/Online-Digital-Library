<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>在线数字图书馆登录</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
	<style type="text/css">
	body{
	padding-top:60px;
	padding-bottom:40px;
	}
	</style>
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</head>  
<body>  
<form method="post" action="/j_spring_security_check">
<div class="container">
<div class="hero-unit">
<table class="">
    <tr><td>姓名</td><td><input id="username" name="j_username" type="text" /></td></tr>
    <tr><td>密码</td><td><input id="password" name="j_password"  type="password" /></td></tr>  
    <tr><td colSpan="2" align="center">
    	<input type="submit" value="提交" class="btn"/><input type="button" onclick="" value="返回" class="btn"/> </td></tr>
</table>
</div>
</div>
</form>  
</body>  
</html> 