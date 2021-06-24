<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        th {
            border: 1px solid black;
        }

        td {
            border: 10px groove black;
        }
    </style>
</head>
<body>

<b style="color:red">Information about tour:</b><br/>
<table>
    <tr>
        <th>Name:</th>
        <th>${requestScope.tour.name}</th>
    </tr>
    <tr>
        <th>Country:</th>
        <th>${requestScope.tour.country}</th>
    </tr>
    <tr>
        <th>Category:</th>
        <th>${requestScope.tour.category}</th>
    </tr>
    <tr>
        <th>Start:</th>
        <th>${requestScope.tour.startDateFormat}</th>
    </tr>
    <tr>
        <th>End:</th>
        <th>${requestScope.tour.endDateFormat}</th>
    </tr>

    <tr>
        <th>Price:</th>
        <th>${requestScope.tour.price}</th>
    </tr>
</table>

<br/>
<br/>
<b style="color: red">Information about hotel:</b><br/>

<table>
    <tr>
        <th>Name:</th>
        <th>${requestScope.tour.hotel.name}</th>
    </tr>
    <tr>
        <th>Address:</th>
        <th>${requestScope.tour.hotel.address}</th>
    </tr>
    <tr>
        <th>City:</th>
        <th>${requestScope.tour.hotel.city}</th>
    </tr>
    <tr>
        <th>Stars:</th>
        <th>${requestScope.tour.hotel.stars}</th>
    </tr>
</table>

<br/>
<br/>
<form action="${pageContext.request.contextPath}/order">
    <input type="hidden" name="tourId" value="${requestScope.tour.id}"/>
    <input type="submit" value="order"/>
</form>

<h3 style="color:red">${requestScope.order}</h3>


</body>
</html>
