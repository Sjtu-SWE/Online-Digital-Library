<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线数字图书馆用户重置密码</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</head>
<script src="/js/jquery-1.10.2.js"></script>
<script src="/js/bootstrap.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
// 		$('.error').hide();
		$('.submit').click(function(event){
			var email=$('.email').val();
			
			if(valid_email(email)) {
// 				$('.error').hide();
			} else {
// 				$('.error').show();
				event.preventDefault();
			}
		});
	});
	
	function valid_email(email) {
		var patten = new RegExp(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/);
		return patten.test(email);
	}
</script> 
<body>
<div class="container">
<jsp:include page="navigation.jsp"></jsp:include> 
<form method="post" action="/forgetPassword.do">
<div class="hero-unit">
<h3>重置密码</h3>
<table>
    <tr>
    	<td>邮箱</td>
    	<td><input type="text" class="email" name="email" />
    		<span class="hidden" type="text"><font color="red">请输入邮箱正确格式！</font></span>
		</td></tr>
    <tr><td colSpan="2" align="center">
    		<input type="submit" value="重置密码" class="btn"/>
    	</td></tr>
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