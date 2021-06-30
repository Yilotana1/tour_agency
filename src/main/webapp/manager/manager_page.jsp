<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="javatpoint" %>
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
<jsp:include page="/locale_buttons.jsp"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>


<jsp:include page="/profile_header.jsp"/>


<form action="${pageContext.request.contextPath}/logout">
    <fmt:message key="logout" var="logout"/>
    <input type="submit" value="${logout}">
</form>

<fmt:message key="show_my_orders" var="show"/>
<form action="${pageContext.request.contextPath}/my_orders">
    <input type="submit" value="${show}"/>
</form>

<br/><br/><br/>

<span style="color: red"><c:openedOrdersNum/> <fmt:message key="opened_orders"/></span>
<br/>
<table>
    <tr>
        <th><fmt:message key="manage_tours" var="manage_tours"/>
            <form action="${pageContext.request.contextPath}/manager/manage_tours">
                <input type="submit" value="${manage_tours}"/>
            </form>
        </th>

        <th>
            <fmt:message key="manage_orders" var="manage_orders"/>
            <form action="${pageContext.request.contextPath}/manager/manage_orders">
                <input type="submit" value="${manage_orders}"/>
            </form>
        </th>

    </tr>

</table>

<form action="${pageContext.request.contextPath}">
    <fmt:message key="main" var="main"/>
    <input type="submit" value="${main}">
</form>


<br/>


</body>
</html>
