<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 17.06.2021
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>

    <style>
        <jsp:include page="/styles/style.css"/>
    </style>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>
<jsp:include page="/locale_buttons.jsp"/>
<jsp:include page="/profile_header.jsp"/>


<fmt:message key="logout" var="logout"/>
<form action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="${logout}">
</form>

<fmt:message key="show_my_orders" var="show"/>
<form action="${pageContext.request.contextPath}/my_orders">
    <input type="submit" value="${show}"/>
</form>

<br/><br/><br/>

<fmt:message key="main" var="main"/>
<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="${main}">
</form>


<table>
    <tr>
        <th><fmt:message key="manage_users" var="manage_users"/>
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <input type="submit" value="${manage_users}">
            </form></th>
        <th><fmt:message key="manage_orders" var="manage_orders"/>
            <form action="${pageContext.request.contextPath}/admin/manage_orders">
                <input type="submit" value="${manage_orders}"/>
            </form></th>
        <th><fmt:message key="manage_tours" var="manage_tours"/>
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
                <input type="submit" value="${manage_tours}"/>
            </form></th>
    </tr>
</table>






</body>
</html>