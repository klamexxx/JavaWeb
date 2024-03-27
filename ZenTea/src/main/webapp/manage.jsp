<%--
  Created by IntelliJ IDEA.
  User: 腾云之帆
  Date: 2024/1/2
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>茶叶管理页面</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/Mycss.css">
</head>
<body>
<div class="viewAllTea" id="viewAllTea">
    <div class="search_top">
        <div>
            <form class="d-flex" role="search">
                <input id="input1" class="form-control me-2" type="search" placeholder="按类别搜索" aria-label="Search">
                <button class="btn btn-outline-success" type="button" onclick="fun1()">Search</button>
            </form>
        </div>
        <div>
            <form class="d-flex" role="search">
                <input id="input2" class="form-control me-2" type="search" placeholder="按id搜索" aria-label="Search">
                <button class="btn btn-outline-success" type="button" onclick="fun2()">Search</button>
            </form>
        </div>
        <div>
            <form class="d-flex" role="search">
                <input id="input3" class="form-control me-2" type="search" placeholder="按名字搜索" aria-label="Search">
                <button class="btn btn-outline-success" type="button" onclick="fun3()">Search</button>
            </form>
        </div>
    </div>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th class="col-1"></th>
                <th class="col-1">#</th>
                <th class="col-1">茶叶名</th>
                <th class="col-1">种类</th>
                <th class="col-1">价格(元/kg)</th>
                <th class="col-1">数量(kg)</th>
                <th class="col-2">产出地</th>
                <th class="col-2">茶叶介绍</th>
                <th class="col">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${teaList}" var="tea">
                <tr>
                    <td class="col-1"><img style="width: 100px" src="image/${tea.teaImg}"></td>
                    <td class="col-1">${tea.teaId}</td>
                    <td class="col-1">${tea.teaName}</td>
                    <td class="col-1">${tea.teaCategory}</td>
                    <td class="col-1">${tea.teaPrice}</td>
                    <td class="col-1">${tea.teaNum}</td>
                    <td class="col-2">${tea.teaProduce}</td>
                    <td class="col-2 ">${tea.teaIntroduction}</td>
                    <td class="col-2">
                        <button class="btn btn-info" type="button" data-bs-toggle="offcanvas"
                                data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"
                                data-tea-id="${tea.teaId}">修改
                        </button>

                        <a class="btn btn-danger" href="tea?method=delete&amp;teaId=${tea.teaId}" role="button">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%----%>

<div class="view_orders" id="view_orders" style="display: none">
    <div class="search_top">
        <div>
            <form class="d-flex" role="search">
                <input id="input4" class="form-control me-2" type="search" placeholder="查询订单项" aria-label="Search">
                <button class="btn btn-outline-success" type="button" onclick="fun4()">Search</button>
            </form>
        </div>
    </div>
    <div class="container">
        <div class="table1" style="display: block">
            <table class="table">
                <thead>
                <tr>
                    <th class="col">订单编号</th>
                    <th class="col">购买人</th>
                    <th class="col">收货人</th>
                    <th class="col">电话号码</th>
                    <th class="col">收货地址</th>
                    <th class="col">下单时间</th>
                    <th class="col">预计收获时间</th>
                    <th class="col">送货状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderList}" var="order">
                    <tr>
                        <td class="col">${order.orderId}</td>
                        <td class="col">${order.consumerName}</td>
                        <td class="col">${order.consignee}</td>
                        <td class="col">${order.telNum}</td>
                        <td class="col">${order.address}</td>
                        <td class="col">${order.shippingTime}</td>
                        <c:if test="${order.deliveryTime == null}">
                            <td class="col">未知</td>
                        </c:if>
                        <c:if test="${order.deliveryTime != null}">
                            <td class="col">${order.deliveryTime}</td>
                        </c:if>
                        <td class="col">${order.deliveryStatus}</td>
                        <td class="col">${order.signatureStatus}</td>
                        <td class="col-2">
                            <button class="btn btn-info" type="button" data-bs-toggle="offcanvas"
                                    data-bs-target="#offcanvasRight2" aria-controls="offcanvasRight2"
                                    data-tea-id="${tea.teaId}">修改
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="table2" style="display: none">
            <table class="table">
                <thead>
                <tr>
                    <th class="col">订单编号</th>
                    <th class="col">茶叶编号</th>
                    <th class="col">茶叶名称</th>
                    <th class="col">茶叶价格</th>
                    <th class="col">茶叶数量</th>
                    <th class="col">总和价格</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderItems.teaSet}" var="Item">
                    <tr>
                        <td class="col">${orderItems.orderId}</td>
                        <td class="col">${Item.teaId}</td>
                        <td class="col">${Item.teaName}</td>
                        <td class="col">${Item.teaPrice}</td>
                        <td class="col">${Item.teaNum}</td>
                        <td class="col">${Item.teaNum*Item.teaPrice}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%--                          --%>
