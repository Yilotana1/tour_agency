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
<jsp:include page="/html/locale_buttons.html"/>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="message"/>

<h1><fmt:message key="welcome"/> ${user.firstname} ${user.lastname}</h1>
<h2><fmt:message key="information_about_you"/></h2><br/>
<b><fmt:message key="phone_1"/></b> ${user.phone},<br/>
<b><fmt:message key="phone_1"/></b> ${user.email},<br/>
<b><fmt:message key="status_1"/></b> ${user.status},<br/>
<b><fmt:message key="login_label"/></b> ${user.login} <br/><br/><br/>

<fmt:message key="logout" var="logout"/>
<form action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="${logout}">
</form>
<br/>

<fmt:message key="show_my_orders" var="show"/>
<form action="${pageContext.request.contextPath}/my_orders">
    <input type="submit" value="${show}"/>
</form>
<br/><br/><br/>

<fmt:message key="main" var="main"/>
<form action="${pageContext.request.contextPath}">
    <input type="submit" value="${main}">
</form>

</body>
</html>
