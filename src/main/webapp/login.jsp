<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>在线数字图书馆登录</title>  
<mce:script type="text/javascript">
<!--   
function turnback(){   
    window.location.href="<%=request.getContextPath() %>";   
}  
// --></mce:script>  
</head>  
<body>  
<form method="post" action="/login.do">
<div></div>  
<table>  
    <tr><td>姓名</td><td><input id="username" name="username" type="text" /></td></tr>  
    <tr><td>密码</td><td><input id="password" name="password"  type="text" /></td></tr>  
    <tr><td colSpan="2" align="center">
    	<input type="submit" value="提交"/><input type="button" onclick="turnback()" value="返回" /> </td></tr>  
</table>  
</form>  
</body>  
</html> 