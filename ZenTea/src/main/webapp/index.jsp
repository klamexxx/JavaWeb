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
    <title>首页</title>
</head>
<body>
<iframe id="iframe1" src="${pageContext.request.contextPath}/topnav.jsp" name="head"
        style="flex: 0 0 auto; width: 100%; border: none;" scrolling="no"></iframe>
<div style="display: flex; flex-direction: row;height: 100%">
    <c:set var="username" value="${sessionScope.username}"/>
    <c:choose>
        <c:when test="${username eq 'admin'}">
            <jsp:include page="navleft.jsp"/>
            <iframe id="iframe2" src="${pageContext.request.contextPath}/manage.jsp" name="rbody"
                    style="flex: 1 1 auto; width: 100%; border: none; height: 100%;"></iframe>
        </c:when>
        <c:otherwise>
            <iframe id="iframe3" src="${pageContext.request.contextPath}/teabody.jsp" name="rbody"
                    style="flex: 1 1 auto; width: 100%; border: none; height: 100%;"></iframe>
        </c:otherwise>
    </c:choose>
</div>
</body>
<% if (request.getAttribute("msg3") != null) { %>
<script>
    alert("<%= request.getAttribute("msg3") %>");
</script>
<% } %>
</html>