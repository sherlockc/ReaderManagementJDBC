<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-12
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h3>搜索</h3>
    <form action="/querySelectedReaderServlet" method="get">
        <div>姓名<input type = "text" name = "name"/></div>
        <div>地址<input type = "text" name = "address"/></div>

        <div>
            <button type = "submit">搜索</button>
            <button type = "reset">重置</button>
        </div>
    </form>
</div>
</body>
</body>
</html>
