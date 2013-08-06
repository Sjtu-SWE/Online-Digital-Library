<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar-nav">
    <form class="search form-inline">
        <input type="text" placeholder="Search...">
    </form>
    <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>Dashboard</a>
    <ul id="dashboard-menu" class="nav nav-list collapse in">
        <li><a href="/admin/dashboard.do">Home</a></li>
    </ul>

    <a href="#accounts-menu" class="nav-header" data-toggle="collapse"><i class="icon-user"></i>用户管理</a>
    <ul id="accounts-menu" class="nav nav-list collapse">
        <li><a href="/admin/user/list.do">用户列表</a></li>
    </ul>

    <a href="#books-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-book"></i>书籍管理</a>
    <ul id="books-menu" class="nav nav-list collapse">
        <li><a href="/admin/book/list.do">书籍列表</a></li>
    </ul>

    <a href="#tags-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-tags"></i>分类管理</a>
    <ul id="tags-menu" class="nav nav-list collapse">
        <li><a href="/admin/classification/list.do">分类列表</a></li>
    </ul>
</div>