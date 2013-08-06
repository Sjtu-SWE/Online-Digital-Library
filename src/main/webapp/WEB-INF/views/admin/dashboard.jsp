<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-8-6
  Time: 下午9:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="comomResource.jsp"></jsp:include>
</head>

<!--[if lt IE 7 ]>
<body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]>
<body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]>
<body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]>
<body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
<!--<![endif]-->
<jsp:include page="navbar.jsp"></jsp:include>

<jsp:include page="sidebar.jsp"></jsp:include>
<div class="content">
    <div class="header">
        <div class="stats">
            <p class="stat"><span class="number">53</span>tickets</p>

            <p class="stat"><span class="number">27</span>tasks</p>

            <p class="stat"><span class="number">15</span>waiting</p>
        </div>

        <h1 class="page-title">Dashboard</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="index.html">Home</a> <span class="divider">/</span></li>
        <li class="active">Dashboard</li>
    </ul>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="hero-unit"><h1>欢迎来到在线数字图书馆后台管理系统!</h1></div>
            <jsp:include page="foot.jsp"></jsp:include>
        </div>
    </div>
</div>
</body>
</html>