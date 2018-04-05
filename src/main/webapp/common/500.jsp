<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/4
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page import="java.io.ByteArrayOutputStream,java.io.PrintStream"%>
<%@ page import="java.util.Enumeration" %>
<%response.setStatus(HttpServletResponse.SC_OK); %>
<html>
<head><title>系统异常</title></head>
<body>
<div class="errortop"><div class="errorfot">
  <div class="errorhead">
    <div class="errorfont pub_f24">对不起，出错啦！</div>
    <a href="${ctx}/" class="errorbtn">返回首页</a>
  </div>
</div></div>


<div  id="errorId">
  程序发生了错误，有可能该页面正在调试或者是设计上的缺陷.

  <hr width=80%/>
  <h2><font color=#DB1260>JSP Error Page</font></h2>
  <% if(exception == null){ return ;}%>
  }
  <p>Host info: <b> <%=request.getLocalAddr()+":"+request.getLocalPort()%></b></p>
  <p>An exception was thrown: <b> <%=exception.getClass()%>:<%=exception.getMessage()%></b></p>
  <%
    System.out.println("Header....");
    Enumeration<String> e = request.getHeaderNames();
    String key;
    while(e.hasMoreElements()){
      key = e.nextElement();
      System.out.println(key+"="+request.getHeader(key));
    }
    System.out.println("Attribute....");
    e = request.getAttributeNames();
    while(e.hasMoreElements()){
      key = e.nextElement();
      System.out.println(key+"="+request.getAttribute(key));
    }

    System.out.println("arameter....");
    e = request.getParameterNames();
    while(e.hasMoreElements()){
      key = e.nextElement();
      System.out.println(key+"="+request.getParameter(key));
    }
  %>
  111<%=request.getAttribute("javax.servlet.forward.request_uri") %>

  <%=request.getAttribute("javax.servlet.forward.servlet_path") %>

  <p>With the following stack trace:</p>
<pre>
<%exception.printStackTrace();
  ByteArrayOutputStream ostr = new ByteArrayOutputStream();
  exception.printStackTrace(new PrintStream(ostr));
  out.print(ostr);
%>
</pre>
  <hr width=80% />
</div>

</body>
</html>