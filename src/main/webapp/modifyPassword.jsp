<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线数字图书馆用户修改密码</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
	<style type="text/css">
	</style>
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/bootstrap.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$('.error1').hide();
		$('.error2').hide();
		$('.error3').hide();
		$('.submit').click(function(event){
			var password=$('.password').val();
			var newPassword=$('.newPassword').val();
			var renewPassword=$('.renewPassword').val();
			
			if(valid_password(password)) {
				$('.error1').hide();
			} else {
				$('.error1').show();
				event.preventDefault();
			}
			if(valid_password(newPassword)) {
				$('.error2').hide();
			} else {
				$('.error2').show();
				event.preventDefault();
			}
			if(valid_password(renewPassword)) {
				$('.error3').hide();
			} else {
				$('.error3').show();
				event.preventDefault();
			}
		});
	});
	
	function valid_password(password) {
		var patten = new RegExp(/^[a-zA-Z][0-9a-zA-Z_]{5,7}$/);
		return patten.test(password);
	}
</script> 
<body>
<div class="container">
<jsp:include page="navigation.jsp"></jsp:include> 
<form method="post" action="/savePassword.do">
<div class="hero-unit">
<h3>用户修改密码</h3>
<table class="">
    <tr>
    	<td>原密码</td>
    	<td>
    		<input type="password" class="password" name="password" />
    		<span class="error1"><font color="red">密码必须以字母开头，字符、数字、下划线组成，长度6~8位</font></span>
    	</td></tr>
    <tr>
    	<td>新密码</td>
    	<td>
    		<input type="password" class="newPassword" name="newPassword" />
    		<span class="error2"><font color="red">密码格式不正确！</font></span>
    	</td></tr>
    <tr>
    	<td>确认新密码</td>
    	<td>
    		<input type="password" class="renewPassword" name="renewPassword" />
    		<span class="error3"><font color="red">密码格式不正确！</font></span>
        </td></tr>
    <tr><td colSpan="2" align="center">
    	<input type="submit" value="提交" class="submit"/>
    	&nbsp;<input type="button" onclick="javascript:window.location('/index.do');" value="返回" class="btn"/> </td></tr>
</table>
</div>
</div>

</form>
</body>
<script type="text/javascript">
   if(${!empty param.message})
		alert('${param.message}');
   
</script> 
</html> 