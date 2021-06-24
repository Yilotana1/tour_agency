<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 24.06.2021
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.touragency.model.entity.enums.Role" %>
<html>
<head>
    <title>Title</title>

    <style>
        th {
            border: 1px solid black;
        }

        td {
            border: 10px groove black;
        }
    </style>
</head>
<body>

<table>

    <tr>
        <th style="color: red"><b>ID</b></th>
        <th style="color: red"><b>TOUR</b></th>
        <th style="color: red"><b>CREATION DATA</b></th>
        <th style="color: red"><b>PRICE</b></th>
        <th style="color: red"><b>ORDER STATUS</b></th>
    </tr>

    <c:forEach var="order" items="${requestScope.items}">


        <tr>
            <th>${order.id}</th>
            <th>${order.tourName}</th>
            <th>${order.dateFormat}</th>
            <th>${order.price}</th>
            <th>${order.status}</th>
        </tr>
    </c:forEach>
</table>


<br/>
<br/>
<br/>

<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="main">
</form>
<c:if test="${sessionScope.role.equals(Role.ADMIN)}">
    <form action="${pageContext.request.contextPath}/admin/admin_page.jsp">
        <input type="submit" value="profile">
    </form>
</c:if>

<c:if test="${sessionScope.role.equals(Role.CLIENT)}">
    <form action="${pageContext.request.contextPath}/client/client_page.jsp">
        <input type="submit" value="profile">
    </form>
</c:if>

<c:if test="${sessionScope.role.equals(Role.MANAGER)}">
    <form action="${pageContext.request.contextPath}/manager/manager_page.jsp">
        <input type="submit" value="profile">
    </form>
</c:if>


</body>
</html>
