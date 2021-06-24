<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        th {
            border: 1px solid black;
        }

        td {
            border: 10px groove black;
        }
    </style>

</head>
<body>
<h1>Orders</h1>

<br/>
Search by client login:
<form>
    <input type="text" name="search"/>
    <input type="submit" value="search"/>
</form>

<br/>

<b>Show first:</b>
<br/>
<br/>
<table>
    <tr>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/admin/manage_orders">
                <c:if test="${requestScope.order.equals('opened')}">
                    <input style="color: red" type="submit" value="opened"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('opened')}">
                    <input type="submit" value="opened"/>
                </c:if>
                <input type="hidden" value="opened" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/admin/manage_orders">
                <c:if test="${requestScope.order.equals('paid')}">
                    <input style="color: red" type="submit" value="paid"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('paid')}">
                    <input type="submit" value="paid"/>
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
        <th style="color: red"><b>ID</b></th>
        <th style="color: red"><b>TOUR</b></th>
        <th style="color: red"><b>FIRSTNAME</b></th>
        <th style="color: red"><b>LASTNAME</b></th>
        <th style="color: red"><b>LOGIN</b></th>
        <th style="color: red"><b>PHONE</b></th>
        <th style="color: red"><b>EMAIL</b></th>
        <th style="color: red"><b>CREATION DATA</b></th>
        <th style="color: red"><b>PRICE</b></th>
        <th style="color: red"><b>ORDER STATUS</b></th>
    </tr>

    <c:forEach var="order" items="${requestScope.items}">
    <form action="${pageContext.request.contextPath}/admin/manage_orders">


        <tr>
            <th>${order.id}</th>
            <th>${order.tourName}</th>
            <th>${order.client.firstname}</th>
            <th>${order.client.lastname}</th>
            <th>${order.client.login}</th>
            <th>${order.client.phone}</th>
            <th>${order.client.email}</th>
            <th>${order.dateFormat}</th>
            <th>${order.price}</th>
            <th>
                <select name="status">
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
                <input type="hidden" name="id" value="${order.id}">
            </th>
            <th><input type="submit" value="update"/>
                <input type="hidden" name="order" value="${requestScope.order}">
                <input type="hidden" name="${Paginator.PAGE}" value="${requestScope.page}">
            </th>
        </tr>

    </form>
    </c:forEach>
<table/>

    <br/>
    <br/>
    <br/>
    <table>
        <tr>
            <th style="border: none">
                <form action="${pageContext.request.contextPath}/admin/manage_orders">
                    <input type="hidden" name="${Paginator.PREVIOUS_PAGE}" value="${requestScope.page}"/>
                    <input type="hidden" name="order" value="${requestScope.order}"/>
                    <input type="submit" value="previous">
                </form>
            </th>
            <c:forEach begin="1" end="${requestScope.page_count}" step="1" var="i">
                <th style="border: none">
                    <form action="${pageContext.request.contextPath}/admin/manage_orders">
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
                <form action="${pageContext.request.contextPath}/admin/manage_orders">
                    <input type="hidden" name="${Paginator.NEXT_PAGE}" value="${requestScope.page}"/>
                    <input type="hidden" name="order" value="${requestScope.order}"/>
                    <input type="submit" value="next">
                </form>
            </th>
        </tr>
    </table>

    <br/>
    <br/>
    <br/>

    <form action="${pageContext.request.contextPath}/">
        <input type="submit" value="main">
    </form>

    <form action="${pageContext.request.contextPath}/admin/admin_page.jsp">
        <input type="submit" value="profile">
    </form>


</body>
</html>
