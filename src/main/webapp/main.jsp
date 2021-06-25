<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.touragency.model.entity.enums.Role" %>
<%@ page import="com.example.touragency.controller.commands.Paginator" %>
<%@ page import="com.example.touragency.Tools" %>
<!DOCTYPE html>


<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        <jsp:include page="styles/style.css"/>
    </style>

</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>
<jsp:include page="html/locale_buttons.html"/>
<h2 style="color:red" align="center"><b><fmt:message key="company_name"/></b></h2>


<fmt:message key="profile" var="profile"/>
<c:if test="${sessionScope.role.equals(Role.CLIENT)}">
    <form action="client/client_page.jsp">
        <input type="submit" value="${profile}"/>
    </form>
</c:if>

<c:if test="${sessionScope.role.equals(Role.ADMIN)}">
    <form action="admin/admin_page.jsp">
        <input type="submit" value="${profile}"/>
    </form>
</c:if>

<c:if test="${sessionScope.role.equals(Role.MANAGER)}">
    <form action="manager/manager_page.jsp">
        <input type="submit" value="${profile}"/>
    </form>
</c:if>

<c:if test="${sessionScope.role.equals(Role.UNKNOWN)}">
    <form action="login.jsp">
        <fmt:message key="login_button" var="login_button"/>
        <input type="submit" value="${login_button}"/>
    </form>

    <br/>

    <form action="register.jsp">
        <fmt:message key="register_button" var="register_button"/>
        <input type="submit" value="${register_button}"/>
    </form>
</c:if>
<br/>
<br/>


<fmt:message key="search_by_country"/>
<form action="${pageContext.request.contextPath}/">
    <input type="text" name="country"/>
    <input type="hidden" name="order" value="country"/>
    <fmt:message key="search" var="search"/>
    <input type="submit" value="${search}"/>
</form>

<br/>


<b><fmt:message key="show_first"/></b>
<br/>
<br/>
<table>
    <tr>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <fmt:message key="burning" var="burning"/>
                <c:if test="${requestScope.order.equals('burning')}">
                    <input style="color: red" type="submit" value="${burning}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('burning')}">
                    <input type="submit" value="${burning}"/>
                </c:if>
                <input type="hidden" value="burning" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <fmt:message key="non_burning" var="non_burning"/>
                <c:if test="${requestScope.order.equals('non_burning')}">
                    <input style="color: red" type="submit" value="${non_burning}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('non_burning')}">
                    <input type="submit" value="${non_burning}"/>
                </c:if>
                <input type="hidden" value="non_burning" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <fmt:message key="highest_stars" var="highest_stars"/>
                <c:if test="${requestScope.order.equals('high_hotel_stars')}">
                    <input style="color: red" type="submit" value="${highest_stars}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('high_hotel_stars')}">
                    <input type="submit" value="${highest_stars}"/>
                </c:if>
                <input type="hidden" value="high_hotel_stars" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <fmt:message key="lowest_stars" var="lowest_stars"/>
                <c:if test="${requestScope.order.equals('low_hotel_stars')}">
                    <input style="color: red" type="submit" value="${lowest_stars}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('low_hotel_stars')}">
                    <input type="submit" value="${lowest_stars}"/>
                </c:if>
                <input type="hidden" value="low_hotel_stars" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <fmt:message key="highest_price" var="highest_price"/>
                <c:if test="${requestScope.order.equals('high_price')}">
                    <input style="color: red" type="submit" value="${highest_price}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('high_price')}">
                    <input type="submit" value="${highest_price}"/>
                </c:if>
                <input type="hidden" value="high_price" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <fmt:message key="lowest_price" var="lowest_price"/>
                <c:if test="${requestScope.order.equals('low_price')}">
                    <input style="color: red" type="submit" value="${lowest_price}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('low_price')}">
                    <input type="submit" value="${lowest_price}"/>
                </c:if>
                <input type="hidden" value="low_price" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <fmt:message key="excursion" var="excursion"/>
                <c:if test="${requestScope.order.equals('excursion')}">
                    <input style="color: red" type="submit" value="${excursion}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('excursion')}">
                    <input type="submit" value="${excursion}"/>
                </c:if>
                <input type="hidden" value="excursion" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <fmt:message key="rest" var="rest"/>
                <c:if test="${requestScope.order.equals('rest')}">
                    <input style="color: red" type="submit" value="${rest}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('rest')}">
                    <input type="submit" value="${rest}"/>
                </c:if>
                <input type="hidden" value="rest" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <fmt:message key="shopping" var="shopping"/>
                <c:if test="${requestScope.order.equals('shopping')}">
                    <input style="color: red" type="submit" value="${shopping}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('shopping')}">
                    <input type="submit" value="${shopping}"/>
                </c:if>
                <input type="hidden" value="shopping" name="order"/>
            </form>
        </td>
    </tr>
