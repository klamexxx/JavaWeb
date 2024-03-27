<%--
  Created by IntelliJ IDEA.
  User: 腾云之帆
  Date: 2023/12/31
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/Mycss.css">
</head>
<body>
<div class="blurry-background">
    <div class="logincontainer">
        <form action="login" method="post">
            <input type="hidden" name="loginType" value="regist">
            <div class="loginheader">
                填写注册信息
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">姓名</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="输入姓名" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="pwd" placeholder="输入密码" required>
            </div>
            <div class="mb-3">
                <label for="telNum" class="form-label">手机号</label>
                <input type="text" class="form-control" id="telNum" name="telNum" placeholder="输入手机号" required>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">注册</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
