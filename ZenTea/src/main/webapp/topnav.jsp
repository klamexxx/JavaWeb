<%--
  Created by IntelliJ IDEA.
  User: 腾云之帆
  Date: 2023/12/30
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/Mycss.css">
</head>

<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src=""></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/index.jsp">ZenTea</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">商城首页</a>
                </li>
            </ul>
        </div>
        <div class="login_div">
            <div class="logbox">
                <c:choose>
                    <c:when test="${empty sessionScope.username}">

                        <div class="btn-group">
                            <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <a href="#"> 请登录</a>
                            </button>

                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login.jsp" onclick="redirectToLogin1()">用户登录</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/managerlogin.jsp" onclick="redirectToLogin2()">管理员登录</a></li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="btn-group">
                            <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <a href="#">欢迎 ${sessionScope.username}</a>
                            </button>

                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/personal.jsp " onclick="">修改个人信息</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">退出登录</a></li>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="logbox">
                <a href="${pageContext.request.contextPath}/regist.jsp" onclick="redirectToRegist()">注册</a>
            </div>
            <div class="logbox">
                <a href="${pageContext.request.contextPath}/cart.jsp"onclick="redirectToCart()">购物车</a>
            </div>
        </div>


    </div>
</nav>
<!-- 面包屑导航分类 -->
<div class="head_search" >
    <div class="search_left">
        <nav aria-label="breadcrumb" id="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">产品分类</li>
                <li class="breadcrumb-item"><a href="#">绿茶</a></li>
                <li class="breadcrumb-item"><a href="#">红茶</a></li>
                <li class="breadcrumb-item active" aria-current="page">更多分类待上架</li>

            </ol>
        </nav>
    </div>
    <div class="search_right" >
        <form class="d-flex" role="search" id="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>
</div>
<%
    // 获取会话中的账户名
    HttpSession session2 = request.getSession();
    String username = (String) session2.getAttribute("username");
%>
</body>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="js/myJs.js"></script>
</html>