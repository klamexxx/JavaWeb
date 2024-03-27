<%--
  Created by IntelliJ IDEA.
  User: 腾云之帆
  Date: 2024/1/1
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的购物车</title>
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
                            <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                <a href="#"> 请登录</a>
                            </button>

                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login.jsp"
                                       onclick="redirectToLogin1()">用户登录</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/managerlogin.jsp"
                                       onclick="redirectToLogin2()">管理员登录</a></li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="btn-group">
                            <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                <a href="#">欢迎 ${sessionScope.username}</a>
                            </button>

                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/personal.jsp "
                                       onclick="">修改个人信息</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/login">退出登录</a></li>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="logbox">
                <a href="${pageContext.request.contextPath}/regist.jsp" onclick="redirectToRegist()">注册</a>
            </div>
            <div class="logbox">
                <a href="${pageContext.request.contextPath}/cart.jsp" onclick="redirectToCart()">购物车</a>
            </div>
        </div>
    </div>
</nav>

<div class="cart_body" id="cart_body">
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th class="col"></th>
                <th class="col">#</th>
                <th class="col">茶叶名</th>
                <th class="col">价格(元/kg)</th>
                <th class="col">数量(kg)</th>
                <th class="col">总价格</th>
                <th class="col">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${cartList.items}">
                <tr>
                    <td><img style="width: 100px" src="image/${item.teaImg}"></td>
                    <td>${item.teaId}</td>
                    <td>${item.teaName}</td>
                    <td>${item.teaPrice}</td>
                    <td>${item.teaNum}</td>
                    <td>${item.teaNum * item.teaPrice}</td>
                    <td>
                        <a class="btn btn-danger" href="cart?method=remove&amp;teaId=${item.teaId}"
                           role="button">移除购物车</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<footer style="position: fixed; right: 20px; bottom: 20px;margin-right: 220px">
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
        结算
    </button>
</footer>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">填写订单信息</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="order?method=createOrder" method="post">
                    <div class="mb-3">
                        <label for="consumerName" class="form-label">付款人</label>
                        <input type="text" class="form-control" id="consumerName" name="consumerName">
                    </div>
                    <div class="mb-3">
                        <label for="reaciveName" class="form-label">接收人</label>
                        <input type="text" class="form-control" id="reaciveName" name="reaciveName">
                    </div>
                    <div class="mb-3">
                        <label for="telNum" class="form-label">接收人电话号码</label>
                        <input type="text" class="form-control" id="telNum" name="telNum">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">收获地址</label>
                        <input type="text" class="form-control" id="address" name="address">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">提交</button>
                    </div>
                </form>

            </div>
        </div>
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
<script type="text/javascript">
    window.addEventListener("load", function () {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "cart?method=list", true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var response = xhr.responseText;
                var tableStart = response.indexOf("<tbody>");
                var tableEnd = response.indexOf("</tbody>") + "</tbody>".length;
                var tableMarkup = response.substring(tableStart, tableEnd);
                var tbody = document.querySelector("#cart_body tbody");
                tbody.innerHTML = tableMarkup;
            }
        };
        xhr.send();
    });
</script>
</html>
