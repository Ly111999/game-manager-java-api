<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Huong Ly
  Date: 7/18/2019
  Time: 10:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp" />
<div class="main">
    <jsp:include page="include/left-bar.jsp"/>
    <div class="main-content">
        <h1>List tube</h1>
        <ul>
            <c:forEach var="game" items="${list}">
                <li><c:out value="${game.name}"</li>
            </c:forEach>
        </ul>
    </div>
</div>
<jsp:include page="include/footer.jsp"/>
