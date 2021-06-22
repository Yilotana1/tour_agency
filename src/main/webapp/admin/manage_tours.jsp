<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.touragency.model.entity.enums.TourCategory" %>
<%@ page import="com.example.touragency.model.entity.enums.TourStatus" %>
<%@ page import="com.example.touragency.controller.commands.Paginator" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 22.06.2021
  Time: 10:37
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
<br>

<h1>Tours</h1>
<br/>

Search by country:
<form action="${pageContext.request.contextPath}/admin/manage_tours">
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
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
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
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
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
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
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
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
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
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
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
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
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
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
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
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
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
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
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
        <form action="${pageContext.request.contextPath}/admin/manage_tours">

            <tr>
                <th><input type="text" name="tour_name" value="${tour.name}"/></th>
                <th><input type="text" name="tour_country" value="${tour.country}"/></th>
                <th><input type="text" name="tour_city" value="${tour.city}"/></th>
                <th><input type="text" name="tour_price" value="${tour.price}"/></th>
                <th>
                    <input type="text" name="tour_hotel_name" value="${tour.hotel.name}"/>
                    <input type="hidden" name="tour_hotel_id" value="${tour.hotel.id}"/>
                </th>
                <th><input type="text" name="tour_maxPlaces" value="${tour.maxPlaces}"/></th>
                <th><input type="text" name="tour_minPlaces" value="${tour.minPlaces}"/></th>
                <th>${tour.takenPlaces}</th>
                <th><input type="date" name="tour_startDate" value="${tour.startDateFormat}"/></th>
                <th><input type="date" name="tour_endDate" value="${tour.endDateFormat}"/></th>

                <th>
                    <select>
                        <c:if test="${tour.category.equals(TourCategory.EXCURSION)}">
                            <option selected>${TourCategory.EXCURSION}</option>
                            <option>${TourCategory.REST}</option>
                            <option>${TourCategory.SHOPPING}</option>
                        </c:if>
                        <c:if test="${tour.category.equals(TourCategory.REST)}">
                            <option selected>${TourCategory.REST}</option>
                            <option>${TourCategory.EXCURSION}</option>
                            <option>${TourCategory.SHOPPING}</option>
                        </c:if>
                        <c:if test="${tour.category.equals(TourCategory.SHOPPING)}">
                            <option selected>${TourCategory.SHOPPING}</option>
                            <option>${TourCategory.EXCURSION}</option>
                            <option>${TourCategory.REST}</option>
                        </c:if>
                    </select>
                </th>

                <th><select>
                    <c:if test="${tour.status.equals(TourStatus.BURNING)}">
                        <option selected>${TourStatus.BURNING}</option>
                        <option>${TourStatus.NON_BURNING}</option>
                    </c:if>
                    <c:if test="${tour.status.equals(TourStatus.NON_BURNING)}">
                        <option selected>${TourStatus.NON_BURNING}</option>
                        <option>${TourStatus.BURNING}</option>
                    </c:if>
                </select>
                </th>

                <th><input type="submit" value="update"/></th>
            </tr>
        </form>

    </c:forEach>
</table>

<br/><br/>

<table>
    <tr>
        <th style="border: none">
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
                <input type="hidden" name="${Paginator.PREVIOUS_PAGE}" value="${requestScope.page}"/>
                <input type="hidden" name="order" value="${requestScope.order}"/>
                <input type="submit" value="previous">
            </form>
        </th>
        <c:forEach begin="1" end="${requestScope.page_count}" step="1" var="i">
            <th style="border: none">
                <form action="${pageContext.request.contextPath}/admin/manage_tours">
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
            <form action="${pageContext.request.contextPath}/admin/manage_tours">
                <input type="hidden" name="${Paginator.NEXT_PAGE}" value="${requestScope.page}"/>
                <input type="hidden" name="order" value="${requestScope.order}"/>
                <c:if test="${requestScope.order.equals('country')}">
                    <input type="hidden" name="country" value="${requestScope.country}">
                </c:if>
                <input type="submit" value="${Paginator.PAGE}">
            </form>
        </th>
    </tr>
</table>

<form action="${pageContext.request.contextPath}/main.jsp">
    <input type="submit" value="main">
</form>

<form action="${pageContext.request.contextPath}/admin/admin_page.jsp">
    <input type="submit" value="profile">
</form>


</body>
</html>
