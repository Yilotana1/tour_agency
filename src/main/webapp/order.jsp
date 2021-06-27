<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 24.06.2021
  Time: 09:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <jsp:include page="/styles/style.css"/>
    </style>
</head>
<body>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>

<b style="color:red"><fmt:message key="information_about_tour"/></b><br/>
<table>
    <tr>
        <th><fmt:message key="name_1"/></th>
        <th>${requestScope.tour.name}</th>
    </tr>
    <tr>
        <th><fmt:message key="country_1"/></th>
        <th>${requestScope.tour.country}</th>
    </tr>
    <tr>
        <th><fmt:message key="category_1"/></th>
        <th>${requestScope.tour.category}</th>
    </tr>
    <tr>
        <th><fmt:message key="start_1"/></th>
        <th>${requestScope.tour.startDateFormat}</th>
    </tr>
    <tr>
        <th><fmt:message key="end_1"/></th>
        <th>${requestScope.tour.endDateFormat}</th>
    </tr>

    <tr>
        <th><fmt:message key="price_1"/></th>
        <th>${requestScope.tour.price}</th>
    </tr>
</table>

<br/>
<br/>
<b style="color: red"><fmt:message key="information_about_hotel"/></b><br/>

<table>
    <tr>
        <th><fmt:message key="name_1"/></th>
        <th>${requestScope.tour.hotel.name}</th>
    </tr>
    <tr>
        <th><fmt:message key="address_1"/></th>
        <th>${requestScope.tour.hotel.address}</th>
    </tr>
    <tr>
        <th><fmt:message key="city_1"/></th>
        <th>${requestScope.tour.hotel.city}</th>
    </tr>
    <tr>
        <th><fmt:message key="stars_1"/></th>
        <th>${requestScope.tour.hotel.stars}</th>
    </tr>
</table>

<br/>
<br/>
<span style="color: #1bd01b"><fmt:message key="discount_message"/></span>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/order">
    <input type="hidden" name="tourId" value="${requestScope.tour.id}"/>
    <fmt:message key="order" var="order"/>
    <input type="submit" value="${order}"/>
</form>

<h3 style="color:red">${requestScope.order}</h3>

<fmt:message key="main" var="main"/>
<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="${main}">
</form>

</body>
</html>
