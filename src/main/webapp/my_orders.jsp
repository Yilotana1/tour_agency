<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 24.06.2021
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.example.touragency.model.entity.enums.Role" %>
<html>
<head>
    <title>Title</title>

    <style>
       <jsp:include page="styles/style.css"/>
    </style>
</head>
<body>

<fmt:setLocale value="${sessionScope.locale}"/>
<jsp:include page="html/locale_buttons.html"/>


<fmt:setBundle basename="message"/>

<table>

    <tr>
        <th style="color: red"><b><fmt:message key="tour"/></b></th>
        <th style="color: red"><b><fmt:message key="creation_data"/></b></th>
        <th style="color: red"><b><fmt:message key="price"/></b></th>
        <th style="color: red"><b><fmt:message key="order_status"/></b></th>
    </tr>

    <c:forEach var="order" items="${requestScope.items}">
        <fmt:message key="money_koef" var="koef"/>

        <tr>
            <th>${order.tourName}</th>
            <th>${order.dateFormat}</th>
            <th>${order.price/koef}
                <fmt:message key="money"/> </th>
            <th>${order.status}</th>
        </tr>
    </c:forEach>
</table>


<br/>
<br/>
<br/>

<fmt:message key="main" var="main"/>
<fmt:message key="profile" var="profile"/>

<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="${main}">
</form>
<c:if test="${sessionScope.role.equals(Role.ADMIN)}">
    <form action="${pageContext.request.contextPath}/admin/admin_page.jsp">
        <input type="submit" value="${profile}">
    </form>
</c:if>

<c:if test="${sessionScope.role.equals(Role.CLIENT)}">
    <form action="${pageContext.request.contextPath}/client/client_page.jsp">
        <input type="submit" value="${profile}">
    </form>
</c:if>

<c:if test="${sessionScope.role.equals(Role.MANAGER)}">
    <form action="${pageContext.request.contextPath}/manager/manager_page.jsp">
        <input type="submit" value="${profile}">
    </form>
</c:if>


</body>
</html>
