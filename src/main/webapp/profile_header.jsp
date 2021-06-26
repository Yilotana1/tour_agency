<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.touragency.model.entity.enums.UserStatus" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 26.06.2021
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        th{
            border: 0;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>

<h1><fmt:message key="welcome"/></h1>

<h2><fmt:message key="you_joined_as"/> <fmt:message key="${user.role}"/></h2>

<c:if test="${user.status.equals(UserStatus.BLOCKED)}">
    <b style="color: red"><fmt:message key="you_are_blocked"/></b>
</c:if>


<h3><fmt:message key="information_about_you"/>:</h3>
<%--<b><fmt:message key="phone_1"/></b> ${user.phone},<br/>--%>
<%--<b><fmt:message key="email_1"/></b> ${user.email},<br/>--%>
<%--<b><fmt:message key="status_1"/></b> ${user.status},<br/>--%>
<%--<b><fmt:message key="login_label"/> </b> ${user.login} <br/><br/><br/>--%>
<form action="${pageContext.request.contextPath}/edit_profile">
<table>
    <input type="hidden" name="status" value="${sessionScope.user.status.id}"/>
    <tr>
        <th><fmt:message key="firstname_1"/>:</th>
        <th><input name="firstname" value="${sessionScope.user.firstname}"/></th>
    </tr>
    <tr>
        <th><fmt:message key="lastname_1"/>:</th>
        <th><input name="lastname" value="${sessionScope.user.lastname}"/></th>
    </tr>
    <tr>
        <th><fmt:message key="phone_1"/>:</th>
        <th><input name="phone" value="${sessionScope.user.phone}"/></th>
    </tr>
    <tr>
        <th><fmt:message key="email_1"/>:</th>
        <th><input name="email" value="${sessionScope.user.email}"/></th>
    </tr>
    <tr>
        <th><fmt:message key="login_label"/>:</th>
        <th><input name="login" value="${sessionScope.user.login}"/></th>
    </tr>
    <tr>
        <th><fmt:message key="password_label"/>:</th>
        <fmt:message key="write_new_password" var="write_new_password"/>
        <th><input name="password" value="${write_new_password}"/></th>
    </tr>
</table>
<span style="color: red">${requestScope.error}</span>
<br/>

<fmt:message key="edit" var="edit"/>
    <input type="submit" value="${edit}"/>
</form>

<br/>
<br/>
<br/>

</body>
</html>
