<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.touragency.model.entity.enums.TourStatus" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 26.06.2021
  Time: 22:55
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

<jsp:include page="/locale_buttons.jsp"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>


<h1><fmt:message key="tours"/></h1>

<br/>

<fmt:message key="search" var="search"/>
<fmt:message key="search_by_name"/>
<form action="${pageContext.request.contextPath}/manager/manage_tours">
  <input type="text" name="name"/>
  <input type="hidden" name="order" value="name"/>
  <input type="submit" value="${search}"/>
</form>

<br/>

<b><fmt:message key="show_first"/></b>
<br/>
<br/>
<table>
  <tr>
    <td style="border:none">
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
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
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
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
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
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
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
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
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
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
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
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
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
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
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
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
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
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
    <th style="color: red"><b>
      <fmt:message key="UAH" var="UAH"/>
      <fmt:message key="price"/> (${UAH})
    </b></th>
    <th style="color: red"><b><fmt:message key="hotel_name"/></b></th>
    <th style="color: red"><b><fmt:message key="max_tickets"/></b></th>
    <th style="color: red"><b><fmt:message key="taken_tickets"/></b></th>
    <th style="color: red"><b><fmt:message key="start_date"/></b></th>
    <th style="color: red"><b><fmt:message key="end_date"/></b></th>
    <th style="color: red"><b><fmt:message key="category"/></b></th>
    <th style="color: red"><b><fmt:message key="status"/></b></th>
  </tr>

  <c:forEach var="tour" items="${requestScope.items}">
    <form action="${pageContext.request.contextPath}/manager/manage_tours">
      <input type="hidden" name="id" value="${tour.id}">
      <tr>
        <th>${tour.name}</th>
        <th>${tour.country}</th>
        <th>${tour.city}</th>
        <th>${tour.price}</th>
        <th>${tour.hotel.name}</th>
        <th>${tour.maxTickets}</th>
        <th>${tour.takenTickets}</th>
        <th>${tour.startDateFormat}</th>
        <th>${tour.endDateFormat}</th>
        <th>${tour.category}</th>
        <th> <select name="status">
          <c:if test="${tour.status.equals(TourStatus.BURNING)}">
            <option selected value="${TourStatus.BURNING.id}">${TourStatus.BURNING}</option>
            <option value="${TourStatus.NON_BURNING.id}">${TourStatus.NON_BURNING}</option>
          </c:if>
          <c:if test="${tour.status.equals(TourStatus.NON_BURNING)}">
            <option selected value="${TourStatus.NON_BURNING.id}">${TourStatus.NON_BURNING}</option>
            <option value="${TourStatus.BURNING.id}">${TourStatus.BURNING}</option>
          </c:if>
        </select></th>
        <fmt:message key="update" var="update"/>
        <th><input type="submit" value="${update}"/></th>
      </tr>
    </form>

  </c:forEach>
</table>

<br/>
<c:if test="${!(requestScope.error == null)}">
  <span style="color:red"><fmt:message key="${requestScope.error}"/></span>
</c:if>

<br/><br/>

<fmt:message key="previous" var="previous"/>
<fmt:message key="next" var="next"/>
<table>
  <tr>
    <th style="border: none">
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
        <input type="hidden" name="${Paginator.PREVIOUS_PAGE}" value="${requestScope.page}"/>
        <input type="hidden" name="order" value="${requestScope.order}"/>
        <input type="submit" value="${previous}">
      </form>
    </th>
    <c:forEach begin="1" end="${requestScope.page_count}" step="1" var="i">
      <th style="border: none">
        <form action="${pageContext.request.contextPath}/manager/manage_tours">
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
      <form action="${pageContext.request.contextPath}/manager/manage_tours">
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

<fmt:message key="main" var="main"/>
<fmt:message key="profile" var="profile"/>

<form action="${pageContext.request.contextPath}/">
  <input type="submit" value="${main}">
</form>

<form action="${pageContext.request.contextPath}/profile_view">
  <input type="submit" value="${profile}">
</form>


</body>
</html>
