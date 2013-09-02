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
  function checkemail()
    {
        var temp = document.getElementById("email");
        var myreg = /(\S)+[@]{1}(\S)+[.]{1}(\w)+/;
        if(temp.value!="")
        {
            if(!myreg.test(temp.value))
            {
                document.getElementById("mail").innerHTML="请输入邮箱正确格式！";
                document.getElementById("mail").style.color="red";
                temp.value="";
                temp.focus();
                return false;
            }
            else{
                document.getElementById("mail").innerHTML="email可以使用";
                document.getElementById("mail").style.color="green";
            }
        }
    }
</script> 
<body>
<div class="container">
<jsp:include page="navigation.jsp"></jsp:include> 
<form method="post" action="/forgetPassword.do" onsubmit="return checkemail()">
<div class="hero-unit">
<h3>重置密码</h3>
<table>
    <tr>
    	<td>邮箱</td>
    	<td>
    		<input id="email" name="email" type="text" />
    		<span id="mail"></span>&nbsp;<br>
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