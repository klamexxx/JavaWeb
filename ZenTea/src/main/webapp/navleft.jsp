<%--
  Created by IntelliJ IDEA.
  User: 腾云之帆
  Date: 2024/1/2
  Time: 1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员导航栏</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/Mycss.css">
</head>
<body>
<div class=" bg-light left-nav">
    <!-- 左侧功能导航栏 -->
    <nav class="nav flex-column nav-column">
        <a class="nav-link active" data-bs-toggle="collapse" href="#teaNav">茶叶管理</a>
        <div class="collapse sub-nav" id="teaNav">
            <a class="nav-link" href="#viewAllTea">预览全部</a>
            <a class="nav-link" href="#addTea">上架商品</a>
        </div>
        <a class="nav-link" data-bs-toggle="collapse" href="#orderNav">订单管理</a>
        <div class="collapse sub-nav" id="orderNav">
            <a class="nav-link" href="#listOrders">预览全部订单信息</a>
        </div>
    </nav>
</div>
</body>
<script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="js/myJs.js"></script>
<script type="text/javascript">
    window.onload = function () {
        // 获取左侧导航栏中的所有链接
        var links = document.querySelectorAll(".nav-link");
        var iframe = window.parent.document.getElementById("iframe2");
        // 获取iframe内部的文档对象
        var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;


        var viewAllTea = iframeDoc.getElementById("viewAllTea");
        var addTea = iframeDoc.getElementById("addTea");
        var listOrders = iframeDoc.getElementById("view_orders");
        var searchTop = iframeDoc.querySelector(".search_top");
        // 遍历所有链接，为它们绑定点击事件
        for (var i = 0; i < links.length; i++) {
            links[i].onclick = function () {
                // 获取链接的href属性值
                var href = this.getAttribute("href");
                // 如果链接的href属性值为"#viewAllTea"，则展开或收起"viewAllTea"盒子
                if (href == "#viewAllTea") {
                    if (viewAllTea.style.display == "none") {
                        viewAllTea.style.display = "block";
                        addTea.style.display = "none"; // 隐藏addTea盒子
                        listOrders.style.display = "none"; // 隐藏listOrders盒子
                        searchTop.style.display = "block";
                    }
                }
                // 如果链接的href属性值为"#addTea"，则展开或收起"addTea"盒子
                else if (href == "#addTea") {
                    if (addTea.style.display == "none") {
                        addTea.style.display = "block";
                        viewAllTea.style.display = "none"; // 隐藏viewAllTea盒子
                        listOrders.style.display = "none"; // 隐藏listOrders盒子
                    }
                }
                // 如果链接的href属性值为"#addTea"，则展开或收起"addTea"盒子
                else if (href == "#listOrders") {
                    if (addTea.style.display == "none") {
                        listOrders.style.display = "block"; // 隐藏listOrders盒子
                        addTea.style.display = "none";
                        viewAllTea.style.display = "none"; // 隐藏viewAllTea盒子
                        searchTop.style.display = "none";
                    }
                }

                return false;
            }
        }
    };
</script>
</html>
