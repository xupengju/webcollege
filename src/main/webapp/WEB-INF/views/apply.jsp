<%@ page language="java" pageEncoding="utf-8" %>

<html>

<head>

    <tilte>${test}</tilte>

</head>

<body>

<%
    request.setCharacterEncoding("utf-8");
%>

用户名：${username}<br/>

密 码：${password}<br/>

</body>

</html>