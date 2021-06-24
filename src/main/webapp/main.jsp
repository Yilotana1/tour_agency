<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.touragency.model.entity.enums.Role" %>
<%@ page import="com.example.touragency.controller.commands.Paginator" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
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

<h2 style="color:red" align="center"><b>TOUR AGENCY</b></h2>

<c:if test="${sessionScope.role.equals(Role.CLIENT)}">
    <form action="client/client_page.jsp">
        <input type="submit" value="profile"/>
    </form>
</c:if>

<c:if test="${sessionScope.role.equals(Role.ADMIN)}">
    <form action="admin/admin_page.jsp">
        <input type="submit" value="profile"/>
    </form>
</c:if>

<c:if test="${sessionScope.role.equals(Role.MANAGER)}">
    <form action="manager/manager_page.jsp">
        <input type="submit" value="profile"/>
    </form>
</c:if>

<c:if test="${sessionScope.role.equals(Role.UNKNOWN)}">
    <form action="login.jsp">
        <input type="submit" value="login"/>
    </form>

    <br/>

    <form action="register.jsp">
        <input type="submit" value="register"/>
    </form>
</c:if>
<br/>
<br/>


Search by country:
<form action="${pageContext.request.contextPath}/">
    <input type="text" name="country"/>
    <input type="hidden" name="order" value="country"/>
    <input type="submit" value="search"/>
</form>

<br/>



<b>Show first:</b>
<br/>
<br/>
<table>
    <tr>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <c:if test="${requestScope.order.equals('burning')}">
                    <input style="color: red" type="submit" value="burning"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('burning')}">
                    <input type="submit" value="burning"/>
                </c:if>
                <input type="hidden" value="burning" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <c:if test="${requestScope.order.equals('non_burning')}">
                    <input style="color: red" type="submit" value="non burning"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('non_burning')}">
                    <input type="submit" value="non burning"/>
                </c:if>
                <input type="hidden" value="non_burning" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <c:if test="${requestScope.order.equals('high_hotel_stars')}">
                    <input style="color: red" type="submit" value="high hotel stars"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('high_hotel_stars')}">
                    <input type="submit" value="high hotel stars"/>
                </c:if>
                <input type="hidden" value="high_hotel_stars" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <c:if test="${requestScope.order.equals('low_hotel_stars')}">
                    <input style="color: red" type="submit" value="low hotel stars"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('low_hotel_stars')}">
                    <input type="submit" value="low hotel stars"/>
                </c:if>
                <input type="hidden" value="low_hotel_stars" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <c:if test="${requestScope.order.equals('high_price')}">
                    <input style="color: red" type="submit" value="high price"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('high_price')}">
                    <input type="submit" value="high price"/>
                </c:if>
                <input type="hidden" value="high_price" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <c:if test="${requestScope.order.equals('low_price')}">
                    <input style="color: red" type="submit" value="low price"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('low_price')}">
                    <input type="submit" value="low price"/>
                </c:if>
                <input type="hidden" value="low_price" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <c:if test="${requestScope.order.equals('excursion')}">
                    <input style="color: red" type="submit" value="excursion"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('excursion')}">
                    <input type="submit" value="excursion"/>
                </c:if>
                <input type="hidden" value="excursion" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <c:if test="${requestScope.order.equals('rest')}">
                    <input style="color: red" type="submit" value="rest"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('rest')}">
                    <input type="submit" value="rest"/>
                </c:if>
                <input type="hidden" value="rest" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/">
                <c:if test="${requestScope.order.equals('shopping')}">
                    <input style="color: red" type="submit" value="shopping"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('shopping')}">
                    <input type="submit" value="shopping"/>
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
        <th style="color: red"><b>NAME</b></th>
        <th style="color: red"><b>COUNTRY</b></th>
        <th style="color: red"><b>CITY</b></th>
        <th style="color: red"><b>PRICE</b></th>
        <th style="color: red"><b>HOTEL NAME</b></th>
        <th style="color: red"><b>MAX TICKETS</b></th>
        <th style="color: red"><b>MIN TICKETS</b></th>
        <th style="color: red"><b>TAKEN TICKETS</b></th>
        <th style="color: red"><b>START DATE</b></th>
        <th style="color: red"><b>END DATE</b></th>
        <th style="color: red"><b>CATEGORY</b></th>
        <th style="color: red"><b>STATUS</b></th>
    </tr>

    <c:forEach var="tour" items="${requestScope.items}">
        <form action="${pageContext.request.contextPath}/order_form">
            <input type="hidden" name="id" value="${tour.id}">
            <tr>
                <th>${tour.name}</th>
                <th>${tour.country}</th>
                <th>${tour.city}</th>
                <th>${tour.price}</th>
                <th>${tour.hotel.name}</th>
                <th>${tour.maxPlaces}</th>
                <th>${tour.minPlaces}</th>
                <th>${tour.takenPlaces}</th>
                <th>${tour.startDateFormat}</th>
                <th>${tour.endDateFormat}</th>
                <th>${tour.category}</th>
                <th>${tour.status}</th>

                <th><input type="submit" value="order"/></th>
            </tr>
        </form>

    </c:forEach>
</table>

<br/>
<span style="color:red">${requestScope.error}</span>

<br/><br/>

<table>
    <tr>
        <th style="border: none">
            <form action="${pageContext.request.contextPath}/">
                <input type="hidden" name="${Paginator.PREVIOUS_PAGE}" value="${requestScope.page}"/>
                <input type="hidden" name="order" value="${requestScope.order}"/>
                <input type="submit" value="previous">
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
                <input type="submit" value="${Paginator.NEXT_PAGE}">
            </form>
        </th>
    </tr>
</table>

</body>
</html>
