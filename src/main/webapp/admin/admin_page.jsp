<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 17.06.2021
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>

    <style>
        <jsp:include page="/styles/style.css"/>
    </style>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>
<jsp:include page="/html/locale_buttons.html"/>
<jsp:include page="/profile_header.jsp"/>


<%--<h1><fmt:message key="welcome"/> ${user.firstname} ${user.lastname}</h1>--%>

<%--<h2 style="color: red"><fmt:message key="you_joined_as_admin"/></h2>--%>
<%--    <h3><fmt:message key="information_about_you"/></h3><br/>--%>
<%--    <b><fmt:message key="phone_1"/></b> ${user.phone},<br/>--%>
<%--    <b><fmt:message key="email_1"/></b> ${user.email},<br/>--%>
<%--    <b><fmt:message key="status_1"/></b> ${user.status},<br/>--%>
<%--    <b><fmt:message key="login_label"/> </b> ${user.login} <br/><br/><br/>--%>


<fmt:message key="logout" var="logout"/>
<form action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="${logout}">
</form>

<fmt:message key="show_my_orders" var="show"/>
<form action="${pageContext.request.contextPath}/my_orders">
    <input type="submit" value="${show}"/>
</form>

<br/><br/><br/>

<fmt:message key="main" var="main"/>
<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="${main}">
</form>

<fmt:message key="manage_users" var="manage_users"/>
<form action="${pageContext.request.contextPath}/admin/manage_users">
    <input type="submit" value="${manage_users}">
</form>

<fmt:message key="manage_orders" var="manage_orders"/>
<form action="${pageContext.request.contextPath}/admin/manage_orders">
    <input type="submit" value="${manage_orders}"/>
</form>

<fmt:message key="manage_tours" var="manage_tours"/>
<form action="${pageContext.request.contextPath}/admin/manage_tours">
    <input type="submit" value="${manage_tours}"/>
</form>

</body>
</html>