<div class="addTea" id="addTea" style="display: none">
    <div class="container">
        <div style="width: 50%">
            <form action="tea?method=addTea" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="teaName" class="form-label">茶叶名</label>
                    <input type="text" class="form-control" id="teaName" name="teaName">
                </div>
                <div class="mb-3">
                    <label for="teaType" class="form-label">种类</label>
                    <input type="text" class="form-control" id="teaType" name="teaType">
                </div>
                <div class="mb-3">
                    <label for="teaPrice" class="form-label">价格</label>
                    <input type="text" class="form-control" id="teaPrice" name="teaPrice">
                </div>
                <div class="mb-3">
                    <label for="teaNum" class="form-label">数量</label>
                    <input type="text" class="form-control" id="teaNum" name="teaNum">
                </div>
                <div class="mb-3">
                    <label for="teaOrigin" class="form-label">产地</label>
                    <input type="text" class="form-control" id="teaOrigin" name="teaOrigin">
                </div>
                <div class="mb-3">
                    <label for="introduce" class="form-label">介绍</label>
                    <input type="text" class="form-control" id="introduce" name="teaIntroduce">
                </div>
                <div class="mb-3">
                    <label for="introduce" class="form-label">添加图片</label>
                    <input type="file" class="form-control" name="teaImg">
                </div>
                <button type="submit" class="btn btn-primary">添加</button>
            </form>
        </div>

    </div>
</div>

<%----%>
<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight"
     aria-labelledby="offcanvasRightLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasRightLabel">表单修改</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                aria-label="Close"></button>

    </div>
    <div class="offcanvas-body">
        <form action="tea?method=updateTea" method="post">
            <div class="mb-3">
                <label for="teaId2" class="form-label">茶叶编号</label>
                <input type="text" class="form-control" id="teaId2" name="teaId">
            </div>
            <div class="mb-3">
                <label for="teaName2" class="form-label">茶叶名</label>
                <input type="text" class="form-control" id="teaName2" name="teaName2">
            </div>
            <div class="mb-3">
                <label for="teaType2" class="form-label">种类</label>
                <input type="text" class="form-control" id="teaType2" name="teaType">
            </div>
            <div class="mb-3">
                <label for="teaPrice2" class="form-label">价格</label>
                <input type="text" class="form-control" id="teaPrice2" name="teaPrice">
            </div>
            <div class="mb-3">
                <label for="teaNum2" class="form-label">数量</label>
                <input type="text" class="form-control" id="teaNum2" name="teaNum">
            </div>
            <div class="mb-3">
                <label for="teaOrigin2" class="form-label">产地</label>
                <input type="text" class="form-control" id="teaOrigin2" name="teaOrigin">
            </div>
            <div class="mb-3">
                <label for="introduce2" class="form-label">介绍</label>
                <input type="text" class="form-control" id="introduce2" name="teaIntroduce">
            </div>
            <button type="submit" class="btn btn-primary">提交</button>
        </form>

    </div>
</div>
<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight2"
     aria-labelledby="offcanvasRightLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasRightLabel2">修改订单信息</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                aria-label="Close"></button>

    </div>
    <div class="offcanvas-body">
        <form action="order?method=updateOrder" method="post">
            <div class="mb-3">
                <label for="orderId" class="form-label">订单编号</label>
                <input type="text" class="form-control" id="orderId" name="orderId">
            </div>
            <div class="mb-3">
                <label for="consignee2" class="form-label">修改收货人为</label>
                <input type="text" class="form-control" id="consignee2" name="consignee">
            </div>
            <div class="mb-3">
                <label for="telNum" class="form-label">修改收货人电话号码为</label>
                <input type="text" class="form-control" id="telNum" name="telNum">
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">修改收货地址为</label>
                <input type="text" class="form-control" id="address" name="address">
            </div>
            <div class="mb-3">
                <label for="deliveryTime" class="form-label">修改送达时间为</label>
                <input type="text" class="form-control" id="deliveryTime" name="deliveryTime">
            </div>
            <div class="mb-3">
                <label for="deliveryStauts" class="form-label">修改送货状态为</label>
                <input type="text" class="form-control" id="deliveryStauts" name="deliveryStauts">
            </div>
            <button type="submit" class="btn btn-primary">提交</button>
        </form>

    </div>
