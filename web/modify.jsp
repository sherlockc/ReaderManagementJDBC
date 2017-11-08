<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-12
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>edit</title>
</head>
<body>
<div align="center">
    <h3>编辑</h3>
    <form action="/modifyReaderServlet" method="post">
        <input type = "text" name = "r_id" value = ${reader.r_id}>
        <div>姓名<input type = "text" name = "name" value = "${reader.name}"/></div>

        <div>地址<input type = "text" name = "address" value = "${reader.address}"/></div>
        <div>
            <button type = "submit">提交</button>
            <button type = "reset">重置</button>
        </div>
    </form>
</div>
</body>
</html>