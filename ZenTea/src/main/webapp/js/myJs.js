
function refreshImage() {
    var imageElement = document.getElementById("captchaImage");
    imageElement.src = "captcha.jsp?" + new Date().getTime();
}

function redirectToLogin1() {
    // 获取父级页面的 window 对象
    var parentWindow = window.parent;

    // 修改父级页面的 URL
    parentWindow.location.href = "http://localhost:9001/ZenTea/login.jsp";
}
function redirectToLogin2() {
    var parentWindow = window.parent;
    parentWindow.location.href = "http://localhost:9001/ZenTea/managerlogin.jsp";
}
function redirectToupdateInf() {
    var parentWindow = window.parent;
    parentWindow.location.href = "http://localhost:9001/ZenTea/";
}
function redirectToRegist() {
    var parentWindow = window.parent;
    parentWindow.location.href = "http://localhost:9001/ZenTea/regist.jsp";
}
function redirectToCart() {
    var parentWindow = window.parent;
    parentWindow.location.href = "http://localhost:9001/ZenTea/cart.jsp";
}
function redirectToMyorder() {
    var parentWindow = window.parent;
    parentWindow.location.href = "http://localhost:9001/ZenTea/myorder.jsp";
}