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

<h2><fmt:message key="you_joined_as"/> <fmt:message key="${requestScope.user.role}"/></h2>

<c:if test="${requestScope.user.status.equals(UserStatus.BLOCKED)}">
    <b style="color: red"><fmt:message key="you_are_blocked"/></b>
</c:if>


<h3><fmt:message key="information_about_you"/>:</h3>
<form action="${pageContext.request.contextPath}/edit_profile">
<table>
    <input type="hidden" name="status" value="${requestScope.user.status.id}"/>
    <tr>
        <th><fmt:message key="firstname_1"/>:</th>
        <th><input name="firstname" value="${requestScope.user.firstname}"/></th>
    </tr>
    <tr>
        <th><fmt:message key="lastname_1"/>:</th>
        <th><input name="lastname" value="${requestScope.user.lastname}"/></th>
    </tr>
    <tr>
        <th><fmt:message key="phone_1"/>:</th>
        <th><input name="phone" value="${requestScope.user.phone}"/></th>
    </tr>
    <tr>
        <th><fmt:message key="email_1"/>:</th>
        <th><input name="email" value="${requestScope.user.email}"/></th>
    </tr>
    <tr>
        <th><fmt:message key="login_label"/>:</th>
        <th><input name="login" value="${requestScope.user.login}"/></th>
    </tr>
    <tr>
        <th><fmt:message key="password_label"/>:</th>
        <th><input type="password" name="password" value="${requestScope.user.password}"/></th>
    </tr>
</table>

    <c:if test="${!(requestScope.error == null)}">
        <span style="color:red"><fmt:message key="${requestScope.error}"/></span>
    </c:if>
<br/>

<fmt:message key="edit" var="edit"/>
    <input type="submit" value="${edit}"/>
</form>

<br/>
<br/>
<br/>

</body>
</html>
