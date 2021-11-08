<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kais
  Date: 2021/10/21
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Title</title>
</head>
<body>
<table align="center">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Gender</th>
        <th>Email</th>
        <th>DeptName</th>
    </tr>
    <c:forEach items="${requestScope.employees}" var="employee">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.gender}</td>
            <td>${employee.email}</td>
            <td>${employee.dept.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>