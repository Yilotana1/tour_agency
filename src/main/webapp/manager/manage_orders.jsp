<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.example.touragency.model.entity.enums.OrderStatus" %>
<%@ page import="com.example.touragency.controller.commands.Paginator" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 21.06.2021
  Time: 13:36
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
<%--Path that defines either request is from admin or manager--%>
<c:set var="manage_orders_page" scope="page" value="${requestScope.path}"/>

<jsp:include page="/html/locale_buttons.html"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>
<h1><fmt:message key="orders"/></h1>

<jsp:include page="/discount_form.jsp"/>


<br/>
<fmt:message key="select_by_client_login"/>
<form>
    <fmt:message key="search" var="search"/>
    <input type="text" name="search"/>
    <input type="submit" value="${search}"/>
</form>

<br/>

<fmt:message key="show_first" var="show"/>
<b>${show}</b>
<br/>
<br/>
<table>
    <tr>
        <td style="border:none">
            <form action="${manage_orders_page}">
                <fmt:message key="opened" var="opened"/>
                <c:if test="${requestScope.order.equals('opened')}">
                    <input style="color: red" type="submit" value="${opened}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('opened')}">
                    <input type="submit" value="${opened}"/>
                </c:if>
                <input type="hidden" value="opened" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <fmt:message key="paid" var="paid"/>
            <form action="${manage_orders_page}">
                <c:if test="${requestScope.order.equals('paid')}">
                    <input style="color: red" type="submit" value="${paid}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('paid')}">
                    <input type="submit" value="${paid}"/>
                </c:if>
                <input type="hidden" value="paid" name="order"/>
            </form>
        </td>
    </tr>
</table>

<br/>
<br/>

<table>

    <tr>
        <th style="color: red"><b><fmt:message key="id"/></b></th>
        <th style="color: red"><b><fmt:message key="tour"/></b></th>
        <th style="color: red"><b><fmt:message key="FIRSTNAME"/></b></th>
        <th style="color: red"><b><fmt:message key="LASTNAME"/></b></th>
        <th style="color: red"><b><fmt:message key="LOGIN"/></b></th>
        <th style="color: red"><b><fmt:message key="PHONE"/></b></th>
        <th style="color: red"><b><fmt:message key="EMAIL"/></b></th>
        <th style="color: red"><b><fmt:message key="creation_data"/> </b></th>
        <th style="color: red"><b><fmt:message key="price"/></b></th>
        <th style="color: red"><b><fmt:message key="order_status"/></b></th>
    </tr>

    <c:forEach var="order" items="${requestScope.items}">
    <form action="${manage_orders_page}">


        <tr>
            <th>${order.id}</th>
            <th>${order.tourName}</th>
            <th>${order.client.firstname}</th>
            <th>${order.client.lastname}</th>
            <th>${order.client.login}</th>
            <th>${order.client.phone}</th>
            <th>${order.client.email}</th>
            <th>${order.dateFormat}</th>
            <fmt:message key="money_koef" var="koef"/>
            <th>${order.price/koef}
                <fmt:message key="money"/></th>
            <th>
                <select name="status" style="width: 100%">
                    <c:if test="${order.status.equals(OrderStatus.OPENED)}">
                        <option selected="selected" value="${OrderStatus.OPENED.id}">${OrderStatus.OPENED}</option>
                        <option value="${OrderStatus.PAID.id}">${OrderStatus.PAID}</option>
                        <option value="${OrderStatus.CANCELED.id}">${OrderStatus.CANCELED}</option>
                    </c:if>
                    <c:if test="${order.status.equals(OrderStatus.PAID)}">
                        <option selected="selected" value="${OrderStatus.PAID.id}">${OrderStatus.PAID}</option>
                        <option value="${OrderStatus.OPENED.id}">${OrderStatus.OPENED}</option>
                        <option value="${OrderStatus.CANCELED.id}">${OrderStatus.CANCELED}</option>
                    </c:if>
                    <c:if test="${order.status.equals(OrderStatus.CANCELED)}">
                        <option selected="selected"
                                value="${OrderStatus.CANCELED.id}">${OrderStatus.CANCELED}</option>
                        <option value="${OrderStatus.PAID.id}">${OrderStatus.PAID}</option>
                        <option value="${OrderStatus.OPENED.id}">${OrderStatus.OPENED}</option>
                    </c:if>
                </select>
            </th>
            <input type="hidden" name="id" value="${order.id}">
            <fmt:message key="update" var="update"/>
            <th><input type="submit" value="${update}"/>
                <input type="hidden" name="order" value="${requestScope.order}">
                <input type="hidden" name="${Paginator.PAGE}" value="${requestScope.page}">
            </th>
        </tr>

    </form>
    </c:forEach>
    <table/>
<span style="color: red">${requestScope.error}</span>

    <br/>
    <br/>
    <br/>
    <table>
        <fmt:message key="previous" var="previous"/>
        <fmt:message key="next" var="next"/>
        <tr>
            <th style="border: none">
                <form action="${manage_orders_page}">
                    <input type="hidden" name="${Paginator.PREVIOUS_PAGE}" value="${requestScope.page}"/>
                    <input type="hidden" name="order" value="${requestScope.order}"/>
                    <input type="submit" value="${previous}">
                </form>
            </th>
            <c:forEach begin="1" end="${requestScope.page_count}" step="1" var="i">
                <th style="border: none">
                    <form action="${manage_orders_page}">
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
                <form action="${manage_orders_page}">
                    <input type="hidden" name="${Paginator.NEXT_PAGE}" value="${requestScope.page}"/>
                    <input type="hidden" name="order" value="${requestScope.order}"/>
                    <input type="submit" value="${next}">
                </form>
            </th>
        </tr>
    </table>

    <br/>
    <br/>
    <br/>

    <form action="${pageContext.request.contextPath}/">
        <fmt:message key="main" var="main"/>
        <input type="submit" value="${main}">
    </form>

    <form action="${pageContext.request.contextPath}/profile_view">
        <fmt:message key="profile" var="profile"/>
        <input type="submit" value="${profile}">
    </form>


</body>
</html>
