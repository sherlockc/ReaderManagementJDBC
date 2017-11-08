<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-11
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<div align="center">
    <h3>登录</h3>
    <form action="/loginUserServlet" method="get">
        <div>姓名<input type = "text" name = "name"/></div>
        <div>密码<input type = "text" name = "password"/></div>
        <div>
            <button type = "submit">提交</button>
            <button type = "reset">重置</button>
        </div>
    </form>
</div>
</body>
</html>
