<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar">
    <div class="navbar-inner">
        <ul class="nav pull-right">
            <li id="fat-menu" class="dropdown">
                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="icon-user"></i> Jack Smith
                    <i class="icon-caret-down"></i>
                </a>

                <ul class="dropdown-menu">
                    <li><a tabindex="-1" class="visible-phone" href="#">Settings</a></li>
                    <li class="divider visible-phone"></li>
                    <li><a tabindex="-1" href="/j_spring_security_logout">Logout</a></li>
                </ul>
            </li>

        </ul>
        <a class="brand" href="/admin/dashboard.do"><span class="first">在线数字图书馆后台</span> </a>
    </div>
</div>