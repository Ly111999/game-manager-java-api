<%@ page import="com.example.demo.entity.Game" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Huong Ly
  Date: 7/17/2019
  Time: 10:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Game list</title>
</head>
<body>
Item <c:out value="${game.size()}"/>
<ul>
    <c:forEach items="${games}" var="game">
        <li>${game.name} - ${game.price}</li>
    </c:forEach>
</ul>
</body>
</html>
