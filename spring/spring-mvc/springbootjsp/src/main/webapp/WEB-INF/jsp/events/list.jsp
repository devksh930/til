<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: devksh930
  Date: 2020/12/30
  Time: 9:52 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>이벤트 목록</h1>
<h2>${message}</h2>
<table>
    <tr>
        <th>이름</th>
        <th>시작</th>
    </tr>
    <c:forEach items="${events}" var="event">
        <tr>
            <td>${event.name}</td>
            <td>${event.start}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
