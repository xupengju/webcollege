<%--
  Created by IntelliJ IDEA.
  User: xpj
  Date: 2018/4/5
  Time: 下午3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <!--<link rel="stylesheet/less" type="text/css" href="css/common.less"/>
    <link rel="stylesheet/less" type="text/css" href="css/login.less"/>-->
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script src="js/less.min.js"></script>
</head>
<body>
<div class="content">
    <img class="logo" src="img/logo.png"/>
    <div class="formList">
        <p>用户名：<input type="text"/></p>
        <p>密&nbsp;码：<input type="text"/></p>
        <p>验证码：<input type="text"/><span onselectstart="return false;" id="code"><b>1</b><b>3</b><b>b</b><b>d</b></span></p>
        <a class="btn" href="#">登陆</a>
    </div>
</div>
<script src="js/login.js"></script>
</body>
</html>

