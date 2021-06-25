<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<jsp:include page="html/locale_buttons.html"/>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="message"/>

<h2 style="color: red">${requestScope.message}</h2>

${requestScope.errorMessageTour}<br/>
${requestScope.errorMessageUser}

<fmt:message key="main" var="main"/>
<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="${main}">
</form>
</body>
</html>
