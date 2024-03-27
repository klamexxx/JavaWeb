
<%--
  Created by IntelliJ IDEA.
  User: 腾云之帆
  Date: 2024/1/1
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>茶叶预览页</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/Mycss.css">

</head>
<body>
<div class="tea_view_container">
    <div class="container">
        <div class="row">
            <h4 class="text-decoration-underline">茶叶预览</h4>
        </div>
        <div class="teaLoader">
            <c:forEach items="${teaList}" var="tea" varStatus="status">
                <c:if test="${(status.index + 1) % 5 == 1 }">
                    <div class="row">
                </c:if>
                <div class="col-6 col-md-4 col-lg-2">
                    <div class="card_container">
                        <div class="card mt-4 border-0" style="width: 100%;">
                            <img src="image/${tea.teaImg}" class="card-img-top" alt="${tea.teaName}">
                            <div class="card-body">
                                <h5 class="card-title">${tea.teaName}</h5>
                                <p class="card-text">${tea.teaPrice}元/kg</p>
                                <a href="cart?method=add&amp;teaId=${tea.teaId}" class="btn btn-primary">加入购物车</a>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${(status.index + 1) % 5 == 0 }">
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
</body>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "tea?method=queryAll&page=1", true);
        xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var response = xhr.responseText;
                var bodystart=response.indexOf("<div class='tea_view_container'>")
                var bodyend=response.indexOf("</div>");
                var bodyinf=response.substring(bodystart,bodyend+6);
                document.querySelector(".teaLoader").innerHTML = bodyinf;
            }
        };
        xhr.send();
    });
</script>
</html>
