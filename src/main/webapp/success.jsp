<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-8-4
  Time: 上午10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <jsp:include page="/WEB-INF/views/admin/comomResource.jsp"></jsp:include>
    <script type="text/javascript">
        $(function () {
            var sec = 2;
            setInterval(function () {
                sec = sec - 1;

                $("#messageSec").html(sec);
                if (sec == 0) {
                    location = "${url}"
                }
            }, sec * 1000);
        })
    </script>
</head>
<body>
<div class="success">
    <font color="red">${message}</font><span id="messageSec">2</span>秒后跳转到列表页,点击 <a href="${url}">这里</a>
</div>
</body>
</html>