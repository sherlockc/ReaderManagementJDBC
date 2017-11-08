<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-12
  Time: 08:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list</title>
</head>
<body>
<div align="center"><h3>全部结果列表</h3></div>
<table border = "1" align="center">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>地址</th>
        <th>操作</th>
    </tr>
    <c:forEach var="cstm" items="${pageBean_all.readerlist}">
        <tr>
            <td>${cstm.r_id}</td>
            <td>${cstm.name}</td>
            <td>${cstm.address}</td>

            <td>
                <a href = "/preModifyReaderServlet?r_id=${cstm.r_id}"/>编辑
                <a href = "/deleteReaderServlet?r_id=${cstm.r_id}"/>删除
            </td>
        </tr>
    </c:forEach>
</table>

<a href = "/queryAllReaderServlet?currentPage=1">首页</a>
<a href = "/queryAllReaderServlet?currentPage=${pageBean_all.getPreviousPage()}">上一页</a>
<a href = "/queryAllReaderServlet?currentPage=${pageBean_all.getNextPage()}">下一页</a>
<a href = "/queryAllReaderServlet?currentPage=${pageBean_all.getTotalPage()}">末页</a>



</body>
</html>