</table>


<br/>
<br/>

<table>
    <tr>

        <th style="color: red"><b><fmt:message key="name"/></b></th>
        <th style="color: red"><b><fmt:message key="country"/></b></th>
        <th style="color: red"><b><fmt:message key="city"/></b></th>
        <th style="color: red"><b><fmt:message key="price"/></b></th>
        <th style="color: red"><b><fmt:message key="hotel_name"/></b></th>
        <th style="color: red"><b><fmt:message key="max_tickets"/></b></th>
        <th style="color: red"><b><fmt:message key="taken_tickets"/></b></th>
        <th style="color: red"><b><fmt:message key="start_date"/></b></th>
        <th style="color: red"><b><fmt:message key="end_date"/></b></th>
        <th style="color: red"><b><fmt:message key="category"/></b></th>
        <th style="color: red"><b><fmt:message key="status"/></b></th>
    </tr>

    <c:forEach var="tour" items="${requestScope.items}">
        <form action="${pageContext.request.contextPath}/order_form">
            <input type="hidden" name="id" value="${tour.id}">
            <tr>
                <th>${tour.name}</th>
                <th>${tour.country}</th>
                <th>${tour.city}</th>
                <fmt:message key="money_koef" var="koef"/>
                <th>${tour.price/koef}<fmt:message key="money"/></th>
                <th>${tour.hotel.name}</th>
                <th>${tour.maxPlaces}</th>
                <th>${tour.takenPlaces}</th>
                <th>${tour.startDateFormat}</th>
                <th>${tour.endDateFormat}</th>
                <th>${tour.category}</th>
                <th>${tour.status}</th>

                <fmt:message key="order" var="order_locale"/>
                <th><input type="submit" value="${order_locale}"/></th>
            </tr>
        </form>

    </c:forEach>
</table>

<br/>
<span style="color:red">${requestScope.error}</span>

<br/><br/>

<fmt:message key="previous" var="previous"/>
<fmt:message key="next" var="next"/>
<table>
    <tr>
        <th style="border: none">
            <form action="${pageContext.request.contextPath}/">
                <input type="hidden" name="${Paginator.PREVIOUS_PAGE}" value="${requestScope.page}"/>
                <input type="hidden" name="order" value="${requestScope.order}"/>
                <input type="submit" value="${previous}">
            </form>
        </th>
        <c:forEach begin="1" end="${requestScope.page_count}" step="1" var="i">
            <th style="border: none">
                <form action="${pageContext.request.contextPath}/">
                    <input type="hidden" name="page" value="${i}"/>
                    <input type="hidden" name="order" value="${requestScope.order}"/>
                    <c:if test="${requestScope.page.equals(i)}">
                        <input style="color: red" type="submit" value="${i}">
                    </c:if>

                    <c:if test="${!requestScope.page.equals(i)}">
                        <input type="submit" value="${i}">
                    </c:if>

                </form>
            </th>
        </c:forEach>
        <th style="border: none">
            <form action="${pageContext.request.contextPath}/">
                <input type="hidden" name="${Paginator.NEXT_PAGE}" value="${requestScope.page}"/>
                <input type="hidden" name="order" value="${requestScope.order}"/>
                <c:if test="${requestScope.order.equals('country')}">
                    <input type="hidden" name="country" value="${requestScope.country}">
                </c:if>
                <input type="submit" value="${next}">
            </form>
        </th>
    </tr>
</table>

</body>
</html>
