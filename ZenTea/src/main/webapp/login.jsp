<%--
  Created by IntelliJ IDEA.
  User: 腾云之帆
  Date: 2023/12/31
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登陆页面</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/Mycss.css">
</head>
<body style="position: fixed">
<div class="blurry-background">
    <div class="logincontainer">
        <form action="login" method="post">
            <input type="hidden" name="loginType" value="user" >
            <div class="loginheader">
                用户登录
            </div>
            <div class="mb-3">
                <label for="username" class="form-label">账号</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="输入手机号或者id" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">密码</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="输入密码" required>
                <p class="error-message">${msg}</p>
            </div>
            <div class="mb-3">
                <label for="validate" class="form-label">验证码</label>
                <input type="text" class="form-control" id="validate" name="validate" placeholder="输入验证码" required
                       style="display: inline;justify-content: space-around;width: 200px !important;">
                <img id="captchaImage" src="captcha.jsp" style="margin-left:46px;height: 38px;" alt="验证码" onclick="refreshImage()">
                <p class="error-message">${msg2}</p>
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1" required>
                <label class="form-check-label" for="exampleCheck1">同意协议</label>
            </div>
            <div>
                <button type="submit" class="btn btn-primary" >登录</button><%--onclick="fun1()"--%>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="js/myJs.js"></script>
</html>
