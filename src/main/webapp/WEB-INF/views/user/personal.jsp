<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sjtu.onlinelibrary.util.SpringSecurityUtils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/book.css" rel="stylesheet">
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="container">
    <jsp:include page="../../../navigation.jsp"></jsp:include>
    <div class="row-fluid">
        <div class="row-fluid ">
            <ul class="breadcrumb ">
                <li><a href="/">首页</a> <span class="divider">/</span></li>
                <li class="active">个人主页</li>
            </ul>
        </div>
        <div class="row-fluid ">
            <div class="tabbable block">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#tab1" data-toggle="tab">我的信息</a></li>
                    <li class=""><a href="#tab2" data-toggle="tab">我的消费记录</a></li>
                    <li class=""><a href="#tab3" data-toggle="tab">我的充值记录</a></li>
                </ul>
                <div class="tab-content block-body">
                    <div class="tab-pane active" id="tab1">
                        <form method="post" class="form-horizontal" action="/savePersonal.do">

                            <form:hidden path="user.id"></form:hidden>
                            <div class="control-group">
                                <label class="control-label" for="username">用户名：</label>

                                <div class="controls">
                                    <form:input path="user.username" cssClass="input-xlarge" readonly="true"></form:input>
                                    <form:errors cssClass="error text-error" path="user.username"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="realName">真实名：</label>

                                <div class="controls">
                                    <input type="realName" class="realName" name="realName" value="${user.realName}"/>
                                    <span class="error1"><font color="red">真实名必须是中文。</font></span>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="password">密码：</label>

                                <div class="controls">
                                    <input name="password" type="password" value="${user.password }" readonly="true"/>
                                    <form:errors cssClass="error text-error" path="user.password"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="email">邮箱：</label>

                                <div class="controls">
                                    <input type="email" class="email" name="email" value="${user.email}"/>
                                    <span class="error2"><font color="red">请输入正确的邮箱格式</font></span>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="phone">手机号码：</label>

                                <div class="controls">
                                    <input type="phone" class="phone" name="phone" value="${user.phone }"/>
                                    <span class="error3"><font color="red">请输入正确格式的11位手机号码</font></span>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="credits">信用值：</label>

                                <div class="controls">
                                    <form:input path="user.credits" cssClass="input-xlarge" readonly="true"></form:input>
                                    <form:errors cssClass="error text-error" path="user.credits"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="roleName">角色：</label>

                                <div class="controls">
                                    <form:select path="user.roleName" items="${types}" itemValue="value" itemLabel="name"
                                                 disabled="true"/>
                                </div>
                            </div>
                            <c:if test="${user.id!=null&&!''.equals(user.id)}">
                                <div class="control-group">
                                    <label class="control-label" for="createDate">注册日期：</label>

                                    <div class="controls">
                                        <form:input path="user.createDate" cssClass="input-xlarge" placeholder=""
                                                    readonly="true"></form:input>
                                        <form:errors cssClass="error text-error" path="user.createDate"></form:errors>
                                    </div>
                                </div>
                            </c:if>
                            <div class="control-group">
                                <label class="control-label" for="note">备注：</label>

                                <div class="controls">
                                    <form:input path="user.note" cssClass="input-xlarge"></form:input>
                                </div>
                            </div>
                            <div class="form-actions">
                                <input type="submit" value="更新个人信息" class="btn btn-primary"/>&nbsp;
                                <a class="btn btn-primary recharge" href="javascript:void(0)">充值</a>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="tab2">
                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <th class="span3">书名</th>
                                <th class="span4">作者</th>
                                <th>花费（信用值）</th>
                                <th>购买时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${purchaseRecodes.getList()}" var="purchaseRecode">
                                <tr>
                                    <td>${purchaseRecode.book.name}</td>
                                    <td>${purchaseRecode.book.author}</td>
                                    <td>${purchaseRecode.book.price}</td>
                                    <td><fmt:formatDate value="${purchaseRecode.createdOn}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane" id="tab3">
                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <th class="span2">序列号</th>
                                <th class="span1">面值</th>
                                <th class="span2">充值时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${rechargeRecodes.getList()}" var="rechargeRecode">
                                <tr>
                                    <td>${rechargeRecode.pointCard.id}</td>
                                    <td>${rechargeRecode.pointCard.credits}</td>
                                    <td><fmt:formatDate value="${rechargeRecode.createdOn}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <jsp:include page="../common/foot.jsp"></jsp:include>
</div>
<!-- /container -->
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $('.error1').hide();
        $('.error2').hide();
        $('.error3').hide();
        $('.submit').click(function (event) {
            var realName = $('.realName').val();
            var email = $('.email').val();
            var phone = $('.phone').val();
            if (valid_realName(realName)) {
                $('.error1').hide();
            } else {
                $('.error1').show();
                event.preventDefault();
            }
            if (valid_email(email)) {
                $('.error2').hide();
            } else {
                $('.error2').show();
                event.preventDefault();
            }
            if (valid_phone(phone)) {
                $('.error3').hide();
            } else {
                $('.error3').show();
                event.preventDefault();
            }
        });
    });

    function valid_realName(realName) {
        var patten = new RegExp(/^[\u4e00-\u9fa5]+$/);
        return patten.test(realName);
    }
    function valid_email(email) {
        var patten = new RegExp(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/);
        return patten.test(email);
    }
    function valid_phone(phone) {
        var patten = new RegExp(/^(13+[0-9]{9})|(15+[0-9]{9})|(18+[0-9]{9})$/);
        return patten.test(phone);
    }
    if (${!empty param.message})
        alert('${param.message}');
</script>
</html>
