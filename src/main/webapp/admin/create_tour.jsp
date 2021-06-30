<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <jsp:include page="/styles/style.css"/>

    </style>
</head>
<body>
<jsp:include page="/locale_buttons.jsp"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>


<fieldset>
<legend><fmt:message key="specify_tour_inform"/></legend>
<form action="${pageContext.request.contextPath}/admin/create_tour">
    <table>
        <tr>
            <th><fmt:message key="name_1"/>:</th>
            <th><input type="text" name="name"/></th>
        </tr>
        <tr>
            <th><fmt:message key="country_1"/>:</th>
            <th><input type="text" name="country"/></th>
        </tr>
        <tr>
            <th><fmt:message key="city_1"/>:</th>
            <th><input type="text" name="city"/></th>
        </tr>
        <tr>
            <th><fmt:message key="price_1"/>:</th>
            <th><input type="text" name="price"/></th>
        </tr>
        <tr>
            <th><fmt:message key="hotel_1"/>:</th>
            <th><input type="text" name="hotelName"/></th>
        </tr>
        <tr>
            <th><fmt:message key="max_tickets_1"/>:</th>
            <th><input type="text" name="maxTickets"/></th>
        </tr>
        <tr>
            <th><fmt:message key="start_date_1"/>:</th>
            <th><input type="date" name="startDate"/></th>
        </tr>
        <tr>
            <th><fmt:message key="end_date_1"/>:</th>
            <th><input type="date" name="endDate"/></th>
        </tr>
        <tr>
            <th><fmt:message key="category_1"/>:</th>
            <th><select name="category">
                    <option selected="${TourCategory.REST}" value="${TourCategory.REST.id}">${TourCategory.REST}</option>
                    <option value="${TourCategory.EXCURSION.id}">${TourCategory.EXCURSION}</option>
                    <option value="${TourCategory.SHOPPING.id}">${TourCategory.SHOPPING}</option>
            </select></th>
        </tr>
        <tr>
            <th><fmt:message key="status_1"/>:</th>
            <th><select name="status">
                <option selected="${TourStatus.BURNING.id}" value="${TourStatus.BURNING.id}">${TourStatus.BURNING}</option>
                <option value="${TourStatus.NON_BURNING.id}">${TourStatus.NON_BURNING}</option>
            </select></th>
        </tr>
    </table>
    <br/><br/>

    <fmt:message key="create" var="create"/>
    <input type="submit" value="${create}"/>
    <br/><br/>

    <c:if test="${!(requestScope.error == null)}">
        <span style="color:red"><fmt:message key="${requestScope.error}"/></span>
    </c:if>

</form>
<form action="${pageContext.request.contextPath}/">
    <fmt:message key="main" var="main"/>
    <input type="submit" value="${main}"/>
</form>
</fieldset>
</body>
</html>
