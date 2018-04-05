<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/4
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>系统异常</title></head>
<body>
<table border="0" width="100%" height="100%">
    <tr align="center" valign="middle">
        <td align="center" valign="middle">
            <h1>对不起您没有该权限，请联系系统管理员。</h1>
        </td>
    </tr>
    <tr>
        <td style="display: none">
            <%
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) { %>
                Authentication object as a String: <%= auth.toString() %>
            <BR/>
            <BR/>
            <% }
            %>
        </td>
    </tr>
</table>

</body>
</html>