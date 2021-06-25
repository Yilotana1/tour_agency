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

<table align="right">
    <tr>
        <th>
            <form name="${pageContext.request.contextPath}">
                <input value="ua" type="submit"/>
                <input value="ua" type="hidden" name="locale"/>
            </form>
        </th>
        <th>
            <form name="${pageContext.request.contextPath}">
                <input value="en" type="submit"/>
                <input value="en" type="hidden" name="locale"/>
            </form>
        </th>
    </tr>
</table>
<h1><fmt:message key="welcome"/> ${user.firstname} ${user.lastname}</h1>
<h2 style="color: red"><fmt:message key="you_joined_as_manager"/></h2>
<h3><fmt:message key="information_about_you"/>:</h3><br/>
<b><fmt:message key="phone_1"/>:</b>${user.phone},<br/>
<b><fmt:message key="email_1"/>:</b> ${user.email},<br/>
<b><fmt:message key="status_1"/>:</b> ${user.status},<br/>
    <b><fmt:message key="login_label"/>:</b> ${user.login} <br/><br/><br/>

        <form action="${pageContext.request.contextPath}/logout">
        <fmt:message key="logout" var="logout"/>
        <input type="submit" value="${logout}">
        </form>

        <fmt:message key="show_my_orders" var="show"/>
        <form action="${pageContext.request.contextPath}/my_orders">
        <input type="submit" value="${show}"/>
        </form>

        <br/><br/><br/>

        <form action="${pageContext.request.contextPath}">
        <fmt:message key="main" var="main"/>
        <input type="submit" value="${main}">
        </form>
</body>
</html>
