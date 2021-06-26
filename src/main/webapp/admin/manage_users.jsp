<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.example.touragency.model.entity.enums.UserStatus" %>
<%@ page import="com.example.touragency.model.entity.enums.Role" %>
<%@ page import="com.example.touragency.controller.commands.Paginator" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 19.06.2021
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<head>
    <style>
        <jsp:include page="/styles/style.css"/>
    </style>

</head>
<jsp:include page="/html/locale_buttons.html"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>


<h1><fmt:message key="Users"/></h1>

<br/>

<fmt:message key="search" var="search"/>
<fmt:message key="search_by_login"/>
<form>
    <input type="text" name="search"/>
    <input type="submit" value="${search}"/>
</form>

<br/>


<b><fmt:message key="show_first"/> </b>
<br/>
<br/>
<table>
    <tr>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <fmt:message key="clients" var="clients"/>
                <c:if test="${requestScope.order.equals('clients')}">
                    <input style="color: red" type="submit" value="${clients}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('clients')}">
                    <input type="submit" value="${clients}"/>
                </c:if>
                <input type="hidden" value="clients" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <fmt:message key="managers" var="managers"/>
                <c:if test="${requestScope.order.equals('managers')}">
                    <input style="color: red" type="submit" value="${managers}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('managers')}">
                    <input type="submit" value="${managers}"/>
                </c:if>
                <input type="hidden" value="managers" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <fmt:message key="non_blocked" var="non_blocked"/>
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <c:if test="${requestScope.order.equals('non_blocked')}">
                    <input style="color: red" type="submit" value="${non_blocked}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('non_blocked')}">
                    <input type="submit" value="${non_blocked}"/>
                </c:if>
                <input type="hidden" value="non_blocked" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <fmt:message key="blocked" var="blocked"/>
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <c:if test="${requestScope.order.equals('blocked')}">
                    <input style="color: red" type="submit" value="${blocked}"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('blocked')}">
                    <input type="submit" value="${blocked}"/>
                </c:if>
                <input type="hidden" value="blocked" name="order"/>
            </form>
        </td>
    </tr>
</table>

<br/>
<br/>

<table>
    <tr>
        <th style="color: red"><b><fmt:message key="FIRSTNAME"/></b></th>
        <th style="color: red"><b><fmt:message key="LASTNAME"/></b></th>
        <th style="color: red"><b><fmt:message key="PHONE"/></b></th>
        <th style="color: red"><b><fmt:message key="LOGIN"/></b></th>
        <th style="color: red"><b><fmt:message key="STATUS"/></b></th>
        <th style="color: red"><b><fmt:message key="EMAIL"/></b></th>
        <th style="color: red"><b><fmt:message key="ROLE"/></b></th>
    </tr>
    <c:forEach var="client" items="${requestScope.items}">
        <form action="${pageContext.request.contextPath}/admin/manage_users">
            <input type="hidden" name="id" value="${client.id}"/>
            <input type="hidden" name="firstname" value="${client.firstname}"/>
            <input type="hidden" name="lastname" value="${client.lastname}"/>
            <input type="hidden" name="phone" value="${client.phone}"/>
            <input type="hidden" name="login" value="${client.login}"/>
            <input type="hidden" name="password" value="${client.password}"/>
            <input type="hidden" name="email" value="${client.email}"/>
            <tr>
                <th>${client.firstname}</th>
                <th>${client.lastname}</th>
                <th>${client.phone}</th>
                <th>${client.login}</th>
                <th>

                    <c:if test="${!client.role.equals(Role.ADMIN)}">

                        <select name="status">

                            <c:if test="${client.status.equals(UserStatus.NON_BLOCKED)}">
                                <option selected="selected"
                                        value="${UserStatus.NON_BLOCKED.id}">${UserStatus.NON_BLOCKED}</option>
                                <option value="${UserStatus.BLOCKED.id}">${UserStatus.BLOCKED}</option>
                            </c:if>

                            <c:if test="${client.status.equals(UserStatus.BLOCKED)}">
                                <option selected="selected"
                                        value="${UserStatus.BLOCKED.id}">${UserStatus.BLOCKED}</option>
                                <option value="${UserStatus.NON_BLOCKED.id}">${UserStatus.NON_BLOCKED}</option>
                            </c:if>

                        </select>

                    </c:if>

                    <c:if test="${client.role.equals(Role.ADMIN)}">
                        ${client.status}
                    </c:if>
                </th>
                <th>${client.email}</th>


                <th>
                    <c:if test="${!client.role.equals(Role.ADMIN)}">

                        <select name="role">

                            <c:if test="${client.role.equals(Role.CLIENT)}">
                                <option selected="selected" value="${Role.CLIENT.id}">${Role.CLIENT}</option>
                                <option value="${Role.MANAGER.id}">${Role.MANAGER}</option>
                            </c:if>
                            <c:if test="${client.role.equals(Role.MANAGER)}">
                                <option selected="selected" value="${Role.MANAGER.id}">${Role.MANAGER}</option>
                                <option value="${Role.CLIENT.id}">${Role.CLIENT}</option>
                            </c:if>

                        </select>

                    </c:if>

                    <c:if test="${client.role.equals(Role.ADMIN)}">
                        ${client.role}
                    </c:if>
                </th>
                <c:if test="${client.role.equals(Role.ADMIN)}">
                    <th></th>
                </c:if>
                <c:if test="${!client.role.equals(Role.ADMIN)}">
                    <th>
                        <input type="hidden" name="order" value="${requestScope.order}">
                        <input type="hidden" name="${Paginator.PAGE}" value="${requestScope.page}">
                        <fmt:message key="update" var="update"/>
                        <input type="submit" value="${update}"/>
                    </th>
                </c:if>
            </tr>
        </form>

    </c:forEach>
</table>

<br/>

<table>
    <tr>
        <th style="border: none">
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <input type="hidden" name="${Paginator.PREVIOUS_PAGE}" value="${requestScope.page}"/>
                <input type="hidden" name="order" value="${requestScope.order}"/>
                <fmt:message key="previous" var="previous"/>
                <input type="submit" value="${previous}">
            </form>
        </th>
        <c:forEach begin="1" end="${requestScope.page_count}" step="1" var="i">
            <th style="border: none">
                <form action="${pageContext.request.contextPath}/admin/manage_users">
                    <input type="hidden" name="${Paginator.PAGE}" value="${i}"/>
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
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <input type="hidden" name="${Paginator.NEXT_PAGE}" value="${requestScope.page}"/>
                <input type="hidden" name="order" value="${requestScope.order}"/>
                <fmt:message key="next" var="next"/>
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
</html>