</div>
</body>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script>
    window.addEventListener("load", function() {
        var iframe = window.parent.document.getElementById("iframe1");
        // 获取iframe内部的文档对象
        var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
        var search = iframeDoc.getElementById("search");
        search.innerHTML="";
        var breadcrumb=iframeDoc.getElementById("breadcrumb");
        breadcrumb.innerHTML="";
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "tea?method=queryAll&page=2", true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var response = xhr.responseText;
                var tableStart = response.indexOf("<tbody>");
                var tableEnd = response.indexOf("</tbody>") + "</tbody>".length;
                var tableMarkup = response.substring(tableStart, tableEnd);
                var tbody = document.querySelector("#viewAllTea tbody");
                tbody.innerHTML = tableMarkup;


            }
        };
        xhr.send();
        var xhr2 = new XMLHttpRequest();
        var table1 = document.querySelector('.table1');
        var table2 = document.querySelector('.table2');
        table1.style.display = 'block';
        table2.style.display = 'none';
        xhr2.open("POST", "order?method=listOrders", true);
        xhr2.onreadystatechange = function () {
            if (xhr2.readyState == 4 && xhr2.status == 200) {
                var response = xhr2.responseText;
                var tableStart = response.indexOf("<tbody>", response.indexOf("<tbody>") + 1);
                var tableEnd = response.indexOf("</tbody>", tableStart) + "</tbody>".length;
                var tableMarkup = response.substring(tableStart, tableEnd);
                var tbody = document.querySelector("#view_orders tbody");
                tbody.innerHTML = tableMarkup;
            }
        };
        xhr2.send();
    });

    function fun1() {
        var xhr = new XMLHttpRequest();
        var str = document.getElementById("input1").value;
        xhr.open("POST", "tea?method=queryCatrgory&teaCategory="+str, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                var response = xhr.responseText;
                var tableStart = response.indexOf("<tbody>");
                var tableEnd = response.indexOf("</tbody>") + "</tbody>".length;
                var tableMarkup = response.substring(tableStart, tableEnd);
                var tbody = document.querySelector("#viewAllTea tbody");
                tbody.innerHTML = tableMarkup;
            }
        };
        xhr.send();
    }

    function fun2() {
        var xhr = new XMLHttpRequest();
        var str = document.getElementById("input2").value;
        xhr.open("POST", "tea?method=queryById&teaId="+str, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                var response = xhr.responseText;
                var tableStart = response.indexOf("<tbody>");
                var tableEnd = response.indexOf("</tbody>") + "</tbody>".length;
                var tableMarkup = response.substring(tableStart, tableEnd);
                var tbody = document.querySelector("#viewAllTea tbody");
                tbody.innerHTML = tableMarkup;
            }
        };
        xhr.send();
    }

    function fun3() {
        var xhr = new XMLHttpRequest();
        var str = document.getElementById("input3").value;
        xhr.open("POST", "tea?method=queryByName&teaName="+str, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                var response = xhr.responseText;
                var tableStart = response.indexOf("<tbody>");
                var tableEnd = response.indexOf("</tbody>") + "</tbody>".length;
                var tableMarkup = response.substring(tableStart, tableEnd);
                var tbody = document.querySelector("#viewAllTea tbody");
                tbody.innerHTML = tableMarkup;
            }
        };
        xhr.send();
    }

    function fun4() {
        var table1 = document.querySelector('.table1');
        var table2 = document.querySelector('.table2');
        table1.style.display = 'none'; // 将block包裹在引号中
        table2.style.display = 'block'; // 将none包裹在引号中
        var xhr = new XMLHttpRequest();
        var str = document.getElementById("input4").value;
        xhr.open("POST", "order?method=queryItems&orderId="+str, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                var response = xhr.responseText;
                var tbodyElements = document.getElementsByTagName("tbody");
                var startTag = "<tbody>";
                var endTag = "</tbody>";
                var startIndex = response.indexOf(startTag);
                startIndex = response.indexOf(startTag, startIndex + 1);
                startIndex = response.indexOf(startTag, startIndex + 1);
                var endIndex = response.indexOf(endTag, startIndex) + endTag.length;
                var tableContent = response.substring(startIndex, endIndex);
                var tbody = document.querySelector("#view_orders .table2 tbody");
                console.log(tableContent)
                tbody.innerHTML = tableContent;
            }
        };
        xhr.send();
    }
</script>
</html>
