<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 23.06.2021
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.touragency.model.entity.enums.TourCategory" %>
<%@ page import="com.example.touragency.model.entity.enums.TourStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style>
        input[type=text], select, input[type=date]{
            width: 150px;
        }

        fieldset{
            width: 50%;
        }


    </style>
</head>
<body>
<fieldset>
<legend>Please, specify information about tour to insert into the system</legend>
<form action="${pageContext.request.contextPath}/admin/create_tour">
    <table>
        <tr>
            <th>Name:</th>
            <th><input type="text" name="name"/></th>
        </tr>
        <tr>
            <th>Country:</th>
            <th><input type="text" name="country"/></th>
        </tr>
        <tr>
            <th>City:</th>
            <th><input type="text" name="city"/></th>
        </tr>
        <tr>
            <th>Price:</th>
            <th><input type="text" name="price"/></th>
        </tr>
        <tr>
            <th>Hotel:</th>
            <th><input type="text" name="hotelName"/></th>
        </tr>
        <tr>
            <th>Max tickets:</th>
            <th><input type="text" name="maxTickets"/></th>
        </tr>
        <tr>
            <th>Min tickets:</th>
            <th><input type="text" name="minTickets"/></th>
        </tr>
        <tr>
            <th>Start-date:</th>
            <th><input type="date" name="startDate"/></th>
        </tr>
        <tr>
            <th>End-date:</th>
            <th><input type="date" name="endDate"/></th>
        </tr>
        <tr>
            <th>Category:</th>
            <th><select name="category">
                    <option selected="${TourCategory.REST}" value="${TourCategory.REST.id}">${TourCategory.REST}</option>
                    <option value="${TourCategory.EXCURSION.id}">${TourCategory.EXCURSION}</option>
                    <option value="${TourCategory.SHOPPING.id}">${TourCategory.SHOPPING}</option>
            </select></th>
        </tr>
        <tr>
            <th>Status:</th>
            <th><select name="status">
                <option selected="${TourStatus.BURNING.id}" value="${TourStatus.BURNING.id}">${TourStatus.BURNING}</option>
                <option value="${TourStatus.NON_BURNING.id}">${TourStatus.NON_BURNING}</option>
            </select></th>
        </tr>
    </table>
    <br/><br/>

    <input type="submit" value="create"/>
    <br/><br/>

    <span style="color:red">${requestScope.error}</span>

</form>
<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="main"/>
</form>
</fieldset>
</body>
</html>
