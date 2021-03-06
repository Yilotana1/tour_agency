<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 24.06.2021
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <jsp:include page="styles/style.css"/>
    </style>
</head>
<body>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="message"/>

<c:if test="${!(requestScope.message == null)}">
    <h2 style="color:#0fb30f"><fmt:message key="${requestScope.message}"/></h2>
</c:if>

<c:if test="${!(requestScope.error == null)}">
    <h2 style="color:red"><fmt:message key="${requestScope.error}"/></h2>
</c:if>

<br/>
<fmt:message key="main" var="main"/>
<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="${main}">
</form>
</body>
</html>
